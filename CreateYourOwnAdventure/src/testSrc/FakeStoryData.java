package testSrc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import cmput301.f13t01.createyourownadventure.ReadStoryManager;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryFragment;
import cmput301.f13t01.createyourownadventure.Text;

// generates a fake story
public class FakeStoryData {

	public FakeStoryData() {
		// TODO Auto-generated constructor stub
		
		// Creation of fake story data
		ReadStoryManager manager;
		Context context;
		SpannableString spanString1;
		
		// Fake story data
		Integer storyid = 1;
		Story story = new Story();
		Boolean stitle = story.setTitle("Test Story");
		Boolean sauthor = story.setAuthor("Test Author");
		Boolean sdescription = story.setDescription("Test Description inserted");
		Boolean sfirstPage = story.setFirstPage(1);
		
		// Fake media 1
		spanString1 = new SpannableString("test spannable string");
		ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
		StyleSpan boldSpan = new StyleSpan( Typeface.BOLD );
		spanString1.setSpan(colorSpan, 0, 4, 0);
		
		
		Text text1 = new Text(spanString1);
		
		
		// Fake fragment 1
		StoryFragment fragment1 = new StoryFragment();
		Boolean f1title = fragment1.setTitle("Fragment 1 title");
		Boolean f1description = fragment1.setDescription("Fragment 1 description");
		//Boolean f1content = fragment1.addContent(f1media);
		
		// Fake fragment 2
		StoryFragment fragment2 = new StoryFragment();
		Boolean f2title = fragment2.setTitle("Fragment 2 title");
		Boolean f2description = fragment2.setDescription("Fragment 2 description");
		//Boolean f2content = fragment2.addContent(f2media);
	}
}
