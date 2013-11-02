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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * sets up and handles main screen ui
 * from the ma
 */
public class MainActivity extends Activity{
	//declare and null init story listview, its adapter and listeners
	private ListView lsvStories = null;
	private ArrayAdapter<String> objStoryAdapter = null;
	private OnItemClickListener objListListen = null;
	private OnItemLongClickListener objLongListListen = null;
	
	//interim array for testing	
	private String[] storyList = {"Author 1 Story 1", "Author 1 Story 2", "Author 2 Story 1", "Author 2 Story 2"};
	
	/**
	 * create screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//get last instance state and set view to main screen
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//set list view and array adapter
		lsvStories = (ListView) findViewById(R.id.main_activity_listview);
		objStoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, storyList);
		lsvStories.setAdapter(objStoryAdapter);
		//register view for the on long click context menu
		registerForContextMenu(lsvStories);
		//TODO register on click contextual menu for read story options		
		
		
		//set on click listener and long click listener
		lsvStories.setOnItemClickListener(objListListen);
		lsvStories.setOnItemLongClickListener(objLongListListen);
		
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
	 * create context menu for on long click of story item in story listview
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_editstory_menu, menu);
	}
	
	/**
	 * handle user selection of an on long click contextual menu item
	 */
	@Override
	public boolean onContextItemSelected (MenuItem item) {
		//get the menu item info
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
        //user wants to edit the story
        case R.id.action_edit_story:
        	//
            startEditStory();
            return true;
        //user wants to delete story
        case R.id.action_delete_story:
        	//
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
