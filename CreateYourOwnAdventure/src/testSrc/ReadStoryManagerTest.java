package testSrc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.ReadStoryManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryFragment;

public class ReadStoryManagerTest extends TestCase {
	
	ReadStoryManager manager;
	Story story;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		story = new Story();
		;
	}
	
	@Test
	public void testsetStory() {
		assertTrue(manager.getStory() == null);
		manager.setStory(story);
		assertTrue(manager.getStory() == story);
	}

}