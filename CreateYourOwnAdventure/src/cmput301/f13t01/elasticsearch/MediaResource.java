/*
MediaResource Class for CreateYourOwnAdventure.
This is a simple class to keep track of the identifier/location and the
type of a single Media object so that its location in the file hierarchy,
as well as the on the server, can be known.

     Copyright  �2013 Reginald Miller
    <Contact: rmiller3@ualberta.ca>
    
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

package cmput301.f13t01.elasticsearch;

import cmput301.f13t01.createyourownadventure.Media;
import cmput301.f13t01.createyourownadventure.MediaType;

/**
 * This is a class to keep track of the identifier/location of
 * a Media object of a given type, so that its location on the server
 * and/or in the file hierarchy can be known.
 * 
 * @author Reginald Miller
 *  
 */

public class MediaResource {
	
	private String identifier;
	private MediaType type;
	
	/**
	 * Constructor gets the media's type and identifier.
	 * 
	 * @param media   The media object to be stored on the server.
	 */
	public MediaResource(Media media) {
		this.identifier = media.getResource();
		this.type = media.getType();
	}
	
	/**
	 * Getter for the MediaResource's identifier.
	 * 
	 * @return   A String of the identifier.
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	/**
	 * Getter for the MediaResource's type.
	 * 
	 * @return   Returns the MediaType of the MediaResource.
	 */
	public MediaType getType() {
		return type;
	}
}
