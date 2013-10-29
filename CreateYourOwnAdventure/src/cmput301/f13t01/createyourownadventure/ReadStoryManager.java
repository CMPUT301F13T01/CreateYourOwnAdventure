package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ReadStoryManager implements OnItemClickListener {

	ReadFragmentView view = null;
	Fragment fragment = null;
	Context context = null;
	Story story;

	public ReadStoryManager(final Story story, final String fragment_name,
			final ReadFragmentView view, final ReadFragmentActivity context) {
		this.story = story;
		this.view = view;
		this.context = context;
		
		// fetch the fragment using the fragment name at story level?
		fragment = story.fragment_list.get(fragment_name);

		// set view's media according to media in fragment
		
		// get media_list, is there a getter for this?
		ArrayList<Media> media_list = fragment.content_list;
		
		// cycle through the media list
		for (int i = 0; i < media_list.size(); i++) {
			
			//get media's type somehow
			media_type = media_list.get(i);
			
			//if type is text
				//get media content's string as s
				String s;
				view.setTextView(s);
			
			//the rest are implemented later for iteration 2
			
			//else if type is image
			
			//else if type is sound
			
			//else if type is video
		}

		// from story level with fragment name, get the choice array list
		story.choice_map.get(fragment)
		
		// if choice array list isn't null:
		// choice = extract flavor text as an arraylist of strings for this fragment
		view.setChoiceView(choice, this);

	}

	/**
	 * Go to the beginning of a story
	 */
	public void toBeginning() {
		String first_fragment_name = story.first_page;

	}

	/**
	 * Go back to the previous page dictated by the history stack
	 */
	public void toPrevious() {
		String back_fragment_name = story.history_stack.go_back();
		// history remove last from stack

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// When a choice is clicked:

		// Create an intent and pass the appropriate destination fragment in
		// intent
		Intent intent = new Intent(context, ReadFragmentActivity.class);
		// destination = destination fragment according to choice map for this
		// choice
		// intent.putExtra("destination_fragment", destination);

	}

}
