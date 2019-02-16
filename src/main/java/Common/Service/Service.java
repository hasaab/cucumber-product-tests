package Common.Service;

import java.util.concurrent.TimeUnit;

import static com.jayway.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ConnectionConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;

public class Service {

  public static RestAssuredConfig config = RestAssured.config().connectionConfig(new ConnectionConfig().closeIdleConnectionsAfterEachResponseAfter(10, TimeUnit.MILLISECONDS));

  public static Response sendRequest(String service, String fullRequestUrl, String requestType, JSONObject requestBody)
  {
    Response response=null;

    if(!(requestBody.entrySet().size() == 0))
      System.out.println(" service is: " + service + "\n full request url is: " + fullRequestUrl +
                         "\n request type is: " + requestType+ "\n request body is: " + requestBody.toString()+ "\n");
    else
      System.out.println(" service is: " + service+ "\n full request url is: " + fullRequestUrl +
                         "\n request type is: " + requestType + "\n");

    if (requestType.equals("GET"))
    {
      response =
      //RestAssured.with().config(config).
      given().
      //contentType("application/json").
      //body(request_body).
      //queryParam().
      //when().
      get(fullRequestUrl).
                         then().
                               statusCode(200).
                                              extract().response();

      return response;
    }
    else if (requestType.equals("POST"))
    {
      response =
      given().
             contentType("application/json; charset=UTF-8").
                                                           body(requestBody).
      //queryParam("").
      when().
            post(fullRequestUrl).
                                then().
                                      statusCode(201).
                                                     extract().response();
      return response;
    }
    else if (requestType.equals("PUT"))
    {
      response =
      given().
             contentType("application/json").
                                            body(requestBody).
      //queryParam("").
      when().
            put(fullRequestUrl).
                               then().
                                     statusCode(201).
                                                    extract().response();
      return response;

    }
    else System.out.println( requestType + " request type has not defined yet in the test messenger");
    return response;

  }




}
