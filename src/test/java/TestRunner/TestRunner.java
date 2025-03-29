package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/FeatureFiles",
        glue = "StepDefinitionFiles",
        plugin = {
                "pretty", // Pretty console output
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"

        },
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests{

}

