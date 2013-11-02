package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ChoiceListFragment extends ListFragment {
	
	private ChoiceListListener listener;
	
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int fragmentId = savedInstanceState.getInt(getResources().getString(R.string.story_id));


		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getEditManager().getChoiceList(fragmentId);

		ChoiceListAdapter adapter = new ChoiceListAdapter(getActivity(),
				choices);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ChoiceListAdapter adapter = (ChoiceListAdapter) getListAdapter();
		Choice choice = adapter.getChoiceAtPosition(position)
		listener.onChoiceSelected(choice);
	}
}
