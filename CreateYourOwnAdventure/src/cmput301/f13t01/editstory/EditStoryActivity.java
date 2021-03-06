/*
EditStoryActivity activity for CreateYourOwnAdventure.
This is the activity that allows the user to edit a story's
information, as well as to decide if they wish to add a fragment,
jump to a fragment, etc.

     Copyright  �2013 Jesse Huard
    <Contact: jhuard@ualberta.ca>
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package cmput301.f13t01.editstory;

import java.util.UUID;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cmput301.f13t01.R;
import cmput301.f13t01.elasticsearch.ESManager;
import cmput301.f13t01.model.Story;
import cmput301.f13t01.model.StoryFragment;
import cmput301.f13t01.readstory.StoryManager;
import cmput301.f13t01.storylibrary.GlobalManager;
import cmput301.f13t01.storylibrary.HelpFragment;
import cmput301.f13t01.storylibrary.HelpMessage;

/**
 * The activity that allows the user to edit a story's information, in addition
 * to make decisions regarding which fragment they would like to view, add, etc.
 * 
 * @author Jesse Huard
 * 
 */

public class EditStoryActivity extends FragmentActivity implements
		StoryFragmentListListener {
	private StoryManager manager;
	private boolean isNew, buttonPressed = false;
	private UUID storyId;

	@Override
	/**
	 * Override onCreate function
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_story);
		getActionBar().setDisplayHomeAsUpEnabled(false);

		final Button button = (Button) findViewById(R.id.edit_set_first_page);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				buttonPressed = true;
				showFragmentSelection();
			}
		});

		// Get the story manager
		GlobalManager app = (GlobalManager) getApplication();
		manager = GlobalManager.getStoryManager();

		if (savedInstanceState != null) {
			isNew = savedInstanceState.getBoolean(getResources().getString(
					R.string.story_is_new));
			storyId = (UUID) savedInstanceState.getSerializable(getResources()
					.getString(R.string.story_id));

			app.setStoryManager(storyId);
		} else {
			Intent intent = getIntent();

			isNew = (boolean) intent.getBooleanExtra(
					getResources().getString(R.string.story_is_new), false);

			if (isNew == false) {
				storyId = (UUID) intent.getSerializableExtra(getResources()
						.getString(R.string.story_id));
				app.setStoryManager(storyId);

				EditText title = (EditText) findViewById(R.id.edit_story_title);
				EditText author = (EditText) findViewById(R.id.edit_story_author);
				EditText desc = (EditText) findViewById(R.id.edit_story_description);

				title.setText(manager.getTitle());
				author.setText(manager.getAuthor());
				desc.setText(manager.getDescription());

			} else {
				storyId = app.createAndSetStory();
			}
		}

		TextView firstPage = (TextView) findViewById(R.id.edit_first_page);
		StoryFragment firstPageFragment = manager.getFirstPage();

		if (firstPageFragment == null) {
			firstPage
					.setText(getResources().getString(R.string.first_page)
							+ " "
							+ getResources().getString(
									R.string.first_page_empty));
		} else {
			firstPage.setText(getResources().getString(R.string.first_page)
					+ " " + manager.getFirstPage().getTitle());
		}

	}

	@Override
	/**
	 * Override onCreateOptionsMenu
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_edit_story_actionbar, menu);
		return true;
	}

	@Override
	/**
	 * Behaviour for selecting a Story Fragment
	 */
	public void onStoryFragmentSelected(int fragmentId) {
		if (buttonPressed) {
			manager.setFirstPage(fragmentId);

			TextView firstPage = (TextView) findViewById(R.id.edit_first_page);

			firstPage.setText(getResources().getString(R.string.first_page)
					+ " " + manager.getFragmentInfo(fragmentId).getTitle());

			buttonPressed = false;
		} else {
			Intent intent = new Intent(this, EditFragmentInfoActivity.class);

			intent.putExtra(getResources().getString(R.string.fragment_is_new),
					false);
			intent.putExtra(getResources().getString(R.string.fragment_id),
					fragmentId);
			startActivity(intent);
		}
	}

	@Override
	/**
	 * Override onOptionsItemSelected
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			onSelectDelete();
			return true;
		case R.id.action_edit_add_fragment:
			onAddFragment();
			return true;
		case R.id.action_edit_edit_fragment:
			onEditFragment();
			return true;
		case R.id.action_publish:
			onSelectPublish();
			return true;
		case R.id.action_help:
			onSelectHelp();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void onSelectPublish() {
		// Check the status of the ESManager
		ESManager esManager = GlobalManager.getESManager();
		if (esManager.isBusy()) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Online Connection Busy", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			EditText title = (EditText) findViewById(R.id.edit_story_title);
			EditText author = (EditText) findViewById(R.id.edit_story_author);
			EditText desc = (EditText) findViewById(R.id.edit_story_description);

			manager.setTitle(title.getText().toString());
			manager.setAuthor(author.getText().toString());
			manager.setDescription(desc.getText().toString());

			PublishStoryTask task = new PublishStoryTask();
			task.execute(storyId);

			// Block while publishing
			Toast startPublishToast = Toast.makeText(getApplicationContext(),
					"Publishing, please wait...", Toast.LENGTH_LONG);
			startPublishToast.show();
			// Wait to allow ESManager to set state
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Block
			while (esManager.isBusy()) {
			}
			// Finished Publishing
			Toast endPublishToast = Toast.makeText(getApplicationContext(),
					"Finished Publishing.", Toast.LENGTH_SHORT);
			endPublishToast.show();
		}
	}

	private class PublishStoryTask extends AsyncTask<UUID, Integer, Integer> {

		public boolean locked;

		public boolean isLocked() {
			return locked;
		}

		@Override
		public void onPreExecute() {
			super.onPreExecute();
			locked = true;
		}

		@Override
		public Integer doInBackground(UUID... arg0) {
			GlobalManager.saveStory(storyId);
			Story story = GlobalManager.getStoryManager().getStory();
			GlobalManager.getESManager().saveStory(storyId, story);
			return 1;
		}

		@Override
		public void onPostExecute(Integer result) {
			super.onPostExecute(result);
			locked = false;
		}

	}

	private void onSelectHelp() {
		android.app.FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		android.app.Fragment prev = getFragmentManager().findFragmentByTag(
				"help_dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		android.app.DialogFragment newFragment = (android.app.DialogFragment) HelpFragment
				.newInstance(HelpMessage.EDIT_STORY);
		newFragment.show(ft, "help_dialog");
	}

	private void onEditFragment() {
		showFragmentSelection();
	}

	private void onAddFragment() {
		Intent intent = new Intent(this, EditFragmentInfoActivity.class);
		intent.putExtra(getResources().getString(R.string.fragment_is_new),
				true);
		startActivity(intent);
	}

	private void onSelectCancel() {
		if (isNew) {
			GlobalManager.getLocalManager().removeStory(storyId);
		}
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	private void onSelectDelete() {
		GlobalManager.getLocalManager().removeStory(storyId);

		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.story_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	/**
	 * Define Back behaviour
	 */
	public void onBackPressed() {

		EditText title = (EditText) findViewById(R.id.edit_story_title);
		EditText author = (EditText) findViewById(R.id.edit_story_author);
		EditText desc = (EditText) findViewById(R.id.edit_story_description);

		manager.setTitle(title.getText().toString());
		manager.setAuthor(author.getText().toString());
		manager.setDescription(desc.getText().toString());

		GlobalManager.saveStory(storyId);

		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.story_save_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	/**
	 * Display selection of fragment
	 */
	public void showFragmentSelection() {
		android.app.FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		android.app.Fragment prev = getFragmentManager().findFragmentByTag(
				"choice_dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);

		android.app.DialogFragment newFragment = (android.app.DialogFragment) StoryFragmentListFragment
				.newInstance();
		newFragment.show(ft, "choice_dialog");
	}

	@Override
	/**
	 * Generate instance state
	 */
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(getResources().getString(R.string.story_is_new),
				isNew);
		outState.putSerializable(getResources().getString(R.string.story_id),
				storyId);
	}

}
