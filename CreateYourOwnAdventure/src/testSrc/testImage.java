package testSrc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Image;


public class testImage extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testSetContent() {
		Image image = new Image();
		
		String content = "resourceidentifier";
		
		image.setContent(content);
		
		String resourceString = image.getContent();
		
		assertTrue(resourceString.equals(content));
	}

}
