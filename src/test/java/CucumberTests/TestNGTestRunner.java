package CucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/CucumberTests", glue="CucumberTestStepDefinition"
,monochrome=true, tags="@Confirmation", plugin= {"html:target/cucumber.html"})
/*
 * run all feature files present in cucumber package
 * map the using stepDefinitions
 * print results in readable format (monochrome=true)
 * run certain tests with marked tags
 * generate the report in html
 */
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
	
}
