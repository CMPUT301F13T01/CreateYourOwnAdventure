/*
ESManager Class for CreateYourOwnAdventure.
This deals with the management of stories on ElasticSearch.
All saving & loading is handled here, along with deletion.

     Copyright  ©2013 Jesse Chu
    <Contact: jhchu@ualberta.ca>
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cmput301.f13t01.elasticsearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import cmput301.f13t01.createyourownadventure.GlobalManager;
import cmput301.f13t01.createyourownadventure.LibraryManager;
import cmput301.f13t01.createyourownadventure.LocalManager;
import cmput301.f13t01.createyourownadventure.Media;
import cmput301.f13t01.createyourownadventure.MediaType;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryFragment;
import cmput301.f13t01.createyourownadventure.StoryFragmentInfo;
import cmput301.f13t01.createyourownadventure.StoryInfo;

/**
 * This class is designed to interact with stored stories on ElasticSearch.
 * Saving, Loading, and Deleting are handled.
 * 
 * @author Jesse Chu, Reginald Miller
 */

public class ESManager implements LibraryManager {

	private ESClient client;
	private LocalManager localManager;
	private Context context;

	public ESManager(Context context) {
		this.client = new ESClient();
		this.localManager = GlobalManager.getLocalManager();
		this.context = context;
	}

	/**
	 * Method that returns a requested Story by ID.
	 * 
	 * @param storyId
	 *            the ID of the requested Story
	 * @return the requested story
	 */
	public Story getStory(UUID storyId) {
		return client.getStory(storyId);
	}

	/**
	 * Method to return an ArrayList of first 20 StoryInfo objects
	 * 
	 * @return an ArrayList of all StoryInfo
	 */
	public ArrayList<StoryInfo> getStoryInfoList() {
		return client.getStoryInfos(0, 20);
	}

	/**
	 * Fetches 20 StoryInfos starting from the index start. Call this if want 20
	 * new StoryInfo objects.
	 * 
	 * @param start
	 *            The start index of which StoryInfos to fetch (equal to current
	 *            total number of StoryInfos)
	 * @return The ArrayList of StoryInfos of size 20 (or less if no more on
	 *         server)
	 */
	public ArrayList<StoryInfo> getStoryInfoList(int start) {
		return client.getStoryInfos(start, 20);
	}

	/**
	 * Saves a story from the server.
	 * 
	 * @param id
	 *            ID of the story to be posted.
	 * @param story
	 *            The story to be posted.
	 */
	public UUID downloadStory(UUID id) {
		Story story = client.getStory(id);
		UUID newId = localManager.addStory(story);
		return newId;
	}

	/**
	 * Method to remove/delete a Story and all associated files from the server.
	 * 
	 * @param storyId
	 *            the ID of the Story to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeStory(UUID storyId) {
		try {
			client.deleteStory(storyId);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			client.deleteStoryResources(storyId);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		try {
			client.deleteStoryInfo(storyId);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Publishes the story and all its resources to the server.
	 * 
	 * @param id
	 *            Current ID of the story to be published
	 * @param story
	 *            The story to be saved
	 * @return true if successful, false otherwise
	 */
	public boolean saveStory(UUID id, Story story) {

		try {
			client.postStory(id, story);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		}

		try {
			client.postStoryInfo(new StoryInfo(id, story));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		}

		// Compiles the StoryResource to post
		StoryResource storyResource = compileMediaResources(id, story);

		try {
			client.postStoryResources(storyResource);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return false;
		}

		ArrayList<MediaResource> mediaList = storyResource.getMediaResources();
		for (MediaResource resource : mediaList) {
			String identifier = resource.getIdentifier();
			MediaType type = resource.getType();
			File media = new File(context.getFilesDir().getAbsolutePath() + "/"
					+ type.toString() + "/" + identifier);
			// Convert to base64
			// TODO: Create base64 string from File object
			String encodedMedia = "";
			client.postMedia(identifier, type, encodedMedia);
		}

		return true;
	}

	private StoryResource compileMediaResources(UUID id, Story story) {
		// Instantiate return value
		StoryResource storyResource = new StoryResource(id);
		// Get list of all fragments in the story
		ArrayList<StoryFragmentInfo> fragmentList = story.getFragmentInfoList();
		// Iterate over all fragments in the story
		for (StoryFragmentInfo info : fragmentList) {
			// Get each fragment
			Integer fragmentId = info.getId();
			StoryFragment fragment = story.getFragment(fragmentId);
			// Add all content media to storyResource
			ArrayList<Media> content = fragment.getContentList();
			storyResource = extractMedia(storyResource, content);
			// Add all annotation media to storyResource
			ArrayList<Media> annotations = fragment.getAnnotationList();
			storyResource = extractMedia(storyResource, annotations);
		}
		return storyResource;
	}

	private StoryResource extractMedia(StoryResource resourceList,
			ArrayList<Media> mediaList) {
		// Check all media in given list
		for (Media media : mediaList) {
			// Type check the media
			if (media.type != MediaType.TEXT) {
				// Media is not text, generate a MediaResource object
				MediaResource resource = new MediaResource(media);
				// Add to resource list if not already in it
				if (!resourceList.contains(resource)) {
					resourceList.addMediaResource(resource);
				}
			}
		}
		return resourceList;
	}

}
