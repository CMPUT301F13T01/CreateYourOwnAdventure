/*
ESClient Class for CreateYourOwnAdventure.

This handles all of the direct posting and getting access to the server
using ElasticSearch. These methods are mostly called by ESManager, as all
organizational handling is conducted there.

Many methods used in this code were adapted from code created by GitHub user rayzhangcl
at the following repository: https://github.com/rayzhangcl/ESDemo

     Copyright  �2013 Reginald Miller, Jesse Chu
    <Contact: rmiller3@ualberta.ca, jhchu@ualberta.ca>
    
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
    
    
    
    GSon/HttpClient License: Apache License, Version 2.0, January 2004.
    <http://www.apache.org/licenses/>
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package cmput301.f13t01.elasticsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cmput301.f13t01.createyourownadventure.MediaType;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * This class deals with all direct interaction with the server via
 * ElasticSearch. Contains methods pertaining to the posting, getting and
 * deletion of various objects related to stories.
 * 
 * @author Reginald Miller, Jesse Chu
 *
 */

public class ESClient {

	private HttpClient httpclient = new DefaultHttpClient();
	private Gson gson = new Gson();

	
	/**
	 * Empty constructor.
	 */
	public ESClient() {
		
	}

	/**
	 * This method is used to post a StoryInfo object to the appropriate
	 * location on the server. Its server ID is the UUID of the story it represents.
	 * 
	 * @param info   The StoryInfo object to post to the server.
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void postStoryInfo(StoryInfo info) throws IllegalStateException,
			IOException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/"
						+ info.getId().toString());
		
		postData(info, httpPost);
		
		return;
	}

	/**
	 * This method is used to post a Story object to the appropriate
	 * location on the server. Its server ID is its own UUID.
	 * 
	 * @param id   The ID of the story to be posted to the server.
	 * @param story   The Story object to be posted to the server.
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void postStory(UUID id, Story story) throws IOException,
			IllegalStateException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
						+ id.toString());

		// Need to clear history before posting
		story.clearHistory();

		postData(story, httpPost);
		
		return;
	}

	/**
	 * This method is used to post a StoryResource object to the appropriate
	 * location on the server. Its server ID is the UUID of the story it
	 * represents.
	 * 
	 * @param storyResource   The StoryResource object to be posted to the server.
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void postStoryResources(StoryResource storyResource)
			throws IOException, IllegalStateException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ storyResource.getStoryId().toString());
		
		postData(storyResource, httpPost);
		
		return;
	}

	/**
	 * This method is used to post a Media object to the appropriate
	 * location on the server. Its server ID is the identifier of the Media object
	 * that is being posted.
	 * 
	 * @param identifier   The unique identifier of the Media object, which will be its server ID.
	 * @param type   The type of the Media object, to allow it to be posted to the proper type location.
	 * @param data   The base64 String that is the encoded Media object.
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public void postMedia(String identifier, String type, String data)
			throws IOException, IllegalStateException {
		
		// Queries if media already exists on server
		HttpGet getRequest = new HttpGet(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/"
						+ type.toString() + "/" + identifier);
		getRequest.setHeader("Accept", "application/json");
		
		HttpResponse getResponse = null;
		try {
			getResponse = httpclient.execute(getRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int getStatus = -1;
		if (getResponse != null) {
			getStatus = getResponse.getStatusLine().getStatusCode();
		}

		// If get 404, request succeeded but returned nothing
		if (getStatus != 404) {
			return;
		}

		// Media not found, post to server
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/"
						+ type.toString() + "/" + identifier);
		
		postData(data, httpPost);
		
		return;
	}

	/**
	 * This method gets an ArrayList of a specified number of StoryInfo objects
	 * starting at a given index based on their ordering on the server.
	 * 
	 * @param from   The starting index from which the StoryInfo objects will be fetched.
	 * @param num   The number of StoryInfo objects to be fetched from the server.
	 * @return   The ArrayList of StoryInfo objects returned from the server. Returns null on failure.
	 */
	public ArrayList<StoryInfo> getStoryInfos(int from, int num) {

		// Make sure a positive number is passed
		if (num <= 0 || from < 0) {
			return null;
		}

		try {
			HttpPost getRequest = new HttpPost(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/_search?pretty=1");
			String query = "{\"from\" : " + from + ", \"size\" : " + num + "}";
			StringEntity stringentity = new StringEntity(query);

			getRequest.setHeader("Accept", "application/json");
			getRequest.setEntity(stringentity);

			ArrayList<StoryInfo> infos = compileStoryInfoList(getRequest);
			
			return infos;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * This method gets an ArrayList of StoryInfo objects based on a properly-constructed query String.
	 * 
	 * @param query   The properly-constructed JSON String to query the server.
	 * @param from   The starting index of the StoryInfo objects to fetch based on their ordering in the server.
	 * @param num   The number of StoryInfo objects to fetch.
	 * @return   The ArrayList of StoryInfo objects, or null if the fetch fails.
	 */
	public ArrayList<StoryInfo> getStoryInfosByQuery(String query, int from, int num) {
		
		// Make sure a positive number is passed
		if (num <= 0 || from < 0) {
			return null;
		}

		try {
			HttpPost getRequest = new HttpPost(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/_search?pretty=1");
			String comma = ",";
			if (query.isEmpty()) {
				comma = "";
			}
			query = "{\"from\" : " + from + ", \"size\" : " + num + comma + query + "}";
			StringEntity stringentity = new StringEntity(query);

			getRequest.setHeader("Accept", "application/json");
			getRequest.setEntity(stringentity);
			
			ArrayList<StoryInfo> infos = compileStoryInfoList(getRequest);

			return infos;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		
	}

	/**
	 * This method gets a Story object from the server based on a given UUID.
	 * 
	 * @param id   The UUID of the Story object to be fetched.
	 * @return   Returns the Story object, or null if the fetch fails.
	 */
	public Story getStory(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
							+ id.toString());
			getRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);
			
			String json = getJson(response);

			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>(){}.getType();
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			Story data = esResponse.getSource();

			response.getEntity().consumeContent();
			
			return data;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * This method sends a simple request to the server in order to determine how many
	 * stories currently exist on the server. This is generally used to determine which
	 * range of values is needed in order to fetch a random story.
	 * 
	 * @return   Returns an Integer object in order to help fetch a random story. Null if request fails.
	 */
	protected Integer getStoryCount() {
		
		try {
			HttpPost getRequest = new HttpPost(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/_search?pretty=1");
			String query = "{\"from\" : 0, \"size\" : 1}";
			StringEntity stringentity = new StringEntity(query);

			getRequest.setHeader("Accept", "application/json");
			getRequest.setEntity(stringentity);

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchSearchResponseType = 
					new TypeToken<ElasticSearchSearchResponse<StoryInfo>>(){}.getType();
			// Now we expect to get a StoryInfo response
			ElasticSearchSearchResponse<StoryInfo> esResponse = gson.fromJson(
					json, elasticSearchSearchResponseType);
			
			Integer total = esResponse.getTotal();

			response.getEntity().consumeContent();
			
			return total;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method fetches a Story object from the server at the given index, based on
	 * the ordering of the objects in the server.
	 * 
	 * @param index   The index of the Story object to be fetched from the server.
	 * @return   Returns the Story object fetched from the server.
	 */
	public Story getStoryByIndex(Integer index) {
		ArrayList<StoryInfo> infos = this.getStoryInfos(index, 1);
		UUID id = infos.get(0).getId();
		Story story = this.getStory(id);
		return story;
	}

	/**
	 * This method gets a base64 String encoding of a requested Media object from the server.
	 * 
	 * @param identifier   The identifier of the Media object to be fetched.
	 * @param type   The type of the Media object that is being fetched.
	 * @return   The base64 String encoding of the Media object.
	 */
	public String getMedia(String identifier, String type) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+type.toString()+"/"+identifier);
			getRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);
			
			String json = getJson(response);

			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<String>>(){}.getType();
			ElasticSearchResponse<String> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			String data = esResponse.getSource();

			response.getEntity().consumeContent();
			
			return data;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method gets the StoryResource object associated with a given UUID from the server.
	 * 
	 * @param id   The UUID of the story whose resources are being fetched from the server.
	 * @return   The StoryResource object associated with the given UUID.
	 */
	public StoryResource getStoryResources(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
							+ id.toString());

			getRequest.setHeader("Accept", "application/json");
			
			HttpResponse response = httpclient.execute(getRequest);
			
			String json = getJson(response);

			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<StoryResource>>(){}.getType();
			ElasticSearchResponse<StoryResource> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			StoryResource data = esResponse.getSource();

			response.getEntity().consumeContent();
			
			return data;
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Removes a StoryInfo object of the story associated with the given UUID.
	 * 
	 * @param id   The UUID of the story whose StoryInfo should be deleted.
	 * @throws IOException
	 */
	public void deleteStoryInfo(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");
		
		deleteData(httpDelete);
		
		return;
	}

	/**
	 * Removes a Story object of the story with the given UUID from the server.
	 * 
	 * @param id   The UUID of the story to be removed from the server.
	 * @throws IOException
	 */
	public void deleteStory(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");
		
		deleteData(httpDelete);

		return;
	}

	/**
	 * Removes a StoryResource object of the story with the given UUID from the server.
	 * 
	 * @param id   The UUID of the story whose StoryResource will be removed from the server.
	 * @throws IOException
	 */
	public void deleteStoryResources(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");

		deleteData(httpDelete);
		
		return;
	}

	private static String getEntityContent(HttpResponse response) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(response.getEntity().getContent())));
		String output;
		System.err.println("Output from Server -> ");
		String json = "";
		while ((output = br.readLine()) != null) {
			System.err.println(output);
			json += output;
		}
		System.err.println("JSON:" + json);
		return json;
	}
	
	private <T> void postData(T data, HttpPost httpPost) 
			throws IllegalStateException, IOException {
		
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(data));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	private String getJson(HttpResponse response) 
			throws ClientProtocolException, IOException {

		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);
		
		return json;
	}
	
	private void deleteData(HttpDelete httpDelete) 
			throws ClientProtocolException, IOException {
		
		HttpResponse response = httpclient.execute(httpDelete);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		HttpEntity entity = response.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent()));
		String output;
		System.err.println("Output from Server -> ");
		while ((output = br.readLine()) != null) {
			System.err.println(output);
		}

		entity.consumeContent();
		
		return;
	}
	
	private ArrayList<StoryInfo> compileStoryInfoList(HttpPost getRequest) 
			throws ClientProtocolException, IOException {

		HttpResponse response = httpclient.execute(getRequest);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		// We have to tell GSON what type we expect
		Type elasticSearchSearchResponseType = 
				new TypeToken<ElasticSearchSearchResponse<StoryInfo>>(){}.getType();
		// Now we expect to get a StoryInfo response
		ElasticSearchSearchResponse<StoryInfo> esResponse = gson.fromJson(
				json, elasticSearchSearchResponseType);
		// We get the StoryInfo objects from it!
		ArrayList<StoryInfo> infos = new ArrayList<StoryInfo>();
		for (ElasticSearchResponse<StoryInfo> r : esResponse.getHits()) {
			StoryInfo info = r.getSource();
			infos.add(info);
		}

		response.getEntity().consumeContent();
		
		return infos;
	}

}
