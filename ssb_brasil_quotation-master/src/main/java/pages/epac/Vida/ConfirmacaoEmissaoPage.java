package pages.epac.Vida;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ConfirmacaoEmissaoPage extends PageObjectBase {

    public ConfirmacaoEmissaoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_CONFIRMACAO_EMISSAO_PAGE_PRESENTATION = "//*[contains(text(), 'Obrigado por escolher o seguro Allianz Vida')]";

    private static final String BUTTON_EMITIR = "//button//span[contains(text(), 'Emitir')]";
    private static final String NUMERO_PROPOSTA = "//div[contains(text(), ' Número da Proposta:')]//following-sibling::span";
    private static final String NUMERO_OPERACAO = "//div[contains(text(), 'Número da operação')]//following-sibling::span";
    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";


    //CHECKS
    public boolean isConfirmacaoPageDisplayed() {
        return isElementVisible(CHECK_CONFIRMACAO_EMISSAO_PAGE_PRESENTATION);
    }

    public String getNumeroProposta() {
        return driver.findElement(By.xpath(NUMERO_PROPOSTA)).getText();
    }

    public String getNumeroOperacao() {
        return driver.findElement(By.xpath(NUMERO_OPERACAO)).getText();
    }

    @Step("Clicar para abrir o PDF")
    public void openPDF() {
        click(BUTTON_EMITIR);
        makeScreenshot();
    }

    @Attachment(value = "Screenshot jpg attachment", type = "image/jpg")
    @Step("Taking full screenshot from '{0}'")
    public byte[] fullScreenshot(String url, String path, String name) {

        PageSnapshot pageSnapshot = Shutterbug.shootPage(driver, Capture.FULL);
        pageSnapshot.withName(name);
        pageSnapshot.save(path);
        try {
            return pageSnapshot.getBytes();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception taking full screenshot - {0}", e.getMessage());
        }

        return new byte[0];
    }

    public List<String> getListMensagemErro() {
        List<String> listaErros = new ArrayList<>();
        List<WebElement> erros = driver.findElements(By.xpath(LISTA_ERROS_XPATH));
        for (WebElement erro : erros) {
            listaErros.add(erro.getText());
        }
        return listaErros;
    }
}