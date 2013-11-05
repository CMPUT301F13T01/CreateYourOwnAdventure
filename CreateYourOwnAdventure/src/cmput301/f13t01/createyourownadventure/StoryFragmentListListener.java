package cmput301.f13t01.createyourownadventure;

/**
 * The StoryFragmentListListener interface is an interface that must be
 * implemented by any activity using a StoryFragmentListFragment. This interface
 * is to ensure that the activity implements the onStoryFragmentSelected callback
 * function to correctly handle item selection in the ListFragment.
 * 
 * @author Jesse Huard
 * @version 1.0, 30/10/13
 * 
 */

public interface StoryFragmentListListener {
	void onStoryFragmentSelected(int fragmentId);
}
