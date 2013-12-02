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

public class ImageResizeFragment extends DialogFragment {
	private static ImageResizeListener listener;

	static ImageResizeFragment newInstance(Integer scale, Integer position) {
		Log.d("oops", "New resize fragment scale: " + scale + " position: " + position);
		
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
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		
		Integer initialValue = getArguments().getInt("scale");
		final Integer position = getArguments().getInt("position");
		
		// Add the onclick listener
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Image Resize");
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		View dialogView = inflater.inflate(R.layout.image_resize, null);
		
		final NumberPicker picker = (NumberPicker) dialogView.findViewById(R.id.image_resize_picker);
		picker.setMinValue((int) ((float)StoryBitmapFactory.MIN_SIZE / StoryBitmapFactory.DEFAULT_SIZE * 100));
		picker.setMaxValue((int) ((float)StoryBitmapFactory.MAX_SIZE / StoryBitmapFactory.DEFAULT_SIZE * 100));
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
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do Nothing
			}
		});
		
		return builder.create();
	}
}
