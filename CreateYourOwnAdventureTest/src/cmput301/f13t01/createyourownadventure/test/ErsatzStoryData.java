package cmput301.f13t01.createyourownadventure.test;

import java.util.UUID;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import cmput301.f13t01.createyourownadventure.Choice;
import cmput301.f13t01.createyourownadventure.ReadStoryManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryFragment;
import cmput301.f13t01.createyourownadventure.Text;

// generates a fake story
public class ErsatzStoryData {

	public static Story createErsatzStory() {
		// TODO Auto-generated constructor stub
		
		ReadStoryManager manager = new ReadStoryManager();
		
		// Fake story data
		UUID storyid = UUID.randomUUID();
		Story story = new Story();
		manager.setStory(story);
		Boolean sauthor = manager.setAuthor("Test Author");
		Boolean sdescription = manager.setDescription("Test Description inserted");

		// Fake media 1
		SpannableString spanString1 = new SpannableString("fragment 1 media 1");
		StyleSpan boldSpan = new StyleSpan( Typeface.BOLD );
		spanString1.setSpan(boldSpan, 0, 4, 0);
		Text text1 = new Text(spanString1);
		
		// Fake media 2
		SpannableString spanString2 = new SpannableString("fragment 1 media 2");
		ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
		spanString2.setSpan(colorSpan, 6, 12, 0);
		Text text2 = new Text(spanString2);
		
		// Fake media 3
		SpannableString spanString3 = new SpannableString("fragment 2 string");
		Text text3 = new Text(spanString3);
		
		// Fake media 4
		SpannableString spanString4 = new SpannableString("fragment 3 string");
		Text text4 = new Text(spanString4);
		
		// Fake fragment 1
		StoryFragment fragment1 = new StoryFragment();
		Boolean f1title = fragment1.setTitle("Fragment 1 title");
		Boolean f1description = fragment1.setDescription("Fragment 1 description");
		Boolean setcontent = fragment1.addContent(text1);
		setcontent = fragment1.addContent(text2);
		
		// Fake fragment 2
		StoryFragment fragment2 = new StoryFragment();
		Boolean f2title = fragment2.setTitle("Fragment 2 title");
		Boolean f2description = fragment2.setDescription("Fragment 2 description");
		setcontent = fragment2.addContent(text3);
		
		// Fake fragment 3
		StoryFragment fragment3 = new StoryFragment();
		Boolean f3title = fragment3.setTitle("Fragment 3 title");
		Boolean f3description = fragment3.setDescription("Fragment 3 description");
		setcontent = fragment3.addContent(text3);
		
		// Fake choices
		Choice choice1f1 = new Choice(1, 2, "from 1 to 2");
		Choice choice2f1 = new Choice(1, 1, "from 1 to 1");
		Choice choice3f1 = new Choice(1, 3, "from 1 to 3 (end)");
		Choice choice1f2 = new Choice(2, 1, "from 2 to 1");
		Choice choice2f2 = new Choice(2, 3, "from 2 to 3 (end)");
		
		// Set choices
		manager.addChoice(1, choice1f1);
		manager.addChoice(1, choice2f1);
		manager.addChoice(1, choice3f1);
		manager.addChoice(2, choice1f2);
		manager.addChoice(2, choice2f2);
		
		// Add fragments
		Integer id1 = manager.addFragment(fragment1);
		Integer id2 = manager.addFragment(fragment2);
		Integer id3 = manager.addFragment(fragment3);
		Boolean work = manager.setFirstPage(id1);
		
		Boolean stitle = manager.setTitle(work.toString());
		
		return manager.getStory();
	}
}
