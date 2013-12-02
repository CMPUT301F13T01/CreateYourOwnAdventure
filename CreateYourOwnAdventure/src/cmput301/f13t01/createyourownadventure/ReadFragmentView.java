/*
FragmentList Class for CreateYourOwnAdventure App.
The view for the ReadFragmentActivity. Constructs the required view
to display the media of the story fragment properly.
    
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
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * The view for the ReadFragmentActivity. Constructs the required fragment to
 * display the media of the story fragment properly. Uses the story manager to
 * access parts of the story for display.
 * 
 * @author Eddie Tai <eddie@ualberta.ca>
 */
public class ReadFragmentView extends Fragment {

	ReadStoryManager storyManager;
	Integer fragmentId;

	// get the storyManager on attachment to the activity
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		GlobalManager app = (GlobalManager) activity.getApplication();
		this.storyManager = app.getStoryManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the view
		View scrollable = inflater.inflate(R.layout.view_fragment, container,
				false);

		// Setup fragment's outer container-layout
		LinearLayout layout = (LinearLayout) scrollable
				.findViewById(R.id.view_fragment_linear);
		// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		// LinearLayout.LayoutParams.MATCH_PARENT,
		// LinearLayout.LayoutParams.WRAP_CONTENT);

		// fetch story fragment Id
		String resourceString = getResources().getString(
				R.string.destination_id);
		fragmentId = getArguments().getInt(resourceString);

		// shows all media in the fragment
		ArrayList<Media> mediaList = storyManager.getMediaList(fragmentId);

		StoryFragmentViewFactory.ConstructView(layout, mediaList, getActivity(), false);

		// shows the annotations for the fragment, if any
		ArrayList<Media> annotateList = storyManager
				.getAnnotationList(fragmentId);

		// create an annotation header, if there is an annotation
		if (annotateList.size() > 0) {

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);

			TextView text = new TextView(getActivity());
			text.setTextColor(Color.BLACK);
			text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			text.setText("\n\n Reader's Annotations:");
			layout.addView(text, params);	
		}
				
		StoryFragmentViewFactory.ConstructView(layout, annotateList, getActivity(), false);

		// from story level with fragment id, get the array list of choice
		// objects
		ArrayList<Choice> choices = storyManager.getChoices(fragmentId);

		// if there are choices, cycle through them and extract the flavour
		// texts
		if (choices != null) {
			ArrayList<String> flavourText = new ArrayList<String>();

			for (int i = 0; i < choices.size(); i++) {
				Choice currentChoice = choices.get(i);
				String s = currentChoice.getFlavourText();
				flavourText.add(s);
			}

			// use for loop to make series of buttons consisting of the
			// flavour texts along with a random choice button

			for (Integer i = 0; i < flavourText.size(); i++) {
				Button choiceButton = new Button(getActivity());

				choiceButton.setText(flavourText.get(i));
				choiceButton.setId(i + 1);
				choiceButton.setTextSize(12);
				layout.addView(choiceButton);

				// set each button's controller to use Activity's function
				choiceButton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						((ReadFragmentActivity) getActivity())
								.onFragmentListClick(v, fragmentId);
					}
				});
			}

			// add a random choice if there are at least 2 choices
			if (flavourText.size() > 1) {
				Button randomChoiceButton = new Button(getActivity());
				randomChoiceButton.setText("Pick a random choice!");
				randomChoiceButton.setId(flavourText.size() + 1);
				randomChoiceButton.setTextSize(12);
				layout.addView(randomChoiceButton);

				// set the button's controller to use Activity's function
				randomChoiceButton
						.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								((ReadFragmentActivity) getActivity())
										.onFragmentListClick(v, fragmentId);
							}
						});
			}

		}

		return scrollable;
	}
}
