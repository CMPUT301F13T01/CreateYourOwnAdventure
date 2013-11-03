/*
StoryList Class for CreateYourOwnAdventure App.
Maps ID numbers to StoryInfo objects.
    
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Jesse Chu <jhchu@ualberta.ca>
 *
 * StoryList, holds all StoryInfo objects and gives each a unique ID.
 * Used by Library object.
 */

@Deprecated
public class StoryList implements Serializable {
	
	// HashMap of Story ID's to Story Objects
	private HashMap<Integer, Story> storyList;

	/**
	 * Constructor. Creates an empty HashMap for use.
	 */
	public StoryList() {
		this.storyList = new HashMap<Integer, Story>();
	}
	
	@Deprecated
	/**
	 * Returns requested Story from StoryList.
	 * Bad request returns null.
	 * 
	 * @param id ID of requested Story
	 * @return the Story associated with the given ID, null if it doesn't exist
	 */
	public Story getStory(Integer id) {
		// Story exists, is returned
		if (storyList.containsKey(id)) {
			return storyList.get(id);
		} else {
			// Story does not exist, null
			return null;
		}
	}
	
	@Deprecated
	/**
	 * Returns the ID of a given StoryFragment.
	 * 
	 * @param fragment the StoryFragment to get the ID of
	 * @return the ID of the StoryFragment, null if not found
	 */
	public Integer getStoryId(Story story){
		// Default return ID
		Integer id = null;
		// Check entrySet of the HashMap for given Story
		Set<Map.Entry<Integer, Story>> set = this.storyList.entrySet();
		for (Map.Entry<Integer, Story> entry : set) {
			if (entry.getValue() == story) {
				// Found Fragment, save its ID
				id = entry.getKey();
			}
		}
		// Return the ID
		return id;
	}
	
	@Deprecated
	/**
	 * Returns a StoryInfo object for the given ID.
	 * Used to display StoryInfo in lists.
	 * 
	 * @param id the ID of the Story to get the info of
	 * @return a StoryInfo object for that ID
	 */
	public StoryInfo getStoryInfo(Integer id) {
		// Fetch the story
		Story story = this.storyList.get(id);
		if (story != null) {
			// Return for valid id
			return new StoryInfo(id, story);
		} else {
			// Return null if not found
			return new StoryInfo();
		}
	}
	
	@Deprecated
	/**
	 * Returns an ArrayList of StoryInfo objects for
	 * all Story objects.
	 * 
	 * @return an ArrayList of all StoryInfo
	 */
	public ArrayList<StoryInfo> getStoryInfoList() {
		ArrayList<StoryInfo> storyInfoList = new ArrayList<StoryInfo>();
		for (Map.Entry<Integer, Story> entry:this.storyList.entrySet()) {
			Integer id = entry.getKey();
			Story story = entry.getValue();
			storyInfoList.add(new StoryInfo(id, story));
		}
		return storyInfoList;
	}
	
	@Deprecated
	/**
	 * Places a new story into the StoryList.
	 * A new ID is automatically generated for each story.
	 * 
	 * @param newStory Story object to add to StoryList
	 */
	public void addStory(Story newStory) {
		// Empty StoryList, first ID is 0
		Integer id = 0;
		// Non-empty StoryList, make new id
		if (!this.storyList.isEmpty()) {
			// New ID is 1 greater than the current largest ID, for uniqueness
			id = Collections.max(this.storyList.keySet()) + 1;
		}
		// Places new Story into StoryList
		this.storyList.put(id, newStory);
	}
	
	@Deprecated
	/**
	 * Removes a requested Story from the StoryList.
	 * Returns boolean based on success/failure.
	 * 
	 * @param id ID of the Story to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeStory(Integer id) {
		// Story exists, removed
		if (storyList.containsKey(id)) {
			this.storyList.remove(id);
			return true;
		} else {
			// Story does not exist, failure
			return false;
		}
	}
	
	/**
	 * Updates the Story associated with a given ID.
	 * Story ID must have been in use to be valid
	 * 
	 * @param id ID of the Story in to updated
	 * @param story Story to update to
	 * @returns true is successful, false otherwise
	 */
	public boolean updateStory(Integer id, Story story) {
		// Story ID exists, update the story
		if (storyList.containsKey(id)) {
			// Updates HashMap entry for the given ID:Story pair
			this.storyList.put(id, story);
			return true;
		} else {
			// Story ID doesn't exist, update fails
			return false;
		}	
	}
	
	
	/* Methods required for Serializable Interface */
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(storyList);
	}
	
	private void readObject(java.io.ObjectInputStream in)
	    throws IOException, ClassNotFoundException {
		this.storyList = (HashMap<Integer, Story>) in.readObject();
	}
	
	private void readObjectNoData()
	    throws ObjectStreamException{
	}
	
}
