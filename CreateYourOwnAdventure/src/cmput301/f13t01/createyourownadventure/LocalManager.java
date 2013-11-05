/*
Library class for CreateYourOwnAdventure.
This deals with the management of stories on disk.
All saving & loading is handled here, along with deletion.

     Copyright  ©2013 Reginald Miller, Jesse Chu
    <Contact: rmiller3@ualberta.ca, jhchu@ualberta.ca>
    
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import android.content.Context;


/**
 * This class is designed to maintain all of the
 * stories that are stored locally on the user's
 * device. Saving, Loading, and Deleting are
 * handled.
 * 
 * @author Reginald Miller, Jesse Chu
 *
 */

public class LocalManager implements Serializable {
	
	// TODO: Take a Context
	// TODO: Handle saving/loading of StoryInfoList (Hard-coded)
	/* Instance Variables for Library */
	// DO NOT DO ANYTHING WITH loadedId AND loadedStory!!!
	private Context context;
	private Integer loadedId;
	private Story loadedStory;
	private HashMap<Integer, StoryInfo> storyInfoList;
	
	public LocalManager(Context context) {
		this.context = context;
		this.loadStoryInfoList();
	}
	
	/**
	 * Gets the story with a given ID.
	 * 
	 * @param storyId   ID of the story
	 * @return   Returns the story with that ID
	 */
	public Story getStory(Integer storyId) {
		if (this.storyInfoList.keySet().contains(storyId)) {
			this.loadStory(storyId);
			return this.loadedStory;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a StoryInfo object for the given ID.
	 * Used to display StoryInfo in lists.
	 * 
	 * @param id the ID of the Story to get the info of
	 * @return a StoryInfo object for that ID
	 */
	public StoryInfo getStoryInfo(Integer storyId) {
		if (this.storyInfoList.keySet().contains(storyId)) {
			return this.storyInfoList.get(storyId);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns an ArrayList of StoryInfo objects for
	 * all Story objects.
	 * 
	 * @return an ArrayList of all StoryInfo
	 */
	public ArrayList<StoryInfo> getStoryInfoList() {
		// TODO: Sort the list consistently before return
		return new ArrayList<StoryInfo>(this.storyInfoList.values());
	}

	/**
	 * Takes a story and adds it to the list
	 * 
	 * @param story The Story object to add to the Library
	 */
	public void addStory(Story story) {
		// Empty StoryList, first ID is 0
		Integer id = 0;
		// Non-empty StoryList, make new id
		if (!this.storyInfoList.isEmpty()) {
			// New ID is 1 greater than the current largest ID, for uniqueness
			id = Collections.max(this.storyInfoList.keySet()) + 1;
		}
		
		// Places new StoryInfo into StoryInfoList
		StoryInfo newStoryInfo = new StoryInfo(id, story); 
		this.storyInfoList.put(id, newStoryInfo);
		this.saveStoryInfoList();
		
		// Save currently loaded Story
		this.saveStory();
		// Save newly added Story
		loadedId = id;
		loadedStory = story;
		this.saveStory();
	}
	
	/**
	 * Deletes the story according to its ID.
	 * 
	 * @param storyId   ID of story to remove
	 */
	public boolean removeStory(Integer storyId) {
		// Story exists, removed
		if (this.storyInfoList.containsKey(storyId)) {
			// Remove from storyInfoList
			this.storyInfoList.remove(storyId);
			this.saveStoryInfoList();
			// Delete file for Story
			File storyFile = new File(storyId.toString()+".story");
			storyFile.delete();
			// Clear story from local memory if loaded
			if (this.loadedId == storyId) {
				this.loadedId = null;
				this.loadedStory = null;
			}
			return true;
		} else {
			// Story does not exist, failure
			return false;
		}
	}
	
	/**
	 * Removes multiple stories at once from the storyList.
	 * 
	 * @param ArrayList of story IDs to be removed
	 */
	public void removeMultipleStories(ArrayList<Integer> stories) {
		for (Integer storyId : stories) {
			this.removeStory(storyId);
		}
	}
	
	/**
	 * Puts a new or updated story into the list of stories
	 * with the ID
	 * 
	 * @param storyId   ID of story to update
	 * @param story   Story that has been updated
	 */
	public void updateStory(Integer storyId, Story story) {
		// Save currently loaded story
		this.saveStory();
		// Update storyInfoList
		StoryInfo newStoryInfo = new StoryInfo(storyId, story); 
		this.storyInfoList.put(storyId, newStoryInfo);
		this.saveStoryInfoList();
		// Save given story
		this.loadedId = storyId;
		this.loadedStory = story;
		this.saveStory();
	}
	
	/**
	 * Save a Story into a file.
	 * 
	 * @param id The ID of the Story to save
	 */
	public void saveStory() {
		// Save only if there is a Story to save
		if (this.loadedId != null) {
			// Attempts to save a Story to a file
			try{
			    // Generate the save file name
			    String saveFile = this.loadedId.toString()+".story";
				// Output streams to save Story object
				FileOutputStream fos = context.openFileOutput(saveFile, Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// Save the Story
	            oos.writeObject(this.loadedStory);
				fos.close();
			} catch (FileNotFoundException e) {
				// Write access error
				e.printStackTrace();
			} catch (IOException e) {
				// Something went wrong
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Load a Story from a file.
	 * 
 	 * @param id The ID of the Story to save
	 */
	public void loadStory(Integer id) {
	    // Generate the save file name
	    String saveFile = id.toString()+".story";
		// Attempts to Story from file
		try {
			// Input streams to load Story object
			FileInputStream fis = context.openFileInput(saveFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Loads the Story from file
			this.loadedId = id;
			this.loadedStory = (Story) ois.readObject();
			fis.close();
		} catch (FileNotFoundException e) {
			// Save what is loaded
			this.saveStory();
			// Load a null
			this.loadedId = null;
			this.loadedStory = null;
		} catch (IOException e) {
			// Something messed up
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		}
	}
	
	/**
	 * Saves the storyInfoList of the Library to file.
	 * 
	 * Filename is hard coded.
	 */
	public void saveStoryInfoList() {
		// Attempts to save StoryInfoList to a file
		try{
		    // Generate the save file name
		    String saveFile = "StoryInfoList.sav";
			// Output streams to save storyInfoList
			FileOutputStream fos = context.openFileOutput(saveFile, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Save the storyInfoList
            oos.writeObject(this.storyInfoList);
			fos.close();
		} catch (FileNotFoundException e) {
			// Write access error
			e.printStackTrace();
		} catch (IOException e) {
			// Something went wrong
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the storyInfoList of the Library from file.
	 * 
	 * Filename is hard coded.
	 */
	public void loadStoryInfoList() {
	    // Generate the save file name
	    String saveFile = "StoryInfoList.sav";
		// Attempts to Story from file
		try {
			// Input streams to load storyInfoList
			FileInputStream fis = context.openFileInput(saveFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Loads the storyInfoList
			this.storyInfoList = (HashMap<Integer, StoryInfo>) ois.readObject();
			fis.close();
		} catch (FileNotFoundException e) {
			// No existing file
			this.storyInfoList = new HashMap<Integer, StoryInfo>();
			this.saveStoryInfoList();
		} catch (IOException e) {
			// Something messed up
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes the Serializable StoryList object to a file.
	 * 
	 * @param out   ObjectOutputStream to write with
	 * @throws IOException
	 */
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(this.storyInfoList);
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
		this.storyInfoList = (HashMap<Integer, StoryInfo>) in.readObject();
		return;
	}
		 
	private void readObjectNoData() 
		     throws ObjectStreamException {
		return;
	}

}
