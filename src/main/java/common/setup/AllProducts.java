package common.setup;

import org.json.simple.JSONObject;

import static common.setup.AllPages.*;

public class AllProducts {

    public static String getElementSelector(String elementName)
    {
        if(System.getProperty("product").contains("YouTube"))
            return getYouTubeElementSelector(elementName);
        else if(System.getProperty("product").contains("Google"))
            return getGoogleElementSelector(elementName);
        else {System.out.println("Product has not been defined in AllProducts");
            return "";}
    }

    public static String getFullRequestURL(String requestName) {
        String fullRequestURL = null;
        if (System.getProperty("product").contains("features/YouTube")) {
            fullRequestURL = AllRequests.getYouTubeFullRequestURL(requestName);
        } else {
            System.out.println(requestName + " request has not defined yet in the test requests AllPages \n");
            return null;
        }
        return fullRequestURL;
    }

    public static JSONObject getRequestBodyByRequestName(String requestName) {
        JSONObject requestBody = new JSONObject();
        if (System.getProperty("product").contains("features/YouTube")) {
            requestBody = AllRequests.getYouTubeRequestBody(requestName);
        } else {
            System.out.println(requestName + " request has not defined yet in the test requests AllPages \n");
            return null;
        }
        return requestBody;
    }



}
