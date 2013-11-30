/*
ChoiceListFragment class for CreateYourOwnAdventure.
This class is used to create a dialog to show all available choices 
for a fragment, and handle user selection of choices.

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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * This class is used to create a dialog to show all available choices for a
 * fragment, and handle user selection of choices. This dialog can be statically
 * instantiated by giving it a fragmentId.
 * 
 * @author Jesse Huard
 * 
 */

public class ChoiceListFragment extends DialogFragment {

	// Fragment variables
	private ChoiceListListener listener;
	private ChoiceListAdapter adapter;
	private static final String idString = "id";

	/**
	 * Constructor, static.
	 * 
	 * @param fragmentId 
	 * @return the ChoiceListFragment
	 */
	static ChoiceListFragment newInstance(int fragmentId) {
		// Instantiate a new fragment
		ChoiceListFragment fragment = new ChoiceListFragment();
		// Supply id input as an argument.
		Bundle args = new Bundle();
		args.putInt(idString, fragmentId);
		fragment.setArguments(args);

		return fragment;
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
			this.listener = (ChoiceListListener) activity;
		} catch (final ClassCastException e) {
			// Exception
			throw new ClassCastException(activity.toString()
					+ " must implement ChoiceListListener");
		}
	}

	@Override
	/**
	 * Override onCreateDialog method for the Dialog.
	 * 
	 * @param savedInstanceState
	 */
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Call onCreate of super-class
		super.onCreate(savedInstanceState);
		// Get the fragment ID
		int fragmentId = getArguments().getInt(idString);
		// Get Story Manager from the GlobalManager
		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);
		// Get and set adapter
		ChoiceListAdapter adapt = new ChoiceListAdapter(getActivity(), choices);
		this.adapter = adapt;
		// Set up builder/adapter
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.pick_choice);
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Choice choice = adapter.getChoiceAtPosition(item);
				listener.onChoiceSelected(choice, item);
			}
		});
		return builder.create();
	}

	@Override
	/**
	 * Override onCreateView method for a View
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		Dialog dialog = getDialog();
//		Window window = dialog.getWindow();
//		window.setGravity(Gravity.TOP);
		
		// Get Fragment ID
		int fragmentId = getArguments().getInt(idString);
		// Get the StoryManager
		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);
		// Instantiate Adapter
		this.adapter = new ChoiceListAdapter(getActivity(), choices);
		// Inflate the View
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	/**
	 * Override onCreate method
	 */
	public void onCreate(Bundle savedInstanceState) {
		// Call super-class onCreate
		super.onCreate(savedInstanceState);
		// Get Fragment ID
		int fragmentId = getArguments().getInt(idString);
		// Get Story Manager
		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);
		// Instantiate Adapter
		this.adapter = new ChoiceListAdapter(getActivity(), choices);
	}
}
