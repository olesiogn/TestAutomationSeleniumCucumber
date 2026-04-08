package br.com.nttdata.hooks;

import br.com.nttdata.support.DriverManager;
import br.com.nttdata.support.ExtentReportManager;
import br.com.nttdata.support.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final ExtentReports extent = ExtentReportManager.getInstance();
    public static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before()
    public void beforeScenario(Scenario scenario) {
        DriverManager.initDriver();
        ExtentTest extentTest = extent.createTest(scenario.getName());
        extentTest.assignCategory(scenario.getSourceTagNames().toString());
        test.set(extentTest);
    }

    @After()
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.get().log(
                    Status.FAIL,
                    "Cenário falhou: " + scenario.getName(),
                    ScreenshotUtil.capture(DriverManager.getDriver())
            );
        } else {
            test.get().log(Status.PASS, "Cenário passou com sucesso!");
        }
        extent.flush();
        DriverManager.quitDriver();
    }

    public static ExtentTest getCurrentTest() {
        return test.get();
    }
}