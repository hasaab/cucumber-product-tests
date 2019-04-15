import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions
        (
            features = {"src/test/resources/features"},
        tags = { "@Smoke", "@Regression" },
        glue = {"classpath:common/cucumber","classpath:common.setup","classpath:products"},
            plugin = {
            "html:target/cucumber-reports/",
                    "junit:target/cucumber-reports/report.xml",
                    "json:target/cucumber-reports/report.json",
            "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/extent-report.html"
            },monochrome = true
            )


public class CukesRunTest {

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("src/main/resources/config/extent-config.xml"));

    }
}
