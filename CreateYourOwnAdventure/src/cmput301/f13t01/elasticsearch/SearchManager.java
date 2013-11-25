package cmput301.f13t01.elasticsearch;

import cmput301.f13t01.createyourownadventure.GlobalManager;

/**
 * Composes the query to be sent to ESClient
 * 
 * @author Jesse Chu, Reginald Miller
 */

public class SearchManager {
	
	ESClient client;
	
	public SearchManager() {
		client = GlobalManager.getESClient();
	}
	
	public String createQuery(String title, String author, String description) {
		String query = "{\"query\" : {\"bool\" : {\"must\" : [";
		
		query = query + searchTitle(title);
		query = query + searchAuthor(author);
		query = query + searchDescription(description);
		
		query = query + "} } }";
		
		return query;
	}
	
	// Appends the title search query to overall query
	private String searchTitle(String title) {
		
		String titleQuery = "{\"term\" : {\"title\" : \"" + parseSearch(title) + "\" } },";
		
		return titleQuery;
	}
	
	private String searchAuthor(String author) {
	
		String authorQuery = "{\"term\" : {\"author\" : \"" + parseSearch(author) + "\" } },";
		
		return authorQuery;
	}

	private String searchDescription(String description) {
		
		String descriptionQuery = "{\"term\" : {\"description\" : \"" + parseSearch(description) + "\" } } ]";
		
		return descriptionQuery;
	}
	
	// Parses string to use ElasticSearch 'AND' between all searched words
	private String parseSearch(String searchInput) {
		
		String parsedString = "";
		
		for (String str : searchInput.split("\\s+")) {
			if (!parsedString.isEmpty()) {
				parsedString = parsedString + " AND ";
			}
			parsedString = parsedString + str;
		}
		
		return parsedString;
	}
}
