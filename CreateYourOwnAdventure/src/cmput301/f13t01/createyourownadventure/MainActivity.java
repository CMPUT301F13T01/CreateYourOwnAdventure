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

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

/**
 *  @author Gerald Manweiler
 *  sets up and handles main screen ui that allows user to 
 *  display stories, select a story to read from beginning, select a story and read 
 *  from last history, select a store to edit a story, select a story to delete,
 *  browse for online stories, and create a new story
 */
public class MainActivity extends Activity{
	//library manager to handle stories
	private Library objLibrary;
	//declare and null init story list view
	private ListView lsvStories = null;
	//story info object and list adapter for it
	private ArrayList<StoryInfo> storyInfoList;
	private StoryInfoListAdapter objStoryAdapter;	
	
	/**
	 * create screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//get last instance state and set view to main screen
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//set list view and register view for on long click contextual menu
		lsvStories = (ListView) findViewById(R.id.main_activity_listview);
		registerForContextMenu(lsvStories);		
		
		
		//instantiate the library manager
		objLibrary = new Library(this.getApplicationContext());
		//TODO JUNIT for this
		//create and add fake stories to library
		for (int i = 1; i < 5; i++) {
			//create fake story 
			Story objStory = new Story();
			String strCount = String.valueOf(i);
			//give fake story an author, title and description
			objStory.setAuthor("Author " + strCount);
			objStory.setTitle("Fake Story" + strCount);
			objStory.setDescription("fake story");
			//add the story
			objLibrary.addStory(objStory);
		}
		
		
	}
	
	/**
	 * resume activity
	 */
	protected void onResume() {
		super.onResume();

		//empty story info list for interim trial
		//storyInfoList = new ArrayList<StoryInfo>();
		
		//get the story info list from Library manager for the story list adapter
		storyInfoList = objLibrary.getStoryInfoList();
		
		//initialize adapter and update the view
		objStoryAdapter = new StoryInfoListAdapter(this, R.layout.story_info_list_item, storyInfoList);
		lsvStories.setAdapter(objStoryAdapter);
		objStoryAdapter.notifyDataSetChanged();
	}
	
	
	/**
	 * places browse online and create new story icons in action bar
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
	 * create contextual menu on long click of story item in story listview
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		//inflate menu specified in xml resource
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_story_menu, menu);
	}
	
	/**
	 * handle user selection of an on long click contextual menu item
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean onContextItemSelected (MenuItem item) {
		//get the menu item info
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
        //user wants start reading at beginning of story
        case R.id.action_beginning:
        	startBeginingStory();
        	return true;
        //user wants continue reading from last page of history
        case R.id.action_continue:
        	startContinueStory();
        	return true;
        //user wants to edit the story
        case R.id.action_edit_story:
            startEditStory();
            return true;
        //user wants to delete story
        case R.id.action_delete_story:
            startDeleteStory();
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
	
	/**
	 * starts edit story child activity
	 */
	private void startEditStory() {
		//quick and dirty test
		System.out.println("You selected Edit Story");
	}

	/**
	 * starts browse online story child activity
	 */
	private void startDeleteStory() {
		//quick and dirty test
		System.out.println("You selected Delete Story");
	}
	/**
	 * starts read from last history child activity
	 */
	private void startContinueStory() {
		//quick and dirty test
		System.out.println("You selected Continue Story");
	}

	/**
	 * starts reading story at beginning child activity
	 */
	private void startBeginingStory() {
		//quick and dirty test
		System.out.println("You selected Start at Beginning");
	}
	
	
}
