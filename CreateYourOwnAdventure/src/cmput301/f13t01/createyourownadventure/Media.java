package cmput301.f13t01.createyourownadventure;

/* 
 * Generic abstract base class for classes which hold story fragment media. Child classes
 * must implement the getView() method.
 */

public abstract class Media<T> implements Viewable {
	private T content;
	private InteractionManager<T> manager;

	public Media(T content) {
		this.content = content;
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
}
