package Test.Service;

import Product.YouTube.RequestsYouTube;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class AllRequests {

  public static String currentProductName;

  public static String getFullRequestURL(String requestName) {
    String fullRequestURL = null;
    if (currentProductName.contains("YouTube")) {
      fullRequestURL = RequestsYouTube.getFullRequestURL(requestName);
    } else {
      System.out.println(requestName + " request has not defined yet in the test requests AllPages \n");
      return null;
    }
    return fullRequestURL;
  }

  public static JSONObject getRequestBodyByRequestName(String requestName) {
    JSONObject requestBody = new JSONObject();
    if (currentProductName.contains("YouTube")) {
      requestBody = RequestsYouTube.getRequestBody(requestName);
    } else {
      System.out.println(requestName + " request has not defined yet in the test requests AllPages \n");
      return null;
    }
    return requestBody;
  }

  public static JSONObject setRequestValues(JSONObject requestBody,HashMap<String,String> requestMap)
  {

    for (Object key : requestBody.keySet()) {
      String bodyKey = (String)key;

      //System.out.println("key: "+ bodyKey + " value: " + bodyValue);

      for (Map.Entry<String,String> entry : requestMap.entrySet())
      {
        String requestKey = entry.getKey();
        String requestValue = entry.getValue();

        if (bodyKey.equals(requestKey))
          requestBody.put(bodyKey,requestValue);
      }
    }

    return  requestBody;
  }




}
