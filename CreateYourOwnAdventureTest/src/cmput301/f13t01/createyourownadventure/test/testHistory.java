package cmput301.f13t01.createyourownadventure.test;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import cmput301.f13t01.createyourownadventure.History;

public class testHistory extends TestCase {
	
	History history;

	@Before
	public void setUp() throws Exception {
		this.history = new History();
	}

	@Test
	public void testGetMostRecent() {
		assertTrue(history.getMostRecent() == null);
		history.pushToStack(1);
		history.pushToStack(2);
		history.pushToStack(3);
		assertTrue(history.getMostRecent() == 3);
		assertTrue(history.goBack() == 3);
		assertTrue(history.goBack() == 2);
	}
	
	@Test
	public void testGoBack() {
		assertTrue(history.goBack() == null);
		history.pushToStack(0);
		assertTrue(history.goBack() == 0);
		assertTrue(history.getMostRecent() == null);
		history.pushToStack(0);
		history.pushToStack(1);
		assertTrue(history.getMostRecent() == 1);
	}

}
