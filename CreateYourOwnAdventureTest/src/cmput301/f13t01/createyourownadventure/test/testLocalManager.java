package cmput301.f13t01.createyourownadventure.test;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import cmput301.f13t01.createyourownadventure.LocalManager;
import cmput301.f13t01.createyourownadventure.MainActivity;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

public class testLocalManager extends ActivityInstrumentationTestCase2<MainActivity> {
	// Main Activity
	private MainActivity mainActivity;
	// Local Manager
	private LocalManager local;
	//Dummy UUID unassociated with any story
	private UUID uuid;
	//Holder variable for any needed ID
	private UUID currId;

	//Instantiation of a LocalManager automatically calls
	//loadStoryInfoList, so all tests inherently test this
	//too
	public testLocalManager() {
		super(MainActivity.class);
		local = new LocalManager(this.getActivity());
		uuid = UUID.randomUUID();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Get the activity and instantiate Local Manager
		mainActivity = getActivity();
		local = new LocalManager(mainActivity);
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
		local.removeStory(currId);
	}
	
	public void testGetStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		currId = local.addStory(story);
		Story testStory = local.getStory(currId);
		assertTrue(testStory.getTitle().equals("Title 1"));
		assertTrue(testStory.getAuthor().equals("Reg"));
		assertTrue(testStory.getDescription().equals("This is a test"));
		assertTrue(local.getStory(uuid) == null);
		local.removeStory(currId);
	}
	
	public void testLoadStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		currId = local.addStory(story);
		Story testStory = local.loadStory(currId);
		assertTrue(testStory.getTitle().equals("Title 1"));
		assertTrue(testStory.getAuthor().equals("Reg"));
		assertTrue(testStory.getDescription().equals("This is a test"));
		assertTrue(local.loadStory(uuid) == null);
		local.removeStory(currId);
	}
	
	public void testLoadStoryInfoList() {
		local.loadStoryInfoList();
		ArrayList<StoryInfo> infos = local.getStoryInfoList();
		assertTrue(infos.size() == 0);
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		currId = local.addStory(story);
		local.loadStoryInfoList();
		infos = local.getStoryInfoList();
		assertTrue(infos.size() == 1);
		StoryInfo info = infos.get(0);
		assertTrue(info.getTitle().equals("Title 1"));
		assertTrue(info.getAuthor().equals("Reg"));
		assertTrue(info.getDescription().equals("This is a test"));
		local.removeStory(currId);
		local.loadStoryInfoList();
		infos = local.getStoryInfoList();
		assertTrue(infos.size() == 0);
	}
	
	public void testGetStoryInfo() {
		assertTrue(local.getStoryInfo(uuid) == null);
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		currId = local.addStory(story);
		StoryInfo info = local.getStoryInfo(currId);
		assertTrue(info.getTitle().equals("Title 1"));
		assertTrue(info.getAuthor().equals("Reg"));
		assertTrue(info.getDescription().equals("This is a test"));
		local.removeStory(currId);
	}
	
	public void testAddStoryAgain() {
		Story story1 = new Story();
		story1.setTitle("Title 1");
		story1.setAuthor("Reg");
		story1.setDescription("This is a test");
		currId = local.addStory(story1);
		Story story2 = new Story();
		story2.setTitle("Title 2");
		story2.setAuthor("LARS BUMBERSHOOT");
		story2.setDescription("This is another test");
		UUID currId2 = local.addStory(story2);
		assertFalse(currId2.equals(uuid));
		assertTrue(local.getStoryInfoList().size() == 2);
		local.removeStory(currId);
		assertTrue(local.getStoryInfoList().size() == 1);
		local.removeStory(currId2);
		assertTrue(local.getStoryInfoList().size() == 0);
	}

}
