package cmput301.f13t01.createyourownadventure;

import java.io.File;
import java.util.UUID;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import cmput301.f13t01.elasticsearch.ESClient;
import cmput301.f13t01.elasticsearch.ESManager;

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
	
	private static ESManager esManager;
	
	/**
	 * The Application's Elastic Search Client.
	 */
	private static ESClient esClient;

	/**
	 * Application context for the Global Manager.
	 */
	private static Context context;

	/**
	 * Temporary directory used by the application for storing in transit
	 * images.
	 */
	private static File tempDirectory;

	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		readManager = new ReadStoryManager();
		localManager = new LocalManager(context);
		esClient = new ESClient();
		esManager = new ESManager(context);
		String tempFolderPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/tmp";
		tempDirectory = new File(tempFolderPath);
		if (!tempDirectory.exists()) {
			tempDirectory.mkdir();
		}
	}
	
	/**
	 * Get the application's temporary directory.
	 * @return the application's temporary directory.
	 */
	public static File getTempDirectory() {
		if (!tempDirectory.exists()) {
			tempDirectory.mkdir();
		}
		return tempDirectory;

	}

	public static Context getAppContext() {
		return context;
	}

	/**
	 * Get the Application's LocalManager.
	 * 
	 * @return the Application's LocalManager.
	 */
	public static LocalManager getLocalManager() {
		return localManager;
	}

	/**
	 * Get the Application's ReadStoryManager.
	 * 
	 * @return the Application's ReadStoryManager.
	 */
	public static ReadStoryManager getStoryManager() {
		return readManager;
	}

	/**
	 * Get the Application's Elastic Search Manager.
	 * 
	 * @return the Application's LocalManager.
	 */
	public static ESManager getESManager(){
		return esManager;
	}
	
	/**
	 * Get the Application's Elastic Search client.
	 * 
	 * @return the Application's Elastic Search client.
	 */
	public static ESClient getESClient() {
		return esClient;
	}

	/**
	 * Set the ReadStoryManager for the story described in the local library by
	 * the ID argument.
	 * 
	 * @param storyId
	 *            the ID of the story to be managed.
	 */
	public void setStoryManager(UUID storyId) {
		Story story = localManager.getStory(storyId);
		readManager.setStory(story); // Do we want to just construct a new one?
	}

	/**
	 * Saves a newly-created story and sets it as the loaded story in the
	 * ReadStoryManager
	 * 
	 * @return storyId the ID of the newly created story.
	 */
	public UUID createAndSetStory() {
		UUID storyId = localManager.addStory(new Story());
		readManager.setStory(localManager.getStory(storyId));

		return storyId;
	}

	/**
	 * Saves an update to a preexisting story
	 * 
	 * @param storyId
	 *            ID of the story to update
	 */
	public void saveStory(UUID storyId) {
		Story story = readManager.getStory();
		localManager.saveStory(storyId, story);
	}

	public void setStoryManager(Story story) {
		readManager.setStory(story);
	}
}
