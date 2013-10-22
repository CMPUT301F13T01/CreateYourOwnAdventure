package cmput301.f13t01.createyourownadventure;

/*
 * Enum which lists all possible media types.
 */

public enum MediaType {
	TEXT, IMAGE, SOUND, VIDEO;

	@Override
	public String toString() {
		return "Media type: " + name();
	}
}