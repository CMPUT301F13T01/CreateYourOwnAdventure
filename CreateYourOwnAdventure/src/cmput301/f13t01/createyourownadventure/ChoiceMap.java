/*Add choice class with attributes sourceId, destinationId
 * and flavourText. Switch inner hashmap here to be 
 * an arraylist of these choice objects
 * 
 * update_choice(fragment id, index, choice object)
 * 
 * add_choice(fragment id, choice object)
 * 
 * delete_choice(fragment id, index)
 * 
 * get_choices(fragment id)
 * 
 * Go to src -> make new package
 */


/*
ChoiceMap class for CreateYourOwnAdventure.
Allows for access to all choices linking fragments in a story

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all methods associated with selecting
 * adding, editing or removing the choice linkages for a given story.
 * 
 * @author Reginald Miller, October 2013
 *
 */

public class ChoiceMap {

	//Maps Fragment IDs to Fragment ArrayList, to be indexed
	private HashMap<Integer, HashMap<Integer, String>> choice_mapping;
	
	public ChoiceMap() {
		this.choice_mapping = new HashMap<Integer, HashMap<Integer, String>>();
	}
	
	/**
	 * This is called whenever a previously-unlinked choice selects a
	 * new destination.
	 * 
	 * @param fragment_id   ID of fragment where choice is located
	 * @param destination_id   ID of fragment where choice is leading to
	 * @param destination_text   Text associated with the choice
	 */
	public void add_choice(Integer fragment_id, Integer destination_id, 
			String destination_text) {
		
		HashMap<Integer, String> destinations = choice_mapping.get(fragment_id);
		
		if (destinations != null) {
			destinations.put(destination_id, destination_text);
			choice_mapping.put(fragment_id, destinations);
		}
		//This occurs if no choices in the fragment exist yet
		else {
			HashMap<Integer, String> destination = new HashMap<Integer, String>();
			destination.put(destination_id, destination_text);
			choice_mapping.put(fragment_id, destination);
		}
		return;
	}
	
	/**
	 * Removes a choice from the ChoiceMap when author unlinks a choice
	 * 
	 * @param fragment_id   ID of fragment where choice is located
	 * @param destination_id   ID of fragment where choice was going to
	 * @return   Returns true if choice existed, otherwise returns false
	 */
	public boolean remove_choice(Integer fragment_id, Integer destination_id) {
		
		HashMap<Integer, String> destinations = choice_mapping.get(fragment_id);
		
		//Makes sure entry exists in HashMap and that the choice doesn't
		//index outside of length of destinations list
		if(destinations != null && destinations.containsKey(destination_id)) {
			destinations.remove(destination_id);
			choice_mapping.put(fragment_id, destinations);
			return true;
		}
		//If tried to remove a choice that doesn't exist, returns false
		else {
			return false;
		}
	}
	
	/**
	 * Switches the destination of an already-linked choice to a different
	 * destination fragment.
	 * 
	 * @param fragment_id   ID of fragment where choice is located
	 * @param destination_id   ID of fragment where choice is already linked
	 * @param newFragment_id   ID of the fragment where choice will be linked to
	 * @param destination_text   Text associated with the new choice
	 * @return   Returns true if choice already existed, false otherwise
	 */
	public boolean update_choice(Integer fragment_id, Integer destination_id,
			Integer newFragment_id, String destination_text) {
		
		HashMap<Integer, String> destinations = choice_mapping.get(fragment_id);
		
		//Insures that the choice already exists, deletes old choice from ChoiceMap
		if(destinations != null && destinations.containsKey(destination_id)) {
			destinations.remove(destination_id);
			destinations.put(newFragment_id, destination_text);
			choice_mapping.put(fragment_id, destinations);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Want this to be called when a fragment is deleted. If it doesn't
	 * exist, then there's nothing to remove. Also removes all choices
	 * currently linked to it.
	 * 
	 * @param fragment_id   ID of fragment to remove from all choices
	 */
	public void clean_fragment_references(Integer fragment_id) {
		if (choice_mapping.containsKey(fragment_id)) {
			choice_mapping.remove(fragment_id);
		}
		
		//Checks for all instances where the fragment was a 
		//destination and removes them.
		for (Integer id : choice_mapping.keySet()) {
			HashMap<Integer, String> entry = choice_mapping.get(id);
			boolean does_exist = false;
				
			//Make sure we don't remove entry while iterating
			//through it.
			for (int test_id : entry.keySet()) {
				if (test_id == fragment_id) {
					does_exist = true;
					break;
				}
			}
				
			//If choice found, it is removed
			if (does_exist == true) {
				entry.remove(fragment_id);
			}
			
			choice_mapping.put(id, entry);
		}
		return;
	}
	
	/**
	 * Returns an ArrayList of map entries for all possible choices for the fragment.
	 * Entries are sorted by fragment ID.
	 * 
	 * @param fragment_id   ID of fragment to fetch the choices for
	 * @return   Assorted ArrayList of Integer, String pairs associated with fragment ID and choice text
	 */
	public ArrayList<Map.Entry<Integer, String>> get_choices(Integer fragment_id) {
		HashMap<Integer, String> destinations = choice_mapping.get(fragment_id);
		
		if (destinations == null) {
			return null;
		}
		
		ArrayList<Map.Entry<Integer, String>> choices = 
				new ArrayList<Map.Entry<Integer, String>>();
		for (Map.Entry<Integer, String> entry : destinations.entrySet()) {
			choices.add(entry);
		}
		
		//Now want to sort ArrayList according to their fragment_id
		//(so they are consistent in how they are displayed)
		Collections.sort(choices, new Comparator<Map.Entry<Integer, String>>() {
			public int compare(Map.Entry<Integer, String> entry1,
					Map.Entry<Integer, String> entry2) {
				return entry1.getKey().compareTo(entry2.getKey());
			}
		});
		
		return choices;
	}
	
}
