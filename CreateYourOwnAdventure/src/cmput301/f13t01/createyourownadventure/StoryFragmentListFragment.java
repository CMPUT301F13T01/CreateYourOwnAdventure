package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class StoryFragmentListFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<FragmentInfo> info = app.getEditManager()
				.getFragmentInfoList();

		FragmentListAdapter adapter = new FragmentListAdapter(getActivity(),
				info);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		StoryFragmentListListener activity = (StoryFragmentListListener) getActivity();
		FragmentListAdapter adapter = (FragmentListAdapter) getListAdapter();
		int storyId = adapter.getIdAtPosition(position)
		activity.onStoryFragmentSelected(storyId);
	}
}