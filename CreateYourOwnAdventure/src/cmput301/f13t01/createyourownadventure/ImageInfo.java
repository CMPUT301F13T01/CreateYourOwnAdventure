package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.Serializable;

import android.net.Uri;

public class ImageInfo implements Serializable {
	private Uri imageUri;
	private Integer scale;

	public ImageInfo() {
	}

	public ImageInfo(Uri imageUri, Integer scale) {
		this.setImageUri(imageUri);
		this.setScale(scale);
	}

	public Uri getImageUri() {
		return imageUri;
	}

	public void setImageUri(Uri imageUri) {
		this.imageUri = imageUri;
	}

	public Integer getScale() {
		return scale;
	}

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
