package cmput301.f13t01.createyourownadventure;

import junit.framework.TestCase;
import org.junit.Before;

import android.content.Context;
import android.text.SpannableString;

public class ReadStoryManagerTest extends TestCase {

	// Creation of fake story data
	ReadStoryManager manager;
	Context context;
	
	// Fake story data
	Integer storyid = 1;
	Story story = new Story();
	Boolean stitle = story.setTitle("Test Story");
	Boolean sauthor = story.setAuthor("Test Author");
	Boolean sdescription = story.setDescription("Test Description inserted");
	Boolean sfirstPage = story.setFirstPage(1);
	
	// Fake media 1
	SpannableString spanstring = new SpannableString("test spannable string");
	spanstring.setSpan(null, 0, 4, 0);
	Text text1 = new Text(spanstring);
	
	
	// Fake fragment 1
	StoryFragment fragment1 = new StoryFragment();
	Boolean f1title = fragment1.setTitle("Fragment 1 title");
	Boolean f1description = fragment1.setDescription("Fragment 1 description");
	Boolean f1content = fragment1.addContent(f1media);
	
	// Fake fragment 2
	StoryFragment fragment2 = new StoryFragment();
	Boolean f2title = fragment2.setTitle("Fragment 2 title");
	Boolean f2description = fragment2.setDescription("Fragment 2 description");
	Boolean f2content = fragment2.addContent(f2media);
	
	
	@Before
	public void setUp() throws Exception {
		this.manager = new ReadStoryManager(storyId, fragmentId, view, context);
	}
}
