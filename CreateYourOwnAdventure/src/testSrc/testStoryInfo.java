package testSrc;

import java.util.UUID;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;

public class testStoryInfo extends TestCase{

	private Story story;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.story = new Story();
		this.story.setTitle("Test Title");
		this.story.setAuthor("Test Author");
		this.story.setDescription("Test Description");
	}
	
	@Test
	public void testInstance() {
		UUID id = UUID.randomUUID();
		StoryInfo info = new StoryInfo(id, this.story);
		assertTrue(info.getId() == id);
		assertTrue(info.getTitle() == "Test Title");
		assertTrue(info.getAuthor() == "Test Author");
		assertTrue(info.getDescription() == "Test Description");
	}

}
