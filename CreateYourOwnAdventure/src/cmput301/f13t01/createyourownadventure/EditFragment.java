package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditFragment extends Fragment {

	@SuppressWarnings("rawtypes")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		StoryFragment fragment = (StoryFragment) getArguments()
				.getSerializable(
						getResources().getString(R.string.story_fragment));
		ArrayList<Media> content = fragment.getContentList();

		// Inflate the view
		View scrollable = inflater.inflate(R.layout.edit_fragment, container,
				false);

		LinearLayout layout = (LinearLayout) scrollable
				.findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// Display the fragment
		for (Media media : content) {
			if (media.getClass().equals(Text.class)) {
				EditText edit = new EditText(getActivity());
				edit.setText((CharSequence) media.getContent());
				layout.addView(edit, params);
			}
		}
		return scrollable;
	}

}
