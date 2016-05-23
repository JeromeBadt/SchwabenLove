package de.hdm.grouptwo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("resources/arrow-left.png")
	@ImageOptions(height = 16, width = 16)
	ImageResource arrowLeft();

	@Source("resources/arrow-right.png")
	@ImageOptions(height = 16, width = 16)
	ImageResource arrowRight();
}
