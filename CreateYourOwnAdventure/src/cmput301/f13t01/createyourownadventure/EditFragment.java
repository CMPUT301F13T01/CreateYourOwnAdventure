package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditFragment extends Fragment {

	@SuppressWarnings("rawtypes")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		StoryFragment fragment = (StoryFragment) getArguments()
				.getSerializable(
						getResources().getString(R.string.story_fragment));

		// Display the fragment
		for (Media media : fragment.getContentList()) {
			if (media.getClass().equals(Text.class)) {
				EditText edit = new EditText(getActivity());
				edit.setText((CharSequence) media.getContent());
				container.addView(edit);
			}
		}
		return container;
	}

}
