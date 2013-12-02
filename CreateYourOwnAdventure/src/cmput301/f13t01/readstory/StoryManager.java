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

package cmput301.f13t01.readstory;

import java.util.ArrayList;

import cmput301.f13t01.model.Choice;
import cmput301.f13t01.model.Media;
import cmput301.f13t01.model.Story;
import cmput301.f13t01.model.StoryFragment;
import cmput301.f13t01.model.StoryFragmentInfo;

/**
 * ReadStoryManager is the connection between various activities and a story.
 * The GlobalManager sets the story for the manager, and the manager enables
 * various interactions with the story model, such as getting and setting
 * attributes of a story and its fragments.
 * 
 * @author Eddie Tai <eddie@ualberta.ca>
 */
public class StoryManager {

	// declaration of variables
	private Story story;

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

	/**
	 * Getter for the currently loaded story
	 * 
	 * @return Returns the story that is loaded
	 */
	public Story getStory() {
		return story;
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
		return story.getAuthor();
	}

	/**
	 * Getter for the description of the story.
	 * 
	 * @return the description of the story
	 */
	public String getDescription() {
		return story.getDescription();
	}

	/**
	 * Getter for the first fragment of the story.
	 * 
	 * @return the ID of the first fragment of the story
	 */
	public Integer getFirstPageId() {
		return story.getFirstPage();
	}

	public StoryFragment getFirstPage() {
		return story.getFragment(getFirstPageId());
	}

	/**
	 * Setter for the title of the story.
	 * 
	 * @param title
	 *            the string to set the title to
	 * @return true if successful, false otherwise
	 */
	public boolean setTitle(String title) {
		return story.setTitle(title);
	}

	/**
	 * Setter for the author of the story.
	 * 
	 * @param author
	 *            the string to set the author to
	 * @return true if successful, false otherwise
	 */
	public boolean setAuthor(String author) {
		return story.setAuthor(author);
	}

	/**
	 * Setter for the description of the story.
	 * 
	 * @param description
	 *            the string to set the description to
	 * @return true if successful, false otherwise
	 */
	public boolean setDescription(String description) {
		return story.setDescription(description);
	}

	/**
	 * Setter for the first page of the story.
	 * 
	 * @param id
	 *            the id of the fragment to set as first page
	 * @return true if successful, false otherwise
	 */
	public boolean setFirstPage(Integer id) {
		return story.setFirstPage(id);
	}

	/* Functions that deal with the StoryFragmentList */
	/**
	 * Fetches a requested StoryFragment by ID from FragmentList
	 * 
	 * @param id
	 *            the id of the StoryFragment to return
	 * @return the requested StoryFragment, null if it doesn't exist
	 */
	public StoryFragment getFragment(Integer id) {
		return story.getFragment(id);
	}

	/**
	 * Returns the ID of a given StoryFragment.
	 * 
	 * @param fragment
	 *            the StoryFragment to get the ID of
	 * @return the ID of the StoryFragment, null if not found
	 */
	public Integer getFragmentId(StoryFragment fragment) {
		return story.getFragmentId(fragment);
	}

	/**
	 * Returns a StoryFragmentInfo object for the given ID. Used to display
	 * StoryFragmentInfo in lists.
	 * 
	 * @param id
	 *            the ID of the fragment to get the info of
	 * @return a StoryFragmentInfo object for that ID
	 */
	public StoryFragmentInfo getFragmentInfo(Integer id) {
		return story.getFragmentInfo(id);
	}

	/**
	 * Returns an ArrayList of StoryFragmentInfo objects for all StoryFragment
	 * objects.
	 * 
	 * @return an ArrayList of all StoryFragmentInfo
	 */
	public ArrayList<StoryFragmentInfo> getFragmentInfoList() {
		return story.getFragmentInfoList();
	}

	/**
	 * Places a new fragment into the FragmentList. A new ID is automatically
	 * generated for each fragment.
	 * 
	 * @param newFragment
	 *            The StoryFragment to add to StoryFragmentList
	 * @return the ID given to the added StoryFragment
	 */
	public Integer addFragment(StoryFragment newFragment) {
		return story.addFragment(newFragment);
	}

	/**
	 * Removes a requested Fragment from the FragmentList. Returns boolean based
	 * on success/failure.
	 * 
	 * @param id
	 *            ID of the Fragment to remove
	 * @return true if successful, false otherwise
	 */
	public boolean removeFragment(Integer id) {
		return story.removeFragment(id);
	}

