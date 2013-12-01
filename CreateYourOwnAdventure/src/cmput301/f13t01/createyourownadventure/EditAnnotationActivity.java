/*
EditFragmentContentActivity activity for CreateYourOwnAdventure.
This is the activity that allows the user to edit a particular 
story annotation's content.

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

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
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

/**
 * This activity allows the user to edit and maintain a particular story
 * annotation's content.
 * 
 * @author Jesse Huard
 * 
 */

public class EditAnnotationActivity extends Activity implements
		OnMenuItemClickListener {

	// Intent Codes
	private static final int SELECT_IMAGE = 1;
	private static final int CAPTURE_IMAGE = 2;

	// Activity variables
	@SuppressWarnings("rawtypes")
	private ArrayList<Media> annotation;
	private ArrayList<Uri> imageURIs;
	private LinearLayout layout;
	private Uri cameraUri;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 * Override onCreate
	 */
	protected void onCreate(Bundle savedInstanceState) {
		// Call super-class onCreate
		super.onCreate(savedInstanceState);
		// Set-up display
		setContentView(R.layout.activity_edit_annotation);
		layout = (LinearLayout) findViewById(R.id.edit_annotation_linear);
		// Process intent
		Intent intent = getIntent();
		
		annotation = new ArrayList<Media>();
		
		if (savedInstanceState != null) {
			annotation = (ArrayList<Media>) intent
					.getSerializableExtra(getResources().getString(
							R.string.annotation));
			imageURIs = (ArrayList<Uri>) savedInstanceState
					.getSerializable(getResources().getString(
							R.string.story_URIs));
		} else {
			if (intent != null) {
				annotation = (ArrayList<Media>) intent
						.getSerializableExtra(getResources().getString(
								R.string.annotation));
			}
			imageURIs = new ArrayList<Uri>();
			for (Media next : annotation) {
				if (next.getType() == MediaType.IMAGE.toString()) {
					imageURIs.add(Uri.fromFile(new File(getFilesDir()
							.getAbsolutePath()
							+ "/"
							+ next.getType().toString()
							+ "/"
							+ next.getContent())));
				}
			}
		}
		// Display contents
		StoryFragmentViewFactory.ConstructView(layout, annotation,
				getApplicationContext(), true);
	}

	@Override
	/**
	 * Override onCreateOptionsMenu
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_annotation, menu);
		return true;
	}

	@Override
	/**
	 * onOptionsItemSelected
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_edit_add_content:
			onSelectAddContent();
			return true;
		case R.id.action_edit_cancel:
			onSelectCancel();
			return true;
		}
		return super.onOptionsItemSelected(item);
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
				.getAbsolutePath() + "/" + "tmp" + imageURIs.size();
		File imageFile = new File(cameraTempDir);
		cameraUri = Uri.fromFile(imageFile);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
		startActivityForResult(intent, CAPTURE_IMAGE);

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
		View v = findViewById(R.id.action_edit_add_content);

		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.add_image_menu, popup.getMenu());

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

	@SuppressWarnings("rawtypes")
	@Override
	/**
	 * Define Back behaviour
	 */
	public void onBackPressed() {
		ArrayList<Media> annotation = constructSaveAnnotationFromView();

		Intent intent = new Intent();

		intent.putExtra(getResources().getString(R.string.annotation),
				annotation);

		if (getParent() != null)
			getParent().setResult(RESULT_OK, intent);
		setResult(RESULT_OK, intent);

		finish();
	}

	@Override
	/**
	 * Override onActivityResult
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case SELECT_IMAGE:
			if (resultCode == RESULT_OK) {
				Uri image = data.getData();
				imageURIs.add(image);
				StoryFragmentViewFactory.addImage(image, layout, this);
			}
			break;
		case CAPTURE_IMAGE:
			if (resultCode == RESULT_OK) {
				imageURIs.add(cameraUri);
				StoryFragmentViewFactory.addImage(cameraUri, layout, this);
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
				group.removeView(view);
			}
			return false;
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		ArrayList<Media> saveAnnotation = constructTemporaryAnnotationFromView();
		outState.putSerializable(getResources().getString(R.string.annotation),
				saveAnnotation);
		outState.putSerializable(getResources().getString(R.string.story_URIs),
				imageURIs);
	}

	@SuppressWarnings("rawtypes")
	private ArrayList<Media> constructSaveAnnotationFromView() {
		ArrayList<Media> saveAnnotation = new ArrayList<Media>();

		int imageIndex = 0;
		Log.d("ImageSaveDebug", "count: " + layout.getChildCount());

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				saveAnnotation.add(new Text(string));
			} else if (v.getClass().equals(ImageView.class)) {
				String imageName = GlobalManager.getLocalManager().saveMedia(
						imageURIs.get(imageIndex), MediaType.IMAGE);
				saveAnnotation.add(new Image(imageName));
				imageIndex++;
			}
		}

		return saveAnnotation;
	}

	@SuppressWarnings("rawtypes")
	private ArrayList<Media> constructTemporaryAnnotationFromView() {
		ArrayList<Media> tempAnnotation = new ArrayList<Media>();

		int UriIndex = 0;

		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if (v.getClass().equals(EditText.class)) {
				EditText text = (EditText) v;
				SpannableString string = new SpannableString(text.getText());
				tempAnnotation.add(new Text(string));
			} else if (v.getClass().equals(ImageView.class)) {
				tempAnnotation.add(new ImageUri(imageURIs.get(UriIndex)));
				UriIndex++;
			}
		}

		return tempAnnotation;
	}
}