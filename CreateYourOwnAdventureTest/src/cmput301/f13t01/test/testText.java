package cmput301.f13t01.test;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import android.text.SpannableString;
import cmput301.f13t01.model.Text;

public class testText extends TestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testSetContent() {
		Text text = new Text();
		
		SpannableString content = new SpannableString("newtextcontent");
		
		text.setContent(content);
		
		SpannableString resourceString = text.getContent();
		
		assertTrue(resourceString.equals(content));
	}

}
