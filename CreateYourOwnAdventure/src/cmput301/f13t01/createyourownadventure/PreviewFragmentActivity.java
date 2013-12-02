/*
PreviewFragmentActivity activity for CreateYourOwnAdventure.
This activity allows the user to preview a particular
storyFragment's content.

     Copyright  �2013 Jesse Huard
    <Contact: jhuard@ualberta.ca>
    
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

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;

/**
 * This activity allows the user to preview a particular storyFragment's
 * content.
 * 
 * @author jesse
 * 
 */

public class PreviewFragmentActivity extends Activity {

	private StoryFragment storyFragment;

	@SuppressWarnings("rawtypes")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview_fragment);

		Intent intent = getIntent();
		storyFragment = (StoryFragment) intent
				.getSerializableExtra(getResources().getString(
						R.string.story_fragment));

		ArrayList<Media> content = storyFragment.getContentList();

		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_fragment_linear);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		StoryFragmentViewFactory.ConstructView(layout, content, this, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
