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
	
	private UUID id;
	
	private Story testStory;
	
	public ESClient() {
		this.id = UUID.randomUUID();
		this.testStory = new Story();
		this.testStory.setAuthor("Reggie");
		this.testStory.setTitle("Who am I?");
		this.testStory.setDescription("This is a test");
	}
	
	private StoryInfo initializeStoryInfo() {
		
		StoryInfo info = new StoryInfo(id, testStory);
		
		return info;
	}
	
	public void postStoryInfo(StoryInfo info) throws IllegalStateException, IOException {
		HttpPost httpPost = new HttpPost("http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo");
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(info));
		} catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
		
		httpPost.setHeader("Accept","application/json");
		
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
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
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
	
	/*
	 *  TODO: Implement this to grab 30 or 50 StoryInfo objects with
	 *  proper queries
	 */
	public ArrayList<StoryInfo> getStoryInfos(UUID id){
		
		ArrayList<StoryInfo> infos = new ArrayList<StoryInfo>();
		
        try{
        	/*
        	 * TODO: This doesn't do proper query of StoryInfo objects, need specific
        	 * _id or need way to query first available 30 or 50
        	 */
        	HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301f13t01/StoryInfo");
        	//HttpGet getRequest = new HttpGet("http://cmput301.softwareprocess.es:8080/cmput301f13t01/1/3DT0jWgkRhCHnT_M4nL_rw?pretty=1");
            getRequest.addHeader("Accept","application/json");

            HttpResponse response = httpclient.execute(getRequest);

            String status = response.getStatusLine().toString();
            System.out.println(status);

            String json = getEntityContent(response);
            
            // We have to tell GSON what type we expect
            Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<StoryInfo>>(){}.getType();
            // Now we expect to get a StoryInfo response
            ElasticSearchResponse<StoryInfo> esResponse = gson.fromJson(json, elasticSearchResponseType);
            // We get the StoryInfo from it!
            StoryInfo info = esResponse.getSource();
            System.out.println(info.getTitle());
            System.out.println(info.getAuthor());
            System.out.println(info.getDescription());
            
            response.getEntity().consumeContent();
            
            infos.add(info);

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
        
        return infos;
	}
	
	public void deleteStoryInfo(UUID id) throws IOException {
        HttpDelete httpDelete = new HttpDelete("http://cmput301.softwareprocess.es:8080/cmput301f13t01/"+id.toString()+"/1");
        httpDelete.addHeader("Accept","application/json");

        HttpResponse response = httpclient.execute(httpDelete);

        String status = response.getStatusLine().toString();
        System.out.println(status);

        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String output;
        System.err.println("Output from Server -> ");
        while ((output = br.readLine()) != null) {
                System.err.println(output);
        }
        
        entity.consumeContent();
    }
	
	String getEntityContent(HttpResponse response) throws IOException {
        BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));
        String output;
        System.err.println("Output from Server -> ");
        String json = "";
        while ((output = br.readLine()) != null) {
            System.err.println(output);
            json += output;
        }
        System.err.println("JSON:"+json);
        return json;
    }
	
	//public static void main(String [] args){

        //ESClient client = new ESClient();
        //StoryInfo info = client.initializeStoryInfo();
        //System.out.println("StoryInfo has -> Title: "+info.getTitle()+", " +
         		//"Author: "+info.getAuthor()+", Description: "+info.getDescription());

        //insert the info
        //try {
            //client.postStoryInfo(info);
        //} catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //}
        
        //try {
            //client.postStoryInfo(info);
        //} catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //}
	
        //client.getStoryInfo(client.id);
        
        //try {
        	//client.deleteStoryInfo(client.id);
        //} catch (IOException e) {
        	//e.printStackTrace();
        //}
    //}
}
