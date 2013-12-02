/*
The ImageInfo class is used to display and scale images in the UI.
 
     Copyright  �2013 Jesse Huard
    <Contact: jhuard@ualberta.ca>
    
    License GPLv3: GNU GPL Version 3
    <http://gnu.org/licenses/gpl.html>.
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cmput301.f13t01.editstory;

import java.io.IOException;
import java.io.Serializable;

import android.net.Uri;

/**
 * The ImageInfo class is used to display and scale images in the UI.
 * 
 * @author Jesse Huard
 * 
 */
public class ImageInfo implements Serializable {
	private Uri imageUri;
	private Integer scale;

	/**
	 * Empty Constructor
	 */
	public ImageInfo() {
	}

	/**
	 * Constructor to set image and scale
	 * 
	 * @param imageUri
	 *            the Uri of the image
	 * @param scale
	 *            the scale of the image
	 */
	public ImageInfo(Uri imageUri, Integer scale) {
		this.setImageUri(imageUri);
		this.setScale(scale);
	}

	/**
	 * Getter for the Image Uri
	 * 
	 * @return the Uri of the image
	 */
	public Uri getImageUri() {
		return imageUri;
	}

	/**
	 * The setter for the Image Uri
	 * 
	 * @param imageUri
	 *            the Uri of the image
	 */
	public void setImageUri(Uri imageUri) {
		this.imageUri = imageUri;
	}

	/**
	 * The getter for the scale of the image.
	 * 
	 * @return the scale of the image
	 */
	public Integer getScale() {
		return scale;
	}

	/**
	 * The setter for the scale of the image.
	 * 
	 * @param scale
	 *            the scale for the image
	 */
	public void setScale(Integer scale) {
		this.scale = scale;
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(imageUri.getPath());
		out.writeObject(scale);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		imageUri = Uri.parse((String) in.readObject());
		scale = (Integer) in.readObject();
	}
}
