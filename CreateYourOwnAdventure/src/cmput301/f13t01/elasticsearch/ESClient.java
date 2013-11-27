// Image encoding from xil3 and Cheok Yan Cheng here:
// http://stackoverflow.com/questions/4830711/how-to-convert-a-image-into-base64-string/4830846#4830846

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

/*
 * TODO: Add methods for posting a story's resources to the 
 * server as well as getting them when needed.
 */

public class ESClient {

	private HttpClient httpclient = new DefaultHttpClient();
	private Gson gson = new Gson();

	private Story testStory;

	public ESClient() {
		this.testStory = new Story();
		this.testStory.setAuthor("Reggie");
		this.testStory.setTitle("Who am I?");
		this.testStory.setDescription("This is a test");
	}

	// For testing purposes only!
	private StoryInfo initializeStoryInfo() {

		StoryInfo info = new StoryInfo(UUID.randomUUID(), testStory);

		return info;
	}

	public void postStoryInfo(StoryInfo info) throws IllegalStateException,
			IOException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/"
						+ info.getId().toString());
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(info));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// THIS IS NEARLY DUPLICATE CODE TO POSTSTORYINFO
	// -- WILL NEED REFACTORING
	public void postStory(UUID id, Story story) throws IOException,
			IllegalStateException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
						+ id.toString());

		// Want no memory of local user's history
		story.clearHistory();

		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(story));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// THIS IS NEARLY DUPLICATE CODE TO POSTSTORYINFO
	// -- WILL NEED REFACTORING
	public void postStoryResources(StoryResource storyResource)
			throws IOException, IllegalStateException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ storyResource.getStoryId().toString());

		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(storyResource));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String status = response.getStatusLine().toString();
		System.out.println(status);
		HttpEntity entity = response.getEntity();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// THIS IS NEARLY DUPLICATE CODE TO POSTSTORYINFO
	// -- WILL NEED REFACTORING
	public void postMedia(String identifier, MediaType type, String data) {
		
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

		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(data));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse postResponse = null;

		try {
			postResponse = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String postStatus = postResponse.getStatusLine().toString();
		System.out.println(postStatus);
		HttpEntity entity = postResponse.getEntity();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String output;
			System.err.println("Output from Server -> ");
			while ((output = br.readLine()) != null) {
				System.err.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			entity.consumeContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<StoryInfo> getStoryInfos(int from, int num) {

		// Make sure a positive number is passed
		if (num <= 0 || from < 0) {
			return null;
		}

		ArrayList<StoryInfo> infos = new ArrayList<StoryInfo>();

		try {
			HttpPost getRequest = new HttpPost(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/_search?pretty=1");
			String query = "{\"from\" : " + from + ", \"size\" : " + num + "}";
			StringEntity stringentity = new StringEntity(query);

			getRequest.setHeader("Accept", "application/json");
			getRequest.setEntity(stringentity);

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<StoryInfo>>() {
			}.getType();
			// Now we expect to get a StoryInfo response
			ElasticSearchSearchResponse<StoryInfo> esResponse = gson.fromJson(
					json, elasticSearchSearchResponseType);
			// We get the StoryInfo objects from it!
			for (ElasticSearchResponse<StoryInfo> r : esResponse.getHits()) {
				StoryInfo info = r.getSource();
				infos.add(info);

			}

			response.getEntity().consumeContent();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return infos;
	}
	
	public ArrayList<StoryInfo> getStoryInfosByQuery(String query, int from, int num) {
		
		// Make sure a positive number is passed
		if (num <= 0 || from < 0) {
			return null;
		}
		
		ArrayList<StoryInfo> infos = new ArrayList<StoryInfo>();

		try {
			HttpPost getRequest = new HttpPost(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/_search?pretty=1");
			query = "{\"from\" : " + from + ", \"size\" : " + num + ", " + query + "}";
			//query = "{" + query + "}";
			//query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"title\",\"query\" : \"" + "a AND h" + "\"}}}";
			//query = "{\"query\" : [{\"field\" : {\"title\" : \"" + "d AND f" + "\"}, {\"field\" : {\"author\" : \"" + "h AND j" + "\"}}, {\"field\" : {\"description\" : \"" + "f" + "\"}}";
			//query = "{\"query\" : {\"query_string\" : {\"title\" : \"" + "d AND f" + "\"}}}";
			//query = "{\"query\" : {\"bool\" : {\"must\" : [{\"field\" : {\"title\" : \"c AND h\"}}, {\"field\" : {\"author\" : \"i\"}}, {\"field\" : {\"description\" : \"f\"}}]}}}";
			StringEntity stringentity = new StringEntity(query);

			getRequest.setHeader("Accept", "application/json");
			getRequest.setEntity(stringentity);

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<StoryInfo>>() {
			}.getType();
			// Now we expect to get a StoryInfo response
			ElasticSearchSearchResponse<StoryInfo> esResponse = gson.fromJson(
					json, elasticSearchSearchResponseType);
			// We get the StoryInfo objects from it!
			for (ElasticSearchResponse<StoryInfo> r : esResponse.getHits()) {
				StoryInfo info = r.getSource();
				infos.add(info);
			}

			response.getEntity().consumeContent();

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return infos;
		
	}

	// Similar to getStoryInfo, but different enough to
	// warrant its own method
	public Story getStory(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
							+ id.toString());
			getRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
			}.getType();
			// Now we expect to get a StoryInfo response
			ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			// We get the StoryInfo objects from it!
			Story story = esResponse.getSource();

			response.getEntity().consumeContent();

			return story;

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}
	
	public Integer getStoryCount() {
		return 0;
	}
	
	public Story getStoryByIndex(Integer index) {
		ArrayList<StoryInfo> infos = this.getStoryInfos(index, 1);
		UUID id = infos.get(0).getId();
		Story story = this.getStory(id);
		return story;
	}

	// Similar to getStoryInfo, but different enough to
	// warrant its own method
	public String getMedia(String identifier, MediaType type) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+type.toString()+"/"+identifier);
			getRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<String>>(){}.getType();
			// Now we expect to get a String response
			ElasticSearchResponse<String> esResponse = gson.fromJson(json,
					elasticSearchResponseType);
			// We get the String object from it!
			String media = esResponse.getSource();

			response.getEntity().consumeContent();

			return media;

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	// Similar to getStoryInfo, needs refactoring
	public StoryResource getStoryResources(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
							+ id.toString());

			getRequest.setHeader("Accept", "application/json");

			HttpResponse response = httpclient.execute(getRequest);

			String status = response.getStatusLine().toString();
			System.out.println(status);

			String json = getEntityContent(response);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<StoryResource>>() {
			}.getType();
			// Now we expect to get a StoryInfo response
			ElasticSearchResponse<StoryResource> esResponse = gson.fromJson(
					json, elasticSearchResponseType);
			// We get the StoryInfo objects from it!
			StoryResource storyResource = esResponse.getSource();

			response.getEntity().consumeContent();

			return storyResource;

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void deleteStoryInfo(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");

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
	}

	// This is VERY similar to deleteStoryInfo, will need refactoring!
	public void deleteStory(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");

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
	}

	// This is VERY similar to deleteStoryInfo, will need refactoring!
	public void deleteStoryResources(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");

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
	}

	// This is VERY similar to deleteStoryInfo, will need refactoring!
	public void deleteImage(String id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Image/"
						+ id);
		httpDelete.addHeader("Accept", "application/json");

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
	}

	private String getEntityContent(HttpResponse response) throws IOException {
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

	// For testing purposes only
	public static void testStoryInfo(ESClient client) {
		StoryInfo info1 = client.initializeStoryInfo();
		StoryInfo info2 = client.initializeStoryInfo();
		StoryInfo info3 = client.initializeStoryInfo();
		// System.out.println("StoryInfo has -> Title: "+info.getTitle()+", " +
		// "Author: "+info.getAuthor()+", Description: "+info.getDescription());

		// insert all StoryInfo objects

		try {
			client.postStoryInfo(info1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStoryInfo(info2);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStoryInfo(info3);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Guarantee that all info is posted and retrievable
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ArrayList<StoryInfo> infos = client.getStoryInfos(0, 50);

		System.out.println("Size of arraylist is: " + infos.size());

		try {
			client.deleteStoryInfo(info1.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.deleteStoryInfo(info2.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.deleteStoryInfo(info3.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// For testing purposes only
	public static void testStory(ESClient client) {

		UUID testId = UUID.randomUUID();

		try {
			client.postStory(testId, client.testStory);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		// Guarantee that all info is posted and retrievable
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Story story = client.getStory(testId);

		System.out.println("Story has -> Title: " + story.getTitle()
				+ ", Author: " + story.getAuthor() + ", Description: "
				+ story.getDescription());

		try {
			client.deleteStory(testId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Story otherStory = client.getStory(testId);

		if (otherStory == null) {
			System.out.println("Non-existent story");
		} else {
			System.out.println("YA BLEW IT");
		}

		return;
	}

	// For testing purposes only
	public static void main(String[] args) {

		ESClient client = new ESClient();

		// Test posting, getting or deleting of StoryInfo objects
		// testStoryInfo(client);

		// Test posting, getting or deleting of Story objects
		// testStory(client);
		
		//Story story1 = new Story();
		//Story story2 = new Story();
		//Story story3 = new Story();
		
		//story1.setTitle("a b c d e f g");
		//story2.setTitle("e f g h i j");
		//story3.setTitle("x y z");
		
		//story1.setAuthor("g h i j k l m");
		//story2.setAuthor("n o p q r s t u v");
		//story3.setAuthor("l m n o p q");
		
		//story1.setDescription("f f f f f f f");
		//story2.setDescription("a b c d e f g");
		//story3.setDescription("");
		
		//StoryInfo storyInfo1 = new StoryInfo(UUID.randomUUID(), story1);
		//StoryInfo storyInfo2 = new StoryInfo(UUID.randomUUID(), story2);
		//StoryInfo storyInfo3 = new StoryInfo(UUID.randomUUID(), story3);
		
		//try {
			//client.postStoryInfo(storyInfo1);
		//} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		//try {
			//client.postStoryInfo(storyInfo2);
		//} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		//try {
			//client.postStoryInfo(storyInfo3);
		//} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Expect 1 StoryInfo object from this query
		String query = SearchManager.createQuery("f g", "", "f");
		
		ArrayList<StoryInfo> infos = client.getStoryInfosByQuery(query, 0, 1);
		//ArrayList<StoryInfo> infos = client.getStoryInfos(0, 20);
		
		System.out.println("Size of array is: "+infos.size());

	}

}
