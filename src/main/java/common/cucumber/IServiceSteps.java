package common.cucumber;

import java.util.HashMap;
import common.util.DataGen;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import static common.service.ServiceSteps.*;

public class IServiceSteps {

  @Given("^Sending next REST request with next data$")
  public void ICreatingRequest(DataTable dataTable){
    HashMap<String,String> requestMap = new HashMap<String, String>();
    requestMap = DataGen.dataToMap(dataTable, 1);
    CreateRequest(requestMap);
  }

  @Given("^Verifying next REST response details$")
  public void IVerifyResponse(DataTable data_table) {
    HashMap<String, String> expectedResponseMap = new HashMap<String, String>();
    expectedResponseMap = DataGen.dataToMap(data_table, 1);

    VerifyResponse(expectedResponseMap);

  }

}
