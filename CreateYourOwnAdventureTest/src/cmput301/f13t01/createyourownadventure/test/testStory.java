package cmput301.f13t01.createyourownadventure.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Story;

public class testStory extends TestCase{

	private Story story;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.story = new Story();
	}
	
	@Test
	public void testInstance() {
		this.story.setTitle("Test Title");
		this.story.setAuthor("Test Author");
		this.story.setDescription("Test Description");
		assertTrue(this.story.getTitle() == "Test Title");
		assertTrue(this.story.getAuthor() == "Test Author");
		assertTrue(this.story.getDescription() == "Test Description");
		assertTrue(this.story.getFirstPage() == null);
	}

}
