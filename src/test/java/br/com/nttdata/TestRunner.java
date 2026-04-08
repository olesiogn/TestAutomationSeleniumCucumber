package br.com.nttdata;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources",
        glue = "br.com.nttdata.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@Test")
public class TestRunner {
}
