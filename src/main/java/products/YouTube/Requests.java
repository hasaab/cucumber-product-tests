package products.YouTube;

import org.json.simple.JSONObject;

public class Requests {

    public static String getRequestURL(String requestName) {
        String fullRequestURL = null;
        if (requestName.contains("features/YouTube")) {
            fullRequestURL = "http://jsonplaceholder.typicode.com/";
        } else {
            System.out.println(requestName + " request has not defined yet in the test requests AllPages \n");
            return null;
        }
        return fullRequestURL;
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
