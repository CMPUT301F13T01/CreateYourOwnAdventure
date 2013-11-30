/*
EditFragmentInfoActivity activity for CreateYourOwnAdventure.
This is the activity that allows the user to edit a particular 
story fragment's information.

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

package cmput301.f13t01.createyourownadventure;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity allows the user to edit and maintain a particular story
 * fragment's information.
 * 
 * @author Jesse Huard
 * 
 */

public class EditFragmentInfoActivity extends Activity {
	
	// Intent code
	static final int EDIT_FRAGMENT = 0;

	// Activity variables
	private StoryFragment storyFragment;
	private boolean isNew;
	private int fragmentId;
	private ReadStoryManager manager;

	@Override
	/**
	 * Override onCreate
	 */
	protected void onCreate(Bundle savedInstanceState) {
		// Call super-class onCreate
		super.onCreate(savedInstanceState);
		// Set-up display
		setContentView(R.layout.activity_edit_fragment_info);
		setupActionBar();
		// Get the story manager
		manager = GlobalManager.getStoryManager();
		// Process intent
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

		getActionBar().setDisplayHomeAsUpEnabled(false);

	}

	@Override
	/**
	 * Override onCreateOptionsMenu
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_fragment_info, menu);
		return true;
	}

	@Override
	/**
	 * Override onOptionsItemSelected
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit_edit_content:
			onSelectEditContent();
			return true;
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			onSelectDelete();
			return true;
		case R.id.action_edit_add_choice:
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onSelectDelete() {
		manager.removeFragment(fragmentId);
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.fragment_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	private void onSelectCancel() {
		if (isNew) {
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

	@Override
	/**
	 * Define Back behaviour
	 */
	public void onBackPressed() {
		// Get Views
		EditText title = (EditText) findViewById(R.id.fragment_title);
		EditText desc = (EditText) findViewById(R.id.fragment_description);
		// Set the fragment info
		storyFragment.setTitle(title.getText().toString());
		storyFragment.setDescription(desc.getText().toString());
		// Update the fragment
		manager.updateFragment(fragmentId, storyFragment);
		// Toast notification
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.fragment_save_toast), Toast.LENGTH_SHORT);
		toast.show();
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (data != null) {
				storyFragment = (StoryFragment) data
						.getSerializableExtra(getResources().getString(
								R.string.story_fragment));
			}
		} else if (resultCode == RESULT_CANCELED) {
			// Do Nothing
		}
	}

}
