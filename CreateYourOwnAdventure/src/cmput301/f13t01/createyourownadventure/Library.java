/*
Library class for CreateYourOwnAdventure.
This deals with the high-level maintenance of stories and how
they are handled.

     Copyright  ©2013 Reginald Miller
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

package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is designed to maintain all of the
 * stories that are stored locally on the user's
 * device.
 * 
 * @author Reginald Miller
 *
 */

public class Library implements Serializable {
	
	private StoryList storyList;
	
	public Library() {
		this.storyList = new StoryList();
	}
	
	/**
	 * Takes a story and adds it to the list
	 */
	public void createNewStory(Story newStory) {
		storyList.addStory(newStory);
		return;
	}
	
	/**
	 * Gets the story with a given ID.
	 * 
	 * @param storyId   ID of the story
	 * @return   Returns the story with that ID
	 */
	public Story getStory(int storyId) {
		return storyList.getStory(storyId);
	}
	
	/**
	 * Deletes the story according to its ID.
	 * 
	 * @param storyId   ID of story to remove
	 */
	public void removeStory(int storyId) {
		storyList.removeStory(storyId);
		return;
	}
	
	/**
	 * Removes multiple stories at once from the storyList.
	 * 
	 * @param ArrayList of story IDs to be removed
	 */
	public void removeMultipleStories(ArrayList<Integer> stories) {
		for (int storyId : stories) {
			storyList.removeStory(storyId);
			return;
		}
	}
	
	/**
	 * Puts a new or updated story into the list of stories
	 * with the ID
	 * 
	 * @param storyId   ID of story to update
	 * @param story   Story that has been updated
	 */
	public void updateStory(int storyId, Story story) {
		storyList.updateStory(storyId, story);
		return;
	}
	
	/**
	 * Writes the Serializable StoryList object to a file.
	 * 
	 * @param out   ObjectOutputStream to write with
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(storyList);
		return;
	}
	
	/**
	 * Reads the Serializable StoryList object from a file.
	 * 
	 * @param in   ObjectInputStream to read with
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream in)
		     throws IOException, ClassNotFoundException {
		storyList = (StoryList) in.readObject();
		return;
	}
		 
	private void readObjectNoData() 
		     throws ObjectStreamException {
		return;
	}

}
