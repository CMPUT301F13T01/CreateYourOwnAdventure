/*
ViewAnnotationActivity activity for CreateYourOwnAdventure.
This activity allows the user to view a particular
storyFragment's annotation.

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
import android.widget.LinearLayout;

/**
 * This activity allows the user to preview a particular storyFragment's
 * annotation.
 * 
 * @author jesse
 * 
 */

public class ViewAnnotationActivity extends Activity {

	@SuppressWarnings("rawtypes")
	private ArrayList<Media> annotation;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_annotation);

		Intent intent = getIntent();
		annotation = (ArrayList<Media>) intent
				.getSerializableExtra(getResources().getString(
						R.string.annotation));

		LinearLayout layout = (LinearLayout) findViewById(R.id.edit_annotation_linear);

		StoryFragmentViewFactory.ConstructView(layout, annotation, this, false);
	}

}