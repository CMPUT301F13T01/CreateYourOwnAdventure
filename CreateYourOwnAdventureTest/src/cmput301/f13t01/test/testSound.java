package cmput301.f13t01.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.model.Sound;

public class testSound extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testSetContent() {
		Sound sound = new Sound();
		
		String content = "resourceidentifier";
		
		sound.setContent(content);
		
		String resourceString = sound.getContent();
		
		assertTrue(resourceString.equals(content));
	}

}
