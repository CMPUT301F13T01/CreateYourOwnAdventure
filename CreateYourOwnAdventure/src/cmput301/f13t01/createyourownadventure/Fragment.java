/*
Fragment Class for CreateYourOwnAdventure App.
Composed of Content objects which implement the Media interface.
    
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
 * @author Jesse Chu <jhchu@ualberta.ca>
 * 
 * Fragment object. Essentially a single page for a story.
 * Has name and description attributes.
 * Composed of Content and Annotations, which implement the Media interface.
 * Content and Annotations can be one of:
 * Text, Image, Video, Sound
 */

public class Fragment {
	
	/* Instance Variables for a Fragment */
	// Attributes for a Fragment
	private String name;
	private String description;
	
	// Content/Annotation Lists for a Fragment
	private ArrayList<Media> content_list;
	private ArrayList<Media> annotation_list;
	
	/**
	 * Constructor. Initializes all instance variables.
	 * Instantiated with default values.
	 */
	public Fragment() {
		this.name = "New Page";
		this.description = "A new page";
		this.content_list = new ArrayList<Media>();
		this.annotation_list = new ArrayList<Media>();
	}
	
	/**
	 * Getter for Fragment name.
	 * 
	 * @return the name of the Fragment
	 */
	public String get_name() {
		return this.name;
	}
	
	/**
	 * Getter for Fragment description.
	 * 
	 * @return the description of the Fragment
	 */
	public String get_description() {
		return this.description;
	}
	
	/**
	 * Setter for the name of the Fragment.
	 * 
	 * @param title the string to set the name to
	 * @return true if successful, false otherwise
	 */
	public boolean set_name(String name) {
		this.name = name;
		return true;
	}
	
	/**
	 * Setter for the description of the Fragment.
	 * 
	 * @param title the string to set the description to
	 * @return true if successful, false otherwise
	 */
	public boolean set_description(String description) {
		this.description = description;
		return true;
	}
	
	public void display_fragment() {
		
	}

	public void add_content() {
		
	}

	public void remove_content() {
		
	}
	
	public void add_annotation() {
		
	}
	
	public void remove_annotation() {
		
	}
	
}
