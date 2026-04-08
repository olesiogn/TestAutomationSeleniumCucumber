package br.com.nttdata.attributes;

import org.openqa.selenium.By;

public class AttributesProduto {
    protected By productPrice = By.xpath("(//div[@aria-label='new item price']//p[contains(text(),'R$')])[1]");
    protected By addToCartButton = By.xpath("//span[normalize-space()='Colocar no carrinho']");
}
