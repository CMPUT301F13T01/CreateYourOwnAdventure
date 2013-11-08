/*
InfoFragment class for CreateYourOwnAdventure.
This is the fragment that displays the information of a
given story fragment.

     Copyright  ©2013 Jesse Huard
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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * The fragment that is used to display the information
 * of a given story fragment.
 * 
 * @author Jesse Huard
 * 
 */
public class InfoFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for fragment information
		View infoView = inflater.inflate(R.layout.fragment_info, container,
				false);

		// Get the StoryFragment from the arguments
		Bundle bundle = getArguments();
		if (bundle != null) {
			StoryFragment fragment = (StoryFragment) bundle
					.getSerializable(getResources().getString(
							R.string.story_fragment));

			// Find the proper EditText views
			EditText title = (EditText) infoView
					.findViewById(R.id.fragment_title);
			EditText description = (EditText) infoView
					.findViewById(R.id.fragment_description);

			// Initialize the views from the StoryFragment
			title.setText(fragment.getTitle());
			description.setText(fragment.getDescription());
		}

		return infoView;
	}
}
