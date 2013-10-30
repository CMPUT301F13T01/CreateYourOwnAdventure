/*
Story Class for CreateYourOwnAdventure App.
Aggregates functionality and data for a single story.
    
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

/**
 * @author Jesse Chu <jhchu@ualberta.ca>
 * 
 * Story object. Basic functional unit for a story.
 * Has title, author, description, and first page attributes.
 * Aggregates History, FragmentList, and ChoiceMap objects.
 * - History tracks reader progress
 * - FragmentList is the container for the Fragments (Pages) of the story
 * - ChoiceMap stores the choices and associates for the Fragments
 */

public class Story {
	
	/* Instance Variables for a Story */
	// Story Attributes
	private String title;
	private String author;
	private String description;
	private Integer first_page;
	// Aggregated objects for functionality
	private History history_stack;
	private FragmentList fragment_list;
	private ChoiceMap choice_map;

	/**
	 * Constructor. Creates a new instance of a Story.
	 * Created with default values assigned to attributes.
	 */
	public Story() {
		this.title = "New Story";
		this.author = "";
		this.description = "A new Story";
		this.first_page = null;
		this.history_stack = new History();
		this.fragment_list = new FragmentList();
		this.choice_map = new ChoiceMap();
	}
	
	/**
	 * Getter for story title.
	 * 
	 * @return the title of the story
	 */
	public String get_title() {
		return this.title;
	}
	
	/**
	 * Getter for story author.
	 * 
	 * @return the author of the story
	 */
	public String get_author() {
		return this.author;
	}
	
	/**
	 * Getter for the description of the story.
	 * 
	 * @return the description of the story
	 */
	public String get_description() {
		return this.description;
	}
	
	/**
	 * Getter for the first fragment of the story.
	 * 
	 * @return the ID of the first fragment of the story
	 */
	public Integer get_first_page() {
		return this.first_page;
	}
	
	/**
	 * Setter for the title of the story.
	 * 
	 * @param title the string to set the title to
	 * @return true if successful, false otherwise
	 */
	public boolean set_title(String title) {
		this.title = title;
		return true;
	}
	
	/**
	 * Setter for the author of the story.
	 * 
	 * @param author the string to set the author to
	 * @return true if successful, false otherwise
	 */
	public boolean set_author(String author) {
		this.author = author;
		return true;
	}
	
	/**
	 * Setter for the description of the story.
	 * 
	 * @param description the string to set the description to
	 * @return true if successful, false otherwise
	 */
	public boolean set_description(String description) {
		this.description = description;
		return true;
	}
	
	/**
	 * Setter for the first page of the story.
	 * 
	 * @param id the id of the fragment to set as first page
	 * @return true if successful, false otherwise
	 */
	public boolean set_first_page(Integer id) {
		Fragment check_fragment = fragment_list.get_fragment(id);
		if (check_fragment != null) {
			this.first_page = id;
			return true;
		} else {
			return false;
		}
	}
	
	public Integer new_session() {
		this.history_stack.clear_history();
		return this.first_page;
	}
	
	public Integer resume_session() {
		Integer fragment_id = this.history_stack.get_most_recent();
		return fragment_id;
	}
	
	public void save_story() {
		
	}

}
