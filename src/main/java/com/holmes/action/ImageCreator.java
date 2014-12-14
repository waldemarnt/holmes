package com.holmes.action;

import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_32F;
import static org.bytedeco.javacpp.opencv_core.cvCopy;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_core.cvGetSize;
import static org.bytedeco.javacpp.opencv_core.cvMinMaxLoc;
import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_core.cvSetImageROI;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_core.cvZero;
import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import static org.bytedeco.javacpp.opencv_highgui.cvSaveImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_CCORR_NORMED;
import static org.bytedeco.javacpp.opencv_imgproc.cvMatchTemplate;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core.CvPoint;
import org.bytedeco.javacpp.opencv_core.CvRect;
import org.bytedeco.javacpp.opencv_core.IplImage;

import com.holmes.model.DefaultObjectMatch;
import com.holmes.util.ImageMatchInterface;

public class ImageCreator implements ImageMatchInterface {
	DefaultObjectMatch objectMatch;
	protected IplImage templateSrc;
	protected IplImage templateNest;
	protected IplImage templateResult;
	protected CvPoint minLoc;
	protected CvPoint maxLoc;
	
	public ImageCreator(DefaultObjectMatch objectMatch) {
		this.objectMatch = objectMatch;
	}

	public void create() {
	
		createNeededImages(objectMatch.getTemplateSrc(),objectMatch.getNestSrc());
		createResultIplImage();
		handleNewImageCreation();
	}
	/*
	 * create first need images the template and the image to search in the template
	 */
	protected void createNeededImages(String template,String nest){
		this.templateSrc = createIplImageFromUrl(template);
		this.templateNest = createIplImageFromUrl(nest);
		
	}
	
	/*
	 * transform an url in a IplImage array
	 */
	protected IplImage createIplImageFromUrl(String url){
		
		return cvLoadImage(url, 3);
	}
	
	/*
	 * create the result template and nest images
	 */
	protected void createResultIplImage(){
		this.templateResult = cvCreateImage(
				cvSize(this.templateSrc.width() - this.templateNest.width() + 1,
						this.templateSrc.height() - this.templateNest.height() + 1), IPL_DEPTH_32F, 1);
		
		cvZero(this.templateResult);
	}
	
	protected void handleNewImageCreation(){
		makeMatch();
		createMinMaxLocByPoint();
		attachImages();
	}
	
	/*
	 * Try to match this images, and attach this changes in the templateSrc
	 */
	protected void makeMatch(){
		
		cvMatchTemplate(this.templateSrc, this.templateNest, this.templateResult, CV_TM_CCORR_NORMED);
	}
	
	protected void createMinMaxLocByPoint(){
		
		DoublePointer min_val = new DoublePointer();
		DoublePointer max_val = new DoublePointer();
				
		minLoc = new CvPoint();
		maxLoc = new CvPoint();

		cvMinMaxLoc(this.templateResult, min_val, max_val, minLoc, maxLoc, null);
	}
	
	/*
	 * create a new image based in the finded point and width x height passed in paremeters
	 */
	protected void createCroppedRect(){

		setImageROIBasedInImageDimensions();
		
		IplImage imageNew = cvCreateImage(cvGetSize(this.templateSrc), this.templateSrc.depth(),
				this.templateSrc.nChannels());
		cvCopy(this.templateSrc, imageNew);
		cvSaveImage(objectMatch.getNewObjectSrc(), imageNew);
	}

	protected void setImageROIBasedInImageDimensions(){
		CvRect rect = createRect();
		cvSetImageROI(this.templateSrc, rect);
	}
	
	/**
	 * if the height of the area is less than of the height parameter the crop will try to crop to another size
	 * @return CvRect
	 */
	protected CvRect createRect(){
		CvRect rect = new CvRect();
		
		if(objectMatch.getHeight()/maxLoc.x() < 1){
			rect.x(maxLoc.x()-objectMatch.getWidth());
			rect.y(maxLoc.y()- objectMatch.getHeight());
			rect.width(this.templateNest.width() + objectMatch.getWidth());
			rect.height(this.templateNest.width() + objectMatch.getHeight());
			
			return rect;

		}		
		rect.x(maxLoc.x());
		rect.y(maxLoc.y());
		rect.width(this.templateNest.width() + objectMatch.getWidth());
		rect.height(this.templateNest.width() + objectMatch.getHeight());
		
		return rect;
	}
	
	/*
	 * Attach the new information to DefaultObjetMatch
	 */
	protected void attachImages(){
		objectMatch.setIplTemplateNest(this.templateNest);
		objectMatch.setIplTemplateSrc(this.templateSrc);
		objectMatch.setIplTemplateResult(this.templateResult);
	}
}
