package Test.SetUp;

import org.junit.runner.RunWith;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",
overviewReport = true,
outputFolder = "target")
@CucumberOptions
(
plugin = {"pretty", "html:target/TestReports/TestReport.html"
         //"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
         //"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"
},
features = { "src/test/resources/Product1/" },
glue ={"src/test/java/Steps/"}
)


public class RunCukes {

}