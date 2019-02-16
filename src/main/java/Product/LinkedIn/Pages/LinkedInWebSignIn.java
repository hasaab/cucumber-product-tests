package Product.LinkedIn.Pages;

import java.util.HashMap;
import java.util.Map;

public class LinkedInWebSignIn {

  public static Map<String, String> elementLocators = new HashMap<String, String>();

  public static void setElements()
  {
    elementLocators.put("select_language_dropdown", "");
    elementLocators.put("english_language_selection", "");
    elementLocators.put("accept_cookies_button", "button#dismiss-alert");
    elementLocators.put("email_entry", "input#login-email");
    elementLocators.put("password_entry", "input#login-password");
    elementLocators.put("signin_button", "input#login-submit");
    elementLocators.put("1", "");

  }

  public static String getELementLocator(String element_name)
  {
    setElements();
    String elementLocator = elementLocators.get(element_name);
    return elementLocator;
  }

}
