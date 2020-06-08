package common.cucumber;

import common.etl.ETLSteps;
import cucumber.api.java.en.Given;
import java.io.FileNotFoundException;
import static common.setup.RunnerHooks.*;

public class IETLSteps {

    @Given("^I read the \"([^\"]*)\" file from \"([^\"]*)\"$")
    public void IReadFileFrom(String file, String path) throws FileNotFoundException {
        VerifyExecutedStep(ETLSteps.readFile(file,path));
    }

    @Given("^I diff \"([^\"]*)\" and write into \"([^\"]*)\" in \"([^\"]*)\"$")
    public void IDiffFilesAndWriteInto(String diff, String file, String path) throws FileNotFoundException {
        VerifyExecutedStep(ETLSteps.diffFilesAndWriteInto(diff,file,path));
    }




}
