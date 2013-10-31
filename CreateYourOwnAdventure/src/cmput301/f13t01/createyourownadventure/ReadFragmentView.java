package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.content.Context;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author Eddie
 * 
 */
public class ReadFragmentView extends ScrollView {

	// declaration of variables
	LinearLayout ll;
	Context context;

	/**
	 * Constructor for the view. A LinearLayout is added to the ScrollView since
	 * ScrollView only accepts 1 possible view, and we need to populate the view
	 * with multiple views, depending on the story fragment and its content
	 * media files.
	 * 
	 * @param context the activity that the view belongs to
	 */
	public ReadFragmentView(Context c) {
		super(c);

		// add LinearLayout to ScrollView since ScrollView only accepts 1 view
		ll = new LinearLayout(context);
		ll.setOrientation(LinearLayout.VERTICAL);
		this.addView(ll);

		// all views within the ScrollView obviously shares the same context
		context = c;

	}

	/**
	 * Add a TextView with content of string s to the current view
	 * 
	 * @param s
	 *            string to be displayed by the TextView
	 * @param context
	 *            the context of the view
	 */
	public void setTextView(String s) {
		TextView tv = new TextView(context);
		tv.setText("s");
		ll.addView(tv);
	}

	/**
	 * Incomplete ImageView handler
	 */
	public void setImageView() {
		ImageView iv = new ImageView(context);
		ll.addView(iv);
	}

	/**
	 * Shows the list of choices using their given flavor text
	 * @param choice_text Arraylist of strings indicating the flavor text
	 * @param onItemClickListener controller for the views
	 */
	public void setChoiceView(ArrayList<String> choice_text,
			OnItemClickListener onItemClickListener) {
		
		ListView lv = new ListView(context);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				R.layout.list_choices, choice_text);
		lv.setAdapter(adapter);

		// set the controller for these list items
		lv.setOnItemClickListener(onItemClickListener);
	}

}
