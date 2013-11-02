package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PreviewFragment extends android.support.v4.app.Fragment {

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
				TextView text = new TextView(getActivity());
				text.setText((CharSequence) media.getContent());
				container.addView(text);
			}
		}
		return container;
	}
}
