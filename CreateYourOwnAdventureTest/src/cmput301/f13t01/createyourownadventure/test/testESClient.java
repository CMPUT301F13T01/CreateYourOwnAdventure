package cmput301.f13t01.createyourownadventure.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;
import cmput301.f13t01.elasticsearch.ESClient;
import cmput301.f13t01.elasticsearch.SearchManager;

public class testESClient extends TestCase {
	
	ESClient client;
	
	Story testStory1;
	Story testStory2;
	Story testStory3;
	
	StoryInfo storyInfo1;
	StoryInfo storyInfo2;
	StoryInfo storyInfo3;
	
	UUID id1;
	UUID id2;
	UUID id3;

	@Before
	public void setUp() throws Exception {
		
		super.setUp();
		
		testStory1 = new Story();
		testStory2 = new Story();
		testStory3 = new Story();
		
		testStory1.setTitle("1 9");
		testStory2.setTitle("1 8");
		testStory3.setTitle("1 8 9");
		
		testStory1.setAuthor("2 8");
		testStory2.setAuthor("2 9");
		testStory3.setAuthor("2 8 9");
		
		testStory1.setDescription("5 5 5 5 5 5 5");
		testStory2.setDescription("6 6 6 6 6 6 6");
		testStory3.setDescription("5 6 5 6 5 6 5 6");
		
		id1 = UUID.randomUUID();
		id2 = UUID.randomUUID();
		id3 = UUID.randomUUID();
		
		storyInfo1 = new StoryInfo(id1, testStory1);
		storyInfo2 = new StoryInfo(id2, testStory2);
		storyInfo3 = new StoryInfo(id3, testStory3);
		
		client = new ESClient();
	}
	
	@Test
	public void testPostStoryInfo() {
		
		// insert all StoryInfo objects
		try {
			client.postStoryInfo(storyInfo1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStoryInfo(storyInfo2);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStoryInfo(storyInfo3);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Sleep to guarantee objects are on server
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Grab the three StoryInfo objects at index 0
		ArrayList<StoryInfo> infos = client.getStoryInfos(0, 3);
		assertTrue(infos.size() == 3);
		
		// Grab two StoryInfo objects starting at index 1
		infos = client.getStoryInfos(1, 2);
		assertTrue(infos.size() == 2);
		
		String query = SearchManager.createQuery("9", "8", "5");
		infos = client.getStoryInfosByQuery(query, 0, 3);
		assertTrue(infos.size() == 2);
		infos = client.getStoryInfosByQuery(query, 1, 3);
		assertTrue(infos.size() == 1);
		
		query = SearchManager.createQuery("9", "8", "6");
		infos = client.getStoryInfosByQuery(query, 0, 3);
		assertTrue(infos.size() == 1);
		infos = client.getStoryInfosByQuery(query, 1, 3);
		assertTrue(infos.size() == 0);
	}
	
	@Test
	public void testPostStory() {

		// Insert all Story objects
		try {
			client.postStory(id1, testStory1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStory(id2, testStory2);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			client.postStory(id3, testStory3);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Sleep to guarantee objects are on server
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Story story = client.getStory(id1);
		assertTrue(story.getTitle().equals("1 9"));
		story = client.getStory(id2);
		assertTrue(story.getTitle().equals("1 8"));
		story = client.getStory(id3);
		assertTrue(story.getTitle().equals("1 8 9"));
	}

	@After
	public void tearDown() throws Exception {
		client.deleteStoryInfo(id1);
		client.deleteStoryInfo(id2);
		client.deleteStoryInfo(id3);
		client.deleteStory(id1);
		client.deleteStory(id2);
		client.deleteStory(id3);
		super.tearDown();
	}
}
