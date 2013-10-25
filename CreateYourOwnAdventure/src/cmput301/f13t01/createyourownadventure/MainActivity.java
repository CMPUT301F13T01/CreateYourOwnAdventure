/**
 * CreateYourOwnAdventure
 * Gerald Manweiler
 * Copyright 2013 Gerald Manweiler Eddie Tai Jesse Chu Jesse Huard Reggie Miller
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */ 
package cmput301.f13t01.createyourownadventure;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * sets up and handles main screen ui
 */
public class MainActivity extends Activity{
	//instantiate and initialize click listener and set list view listener
	private OnItemClickListener objListListen = null;
	//Listview to show locally cached stories
	private ListView lsvStories;
	
	/**
	 * create screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//set list view widget and its listener
		this.lsvStories = (ListView) findViewById(R.id.activity_main_story_list);
		lsvStories.setOnItemClickListener(this.objListListen);
	}
	
	/**
	 * resume activity
	 */
	protected void onResume() {
		super.onResume();
		//TODO need contract from Library class
	}
	
	/**
	 * places menu in action bar as specified
	 */	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    //inflate menu items specified in xml and return 
	    inflater.inflate(R.menu.main_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * deals with user action bar selection
	 */		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	        //user wants browse online stories
	        case R.id.action_browse_online_stories:
	        	//start browse online story child activity
	            startBrowseOnlineStories();
	            return true;
	        case R.id.action_create_new_story:
	        	//start create new story child activity
	            startCreateNewStory();
	            return true;	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	/**
	 * starts create new story child activity
	 */
	private void startCreateNewStory() {
		//quick and dirty test
		System.out.println("You selected Create New Story");
	}

	/**
	 * starts browse online story child activity
	 */
	private void startBrowseOnlineStories() {
		//quick and dirty test
		System.out.println("You selected Browse Online Stories");
	}
}
