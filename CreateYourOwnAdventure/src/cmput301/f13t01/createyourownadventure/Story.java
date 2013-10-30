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

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

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

public class Story implements Serializable {
	
	/* Instance Variables for a Story */
	// Story Attributes
	private String title;
	private String author;
	private String description;
	private Integer firstPage;
	// Aggregated objects for functionality
	private History historyStack;
	private StoryFragmentList fragmentList;
	private ChoiceMap choiceMap;

	/**
	 * Constructor. Creates a new instance of a Story.
	 * Created with default values assigned to attributes.
	 */
	public Story() {
		this.title = "New Story";
		this.author = "";
		this.description = "A new Story";
		this.firstPage = null;
		this.historyStack = new History();
		this.fragmentList = new StoryFragmentList();
		this.choiceMap = new ChoiceMap();
	}
	
	/**
	 * Getter for story title.
	 * 
	 * @return the title of the story
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Getter for story author.
	 * 
	 * @return the author of the story
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * Getter for the description of the story.
	 * 
	 * @return the description of the story
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Getter for the first fragment of the story.
	 * 
	 * @return the ID of the first fragment of the story
	 */
	public Integer getFirstPage() {
		return this.firstPage;
	}
	
	/**
	 * Getter for the FragmentList object of a Story
	 * 
	 * @return the FragmentList of the Story
	 */
	public StoryFragmentList getFragmentList() {
		return this.fragmentList;
	}
	
	/**
	 * Fetches a requested StoryFragment by ID from FragmentList
	 * 
	 * @param id the id of the StoryFragment to return
	 * @return the requested StoryFragment, null if it doesn't exist
	 */
	public StoryFragment getFragment(Integer id) {
		return this.fragmentList.getFragment(id);
	}
	
	/**
	 * Getter for the ChoiceMap object of a Story
	 * 
	 * @return the ChoiceMap of the Story
	 */
	public ChoiceMap getChoiceMap() {
		return this.choiceMap;
	}
	
	/**
	 * Setter for the title of the story.
	 * 
	 * @param title the string to set the title to
	 * @return true if successful, false otherwise
	 */
	public boolean setTitle(String title) {
		this.title = title;
		return true;
	}
	
	/**
	 * Setter for the author of the story.
	 * 
	 * @param author the string to set the author to
	 * @return true if successful, false otherwise
	 */
	public boolean setAuthor(String author) {
		this.author = author;
		return true;
	}
	
	/**
	 * Setter for the description of the story.
	 * 
	 * @param description the string to set the description to
	 * @return true if successful, false otherwise
	 */
	public boolean setDescription(String description) {
		this.description = description;
		return true;
	}
	
	/**
	 * Setter for the first page of the story.
	 * 
	 * @param id the id of the fragment to set as first page
	 * @return true if successful, false otherwise
	 */
	public boolean setFirstPage(Integer id) {
		StoryFragment checkFragment = fragmentList.getFragment(id);
		if (checkFragment != null) {
			this.firstPage = id;
			return true;
		} else {
			return false;
		}
	}
	
	public Integer newSession() {
		this.historyStack.clearHistory();
		return this.firstPage;
	}
	
	public Integer resumeSession() {
		Integer fragment_id = this.historyStack.getMostRecent();
		return fragment_id;
	}
	
	public void saveStory() {
		
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		
	}
	private void readObject(java.io.ObjectInputStream in)
	    throws IOException, ClassNotFoundException {
		
	}
	private void readObjectNoData()
	    throws ObjectStreamException{
		
	}
	

}
