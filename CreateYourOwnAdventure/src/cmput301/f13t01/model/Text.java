/*
Text class for CreateYourOwnAdventure.
Holds a spannable string which represents its content.

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

import android.text.SpannableString;
import cmput301.f13t01.readstory.MediaInteractionManager;

/**
 * Class for Text type Media. Uses a SpannableString which represents the Text
 * content.
 * 
 * @author Jesse Huard
 */
@SuppressWarnings("serial")
public class Text implements Media<SpannableString>, Serializable {
	private final String type = MediaType.TEXT.toString();

	private SpannableString content;
	private MediaInteractionManager manager;

	public Text(SpannableString content) {
		this.content = content;
	}

	public Text() {
		this.content = new SpannableString("");
	}

	@Override
	public SpannableString getContent() {
		return this.content;
	}

	@Override
	public void setContent(SpannableString content) {
		this.content = content;
	}

	@Override
	public MediaInteractionManager getManager() {
		return this.manager;
	}

	@Override
	public void setManager(MediaInteractionManager manager) {
		this.manager = manager;
	}

	@Override
	public String getResource() {
		return new String();
	}

	public String toString() {
		return this.content.toString();
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(content.toString());
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		content = new SpannableString((String) in.readObject());
	}

	@Override
	public String getType() {
		return this.type;
	}

}
