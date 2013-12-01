package cmput301.f13t01.createyourownadventure.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Video;

public class testVideo extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testSetContent() {
		Video video = new Video();
		
		String content = "resourceidentifier";
		
		video.setContent(content);
		
		String resourceString = video.getContent();
		
		assertTrue(resourceString.equals(content));
	}
}
