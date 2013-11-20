/*
HelpMessage class for CreateYourOwnAdventure.
This class is used to hold the help messages for different activities 
and to allow access to those messages in a consistent manner.

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

/**
 * This class is used to hold the help messages for different 
 * activities and to allow access to those messages in a consistent manner.
 * 
 * @author Jesse Huard
 */

package cmput301.f13t01.createyourownadventure;

public enum HelpMessage {
	EDIT_STORY(
			"You can edit the basic story information here. To set the first page, press the set button. "
					+ "You will not be able to read your story unless you set the first page.");

	private String message;

	private HelpMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the message associated with this help message.
	 * 
	 * @return the string representation of the message.
	 */
	public String toString() {
		return this.message;
	}

}
