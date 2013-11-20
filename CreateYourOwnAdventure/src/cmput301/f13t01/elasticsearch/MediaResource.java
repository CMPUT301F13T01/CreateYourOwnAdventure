package cmput301.f13t01.elasticsearch;

import cmput301.f13t01.createyourownadventure.Media;
import cmput301.f13t01.createyourownadventure.MediaType;

public class MediaResource {

	/*
	 * This will be an object that will associate a given
	 * media object with its uri
	 * 
	 * NEED:
	 * 		STRING: THIS IS THE URI OF THE MEDIA OBJECT (A UUID)
	 * 		MediaType: This will be the type of media
	 */
	
	private String identifier;
	private MediaType type;
	
	public MediaResource(Media media) {
		//this.identifier = media.getContent();
		this.type = media.getType();
	}
	
}