	/**
	 * Updates the Fragment associated with a given ID. Fragment ID must have
	 * been in use to be valid
	 * 
	 * @param id
	 *            ID of the Fragment in to updated
	 * @param fragment
	 *            Fragment to update to
	 * @returns true is successful, false otherwise
	 */
	public boolean updateFragment(Integer id, StoryFragment fragment) {
		return story.updateFragment(id, fragment);
	}

	/* Functions that deal with the ChoiceMap and Choices */
	/**
	 * Adds a new given Choice to a StoryFragment (by ID)
	 * 
	 * @param fragmentId
	 *            ID of StoryFragment to add Choice to
	 * @param choice
	 *            Choice object to add to the ChoiceMap
	 */
	public void addChoice(int fragmentId, Choice choice) {
		story.addChoice(fragmentId, choice);
	}

	/**
	 * Removes a choice from the ChoiceMap, using a given StoryFragment ID and
	 * index
	 * 
	 * @param fragmentId
	 *            ID of StoryFragment where choice is located
	 * @param index
	 *            Index of the choice to remove
	 * @return Returns true if choice existed, otherwise returns false
	 */
	public boolean deleteChoice(int fragmentId, int index) {
		return story.deleteChoice(fragmentId, index);
	}

	/**
	 * Updates a Choice for a given StoryFragment with a new given Choice, using
	 * its index
	 * 
	 * @param fragmentId
	 *            ID of StoryFragment where choice is located
	 * @param index
	 *            Index of choice to change
	 * @param choice
	 *            New choice to put in there
	 * @return Returns true if choice already existed, false otherwise
	 */
	public boolean updateChoice(int fragmentId, int index, Choice choice) {
		return story.updateChoice(fragmentId, index, choice);
	}

	/**
	 * Used to remove all references to a given StoryFragment ID. Should be
	 * called when a StoryFragment is deleted.
	 * 
	 * @param fragmentId
	 *            ID of StoryFragment to remove references to
	 */
	public void cleanFragmentReferences(int fragmentId) {
		story.cleanFragmentReferences(fragmentId);
	}

	/**
	 * Returns an ArrayList of all Choices for a given StoryFragment ID Entries
	 * are sorted by destination StoryFragment ID
	 * 
	 * @param fragmentId
	 *            ID of StoryFragment to get the choices for
	 * @return ArrayList of Choice objects for the given StoryFragment ID
	 */
	public ArrayList<Choice> getChoices(int fragmentId) {
		return story.getChoices(fragmentId);
	}

	/* Functions that deal with the History */

	/**
	 * Returns the ID of the last-viewed Story Fragment. If History is empty,
	 * returns null.
	 * 
	 * @return Integer ID of last-viewed fragment
	 */
	public Integer getMostRecent() {
		return story.getMostRecent();
	}

	/**
	 * Removes the most recent StoryFragment ID from the stack, and returns the
	 * previous StoryFragment ID. When there is no previous ID to return,
	 * returns null.
	 * 
	 * @return StoryFragmentID of previous StoryFragment
	 */
	public Integer goBack() {
		return story.goBack();
	}

	/**
	 * Pushes a StoryFragment ID onto the History Stack. Should be called for
	 * every new StoryFragment viewed.
	 * 
	 * @param fragment_id
	 *            ID of StoryFragment to be pushed to stack
	 */
	public void pushToStack(Integer fragmentId) {
		story.pushToStack(fragmentId);
	}

	/**
	 * Clears the History stack. Used when a user decides to start a new reading
	 * session.
	 */
	public void clearHistory() {
		story.clearHistory();
	}

	/**
	 * Given a fragment Id for a story, fetch the Media List for a fragment
	 * 
	 * @return an ArrayList of Media content for the fragment
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<Media> getMediaList(Integer fragmentId) {
		StoryFragment fragment = getFragment(fragmentId);
		return fragment.getContentList();
	}

	/**
	 * Given a fragment Id for a story, fetch the Annotation List for a fragment
	 * 
	 * @return an ArrayList of Media content for the fragment
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<Media> getAnnotationList(Integer fragmentId) {
		StoryFragment fragment = getFragment(fragmentId);
		return fragment.getAnnotationList();
	}

	/**
	 * Given an annotation list, set the fragment's annotation list to be the
	 * one given.
	 * 
	 * @param fragmentId
	 *            Id of the Fragment
	 * @param newAnnotationList
	 * @return true if successful, false if it fails
	 */
	public boolean setAnnotation(Integer fragmentId,
			ArrayList<Media> newAnnotationList) {
		StoryFragment fragment = getFragment(fragmentId);
		return fragment.setAnnotation(newAnnotationList);
	}

}
