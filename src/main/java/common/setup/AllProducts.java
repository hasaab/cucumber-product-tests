package common.setup;

import org.json.simple.JSONObject;

import static common.setup.AllPages.*;
import static common.setup.AllRequests.getLinkedinRequestURL;
import static common.setup.AllRequests.getYouTubeRequestURL;

public class AllProducts {

    public static String getElementSelector(String elementName)
    {
        if(System.getProperty("product").contains("YouTube"))
            return getYouTubeElementSelector(elementName);
        else if(System.getProperty("product").contains("Linkedin"))
            return getLinkedinElementSelector(elementName);
        else {System.out.println("Product has not been defined in AllProducts");
            return "";}
    }

    public static String getFullRequestURL(String requestName) {
        if (System.getProperty("product").contains("YouTube")) {
            return getYouTubeRequestURL(requestName);}
        else if (System.getProperty("product").contains("Linkedin")) {
            return getLinkedinRequestURL(requestName);
        } else {
            System.out.println("Product has not defined yet in the test requests AllProducts \n");
            return "";
        }
    }

    public static JSONObject getRequestBodyByRequestName(String requestName) {
        if (System.getProperty("product").contains("YouTube")) {
            return AllRequests.getYouTubeRequestBody(requestName);
        }
        else if (System.getProperty("product").contains("Linkedin")) {
             return AllRequests.getLinkedinRequestBody(requestName);
        }else {
            System.out.println("Product request has not defined yet in the test requests AllProducts \n");
            return null;
        }
    }



}
