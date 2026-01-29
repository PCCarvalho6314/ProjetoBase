package pages.intra.login;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.util.Set;

public class PortalsPage extends PageObjectBase {


    //Locators - Xpath
    private static final String BUTTON_MENU_OPTION_XPATH = "//span[normalize-space(text())='%s']";
    private static final String SUBMENU_ADMINISTRACAO_XPATH = "//span[normalize-space(text())='Administração']";
    private static final String OPTION_PAGNET_XPATH = "//a[normalize-space(text())='Pagnet']";

    private static final String BUTTON_PORTAL_OPTION_XPATH = "//a[normalize-space(text())='%s']";
    private static final String DDM_USERS_XPATH = "//div[@id='nx-dropdown-rendered-0']";
    private static final String OPTION_USER_XPATH = "//div[normalize-space(text())='%s']";
    private static final String CHECK_LOGIN_XPATH = "//*[contains(text(),'Último acesso')]";
    private static final String BUTTON_INICIAR_SESION_XPATH = "//span[contains(text(),'Iniciar')]/ancestor::button";
    private static final String TITLE_PRODUCAO_E_SINISTROS_HOME_INTRANET_XPATH = "//h2[contains(text(), 'Produção & Sinistros')]";


    //Constructor
    public PortalsPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    @Step("Checking login sucessfully done...")
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
    @Step("Checa se a página em foco é a inicial de Produção e Sinistros da Intranet")
    public boolean isAlreadyOnFocusedHomePageIntranet() {
            return isElementVisible(TITLE_PRODUCAO_E_SINISTROS_HOME_INTRANET_XPATH,3);
        }

    //Actions
    @Step("Accessing to portal '{0}'")
    private void accessPortal(Portal portalOption) {
        waitForClickable(String.format(BUTTON_MENU_OPTION_XPATH, portalOption.getMenuName()));
        getMenuButton(portalOption).click();
        waitForClickable(String.format(BUTTON_PORTAL_OPTION_XPATH, portalOption.getPortalname()));
        getPortalButton(portalOption).click();
        makeScreenshot();
        waitForJSandJqueryFinish();
    }

    @Step("Acessando o Pagnet")
    private void accessMenu_Pagnet(Portal portalOption) {
        clickByJS(String.format(BUTTON_MENU_OPTION_XPATH, portalOption.getMenuName()));
        clickByJS(SUBMENU_ADMINISTRACAO_XPATH);
        clickByJS(OPTION_PAGNET_XPATH);
    }

    @Step("Performing login with user '{0}'")
    public void login(Portal portal) {
        click(DDM_USERS_XPATH);
        getUserOption(portal).click();
        getIniciarSesionButton().click();
        waitForJSandJqueryFinish();
        makeScreenshot();
    }

    //Aux methods
    public void accessApplication(Portal portal) {
        login(portal);
        checkLogin();
        accessPortal(portal);
        waitForNumberOfWindows(2);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        NoDriverUtils.await(4);
    }
    public void accessPagnetMenu(Portal portal) {
        login(portal);
        checkLogin();
        accessMenu_Pagnet(portal);
        waitForNumberOfWindows(2);
        changeToNextWindow();
        waitForJSandJqueryFinish();
        NoDriverUtils.await(4);
    }

    public void closeCurrentWindow() {
        driver.close();
        Set<String> windows = driver.getWindowHandles();
        String mainWindow = windows.stream().findFirst()
                .toString().replace("Optional[", "").replace("]", "");
        driver.switchTo().window(mainWindow);
    }

    public void accessMenu(Portal portal) {
        accessPortal(portal);
        waitForNumberOfWindows(2);
        changeToNextWindow();
        NoDriverUtils.await(2);
    }

    //Webelements
    private WebElement getMenuButton(Portal portalOption) {
        return driver.findElement(By.xpath(String.format(BUTTON_MENU_OPTION_XPATH, portalOption.getMenuName())));
    }

    private WebElement getPortalButton(Portal portalOption) {
        return driver.findElement(By.xpath(String.format(BUTTON_PORTAL_OPTION_XPATH, portalOption.getPortalname())));
    }

    private WebElement getUserOption(Portal portal) {
        return driver.findElement(By.xpath(String.format(OPTION_USER_XPATH, portal.getLoginUser())));
    }

    private WebElement getIniciarSesionButton() {
        return driver.findElement(By.xpath(BUTTON_INICIAR_SESION_XPATH));
    }


}
