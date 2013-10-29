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

import java.util.Collections;
import java.util.HashMap;

/**
 * @author Jesse Chu <jhchu@ualberta.ca>
 *
 * FragmentList, holds all Fragment objects and gives each a unique ID.
 * Every Story object should have one of these.
 */

public class FragmentList {
	
	// HashMap of Fragment ID's to Fragment Objects
	private HashMap<Integer, Fragment> fragment_list;

	/**
	 * Constructor. Creates an empty HashMap for use.
	 */
	public FragmentList() {
		this.fragment_list = new HashMap<Integer, Fragment>();
	}
	
	/**
	 * Creates and places a new fragment into the FragmentList.
	 * A new ID is automatically generated for each fragment.
	 */
	public void add_fragment() {
		// Empty FragmentList, first ID is 0
		Integer id = 0;
		// Non-empty FragmentList, make new id
		if (!this.fragment_list.isEmpty()) {
			// New ID is 1 greater than the current largest ID, for uniqueness
			id = Collections.max(this.fragment_list.keySet()) + 1; 
		}
		// Creates new Fragment to insert
		Fragment new_fragment = new Fragment();
		// Sets Fragment's name to Page #
		Integer page_number = this.fragment_list.size() + 1;
		new_fragment.set_name("Page " + page_number.toString());
		// Places new Fragment into FragmentList
		this.fragment_list.put(id, new_fragment);
	}
	
	/**
	 * Removes a requested Fragment from the FragmentList.
	 * Returns boolean based on success/failure.
	 * 
	 * @param id ID of the Fragment to remove
	 * @return true if successful, false otherwise
	 */
	public boolean remove_fragment(Integer id) {
		// Fragment exists, removed
		if (fragment_list.containsKey(id)) {
			this.fragment_list.remove(id);
			return true;
		} else {
			// Fragment does not exist, failure
			return false;
		}
	}

	/**
	 * Returns requested Fragment from FragmentList.
	 * Bad request returns null.
	 * 
	 * @param id ID of requested Fragment
	 * @return the Fragment associated with the given ID, null if it doesn't exist
	 */
	public Fragment get_fragment(Integer id) {
		// Fragment exists, is returned
		if (fragment_list.containsKey(id)) {
			return fragment_list.get(id);
		} else {
			// Fragment does not exist, null
			return null;
		}
	}
}
