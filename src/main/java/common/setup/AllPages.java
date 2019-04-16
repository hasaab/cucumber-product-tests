package common.setup;

public class AllPages {

    public static String getYouTubeElementSelector(String elementName)
    {
        if(System.getProperty("activePage").equals("Home"))
            return products.YouTube.Pages.HomePage.getElementSelector(elementName);
        else if(System.getProperty("activePage").equals("Search"))
            return products.YouTube.Pages.SearchPage.getElementSelector(elementName);
        else {System.out.println( System.getProperty("product") + " " + System.getProperty("activePage") +
                                 " Page has not been defined in the AllPages");
            return "";}
    }

    public static String getLinkedinElementSelector(String elementName)
    {
        if(System.getProperty("activePage").equals("Home"))
            return products.Linkedin.Pages.HomePage.getElementSelector(elementName);
        else if(System.getProperty("activePage").equals("Search"))
            return products.Linkedin.Pages.SearchPage.getElementSelector(elementName);
        else {System.out.println( System.getProperty("product") + " " + System.getProperty("activePage") +
                                  " Page has not been defined in the AllPages");
            return "";}
    }


}
