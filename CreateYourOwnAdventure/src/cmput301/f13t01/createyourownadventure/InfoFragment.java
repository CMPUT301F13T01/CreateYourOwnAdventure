/**
 * 
 */
package cmput301.f13t01.createyourownadventure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @author Jesse Huard
 * 
 */
public class InfoFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for fragment information
		View infoView = inflater.inflate(R.layout.fragment_info, container, false);
		
		// Get the StoryFragment from the arguments
		StoryFragment fragment = (StoryFragment) getArguments().getSerializable(getResources().getString(R.string.story_fragment));
		
		// Find the proper EditText views
		EditText title = (EditText) infoView.findViewById(R.id.fragment_title);
		EditText description = (EditText) infoView.findViewById(R.id.fragment_description);
		
		// Initialize the views from the StoryFragment
		title.setText(fragment.getTitle());
		description.setText(fragment.getDescription());
		
		return infoView;
	}
}
