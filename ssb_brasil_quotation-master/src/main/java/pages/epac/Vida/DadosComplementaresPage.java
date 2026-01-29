package pages.epac.Vida;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import walle.frw.web.page.PageObjectBase;

public class DadosComplementaresPage extends PageObjectBase {

    public DadosComplementaresPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_DADOS_COMPLEMENTARES_PAGE_PRESENTATION = "//*[contains(text(), 'Dados do segurado')]";
    private static final String INPUT_CELULAR = "//*[@id='nx-input-22']";
    private static final String BUTTON_AVANCAR = "//button//span[contains(text(), 'Avançar')]";


    //CHECKS
    public boolean isDadosComplementaresPageDisplayed() {
        return isElementVisible(CHECK_DADOS_COMPLEMENTARES_PAGE_PRESENTATION);
    }

    @Step("Insere o número de celular {0}'")
    public void inputCelular(String celular) {
        if (celular != "") {
            typeText(INPUT_CELULAR, celular, true);
            makeScreenshot();
        }
    }

    @Step("Clica no Botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR);
        makeScreenshot();
    }


}
