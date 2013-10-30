package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class StoryListEntry implements Serializable {

	private Integer id;
	private Story story;
	
	public StoryListEntry() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the story
	 */
	public Story getStory() {
		return story;
	}

	/**
	 * @param story the story to set
	 */
	public void setStory(Story story) {
		this.story = story;
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		
	}
	private void readObject(java.io.ObjectInputStream in)
	    throws IOException, ClassNotFoundException {
		
	}
	private void readObjectNoData()
	    throws ObjectStreamException{
		
	}
	

}
