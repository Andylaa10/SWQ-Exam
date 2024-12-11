package postoffice;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "postoffice.stepdefs",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunCucumberTest {
}
