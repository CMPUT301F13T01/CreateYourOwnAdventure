/*
LibraryManager Interface for CreateYourOwnAdventure.
This deals with the management of stories, both on disk and on ElasticSearch.
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
package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This interface is designed to interact with stored stories.
 * It is implemented for both ElasticSearch and LocalManagers.
 * Saving, Loading, and Deleting are handled.
 * 
 * @author Jesse Chu
 * 
 */

public interface LibraryManager {
	
	/**
	 * Method that returns a requested Story by ID.
	 * 
	 * @param storyId the ID of the requested Story
	 * @return the requested story
	 */
	public Story getStory(UUID storyId);
	
	/**
	 * Method to return an ArrayList of all StoryInfo
	 * that can be requested.
	 * 
	 * @return an ArrayList of all StoryInfo
	 */
	public ArrayList<StoryInfo> getStoryInfoList();
	
	/**
	 * Method to remove/delete a Story from storage.
	 * 
	 * @param storyId the ID of the Story to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeStory(UUID storyId);
	
	/**
	 * Method to save/update a Story to storage.
	 * 
	 * @param id the ID to save the Story under
	 * @param story the Story to save
	 * @return true if successful, false otherwise
	 */
	public boolean saveStory(UUID id, Story story);

}
