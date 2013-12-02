/*
EditFragmentChoiceActivity activity for CreateYourOwnAdventure.
This is the activity that allows the user to edit a particular choice
for a particular story fragment.

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
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity allows the user to edit a choice in a particular fragment.
 * 
 * @author Jesse Huard
 * 
 */

public class EditFragmentChoiceActivity extends Activity implements
		StoryFragmentListListener {
	
	// Variables to track for the activity
	private Choice choice;
	private int sourceId, position;
	private boolean isNew;
	private ReadStoryManager manager;

	@Override
	/**
	 * Override the built-in onCreate
	 */
	protected void onCreate(Bundle savedInstanceState) {
		// Call super-class onCreate
		super.onCreate(savedInstanceState);
		// Set-up the view
		setContentView(R.layout.activity_edit_fragment_choice);
		setupActionBar();
		// Set onClick behaviour
		final Button button = (Button) findViewById(R.id.edit_set_destination);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showFragmentSelection();
			}
		});
		// Get the story manager
		manager = GlobalManager.getStoryManager();
		// Process the intent
		Intent intent = getIntent();
		sourceId = intent.getIntExtra(
				getResources().getString(R.string.fragment_id), -1);
		position = intent.getIntExtra(
				getResources().getString(R.string.choice_position), -1);
		Log.d("oops", "pos start: " + position);
		isNew = intent.getBooleanExtra(
				getResources().getString(R.string.choice_is_new), true);
		// Set the TextViews
		TextView sourceText = (TextView) findViewById(R.id.edit_choice_source);
		sourceText.setText("Source: "
				+ manager.getFragmentInfo(sourceId).getTitle());

		TextView destText = (TextView) findViewById(R.id.edit_choice_destination);
		// Set display text
		if (isNew) {
			destText.setText("Destination: Not Set");
			choice = new Choice();
			choice.setSourceId(sourceId);
		} else {
			choice = (Choice) intent.getSerializableExtra(getResources()
					.getString(R.string.choice));
			destText.setText("Destination: "
					+ manager.getFragmentInfo(choice.getDestinationId())
							.getTitle());
			TextView flavourText = (TextView) findViewById(R.id.edit_choice_flavour);
			flavourText.setText(choice.getFlavourText());
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(false);
	}

	@Override
	/**
	 * Override the onCreateOptionsMenu
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_fragment_choice, menu);
		return true;
	}

	/**
	 * Override the onOptionsItemSelected
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Set button behaviours
		switch (item.getItemId()) {
		case R.id.action_edit_cancel:
			// Cancel edit
			onSelectCancel();
			return true;
		case R.id.action_edit_delete:
			// Delete
			onSelectDelete();
			return true;
		case R.id.action_help:
			onSelectHelp();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Behaviour to delete a choice
	 */
	private void onSelectDelete() {
		// Delete a choice
		if (!isNew)
			manager.deleteChoice(sourceId, position);
		// Toast notification
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.choice_delete_toast), Toast.LENGTH_SHORT);
		toast.show();
		// Send intent
		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent);
		finish();
	}

	/**
	 * Behaviour for cancellation
	 */
	private void onSelectCancel() {
		// Toast notification
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();
		// Send intent
		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent);
		finish();
	}
	
	/**
	 * Behaviour for showing help
	 */
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

	@Override
	/**
	 * Override the onBackPressed method
	 */
	public void onBackPressed() {
		Log.d("oops", "pos: " + position);
		// Toast notifications
		if (choice.getDestinationId() != null) {
			TextView flavourText = (TextView) findViewById(R.id.edit_choice_flavour);
			choice.setFlavourText(flavourText.getText().toString());
			if (isNew)
				// Add a new choice
				manager.addChoice(sourceId, choice);
			else
				// Update an existing choice
				manager.updateChoice(sourceId, position, choice);
			// Notification
			Toast toast = Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.choice_saved_toast),
					Toast.LENGTH_SHORT);
			toast.show();
		} else {
			// Notification
			Toast toast = Toast.makeText(getApplicationContext(),
					getResources().getString(R.string.choice_not_saved_toast),
					Toast.LENGTH_SHORT);
			toast.show();
		}
		// Send intent
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}

	/**
	 * Indicate selected fragment
	 */
	public void showFragmentSelection() {
		// Display
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		android.app.Fragment prev = getFragmentManager().findFragmentByTag(
				"dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		DialogFragment newFragment = (DialogFragment) StoryFragmentListFragment
				.newInstance();
		newFragment.show(ft, "dialog");
	}

	@Override
	/**
	 * Action for selecting a destination fragment
	 */
	public void onStoryFragmentSelected(int fragmentId) {
		// Set choice destination
		choice.setDestinationId(fragmentId);
		// Update display
		TextView destText = (TextView) findViewById(R.id.edit_choice_destination);
		destText.setText("Destination: "
				+ manager.getFragmentInfo(choice.getDestinationId()).getTitle());
	}

}
