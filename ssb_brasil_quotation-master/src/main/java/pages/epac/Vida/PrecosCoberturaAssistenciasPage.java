package pages.epac.Vida;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class PrecosCoberturaAssistenciasPage extends PageObjectBase {

    public PrecosCoberturaAssistenciasPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_DADOS_PRECOS_COBERTURA_ASSITENCIA_PAGE_PRESENTATION = "//*[contains(text(), 'Escolher coberturas')]";
    private static final String ABA_ESCOLHER_ASSITENCIAS = "//*[@id='nx-tab-label-1-1']";
    private static final String RADIO_FUNERAL = "//*[@class='nx-switcher__toggle']";
    private static final String SELECT_TIPO_COBERTURA_ASSISTENCIA = "//*[@formcontrolname='dropFuneralTipo']";
    private static final String OPTION_TIPO_COBERTURA_ASSISTENCIA = "//*[@id='nx-dropdown-modal-3']//*[contains(text(), '%s')]";
    private static final String SELECT_VALOR_COBERTURA_ASSISTENCIA = "//*[@formcontrolname='dropFuneralValue']";
    private static final String OPTION_VALOR_COBERTURA_ASSISTENCIA = "//*[@id='nx-dropdown-modal-5']//*[contains(text(), 'R$ %s')]";
    private static final String BUTTON_EXPANSAO_CONDICOES_COMERCIASIS = "//*[@id='nx-expansion-panel-header-0']/div";
    private static final String INPUT_COMISSAO = "//*[@id='nx-input-10']";
    private static final String INPUT_AGENCIAMENTO = "//*[@id='nx-input-11']";
    private static final String BUTTON_RECALCULAR = "//button//span[contains(text(), 'Recalcular')]";
    private static final String SELECT_FORMA_PAGAMENTO = "//nx-dropdown[@aria-controls='nx-dropdown-modal-7']";
    private static final String OPTION_FORMA_PAGAMENTO = "//div[@id='nx-dropdown-modal-7']//div[contains(text(), '%s')]";
    private static final String SELECT_QTD_PARCELAS = "//nx-dropdown[@aria-labelledby = 'nx-formfield-label-14']";
    private static final String OPTION_QTD_PARCELAS = "(//div[@id='nx-dropdown-modal-9']//div[contains(text(), '%s x')])[1]";
    private static final String BUTTON_SALVAR_COTACAO = "(//button//span[contains(text(), 'Salvar cotação')])[2]";

    private static final String NUMERO_COTACAO = "(//div[contains(text(), 'Número da cotação')]//following-sibling::span)[2]";

    private static final String BUTTON_FECHAR_MODAL_COTACAO = "//*[@id='nx-modal-0']/button";
    private static final String BUTTON_AVANCAR = "//button//span[contains(text(), 'Avançar')]";


    //CHECKS
    public boolean isPrecoCoberturasAssistenciaPageDisplayed() {
        return isElementVisible(CHECK_DADOS_PRECOS_COBERTURA_ASSITENCIA_PAGE_PRESENTATION);
    }


    @Step("Acessa a aba 'Escolher Assistências' e seleciona a assisstência '{0}'")
    public void selecionarAssitencia(String assistencia) {
        if (assistencia != "") {
            click(ABA_ESCOLHER_ASSITENCIAS);
            click(RADIO_FUNERAL);
        }
        makeScreenshot();
    }

    @Step("Seleciona a cobertura {0}'")
    public void selecionarTipoCoberturaAssistencia(String tipoCoberturaAssistencia) {
        if (tipoCoberturaAssistencia != "") {
            click(SELECT_TIPO_COBERTURA_ASSISTENCIA);
            click(String.format(OPTION_TIPO_COBERTURA_ASSISTENCIA, tipoCoberturaAssistencia));
            makeScreenshot();
        }
    }

    public void selecionarValorAssistencia(String valorAssistencia) {
        if (valorAssistencia != "") {
            click(SELECT_VALOR_COBERTURA_ASSISTENCIA);
            click(String.format(OPTION_VALOR_COBERTURA_ASSISTENCIA, valorAssistencia));
            makeScreenshot();
        }
    }

    @Step("Insere o valor de comissão {0}'")
    public void inputValorComissao(String comissao) {
        if (comissao != "") {
            click(BUTTON_EXPANSAO_CONDICOES_COMERCIASIS);
            typeText(INPUT_COMISSAO, comissao, true);
            makeScreenshot();
        }
    }

    @Step("Insere o valor de Agenciamento {0}'")
    public void inputValorAgenciamento(String agenciamento) {
        if (agenciamento != "") {
            typeText(INPUT_AGENCIAMENTO, agenciamento, true);
            makeScreenshot();
        }
    }

    @Step("Clica no botão 'Recalcular'")
    public void clickRecalcular() {
        click(BUTTON_RECALCULAR);
        makeScreenshot();
    }

    @Step("Seleciona a forma de pagameto '{0}'")
    public void selectFormaPagamento(String formaPagamento) {
        if (formaPagamento != "") {
            click(SELECT_FORMA_PAGAMENTO);
            click(String.format(OPTION_FORMA_PAGAMENTO, formaPagamento));
            makeScreenshot();
        }
    }

    @Step("Seleciona a quantidade de parcelas '{0}'")
    public void selectQtdParcelas(String qtdParcelas) {
        if (qtdParcelas != "") {
            click(SELECT_QTD_PARCELAS);
            click(String.format(OPTION_QTD_PARCELAS, qtdParcelas));
            makeScreenshot();
        }
    }

    @Step("Clica no botão 'Salvar cotação'")
    public void clickSalvarCotacao() {
        click(BUTTON_SALVAR_COTACAO);
        makeScreenshot();
    }

    public String getNumeroCotacao() {
        NoDriverUtils.await(3);
        return driver.findElement(By.xpath(NUMERO_COTACAO)).getText();
    }

    @Step("Fecha o modal de cotação'")
    public void clickFecharModalCotacao() {
        click(BUTTON_FECHAR_MODAL_COTACAO);
        makeScreenshot();
    }

    @Step("Clica no Botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR);
        makeScreenshot();
    }


}
