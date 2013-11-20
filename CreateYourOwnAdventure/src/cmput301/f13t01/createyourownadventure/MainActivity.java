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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

/**
 *  Sets up and handles main screen ui that allows user to 
 *  display stories saved on device, select a story to read from beginning, select a story to read 
 *  from last history, select a story to edit a story, select a story to delete,
 *  browse for online stories, and create a new story
 *  
 *  @author Gerald Manweiler
 *  @version 1.3 Nov 6 2013
 */
public class MainActivity extends Activity {
	// local manager object to handle local story saving
	private LocalManager objLibrary;
	//story list view object
	private ListView lsvStories = null;
	//story info array list object
	private ArrayList<StoryInfo> storyInfoList;
	// adapter for story info array list
	private StoryInfoListAdapter objStoryAdapter;
	
	/**
	 * Create main screen
	 * 
	 * @param savedInstanceState    The state of activity at last kill time
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//get last instance state and set view to main screen
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//set list view and register view for on long click contextual menu
		lsvStories = (ListView) findViewById(R.id.main_activity_listview);
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
	    inflater.inflate(R.menu.main_actionbar_menu, menu);
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
	        //user wants browse online stories
	        case R.id.action_browse_online_stories:
	        	//start browse online story child activity
	            startBrowseOnlineStories();
	            return true;
	        case R.id.action_create_new_story:
	        	//start create new story child activity
	            startCreateNewStory();
	            return true;
	        case R.id.action_random_story:
	        	//select a random story to start reading at begininng       	
	        	startRandomStory();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/**
	 * Create contextual menu on long click of story item in story list view
	 * 
	 * @param menu    The menu xml resource to inflate into contextual menu
	 * @param v    The view in which create the contextual menu
	 * @param menuInfo    The additional info about item selected
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		//inflate menu specified in xml resource
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_story_menu, menu);
	}
	
	/**
	 * Handle user selection of an on long click contextual menu item
	 * 
     * @param item    The menu item resource selected by user
	 * @return boolean    Returns true for successful handling of menu item selection, false otherwise (from superclass)	
    */
	@SuppressWarnings("unused")
	@Override
	public boolean onContextItemSelected (MenuItem item) {
		//get the menu item info
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		//now get the story info for the the selected story in list view
		StoryInfo storyPicked = objStoryAdapter.getItem(info.position);
	    switch (item.getItemId()) {
        //user wants start reading at beginning of story
        case R.id.action_beginning:
        	//get the story uuid and pass it to start reading at begininng
        	startAtBeginning(storyPicked.getId());
        	return true;
        //user wants continue reading from last page of history
        case R.id.action_continue:
        	//get the story uuid and pass it to startContinueStory
        	startContinueStory(storyPicked.getId());
        	return true;
        //user wants to edit the story
        case R.id.action_edit_story:
        	//get the story uuid and pass it to startEditStory
            startEditStory(storyPicked.getId());
            return true;
        //user wants to delete story
        case R.id.action_delete_story:
            startDeleteStory(storyPicked.getId());
            return true;        	
        default:
            return super.onOptionsItemSelected(item);
	    }		

	}
	
	/**
	 * select random story and opens it for reading from beginning
	 */
	private void startRandomStory() {
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_random_story), Toast.LENGTH_SHORT);
		toast.show();
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
	}

	/**
	 * Starts create new story child activity
	 */
	private void startCreateNewStory() {
		//TODO quick and dirty test, replace with a toast
		System.out.println("You selected Create New Story");
		//create the intent to launch create new story activity
	    Intent intent = new Intent(this, EditStoryActivity.class);
		intent.putExtra(getResources().getString(R.string.story_is_new), true);
        startActivity(intent);
	}

	/**
	 * Starts browse online story child activity
	 */
	private void startBrowseOnlineStories() {
		//TODO quick and dirty test
		System.out.println("You selected Browse Online Stories");
	}
	
	/**
	 * Starts edit story child activity
	 * 
	 * @param storyId    UUID of story selected by user
	 */
	private void startEditStory(UUID storyId) {
		//TODO quick and dirty test replace with a toast
		System.out.println("You selected Edit Story");
		//create the intent to launch edit story activity
	    Intent intent = new Intent(this, EditStoryActivity.class);
	    intent.putExtra(getResources().getString(R.string.story_is_new), false);
		intent.putExtra(getResources().getString(R.string.story_id), storyId);
        startActivity(intent);		
	}

	/**
	 * Starts browse online story child activity
	 * 
	 * @param storyId    UUID of story selected by user 
	 */
	private void startDeleteStory(UUID storyId) {
		//quick and dirty test
		System.out.println("You selected Delete Story");
		//Send the signal to the LocalManager to delete the story
		objLibrary.removeStory(storyId);
		//update the ListView
		storyInfoList = objLibrary.getStoryInfoList();
		objStoryAdapter = new StoryInfoListAdapter(this, R.layout.story_info_list_item, storyInfoList);
		lsvStories.setAdapter(objStoryAdapter);
		objStoryAdapter.notifyDataSetChanged();

	}
	/**
	 * Starts read from last history child activity
	 */
	private void startContinueStory(UUID storyId) {
		//quick and dirty test
		System.out.println("You selected Continue Story");
		//create the intent to launch read story activity		
	    Intent intent = new Intent(this, ReadFragmentActivity.class);
	    intent.putExtra(getResources().getString(R.string.story_continue), false);
		intent.putExtra(getResources().getString(R.string.story_id), storyId);
        startActivity(intent);
	}

	/**
	 * Starts reading story at beginning child activity
	 * 
	 * @param storyId    UUID of story selected by user
	 */
	private void startAtBeginning(UUID storyId) {
		//quick and dirty test
		System.out.println("You selected Start at Beginning");
		//create the intent to launch read story activity
	    Intent intent = new Intent(this, ReadFragmentActivity.class);
	    intent.putExtra(getResources().getString(R.string.story_continue), true);
		intent.putExtra(getResources().getString(R.string.story_id), storyId);
        startActivity(intent);		
	}
	
	
}
