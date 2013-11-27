package cmput301.f13t01.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import cmput301.f13t01.createyourownadventure.GlobalManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

/**
 * Composes the query to be sent to ESClient
 * 
 * @author Reginald Miller, Jesse Chu
 */

public class SearchManager {
	
	public SearchManager() {
		
	}
	
	public static String createQuery(String title, String author, String description) {
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
