package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/java/FeatureFiles",
        glue = "StepDefinitionFiles",
        plugin = {
                "pretty", // Pretty console output
                "html:target/cucumber-reports/cucumber.html", // HTML Report
                "json:target/cucumber-reports/cucumber.json", // JSON Report
                "rerun:target/rerun.txt" // Re-run failed scenarios
        },
        monochrome = true, // Better readability in console
        dryRun = false // Change to 'true' to check step definition mapping
)
public class TestRunner extends AbstractTestNGCucumberTests{

}

