package features;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber; //Junit test runner
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-report.html"} ,tags ="")
//tags run certain scenarios
//, tags = "" run all tests
//tags = "@testSuite1 and @testSuite2") run test with both tags
//tags = "@testSuite1" run single tag
//tags = "@testDataTable"

public class RunCucumberTest {
}
