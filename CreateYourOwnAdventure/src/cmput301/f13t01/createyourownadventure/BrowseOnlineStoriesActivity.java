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
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import cmput301.f13t01.elasticsearch.ESClient;
import cmput301.f13t01.elasticsearch.ESManager;
import cmput301.f13t01.elasticsearch.SearchManager;

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
	//manager objects for local and online
	private LocalManager objLibrary;
	private ESManager esLibrary;
	//arraylist and its adapter
	private ArrayList<StoryInfo> results;
	private StoryInfoListAdapter objStoryAdapter;
	//widgets objects
	private ListView lsvStories = null;
	private EditText searchTitle;
	private EditText searchAuthor;
	private EditText searchDesc;

	/**
	 * Create Browse Online Story Screen
	 * 
	 * @param saveInstanceState The state of activity last kill
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//get last instance state and set view to main screen
		super.onCreate(savedInstanceState);
		//get view, set list view and, register it for contextual menu
		setContentView(R.layout.browse_online_activity);
		lsvStories = (ListView) findViewById(R.id.browse_online_activity_listview);
		registerForContextMenu(lsvStories);		
		//set up text input boxes
		searchTitle = (EditText) findViewById(R.id.search_title);
		searchAuthor = (EditText) findViewById(R.id.search_author);
		searchDesc = (EditText) findViewById(R.id.search_description);
		
		/*
		//Set default click behaviour to read story from continue point
		lsvStories.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				StoryInfo selectedStory = results.get(position); 
				UUID clickId = selectedStory.getId();
				//save to local
				saveToLocal();
				startAtBeginning(clickId);
			}
		});
		*/
		
		//grab the managers
		GlobalManager app = (GlobalManager) getApplication();
		objLibrary = app.getLocalManager();
		esLibrary = app.getESManager();
	}
	
	/**
	 * Resume activity clears the search input boxes
	 */
	protected void onResume() {
		super.onResume();
		//clear the search input boxes
		clearInputBoxes();
		//get all the story infos list from  es manager for the story list adapter
		results = esLibrary.getStoryInfoList(0);
		//initialize adapter and update the view
		objStoryAdapter = new StoryInfoListAdapter(this, R.layout.story_info_list_item, results);
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
	 * Create contextual menu on long click of story item in story list view
	 * 
	 * @param menu        The menu xml resource to inflate into contextual menu
	 * @param v           The view in which create the contextual menu
	 * @param menuInfo    The additional info about item selected
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		//inflate menu specified in xml resource
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.browse_online_story_menu, menu);
	}
	
	/**
	 * Handle user selection of an on long click contextual menu item
	 * 
     * @param item        The menu item resource selected by user
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
	        //user wants start reading story
	        case R.id.action_beginning:   
	            //save story locally and then read it from beginning
	            UUID localId = esLibrary.downloadStory(storyPicked.getId());
	        	startAtBeginning(localId);
	        	return true;
	        //user wants save story without reading immediately
	        case R.id.action_save_online_story:
	        	//save the story
	        	esLibrary.downloadStory(storyPicked.getId());
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
		
		// Jesse's Additions past this point

		// This function needs to be called on a specific story...
		// It shouldn't be an action bar button
		// This call will save the story from the server locally
		// It returns the UUID of the story saved
		// localId = esLibrary.downloadStory(id)
	}
	/**
	 * clears input boxes for new search
	 */
	private void clearInputBoxes() {
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_new_search), Toast.LENGTH_SHORT);
		toast.show();
		//clear the text inputs
		searchTitle.setText("");
		searchAuthor.setText("");
		searchDesc.setText("");
	}
	
	/**
	 * search for online stories based on search input parameters
	 */
	private void startSearchOnline(){
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_search_online_stories), Toast.LENGTH_SHORT);
		toast.show();		
		// Grab the search parameters
		String title = searchTitle.getText().toString();
		String author = searchAuthor.getText().toString();
		String desc = searchDesc.getText().toString();
		
		// Search for stories, 0 is just a default we leave there, it's needed
		//results = esLibrary.searchOnlineStories(title, author, desc, 0);
		//this is test so I don'thave to type input
		results = esLibrary.searchOnlineStories("w x y z", "a b c", "f", 0);
		
		//initialize adapter and update the view
		objStoryAdapter = new StoryInfoListAdapter(this, R.layout.story_info_list_item, results);
		lsvStories.setAdapter(objStoryAdapter);
		objStoryAdapter.notifyDataSetChanged();
	}
	
	/**
	 * get next 20 stories
	 */
	private void getNextTwenty(){
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_get_next_20), Toast.LENGTH_SHORT);
		toast.show();
		
		// Index to start new query at (based on size of storyInfoList)
		Integer index = results.size();
		ArrayList<StoryInfo> next20 = esLibrary.getStoryInfoList(index);
		
		// You now have the next 20 StoryInfo objects in an ArrayList
		//add them to list and update data set
		results.addAll(next20);
		objStoryAdapter.notifyDataSetChanged();		
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
	 * Starts reading story at beginning child activity
	 * 
	 * @param storyId    UUID of story selected by user
	 */
	private void startAtBeginning(UUID storyId) {
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_beginning), Toast.LENGTH_SHORT);
		toast.show();   
		//create the intent to launch read story activity
	    Intent intent = new Intent(this, ReadFragmentActivity.class);
	    intent.putExtra(getResources().getString(R.string.story_continue), true);
		intent.putExtra(getResources().getString(R.string.story_id), storyId);
        startActivity(intent);		
	}
	
	/*
	/**
	 * select random story and opens it for reading from beginning
	 */
	
	private void startRandomStory() {
		//feedback to user, echoing menu text
		Toast toast = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.action_random_story), Toast.LENGTH_SHORT);
		toast.show();
		
		// Jesse's additions below
		// A random story object. We can play with the returns later if you want
		//Story randomStory = esLibrary.getRandomOnlineStory();
		//now we need to save this story to local device, getting a new UUID for it
		
		//then we open the locally save story for local reading with the new UUID
		//startAtBeginning(newUuid);
	}	
}
