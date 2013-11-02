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
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
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
	
    static class StoryInfoHolder {
        TextView title;
        TextView author;
    }
}
