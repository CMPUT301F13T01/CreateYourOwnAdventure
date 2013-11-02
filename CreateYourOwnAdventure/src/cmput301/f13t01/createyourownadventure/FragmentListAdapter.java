package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The FragmentListAdapter class is the ArrayAdapter for the FragmentList
 * Fragment. It handles inflating the list's views by extracting the strings
 * from the array FragmentInfo objects passed to its constructor. Activities
 * using this fragment must implement the StoryFragmentListListener interface.
 * 
 * @author Jesse Huard
 * @version 1.0, 30/10/13
 * 
 */

public class FragmentListAdapter extends ArrayAdapter<StoryFragmentInfo> {

	/**
	 * Holds the application context.
	 */
	private final Context context;

	/**
	 * The array of StoryFragment information displayed in the list.
	 */
	private final ArrayList<StoryFragmentInfo> info;

	public FragmentListAdapter(Context context, ArrayList<StoryFragmentInfo> info) {
		super(context, R.layout.story_fragment_list_item, info);
		this.context = context;
		this.info = info;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// Inflate the view
		View rowView = inflater.inflate(R.layout.story_fragment_list_item,
				parent, false);

		// Get the view's TextViews
		TextView title = (TextView) rowView
				.findViewById(R.id.fragment_list_title);
		TextView description = (TextView) rowView
				.findViewById(R.id.fragment_list_description);

		// Copy the story fragment's information into the view
		title.setText(info.get(position).getTitle());
		description.setText(info.get(position).getDescription());

		return rowView;
	}

	/**
	 * Gets the ID of the StoryFragment at the selected position.
	 * 
	 * @param position
	 *            the position of the desired story fragment ID.
	 * @return the ID of the selected story fragment
	 */
	public int getIdAtPosition(int position) {
		return info.get(position).getId();
	}
}
