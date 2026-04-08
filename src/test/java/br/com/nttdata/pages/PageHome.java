package br.com.nttdata.pages;

import br.com.nttdata.attributes.AttributesHome;
import org.openqa.selenium.WebDriver;

public class PageHome extends AttributesHome {
    private WebDriver driver;

    public PageHome(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConsentButton() {
        driver.findElement(consentButton).click();
    }

    public void selectMenu(String menu){
        driver.findElement(menuCelulares(menu)).click();
    }
}
