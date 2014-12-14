package com.holmes.model;

import org.bytedeco.javacpp.opencv_core.IplImage;

import com.holmes.util.ImageMatchInterface;

/*
 * this is the default object of match, with all informations of our data
 */

public class DefaultObjectMatch {
	//template src is the big image
	private String templateSrc;
	//test src is the image to search in the templateSrc
	private String nestSrc;
	//newObjectSrc is the url to save the new created image
	private String   newObjectSrc;
	//width and height is the aditional size to incremente in the newOBjectSrc size
	private int width;
	private int height;
	private boolean showPreview = false;
	//created images attached after usign ImageCreator
	private IplImage iplTemplateSrc;
	private IplImage iplTemplateNest;
	private IplImage iplTemplateResult;

	public DefaultObjectMatch() {
	}



	public DefaultObjectMatch(String templateSrc, String nestSrc,
			String newObjectSrc) {
		this.templateSrc = templateSrc;
		this.nestSrc = nestSrc;
		this.newObjectSrc = newObjectSrc;
	}

	public DefaultObjectMatch(String templateSrc, String nestSrc,
			String newObjectSrc, int width, int height) {
		this.templateSrc = templateSrc;
		this.nestSrc = nestSrc;
		this.newObjectSrc = newObjectSrc;
		this.width = width;
		this.height = height;
	}



	public String getTemplateSrc() {
		return templateSrc;
	}

	public void setTemplateSrc(String templateSrc) {
		this.templateSrc = templateSrc;
	}

	public String getNestSrc() {
		return nestSrc;
	}

	public void setNestSrc(String nestSrc) {
		this.nestSrc = nestSrc;
	}

	public String getNewObjectSrc() {
		return newObjectSrc;
	}

	public void setNewObjectSrc(String newObjectSrc) {
		this.newObjectSrc = newObjectSrc;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isShowPreview() {
		return showPreview;
	}

	public void setShowPreview(boolean showPreview) {
		this.showPreview = showPreview;
	}



	public IplImage getIplTemplateSrc() {
		return iplTemplateSrc;
	}



	public void setIplTemplateSrc(IplImage iplTemplateSrc) {
		this.iplTemplateSrc = iplTemplateSrc;
	}



	public IplImage getIplTemplateNest() {
		return iplTemplateNest;
	}



	public void setIplTemplateNest(IplImage iplTemplateNest) {
		this.iplTemplateNest = iplTemplateNest;
	}



	public IplImage getIplTemplateResult() {
		return iplTemplateResult;
	}



	public void setIplTemplateResult(IplImage iplTemplateResult) {
		this.iplTemplateResult = iplTemplateResult;
	}
	
	
	
	
}
