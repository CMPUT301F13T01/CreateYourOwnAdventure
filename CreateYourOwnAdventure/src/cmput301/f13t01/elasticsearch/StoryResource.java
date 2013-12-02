/*
StoryResource Class for CreateYourOwnAdventure.
This is a simple class to keep track of the MediaResources for a story
with a given ID to be posted on the server upon publishing. This will permit
knowledge of the locations of associated Media objects on the server.

     Copyright  �2013 Reginald Miller
    <Contact: rmiller3@ualberta.ca>
    
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

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class keeps track of the MediaResource objects associated with a given
 * story so that the locations of Media objects on the server can be known.
 * 
 * @author Reginald Miller
 * 
 */

public class StoryResource {

	private UUID id;
	private ArrayList<MediaResource> resources;

	/**
	 * Constructor needs the UUID of the story it is associated with.
	 * Initializes the MediaResource ArrayList.
	 * 
	 * @param id
	 *            UUID of the story it is associated with.
	 */
	public StoryResource(UUID id) {
		this.id = id;
		this.resources = new ArrayList<MediaResource>();
	}

	/**
	 * Getter for the story's ID.
	 * 
	 * @return The UUID of the story.
	 */
	public UUID getStoryId() {
		return this.id;
	}

	/**
	 * Getter for the MediaResource ArrayList.
	 * 
	 * @return The ArrayList of MediaResources.
	 */
	public ArrayList<MediaResource> getMediaResources() {
		return resources;
	}

	/**
	 * Adds a MediaResource object to the list.
	 * 
	 * @param resource
	 *            The resource to add to the list.
	 */
	public void addMediaResource(MediaResource resource) {
		resources.add(resource);
		return;
	}

	/**
	 * Simple method to determine if the resource already exists within the
	 * list.
	 * 
	 * @param resource
	 *            The resource being checked against.
	 * @return Returns true if the resource already exists in the list, false
	 *         otherwise.
	 */
	public boolean contains(MediaResource resource) {

		for (MediaResource mediaResource : resources) {
			if (mediaResource.getIdentifier().equals(resource.getIdentifier())) {
				return true;
			}
		}

		return false;
	}

}
