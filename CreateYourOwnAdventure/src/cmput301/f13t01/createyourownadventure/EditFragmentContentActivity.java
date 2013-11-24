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
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * This activity allows the user to edit and maintain a particular story
 * fragment's content.
 * 
 * @author Jesse Huard
 * 
 */

public class EditFragmentContentActivity extends Activity implements
		ChoiceListListener, OnMenuItemClickListener {

	private static final int EDIT_CHOICE = 0;
	private static final int SELECT_IMAGE = 1;

	private StoryFragment storyFragment;
	private int fragmentId;
	private ReadStoryManager manager;
	private ArrayList<Uri> imageURIs;
	private LinearLayout layout;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment_content);

		setupActionBar();
		
		layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);

		// Get the story manager
		manager = GlobalManager.getStoryManager();

		Intent intent = getIntent();

		if (savedInstanceState != null) {
			fragmentId = savedInstanceState.getInt(getResources().getString(
					R.string.fragment_id));
			storyFragment = (StoryFragment) savedInstanceState
					.getSerializable(getResources().getString(
							R.string.story_fragment));
			imageURIs = (ArrayList<Uri>) savedInstanceState
					.getSerializable(getResources().getString(
							R.string.story_URIs));
		} else {

			if (intent != null) {
				fragmentId = (int) intent.getIntExtra(
						getResources().getString(R.string.fragment_id), -1);
				storyFragment = (StoryFragment) intent
						.getSerializableExtra(getResources().getString(
								R.string.story_fragment));
			}
			imageURIs = new ArrayList<Uri>();
		}

		ArrayList<Media> content = storyFragment.getContentList();

		StoryFragmentViewFactory.ConstructView(layout, content, this, true);
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

		StoryFragment previewFragment = constructTemporaryFragmentFromView();

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
		View v = findViewById(R.id.action_edit_add_content);

		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.add_content_menu, popup.getMenu());

		popup.setOnMenuItemClickListener(this);

		popup.show();

	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit_add_text:
			onSelectAddText();
			return true;
		case R.id.action_edit_add_image:
			onSelectAddImage();
			return true;
		case R.id.action_edit_add_sound:
			return true;
		case R.id.action_edit_add_video:
			return true;
		default:
			return false;
		}
	}

	private void onSelectAddText() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		EditText text = new EditText(getApplicationContext());
		text.setTextColor(Color.BLACK);
		text.setHint("Your Story Text");

		text.setOnLongClickListener(new EditContentViewListener(layout));

		layout.addView(text, params);
	}

	private void onSelectAddImage() {
		// TODO: Give an option to use camera, select from story photos, or
		// select from gallery.

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SELECT_IMAGE);
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

		switch (requestCode) {
		case EDIT_CHOICE:
			if (resultCode == RESULT_OK) {
				// Don't need to do anything right now.
			}
		case SELECT_IMAGE:
			if (resultCode == RESULT_OK) {
				Uri image = data.getData();
				imageURIs.add(image);
				StoryFragmentViewFactory.addImage(image, layout, this);
			}
		}
	}

	class EditContentViewListener implements View.OnLongClickListener {

		private LinearLayout layout;

		public EditContentViewListener(LinearLayout layout) {
			this.layout = layout;
		}

		@Override
		public boolean onLongClick(View v) {
			PopupMenu popup = new PopupMenu(getApplicationContext(), v);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.edit_long_click_menu, popup.getMenu());

			popup.setOnMenuItemClickListener(new SimplePopupListener(v,
					(ViewGroup) layout));

			popup.show();

			return true;
		}
	}

	class SimplePopupListener implements OnMenuItemClickListener {

		View view;
		ViewGroup group;

		SimplePopupListener(View v, ViewGroup group) {
			this.view = v;
			this.group = group;
		}

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.action_edit_delete_content:
				ContextMenuInfo info = item.getMenuInfo();
				if (info != null) {
					Log.d("oops", "Popup Menu Info is not null");
				} else {
					Log.d("oops", "Popup Menu Info is null");
				}
				group.removeView(view);
			}
			return false;
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(getResources().getString(R.string.fragment_id),
				fragmentId);
		StoryFragment savedFragment = constructTemporaryFragmentFromView();
		outState.putSerializable(
				getResources().getString(R.string.story_fragment),
				savedFragment);
		outState.putSerializable(getResources().getString(R.string.story_URIs),
				imageURIs);
	}

	private StoryFragment constructSaveFragmentFromView() {

		StoryFragment fragment = storyFragment;
		fragment.removeAllContent();

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				fragment.addContent(new Text(string));
			}
		}

		return fragment;
	}

	private StoryFragment constructTemporaryFragmentFromView() {
		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		StoryFragment fragment = storyFragment;
		fragment.removeAllContent();

		int UriIndex = 0;

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				fragment.addContent(new Text(string));
			} else if (v.getClass().equals(ImageView.class)) {
				fragment.addContent(new ImageUri(imageURIs.get(UriIndex)));
				UriIndex++;
			}
		}

		return fragment;
	}
}
