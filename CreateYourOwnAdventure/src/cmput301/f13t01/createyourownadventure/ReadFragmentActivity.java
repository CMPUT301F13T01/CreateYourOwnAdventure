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
 * 
 * Self reminder for git usages:
 * git status
 * git commit -m -a "comments"
 * git push http://github.com/CMPUT301F13T01/CreateYourOwnAdventure etai
 * 
 * do not use name of class in a class's locat variable
 * use camel case (no understore style)
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

		// intent has the story ID, and story fragment ID to display
		Intent intent = getIntent();
		// receive id of story fragment to show
		Integer storyId = intent.getIntExtra("storyId", 0);
		Integer fragmentId = intent.getIntExtra("fragmentId", 0);

		// set the view and controller
		final ReadFragmentView thisView = new ReadFragmentView(this);
		manager = new ReadStoryManager(fragmentId, thisView, this);
		GlobalManager globalmanager = (GlobalManager)getApplication();
		globalmanager.setStoryManagers(storyId);

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
