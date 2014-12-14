package com.holmes;

import static org.bytedeco.javacpp.opencv_core.cvReleaseImage;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;

import com.holmes.action.ImageCreator;
import com.holmes.model.ImageObjectMatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ImageObjectMatch obj = delegateCorrectConstructor(args);
        ImageCreator ic = new ImageCreator(obj);
        
        /*
         * if show proview is true, show the image
         */
        if(obj.isShowPreview()){
    		cvShowImage("Template Matching preview 0.0.1", obj.getIplTemplateSrc());

        }
		
        cvWaitKey(0);
		cvReleaseImage(obj.getIplTemplateSrc());
		cvReleaseImage(obj.getIplTemplateNest());
		cvReleaseImage(obj.getIplTemplateResult());    
        
    }
    
    /*
     * Call the correct constructor of ImageObjectMatch
     */
    private static ImageObjectMatch delegateCorrectConstructor(String[] args){
        ImageObjectMatch obj = new ImageObjectMatch();

    	switch (args.length) {
		case 3:
			
			obj =  new ImageObjectMatch(args[0], args[1], args[2]);
			
			break;
		case 5:
			
			obj =  new ImageObjectMatch(args[0], args[1], args[2],Integer.parseInt(args[3]), Integer.parseInt(args[4]));
			
			break;
		case 6:
			obj =  new ImageObjectMatch(args[0], args[1], args[2],Integer.parseInt(args[3]), Integer.parseInt(args[4]));
			obj.setShowPreview(true);
			
			break;

		default:
			break;
		}
    	
    	return obj;
    }
}
