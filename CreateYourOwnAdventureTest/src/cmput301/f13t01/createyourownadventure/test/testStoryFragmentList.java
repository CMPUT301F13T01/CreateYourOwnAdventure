package cmput301.f13t01.createyourownadventure.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.StoryFragment;
import cmput301.f13t01.createyourownadventure.StoryFragmentInfo;
import cmput301.f13t01.createyourownadventure.StoryFragmentList;

public class testStoryFragmentList extends TestCase{

	private StoryFragmentList fragmentList;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.fragmentList = new StoryFragmentList();
	}
	
	@Test
	public void testGetFragmentId() {
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("Test Title 1");
		fragment.setDescription("Test Description 1");
		Integer id = this.fragmentList.addFragment(fragment);
		assertTrue(this.fragmentList.getFragmentId(fragment) == id);
	}
	
	@Test
	public void testAddFragment() {
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("Test Title 2");
		fragment.setDescription("Test Description 2");
		Integer id = this.fragmentList.addFragment(fragment);
		assertTrue(this.fragmentList.getFragment(id) == fragment);
	}
	
	@Test
	public void testRemoveFragment() {
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("Test Title 3");
		fragment.setDescription("Test Description 3");
		Integer id = this.fragmentList.addFragment(fragment);
		this.fragmentList.removeFragment(id);
		assertTrue(this.fragmentList.getFragment(id) == null);
	}
	
	@Test
	public void testGetFragmentInfo() {
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("Test Title 4");
		fragment.setDescription("Test Description 4");
		Integer id = this.fragmentList.addFragment(fragment);
		StoryFragmentInfo info = this.fragmentList.getFragmentInfo(id);
		assertTrue(info.getId() == id);
		assertTrue(info.getTitle() == "Test Title 4");
		assertTrue(info.getDescription() == "Test Description 4");
	}

	@Test
	public void testUpdateFragment() {
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("Test Title 5A");
		fragment.setDescription("Test Description 5A");
		Integer id = this.fragmentList.addFragment(fragment);
		StoryFragment fragment2 = new StoryFragment();
		fragment2.setTitle("Test Title 5B");
		fragment2.setDescription("Test Description 5B");
		this.fragmentList.updateFragment(id, fragment2);
		assertTrue(this.fragmentList.getFragment(id) == fragment2);
	}
	
}
