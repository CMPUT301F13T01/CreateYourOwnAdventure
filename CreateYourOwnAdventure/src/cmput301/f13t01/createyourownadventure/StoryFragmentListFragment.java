package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class StoryFragmentListFragment extends DialogFragment {

	private StoryFragmentListListener listener;
	private FragmentListAdapter adapter;
	
	static StoryFragmentListFragment newInstance() {
		StoryFragmentListFragment f = new StoryFragmentListFragment();

		// Supply id input as an argument.
		Bundle args = new Bundle();
		f.setArguments(args);

		return f;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			this.listener = (StoryFragmentListListener) activity;
		} catch (final ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement StoryFragmentListListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<StoryFragmentInfo> info = app.getStoryManager()
				.getFragmentInfoList();

		FragmentListAdapter adapt = new FragmentListAdapter(getActivity(), info);
		this.adapter = adapt;

		// Add the onclick listener
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.story_fragments);
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				int fragmentId = adapter.getIdAtPosition(item);
				listener.onStoryFragmentSelected(fragmentId);
			}
		});
		return builder.create();
	}

/*	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Dialog dialog = getDialog();
		Window window = dialog.getWindow();
		window.setGravity(Gravity.TOP);

		return super.onCreateView(inflater, container, savedInstanceState);
	}*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GlobalManager app = (GlobalManager) getActivity().getApplication();
		ArrayList<StoryFragmentInfo> info = app.getStoryManager().getFragmentInfoList();

		this.adapter = new FragmentListAdapter(getActivity(), info);

	}
}