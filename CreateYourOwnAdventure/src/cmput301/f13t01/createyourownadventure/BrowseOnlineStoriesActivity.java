/*
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

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Sets up and handles browse online ui that allows user to search for and display
 * stories online, select an online story to read from beginning, select an online
 * story to save
 * 
 * @author Gerald Manweiler
 * @version 1.0 Nov 23 2013
 *
 */
public class BrowseOnlineStoriesActivity extends Activity{
	// local manager object to handle local story saving
	private LocalManager objLibrary;
	//story list view object
	private ListView lsvStories = null;
	//story info array list object
	private ArrayList<StoryInfo> storyInfoList;
	// adapter for story info array list
	private StoryInfoListAdapter objStoryAdapter;	

	/**
	 * Create Browse Online Story Screen
	 * 
	 * @param saveInstanceState The state of activity last kill
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//get last instance state and set view to main screen
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_online_activity);
		//set list view and register view for on long click contextual menu
		lsvStories = (ListView) findViewById(R.id.browse_online_activity_listview);
		registerForContextMenu(lsvStories);		
		
		//grab the local manager
		GlobalManager app = (GlobalManager) getApplication();
		objLibrary = app.getLocalManager();
	}
	
	/**
	 * Resume activity updates the story list view
	 */
	protected void onResume() {
		super.onResume();
		//get the story info list from Local manager for the story list adapter
		storyInfoList = objLibrary.getStoryInfoList();
		//initialize adapter and update the view
		objStoryAdapter = new StoryInfoListAdapter(this, R.layout.story_info_list_item, storyInfoList);
		lsvStories.setAdapter(objStoryAdapter);
		objStoryAdapter.notifyDataSetChanged();
	}
	
	
	/**
	 * Places browse online and create new story icons in action bar
	 * 
	 * @param menu    The action bar menu xml resource to inflate into action bar
	 * @return boolean    True for display menu, false for no display
	 */	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    //inflate menu items specified in xml and return 
	    inflater.inflate(R.menu.browse_online_actionbar_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	/**
	 * Deals with user action bar selection
	 * 
	 * @param item    The menu item resource selected by user
	 * @return boolean    Returns true for successful handling of menu item selection, false otherwise (from superclass)
	 */		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    
	    switch (item.getItemId()) {
	        //user wants to clear inputs for new search
	        case R.id.action_new_search:
	        	clearInputBoxes();
	        	return true;
	        //user wants to get online stories
	        case R.id.action_search_online_stories:
	            startSearchOnline();
	            return true;
	        //user wants next 20 stories
	        case R.id.action_get_next_20:
	            getNextTwenty();
	            return true;
	        //user wants to save  SELECTED STORY TO LOCAL DEVICE
	        case R.id.action_save_online_story:
	        	saveToLocal();
	        	return true;
	        //user wants a random story
	        case R.id.action_random_story:       	
	        	startRandomStory();
	        	return true;
	        //user wants the help menu
			case R.id.action_help:
				onSelectHelp();	
				return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * save online story to local device
	 */
	private void saveToLocal(){
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_save_online_story), Toast.LENGTH_SHORT);
		toast.show();
	}
	/**
	 * clears input boxes for new search
	 */
	private void clearInputBoxes() {
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_new_search), Toast.LENGTH_SHORT);
		toast.show();
		//implement edit box entry wipeout
	}
	
	/**
	 * search for online stories based on search input parameters
	 */
	private void startSearchOnline(){
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_search_online_stories), Toast.LENGTH_SHORT);
		toast.show();		
	}
	
	/**
	 * get next 20 stories
	 */
	private void getNextTwenty(){
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_get_next_20), Toast.LENGTH_SHORT);
		toast.show();
	}
	/**
	 * displays screen specific help
	 */
	private void onSelectHelp() {
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		android.app.Fragment prev = getFragmentManager().findFragmentByTag("help_dialog");
		if (prev != null) {
			ft.remove(prev);
		}
		ft.addToBackStack(null);
		android.app.DialogFragment newFragment = (android.app.DialogFragment) HelpFragment.newInstance(HelpMessage.BROWSE_ONLINE);
		newFragment.show(ft, "help_dialog");
	}
	
	/**
	 * select random story and opens it for reading from beginning
	 */
	private void startRandomStory() {
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_random_story), Toast.LENGTH_SHORT);
		toast.show();
		
		/*
		//get the story info list from manager and the size of list
		ArrayList<StoryInfo> randStorySource = objLibrary.getStoryInfoList();
		//if list size zero or less, no stories to select
		if (randStorySource.size() <= 0){
			return;
		}
		else
		{
			//new random object and story list size
		 	Random r = new Random();
		 	int listSize = randStorySource.size();
			//get next integer random number in range 0 to listSize - 1		 	
			int randStory = r.nextInt(listSize);
			//get the uuid of the randomly selected story
		    UUID randStoryId = randStorySource.get(randStory).getId();
			//create the intent and launch read story activity from beginning
		    Intent intent = new Intent(this, ReadFragmentActivity.class);
		    intent.putExtra(getResources().getString(R.string.story_continue), true);
			intent.putExtra(getResources().getString(R.string.story_id), randStoryId);
	        startActivity(intent);	
		}
		*/
	}	

}
