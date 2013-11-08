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

	private ChoiceListListener listener;
	private ChoiceListAdapter adapter;
	private static final String idString = "id";

	static ChoiceListFragment newInstance(int fragmentId) {
		ChoiceListFragment f = new ChoiceListFragment();

		// Supply id input as an argument.
		Bundle args = new Bundle();
		args.putInt(idString, fragmentId);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.listener = (ChoiceListListener) activity;
		} catch (final ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement ChoiceListListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int fragmentId = getArguments().getInt(idString);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);

		ChoiceListAdapter adapt = new ChoiceListAdapter(getActivity(), choices);
		this.adapter = adapt;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		Dialog dialog = getDialog();
//		Window window = dialog.getWindow();
//		window.setGravity(Gravity.TOP);

		int fragmentId = getArguments().getInt(idString);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);

		this.adapter = new ChoiceListAdapter(getActivity(), choices);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int fragmentId = getArguments().getInt(idString);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getStoryManager()
				.getChoices(fragmentId);

		this.adapter = new ChoiceListAdapter(getActivity(), choices);
	}
}
