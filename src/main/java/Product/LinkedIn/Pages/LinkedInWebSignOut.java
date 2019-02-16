package Product.LinkedIn.Pages;

import java.util.HashMap;

/**
 * Created by Sissy and Zol on 30/07/2017.
 */
public class LinkedInWebSignOut {

  public static HashMap<String, String> elementLocators = new HashMap<String, String>();

  public static void setElements()
  {
    elementLocators.put("user_name_entry", "input#login-email");
    elementLocators.put("pass_word_entry", "input#login-password");
    elementLocators.put("sign_in_button", "input#login-submit");
    elementLocators.put("signed_out_message", "#page-title > h1");
    elementLocators.put("close_cooky_policy_alert", "#dismiss-alert");
    elementLocators.put("1", "");

  }

  public static String getELementLocator(String element_name)
  {
    setElements();
    String elementLocator = elementLocators.get(element_name);
    return elementLocator;
  }



}
