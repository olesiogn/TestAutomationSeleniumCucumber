package br.com.nttdata.pages;

import br.com.nttdata.attributes.AttributesCarrinho;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageCarrinho extends AttributesCarrinho {
    private WebDriver driver;

    public PageCarrinho(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductInCartVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productInCart)).isDisplayed();
    }

    public String getTotalCartValue() {
        String precoTotal = driver.findElement(totalCartValue).getText();
        System.out.println("Valor total do carrinho: " + precoTotal);
        return precoTotal;
    }

}
