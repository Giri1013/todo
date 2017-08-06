package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/Features"}, plugin = "json:target/cucumber.json",glue = "src/main/java/Steps")
public class TestRunner extends AbstractTestNGCucumberTests {
}
