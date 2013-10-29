/*
Library class for CreateYourOwnAdventure.
Keeps a list of all local stories

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

/**
 * This class is designed to maintain all of the
 * stories that are stored locally on the user's
 * device.
 * 
 * @author Reginald Miller
 *
 */

public class Library {
	
	private ArrayList<Story> story_list;
	
	public Library() {
		this.story_list = new ArrayList<Story>();
	}
	
	/**
	 * Creates a new story and adds it to the list
	 */
	public void create_new_story() {
		story_list.add(new Story());
		return;
	}
	
	/**
	 * Gets the story to the list and passes it to whoever
	 * requests it. Returns null if pos is out of range.
	 * 
	 * @param pos   Story's position in list
	 * @return   Returns the story at the position
	 */
	public Story get_story(int pos) {
		if (pos >= 0 && pos < story_list.size() - 1) {
			return story_list.get(pos);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Deletes the story at a given position in the list
	 * 
	 * @param pos   Position in list of story to delete
	 * @return   Returns true if successfully deleted, if pos out of range returns false
	 */
	public boolean delete_story(int pos) {
		if (pos >= 0 && pos < story_list.size() - 1) {
			story_list.remove(pos);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Puts a new or updated story into the list of stories
	 * at the desired position.
	 * 
	 * @param pos   Position to update story
	 * @param story   Story that has been updated
	 * @return   Returns true if pos in range; returns false otherwise
	 */
	public boolean update_story(int pos, Story story) {
		if (pos >= 0 && pos < story_list.size() - 1) {
			story_list.set(pos, story);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void load_stories() {
		return;
	}
	
	public void save_stories() {
		return;
	}
	

}
