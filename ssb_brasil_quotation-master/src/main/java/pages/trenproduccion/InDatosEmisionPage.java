package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.web.page.PageObjectBase;

import java.util.ArrayList;

public class InDatosEmisionPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String DDM_BENEF_ENCASODE_ID = "BeneficiariosNomBean$beneficiarioNom.enCaso";
    private static final String DDM_TIPO_BENEF_ID = "BeneficiariosNomBean$beneficiarioNom.beneficiarioSeguro";
    private static final String DDM_TIPO_BENEF2_ID = "BeneficiariosNomBean$beneficiarioNom.beneficiarioSegNoIde";
    private static final String DDM_DESTINATARIO_ID = "DocumentacionBean$envioDoc";
    private static final String DDM_ENDERECO_ENTREGA_ID = "DocumentacionBean$destinatario";
    private static final String BENEFICIARIO_NIF_TEXTBOX_ID = "BeneficiariosNomBean$nifBeneficiario_doc";
    private static final String BENEFICIARIO_PORCENTAJE_TEXTBOX_ID = "BeneficiariosNomBean$beneficiarioNom.porcBeneficiario";
    private static final String ADD_BENEFICIARIO_BUTTON_ID = "btAddOne";
    private static final String AVANZAR_BUTTON_XPATH = "//div[@class='footerButton'][contains(text(),'Avançar')]";
    private static final String AVANZAR1_BUTTON_XPATH = "//div[@id='o_3']";
    private static final String ACEPTAR_BUTTON_ID = "btnAceptar";
    private static final String DDM_TYPE_ACCOUNT_ID = "DatosBancariosBean$ccc_cuenta_typeAccount";
    private static final String DDM_BANK_ID = "DatosBancariosBean$ccc_cuenta_vBank";
    private static final String INPUT_OFFICE_ID = "DatosBancariosBean$ccc_cuenta_vOffice";
    private static final String INPUT_NUM_ACCOUNT_ID = "DatosBancariosBean$ccc_cuenta_vAccNumber";
    private static final String INPUT_NUM_CONTROL_ID = "DatosBancariosBean$ccc_cuenta_vDigCtrlAcc";
    private static final String BUTTON_VERIFY_ID = "DatosBancariosBean$ccc_cuenta_btnConfirm";
    private static final String INPUT_DIA_PAGAMENTO_ID = "DatosBancariosBean$diaPago";
    private static final String INPUT_DOC_TOMADOR_ID = "DatosBancariosBean$ccc_cuenta_IDNum_doc";

    //LOCATORS - XPATH
    private static final String WARNING_RESTRICCION_XPATH = "//td[contains(text(),'Restrição Técnica para o segurado')]";
    private static final String DATOS_GENERALES_TITLE_XPATH = "//td[text()='DADOS GERAIS']";
    private static final String BENEFICIARIOS_TITLE_XPATH = "//td[text()='BENEFICIÁRIOS']";
    private static final String SISTEMA_INDISPONIBLE_TITLE_XPATH = "//td[contains(text(),'Sistema indisponível')]";
    private static final String DATANASCIMIENTO_SEXO_ALTERADOS_TITLE_XPATH = "//td[contains(text(),'Data Nascimento e Sexo')]";
    private static final String CHECK_DADOS_EMISSAO_XPATH = "//td[@id='13' and contains(text(), 'D. Emissão')]";
    private static final String RESTRINCCION_TECNICA_TITLE_XPATH = "//td[contains(text(),'Técnica para o segurado')]";
    private static final String D_EMISSAO_TITLE_XPATH = "//*[contains(text(), 'D. Emissão') and @id='cellAppPromptHeaderArea']";
    private static final String INCLUIR_BENEF_RADIO_BUTTON_XPATH = "//*[@name='BeneficiariosNomBean$identifBenefNom'";
    private static final String TITULAR_RADIO_BUTTON_XPATH = "//*[@name='DatosBancariosBean$esTitular'";
    private static final String AVANCAR_BUTTON_XPATH = "//*[contains(@id, 'Aceptar') or contains(@id, 'o_3') and contains(text(), 'Avançar') or contains(text(), 'Aceitar')]";

    public InDatosEmisionPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    public boolean isDadosEmissaoDisplayed() {
        return isElementVisible(CHECK_DADOS_EMISSAO_XPATH);
    }

    public boolean isDadosGeraisDisplayed() {
        return isElementVisible(DATOS_GENERALES_TITLE_XPATH, 10);
    }

    // Public
    @Step("Para 'Incluir beneficiário', clicar na opção '{0}'")
    public void clickIncluirBeneficiarioRadioButtonPorValue(String value) { //Use 'S' ou 'N' para 'value'
        String xpathFormatado = INCLUIR_BENEF_RADIO_BUTTON_XPATH + " and @value='" + value + "']";
        waitForClickable(xpathFormatado);
        scrollToElement(xpathFormatado);
        getRadioButtonPorXpath(xpathFormatado).click();
        makeScreenshot();
    }

    @Step("Para 'É o Titular', clicar na opção '{0}'")
    public void clickTitularRadioButtonPorValue(String value) { //Use 'S' ou 'N' para 'value'
        String xpathFormatado = TITULAR_RADIO_BUTTON_XPATH + " and @value='" + value + "']";
        waitForClickable(xpathFormatado);
        scrollToElement(xpathFormatado);
        getRadioButtonPorXpath(xpathFormatado).click();
        makeScreenshot();
    }

    public void inputDatosBeneficiario(String nif) {
        WebElement element = driver.findElement(By.xpath("//*[@id='" + DDM_TIPO_BENEF_ID + "' or @id='" + DDM_TIPO_BENEF2_ID + "']"));

        selectOptionDDM(DDM.SEA, DDM_BENEF_ENCASODE_ID, "Sobrevivência");
        selectOptionDDM(DDM.SEA, String.valueOf(element), "Pessoa Física");

        typeText(BENEFICIARIO_NIF_TEXTBOX_ID, nif, true);
        typeText(BENEFICIARIO_PORCENTAJE_TEXTBOX_ID, "100", true);
        makeScreenshot();
    }

    @Step("Preencher os combos 'Em caso de', 'Tipo de beneficiário' e '%' ")
    public void inputDadosBeneficiario(String emCasoDe, String tipoBenef, String percentual) {
        WebElement element = driver.findElement(By.xpath("//*[@id='" + DDM_TIPO_BENEF_ID + "' or @id='" + DDM_TIPO_BENEF2_ID + "']"));

        selectOptionDDM(DDM.SEA, DDM_BENEF_ENCASODE_ID, emCasoDe);
        selectOptionDDM(DDM.SEA, String.valueOf(element), tipoBenef);
        typeText(BENEFICIARIO_PORCENTAJE_TEXTBOX_ID, percentual, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'CPF/CNPJ'")
    public void inputCPF_CNPJBeneficiario(String doc) {
        typeText(BENEFICIARIO_NIF_TEXTBOX_ID, doc, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Aceitar'")
    public void clickAceptarButton() {
        click(ACEPTAR_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    public void checkSystemUnavaliable() {

        ArrayList<String> errors = new ArrayList<>();
        errors.add(DATANASCIMIENTO_SEXO_ALTERADOS_TITLE_XPATH);
        errors.add(SISTEMA_INDISPONIBLE_TITLE_XPATH);
        errors.add(RESTRINCCION_TECNICA_TITLE_XPATH);
        errors.add(DATOS_GENERALES_TITLE_XPATH);

        for (String error : errors) {

            if (isElementVisible(error, 1)) {
                clickByJS(AVANZAR_BUTTON_XPATH);
                return;
            }
        }

        acceptAlert(1);
        makeScreenshot();
    }

    public boolean isDatosGenerales() {
        return isElementVisible("DocumentacionBean$idioma_label");
    }

    public boolean isD_Emissao() {
        return isElementVisible(D_EMISSAO_TITLE_XPATH, 8);
    }

    public boolean isBeneficiarios() {
        return isElementVisible(BENEFICIARIOS_TITLE_XPATH);
    }

    @Step("Selecionar a opção '{0}' no combo 'Destinatário'")
    public void selectDestinatario(String option) {
        selectOptionDDM(DDM.SEA, DDM_DESTINATARIO_ID, option);
        makeScreenshot();
    }

    @Step("Clicar em 'Avançar'")
    public void clickAvancar() {
        click(AVANCAR_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Endereço de entrega'")
    public void selectEnderecoEntrega(String option) {
        selectOptionDDM(DDM.SEA, DDM_ENDERECO_ENTREGA_ID, option);
        makeScreenshot();
    }

    @Step("Clicar no botão para 'Adicionar Beneficiario'")
    public void clickAddBeneficiarioButton() {
        click(ADD_BENEFICIARIO_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Avanzar'")
    public void clickAvanzarButton() {
        waitForClickable(AVANZAR_BUTTON_XPATH);
        scrollToElement(AVANZAR_BUTTON_XPATH);
        clickByJS(AVANZAR_BUTTON_XPATH);
        acceptAlert(2);
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Se compruba warning 'Restrição Técnica para o segurado'")
    public void checkRestriccionTecnica() {
        if (isElementVisible(WARNING_RESTRICCION_XPATH, 10)) {
            getAvanzarButton().click();
            waitForJSandJqueryFinish();
        }
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Avanzar'")
    public void clickAvanzar1Button() {
        waitForClickable(AVANZAR1_BUTTON_XPATH);
        scrollToElement(AVANZAR1_BUTTON_XPATH);
        clickByJS(AVANZAR1_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvanzarWhenVisibleButton() {
        if (isElementVisible(DATOS_GENERALES_TITLE_XPATH, 5) && getDatosGeneralesTitle().isDisplayed()) {
            click(AVANCAR_BUTTON_XPATH);
        }
        makeScreenshot();
    }

    @Step("Selecionar o tipo de conta {0}")
    public void selectTypeAccount(String option) {
        selectOptionDDM(DDM.SEA, DDM_TYPE_ACCOUNT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar o banco: {0}")
    public void selectBank(String option) {
        selectOptionDDM(DDM.SEA, DDM_BANK_ID, option);
        makeScreenshot();
    }
    @Step("Se hace click en verificar cuenta")
    public void inputDiaPagamento(String dia) {
        typeText(INPUT_DIA_PAGAMENTO_ID, dia, true);
        makeScreenshot();
    }

    @Step("Preencher o campo '' com '{0}'")
    public void inputDocTomador(String doc) {
        typeText(INPUT_DOC_TOMADOR_ID, doc, true);
        makeScreenshot();
    }


    private WebElement getAvanzarButton() {
        return driver.findElement(By.xpath(AVANZAR_BUTTON_XPATH));
    }

    private WebElement getDatosGeneralesTitle() {
        return driver.findElement(By.xpath(DATOS_GENERALES_TITLE_XPATH));
    }
    private WebElement getRadioButtonPorXpath(String xpathCompleto) {
        return driver.findElement(By.xpath(xpathCompleto));
    }
}


