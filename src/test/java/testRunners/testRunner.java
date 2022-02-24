package testRunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", glue= {"stepDefinitions"},
monochrome= true,
plugin= {"pretty", "html:target/HtmlReports"},
tags="@River_APITests")

public class testRunner {

}