/*
HelpFragment class for CreateYourOwnAdventure.
This class is used to create a dialog to show the help message for a particular activity.

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

package cmput301.f13t01.storylibrary;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cmput301.f13t01.R;

/**
 * This class is used to create a dialog to show the help message for a
 * particular activity. This dialog can be statically instantiated by handing it
 * a HelpMessage argument.
 * 
 * @author Jesse Huard
 * @see HelpMessage
 * 
 */

public class HelpFragment extends DialogFragment {

	private static final String helpString = "help";

	/**
	 * Constructor.
	 * 
	 * @param messageType the type of help message to display
	 * @return the HelpFragment made
	 */
	public static HelpFragment newInstance(HelpMessage messageType) {
		HelpFragment f = new HelpFragment();

		// Supply help message type as an argument.
		Bundle args = new Bundle();
		args.putSerializable(helpString, messageType);
		f.setArguments(args);

		return f;
	}

	@Override
	/**
	 * Override the onCreateView function
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		HelpMessage message = (HelpMessage) getArguments().getSerializable(
				helpString);

		View v = inflater.inflate(R.layout.help_fragment, container);
		TextView text = (TextView) v.findViewById(R.id.help_fragment_text);
		text.setText(message.toString());
		getDialog().setTitle(R.string.display_help);

		return v;
	}

}