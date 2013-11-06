/*
Adapter to display StoryInfo objects from MainActivity.

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
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
 * The StoryInfoListAdapter class is the ArrayAdapter for the StoryInfo list.
 * It is in charge of displaying the title and author of each story according
 * to a pre-defined format on the main selection screen.
 * 
 * @author Reginald Miller, Jesse Chu
 * @version 1.0, 30/10/13
 * 
 */

public class StoryInfoListAdapter extends ArrayAdapter<StoryInfo> {
	
	/* Instance Variables */
	// Required for ArrayAdapters
	Context context;
	int layoutResourceId;
	ArrayList<StoryInfo> storyInfoList;
	
	/**
	 * The standard constructor for the ArrayAdapter.
	 * 
	 * @param context   The context for the adapter (the activity)
	 * @param layoutResourceId   The resource ID of the layout
	 * @param storyInfoList   The ArrayList of StoryInfo objects to display
	 */
	public StoryInfoListAdapter(Context context, int layoutResourceId, ArrayList<StoryInfo> storyInfoList){
		super(context, layoutResourceId, storyInfoList);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.storyInfoList = storyInfoList;
	}
	
	@Override
	/**
	 * This is the method that returns the inflated view for displaying
	 * the available stories.
	 */
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		StoryInfoHolder holder = null;
		if (row==null) {
			// Inflate views
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            // Store display Views
            holder = new StoryInfoHolder();
            holder.title = (TextView) row.findViewById(R.id.item_title);
            holder.author = (TextView) row.findViewById(R.id.item_author);
            row.setTag(holder);
		} else {
            holder = (StoryInfoHolder) row.getTag();
        }
        final StoryInfo storyInfo = storyInfoList.get(position);
        if (storyInfo != null) {
        	// Displays StoryInfo previews
	        holder.title.setText(storyInfo.getTitle());
	        holder.author.setText(storyInfo.getAuthor());
        }
        return row;
	}
	// Temporary holder to set Views
    static class StoryInfoHolder {
        TextView title;
        TextView author;
    }
}
