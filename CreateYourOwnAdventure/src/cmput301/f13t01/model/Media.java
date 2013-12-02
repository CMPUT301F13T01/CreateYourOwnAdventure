/*
 * The Media<T> interface is an interface which must be implemented by any
 * classes that will be used as media types inside a <code>Fragment</code>. The
 * interface takes a generic type argument which defines the type of content
 * stored inside the media type.
 * 
 * Copyright 2013 Gerald Manweiler Eddie Tai Jesse Chu Jesse Huard Reggie Miller
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cmput301.f13t01.model;

import cmput301.f13t01.readstory.MediaInteractionManager;

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
	 * Returns the media's MediaType.
	 * 
	 * @return the type of the media.
	 */
	public String getType();

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

	/**
	 * Returns the string resource of the media object
	 * 
	 * @return the resource of the media, as a string
	 */
	public String getResource();
}
