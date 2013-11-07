package testSrc;

import java.util.UUID;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.ESManager;
import cmput301.f13t01.createyourownadventure.Story;

public class testESManager extends TestCase{

	private ESManager manager;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.manager = new ESManager();
	}

	@Test
	public void testGetStory() {
		UUID id = UUID.randomUUID();
		assertFalse(this.manager.getStory(id) == null);
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetStoryInfo() {
		UUID id = UUID.randomUUID();
		assertFalse(this.manager.getStoryInfo(id) == null);
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetStoryInfoList() {
		assertFalse(this.manager.getStoryInfoList() == null);
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddStory() {
		Story story = new Story();
		story.setTitle("Test Story 1");
		story.setAuthor("Test Author 1");
		assertTrue(false);
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemoveStory() {
		assertTrue(false);
		fail("Not yet implemented");
	}
	
	@Test
	public void testSaveStory() {
		assertTrue(false);
		fail("Not yet implemented");
	}
}
