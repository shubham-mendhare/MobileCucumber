package com.org.projects.CucumberMobile.Runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/com/org/projects/CucumberMobile/features",glue ="com.org.projects.CucumberMobile.stepDefinations"
,monochrome=true, plugin= {"html:target/cucumber.html", "json:target/cucumber.json"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

	
}
