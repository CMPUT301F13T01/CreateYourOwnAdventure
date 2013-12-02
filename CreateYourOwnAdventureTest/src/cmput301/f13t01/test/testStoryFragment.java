package cmput301.f13t01.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import cmput301.f13t01.model.Image;
import cmput301.f13t01.model.Media;
import cmput301.f13t01.model.StoryFragment;

public class testStoryFragment extends TestCase {
	
	StoryFragment fragment;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.fragment = new StoryFragment();
	}
	
	@Test
	public void testTitle() {
		this.fragment.setTitle("Test Title");
		String checkTitle = this.fragment.getTitle();
		assertTrue(checkTitle == "Test Title");
	}
	
	@Test
	public void testDescription() {
		this.fragment.setDescription("Test Description");
		String checkDescription = this.fragment.getDescription();
		assertTrue(checkDescription == "Test Description");
	}
	
	@Test
	public void testContent() {
		Image content = new Image();
		content.setContent("Test Content");
		this.fragment.addContent(content);
		ArrayList<Media> contentList = this.fragment.getContentList();
		assertTrue(contentList.contains(content));
		this.fragment.removeContent(content);
		ArrayList<Media> newContentList = this.fragment.getContentList();
		assertFalse(newContentList.contains(content));
	}
	
	@Test
	public void testAnnotation() {
		Image annotation = new Image();
		annotation.setContent("Test Content");
		this.fragment.addAnnotation(annotation);
		ArrayList<Media> annotationList = this.fragment.getAnnotationList();
		assertTrue(annotationList.contains(annotation));
		this.fragment.removeAnnotation(annotation);
		ArrayList<Media> newAnnotationList = this.fragment.getAnnotationList();
		assertFalse(newAnnotationList.contains(annotation));
	}
}
