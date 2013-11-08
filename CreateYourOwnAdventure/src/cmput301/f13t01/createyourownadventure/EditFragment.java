/*
EditFragment class for CreateYourOwnAdventure.
This is the fragment that will contain the means to add,
delete, and edit media for a particular story fragment.

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

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Displays a story fragment and allows for it to be edited.
 * 
 * @author Jesse Huard
 * @deprecated
 * 
 */

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
