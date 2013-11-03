package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;

public class ChoiceListFragment extends DialogFragment {

	private ChoiceListListener listener;
	private ChoiceListAdapter adapter;

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
		ArrayList<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice("I'm a drunken sailor!"));
		choices.add(new Choice("Let's go to space!"));
		// app.getStoryManager().getChoiceList(
		// fragmentId);

		ChoiceListAdapter adap = new ChoiceListAdapter(getActivity(), choices);
		this.adapter = adap;

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.pick_choice);
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Choice choice = adapter.getChoiceAtPosition(item);
				listener.onChoiceSelected(choice);
			}
		});
		return builder.create();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Dialog dialog = getDialog();
		Window window = dialog.getWindow();
		window.setGravity(Gravity.TOP);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * int fragmentId = savedInstanceState.getInt(getResources().getString(
		 * R.string.story_id));
		 */

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = new ArrayList<Choice>();
		choices.add(new Choice("I'm a drunken sailor!"));
		choices.add(new Choice("Let's go to space!"));
		// app.getStoryManager().getChoiceList(
		// fragmentId);

		ChoiceListAdapter adapter = new ChoiceListAdapter(getActivity(),
				choices);
	}
}
