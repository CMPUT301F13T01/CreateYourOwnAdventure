package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for fragment editing
		StoryFragment fragment = (StoryFragment) getArguments().getSerializable(getResources().getString(R.string.story_fragment));
		// Display the fragment
		for (Media temp: fragment.getContentList()) {
			
		}
	}

}
