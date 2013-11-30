/*
The ChoiceListAdapter class is the ArrayAdapter for the ChoiceList Fragment.
It handles inflating the list's views by extracting the StoryFragment titles
from the EditManager based on the StoryFragmentIds contained in the passed
Choice. Activities using this fragment must implement the ChoiceListListener
interface.

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

	/**
	 * Constructor, sets the context and choices
	 * 
	 * @param context
	 * @param choices
	 */
	public ChoiceListAdapter(Context context, ArrayList<Choice> choices) {
		super(context, R.layout.choice_list_item, choices);
		this.context = context;
		this.choices = choices;
	}

	@Override
	/**
	 * Creates and inflates views for choices
	 * 
	 * @param position Position of the choice to display
	 * @param convertView View to convert to
	 * @param parent the parent View
	 * @return the view of the row
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		Activity activity = (Activity) context;
		GlobalManager app = (GlobalManager) activity.getApplication();
		ReadStoryManager manager = app.getStoryManager();

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
	 * @param position the position of the desired Choice.
	 * @return the selected Choice
	 */
	public Choice getChoiceAtPosition(int position) {
		return choices.get(position);
	}
}
