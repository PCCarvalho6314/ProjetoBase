package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class SeleccionRamoPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String TIPO_SELECCION_ALTA_ID = "tipoDeSeleccion1";
    private static final String TIPO_SELECCION_OTRAS_ID = "tipoDeSeleccion2";
    private static final String INPUT_POLICY_ID = "polSusep_SUSEP";
    private static final String INPUT_REASON_ID = "motivo";
    private static final String DDM_GROUP_ID = "agrupacion";
    private static final String DDM_PRODUCT_ID = "producto";
    private static final String INPUT_RAMO_ID = "ramo";
    private static final String SUBPRODUCTO_ID = "subproducto2";
    private static final String MODALIDAD_ID = "modalidad";
    private static final String BUSQUEDA_LUPA_ID = "imagenLupa";
    private static final String MEDIADOR_ID = "mediador";
    private static final String DDM_OPERATIVA_ID = "codOperativa";
    private static final String MONEDA_ID = "moneda";
    private static final String SELECCION_BUTTON_ID = "btnSeleccionar";
    private static final String POLIZAS_BLOQUEADAS_BUTTON_ID = "pBloqueadas";
    private static final String SELECCION_OPERATIVA_BUTTON_ID = "btnSeleccionOperativa";
    private static final String BUTTON_NEXT_MOTIVO_ID = "btnModificacion";
    private static final String BUTTON_PESQUISAR_COTACOES_ID = "pArchivados";

    //LOCATORS - XPATH
    private static final String BUTTON_HOME_XPATH = "//*[@title='Início']";
    private static final String CHECK_MENU_PRODUCAO_XPATH = "//*[contains(text(), 'Menu Produção')]";
    private static final String CHECK_SELECAO_DADOS_XPATH = "//td[text()='SELEÇÃO DE DADOS']";
    private static final String RAMOS_SUBPRODUCTO_CERO = "1221,101,308";
    private static final String RAMOS_SUBPRODUCTO_UNO = "2004,2001, 609";
    private static final String RAMOS_MODALIDAD_UNO = "2002,2007,2008";
    private static final String BUTTON_AVANCAR_XPATH = "//*[contains(@id, 'Aceptar') or contains(@id, 'o_3') and contains(text(), 'Avançar') or contains(text(), 'Aceitar')]";


    public SeleccionRamoPage(WebDriver driver) {
        super(driver);
    }

    //CHECKS
    public boolean isMenuProducaoDisplayed() {
        NoDriverUtils.await(3);
        waitForVisible(CHECK_MENU_PRODUCAO_XPATH);
        return isElementVisible(CHECK_MENU_PRODUCAO_XPATH);
    }

    public boolean isSelecaoDadosDisplayed() {
        return isElementVisible(CHECK_SELECAO_DADOS_XPATH, 3);
    }

    //ACTIONS
    @Step("Selecionar a Operativa: {0}")
    public void selectOperativa(String option) {
        waitForClickable(DDM_OPERATIVA_ID);
        selectOptionDDM(DDM.SEA, DDM_OPERATIVA_ID, option);
        click(SELECCION_OPERATIVA_BUTTON_ID);
        acceptAlert(1);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Alta Poliza Multi Situacion'")
    public void seleccionarAltaPoliza() {
        click(SELECCION_BUTTON_ID);
        selectOptionDDM(DDM.SEA, DDM_OPERATIVA_ID, "Emitir apólice");
        click(SELECCION_OPERATIVA_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Alta Poliza Multi Situacion'")
    public void seleccionarAltaPolizaMultiSituacion() {
        click(SELECCION_BUTTON_ID);
        selectOptionDDM(DDM.SEA, DDM_OPERATIVA_ID, "Emitir Apólice de Multisituação");
        click(SELECCION_OPERATIVA_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar o Ramo {0} e o Mediador {1}")
    public void inputRamoMediador(String ramo, String mediador) {
        waitForVisible((MEDIADOR_ID));
        clickEmissaoRadioButton();
        inputRamo(ramo);
        inputSubproductoWhenApplicable(ramo);
        inputModalidadWhenApplicable(ramo);
        clickBusquedaLupaButton();
        selectMonedaWhenApplicable(ramo);
        inputMediador(mediador);
        waitForClickable(SELECCION_BUTTON_ID);
        scrollToElement(SELECCION_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar o Ramo {0}, Modalidade {1} e o Mediador {2}")
    public void inputRamo_Modadalidade_Mediador(String ramo, String numModadidade, String mediador) {
        waitForVisible((MEDIADOR_ID));
        clickEmissaoRadioButton();
        inputRamo(ramo);
        inputSubproductoWhenApplicable(ramo);
        inputModalidade(numModadidade);
        clickBusquedaLupaButton();
        selectMonedaWhenApplicable(ramo);
        inputMediador(mediador);
        waitForClickable(SELECCION_BUTTON_ID);
        scrollToElement(SELECCION_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar o Agrupamento {0}, o Produto {1} e o Mediador {2}")
    public void selectGrupoRamoMediador(String group, String product, String mediador) {
        waitForVisible((MEDIADOR_ID));
        clickEmissaoRadioButton();
        selectGroup(group);
        selectProduct(product);
        inputMediador(mediador);
        click(SELECCION_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar Agrupamento {0}, Produto {1} e Mediador {2}")
    public void selectGrupoRamoMediador1(String group, String product, String mediador) {
        waitForVisible((MEDIADOR_ID));
        clickEmissaoRadioButton();
        selectOptionDDM(DDM.SEA, DDM_GROUP_ID, group);
        NoDriverUtils.await(2);
        selectOptionDDM(DDM.SEA, DDM_PRODUCT_ID, product);
        NoDriverUtils.await();
        inputMediador(mediador);
        click(SELECCION_BUTTON_ID);
        NoDriverUtils.await();
        makeScreenshot();
    }

    @Step("Se introducen Subproductos {0}")
    public void inputSubproductoWhenApplicable(String ramo) {
        if (RAMOS_SUBPRODUCTO_CERO.contains(ramo) || RAMOS_SUBPRODUCTO_UNO.contains(ramo)) {
            waitForClickable(SUBPRODUCTO_ID);
            getSubProductoInput().clear();
            inputSubproducto(RAMOS_SUBPRODUCTO_CERO.contains(ramo) ? "0" : "1");
        }
        makeScreenshot();
    }

    @Step("Se introduce la Modalidad {0}")
    public void inputModalidadWhenApplicable(String ramo) {
        if (RAMOS_MODALIDAD_UNO.contains(ramo)) {
            waitForClickable(MODALIDAD_ID);
            scrollToElement(MODALIDAD_ID);
            inputModalidad("1");
        }
        makeScreenshot();
    }

    public void inputModalidade(String numModalidade) {
        waitForClickable(MODALIDAD_ID);
        scrollToElement(MODALIDAD_ID);
        inputModalidad(numModalidade);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Moneda' para el ramo {0}")
    public void selectMonedaWhenApplicable(String ramo) {
        if (ramo.contains("2007") || ramo.contains("2008")) {
            click(MONEDA_ID);
            makeScreenshot();
        }
    }

    @Step("Preencher o campo 'Ramo / Modalidade' com {0}")
    public void inputRamo(String ramo) {
        waitForClickable(INPUT_RAMO_ID);
        scrollToElement(INPUT_RAMO_ID);
        typeText(INPUT_RAMO_ID, ramo, false);
        makeScreenshot();
    }

    @Step("Se introduce el Mediador {0}")
    private void inputMediador(String mediador) {
        typeText(MEDIADOR_ID, mediador, true);
        makeScreenshot();
    }

    @Step("Preencher o subproduto: {0}")
    public void inputSubproducto(String subproducto) {
        typeText(SUBPRODUCTO_ID, subproducto, true);
        makeScreenshot();
    }

    @Step("Se introduce la Modalidad {0}")
    public void inputModalidad(String modalidad) {
        typeText(MODALIDAD_ID, modalidad, false);
        makeScreenshot();
    }

    @Step("Escolher a opção 'Emissão' no campo 'Tipos de Seleção'")
    public void clickEmissaoRadioButton() {
        waitForClickable(TIPO_SELECCION_ALTA_ID);
        scrollToElement(TIPO_SELECCION_ALTA_ID);
        clickByJS(TIPO_SELECCION_ALTA_ID);
        if (!isElementPresent("//*[@id='agrupacion'][@class='combo']", 5)) {
            click(TIPO_SELECCION_ALTA_ID);
        }

        makeScreenshot();
    }

    @Step("Escolher a opção 'Outras Operações' no campo 'Tipos de Seleção'")
    public void clickOutrasOperacoesRadioButton() {
        click(BUTTON_HOME_XPATH);

        waitForClickable(TIPO_SELECCION_OTRAS_ID);
        scrollToElement(TIPO_SELECCION_OTRAS_ID);
        clickByJS(TIPO_SELECCION_OTRAS_ID);
        if (!getPolicyInput().isEnabled()) {
            click(TIPO_SELECCION_OTRAS_ID);
        }
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Búsqueda Lupa'")
    public void clickBusquedaLupaButton() {
        click(BUSQUEDA_LUPA_ID);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Pólizas Búsquedas'")
    public void clickPolizasBloqueadasButton() {
        click(POLIZAS_BLOQUEADAS_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Selecionar'")
    public void clickSelecionar() {
        click(SELECCION_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar o Agrupamento {0}")
    private void selectGroup(String group) {
        selectOptionDDM(DDM.SEA, DDM_GROUP_ID, group);
        makeScreenshot();
    }

    @Step("Selecionar o Produto {0}")
    private void selectProduct(String product) {
        selectOptionDDM(DDM.SEA, DDM_PRODUCT_ID, product);
        makeScreenshot();
    }

    @Step("Se introduce la póliza: {0}")
    public void inputPolicy(String policy) {
        typeText(INPUT_POLICY_ID, policy, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Motivo' com '{0}'")
    public void inputMotivo(String text) {
        typeText(INPUT_REASON_ID, text, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarMotivo() {
        click(BUTTON_NEXT_MOTIVO_ID);
        acceptAlert(1);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR_XPATH);
        makeScreenshot();
    }

    @Step("Clicar em 'Pesquisar Cotações'")
    public void clickPesquisarCotacoes() {
        waitForClickable(BUTTON_PESQUISAR_COTACOES_ID);
        scrollToElement(BUTTON_PESQUISAR_COTACOES_ID);
        clickByJS(BUTTON_PESQUISAR_COTACOES_ID);
        makeScreenshot();
    }

    //AuxMethod
    @Step("Check Token")
    public void checkToken() {
        if (isElementVisible("//*[contains(text(),'pode validar o token')]")) {
            throw new SkipException("Error no se pudo validar token detected");
        }
    }

    //WEB ELEMENTS
    private WebElement getSubProductoInput() {
        return driver.findElement(By.id(SUBPRODUCTO_ID));
    }

    private WebElement getPolicyInput() {
        return driver.findElement(By.id(INPUT_POLICY_ID));
    }
}

