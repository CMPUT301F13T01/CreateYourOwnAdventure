package testSrc;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.elasticsearch.Searcher;

public class testSearcher extends TestCase{

	private Searcher searcher;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.searcher = new Searcher();
	}
	
	@Test
	public void testSearchTitle() {
		this.searcher.searchTitle();
		assertTrue(false);
		fail("Not yet implemented");
	}
	
	@Test
	public void testSearchAuthor() {
		this.searcher.searchAuthor();
		assertTrue(false);
		fail("Not yet implemented");
	}

	@Test
	public void testSearchDescription() {
		this.searcher.searchDescription();
		assertTrue(false);
		fail("Not yet implemented");
	}

}
