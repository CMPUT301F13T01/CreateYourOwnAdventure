package cmput301.f13t01.elasticsearch;

import java.util.ArrayList;
import java.util.UUID;

public class StoryResource {

	/*
	 *This will be posted online so that the story's resources
	 *can be accessed.
	 *
	 * NEED:
	 * 		UUID OF STORY
	 * 		ARRAYLIST<MEDIARESOURCE>
	 */
	
	private String id;
	private ArrayList<MediaResource> resources;
	
	public StoryResource(UUID id) {
		this.id = id.toString();
		this.resources = new ArrayList<MediaResource>();
	}
	
	public String getStoryId() {
		return this.id;
	}
	
	public ArrayList<MediaResource> getMediaResources() {
		return resources;
	}
	
	public void addMediaResource(MediaResource resource) {
		resources.add(resource);
		return;
	}
	
}
