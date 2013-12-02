/*
ElasticSearchSearchReponse Class for CreateYourOwnAdventure.
This is a simple class to allow for the organization of multiple
objects received from the server.

Adapted heavily from code created by GitHub user rayzhangcl
at the following repository: https://github.com/rayzhangcl/ESDemo

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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class allowing for the organization of objects received from the server.
 * 
 * @author Reginald Miller
 * 
 * @param <T>
 *            The type of the objects being received from the server.
 */
public class ElasticSearchSearchResponse<T> {

	int took;
	boolean timed_out;
	transient Object _shards;
	Hits<T> hits;
	boolean exists;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits.getHits();
	}

	/**
	 * Returns a collection of the objects received from the server.
	 * 
	 * @return The collection of objects received from the server.
	 */
	public Collection<T> getSources() {
		Collection<T> out = new ArrayList<T>();
		for (ElasticSearchResponse<T> essrt : getHits()) {
			out.add(essrt.getSource());
		}
		return out;
	}

	/**
	 * The overrode toString method to output the class' data.
	 * 
	 * @return The String output of the data.
	 */
	public String toString() {
		return (super.toString() + ":" + took + "," + _shards + "," + exists
				+ "," + hits);
	}

	/**
	 * Getter for the total number of hits found on the server.
	 * 
	 * @return The total number of hits found on the server.
	 */
	public int getTotal() {
		return hits.getTotal();
	}

}
