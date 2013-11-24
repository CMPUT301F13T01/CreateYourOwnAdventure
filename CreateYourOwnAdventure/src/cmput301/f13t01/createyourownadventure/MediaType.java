/*
MediaType enum for CreateYourOwnAdventure.
This is an enumeration that classifies all the possible media types
that will be viewable in a story fragment.

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

/**
 * Enumeration that classifies the media types that 
 * are available.
 * 
 * @author Jesse Huard
 *
 */

public enum MediaType {
	TEXT, IMAGE, SOUND, VIDEO, IMAGEURI;

	@Override
	public String toString() {
		return name();
	}
}