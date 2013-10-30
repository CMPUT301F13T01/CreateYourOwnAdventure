package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PreviewFragment extends android.support.v4.app.Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for fragment preview
		Fragment fragment = (Fragment) getArguments().getSerializable(getResources().getString(R.string.story_fragment));
		// Display the fragment
		for (Media temp: fragment.content_list) {
			
		}
	}
}
