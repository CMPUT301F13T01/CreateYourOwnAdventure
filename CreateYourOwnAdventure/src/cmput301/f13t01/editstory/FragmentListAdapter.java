/*
The FragmentListAdapter class is the ArrayAdapter for the FragmentList
Fragment. It handles inflating the list's views by extracting the strings
from the array FragmentInfo objects passed to its constructor. Activities
using this fragment must implement the StoryFragmentListListener interface.

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
package cmput301.f13t01.editstory;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cmput301.f13t01.R;
import cmput301.f13t01.model.StoryFragmentInfo;

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

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            context adapter is being used in
	 * @param info
	 *            ArrayList of StoryFragmentInfo for display
	 */
	public FragmentListAdapter(Context context,
			ArrayList<StoryFragmentInfo> info) {
		super(context, R.layout.story_fragment_list_item, info);
		this.context = context;
		this.info = info;
	}

	@Override
	/**
	 * Override getView
	 */
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
