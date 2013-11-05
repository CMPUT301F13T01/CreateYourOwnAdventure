/*
FragmentList Class for CreateYourOwnAdventure App.
ReadStoryManager is the manager that allows interaction between the user
and the view. It dictates what content is to be displayed by the view. It
keeps track of the history stack as well when read navigates to various
other story fragments.
    
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
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Eddie Tai <eddie@ualberta.ca>
 * 
 *         ReadStoryManager is the manager that allows interaction between the
 *         user and the view. It dictates what content is to be displayed by the
 *         view. It keeps track of the history stack as well when read navigates
 *         to various other story fragments.
 * 
 */
public class ReadStoryManager implements OnItemClickListener {

	// declaration of variables
	ReadFragmentView view;
	StoryFragment fragment;
	Integer fragmentId;
	Context context;
	Story story;
	Integer storyId;
	ArrayList<Choice> choices;

	public ReadStoryManager(final Integer storyId, final Integer fragmentId,
			final ReadFragmentView view, final ReadFragmentActivity context) {

		// setting of variables
		this.view = view;
		this.context = context;
		this.storyId = storyId;
		this.fragmentId = fragmentId;

		// set the story using storyId through global manager
		GlobalManager globalmanager = (GlobalManager) ((Activity) context)
				.getApplication();
		globalmanager.setStoryManager(storyId);

		// fetch the fragment from the story level
		fragment = story.getFragment(fragmentId);

		// get media_list
		ArrayList<Media> media_list = fragment.getContentList();

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

	}

	/**
	 * Go to the beginning (first page) of a story Apprehend the current page to
	 * the history stack History is cleared.
	 */
	public void toBeginning() {

		// get the fragment id of the story's first page
		Integer destinationId = getFirstPageId();
		clearHistory();

		// read the next story fragment
		readNextFragment(storyId, destinationId);
	}

	/**
	 * Go back to the previous page dictated by the history stack Remove the
	 * current page from the history stack
	 */
	public void toPrevious() {

		// go back to previous, adjusting history stack properly
		Integer destinationId = getMostRecent();

		if (destinationId != null) {
			// read the next story fragment if there is a previous fragment in
			// the history stack
			readNextFragment(storyId, destinationId);
		} else {
			// go back to the previous level
			((Activity) context).finish();
		}

	}

	/**
	 * On selection of a choice in view, direct the user to the next story
	 * fragment according to the choice map.
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		// use position to refer to the choice item from choice map
		Choice choice = choices.get(position);

		// need getter of this?
		Integer destinationId = choice.getDestinationId();

		// add to the history stack
		pushToStack(destinationId);

		// read the next story fragment
		readNextFragment(storyId, destinationId);

	}

	/**
	 * A function that starts a new activity
	 * 
	 * @param storyId
	 *            the Id of the story object that the user is reading at the
	 *            moment
	 * @param fragmentId
	 *            the Id of the story fragment that the user is to read next
	 */
	public void readNextFragment(Integer storyId, Integer fragmentId) {
		Intent intent = new Intent(context, ReadFragmentActivity.class);
		intent.putExtra("storyId", storyId);
		intent.putExtra("fragmentId", fragmentId);
		((Activity) context).startActivity(intent);
	}
	
	/**
	 * Used by the global manager to inform the ReadStoryManager of what story
	 * is to be read
	 * 
	 * @param story
	 *            the story that is to be read
	 */
	public void setStory(Story story) {
		this.story = story;
	}
	
	/* Functions that deal with Story attributes */
    /**
     * Getter for story title.
     *
     * @return the title of the story
     */
    public String getTitle() {
            return story.getTitle();
    }
    
    /**
     * Getter for story author.
     *
     * @return the author of the story
     */
    public String getAuthor() {
            return this.story.getAuthor();
    }
    
    /**
     * Getter for the description of the story.
     *
     * @return the description of the story
     */
    public String getDescription() {
            return this.story.getDescription();
    }
    
    /**
     * Getter for the first fragment of the story.
     *
     * @return the ID of the first fragment of the story
     */
    public Integer getFirstPageId() {
            return this.story.getFirstPage();
    }
    
    public StoryFragment getFirstPage() {
    	return story.getFragment(getFirstPageId());
    }
    
    /**
     * Setter for the title of the story.
     *
     * @param title the string to set the title to
     * @return true if successful, false otherwise
     */
    public boolean setTitle(String title) {
            return this.story.setTitle(title);
    }
    
    /**
     * Setter for the author of the story.
     *
     * @param author the string to set the author to
     * @return true if successful, false otherwise
     */
    public boolean setAuthor(String author) {
            return this.story.setAuthor(author);
    }
    
    /**
     * Setter for the description of the story.
     *
     * @param description the string to set the description to
     * @return true if successful, false otherwise
     */
    public boolean setDescription(String description) {
            return this.story.setDescription(description);
    }
    
    /**
     * Setter for the first page of the story.
     *
     * @param id the id of the fragment to set as first page
     * @return true if successful, false otherwise
     */
    public boolean setFirstPage(Integer id) {
            return this.story.setFirstPage(id);
    }

    
    /* Functions that deal with the StoryFragmentList */
    /**
     * Fetches a requested StoryFragment by ID from FragmentList
     *
     * @param id the id of the StoryFragment to return
     * @return the requested StoryFragment, null if it doesn't exist
     */
    public StoryFragment getFragment(Integer id) {
            return this.story.getFragment(id);
    }
    
