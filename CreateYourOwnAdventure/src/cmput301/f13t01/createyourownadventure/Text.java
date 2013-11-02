package cmput301.f13t01.createyourownadventure;

import java.io.IOException;
import java.io.Serializable;

import android.text.SpannableString;

/*
 * Class for Text type Media. Uses a SpannableString so it can be formatted.
 */
@SuppressWarnings("serial")
public class Text implements Media<SpannableString>, Serializable {
	private SpannableString content;
	private MediaInteractionManager manager;
	
	public Text(SpannableString content) {
		this.content = content;
	}

	@Override
	public SpannableString getContent() {
		return this.content;
	}

	@Override
	public void setContent(SpannableString content) {
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
		content = (SpannableString) in.readObject();
	}

}
