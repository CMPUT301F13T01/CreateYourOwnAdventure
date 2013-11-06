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

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * @author Eddie Tai <eddie@ualberta.ca>
 * 
 *         The view for the ReadFragmentActivity. Constructs the required view
 *         to display the media of the story fragment properly.
 * 
 */
public class ReadFragmentView extends Fragment {

	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		
		ScrollView sv = new ScrollView(getActivity());
		
		ReadStoryManager manager = new ReadStoryManager(UUID, getActivity());
		


		// fetch the fragment
		fragment = manager.getFragment(fragmentId);
		
		// cycle through the media list
		for (int i = 0; i < media_list.size(); i++) {
			@SuppressWarnings("rawtypes")
			Media media = media_list.get(i);

			// get media file's type and do class comparisons
			if (media.getClass().equals(Text.class)) {
				Text text = (Text) media;
				
				// get media content's SpannableString as s
				SpannableString s = text.getContent();
				view.setTextView(s);
			}

			// the rest are implemented later for iteration 3

			// else if type is image
			// if(media_type == Image.class.getClass())

			// else if type is sound
			// if(media_type == Audio.class.getClass())

			// else if type is video
			// if(media_type == Video.class.getClass())
		}

		// from story level with fragment id, get the array list of choice
		// objects
		ArrayList<Choice> choices = getChoices(fragmentId);

		// if there are choices, cycle through them and extract the flavour
		// texts
		if (choices != null) {
			ArrayList<String> flavourText = new ArrayList<String>();

			for (int i = 0; i < choices.size(); i++) {
				String s = choices.get(i).getFlavourText();
				flavourText.add(s);
			}

			// set the view of choices with flavour text
			view.setChoiceView(flavourText, this);
		}
		
		return sv;
	}

	/**
	 * Add a TextView with content of string s to the current view
	 * 
	 * @param s
	 *            SpannableString to be displayed by the TextView
	 * @param context
	 *            the context of the view
	 */
	public void setTextView(SpannableString s) {
		TextView tv = new TextView(getActivity() );
		tv.setText("s",BufferType.SPANNABLE);
		this.addView(tv);
	}

	/**
	 * Incomplete ImageView handler
	 */
	public void setImageView() {
		ImageView iv = new ImageView(getActivity());
		ll.addView(iv);
	}

	/**
	 * Shows the list of choices using their given flavour text and sets the
	 * manager for these choice options.
	 * 
	 * @param choice_text
	 *            Arraylist of strings indicating the flavour text
	 * @param onItemClickListener
	 *            controller for the views
	 */
	public void setChoiceView(ArrayList<String> choice_text,
			OnItemClickListener onItemClickListener) {

		// populate the list with choices' flavour text
		ListView lv = new ListView(getActivity());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.list_choices, choice_text);
		lv.setAdapter(adapter);

		// set the controller for these list items
		lv.setOnItemClickListener(onItemClickListener);
	}

}


