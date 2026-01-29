package pages.epac;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class PagamentoPage extends PageObjectBase {

    //Locators - id
    private static final String CHECK_DISPLAY_PAGAMENTO_PAGE_ID = "//*[contains(text(),'Pagamento')]";


    //Locators - xpath
    private static final String CHECK_CONFIRMATION1_XPATH = "//*[contains(text(),'Obrigado por efetuar a aquisição do Seguro')]";
    private static final String CHECK_CONFIRMATION_OUTPUT_XPATH = "//*[@id= 'ConfPendencia_NAOForm']/p";
    private static final String CHECK_CONFIRMATION2_XPATH = "//*[text()='Número da apólice']//following-sibling::p";
    private static final String BUTTON_CONFIRMAR_PAGAMENTO_E_EMITIR_XPATH = "//button[contains(text(), 'Emitir')]";
    private static final String CONFIRMAR_PAGAMENTO_XPATH = "//*[contains(text(),'Obrigado, falta pouco para efetuar a')]";
    private static final String TXT_NUMERO_PROPOSTA_XPATH = "//*[text()='Número da proposta']//following-sibling::p";
    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";
    private static final String AVANCAR_BUTTON = "//button[contains (text(), 'Avançar')]";
    private static final String TEXT_NUMERO_COTACAO_AUTO_XPATH = "//label[@id='quotationNum' or @id='idLabelNumCotacao']";
    private static final String NUM_OPERACAO_XPATH = "//label[@id='operation' or @id='idLabelOperation']";

    private static final String LOADING_BACKGROUND_ID = "//*[@id='tblDialogProcess']";
    private static final String LOADING_BACKGROUND_2 = "//*[@id='divDialogProcess']";

    public PagamentoPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    public boolean isPagamentPageDisplayed() {
        return isElementVisible(CHECK_DISPLAY_PAGAMENTO_PAGE_ID, 6);
    }


    public String getNumeroCotacao() {
        return driver.findElement(By.xpath(TEXT_NUMERO_COTACAO_AUTO_XPATH)).getText();
    }
    public String getNumeroOperacao() {
        return driver.findElement(By.xpath(NUM_OPERACAO_XPATH)).getText();
    }

    public boolean isOutputsDisplayed() {
        return isElementVisible(CHECK_CONFIRMATION_OUTPUT_XPATH, 5);
    }

    public Boolean isApoliceNumberDisplayed() {
        return isElementVisible(CHECK_CONFIRMATION2_XPATH);
    }

    public boolean isDatosConfirmarPagamentoDisplayed() {
        return isElementVisible(String.valueOf(By.xpath(CONFIRMAR_PAGAMENTO_XPATH)));
    }

    //Actions
    @Step("Clica no botão 'Emitir'")
    public void clickEmitirButton() {
        NoDriverUtils.await(2);
        click(BUTTON_CONFIRMAR_PAGAMENTO_E_EMITIR_XPATH, true);
        makeScreenshot();
    }

    public void waitLoadingToFinish() {
        waitForVisible(LOADING_BACKGROUND_2);
        waitforInvisibleHard(LOADING_BACKGROUND_2, 3, 10);
    }
    @Step("Clica no botão 'Emitir'")
    public void clickAvancar() {
        NoDriverUtils.await(2);
        click(AVANCAR_BUTTON, true);
        makeScreenshot();
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApolice() {
        Allure.step("Apólice nº: " + getApoliceTxt());
    }

    @Step("Gerar Nº da PROPOSTA")
    public void exibeNumeroProposta() {
        Allure.step("Proposta nº: " + getPropostaTxt());
    }

    @Step("Nº da OPERAÇÃO: ")
    public void exibeNumeroOperacao(String numOperacao) {
        Allure.step("Operação nº: " + numOperacao);
        System.out.println("Operação nº: " + numOperacao);
    }

    @Step("Nº da COTAÇÃO: ")
    public void exibeNumeroCotacao(String numCotacao) {
        Allure.step("Cotação nº: " + numCotacao);
        System.out.println("Cotação nº: " + numCotacao);
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

    public boolean isConfirmationAutoDisplayed() {
        NoDriverUtils.await(3);
        return ((isElementVisible(CHECK_CONFIRMATION1_XPATH)) &&
                !getPolizaTextView().getText().isEmpty()) ||
                isElementVisible(CONFIRMAR_PAGAMENTO_XPATH);
    }

    public WebElement getPolizaTextView() {
        return driver.findElement(By.xpath(CHECK_CONFIRMATION2_XPATH));
    }

    public String getApoliceTxt() {
        return getPolizaTextView().getText();
    }

    public String getPropostaTxt() {
        return getProposalText().getText();
    }

    private WebElement getProposalText() {
        return driver.findElement(By.xpath(TXT_NUMERO_PROPOSTA_XPATH));
    }


}