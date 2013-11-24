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
					+ "You will not be able to read your story unless you set the first page."),
	BROWSE_ONLINE(
			"Select New Search to clear input boxes. Enter the Title and/or Author and/or Content to search for. "
			+ " Select Next to get next batch of found stories. Select Save icon to save a selected story to local device \n\r\n\r"
			+ " Open Overflow on actionbar to get Select Random Story and this help."),
	MAIN_SCREEN(
			"Select web icon on actionbar to browse online stories. Select + icon on actionbar to create a new story. "
			+ " Open Overflow on actionbar to get Select Random Story and this help. \n\r\n\r"
			+ "Long Click on a story to get contextual menu. In the contextual menu, select Start at Beginning or Continue to read a story. "
			+ " Select Edit Story or Delete Story to manipulate a story."),
	READ_STORY(
			"You can read the content of a story here. Select the buttons at the bottom to make a choice, if any. If there are" +
			" more than 2 available choices, there is an option to pick the choice randomly. \n\r\n\r" +
			"Select the back button to return to the previously viewed page in the story. \n\r\n\r" +
			"Select the calendar icon to return to the first page, clearing all history of pages that you have viewed for this story.");

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
