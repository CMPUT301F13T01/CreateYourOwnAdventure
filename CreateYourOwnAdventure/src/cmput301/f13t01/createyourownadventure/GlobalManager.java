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
	 * The Application's LibraryManager.
	 * 
	 * @see #getLibraryManager()
	 * @see #setLibraryManager()
	 */
	private static Library libraryManager;

	/**
	 * Get the Application's LibraryManager.
	 * 
	 * @return the Application's LibraryManager.
	 */
	public Library getLibraryManager() {
		return libraryManager;
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
	 * Set the ReadStoryManager for the story described in the library by the ID
	 * argument.
	 * 
	 * @param storyId
	 *            the ID of the story to be managed.
	 */
	public void setStoryManager(int storyId) {
		Story story = libraryManager.getStory(storyId);
		readManager.setStory(story); // Do we want to just construct a new one?
	}
}
