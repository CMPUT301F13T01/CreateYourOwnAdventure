package cmput301.f13t01.createyourownadventure;

/**
 * The Media<T> interface is an interface which must be implemented by any
 * classes that will be used as media types inside a <code>Fragment</code>. The
 * interface takes a generic type argument which defines the type of content
 * stored inside the media type.
 * 
 * @author Jesse Huard
 * @version 1.0, 28/10/13
 */

public interface Media<T> {
	/**
	 * Returns the content of the media type.
	 * 
	 * @return the media type's content.
	 */
	public T getContent();

	/**
	 * Sets the content of the media type.
	 * 
	 * @param content
	 *            the content to be held by the media type.
	 */
	public void setContent(T content);

	/**
	 * Gets the <code>MediaInteractionManager</code> of the media type.
	 * 
	 * @return the media type's <code>MediaInteractionManager</code>.
	 */
	public MediaInteractionManager getManager();

	/**
	 * Sets the <code>MediaInteractionManager</code> of the media type.
	 * 
	 * @param manager
	 *            the <code>MediaInteractionManager</code> to be used with the
	 *            media type.
	 */
	public void setManager(MediaInteractionManager manager);
}
