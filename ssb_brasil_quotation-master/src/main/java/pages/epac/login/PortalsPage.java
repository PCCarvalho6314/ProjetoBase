package pages.epac.login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class PortalsPage extends PageObjectBase {

    //Locators - Xpath
    private static final String DDM_USERS_XPATH = "//nx-dropdown[@class='nx-dropdown' or @title='Usuarios genéricos']";
    private static final String OPTION_USER_XPATH = "//div[normalize-space(text())='%s']";
    private static final String CHECK_LOGIN_XPATH = "//*[contains(text(),'Último acesso')]";
    private static final String BUTTON_INICIAR_SESION_XPATH = "//span[contains(text(),'Iniciar')]/ancestor::button";
    private static final String BUTTON_MEDIADORES_XPATH = "(//*[contains(text(),'Corretor principal')])[1]";
    private static final String BUTTON_MEDIADOR_XPATH = "//*[@class='nx-dropdown-results__option-label ng-star-inserted']//*[contains(text(),'%s')]";
    private static final String BUTTON_GRIC_TAB_XPATH = "//nx-expansion-panel-title[text()='%s']";
    private static final String BUTTON_GRIC_PRODUCT_XPATH = "//div[text()='%s']";
    private static final String CHECK_SELECTOR_PRODCT_XPATH = "//*[@name='close']";
    private static final String CHECK_NECESIDADES_CLIENTE_XPATH = "//*[text()='Análisis de necesidades del cliente']";
    private static final String BUTTON_NECESIDADES_ACEPTAR_XPATH = "//*[text()='aquí']";
    private static final String FRAME_APPAREA_ID = "appArea";
    private static final String BUTTON_NUEVA_ALTA_ID = "link_new_policy";
    private static final String CHECKBOX_ACCEPT_TERMS_AND_CONDITIONS = "//span[@class='nx-checkbox__control']";
    private static final String BUTTON_ACCEPT_TERMS_AND_CONDITIONS = "//span[contains(text(), 'Aceitar')]";

    //Constructor
    public PortalsPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    @Step("Checando se o login ocorreu corretamente...")
    private void checkLogin() {

        boolean loginOK = isElementVisible(CHECK_LOGIN_XPATH, 10);

        try {
            Assert.assertTrue(loginOK);
        } catch (AssertionError e) {
            throw new AssertionError("Login failure");
        } finally {
            makeScreenshot();
        }
    }

    //Actions
    @Step("Realizando login com '{1}'")
    private void login(PortalProductIssuancePage portalProductIssuancePage, String loginUser) {
        click(DDM_USERS_XPATH);
        getUserOption(portalProductIssuancePage).click();
        getIniciarSesionButton().click();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Definindo o Mediador como: '{0}'")
    public void changeMediador(String mediador) {
        click(BUTTON_MEDIADORES_XPATH);

        NoDriverUtils.await(2);
        waitForClickable(String.format(BUTTON_MEDIADOR_XPATH, mediador));
        getSelectorAgenteInput(mediador).click();

        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    @Step("Acessando o portal de produtos para nova emissão de: '{1}'")
    public void accessAppIssuance(PortalProductIssuancePage portal, String productName) {
        click(BUTTON_NUEVA_ALTA_ID);

        if (isElementVisible(CHECK_NECESIDADES_CLIENTE_XPATH, 2)) {
            getAceptarNecesidadesButton().click();
        }

        waitForClickable(CHECK_SELECTOR_PRODCT_XPATH);
        getTabButton(portal.getTabName()).click();
        getProductIssuanceButton(portal.getProductName()).click();

        changeToAppAreaFrame();
        makeScreenshot();

        acceptAlert(1);
    }

    //Aux methods
    public void accessIssuanceApplication(PortalProductIssuancePage portal, String mediador) {
        login(portal, portal.getLoginUser());
        acceptTermsAndConditions();
        checkLogin();
        NoDriverUtils.await(2);
        if (!mediador.isEmpty()) changeMediador(mediador);
        if (portal.equals(PortalProductIssuancePage.CONSULTAS)) return;

        accessAppIssuance(portal, portal.getProductName());

    }
    @Step("Aceitando os termos e condições")
    public void acceptTermsAndConditions() {
        click(CHECKBOX_ACCEPT_TERMS_AND_CONDITIONS);
        makeScreenshot();
        click(BUTTON_ACCEPT_TERMS_AND_CONDITIONS);
        makeScreenshot();
    }

    public void changeToAppAreaFrame() {
        changeToFrameWaitAvailable(FRAME_APPAREA_ID);
        waitForJSandJqueryFinish();
    }

    @Override
    public void changeToDefaultFrame() {
        super.changeToDefaultFrame();
        waitForJSandJqueryFinish();
    }

    //Webelements
    private WebElement getUsersDDM() {
        return driver.findElement(By.xpath(DDM_USERS_XPATH));
    }

    private WebElement getUserOption(PortalProductIssuancePage portalProductIssuancePage) {
        return driver.findElement(By.xpath(String.format(OPTION_USER_XPATH, portalProductIssuancePage.getLoginUser())));
    }

    private WebElement getIniciarSesionButton() {
        return driver.findElement(By.xpath(BUTTON_INICIAR_SESION_XPATH));
    }

    private WebElement getSelectorAgente() {
        return driver.findElement(By.xpath(BUTTON_MEDIADORES_XPATH));
    }

    private WebElement getSelectorAgenteInput(String mediador) {
        return driver.findElement(By.xpath(String.format(BUTTON_MEDIADOR_XPATH, mediador)));
    }

    private WebElement getTabButton(String tab) {
        return driver.findElement(By.xpath(String.format(BUTTON_GRIC_TAB_XPATH, tab)));
    }

    private WebElement getProductIssuanceButton(String product) {
        return driver.findElement(By.xpath(String.format(BUTTON_GRIC_PRODUCT_XPATH, product)));
    }

    private WebElement getAceptarNecesidadesButton() {
        return driver.findElement(By.xpath(BUTTON_NECESIDADES_ACEPTAR_XPATH));
    }

    private WebElement getNuevaAltaButton() {
        return driver.findElement(By.id(BUTTON_NUEVA_ALTA_ID));
    }

}
