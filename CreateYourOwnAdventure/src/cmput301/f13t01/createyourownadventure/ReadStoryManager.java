package cmput301.f13t01.createyourownadventure;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Eddie
 *
 */
public class ReadStoryManager implements OnItemClickListener {

	ReadFragmentView view = null;
	Fragment fragment = null;
	Context context = null;
	Story story;
	
	public ReadStoryManager(final Integer fragment_id,
			final ReadFragmentView view, final ReadFragmentActivity context) {
		this.view = view;
		this.context = context;
		
		// fetch the fragment from the story level
		StoryFragmentList fragmentlist = story.getFragmentList();
		fragment = fragmentlist.get_fragment(fragment_id);

		// set view's media according to media in fragment
		
		// get media_list, is there a getter for this?
		ArrayList<Media> media_list = fragment.content_list;
		
		// cycle through the media list
		for (int i = 0; i < media_list.size(); i++) {
			
			//get media's type somehow
			Class media_type = media_list.get(i).getClass();
			if(media_type == Text.class.getClass())
			
			//if type is text
				//get media content's string as s
				String s;
				view.setTextView(s);
			
			//the rest are implemented later for iteration 2
			
			//else if type is image
			//if(media_type == Image.class.getClass())
			
			//else if type is sound
			//if(media_type == Audio.class.getClass())
			
			//else if type is video
			//if(media_type == Video.class.getClass())
		}

		// from story level with fragment id, get the choice map
		// from choice map, use fragment id to get arraylist of choice object
		// choice object has getters for flavor text and destination ID
		
		// if choice array list isn't null:
		// choice = extract flavor text as an arraylist of strings for this fragment
		
		// if choice array is null, then no choices
		
		// set the view
		view.setChoiceView(choice, this);

	}

	/**
	 * Go to the beginning of a story
	 */
	public void toBeginning() {
		Integer first_page_id = story.get_first_page();

	}

	/**
	 * Go back to the previous page dictated by the history stack
	 */
	public void toPrevious() {
		Integer previous_page_id = story.history_stack.go_back();
		// history remove last from stack by itself
		
		// 

	}
	
	/**
	 * @param story
	 */
	public void setStory(Story story) {
		this.story = story;
	}

	/**
	 * On selection of a choice in view, direct the user to the next story fragment
	 * according to the choice map.
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// When a choice is clicked:

		// Create an intent and pass the appropriate destination fragment in
		// intent
		Intent intent = new Intent(context, ReadFragmentActivity.class);
		// destination = destination fragment according to choice map for this
		// choice
		Intent intent = createIntent(story, destinationId);
		

	}
	
	/**
	 * Create an intent with the story and necessary fragment ID in order to display the
	 * next necessary fragment
	 * @param story the Story object that the user is reading at the moment
	 * @param id the id of the fragment to display next
	 * @return the intent that has the story and the fragment id in a bundle
	 */
	public Intent createIntent(Story story, Integer id) {
		Intent intent = new Intent(context, ReadFragmentActivity.class);
		intent.putExtra("story", story);
		intent.putExtra("fragment_id", id);
		return intent;
	}

}
