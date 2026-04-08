package br.com.nttdata.steps;

import br.com.nttdata.pages.PageCarrinho;
import br.com.nttdata.pages.PageCelulares;
import br.com.nttdata.pages.PageHome;
import br.com.nttdata.pages.PageProduto;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StoreVivoSteps {
    private WebDriver driver;
    String precoProduto = "";

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    @Dado("que acesso o site {string}")
    public void que_acesso_o_site(String url) {
        driver.get("https://store.vivo.com.br/");
        new PageHome(driver).clickConsentButton();
    }

    @E("navego até a seção de {string}")
    public void navegoAteASecaoDe(String menu) {
        new PageHome(driver).selectMenu(menu);
    }

    @E("seleciono o primeiro produto exibido")
    public void selecionoOPrimeiroProdutoExibido() {
        new PageCelulares(driver).clickFirstProduct();
    }

    @E("verifico o preço do produto")
    public void verificoOPrecoDoProduto() {
        precoProduto = new PageProduto(driver).returnProductPrice();
    }

    @Quando("adiciono o produto ao carrinho")
    public void adicionoOProdutoAoCarrinho() {
        new PageProduto(driver).clickAddToCart();
    }

    @Então("o carrinho deve conter o item adicionado")
    public void oCarrinhoDeveConterOItemAdicionado() {
        boolean isVisible = new PageCarrinho(driver).isProductInCartVisible();
        assertTrue("O produto deveria estar visível no carrinho", isVisible);
    }

    @E("o total do carrinho deve igual ao valor do produto")
    public void oTotalDoCarrinhoDeveIgualAoValorDoProduto() {
        String valorTotal = new PageCarrinho(driver).getTotalCartValue();
        assertEquals("O valor total do carrinho deveria ser igual ao preço do produto", precoProduto, valorTotal);
    }
}
