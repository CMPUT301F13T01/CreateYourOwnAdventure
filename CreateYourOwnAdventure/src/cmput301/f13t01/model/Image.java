/*
Image class for CreateYourOwnAdventure.
Holds a string which represents its resource string.

     Copyright  �2013 Jesse Huard
    <Contact: jhuard@ualberta.ca>
    
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

package cmput301.f13t01.model;

import java.io.IOException;
import java.io.Serializable;

import cmput301.f13t01.readstory.MediaInteractionManager;

/**
 * Class for Image type Media. Uses a String which refers to the resource name.
 * 
 * @author Jesse Huard
 * @version 1.0, 29/10/13
 */
@SuppressWarnings("serial")
public class Image implements Media<String>, Serializable {
	private static final String type = MediaType.IMAGE.toString();
	
	private String content;
	private Integer scale;
	private MediaInteractionManager manager;
	
	/**
	 * Constructor.
	 * 
	 * @param content content to set as the image
	 */
	public Image(String content) {
		this.content = content;
	}
	
	/**
	 * Empty constructor
	 */
	public Image() { }

	public Image(String content, Integer scale) {
		this.content = content;
		this.scale = scale;
	}

	/**
	 * Returns the resource identifier string.
	 * 
	 * @return the resource identifier string.
	 */
	@Override
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the resource identifier string.
	 * 
	 * @param content
	 *            the new resource identifier string.
	 */
	@Override
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	/**
	 * Returns the content string of the image
	 */
	public String getResource() {
		return getContent();
	}

	/**
	 * Gets the <code>MediaInteractionManager</code> for the image.
	 * 
	 * @return the image's <code>MediaInteractionManager</code>.
	 */
	@Override
	public MediaInteractionManager getManager() {
		return this.manager;
	}

	/**
	 * Sets the <code>MediaInteractionManager</code> of Image.
	 * 
	 * @param manager
	 *            the <code>MediaInteractionManager</code> to be used with the
	 *            Image.
	 */
	@Override
	public void setManager(MediaInteractionManager manager) {
		this.manager = manager;
	}

	/**
	 * Return a string representation of the content.
	 * 
	 * @return the string representation of the content.
	 */
	public String toString() {
		return this.content.toString();
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(content);
		out.writeObject(scale);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		content = (String) in.readObject();
		scale = (Integer) in.readObject();
	}

	@Override
	/**
	 * Override the getType function
	 */
	public String getType() {
		return Image.type;
	}

	/**
	 * Gets the scale of the image
	 * 
	 * @return the scale of the image
	 */
	public Integer getScale() {
		return scale;
	}

	/**
	 * Sets the scale of the image
	 * 
	 * @param scale the scale to set for the image
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}
}
