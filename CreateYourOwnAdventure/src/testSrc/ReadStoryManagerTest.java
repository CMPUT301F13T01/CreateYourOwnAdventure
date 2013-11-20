package testSrc;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.createyourownadventure.Choice;
import cmput301.f13t01.createyourownadventure.ChoiceMap;
import cmput301.f13t01.createyourownadventure.ReadStoryManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryFragment;
import cmput301.f13t01.createyourownadventure.StoryFragmentInfo;
import cmput301.f13t01.createyourownadventure.StoryFragmentList;

public class ReadStoryManagerTest extends TestCase {

	ReadStoryManager manager;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		manager = new ReadStoryManager();
		;
	}

	@Test
	public void testStory() {
		Story story = new Story();
		assertTrue(manager.getStory() == null);
		manager.setStory(story);
		assertFalse(manager.getStory() == null);
	}

	@Test
	public void testAuthor() {
		Story story = new Story();
		manager.setStory(story);
		assertTrue(manager.getAuthor() == null);
		manager.setAuthor("test author");
		assertFalse(manager.getAuthor() == null);
	}

	@Test
	public void testDescription() {
		Story story = new Story();
		manager.setStory(story);
		assertTrue(manager.getDescription() == null);
		manager.setDescription("test description");
		assertFalse(manager.getDescription() == null);
	}

	@Test
	public void testFragmentFunction() {
		Story story = new Story();
		manager.setStory(story);
		assertTrue(manager.getFirstPage() == null);
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("test title");
		fragment.setDescription("test description");
		Integer id = manager.addFragment(fragment);
		manager.setFirstPage(id);
		assertFalse(manager.getFirstPage() == null);
		assertTrue(manager.getFragment(id) == fragment);
		assertTrue(manager.getFragmentId(fragment) == id);
		StoryFragmentInfo info = manager.getFragmentInfo(id);
		assertTrue(info.getId() == id);
		assertTrue(info.getTitle() == "test title");
		manager.removeFragment(id);
		assertTrue(manager.getFragment(id) == null);
	}

	@Test
	public void testUpdateFragment() {
		Story story = new Story();
		manager.setStory(story);
		StoryFragmentList fragmentList = new StoryFragmentList();
		StoryFragment fragment = new StoryFragment();
		fragment.setTitle("original title");
		fragment.setDescription("original description");
		Integer id = fragmentList.addFragment(fragment);
		StoryFragment fragment2 = new StoryFragment();
		fragment2.setTitle("new title");
		fragment2.setDescription("new description");
		fragmentList.updateFragment(id, fragment2);
		assertTrue(fragmentList.getFragment(id) == fragment2);
	}

	@Test
	public void testAddChoice() {
		ChoiceMap choicemap = new ChoiceMap();
		manager.addChoice(1, new Choice(1, 2, "test"));
		ArrayList<Choice> testList = choicemap.getChoices(1);
		Choice choice = testList.get(0);
		assertTrue(choice.getSourceId() == 1);
		assertTrue(choice.getDestinationId() == 1);

		manager.addChoice(1, new Choice(1, 3, "second test"));
		testList = choicemap.getChoices(1);
		choice = testList.get(1);
		assertTrue(choice.getSourceId() == 1);
		assertTrue(choice.getDestinationId() == 3);
		choice = testList.get(0);
		assertTrue(choice.getSourceId() == 1);
		assertTrue(choice.getDestinationId() == 2);

		testList = choicemap.getChoices(2);
		assertTrue(testList == null);
	}

	@Test
	public void testDeleteChoice() {
		ChoiceMap choicemap = new ChoiceMap();
		manager.addChoice(1, new Choice(1, 2, "Hello"));
		manager.addChoice(1, new Choice(1, 3, "Hello again"));
		manager.addChoice(1, new Choice(1, 4, "Hello goodbye"));
		assertTrue(manager.deleteChoice(1, 1));
		ArrayList<Choice> testList = choicemap.getChoices(1);
		Choice choice = testList.get(1);
		assertTrue(choice.getSourceId() == 1);
		assertTrue(choice.getDestinationId() == 4);
	}

	@Test
	public void testUpdateChoice() {
		ChoiceMap choicemap = new ChoiceMap();
		manager.addChoice(1, new Choice(1, 2, "Hello"));
		manager.addChoice(1, new Choice(1, 3, "Hello again"));
		manager.addChoice(1, new Choice(1, 4, "Hello goodbye"));
		assertTrue(manager.updateChoice(1, 1, new Choice(1, 5, "Who am I?")));
		ArrayList<Choice> testList = choicemap.getChoices(1);
		Choice choice = testList.get(1);
		assertTrue(choice.getSourceId() == 1);
		assertTrue(choice.getDestinationId() == 5);
	}

	@Test
	public void testCleanFragmentReferences() {
		ChoiceMap choicemap = new ChoiceMap();
		manager.addChoice(1, new Choice(1, 2, "Hello"));
		manager.addChoice(2, new Choice(2, 3, "What?"));
		manager.addChoice(2, new Choice(2, 4, "Hey!"));
		manager.addChoice(2, new Choice(2, 4, "Hey2!"));
		manager.addChoice(4, new Choice(4, 1, "Haha!"));
		// Should remove all choices referencing fragment 4
		manager.cleanFragmentReferences(4);
		ArrayList<Choice> testList = choicemap.getChoices(4);
		assertTrue(testList == null);
		testList = choicemap.getChoices(2);
		assertTrue(testList.size() == 1);
	}

	@Test
	public void testHistoryMove() {
		assertTrue(manager.goBack() == null);
		assertTrue(manager.getMostRecent() == null);
		manager.pushToStack(1);
		manager.pushToStack(2);
		manager.pushToStack(3);
		assertTrue(manager.goBack() == 2);
		manager.clearHistory();
		assertTrue(manager.getMostRecent() == null);
	}

}