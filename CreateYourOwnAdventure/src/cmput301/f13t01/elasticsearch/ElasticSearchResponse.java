/*
ElasticSearchResponse Class for CreateYourOwnAdventure.
This is a simple class to dictate the form of an ElasticSearch query response.

This is the same code used by GitHub user rayzhangcl
at the following repository: https://github.com/rayzhangcl/ESDemo

    Copyright  �2013 rayzhangcl
    <Contact: https://github.com/rayzhangcl/ESDemo>
    
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

/**
 * A class to define the form of an ElasticSearch query response.
 * 
 * @author rayzhangcl
 *
 * @param <T>   The type the object being received from the server.
 */

public class ElasticSearchResponse<T> {
	String _index;
    String _type;
    String _id;
    T _source;
    
    /**
     * Getter for the requested object from the server.
     * 
     * @return   The object received from the server.
     */
    public T getSource() {
        return _source;
    }
}
