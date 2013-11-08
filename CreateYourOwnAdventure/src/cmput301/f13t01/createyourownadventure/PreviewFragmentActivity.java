package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreviewFragmentActivity extends Activity {

	private StoryFragment storyFragment;

	@SuppressWarnings("rawtypes")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_fragment);

		Intent intent = getIntent();
		storyFragment = (StoryFragment) intent.getSerializableExtra(getResources().getString(
				R.string.story_fragment));

		ArrayList<Media> content = storyFragment.getContentList();

		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// Display the fragment
		for (Media media : content) {
			if (media.getClass().equals(Text.class)) {
				TextView text = new TextView(getApplicationContext());
				text.setTextColor(Color.BLACK);
				text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
				text.setText((CharSequence) media.getContent());
				layout.addView(text, params);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
