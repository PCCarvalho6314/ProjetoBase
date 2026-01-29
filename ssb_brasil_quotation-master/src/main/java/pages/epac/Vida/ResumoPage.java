package pages.epac.Vida;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.web.page.PageObjectBase;

public class ResumoPage extends PageObjectBase {

    public ResumoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_RESUMO_PAGE_PRESENTATION ="//*[@id='nx-tab-label-0-4']";

    private static final String BUTTON_EMITIR = "//button//span[contains(text(), 'Emitir')]";


    //CHECKS
    public boolean isResumoPageDisplayed() {
        return isElementEnabled(CHECK_RESUMO_PAGE_PRESENTATION);
    }


    @Step("Clica no Botão 'Avançar'")
    public void clickEmitirButton() {
        click(BUTTON_EMITIR);
        makeScreenshot();
    }
}