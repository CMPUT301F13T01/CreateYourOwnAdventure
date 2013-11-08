/*
EditFragmentContentActivity activity for CreateYourOwnAdventure.
This is the activity that allows the user to edit a particular 
story fragment's content.

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

import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * This activity allows the user to edit and maintain a particular story
 * fragment's content.
 * 
 * @author Jesse Huard
 * 
 */

public class EditFragmentContentActivity extends Activity implements
		ChoiceListListener {

	static final int EDIT_CHOICE = 0;

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
			Log.d("oops", "WHY ARE YOU STARTING AGAIN?");
			fragmentId = (int) intent.getIntExtra(
					getResources().getString(R.string.fragment_id), -1);
			storyFragment = (StoryFragment) intent
					.getSerializableExtra(getResources().getString(
							R.string.story_fragment));

			ArrayList<Media> content = storyFragment.getContentList();

			LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			// Display the fragment
			for (Media media : content) {
				if (media.getClass().equals(Text.class)) {
					EditText edit = new EditText(getApplication());
					edit.setTextColor(Color.BLACK);
					edit.setText(media.getContent().toString());
					layout.addView(edit, params);
				}
			}
		}

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(false);

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
		case R.id.action_edit_preview:
			onSelectPreview();
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

	private void onSelectPreview() {
		Intent intent = new Intent(getApplicationContext(),
				PreviewFragmentActivity.class);

		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		StoryFragment previewFragment = storyFragment;
		previewFragment.removeAllContent();

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				previewFragment.addContent(new Text(string));
			}
		}

		intent.putExtra(getResources().getString(R.string.story_fragment),
				previewFragment);

		startActivity(intent);
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

	private void onSelectAddContent() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		EditText text = new EditText(getApplicationContext());
		text.setTextColor(Color.BLACK);
		text.setHint("Your Story Text");
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

	@Override
	public void onBackPressed() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		storyFragment.removeAllContent();

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				storyFragment.addContent(new Text(string));
			}
		}

		Intent intent = new Intent();

		intent.putExtra(getResources().getString(R.string.story_fragment),
				storyFragment);

		if (getParent() != null)
			getParent().setResult(RESULT_OK, intent);
		setResult(RESULT_OK, intent);

		finish();
	}

	@Override
	public void onChoiceSelected(Choice choice, int position) {
		Intent intent = new Intent(getApplicationContext(),
				EditFragmentChoiceActivity.class);

		intent.putExtra(getResources().getString(R.string.choice_is_new), false);
		intent.putExtra(getResources().getString(R.string.fragment_id),
				fragmentId);
		intent.putExtra(getResources().getString(R.string.choice_position),
				position);
		intent.putExtra(getResources().getString(R.string.choice), choice);

		startActivityForResult(intent, EDIT_CHOICE);
	}

	private void onAddChoice() {
		Intent intent = new Intent(getApplicationContext(),
				EditFragmentChoiceActivity.class);

		intent.putExtra(getResources().getString(R.string.choice_is_new), true);
		intent.putExtra(getResources().getString(R.string.fragment_id),
				fragmentId);

		startActivityForResult(intent, EDIT_CHOICE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {

		} else if (resultCode == RESULT_CANCELED) {
		}
	}

}
