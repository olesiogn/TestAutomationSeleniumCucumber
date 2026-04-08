package br.com.nttdata.steps;

import br.com.nttdata.hooks.Hooks;
import br.com.nttdata.pages.PageCarrinho;
import br.com.nttdata.pages.PageCelulares;
import br.com.nttdata.pages.PageHome;
import br.com.nttdata.pages.PageProduto;
import br.com.nttdata.support.DriverManager;
import br.com.nttdata.support.ScreenshotUtil;
import com.aventstack.extentreports.Status;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StoreVivoSteps {
    String precoProduto = "";

    @Dado("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        DriverManager.getDriver().get(url);
        new PageHome(DriverManager.getDriver()).clickConsentButton();
        Hooks.getCurrentTest().log(Status.PASS, "Acessou o site: " + url, ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @E("navego até a seção de {string}")
    public void navegoAteASecaoDe(String menu) {
        new PageHome(DriverManager.getDriver()).selectMenu(menu);
        Hooks.getCurrentTest().log(Status.PASS, "Navegou até a seção: " + menu, ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @E("seleciono o primeiro produto exibido")
    public void selecionoOPrimeiroProdutoExibido() {
        new PageCelulares(DriverManager.getDriver()).clickFirstProduct();
        Hooks.getCurrentTest().log(Status.PASS, "Selecionou o primeiro produto exibido", ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @E("verifico o preço do produto")
    public void verificoOPrecoDoProduto() {
        precoProduto = new PageProduto(DriverManager.getDriver()).returnProductPrice();
        Hooks.getCurrentTest().log(Status.PASS, "Verificou o preço do produto: " + precoProduto, ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @Quando("adiciono o produto ao carrinho")
    public void adicionoOProdutoAoCarrinho() {
        new PageProduto(DriverManager.getDriver()).clickAddToCart();
        Hooks.getCurrentTest().log(Status.PASS, "Adicionou o produto ao carrinho", ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @Então("o carrinho deve conter o item adicionado")
    public void oCarrinhoDeveConterOItemAdicionado() {
        boolean isVisible = new PageCarrinho(DriverManager.getDriver()).isProductInCartVisible();
        assertTrue("O produto deveria estar visível no carrinho", isVisible);
        Hooks.getCurrentTest().log(Status.PASS, "Produto está visível no carrinho", ScreenshotUtil.capture(DriverManager.getDriver()));
    }

    @E("o total do carrinho deve igual ao valor do produto")
    public void oTotalDoCarrinhoDeveIgualAoValorDoProduto() {
        String valorTotal = new PageCarrinho(DriverManager.getDriver()).getTotalCartValue();
        assertEquals("O valor total do carrinho deveria ser igual ao preço do produto", precoProduto, valorTotal);
        Hooks.getCurrentTest().log(Status.PASS, "Total do carrinho igual ao preço do produto: " + valorTotal, ScreenshotUtil.capture(DriverManager.getDriver()));
    }
}
