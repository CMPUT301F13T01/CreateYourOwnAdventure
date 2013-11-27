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

package cmput301.f13t01.createyourownadventure.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import cmput301.f13t01.createyourownadventure.LocalManager;
import cmput301.f13t01.createyourownadventure.MainActivity;
import cmput301.f13t01.createyourownadventure.R;
import cmput301.f13t01.createyourownadventure.Story;
import cmput301.f13t01.createyourownadventure.StoryInfo;
import cmput301.f13t01.createyourownadventure.StoryInfoListAdapter;


/*
 *  sets up random choice activity to be tested
 *    
 *  @author Gerald Manweiler
 *  @version 1.0 Nov 25 2013
 */
public class TestCreateYourOwnAdventure extends ActivityInstrumentationTestCase2<MainActivity>{
	private MainActivity mainActivity;
	// local manager object to handle local story saving
	private LocalManager objLibrary;
	//story list view object
	private ListView lsvStories = null;
	//story info array list object
	private ArrayList<StoryInfo> storyInfoList;
	// adapter for story info array list
	private StoryInfoListAdapter objStoryAdapter;


	/*
	 * import an activity for testing
	 */
	public TestCreateYourOwnAdventure(){
		super(MainActivity.class);
		
	}
	
	/*
	 * setup the activity for testing
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//grab the activity and local manager
		mainActivity = getActivity();
		objLibrary = new LocalManager(mainActivity);
		//create and add fake stories here
		for (int i = 0; i <5; i++){
			Story fakeStory = ErsatzStoryData.createErsatzStory();
			fakeStory.setTitle("Fake Story " + i);
			fakeStory.setAuthor("Gerald " + i);
			objLibrary.addStory(fakeStory);
		}
		//now get the story info list
		storyInfoList = objLibrary.getStoryInfoList();

	}
	
	/*
	 * now test boundary, edges and usual case
	 */
	public void testStartRandomStory(){			
		//get the story info list from manager and the size of list
		System.out.println("there are " + storyInfoList.size() + " stories in the list");
		assertTrue(storyInfoList.size() > 0);
		assertTrue(storyInfoList.size() == 5);
		assertFalse(storyInfoList.size() < 1);
		assertFalse(storyInfoList.size() > 6);
		
		//new random object and story list size
	 	Random r = new Random();
	 	int listSize = storyInfoList.size();
	 	ArrayList<Integer> randInts = new ArrayList<Integer>();
	 	//loop until we get all the random numbers from 0 to 4 into list
	 	while (randInts.size() != 5){
	 		randInts.add(Integer.valueOf(r.nextInt(listSize)));
	 	}
	 	assertTrue(randInts.size() == 5);		
	}
	
	/*
	 * teardown fake stories
	 */
	protected void tearDown() throws Exception {
		ArrayList<UUID> ids = new ArrayList<UUID>();
		//get the uuids 
		for (StoryInfo info : objLibrary.getStoryInfoList()){
			ids.add(info.getId());
		}
		//cycle through the uuids and remove stories
		for (UUID id : ids){
			objLibrary.removeStory(id);
		}
		//call superclass last so have references!
		super.tearDown();
	}
}
