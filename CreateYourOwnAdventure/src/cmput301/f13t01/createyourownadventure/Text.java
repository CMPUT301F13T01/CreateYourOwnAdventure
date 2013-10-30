package cmput301.f13t01.createyourownadventure;

import android.text.SpannableString;

/*
 * Class for Text type Media. Uses a SpannableString so it can be formatted.
 */
public class Text implements Media<SpannableString> {
	private SpannableString content;
	private MediaInteractionManager manager;

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

}
