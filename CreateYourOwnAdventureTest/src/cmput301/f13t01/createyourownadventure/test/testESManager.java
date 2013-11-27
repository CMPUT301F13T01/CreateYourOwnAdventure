package cmput301.f13t01.createyourownadventure.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;
import cmput301.f13t01.elasticsearch.ESClient;
import cmput301.f13t01.elasticsearch.SearchManager;

public class testESManager {

	/*
	 
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
		String query = SearchManager.createQuery("x", "", "");
		
		ArrayList<StoryInfo> infos = client.getStoryInfosByQuery(query, 2, 5);
		//ArrayList<StoryInfo> infos = client.getStoryInfos(0, 20);
		
		System.out.println(client.getStoryCount());
		
		System.out.println("Size of array is: "+infos.size());
		
		System.out.println("Size of full array is : " + client.getStoryInfos(0,20).size());

	}
	 
	 */
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
