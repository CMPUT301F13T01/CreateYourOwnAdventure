package cmput301.f13t01.test;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import cmput301.f13t01.model.Story;
import cmput301.f13t01.model.StoryInfo;
import cmput301.f13t01.storylibrary.LocalManager;
import cmput301.f13t01.storylibrary.MainActivity;

public class testLocalManager extends ActivityInstrumentationTestCase2<MainActivity> {
	// Main Activity
	private MainActivity mainActivity;
	// Local Manager
	private LocalManager local;

	//Instantiation of a LocalManager automatically calls
	//loadStoryInfoList, so all tests inherently test this
	//too
	public testLocalManager() {
		super(MainActivity.class);
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
		assertTrue(local.getStoryInfoList() != null);
	}
	
	//Implicitly calls saveStoryInfoList
	@Test
	public void testAddStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		UUID currId = local.addStory(story);
		ArrayList<StoryInfo> infoList = local.getStoryInfoList();
		local.removeStory(currId);
	}
	
	public void testGetStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		UUID currId = local.addStory(story);
		Story testStory = local.getStory(currId);
		assertTrue(testStory.getTitle().equals("Title 1"));
		assertTrue(testStory.getAuthor().equals("Reg"));
		assertTrue(testStory.getDescription().equals("This is a test"));
		local.removeStory(currId);
	}
	
	public void testLoadStory() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		UUID currId = local.addStory(story);
		Story testStory = local.loadStory(currId);
		assertTrue(testStory.getTitle().equals("Title 1"));
		assertTrue(testStory.getAuthor().equals("Reg"));
		assertTrue(testStory.getDescription().equals("This is a test"));
		local.removeStory(currId);
	}
	
	public void testLoadStoryInfoList() {
		local.loadStoryInfoList();
		ArrayList<StoryInfo> infos = local.getStoryInfoList();
		int baseSize = infos.size();
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		UUID currId = local.addStory(story);
		local.loadStoryInfoList();
		infos = local.getStoryInfoList();
		assertTrue(infos.size() == baseSize + 1);
		local.removeStory(currId);
		local.loadStoryInfoList();
		infos = local.getStoryInfoList();
		assertTrue(infos.size() == baseSize);
	}
	
	public void testGetStoryInfo() {
		Story story = new Story();
		story.setTitle("Title 1");
		story.setAuthor("Reg");
		story.setDescription("This is a test");
		UUID currId = local.addStory(story);
		StoryInfo info = local.getStoryInfo(currId);
		assertTrue(info.getTitle().equals("Title 1"));
		assertTrue(info.getAuthor().equals("Reg"));
		assertTrue(info.getDescription().equals("This is a test"));
		local.removeStory(currId);
	}
	
	public void testAddStoryAgain() {
		int baseSize = local.getStoryInfoList().size();
		Story story1 = new Story();
		story1.setTitle("Title 1");
		story1.setAuthor("Reg");
		story1.setDescription("This is a test");
		UUID currId = local.addStory(story1);
		Story story2 = new Story();
		story2.setTitle("Title 2");
		story2.setAuthor("LARS BUMBERSHOOT");
		story2.setDescription("This is another test");
		UUID currId2 = local.addStory(story2);
		assertTrue(local.getStoryInfoList().size() == baseSize + 2);
		local.removeStory(currId);
		assertTrue(local.getStoryInfoList().size() == baseSize + 1);
		local.removeStory(currId2);
		assertTrue(local.getStoryInfoList().size() == baseSize);
	}

}
