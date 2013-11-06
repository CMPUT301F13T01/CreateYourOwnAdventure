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

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Eddie <eddie@ualberta.ca>
 * 
 *         ReadFragmentActivity, the activity called for reading any story
 *         fragment. Relies on ReadFragmentView for the display and
 *         ReadStoryManager to control the content and interaction.
 */
public class ReadFragmentActivity extends FragmentActivity implements OnItemClickListener {

	ReadStoryManager manager;

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_fragment);

		// intent has the story ID, and story fragment ID to display
		Intent intent = getIntent();
		// receive id of story fragment to show
		UUID storyId = (UUID) intent.getSerializableExtra("storyId");
		Boolean fromBeginning = (Boolean) intent
				.getSerializableExtra("fromBeginning");

		// set the controller
		manager = new ReadStoryManager(storyId, fromBeginning, viewFragment, this);

		// enable the Up button in action bar
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		setContentView(R.layout.view_fragment);

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
			manager.toBeginning();
			return true;
		case R.id.action_return_to_previous_page:
			// Go back to the previous story fragment according to history stack
			manager.toPrevious();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
