package pages.trenproduccion;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class TablaPreciosPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String FORMA_DE_PAGO_SELECT_ID = "ParrillaBean$primRecibo";
    private static final String DIA_DE_PAGO_TEXTBOX_ID = "ParrillaBean$diaPago";
    private static final String INPUT_NAME_ID = "EstudioBean$nombreEstudio";
    private static final String INPUT_SURNAME_ID = "EstudioBean$apellidosEstudio";
    private static final String EMITIR_BUTTON_ID = "ParrillaBean$btnEmitir";
    private static final String CALCULAR_BUTTON_ID = "btnCalcular";
    private static final String BUTTON_SAVE_QUOTATION_ID = "btnArchivar";
    private static final String BUTTON_BACK_TO_HOME_ID = "imgOptMenuBackHome";
    private static final String INPUT_PHONE_ID = "EstudioBean$telefonoEstudio";
    private static final String EMAIL_TEXTBOX_ID = "EstudioBean$emailEstudio";
    private static final String BUTTON_SAVE_DOC_QUOTATION0_ID = "doc0";
    private static final String INPUT_REFERENCIA_COTACAO_ID = "EstudioBean$refEstudio";
    private static final String NUM_PARCELAS_TEXTBOX_ID = "ParrillaBean$numFracciones";
    private static final String TIPO_PAGAMENTO_AVISTA_RADIO_BUTTON_ID = "ParrillaBean$modalidades[0]rad";
    private static final String TIPO_PAGAMENTO_PARCELADO_RADIO_BUTTON_ID = "ParrillaBean$modalidades[1]rad";


    //LOCATORS - XPATH
    private static final String TABLA_PRECIOS_TITLE_XPATH = "//td[contains(text(), 'TABELA PREÇOS')]";
    private static final String BUTTON_SAVE_XPATH = "(//div[@class='sectionButton'][contains(text(),'Salvar')])[2]";
    private static final String ALERT_XPATH = "//*[contains(text(),'A cotação foi salva com o número')]";
    private static final String CHECK_QUOTATION_SAVED_XPATH = "//*[contains(text(),'A cotação foi salva com o número')]";
    private static final String ESSENCIAL_RADIO_BUTTON_XPATH = "//input[@name= 'ParrillaBean$modalidadPago' and @value = ' 0 ']";
    private static final String TXT_ALERTA_ERRO_XPATH = "//tr[@class='rowAppErrorTextBlock']/td/b[contains(text(), 'Erro')]";
    private static final String TXT_MENSAGEM_ERRO_XPATH = "//tr[@class='rowAppErrorTextBlock']/td[2]";
    private static final String COBERTURA_XPATH = "//label[contains(text(), '%s')]/ancestor::td/preceding-sibling::td/input[@type='radio']";
    private static final String TXT_FORA_POLITICA_CIA_XPATH = "//*[contains(text(),'ora da política de aceitação da Cia.')]";
    private static final String TXT_ERRO_NO_CALCULO_XPATH = "//*[contains(text(),'Erro de cálculo')]";
    private static final String TXT_MSG_SOMENTE_XPORCENTO_FIPE_XPATH = "//td[contains(text(),'Cálculo realizado com ') or contains(text(), '% FIPE')]";
    private static final String INPUT_SALDO_ONLINE_XPATH = "//tr[td/label[contains(text(), 'Saldo Online')]]/td/input";
    private static final String AVANCAR_BUTTON_XPATH = "//div[@id='o_3']";
    private static final String OPTION_FORMA_PAGAMENTO_XPATH = "//select[@id='ParrillaBean$primRecibo']//option[contains(text(), '%s')]";

    private static final String OPTION_PACOTE_BY_VALUE_XPATH_ = "(//input[@name= 'ParrillaBean$modalidadPago'])[%s]";
    private static final String QTD_PACOTE_ISDISPLAYED_XPATH_ = "(//*[@title='Detalhe Pacote'])[%s]";
    private static final String OPCAO_PACOTE_PERSONALIZADO_BY_VALUE = "//*[@id='InformacoesComplementares$personalizado']/option[text()='%s']";


    //Construtor
    public TablaPreciosPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    public boolean isNumeroCotacaoDisplayed() {
        String numAlert = getQuotationAlert().getText().replaceAll("\\D", "");
        return !numAlert.isEmpty();
    }

    public boolean isPdfOpen() {
        return driver.getCurrentUrl().contains("PDF");
    }


    public boolean isqtdPacotesIsOk(String qtdMustHave) {
        return isElementVisible(String.format(QTD_PACOTE_ISDISPLAYED_XPATH_, qtdMustHave), 3);
    }

    public boolean isTablaPrecios() {
        return isElementVisible(TABLA_PRECIOS_TITLE_XPATH, 4);
    }


    public boolean msgForaPolicitaAceitacaoIsDisplayed() {
        return isElementVisible(TXT_FORA_POLITICA_CIA_XPATH, 2);
    }

    public boolean msgErroCalculoIsDisplayed() {
        return isElementVisible(TXT_ERRO_NO_CALCULO_XPATH, 2);
    }

    public boolean msgSomenteXValueFipeIsDisplayed() {
        return isElementVisible(TXT_MSG_SOMENTE_XPORCENTO_FIPE_XPATH, 5);
    }

    //Actions
    @Step("No combo 'Forma de Pagamento', selecionar a opção '{0}'")
    public void selectFormaPagamento(String formaPagamento) {
        click(String.format(OPTION_FORMA_PAGAMENTO_XPATH, formaPagamento));
        makeScreenshot();
    }


    @Step("Seleciona o pacote '{0}'")
    public void selectPacote(String pacote) {
        if (!pacote.isEmpty()) {

            String numPacote;
            switch (pacote) {
                case "Compacto": {
                    numPacote = "1";
                }
                break;
                case "Ampliado": {
                    numPacote = "2";
                }
                break;
                case "Exclusivo": {
                    numPacote = "3";
                }
                break;
                case "Especial": {
                    numPacote = "4";
                }
                break;
                default:
                    throw new IllegalArgumentException("O pacote informado não existe. Insira um dos presentes na classe Constants");
            }
            click(String.format(OPTION_PACOTE_BY_VALUE_XPATH_, numPacote));
            makeScreenshot();
        }
    }

    @Step("Seleciona o pacote '{0}'")
    public void selectPacotePersonalisado(String opcao) {
        if (!opcao.isEmpty()) {
            click(String.format(OPCAO_PACOTE_PERSONALIZADO_BY_VALUE, opcao));
        }
        }

    @Step("Seleciona o tipo de pagamento (à vista ou parcelado)")
    public void selectTipoPagamento(String tipoPagamento) {
        switch (tipoPagamento) {
            case "AVISTA": {
                click(TIPO_PAGAMENTO_AVISTA_RADIO_BUTTON_ID);
            }
            break;
            case "PARCELADO": {
                click(TIPO_PAGAMENTO_PARCELADO_RADIO_BUTTON_ID);
            }
            break;
        }
        makeScreenshot();
    }

    @Step("Preencher o dia de pagamento com {0}")
    public void inputDiaDePagamento(String dia) {
        typeText(DIA_DE_PAGO_TEXTBOX_ID, dia, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Número de Parcelamento' {0}")
    public void inputNumParcelas(String qtdParcelas) {
        selectOptionDDM(DDM.SEA, NUM_PARCELAS_TEXTBOX_ID, qtdParcelas);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Emitir'")
    public void clickEmitirButton() {
        NoDriverUtils.await(2);
        click(EMITIR_BUTTON_ID);
        acceptAlert(3);
        makeScreenshot();
    }

    @Step("Selecionar a cobertura: '{0}'")
    public void selecionarCoberturaVida(String nomeCobertura) {
        waitForJSandJqueryFinish();
        getCoberturaPorNome(nomeCobertura).click();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Clicar no botão 'Emitir'")
    public void clickEmitirButton2() {
        if (isElementVisible(EMITIR_BUTTON_ID, 2)) {
            clickEmitirButton();
        }
    }


    @Step("Clicar no botão 'Calcular'")
    public void clickCalcularButton() {
        NoDriverUtils.await(2);
        click(CALCULAR_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarButton() {
        NoDriverUtils.await(2);
        click(AVANCAR_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Clicar na modalidade de pagamento 'Essencial'")
    public void clickEssencialRadioButton() {
        click(ESSENCIAL_RADIO_BUTTON_XPATH, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Salvar Cotação'")
    public void clickSalvarCotacao() {
        click(BUTTON_SAVE_QUOTATION_ID);
        makeScreenshot();
    }

    @Step("Preencher o campo Nome: {0}")
    public void inputNome(String text) {
        typeText(INPUT_NAME_ID, text, true);
        makeScreenshot();
    }

    @Step("Preencher o campo Sobrenome {0}")
    public void inputSobrenome(String text) {
        typeText(INPUT_SURNAME_ID, text, true);
        makeScreenshot();
    }

    @Step("Preencher o campo telefone {0}")
    public void inputPhone(String phone) {
        typeText(INPUT_PHONE_ID, phone, true);
        makeScreenshot();
    }

    @Step("Preencher o campo e-mail '{0}'")
    public void inputMail(String email) {
        typeText(EMAIL_TEXTBOX_ID, email, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Salvar'")
    public void clickSalvar() {
        click(BUTTON_SAVE_XPATH);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Clicar em 'Voltar para Home'")
    public void clickVoltarParaHome() {
        waitForClickable(BUTTON_BACK_TO_HOME_ID);
        scrollToElement(BUTTON_BACK_TO_HOME_ID);
        clickByJS(BUTTON_BACK_TO_HOME_ID);
        makeScreenshot();
    }

    @Step("Gerar Nº de COTAÇÃO")
    public void exibeNumeroCotacao() {
        String cotacao = getNumeroCotacaoResidencia().getText();
//        System.out.println("Cotação gerada: " + cotacao);
        Allure.step(cotacao);
    }

    public String getNumeroCotacao() {
        waitForVisible(CHECK_QUOTATION_SAVED_XPATH);
        return getNumeroCotacaoResidencia().getText().replaceAll("\\D+", "");
    }

    @Step("Abrir PDF de COTAÇÃO")
    public void clickPDFCotacao(int window) {
        click(BUTTON_SAVE_DOC_QUOTATION0_ID);
        waitForNumberOfWindows(window);
        changeToNextWindow();
        NoDriverUtils.await(3);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Referência Cotação' com '{0}'")
    public void inputReferenciaCotacao(String cotacao) {
        typeText(INPUT_REFERENCIA_COTACAO_ID, cotacao, true);
        makeScreenshot();
    }

    public String getMensagemErroTxt() {
        return getMensagemErro().getText();
    }

    private WebElement getCoberturaPorNome(String nomeCobertura) {
        return driver.findElement(By.xpath(String.format(COBERTURA_XPATH, nomeCobertura)));
    }

    private WebElement getQuotationAlert() {
        return driver.findElement(By.xpath(ALERT_XPATH));
    }

    public WebElement getNumeroCotacaoResidencia() {
        return driver.findElement(By.xpath(CHECK_QUOTATION_SAVED_XPATH));
    }

    public WebElement getValorAVistaCompacto() {
        return driver.findElement(By.id("modalidad_0_0_primaRecibo"));
    }

    public WebElement getValorAVistaAmpliado1() {
        return driver.findElement(By.id("modalidad_1_0_primaRecibo"));
    }

    public WebElement getValorAVistaAmpliado2() {
        return driver.findElement(By.id("modalidad_2_0_primaRecibo"));
    }

    public WebElement getValorAVistaEspecial() {
        return driver.findElement(By.id("modalidad_3_0_primaRecibo"));
    }

    private WebElement getMensagemErro() {
        return driver.findElement(By.xpath(TXT_MENSAGEM_ERRO_XPATH));
    }
}

