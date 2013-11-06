package testSrc;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import cmput301.f13t01.createyourownadventure.LocalManager;
import cmput301.f13t01.createyourownadventure.MainActivity;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

public class TestLocalManager extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private LocalManager local;
	//Dummy UUID unassociated with any story
	private UUID uuid;
	//Holder variable for any needed ID
	private UUID currId;

	//Instantiation of a LocalManager automatically calls
	//loadStoryInfoList, so all tests inherently test this
	//too
	public TestLocalManager() {
		super(MainActivity.class);
		local = new LocalManager(this.getActivity());
		uuid = UUID.randomUUID();
	}
	
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetStoryInfoList() {
		assertTrue(local.getStoryInfoList().size() == 0);
	}
	
	//Implicitly calls saveStoryInfoList
	@Test
	public void testAddStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		currId = local.addStory(story);
		assertFalse(currId.equals(uuid));
	}
	
	public void testGetStory() {
		Story story = local.getStory(currId);
		assertTrue(story.getTitle().equals("Title 1"));
		assertTrue(story.getAuthor().equals("Reg"));
		assertTrue(story.getDescription().equals("This is a test"));
		assertTrue(local.getStory(uuid) == null);
	}
	
	public void testLoadStory() {
		Story story = local.loadStory(currId);
		assertTrue(story.getTitle().equals("Title 1"));
		assertTrue(story.getAuthor().equals("Reg"));
		assertTrue(story.getDescription().equals("This is a test"));
		assertTrue(local.loadStory(uuid) == null);
	}
	
	public void testLoadStoryInfoList() {
		local.loadStoryInfoList();
		ArrayList<StoryInfo> infos = local.getStoryInfoList();
		assertTrue(infos.size() == 1);
		StoryInfo info = infos.get(0);
		assertTrue(info.getTitle().equals("Title 1"));
		assertTrue(info.getAuthor().equals("Reg"));
		assertTrue(info.getDescription().equals("This is a test"));
	}
	
	public void testGetStoryInfo() {
		assertTrue(local.getStoryInfo(uuid) == null);
		StoryInfo info = local.getStoryInfo(currId);
		assertTrue(info.getTitle().equals("Title 1"));
		assertTrue(info.getAuthor().equals("Reg"));
		assertTrue(info.getDescription().equals("This is a test"));
	}
	
	public void testAddStoryAgain() {
		Story story = new Story();
		story.setTitle("Title 2");
		story.setAuthor("LARS BUMBERSHOOT");
		story.setDescription("This is another test");
		currId = local.addStory(story);
		assertFalse(currId.equals(uuid));
		assertTrue(local.getStoryInfoList().size() == 2);
	}

}
