/*
SearchManager Class for CreateYourOwnAdventure.
This class has a single public method that can be called
statically in order to generate the proper ElasticSearch query
based on title, author and description of a story.

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import cmput301.f13t01.createyourownadventure.GlobalManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

/**
 * Composes the query String to be sent to ESClient.
 * 
 * @author Reginald Miller
 */

public class SearchManager {
	
	public SearchManager() {
		
	}
	
	/**
	 * This method is called to construct the String object that will
	 * be the search query sent to the server.
	 * 
	 * @param title   The title field of the search.
	 * @param author   The author field of the search.
	 * @param description   The description field of the search. 
	 * @return   The JSON String that is the query to be sent to ElasticSearch.
	 */
	public static String createQuery(String title, String author, String description) {
		
		if (title.isEmpty() && author.isEmpty() && description.isEmpty()) {
			return "";
		}
		
		// Not using first open brace since this will be appended with
		// number of items to front
		String query = "\"query\" : {\"bool\" : {\"must\" : [";
		
		query = query + searchTitle(title);
		if (!title.isEmpty() && (!author.isEmpty() || !description.isEmpty())) {
			query = query + ",";
		}
		query = query + searchAuthor(author);
		if (!author.isEmpty() && !description.isEmpty()) {
			query = query + ",";
		}
		query = query + searchDescription(description);
		
		query = query + "] } }";
		
		return query;
	}
	
	// Appends the title search query to overall query
	private static String searchTitle(String title) {
		
		if (!title.isEmpty()) {
			//return "{\"term\" : {\"title\" : \"" + parseSearch(title) + "\" } }";
			return "{\"field\" : {\"title\" : \"" + parseSearch(title) + "\"}}";
		}
		
		return "";
	}
	
	private static String searchAuthor(String author) {
	
		if (!author.isEmpty()){
			// return "{\"term\" : {\"author\" : \"" + parseSearch(author) + "\" } }";
			return "{\"field\" : {\"author\" : \"" + parseSearch(author) + "\"}}";
		}
		
		return "";
	}

	private static String searchDescription(String description) {
		
		if (!description.isEmpty()) {
			// return "{\"term\" : {\"description\" : \"" + parseSearch(description) + "\" } }";
			return "{\"field\" : {\"description\" : \"" + parseSearch(description) + "\"}}";
		}
		
		return "";
	}
	
	// Parses string to use ElasticSearch 'AND' between all searched words
	private static String parseSearch(String searchInput) {
		
		String parsedString = "";
		
		for (String str : searchInput.split("\\s+")) {
			if (!parsedString.isEmpty()) {
				parsedString = parsedString + " AND ";
			}
			parsedString = parsedString + str;
		}
		
		System.out.println(parsedString);
		
		return parsedString;
	}
	
}
