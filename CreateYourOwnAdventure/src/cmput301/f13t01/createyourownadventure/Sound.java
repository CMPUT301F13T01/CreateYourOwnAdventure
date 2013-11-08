package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.Serializable;

/**
 * Class for Sound type Media. Uses a String which refers to the resource name.
 */
public class Sound implements Media<String>, Serializable {
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
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(content);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		content = (String) in.readObject();
	}
}
