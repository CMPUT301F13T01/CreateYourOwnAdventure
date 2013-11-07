package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditFragmentContentActivity extends Activity {

	private StoryFragment storyFragment;
	private int fragmentId;
	private ReadStoryManager manager;

	@SuppressWarnings("rawtypes")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment_content);

		setupActionBar();

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		Intent intent = getIntent();

		if (intent != null) {
			fragmentId = (int) intent.getIntExtra(
					getResources().getString(R.string.fragment_id), -1);
			storyFragment = (StoryFragment) intent
					.getSerializableExtra(getResources().getString(
							R.string.story_fragment));
			Log.d("oops", "GOT ME A CONTENT FRAGMENT");
		}

		ArrayList<Media> content = storyFragment.getContentList();

		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// Display the fragment
		for (Media media : content) {
			if (media.getClass().equals(Text.class)) {
				EditText edit = new EditText(getApplication());
				edit.setText((CharSequence) media.getContent());
				layout.addView(edit, params);
			}
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_fragment_content, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_edit_add_content:
			onSelectAddContent();
			return true;
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_edit_choice:
			showChoiceSelection();
			return true;
		case R.id.action_edit_add_choice:
			onAddChoice();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void showChoiceSelection() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment prev = getFragmentManager().findFragmentByTag("dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		DialogFragment newFragment = (DialogFragment) ChoiceListFragment
				.newInstance(fragmentId);
		newFragment.show(ft, "dialog");
	}
	
	private void onAddChoice() {

	}

	private void onSelectAddContent() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		EditText text = new EditText(getApplicationContext());
		text.setTextColor(Color.BLACK);
		text.setHint("hint");
		layout.addView(text, params);
	}

	private void onSelectCancel() {
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();

		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent);
		finish();
	}

}
