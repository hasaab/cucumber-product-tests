package common.setup;

public class AllURLs {

    public static String YouTubeDev = "https://www.youtube.com";
    public static String YouTubeQA = "https://www.youtube.com";
    public static String YouTubeProd = "https://www.youtube.com";
    public static String GoogleDev = "https://www.linkedin.com";
    public static String GoogleQA = "https://www.linkedin.com";
    public static String GoogleProd = "https://www.linkedin.com";


    public static String getProductURL()
    {

        if (System.getProperty("product").contains("YouTube") && System.getProperty("runEnvironment").equals("DEV"))
            return YouTubeDev;
        else if (System.getProperty("product").contains("YouTube") && System.getProperty("runEnvironment").equals("QA"))
            return YouTubeQA;
        else if (System.getProperty("product").contains("YouTube") && System.getProperty("runEnvironment").equals("PROD"))
            return YouTubeProd;


        else if (System.getProperty("product").contains("Linkedin") && System.getProperty("runEnvironment").equals("DEV"))
            return GoogleDev;
        else if (System.getProperty("product").contains("Linkedin") && System.getProperty("runEnvironment").equals("QA"))
            return GoogleQA;
        else if (System.getProperty("product").contains("Linkedin") && System.getProperty("runEnvironment").equals("PROD"))
            return GoogleProd;

        else return "Product URL has not been defined";


    }


}
