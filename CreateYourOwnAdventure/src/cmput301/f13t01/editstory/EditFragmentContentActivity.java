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

package cmput301.f13t01.editstory;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.util.Log;
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
import cmput301.f13t01.R;
import cmput301.f13t01.model.Choice;
import cmput301.f13t01.model.Image;
import cmput301.f13t01.model.ImageUri;
import cmput301.f13t01.model.Media;
import cmput301.f13t01.model.MediaType;
import cmput301.f13t01.model.StoryFragment;
import cmput301.f13t01.model.Text;
import cmput301.f13t01.storylibrary.GlobalManager;
import cmput301.f13t01.storylibrary.HelpFragment;
import cmput301.f13t01.storylibrary.HelpMessage;

/**
 * This activity allows the user to edit and maintain a particular story
 * fragment's content.
 * 
 * @author Jesse Huard
 * 
 */

public class EditFragmentContentActivity extends Activity implements
		ChoiceListListener, ImageResizeListener, OnMenuItemClickListener {

	// Intent Codes
	private static final int EDIT_CHOICE = 0;
	private static final int SELECT_IMAGE = 1;
	private static final int CAPTURE_IMAGE = 2;

	// Activity variables
	private StoryFragment storyFragment;
	private int fragmentId;
	private ArrayList<ImageInfo> imageInfos;
	private LinearLayout layout;
	private Uri cameraUri;
	private Context context;
	private ProgressDialog bar;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 * Override onCreate
	 */
	protected void onCreate(Bundle savedInstanceState) {
		// Call super-class onCreate
		super.onCreate(savedInstanceState);

		context = this;

		// Set-up display
		setContentView(R.layout.activity_edit_fragment_content);
		setupActionBar();
		layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		// Process intent
		Intent intent = getIntent();
		if (savedInstanceState != null) {
			fragmentId = savedInstanceState.getInt(getResources().getString(
					R.string.fragment_id));
			storyFragment = (StoryFragment) savedInstanceState
					.getSerializable(getResources().getString(
							R.string.story_fragment));
			imageInfos = (ArrayList<ImageInfo>) savedInstanceState
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
			imageInfos = new ArrayList<ImageInfo>();
			ArrayList<Media> media = storyFragment.getContentList();
			for (Media next : media) {
				if (next.getType() == MediaType.IMAGE.toString()) {
					Image image = (Image) next;
					imageInfos.add(new ImageInfo(Uri.fromFile(new File(
							getFilesDir().getAbsolutePath() + "/"
									+ next.getType().toString() + "/"
									+ next.getContent())), image.getScale()));
				}
			}
		}
		// Display contents
		ArrayList<Media> content = storyFragment.getContentList();
		StoryFragmentViewFactory.ConstructView(layout, content,
				getApplicationContext(), true);

		for (int i = 0; i < layout.getChildCount(); ++i) {
			if (layout.getChildAt(i) instanceof EditText) {
				layout.getChildAt(i).setOnLongClickListener(
						new EditTextViewListener(layout));
			} else if (layout.getChildAt(i) instanceof ImageView) {
				layout.getChildAt(i).setOnLongClickListener(
						new EditImageViewListener(layout));
			}
		}

		bar = new ProgressDialog(context);
		bar.setIndeterminate(true);
		bar.setTitle("Saving Fragment");
		bar.setMessage("Please Wait...");
		bar.setCancelable(false);
		bar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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
		getMenuInflater().inflate(R.menu.edit_fragment_content, menu);
		return true;
	}

	@Override
	/**
	 * onOptionsItemSelected
	 */
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
		case R.id.action_help:
			onSelectHelp();
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

	private void onSelectCancel() {
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.cancel_toast), Toast.LENGTH_SHORT);
		toast.show();

		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent);
		finish();
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
				.newInstance(HelpMessage.EDIT_CONTENT);
		newFragment.show(ft, "help_dialog");
	}

	@Override
	/**
	 * Override onMenuItemClick
	 */
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit_add_text:
			onSelectAddText();
			return true;
		case R.id.action_edit_add_image:
			onSelectAddImage();
			return true;
		case R.id.action_edit_add_sound:
			onSelectAddSound();
			return true;
		case R.id.action_edit_add_video:
			onSelectAddVideo();
			return true;
		case R.id.action_edit_from_camera:
			onSelectCamera();
			return true;
		case R.id.action_edit_from_gallery:
			onSelectGallery();
		default:
			return false;
		}
	}

	private void onSelectGallery() {
		String externalFolderPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/StoryTime/";
		File externalFile = new File(externalFolderPath);
		Log.d("oops", "external file path: " + externalFile.getAbsolutePath());

		Intent intent = new Intent();
		Uri external = Uri.fromFile(externalFile);
		// intent.setData(external);
		intent.setDataAndType(external, "image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(intent, SELECT_IMAGE);
	}

	private void onSelectCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String cameraTempDir = GlobalManager.getTempDirectory()
				.getAbsolutePath() + "/" + "tmp" + imageInfos.size();
		File imageFile = new File(cameraTempDir);
		cameraUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
		startActivityForResult(intent, CAPTURE_IMAGE);

	}

	private void onSelectAddSound() {
		// TODO: Implement this.
	}

	private void onSelectAddVideo() {
		// TODO: Implement this.
	}

	private void onSelectAddText() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		EditText text = new EditText(getApplicationContext());
		text.setTextColor(Color.BLACK);
		text.setHint("Your Story Text");

		text.setOnLongClickListener(new EditTextViewListener(layout));

		layout.addView(text, params);
	}

	private void onSelectAddImage() {
		View v = findViewById(R.id.action_edit_add_content);

		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.add_image_menu, popup.getMenu());

		popup.setOnMenuItemClickListener(this);

		popup.show();
	}

	@Override
	/**
	 * Define Back behaviour
	 */
	public void onBackPressed() {

		Toast toast = Toast.makeText(getApplicationContext(),
				"Saving Fragment", Toast.LENGTH_LONG);
		toast.show();

		toast = Toast.makeText(getApplicationContext(), "Saving Fragment",
				Toast.LENGTH_SHORT);
		toast.show();

		StoryFragment saveFragment = constructSaveFragmentFromView();

		Intent intent = new Intent();

		intent.putExtra(getResources().getString(R.string.story_fragment),
				saveFragment);

		if (getParent() != null)
			getParent().setResult(RESULT_OK, intent);
		setResult(RESULT_OK, intent);

		toast = Toast.makeText(getApplicationContext(), "Fragment Saved",
				Toast.LENGTH_SHORT);
		toast.show();

		finish();
	}

	@Override
	/**
	 * Define Choice selection behaviour
	 */
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
	/**
	 * Override onActivityResult
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case EDIT_CHOICE:
			if (resultCode == RESULT_OK) {
				// Don't need to do anything right now.
			}
			break;
		case SELECT_IMAGE:
			if (resultCode == RESULT_OK) {
				Uri image = data.getData();
				Integer scale = (int) ((float) StoryBitmapFactory.DEFAULT_SIZE
						/ StoryBitmapFactory.DEFAULT_SIZE * 100);
				Log.d("oops", "Select scale: " + scale);
				imageInfos.add(new ImageInfo(image, scale));
				StoryFragmentViewFactory.addImage(image, layout, this, scale);
				ImageView view = (ImageView) layout.getChildAt(layout
						.getChildCount() - 1);
				view.setOnLongClickListener(new EditImageViewListener(layout));
			}
			break;
		case CAPTURE_IMAGE:
			if (resultCode == RESULT_OK) {
				Integer scale = (int) ((float) StoryBitmapFactory.DEFAULT_SIZE
						/ StoryBitmapFactory.DEFAULT_SIZE * 100);
				imageInfos.add(new ImageInfo(cameraUri, scale));
				StoryFragmentViewFactory.addImage(cameraUri, layout, this,
						scale);
				ImageView view = (ImageView) layout.getChildAt(layout
						.getChildCount() - 1);
				view.setOnLongClickListener(new EditImageViewListener(layout));
			}
		}
	}

	class EditTextViewListener implements View.OnLongClickListener {

		private LinearLayout layout;

		public EditTextViewListener(LinearLayout layout) {
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

	class EditImageViewListener implements View.OnLongClickListener {

		private LinearLayout layout;

		public EditImageViewListener(LinearLayout layout) {
			this.layout = layout;
		}

		@Override
		public boolean onLongClick(View v) {
			PopupMenu popup = new PopupMenu(getApplicationContext(), v);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.edit_image_long_click_menu, popup.getMenu());

			popup.setOnMenuItemClickListener(new ImagePopupListener(v,
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
				Log.d("oops", "Wrong Delete!");
				group.removeView(view);
				return true;
			}
			return false;
		}

	}

	class ImagePopupListener implements OnMenuItemClickListener {

		View view;
		ViewGroup group;

		ImagePopupListener(View v, ViewGroup group) {
			this.view = v;
			this.group = group;
		}

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			int imageCount = 0;

			switch (item.getItemId()) {
			case R.id.action_edit_resize_image:
				for (int i = 0; i < layout.getChildCount(); ++i) {
					if (layout.getChildAt(i).equals(view)) {
						Log.d("oops", "Resize the image!");
						FragmentTransaction ft = getFragmentManager()
								.beginTransaction();
						Fragment prev = getFragmentManager().findFragmentByTag(
								"resize_dialog");
						if (prev != null) {
							ft.remove(prev);
						}
						ft.addToBackStack(null);

						DialogFragment newFragment = (DialogFragment) ImageResizeFragment
								.newInstance(imageInfos.get(imageCount).getScale(),
										imageCount);
						newFragment.show(ft, "resize_dialog");
					} else if (layout.getChildAt(i) instanceof ImageView) {
						imageCount++;
					}
				}
				return true;
			case R.id.action_edit_delete_content:
				for (int i = 0; i < layout.getChildCount(); ++i) {
					if (layout.getChildAt(i).equals(view)) {
						Log.d("oops", "Delete the image!");
						group.removeView(view);
						imageInfos.remove(imageCount);
					} else if (layout.getChildAt(i) instanceof ImageView) {
						imageCount++;
					}
				}
				return true;
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
				imageInfos);
	}

	private StoryFragment constructSaveFragmentFromView() {

		Log.d("ImageSaveDebug", "count: " + layout.getChildCount());

		StoryFragment fragment = storyFragment;
		fragment.removeAllContent();

		int imageIndex = 0;

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				fragment.addContent(new Text(string));
			} else if (v.getClass().equals(ImageView.class)) {
				ImageInfo info = imageInfos.get(imageIndex);
				String imageName = GlobalManager.getLocalManager().saveMedia(
						info.getImageUri(), MediaType.IMAGE);
				fragment.addContent(new Image(imageName, info.getScale()));
				imageIndex++;
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
				ImageInfo info = imageInfos.get(UriIndex);
				Log.d("oops", "Info: " + info.getImageUri() + " " + info.getScale());
				ImageUri image = new ImageUri(info.getImageUri(), info.getScale());
				Log.d("oops", "image: " + image.getContent() + " " + image.getScale());
				fragment.addContent(image);
				UriIndex++;
			}
		}

		return fragment;
	}

	@Override
	public void onScaleChoice(Integer value, Integer position) {
		Log.d("oops", "Returned scale: " + value + " Position: " + position);
		imageInfos.get(position).setScale(value);
	}
}
