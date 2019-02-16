package Product.YouTube;

import org.json.simple.JSONObject;

public class RequestsYouTube {

  public static String FakeRESTServiceBaseURI = "http://jsonplaceholder.typicode.com/";

  public static String getFullRequestURL(String request) {
    String fullRequestUrl = null;

    fullRequestUrl = FakeRESTServiceBaseURI + request;
    //http://jsonplaceholder.typicode.com/posts?userId=1

    return fullRequestUrl;
  }

  public static JSONObject getRequestBody(String requestName) {
    JSONObject requestBody = new JSONObject();

    if (requestName.contains("posts/")) {
      requestBody.put("postId", "");
      requestBody.put("Id", "");
      requestBody.put("name", "");
      requestBody.put("email", "");
      requestBody.put("body", "");
      return requestBody;
    } else if (requestName.contains("GET")) {
      return requestBody;
    } else {
      System.out.println(requestName + " request has not defined yet in the test services Services \n");
      return requestBody;
    }

  }
}
