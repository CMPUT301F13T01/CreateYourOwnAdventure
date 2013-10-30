package cmput301.f13t01.createyourownadventure;

/*
 * Class for Video type Media. Uses a String which refers to the resource name.
 */
public class Video implements Media<String> {
	private String content;
	private MediaInteractionManager manager;

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public MediaInteractionManager getManager() {
		return this.manager;
	}

	@Override
	public void setManager(MediaInteractionManager manager) {
		this.manager = manager;
	}
	
	public String toString() {
		return this.content.toString();
	}
}