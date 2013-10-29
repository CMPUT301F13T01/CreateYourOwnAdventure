package cmput301.f13t01.createyourownadventure;

/**
 * List of things to ask team about:
 * 
 * Story/Fragment attributes: manager cannot access them if they are private
 * Story: getter for a fragment using fragment name at story level?
 * History_stack: what is the setter for adding to the stack (save history)?
 * History_stack: what is the setter for deleting most recent of stack?
 * Fragment: is there a getter for content_list?
 * MediaHandler: how do I get a media's type?
 * Choice_map: there's some sort of changes to it afaik?
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ReadFragmentActivity extends Activity {

	ReadStoryManager manager;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// intent has the Story, and name of the fragment to display
		Intent intent = getIntent();
		// receive story via intent
		Bundle passed_story = intent.getExtras();
		Story story = (Story) passed_story.getSerializable("story");
		// receive name of story fragment to show
		String fragment_name = intent.getStringExtra("fragment_name");

		// set the view and controller
		final ReadFragmentView thisView = new ReadFragmentView(this);
		manager = new ReadStoryManager(story, fragment_name, thisView, this);

		// display the fragment with the view
		this.setContentView(thisView);

	}

	/**
	 * when activity pauses, need to save history stack
	 */
	protected void onPause() {
		super.onPause();
	}

	/**
	 * deals with user action bar selection
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_return_to_beginning:
			manager.toBeginning();
			return true;
		case R.id.action_return_to_previous_page:
			manager.toPrevious();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
