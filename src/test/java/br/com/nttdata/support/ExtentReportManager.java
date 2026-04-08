package br.com.nttdata.support;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-reports/index.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Relatório de Testes");
            spark.config().setReportName("Automation Test Report");
            spark.config().setEncoding("UTF-8");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Projeto", "Store Vivo");
            extent.setSystemInfo("Autor", "NTT Data");
        }
        return extent;
    }
}
