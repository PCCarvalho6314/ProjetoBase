package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class DatosComunesPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String COMISIONES_ESPECIALES_BUTTON_ID = "btComisionesEsp";
    private static final String AVANZAR_BUTTON_ID = "btnAceptar";
    private static final String AVANCAR_BUTTON_XPATH = "//*[contains(@id, 'Aceptar') or contains(@id, 'o_3') and contains(text(), 'Avançar') or contains(text(), 'Aceitar')]";
    private static final String BUTTON_GESTION_CENTROS_ID = "btCentros";
    private static final String INPUT_CLIENT_ID = "ParamCentrosBean$cliente";
    private static final String BUTTON_SAVE_ID = "btGuardarSubPlCentro";
    private static final String INPUT_FINAL_DATE_ID = "ParamCentrosBean$fechaFinVigenciaCen";

    //LOCATORS - XPATHS
    private static final String RESSEGURO_TITLE_XPATH = "//td[text()='RESSEGURO/COSSEGURO']";
    private static final String TEXT_DATOS_COMISIONES_XPATH = "//td[text()='DADOS COMISSÕES ESPECIAIS']";
    private static final String BUTTON_EMITIR_XPATH = "//div[contains(text(),'Emitir')]";
    private static final String BUTTON_SEARCH_XPATH = "//*[@title='Pesquisa']";
    private static final String TEXT_LISTA_CENTROS_XPATH = "//td[text()='LISTA DE CENTROS']";
    private static final String INFORMACION_AVISOS_TITLE_XPATH = "//td[contains(text(),'INFORMAÇÃO DOS AVISOS E OCORRÊNCIAS')]";
    private static final String D_COMUNS_TITLE_XPATH = "//td[text()='D. Comuns']";
    private static final String ATENCION_TEXT_XPATH = "//*[contains(text(),'ATENÇÃO') or contains(text(), 'Atenção')]";


    public DatosComunesPage(WebDriver driver) {
        super(driver);
    }

    //CHECKS
    public boolean isDatosComunes() {
        return isElementVisible(RESSEGURO_TITLE_XPATH);
    }

    public boolean isComisionesDisplayed() {
        return isElementVisible(TEXT_DATOS_COMISIONES_XPATH);
    }

    public boolean isListaCentrosDisplayed() {
        return isElementVisible(TEXT_LISTA_CENTROS_XPATH);
    }

    public boolean isInformacoesAvisosDisplayed() {
        return isElementVisible(INFORMACION_AVISOS_TITLE_XPATH);
    }

    public boolean isDComunsDisplayed() {
        return isElementVisible(D_COMUNS_TITLE_XPATH, 5);
    }

    public Boolean isAtencionDisplayed() {
        return isElementVisible(ATENCION_TEXT_XPATH, 5);
    }

    //ACTIONS
    @Step("Se hace click en el botón 'Comisiones Especiales'")
    public void clickComisionesEspecialesButton() {
        click(COMISIONES_ESPECIALES_BUTTON_ID);
        acceptAlert(2);
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Clicar em 'Avançar'")
    public void clickAvanzarButton() {
        NoDriverUtils.await(2);
        click(AVANZAR_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Clicar em 'Avançar'")
    public void clickAvancar() {
        click(AVANCAR_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Gestao de Centros'")
    public void clickGestaoCentros() {
        click(BUTTON_GESTION_CENTROS_ID);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Emitir'")
    public void clickEmitir() {
        click(BUTTON_EMITIR_XPATH);
        makeScreenshot();
    }

    @Step("Se introduce el cliente {0}")
    public void inputClient(String client) {
        typeText(INPUT_CLIENT_ID, client, true);
        makeScreenshot();
    }

    @Step("Se hace click en el icono botón 'Buscar'")
    public void clickSearch() {
        click(BUTTON_SEARCH_XPATH);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Guardar'")
    public void clickSave() {
        click(BUTTON_SAVE_ID);
        makeScreenshot();
    }

    @Step("Se introduce la fecha final: {0}")
    public void inputFinalDate(String data) {
        typeText(INPUT_FINAL_DATE_ID, data, true);
        makeScreenshot();
    }
}


