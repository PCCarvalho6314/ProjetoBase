package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class ClausulasPage extends PageObjectBase {

    private static final String AVANZAR_BUTTON_ID = "btnAceptar";
    private static final String CLAUSULAS_OPERACIONALES_TITLE_XPATH = "//td[contains(text(),'CLÁUSULAS OPERACIONAIS') or contains(text(),'Cláusulas')]";
    private static final String CLAUSULAS_PAGE_TITLE_XPATH = "//*[contains(translate(text(),'ACLSU','aclsu'),'cláusulas')]";
    private static final String CLAUSULAS_OPERACIONAIS_PAGE_TITLE_XPATH = "//*[contains(text(),'INFORMAÇÃO DOS AVISOS E OCORRÊNCIAS') or contains(text(),'CLÁUSULAS SELECIONADAS')]";

    public ClausulasPage(WebDriver driver) {
        super(driver);
    }

    // Checks
    public Boolean isClausulasOperacionales() {
        return isElementVisible(CLAUSULAS_OPERACIONALES_TITLE_XPATH);
    }

    public Boolean isClausulasOperacionales2() {
        return isElementVisible(CLAUSULAS_OPERACIONAIS_PAGE_TITLE_XPATH);
    }

    public Boolean isClausulasPageDisplayed() {
        return isElementVisible(CLAUSULAS_PAGE_TITLE_XPATH);
    }

    @Step("Clica no botão 'Avançar'")
    public void clickAvanzarButton() {
        NoDriverUtils.await(2);
        click(AVANZAR_BUTTON_ID);
        makeScreenshot();
    }
}
