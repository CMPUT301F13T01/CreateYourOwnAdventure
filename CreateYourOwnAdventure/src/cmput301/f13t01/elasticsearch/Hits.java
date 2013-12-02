/*
Hits Class for CreateYourOwnAdventure.
This is a simple class to contain all of the desired results from
an ElasticSearch query.

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

import java.util.Collection;

/**
 * The container for all the different responses received from the server when
 * multiple objects have been queried.
 * 
 * @author Reginald Miller
 * 
 * @param <T>
 *            The type of object that is requested from the server.
 */

public class Hits<T> {

	int total;
	double max_score;
	Collection<ElasticSearchResponse<T>> hits;

	/**
	 * Getter for the collection of responses from the server.
	 * 
	 * @return A collection of server responses.
	 */
	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits;
	}

	/**
	 * The overrode toString method for error checking.
	 * 
	 * @return The string output of the data.
	 */
	public String toString() {
		return (super.toString() + "," + total + "," + max_score + "," + hits);
	}

	/**
	 * Getter for the total number of hits found on the server.
	 * 
	 * @return The total number of hits found when server queried.
	 */
	public int getTotal() {
		return total;
	}

}
