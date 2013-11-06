package cmput301.f13t01.createyourownadventure;

import android.app.Application;

/**
 * The GlobalManager class is a class used to hold global story access managers.
 * These managers can be used to load stories that are then accessed across
 * multiple activities without the need to pass the story data around from
 * activity to activity. This class extends the default Android Application to
 * provide global state persistence.
 * 
 * 
 * @author Jesse Huard
 * @version 1.0, 29/10/13
 * @see android.app.Application
 * 
 */
public class GlobalManager extends Application {
	/**
	 * The Application's ReadStoryManager.
	 * 
	 * @see #getReadManager()
	 * @see #setReadManager()
	 */
	private static ReadStoryManager readManager;

	/**
	 * The Application's LocalManager.
	 * 
	 * @see #getLocalManager()
	 * @see #setLocalManager()
	 */
	private static LocalManager localManager;

	/**
	 * Get the Application's LocalManager.
	 * 
	 * @return the Application's LocalManager.
	 */
	public LocalManager getLocalManager() {
		return localManager;
	}

	/**
	 * Get the Application's ReadStoryManager.
	 * 
	 * @return the Application's ReadStoryManager.
	 */
	public ReadStoryManager getStoryManager() {
		return readManager;
	}

	/**
	 * Set the ReadStoryManager for the story described in the local library by the ID
	 * argument.
	 * 
	 * @param storyId
	 *            the ID of the story to be managed.
	 */
	public void setStoryManager(UUID storyId) {
		Story story = localManager.getStory(storyId);
		readManager.setStory(story); // Do we want to just construct a new one?
	}
}
