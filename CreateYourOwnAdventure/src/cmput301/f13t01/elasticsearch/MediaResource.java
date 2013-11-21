package cmput301.f13t01.elasticsearch;

import cmput301.f13t01.createyourownadventure.Media;
import cmput301.f13t01.createyourownadventure.MediaType;

public class MediaResource {
	
	private String identifier;
	private MediaType type;
	
	public MediaResource(Media media) {
		this.identifier = media.getContent();
		this.type = media.getType();
	}
	
}
