package cmput301.f13t01.createyourownadventure;

/* 
 * Interface for classes which hold story fragment media.
 */

public interface Media<T> {

	public T getContent();
	public void setContent(T content);

	public MediaInteractionManager getManager();
	public void setManager(MediaInteractionManager manager);
}
