/*
FragmentList Class for CreateYourOwnAdventure App.
Maps ID numbers to Fragment objects.
    
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
 * FragmentList, holds all Fragment objects and gives each a unique ID.
 * Every Story object should have one of these.
 */

public class StoryFragmentList implements Serializable {
	
	// HashMap of Fragment ID's to Fragment Objects
	private HashMap<Integer, StoryFragment> fragmentList;

	/**
	 * Constructor. Creates an empty HashMap for use.
	 */
	public StoryFragmentList() {
		this.fragmentList = new HashMap<Integer, StoryFragment>();
	}
	
	/**
	 * Returns requested Fragment from FragmentList.
	 * Bad request returns null.
	 * 
	 * @param id ID of requested Fragment
	 * @return the Fragment associated with the given ID, null if it doesn't exist
	 */
	public StoryFragment getFragment(Integer id) {
		// Fragment exists, is returned
		if (fragmentList.containsKey(id)) {
			return fragmentList.get(id);
		} else {
			// Fragment does not exist, null
			return null;
		}
	}
	
	/**
	 * Returns the ID of a given StoryFragment.
	 * 
	 * @param fragment the StoryFragment to get the ID of
	 * @return the ID of the StoryFragment, null if not found
	 */
	public Integer getFragmentId(StoryFragment fragment) {
		// Default return ID
		Integer id = null;
		// Check entrySet of the HashMap for given Fragment
		Set<Map.Entry<Integer, StoryFragment>> set = this.fragmentList.entrySet();
		for (Map.Entry<Integer, StoryFragment> entry : set) {
			if (entry.getValue() == fragment) {
				// Found Fragment, save its ID
				id = entry.getKey();
			}
		}
		// Return the ID
		return id;
	}
	
	/**
	 * Returns a StoryFragmentInfo object for the given ID.
	 * Used to display StoryFragmentInfo in lists.
	 * 
	 * @param id the ID of the fragment to get the info of
	 * @return a StoryFragmentInfo object for that ID
	 */
	public StoryFragmentInfo getFragmentInfo(Integer id) {
		// Fetch the fragment
		StoryFragment fragment = this.fragmentList.get(id);
		if (fragment != null) {
			// Return for valid id
			return new StoryFragmentInfo(id, fragment);
		} else {
			// Return null if not found
			return new StoryFragmentInfo();
		}
	}
	
	/**
	 * Returns an ArrayList of StoryFragmentInfo objects for
	 * all StoryFragment objects.
	 * 
	 * @return an ArrayList of all StoryFragmentInfo
	 */
	public ArrayList<StoryFragmentInfo> getFragmentInfoList() {
		ArrayList<StoryFragmentInfo> fragmentInfoList = new ArrayList<StoryFragmentInfo>();
		for (Map.Entry<Integer, StoryFragment> entry:this.fragmentList.entrySet()) {
			Integer id = entry.getKey();
			StoryFragment fragment = entry.getValue();
			fragmentInfoList.add(new StoryFragmentInfo(id, fragment));
		}
		return fragmentInfoList;
	}
	
	/**
	 * Creates and places a new fragment into the FragmentList.
	 * A new ID is automatically generated for each fragment.
	 */
	public void addFragment() {
		// Empty FragmentList, first ID is 0
		Integer id = 0;
		// Non-empty FragmentList, make new id
		if (!this.fragmentList.isEmpty()) {
			// New ID is 1 greater than the current largest ID, for uniqueness
			id = Collections.max(this.fragmentList.keySet()) + 1;
		}
		// Creates new Fragment to insert
		StoryFragment newFragment = new StoryFragment();
		// Sets Fragment's name to Page #
		Integer pageNumber = this.fragmentList.size() + 1;
		newFragment.setTitle("Page " + pageNumber.toString());
		// Places new Fragment into FragmentList
		this.fragmentList.put(id, newFragment);
	}
	
	/**
	 * Removes a requested Fragment from the FragmentList.
	 * Returns boolean based on success/failure.
	 * 
	 * @param id ID of the Fragment to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeFragment(Integer id) {
		// Fragment exists, removed
		if (fragmentList.containsKey(id)) {
			this.fragmentList.remove(id);
			return true;
		} else {
			// Fragment does not exist, failure
			return false;
		}
	}
	
	/**
	 * Updates the Fragment associated with a given ID.
	 * Fragment ID must have been in use to be valid
	 * 
	 * @param id ID of the Fragment in to updated
	 * @param fragment Fragment to update to
	 * @returns true is successful, false otherwise
	 */
	public boolean updateFragment(Integer id, StoryFragment fragment) {
		// Fragment ID exists, update the fragment
		if (fragmentList.containsKey(id)) {
			// Updates HashMap entry for the given ID:Fragment pair
			this.fragmentList.put(id, fragment);
			return true;
		} else {
			// Fragment ID doesn't exist, update fails
			return false;
		}		
	}
	
	/**
	 * Adds an existing giving fragment to the FragmentList.
	 * 
	 * @param fragment Fragment being imported
	 */
	public void importFragment(StoryFragment fragment) {
		// ID is 1 greater than the current largest ID, for uniqueness
		Integer id = Collections.max(this.fragmentList.keySet()) + 1;
		// Places Fragment into the FragmentList with a new ID
		this.fragmentList.put(id, fragment);
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(this.fragmentList);
	}
	private void readObject(java.io.ObjectInputStream in)
	    throws IOException, ClassNotFoundException {
		this.fragmentList = (HashMap<Integer, StoryFragment>) in.readObject();
	}
	private void readObjectNoData()
	    throws ObjectStreamException{
		
	}
	
}
