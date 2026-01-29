package pages.epac;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.web.page.PageObjectBase;

public class CoberturasPage extends PageObjectBase {

    public CoberturasPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_COBERTURAS_PAGE_PRESENTATION ="//*[contains(text(), 'Incêndio e Complem')]";

    private static final String BUTTON_AVANCAR = "//button[contains(text(), 'Avançar')]";


    //CHECKS
    public boolean isCoberturasPageDisplayed() {
        return isElementEnabled(CHECK_COBERTURAS_PAGE_PRESENTATION);
    }


    @Step("Clica no botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR);
        waitForDialogCoverHide();
        makeScreenshot();
    }
}