package pages.trenproduccion;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmisionFinalPage extends PageObjectBase {


    public EmisionFinalPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - ID
    private static final String NUMERO_PROPOSTA_ID = "CabeceraBean$sucursal";
    private static final String NUMERO_APOLICE_ID = "CabeceraBean$sucursalSUSEP";
    private static final String EMITIR_BUTTON_ID = "btnAceptar";
    private static final String AVANCAR_BUTTON_ID = "btnValida";
    private static final String PDF_PROPOSTA_CONDOMINIO_ID = "btnProposal";
    private static final String PDF_PROPOSTA_CONDOMINIO2_ID = "doc2";


    //LOCATORS - XPATH
    private static final String POLIZA_EMITIDA_XPATH = "//*[contains(text(),'APÓLICE CORRETAMENTE EMITIDA')]";
    private static final String POLIZA_EMITIDA2_XPATH = "//*[contains(text(),'EMISSÃO REALIZADA')]";
    private static final String PENDIENTE_ACEPTACION_XPATH = "//td[contains(text(),'PEDIDO PENDENTE')]";
    private static final String ALERTA_TEXT_XPATH = "//td[contains(text(),'Alerta')]";
    private static final String EMISION_XPATH = "//p[contains(text(),'Apólice emitida com sucesso!')]";
    private static final String PDF_PROPOSTA_XPATH = "//label[@id='doc0'  and @class='labelAnchor']";
    private static final String PDF_PROPOSTA_2_XPATH = "//a[@onclick = \"loadPdf('PdfProposta')\" or @onclick=\"imprimirDoc(6)\"]"; //precisa das barras invertidas, favor não apagar
    private static final String TEXT_EQUIPAMIENTOS_EMISSION_XPATH = "//*[@class='label-text-title']";
    private static final String TEXT_NUM_POLIZA_EPAC_XPATH = "//*[contains(text(),'Número da proposta')]";
    private static final String TEXT_NUM_PROPOSTA_EPAC_XPATH = "//div[@id='body_sectionPaginaCompleta']//div[1]/p[2]";
    private static final String TEXT_NUM_APOLICE_ENDOSSO_EPAC_XPATH = "//label[@id='proposalNum']";
    private static final String TEXT_NUM_APOLICE_SUCESSO_EPAC_XPATH = "//p[contains(text(), 'Número de Apólice') or contains(text(), 'Número da apólice')]/following-sibling::p[1]";
    private static final String TEXT_NUM_PROPOSTA__SUCESSO_EPAC_XPATH = "//*[contains(text(), 'Número da Proposta') or contains(text(), 'Número de proposta')]/following-sibling::p[1]";
    private static final String CHECK_BLOQUEO_EPAC_XPATH = "//*[contains(text(),'Atenção: A proposta gerou bloqueio(s)')]";
    private static final String EFETUAR_SEGURO_XPATH = "//p[contains(text(),'Obrigado, falta pouco para efetuar a')]";
    private static final String PDF_APOLICE_XPATH = "//label[@id='doc0' and @class='labelAnchor']";
    private static final String NUM_PROPOSTA_XPATH = "//*[@id=\"headerPolicyNumber\"]";
    private static final String NUM_APOLICE_XPATH = "//*[@id=\"headerSusepPolicy\"]";


    //CHECKS
    public boolean isPolizaEmitida() {
        waitForJSandJqueryFinish();
        waitForInvisible("//*[contains(text(),'process')]");
        return isElementVisible(POLIZA_EMITIDA_XPATH, 10);
    }

    public boolean isPolizaEmitida3() {
        return isElementVisible(POLIZA_EMITIDA2_XPATH, 10);
    }

    public boolean isPolizaEmitidaEpac() {
        waitForJSandJqueryFinish();
        return isElementVisible(POLIZA_EMITIDA_XPATH, 5);
    }

    public boolean isPolizaEmitida2() {
        waitForJSandJqueryFinish();
        return isElementVisible(TEXT_EQUIPAMIENTOS_EMISSION_XPATH, 10);
    }

    public boolean isPendenteAceitacao() {
        waitForJSandJqueryFinish();
        waitForInvisible("//*[contains(text(),'process')]");
        return isElementVisible(PENDIENTE_ACEPTACION_XPATH, 10);
    }

    public boolean isPolizaBloqueadaEpac() {
        waitForJSandJqueryFinish();
        return isElementVisible(CHECK_BLOQUEO_EPAC_XPATH, 10);
    }

    public boolean isPDFOpen() {
        NoDriverUtils.await(3);
        return driver.getCurrentUrl().contains("ispdf=true");
    }

    public boolean isPDFPropostaOpen() {
        return driver.getCurrentUrl().contains("doc=PdfProposta");
    }

    public Boolean isDatosEmisionDisplayed() {
        return isElementVisible(EMISION_XPATH, 10);
    }

    public Boolean isEfetuarSeguroDisplayed() {
        return isElementVisible(EFETUAR_SEGURO_XPATH, 10);
    }

    public Boolean isApoliceNumberDisplayed() {
        return isElementVisible(NUM_APOLICE_XPATH, 5);
    }

    public Boolean isApoliceNumberDisplayed_Intranet() {
        return isElementVisible(NUMERO_APOLICE_ID, 10);
    }

    //Actions
    @Step("Se hace click en el botón Emitir")
    public void clickEmitirButton() {
        click(EMITIR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarButton() {
        NoDriverUtils.await(2);
        click(AVANCAR_BUTTON_ID, true);
        makeScreenshot();
    }

    @Step("Abrir PDF da Proposta")
    public void clickAbrirPDFProposta() {
        click(PDF_PROPOSTA_XPATH, true, true);
        changeToNextWindow();
        makeScreenshot();
    }

    @Step("Abrir PDF da Proposta")
    public void clickAbrirPDFPropostaEpac() {
        NoDriverUtils.await();
        click(PDF_PROPOSTA_2_XPATH);
        changeToNextWindow();
        makeScreenshot();
    }

    @Step("Abrir PDF da Proposta")
    public void clickAbrirPDFPropostaEpac2() {
        NoDriverUtils.await();
        click(PDF_PROPOSTA_2_XPATH);
        makeScreenshot();
    }

    @Step("Abrir PDF da Proposta")
    public void clickAbrirPDFPropostaCondominio() {
        click(PDF_PROPOSTA_CONDOMINIO_ID);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Abrir PDF da Proposta")
    public void clickAbrirPDFPropostaCondominio2() {
        click(PDF_PROPOSTA_CONDOMINIO2_ID);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Abrir PDF da Apólice")
    public void clickAbrirPDFApolice() {
        NoDriverUtils.await(2);
        click(PDF_APOLICE_XPATH, true);
        waitForNumberOfWindows(3);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Abrir PDF da Apólice")
    public void abrirPDFApolice() {
        click(PDF_APOLICE_XPATH);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Gerar Nº de PROPOSTA")
    public void exibeNumeroProposta() {
        String numeroPropostaAuto = getNumProposta().getText();
        Allure.step("Proposta nº: " + numeroPropostaAuto);
    }

    @Step("Gerar Nº de PROPOSTA")
    public void exibeNumeroPropostaBPD() {
        String numeroProposta = getNumPropostaFormatBPD();
        Allure.step("Proposta nº: " + numeroProposta);
    }

    @Step("Gerar Nº de PROPOSTA")
    public void exibeNumeroPropostaEpac() {
        String numeroProposta = getNumProposta2Epac().getText();
        Allure.step("Proposta nº: " + numeroProposta);
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApoliceEndosso() {
        String numeroApoliceEndosso = getNumApoliceEndosso().getText();
        Allure.step("Apólice nº: " + numeroApoliceEndosso);
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApoliceEndossoSucesso() {
        String numeroApoliceEndosso = getNumApoliceEndossoSucesso().getText();
        Allure.step("Apólice nº: " + numeroApoliceEndosso);
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApoliceEndossoCondominioSucesso() {
        String numeroApoliceEndosso = getNumApolice().getText();
        Allure.step("Apólice nº: " + numeroApoliceEndosso);
    }

    @Step("Gerar Nº de PROPOSTA")
    public void exibeNumeroPropostaIntranet() {
        String numeroProposta = getNumPropostaIntranet();
        Allure.step("Proposta nº: " + numeroProposta);
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApoliceIntranet() {
        String numeroApolice = getNumApoliceIntranet();
        Allure.step("Apólice nº: " + numeroApolice);
    }

    public boolean isAlertaDisplayed() {
        return isElementVisible(ALERTA_TEXT_XPATH, 5);
    }

    //AUX METHODS
    public String getNumPropostaIntranet() {
        return getPropostaText().getText().split("/")[0].trim();
    }

    public String getNumApoliceIntranet() {
        return getApoliceText().getText().split("/")[0].trim();
    }

    public String getNumPropostaFormatBPD() {
        return getNumPropostaBPD().getText().split("/")[0].trim();
    }

    public WebElement getNumProposta() {
        return driver.findElement(By.xpath(TEXT_NUM_PROPOSTA_EPAC_XPATH));
    }

    public WebElement getNumProposal() {
        return driver.findElement(By.xpath(NUM_PROPOSTA_XPATH));
    }

    public WebElement getNumApolice() {
        return driver.findElement(By.xpath(NUM_APOLICE_XPATH));
    }

    public WebElement getNumPropostaBPD() {
        return driver.findElement(By.xpath(TEXT_EQUIPAMIENTOS_EMISSION_XPATH));
    }

    public WebElement getNumProposta2Epac() {
        return driver.findElement(By.xpath(TEXT_NUM_PROPOSTA__SUCESSO_EPAC_XPATH));
    }

    public WebElement getNumApoliceEndosso() {
        return driver.findElement(By.xpath(TEXT_NUM_APOLICE_ENDOSSO_EPAC_XPATH));
    }

    public WebElement getNumApoliceEndossoSucesso() {
        return driver.findElement(By.xpath(TEXT_NUM_APOLICE_SUCESSO_EPAC_XPATH));
    }

    public String getNumPolizaEpac() {
        String str = getPolizaEpacText().getText();
        String regex = "\\d+";
        Matcher m = Pattern.compile(regex).matcher(str);
        String num = "";
        while (m.find()) {
            num = m.group();
        }
        return num;
    }

    //WEBELEMENTS
    private WebElement getPropostaText() {
        return driver.findElement(By.id(NUMERO_PROPOSTA_ID));
    }

    private WebElement getApoliceText() {
        return driver.findElement(By.id(NUMERO_APOLICE_ID));
    }

    private WebElement getPolizaEpacText() {
        return driver.findElement(By.xpath(TEXT_NUM_POLIZA_EPAC_XPATH));
    }
}
