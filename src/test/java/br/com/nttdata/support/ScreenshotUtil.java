package br.com.nttdata.support;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "target/extent-reports/screenshots";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final AtomicInteger counter = new AtomicInteger(1);

    static {
        try {
            Path dir = Paths.get(SCREENSHOT_DIR);
            if (Files.exists(dir)) {
                Files.walk(dir)
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                Files.delete(file);
                            } catch (Exception e) {
                                System.err.println("Falha ao deletar arquivo: " + file);
                            }
                        });
                System.out.println("🗑️  Pasta de screenshots limpa: " + SCREENSHOT_DIR);
            }
        } catch (Exception e) {
            System.err.println("Falha ao limpar pasta de screenshots: " + e.getMessage());
        }
    }

    private static Media processScreenshot(String base64) {
        try {
            Path dir = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(dir);

            String index = String.format("%03d", counter.getAndIncrement());
            String timestamp = LocalDateTime.now().format(FORMATTER);
            String filename = index + "_" + timestamp + ".png";

            byte[] imageBytes = Base64.getDecoder().decode(base64);
            Files.write(dir.resolve(filename), imageBytes);

        } catch (Exception e) {
            System.err.println("Falha ao salvar screenshot no disco: " + e.getMessage());
        }

        try {
            return MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build();
        } catch (Exception e) {
            return null;
        }
    }

    public static Media capture(WebDriver driver) {
        try {
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            return processScreenshot(base64);
        } catch (Exception e) {
            System.err.println("Falha ao capturar screenshot: " + e.getMessage());
            return null;
        }
    }
}