package pages.epac.Vida;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.web.page.PageObjectBase;

public class PagamentoPage extends PageObjectBase {

    public PagamentoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_PAGAMENTO_PAGE_PRESENTATION = "//*[contains(text(), 'Dados de pagamento')]";
    private static final String SELECT_DIA_VENCIMENTO = "//nx-dropdown[@placeholder='Selecione']";
    private static final String OPTION_DIA_VENCIMENTO = "//div[contains(text(), ' %s ')]";
    private static final String BUTTON_SIM_CONFIRMACO_DADOS_PAGAMENTO = "//span[contains(text(), 'Sim')]";
    private static final String BUTTON_AVANCAR = "//button//span[contains(text(), 'Avançar')]";


    //CHECKS
    public boolean isPagamentoPageDisplayed() {
        return isElementVisible(CHECK_PAGAMENTO_PAGE_PRESENTATION);
    }


    @Step("Clica no Botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR);
        makeScreenshot();
    }

    @Step("Seleciona dia do vencimento'")
    public void selectDiaVencimento(String diaVencimento) {
        click(SELECT_DIA_VENCIMENTO);
        click(String.format(OPTION_DIA_VENCIMENTO, diaVencimento));
        makeScreenshot();
    }

    @Step("Clicam em 'Sim' para confirmar dados de pagamento'")
    public void clickSimConfirmarDadosPagamento() {
        click(BUTTON_SIM_CONFIRMACO_DADOS_PAGAMENTO);
        makeScreenshot();
    }
}
