// Image encoding from xil3 and Cheok Yan Cheng here:
// http://stackoverflow.com/questions/4830711/how-to-convert-a-image-into-base64-string/4830846#4830846

package cmput301.f13t01.elasticsearch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

import android.graphics.Bitmap;
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
		// Query for existence
		// If doesn't exist: Post
		// If exists: Do nothing
		HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+type.toString()+"/"+identifier);
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
		
		if (getStatus != 404) {
			return;
		}
		
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+type.toString()+"/"+ identifier);

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
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent())); 
			String output;
			System.err.println("Output from Server -> "); 
			while ((output =br.readLine()) != null) { 
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
	
	/*
	 * TODO: Modify to have some sort of way of querying the objects based on
	 * some sort of sorting mechanism. Also need to work on how to not grab
	 * every object into the response.
	 */
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

	// Quite similar to other get methods
	/*
	 * public Bitmap getImage(String id) {
	 * 
	 * try { HttpGet getRequest = new HttpGet(
	 * "http://cmput301.softwareprocess.es:8080/cmput301f13t01/Image/" + id);
	 * 
	 * getRequest.setHeader("Accept", "application/json");
	 * 
	 * HttpResponse response = httpclient.execute(getRequest);
	 * 
	 * String status = response.getStatusLine().toString();
	 * System.out.println(status);
	 * 
	 * String json = getEntityContent(response);
	 * 
	 * // We have to tell GSON what type we expect Type
	 * elasticSearchResponseType = new
	 * TypeToken<ElasticSearchResponse<byte[]>>() { }.getType(); // Now we
	 * expect to get a StoryInfo response ElasticSearchResponse<byte[]>
	 * esResponse = gson.fromJson(json, elasticSearchResponseType); // We get
	 * the StoryInfo objects from it! byte[] b = esResponse.getSource();
	 * 
	 * response.getEntity().consumeContent();
	 * 
	 * Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
	 * 
	 * return bm;
	 * 
	 * } catch (ClientProtocolException e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * } catch (IOException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return null; }
	 */

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
		testStory(client);

	}

}
