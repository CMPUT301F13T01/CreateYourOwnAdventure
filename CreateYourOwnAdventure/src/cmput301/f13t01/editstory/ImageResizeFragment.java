/*
The ImageResizeFragment class is used to select a scale to resize an image.
     
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import cmput301.f13t01.R;

/**
 * The ImageResizeFragment class is used to select a scale to resize an image.
 * 
 * @author Jesse Huard
 * 
 */
public class ImageResizeFragment extends DialogFragment {
	private static ImageResizeListener listener;

	/**
	 * Constructor
	 * 
	 * @param scale
	 *            scale to resize the image
	 * @param position
	 *            position of the image
	 * @return
	 */
	static ImageResizeFragment newInstance(Integer scale, Integer position) {
		Log.d("oops", "New resize fragment scale: " + scale + " position: "
				+ position);

		ImageResizeFragment f = new ImageResizeFragment();

		Bundle args = new Bundle();
		args.putInt("scale", scale);
		args.putInt("position", position);
		f.setArguments(args);

		return f;
	}

	@Override
	/**
	 * Attaches to an activity.
	 * 
	 * @param activity Activity to attach to
	 */
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			// Try to set the listener
			this.listener = (ImageResizeListener) activity;
		} catch (final ClassCastException e) {
			// Exception
			throw new ClassCastException(activity.toString()
					+ " must implement ChoiceListListener");
		}
	}

	@Override
	/**
	 * Overrides onCreateDialog method
	 */
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);

		Integer initialValue = getArguments().getInt("scale");
		final Integer position = getArguments().getInt("position");

		// Add the onclick listener
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Image Resize");
		LayoutInflater inflater = getActivity().getLayoutInflater();

		View dialogView = inflater.inflate(R.layout.image_resize, null);

		final NumberPicker picker = (NumberPicker) dialogView
				.findViewById(R.id.image_resize_picker);
		picker.setMinValue((int) ((float) StoryBitmapFactory.MIN_SIZE
				/ StoryBitmapFactory.DEFAULT_SIZE * 100));
		picker.setMaxValue((int) ((float) StoryBitmapFactory.MAX_SIZE
				/ StoryBitmapFactory.DEFAULT_SIZE * 100));
		picker.setWrapSelectorWheel(false);
		picker.setValue(initialValue);

		builder.setTitle("Image Resize");
		builder.setView(dialogView);
		builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onScaleChoice(picker.getValue(), position);
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Do Nothing
					}
				});

		return builder.create();
	}
}