    /**
     * Returns the ID of a given StoryFragment.
     *
     * @param fragment the StoryFragment to get the ID of
     * @return the ID of the StoryFragment, null if not found
     */
    public Integer getFragmentId(StoryFragment fragment) {
            return this.story.getFragmentId(fragment);
    }
    
    /**
     * Returns a StoryFragmentInfo object for the given ID.
     * Used to display StoryFragmentInfo in lists.
     *
     * @param id the ID of the fragment to get the info of
     * @return a StoryFragmentInfo object for that ID
     */
    public StoryFragmentInfo getFragmentInfo(Integer id) {
            return this.story.getFragmentInfo(id);
    }
    
	/**
	 * Returns an ArrayList of StoryFragmentInfo objects for
	 * all StoryFragment objects.
	 * 
	 * @return an ArrayList of all StoryFragmentInfo
	 */
    public ArrayList<StoryFragmentInfo> getFragmentInfoList() {
    	return this.story.getFragmentInfoList();
    }

	/**
	 * Places a new fragment into the FragmentList.
	 * A new ID is automatically generated for each fragment.
	 * 
	 * @param newFragment The StoryFragment to add to StoryFragmentList
	 * @return the ID given to the added StoryFragment
	 */
    public Integer addFragment(StoryFragment newFragment) {
    	return this.story.addFragment(newFragment);
    }

	/**
	 * Removes a requested Fragment from the FragmentList.
	 * Returns boolean based on success/failure.
	 * 
	 * @param id ID of the Fragment to remove
	 * @return true if successful, false otherwise
	 */
    public boolean removeFragment(Integer id) {
    	return this.story.removeFragment(id);
    }
    
	/**
	 * Updates the Fragment associated with a given ID.
	 * Fragment ID must have been in use to be valid
	 * 
	 * @param id ID of the Fragment in to updated
	 * @param fragment Fragment to update to
	 * @returns true is successful, false otherwise
	 */
	public boolean updateFragment(Integer id, StoryFragment fragment) {
		return this.story.updateFragment(id, fragment);
	}


    /* Functions that deal with the ChoiceMap */
	/**
	 * Adds a new given Choice to a StoryFragment (by ID)
	 * 
	 * @param fragmentId ID of StoryFragment to add Choice to
	 * @param choice Choice object to add to the ChoiceMap
	 */
	public void addChoice(int fragmentId, Choice choice) {
		this.story.addChoice(fragmentId, choice);
	}
	
	/**
	 * Removes a choice from the ChoiceMap, using a given
	 * StoryFragment ID and index
	 * 
	 * @param fragmentId ID of StoryFragment where choice is located
	 * @param index Index of the choice to remove
	 * @return Returns true if choice existed, otherwise returns false
	 */
	public boolean deleteChoice(int fragmentId, int index) {
		return this.story.deleteChoice(fragmentId, index);
	}
	
	/**
	 * Updates a Choice for a given StoryFragment with a new given
	 * Choice, using its index
	 * 
	 * @param fragmentId ID of StoryFragment where choice is located
	 * @param index Index of choice to change
	 * @param choice New choice to put in there
	 * @return Returns true if choice already existed, false otherwise
	 */
	public boolean updateChoice(int fragmentId, int index, Choice choice) {
		return this.story.updateChoice(fragmentId, index, choice);
	}
	
	/**
	 * Used to remove all references to a given StoryFragment ID.
	 * Should be called when a StoryFragment is deleted.
	 * 
	 * @param fragmentId   ID of StoryFragment to remove references to
	 */
	public void cleanFragmentReferences(int fragmentId) {
		this.story.cleanFragmentReferences(fragmentId);
	}
	
	/**
	 * Returns an ArrayList of all Choices for a given StoryFragment ID
	 * Entries are sorted by destination StoryFragment ID
	 * 
	 * @param fragmentId ID of StoryFragment to get the choices for
	 * @return ArrayList of Choice objects for the given StoryFragment ID
	 */
	public ArrayList<Choice> getChoices(int fragmentId) {
		return this.story.getChoices(fragmentId);
	}
    
	
    /* Functions that deal with the History */
	/**
	 * Returns the ID of the last-viewed Story Fragment.
	 * If History is empty, returns null.
	 * 
	 * @return Integer ID of last-viewed fragment
	 */
	public Integer getMostRecent() {
		return this.story.getMostRecent();
	}
	
	/**
	 * Removes the most recent StoryFragment ID from the stack,
	 * and returns the previous StoryFragment ID.
	 * When there is no previous ID to return, returns null.
	 * 
	 * @return StoryFragmentID of previous StoryFragment
	 */
	public Integer goBack() {
		return this.story.goBack();
	}
	
	/**
	 * Pushes a StoryFragment ID onto the History Stack.
	 * Should be called for every new StoryFragment viewed.
	 * 
	 * @param fragment_id ID of StoryFragment to be pushed to stack
	 */
	public void pushToStack(Integer fragmentId) {
		this.story.pushToStack(fragmentId);
	}
    
	/**
	 * Clears the History stack.
	 * Used when a user decides to start a new reading session.
	 */
	public void clearHistory() {
		this.story.clearHistory();
	}

}
