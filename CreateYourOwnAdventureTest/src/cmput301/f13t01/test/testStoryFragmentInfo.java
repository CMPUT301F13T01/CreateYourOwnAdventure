package cmput301.f13t01.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.model.StoryFragment;
import cmput301.f13t01.model.StoryFragmentInfo;

public class testStoryFragmentInfo extends TestCase {
	
	private StoryFragment fragment;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.fragment = new StoryFragment();
		this.fragment.setTitle("Test Title");
		this.fragment.setDescription("Test Description");
	}
	
	@Test
	public void testInstance() {
		StoryFragmentInfo info = new StoryFragmentInfo(0, this.fragment);
		assertTrue(info.getId() == 0);
		assertTrue(info.getTitle() == "Test Title");
		assertTrue(info.getDescription() == "Test Description");
	}

}
