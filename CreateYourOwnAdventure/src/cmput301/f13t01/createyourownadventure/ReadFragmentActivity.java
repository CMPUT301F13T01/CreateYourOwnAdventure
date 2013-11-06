/*
FragmentList Class for CreateYourOwnAdventure App.
ReadFragmentActivity, the activity called for reading any story
fragment. Relies on ReadFragmentView for the display and
ReadStoryManager to control the content and interaction.
    
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

import java.util.UUID;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Eddie <eddie@ualberta.ca>
 * 
 *         ReadFragmentActivity, the activity called for reading any story
 *         fragment. Relies on ReadFragmentView for the display and
 *         ReadStoryManager for story access.
 */
public class ReadFragmentActivity extends FragmentActivity implements OnItemClickListener {

	private boolean isNew;
	private ReadStoryManager manager;
	private int fragmentId;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_fragment);

		// intent has the story ID, and story fragment ID to display
		Intent intent = getIntent();
		// receive id of story fragment to show
		UUID storyId = (UUID) intent.getSerializableExtra("storyId");
		
		// Get the story manager and set the story
		GlobalManager app = (GlobalManager) getApplication();
		manager = app.getStoryManager();
		app.setStoryManager(storyId);
		
		// depending if we are reading from beginning, 
		// fetch the appropriate fragment ID accordingly
		isNew = (boolean) intent.getBooleanExtra(
				getResources().getString(R.string.fragment_is_new), false);
		if (isNew) {
			this.fragmentId = manager.getFirstPageId();
		}
		else {
			this.fragmentId = manager.getMostRecent();
		}

		// enable the Up button in action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/**
	 * places menu in action bar as specified
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Determines the resulting action of choosing a particular action in the
	 * action bar.
	 */
	public boolean onOptionsItemSeqlected(MenuItem item) {

		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_return_to_beginning:
			// Go back to the start of the story, clearing history stack
			toBeginning();
			return true;
		case R.id.action_return_to_previous_page:
			// Go back to the previous story fragment according to history stack
			toPrevious();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}	

	/**
	 * Go to the beginning (first page) of a story Apprehend the current page to
	 * the history stack History is cleared.
	 */
	public void toBeginning() {

		// get the fragment id of the story's first page
		Integer destinationId = manager.getFirstPageId();
		manager.clearHistory();

		// read the next story fragment
		// TO-DO
	}

	/**
	 * Go back to the previous page dictated by the history stack Remove the
	 * current page from the history stack
	 */
	public void toPrevious() {

		// go back to previous, adjusting history stack properly
		Integer destinationId = manager.getMostRecent();

		if (destinationId != null) {
			// read the next story fragment if there is a previous fragment in
			// the history stack
			// TO-DO
		} else {
			// go back to the previous level
			finish();
		}

	}
	
	/**
	 * On selection of a choice in view, direct the user to the next story
	 * fragment according to the choice map.
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// use position to refer to the choice item from choice map
		Choice choice = manager.getChoices(fragmentId).get(position);

		// 
		Integer destinationId = choice.getDestinationId();

		// add to the history stack
		manager.pushToStack(destinationId);

		// read the next story fragment
		// TO-DO

	}

}
