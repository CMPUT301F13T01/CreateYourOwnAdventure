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

package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.Serializable;

/**
 * Class for Image type Media. Uses a String which refers to the resource name.
 */
@SuppressWarnings("serial")
public class Image implements Media<String>, Serializable {
	private final MediaType type = MediaType.IMAGE;
	
	private String content;
	private MediaInteractionManager manager;

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
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		content = (String) in.readObject();
	}

	@Override
	public MediaType getType() {
		return this.type;
	}
}
