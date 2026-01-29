package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.util.logging.Logger;

public class DatosBasicosPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String DIAS_PAGO_TEXTBOX_ID = "DatosGeneralesBean$diaPago";
    private static final String TOMADOR_TEXTBOX_ID = "IntervinientesBean$nifTomador_doc";
    private static final String CONDUTOR_TEXTBOX_ID = "IntervinientesBean$nifConductor_doc";
    private static final String LMI_GLOBAL_TEXTBOX_ID = "DatosFlotaBean$lmiGlobal";
    private static final String OK_BUTTON_ID = "o_3";
    private static final String ACEITAR_BUTTON_ID = "o_3";
    private static final String OPTION_ISENCAO_IOF = "//input[@name='DatosImpuestosBean$exentoIva' and @value='%s']";
    private static final String NOME_TOMADOR_TEXTBOX_ID = "IntervinientesBean$nomFigTomadorValue";
    private static final String NOME_CONDUTOR_TEXTBOX_ID = "IntervinientesBean$nomFigConductorValue";
    private static final String INPUT_CEP_ID = "EnderecoRiscoGFABean$cepPropriedade";
    private static final String DDM_TIPO_PAGO_ID = "DatosGeneralesBean$primRecibo";
    private static final String FINALIZACION_VIGENCIA_TEXTBOX_ID = "DatosGeneralesBean$fechaTerm";
    private static final String DDM_CATEGORIA_RIESGO_FAMILIA_ID = "CategoriaRiesgoBean$famCatRiesgo";
    private static final String INPUT_LMG_ID = "PartidasGrupoBean$lineasPartidas[0].capital";
    private static final String INPUT_IS_TOTO_ID = "PartidasGrupoBean$lineasPartidas[1].capital";
    private static final String CONTINUAR_BUTTON_ID = "BotoneraCapBean$btnCalcular";
    private static final String DDM_TIPO_PERIODO_ID = "DatosGeneralesBean$tipoPeriodo";
    private static final String DDM_TIPO_SEGURO4_ID = "DadosGeraisEmpPmeBean$tipoSeguro";


    //LOCATORS - XPATH
    private static final String AVANZAR_BUTTON_XPATH = "//div[@class='footerButton'][contains(text(),'Avançar')]";
    private static final String AVANCAR_BUTTON_XPATH = "//button[contains(text(),'Avançar')]";
    private static final String TEXT_ITEMS_SEGURAVEIS_XPATH = "//td[text()='ITENS SEGURÁVEIS']";
    private static final String CHECK_DBASICOS_PAGE_XPATH = "//*[text()='Proposta/Item']";
    private static final String CHECK_COTACAOCAMINHAO_PAGE_XPATH = "//*[text()='Preencha os dados do Segurado e Condutor']";
    private static final String CUSTEIO_RADIO_BUTTON_XPATH = "//input[contains(@id, 'SubvencaoGFABean$custProvCredito')";
    private static final String CHECK_DADOS_FROTA_PAGE_XPATH = "//td[text()='DADOS FROTA']";
    private static final String TOMADOR_TITLE_XPATH = "//td[contains(text(),'Para este Tomador, utilizar o fluxo de renovação')]";
    private static final String ALERTA_COTACAO_EXISTENTE_XPATH = "//td[contains(text(),'Já existe')]";
    private static final String ALERTA_FORA_POLITICA_ACEITACAO_XPATH = "//td[contains(text(),'Fora da política de aceitação')]";
    private static final String ALERTA_VERIFIQUE_EMAIL_XPATH = "//td[contains(text(),'Verifique se o email')]";
    private static final String ALERTA_TXT_XPATH = "//td/b[contains(text(),'Alerta')]";
    private static final String ALERTA_ERRO_TXT_XPATH = "//tr[@class='rowAppErrorTextBlock']";
    private static final String ALERTA_ERRO2_TXT_XPATH = "//tr[@class='rowAppErrorInfoTextBlock']";
    private static final String CAMINHAO_USO_PROPRIO_OPTION_XPATH = "//option[contains(text(),'Caminhão - Uso Própr')]";
    private static final String CNPJ_SEGURO_TEXTBOX_XPATH = "//input[@id='IntervinientesBean$nifTomador_doc']";
    private static final String OPTION_CATEGORIA_RISCO_XPATH = "//option[contains(text(), '%s')]";
    private static final String OPTION_TIPO_SEGURO_XPATH = "//option[@title='Seguro Novo']";


    //Logger
    private static final Logger logger = Logger.getLogger(DatosBasicosPage.class.getName());

    public DatosBasicosPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDBasicosPageDisplayed() {
        return isElementVisible(CHECK_DBASICOS_PAGE_XPATH);
    }

    public boolean isTomador() {
        return isElementVisible(TOMADOR_TITLE_XPATH);
    }

    public boolean isAlertaErroDisplayed() {
        return isElementVisible(ALERTA_ERRO_TXT_XPATH, 4) ||
                isElementVisible(ALERTA_ERRO2_TXT_XPATH, 4);
    }

    @Step("Se inroduce los Dias de Pago")
    public void inputDiasPago() {
        inputDiasPago("1");
    }

    @Step("Preenche o dia de pagamento com: '{0}'")
    public void inputDiasPago(String dias) {
        acceptAlert(2);
        typeText(DIAS_PAGO_TEXTBOX_ID, dias, true);
        makeScreenshot();
    }

    @Step("Preencher o LMI Global {0}")
    public void inputLMIGlobal(String lmi) {
        typeText(LMI_GLOBAL_TEXTBOX_ID, lmi, true);
        makeScreenshot();
    }

    @Step("Se introduce la fecha Finalizacion Vigencia: mes siguiente {0}")
    public void inputFinalizacionVigencia(String date) {
        typeText(FINALIZACION_VIGENCIA_TEXTBOX_ID, date, true);
        makeScreenshot();
    }

    @Step("Preencher o campo CPF/CNPJ do Tomador {0}")
    public void inputDocTomador(String doc) {
        typeText(TOMADOR_TEXTBOX_ID, doc, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Segurado Isento do IOF' com {0}")
    public void selectIsencaoIOF(String option) {
        if (option.contains ("S")) {
            option = "S";
        }
        else {
            option = "N";
        }
            click(String.format(OPTION_ISENCAO_IOF, option));
        makeScreenshot();
    }

    @Step("Preencher o campo CPF/CNPJ do Condutor {0}")
    public void inputDocCondutor(String doc) {
        click("//input[@id = 'IntervinientesBean$esConductor2' and @value = 'N']");
        typeText(CONDUTOR_TEXTBOX_ID, doc, true);
        makeScreenshot();
    }

    @Step("Preencher o campo Cep: {0}")
    public void inputCep(String doc) {
        typeText(INPUT_CEP_ID, doc, true);
        makeScreenshot();
    }

    @Step("Preencher o campo Nome do Tomador {0}")
    public void inputNomeTomador(String name) {
        typeText(NOME_TOMADOR_TEXTBOX_ID, name, true);
        makeScreenshot();
    }

    @Step("Preencher o campo Nome do Condutor {0}")
    public void inputNomeCondutor(String name) {
        typeText(NOME_CONDUTOR_TEXTBOX_ID, name, true);
        makeScreenshot();
    }

    @Step("Seleciona a opção '{0}' no campo 'Forma de Pagamento'")
    public void selectFormaPagamento(String option) {
        selectOptionDDM(DDM.SEA, DDM_TIPO_PAGO_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la categoria riesgo familia condominio '{0}'")
    public void selectFamiliaCondominioResidencial(String option) {
        selectOptionDDM(DDM.SEA, DDM_CATEGORIA_RIESGO_FAMILIA_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção {0} no combo 'Categoria de risco'")
    public void selectCategoriaRisco(String option) {
        click(String.format(OPTION_CATEGORIA_RISCO_XPATH, option));
        makeScreenshot();
    }

    @Step("Clicar no botão 'OK'")
    public void clickOkButton() {
        click(OK_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Para 'Custeio proveniente de Crédito Rural?', clicar na opção '{0}'")
    public void clickCusteioDeCreditoRuralButtonPorValue(String value) { //Use 'S' ou 'N' para 'value'
        String xpathFormatado = CUSTEIO_RADIO_BUTTON_XPATH + " and @value='" + value + "']";
        click(xpathFormatado, true, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvanzarButton() {
        NoDriverUtils.await(2);
        click(AVANZAR_BUTTON_XPATH);
        if (isElementPresent(ALERTA_COTACAO_EXISTENTE_XPATH, 2) || isElementPresent(ALERTA_FORA_POLITICA_ACEITACAO_XPATH, 2)) {
            click(AVANZAR_BUTTON_XPATH);
        }
        makeScreenshot();
    }

    @Step("Clicar no botão 'Aceitar'")
    public void clickAceitarButton() {
        click(ACEITAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarButton() {
        click(AVANCAR_BUTTON_XPATH, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvanzarButton1() {
        click(AVANZAR_BUTTON_XPATH);
        acceptAlert(3);
        makeScreenshot();
        if (isElementPresent(ALERTA_VERIFIQUE_EMAIL_XPATH, 4)) {
            clickByJS(AVANZAR_BUTTON_XPATH);
            acceptAlert(3);
        }
    }


    @Step("Se introduce LMG/Acumulo: {0}")
    public void inputLMGgrupo(String value) {
        typeText(INPUT_LMG_ID, value, true);
    }

    @Step("Se introduce IS total: {0}")
    public void inputIsTotalgrupo(String value) {
        typeText(INPUT_IS_TOTO_ID, value, true);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Continuar'")
    public void clickContinuarButton() {
        click(CONTINUAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar a opção {0} no combo 'Tipo de Período'")
    public void selectTipoPeriodo(String option) {
        selectOptionDDM(DDM.SEA, DDM_TIPO_PERIODO_ID, option);
        clickByJS(DDM_TIPO_PERIODO_ID);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} no combo 'Tipo de Seguro'")
    public void selectTipoSeguro(String option) {
        click(String.format(OPTION_TIPO_SEGURO_XPATH, option));
        makeScreenshot();
    }

    //Empresa PME [2024]
    @Step("Selecionar a opção {0} no combo 'Tipo de Seguro'")
    public void selectTipoSeguro4(String option) {
        selectOptionDDM(DDM.SEA, DDM_TIPO_SEGURO4_ID, option);
        clickByJS(DDM_TIPO_SEGURO4_ID);
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    //WEBELEMENTS

    private WebElement getDocSeguradoInput() {
        return driver.findElement(By.xpath(CNPJ_SEGURO_TEXTBOX_XPATH));
    }
}
