package common.setup;

import org.json.simple.JSONObject;


public class AllRequests {


  public static String getYouTubeRequestURL(String requestName) {
    return products.YouTube.Requests.getRequestURL(requestName);
  }

  public static JSONObject getYouTubeRequestBody(String requestName) {
      return products.YouTube.Requests.getRequestBody(requestName);
  }


  public static String getLinkedinRequestURL(String requestName) {
      return products.Linkedin.Requests.getRequestURL(requestName);
  }

  public static JSONObject getLinkedinRequestBody(String requestName) {
      return products.Linkedin.Requests.getRequestBody(requestName);
  }

}
