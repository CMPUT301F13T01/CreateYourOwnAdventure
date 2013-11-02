package cmput301.f13t01.createyourownadventure;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The ChoiceListAdapter class is the ArrayAdapter for the ChoiceList Fragment.
 * It handles inflating the list's views by extracting the StoryFragment titles
 * from the EditManager based on the StoryFragmentIds contained in the passed
 * Choice. Activities using this fragment must implement the ChoiceListListener
 * interface.
 * 
 * @author Jesse Huard
 * @version 1.0, 30/10/13
 * 
 */

public class ChoiceListAdapter extends ArrayAdapter<Choice> {

	/**
	 * Holds the application context.
	 */
	private final Context context;
	private final ArrayList<Choice> choices;

	public ChoiceListAdapter(Context context, ArrayList<Choice> choices) {
		super(context, R.layout.choice_list_item, choices);
		this.context = context;
		this.choices = choices;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		Activity activity = (Activity) context;
		GlobalManager app = (GlobalManager) activity.getApplication();
		EditManager manager = app.getEditManager();

		// Inflate the view
		View rowView = inflater.inflate(R.layout.choice_list_item, parent,
				false);

		// Get the view's TextViews
		TextView direction = (TextView) rowView
				.findViewById(R.id.choice_list_direction);
		TextView flavour = (TextView) rowView
				.findViewById(R.id.choice_list_flavour);

		String sourceTitle = manager.getFragmentInfo(
				choices.get(position).getSourceId()).getTitle();
		String destTitle = manager.getFragmentInfo(
				choices.get(position).getDestinationId()).getTitle();

		// Copy the story fragment's information into the view
		direction.setText(sourceTitle + " to " + destTitle);
		flavour.setText(choices.get(position).getFlavourText());

		return rowView;
	}

	/**
	 * Gets the Choice at the selected position.
	 * 
	 * @param position
	 *            the position of the desired Choice.
	 * @return the selected Choice
	 */
	public Choice getChoiceAtPosition(int position) {
		return choices.get(position);
	}
}
