package testSrc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.JsonConverter;

public class testJsonConverter extends TestCase{

	private JsonConverter converter;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.converter = new JsonConverter();
	}
	
	@Test
	public void testToJson() {
		this.converter.toJson();
		assertTrue(false);
		fail("Not yet implemented");
	}
	
	@Test
	public void testFromJson() {
		this.converter.fromJson();
		assertTrue(false);
		fail("Not yet implemented");
	}

}
