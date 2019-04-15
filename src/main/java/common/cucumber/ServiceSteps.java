package common.cucumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import common.setup.AllRequests;
import common.util.DataGen;
import org.json.simple.JSONObject;
import org.junit.Assert;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;

import static common.service.Service.sendRequest;
import com.jayway.restassured.response.Response;

public class ServiceSteps {

  public static Response currentResponse;

  @Given("^Sending next REST request with next data$")
  public void CreatingRequest(DataTable dataTable){
    HashMap<String,String> requestMap = new HashMap<String, String>();

    String randomIntString = null;
    requestMap = DataGen.dataToMap(dataTable, 1);
    String serviceName = requestMap.get("service");

    String requestName = requestMap.get("request");
    if (requestName.contains("random_number"))
    {
      requestName = requestName.replace("random_number", randomIntString);
    }

    String requestType = requestMap.get("type");

    String fullRequestURL = AllRequests.getFullRequestURL(requestName);

    JSONObject requestBody = new JSONObject();

    if(!(requestType.equals("GET")))
    {
      requestBody = AllRequests.getRequestBodyByRequestName(requestName);
      requestBody = AllRequests.setRequestValues(requestBody, requestMap);
    }
    currentResponse = sendRequest(serviceName,fullRequestURL,requestType,requestBody);

  }

  @Given("^Verifying next REST response details$")
  public void VerifyResponse(DataTable data_table) {
    HashMap<String, String> expectedResponseMap = new HashMap<String, String>();
    expectedResponseMap = DataGen.dataToMap(data_table, 1);
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
