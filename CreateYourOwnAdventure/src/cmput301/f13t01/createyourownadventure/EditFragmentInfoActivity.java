package cmput301.f13t01.createyourownadventure;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditFragmentInfoActivity extends Activity implements
		ChoiceListListener {

	static final int EDIT_FRAGMENT = 0;

	private StoryFragment storyFragment;
	private boolean isNew;
	private int fragmentId;
	private ReadStoryManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment_info);
		// Show the Up button in the action bar.
		setupActionBar();

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();

		Intent intent = getIntent();

		if (intent != null) {
			isNew = (boolean) intent.getBooleanExtra(
					getResources().getString(R.string.fragment_is_new), false);
			if (isNew == false) {
				fragmentId = (int) intent.getIntExtra(
						getResources().getString(R.string.fragment_id), -1);
				storyFragment = manager.getFragment(fragmentId);
			} else {
				storyFragment = new StoryFragment();
				fragmentId = manager.addFragment(storyFragment);
			}
		}

		// Find the proper EditText views
		EditText title = (EditText) findViewById(R.id.fragment_title);
		EditText description = (EditText) findViewById(R.id.fragment_description);

		// Initialize the views from the StoryFragment
		title.setText(storyFragment.getTitle());
		description.setText(storyFragment.getDescription());
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
		getMenuInflater().inflate(R.menu.edit_fragment_info, menu);
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
		case R.id.action_edit_edit_content:
			onSelectEditContent();
			return true;
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			onSelectDelete();
			return true;
		case R.id.action_edit_edit_choice:
			showChoiceSelection();
			return true;
		case R.id.action_edit_add_choice:
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

	private void onSelectDelete() {
		manager.removeFragment(fragmentId);
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.fragment_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	private void onSelectCancel() {
		if(isNew) {
			manager.removeFragment(fragmentId);
		}
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	private void onSelectEditContent() {
		Intent intent = new Intent(this, EditFragmentContentActivity.class);
		intent.putExtra(getResources().getString(R.string.story_fragment),
				storyFragment);
		intent.putExtra(getResources().getString(R.string.fragment_id),
				fragmentId);
		startActivityForResult(intent, EDIT_FRAGMENT);
	}

	public void onBackpressed() {

		EditText title = (EditText) findViewById(R.id.edit_story_title);
		EditText author = (EditText) findViewById(R.id.edit_story_author);
		EditText desc = (EditText) findViewById(R.id.edit_story_description);

		manager.setTitle(title.getText().toString());
		manager.setAuthor(author.getText().toString());
		manager.setDescription(desc.getText().toString());

		// manager.saveStory();

		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.fragment_save_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	@Override
	public void onChoiceSelected(Choice choice) {
		// TODO Auto-generated method stub

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RESULT_OK) {
			Intent intent = getIntent();

			if (intent != null) {
				storyFragment = (StoryFragment) intent
						.getSerializableExtra(getResources().getString(
								R.string.story_fragment));
			}
		} else if (requestCode == RESULT_CANCELED) {
			// Do Nothing
		}
	}

}
