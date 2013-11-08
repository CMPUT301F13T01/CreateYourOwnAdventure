/*
FragmentInfo Class for CreateYourOwnAdventure App.
Used to get basic information about a StoryFragment
    
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
 * StoryFragmentInfo class, used to represent StoryFragments in a list.
 * For use of views that list StoryFragment objects.
 * 
 * @author Jesse Chu <jhchu@ualberta.ca>
 */

public class StoryFragmentInfo {
	
	/* Instance Variables for StoryFragmentInfo */
	private Integer id;
	private String title;
	private String description;
	
	/**
	 * Constructor. Takes a StoryFragment and extracts its info.
	 * 
	 * @param fragment the StoryFragment to get the info of
	 */
	public StoryFragmentInfo(Integer id, StoryFragment fragment) {
		this.id = id;
		this.title = fragment.getTitle();
		this.description = fragment.getDescription();
	}
	
	/**
	 * Alternate Constructor.
	 * 
	 * Initializes a null instance of FragmentInfo.
	 */
	public StoryFragmentInfo() {
		this.id = null;
		this.title = null;
		this.description = null;
	}

	/**
	 * Getter for the id.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * Getter for the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Getter for the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

}
