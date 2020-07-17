package common.service;

import com.jayway.restassured.response.Response;
import common.setup.AllProducts;
import common.util.DataGen;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static common.service.ServiceHelp.sendRequest;

public class ServiceSteps {

  public static Response currentResponse;

  public static void CreateRequest(HashMap<String,String> requestMap){
    String randomIntString = null;
    String serviceName = requestMap.get("service");

    String requestName = requestMap.get("request");
    if (requestName.contains("random_number"))
    {
      requestName = requestName.replace("random_number", randomIntString);
    }

    String requestType = requestMap.get("type");
    String fullRequestURL = AllProducts.getFullRequestURL(requestName);
    JSONObject requestBody = new JSONObject();

    if(!(requestType.equals("GET")))
    {
      requestBody = AllProducts.getRequestBodyByRequestName(requestName);
      requestBody = ServiceHelp.setRequestValues(requestBody, requestMap);
    }
    currentResponse = sendRequest(serviceName,fullRequestURL,requestType,requestBody);

  }


  public static void VerifyResponse(HashMap<String, String> expectedResponseMap) {
    HashMap<String, String> responseMap = new HashMap<String, String>();

    //String fullResponseAsString = currentResponse.jsonPath().get().toString();
    //System.out.println(fullResponseAsString);

    ArrayList<HashMap> responseArrayList = new ArrayList<HashMap>();
    try {
      responseArrayList = currentResponse.jsonPath().get();
    } catch (Exception ex) {
      responseMap = currentResponse.jsonPath().get();
      responseArrayList.add(responseMap);
    }

    for (Map.Entry<String, String> entry : expectedResponseMap.entrySet())
    {
      String expectedKey = entry.getKey();
      String expectedValue = entry.getValue();

      if (expectedKey.contains("validate") && expectedValue.equals("email_address")) {
        ArrayList<String> emailAddresses = new ArrayList<String>();
        emailAddresses = currentResponse.jsonPath().get("email");

        for (int iterator = 0; iterator != emailAddresses.size(); iterator++) {
          Assert.assertTrue(DataGen.isValidEmailAddress(emailAddresses.get(iterator)));
          System.out.println(emailAddresses.get(iterator) + "email address validated on response " + iterator);
        }
      }

      else
        {

          for (int iteratorList = 0; iteratorList != responseArrayList.size(); iteratorList++)
          {
              responseMap = responseArrayList.get(iteratorList);
              Assert.assertTrue(responseMap.keySet().toString().contains(expectedValue));
              System.out.println(expectedValue + " verified on response " + iteratorList);
          }
        }
    }

  }
}
