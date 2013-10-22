package cmput301.f13t01.createyourownadventure;

/* 
 * Generic base class for classes which hold story fragment media. Note that the class
 * is generic, but must specify what type of media it falls under.
 */

public class Media<T> {
	private T content;
	private MediaType type;
	private InteractionManager<T> manager;

	public Media(T content, MediaType type) {
		this.content = content;
		this.type = type;
		this.setManager(new InteractionManager<T>());
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public InteractionManager<T> getManager() {
		return manager;
	}

	public void setManager(InteractionManager<T> manager) {
		this.manager = manager;
	}

	public MediaType getType() {
		return type;
	}

}
