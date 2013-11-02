package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ChoiceListFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<Choice> choices = app.getEditManager().getChoiceList();

		ChoiceListAdapter adapter = new ChoiceListAdapter(getActivity(),
				choices);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		StoryFragmentListListener activity = (StoryFragmentListListener) getActivity();
		ChoiceListAdapter adapter = (ChoiceListAdapter) getListAdapter();
		Choice choice = adapter.getChoiceAtPosition(position)
		activity.onChoiceSelected(choice);
	}
}
