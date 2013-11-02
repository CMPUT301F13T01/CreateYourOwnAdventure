/*
Choice class for CreateYourOwnAdventure.
This is a simple object that contains a source ID of a
fragment, a destination ID of a fragment, and the flavour
text of the particular choice.

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

package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * This is a simple object that will contain the information
 * related to a particular choice, which will allow the
 * transition from one fragment to another while reading.
 * 
 * @author Reginald Miller, October 2013
 *
 */

public class Choice implements Serializable {

	private int sourceId;
	private int destinationId;
	private String flavourText;
	
	//No initializations
	public Choice() {
		
	}
	
	//Can choose to initialize
	public Choice(int sourceId, int destinationId, String flavourText) {
		this.sourceId = sourceId;
		this.destinationId = destinationId;
		this.flavourText = flavourText;
	}

	/**
	 * @return the fragment's source ID
	 */
	public int getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the fragment's source ID to set
	 */
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the fragment's destination ID
	 */
	public int getDestinationId() {
		return destinationId;
	}

	/**
	 * @param destinationId the fragment's destination ID to set
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	/**
	 * @return the flavour text of the choice
	 */
	public String getFlavourText() {
		return flavourText;
	}

	/**
	 * @param flavourText the flavour text to set
	 */
	public void setFlavourText(String flavourText) {
		this.flavourText = flavourText;
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(sourceId);
		out.writeObject(destinationId);
		out.writeObject(flavourText);
	}
	private void readObject(java.io.ObjectInputStream in)
		     throws IOException, ClassNotFoundException {
		sourceId = (Integer) in.readObject();
		destinationId = (Integer) in.readObject();
		flavourText = (String) in.readObject();
	}
		 
	private void readObjectNoData() 
		     throws ObjectStreamException {
	}
	
}