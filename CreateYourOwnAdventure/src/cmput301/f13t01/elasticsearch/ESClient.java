
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

public class ESClient {

	private HttpClient httpclient = new DefaultHttpClient();
	private Gson gson = new Gson();

	public ESClient() {
		
	}

	public void postStoryInfo(StoryInfo info) throws IllegalStateException,
			IOException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo/"
						+ info.getId().toString());
		
		postData(info, httpPost);
		
		return;
	}

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

	public void postStoryResources(StoryResource storyResource)
			throws IOException, IllegalStateException {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ storyResource.getStoryId().toString());
		
		postData(storyResource, httpPost);
		
		return;
	}

	public void postMedia(String identifier, MediaType type, String data)
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
			//query = "{" + query + "}";
			//query = "{\"query\" : {\"query_string\" : {\"default_field\" : \"title\",\"query\" : \"" + "a AND h" + "\"}}}";
			//query = "{\"query\" : [{\"field\" : {\"title\" : \"" + "d AND f" + "\"}, {\"field\" : {\"author\" : \"" + "h AND j" + "\"}}, {\"field\" : {\"description\" : \"" + "f" + "\"}}";
			//query = "{\"query\" : {\"query_string\" : {\"title\" : \"" + "d AND f" + "\"}}}";
			//query = "{\"query\" : {\"bool\" : {\"must\" : [{\"field\" : {\"title\" : \"c AND h\"}}, {\"field\" : {\"author\" : \"i\"}}, {\"field\" : {\"description\" : \"f\"}}]}}}";
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

	public Story getStory(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
							+ id.toString());
			getRequest.setHeader("Accept", "application/json");

			Story story = getData(getRequest, Story.class);

			return story;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Integer getStoryCount() {
		
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
	
	public Story getStoryByIndex(Integer index) {
		ArrayList<StoryInfo> infos = this.getStoryInfos(index, 1);
		UUID id = infos.get(0).getId();
		Story story = this.getStory(id);
		return story;
	}

	public String getMedia(String identifier, MediaType type) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+type.toString()+"/"+identifier);
			getRequest.setHeader("Accept", "application/json");

			String media = getData(getRequest, String.class);

			return media;

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public StoryResource getStoryResources(UUID id) {

		try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
							+ id.toString());

			getRequest.setHeader("Accept", "application/json");
			
			StoryResource storyResource = getData(getRequest, StoryResource.class);

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
		
		deleteData(httpDelete);
		
		return;
	}

	public void deleteStory(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/Story/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");
		
		deleteData(httpDelete);

		return;
	}

	public void deleteStoryResources(UUID id) throws IOException {
		HttpDelete httpDelete = new HttpDelete(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryResource/"
						+ id.toString());
		httpDelete.addHeader("Accept", "application/json");

		deleteData(httpDelete);
		
		return;
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
	
	private <T> T getData(HttpGet getRequest, Class<T> type) 
			throws ClientProtocolException, IOException {
		
		HttpResponse response = httpclient.execute(getRequest);

		String status = response.getStatusLine().toString();
		System.out.println(status);

		String json = getEntityContent(response);

		Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<T>>(){}.getType();
		ElasticSearchResponse<T> esResponse = gson.fromJson(json,
				elasticSearchResponseType);
		T data = esResponse.getSource();

		response.getEntity().consumeContent();
		
		return data;
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
		
		//story1.setTitle("w x y z");
		//story2.setTitle("e f g");
		//story3.setTitle("v w x y z");
		
		//story1.setAuthor("a b c");
		//story2.setAuthor("d e f");
		//story3.setAuthor("g h i");
		
		//story1.setDescription("f");
		//story2.setDescription("f");
		//story3.setDescription("f");
		
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
		String query = SearchManager.createQuery("", "", "");
		
		ArrayList<StoryInfo> infos = client.getStoryInfosByQuery(query, 2, 3);
		//ArrayList<StoryInfo> infos = client.getStoryInfos(0, 20);
		
		System.out.println(client.getStoryCount());
		
		System.out.println("Size of array is: "+infos.size());
		
		System.out.println("Size of full array is : " + client.getStoryInfos(0,20).size());
		
		for (StoryInfo info : infos) {
			System.out.println(info.getTitle());
		}
	
	}

}
