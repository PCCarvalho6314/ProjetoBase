package pages.epac;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ResultadosPage extends PageObjectBase {

    public ResultadosPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - ID

    //LOCATORS - XPATH
    private static final String CHECK_NUMERO_OPERACAO_XPATH = "//*[contains(text(),'Número da operação')]";
    private static final String FORMA_PAGAMENTO_BUTTON_XPATH = "//button[@data-id='comboPaymentMode']";
    private static final String BOLETO_BANCARIO_BUTTON_XPATH = "//span[text()='Boleto Bancário']";
    private static final String BUTTON_PACOTE_ESSENCIAL_XPATH = "//*[contains(text(),'Essencial')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String BUTTON_PACOTE_BASICO_XPATH = "//*[contains(text(),'Básico')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String BUTTON_PACOTE_AMPLIADO_XPATH = "//*[contains(text(),'Ampliado')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String BUTTON_PACOTE_COMPLETO_XPATH = "//*[contains(text(),'Completo')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String BUTTON_PACOTE_MASTER_XPATH = "//*[contains(text(),'Master')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String BUTTON_PACOTE_EXCLUSIVO_XPATH = "//*[contains(text(),'Exclusivo')]//following-sibling::*//button[starts-with(@id,'button_')]";
    private static final String TEXT_NUMERO_COTACAO_AUTO_XPATH = "//label[@id='quotationNum']";
    private static final String BTN_SALVAR_COTACAO_ID = "saveStudyIcon";
    private static final String BUTTON_CLOSE_POPUP_XPATH = "//button[@aria-label='Close']";

    private static final String PDF_AUTO_BUTTON_XPATH = "//button[@id='b_generatePDF']";
    private static final String NUM_OPERACAO_XPATH = "//label[@id='operation']";


    //CHECKS
    public boolean isNumeroDaOperacaoDisplayed() {
        return isElementVisible(CHECK_NUMERO_OPERACAO_XPATH);
    }

    //ACTIONS
    @Step("Clicar em 'Forma de pagamento' e clicar na opção 'Boleto Bancário'")
    public void clickFormaPagamentoBoletoButton() {
        NoDriverUtils.await(2);
        clickByJS(FORMA_PAGAMENTO_BUTTON_XPATH);
        makeScreenshot();
        NoDriverUtils.await(2);
        clickByJS(BOLETO_BANCARIO_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Gerar Nº de COTAÇÃO")
    public void exibeNumeroCotacaoBPA() {
        String numeroCotacaoAuto = getNumeroCotacaoAuto().getText();
        Allure.step("Cotação nº: " + numeroCotacaoAuto);
    }

    @Step("Clicar em 'Comprar' para o plano Essencial")
    public void clickComprarEssencialButton() {
        NoDriverUtils.await(2);
        click(BUTTON_PACOTE_ESSENCIAL_XPATH);
        makeScreenshot();
    }

    @Step("Clicar em 'Comprar' do pacote de coberturas {0}")
    public void clickComprarPacoteCobertura(String pacoteCoberturas) {
        switch (pacoteCoberturas) {
            case "Essencial":
                click(BUTTON_PACOTE_ESSENCIAL_XPATH);
                break;
            case "Basico":
                click(BUTTON_PACOTE_BASICO_XPATH);
                break;
            case "Ampliado":
                click(BUTTON_PACOTE_AMPLIADO_XPATH);
                break;
            case "Completo":
                click(BUTTON_PACOTE_COMPLETO_XPATH);
                break;
            case "Master":
                click(BUTTON_PACOTE_MASTER_XPATH);
                break;
            case "Exclusivo":
                click(BUTTON_PACOTE_EXCLUSIVO_XPATH);
                break;
            default:
                System.out.println("O pacote de coberturas " + pacoteCoberturas + ", informado na planilha,  não corresponde aos pacotes apresentados na aplicação");
        }
    }

    @Step("Clicar no botão 'Salvar Cotação'")
    public void clicarSalvarCotacao() {
        NoDriverUtils.await(2);
        click(BTN_SALVAR_COTACAO_ID);
        makeScreenshot();
    }

    @Step("Clicar na opção 'Baixar pdf'")
    public void clickBaixarCotacaoPDFAuto() {
        NoDriverUtils.await(3);
        clickByJS(PDF_AUTO_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Clica no botão para fechar do pop-up")
    public void clickClosePopUpButton() {
        click(BUTTON_CLOSE_POPUP_XPATH);
        makeScreenshot();
    }

    @Step("Salva o PDF'")
    public void salvarPDF() throws AWTException {
        savePDFas();
        makeScreenshot();
    }


    public void savePDFas() throws AWTException {

        NoDriverUtils.await(6);
        abriNovaGuia();
        NoDriverUtils.await(2);
        switchToWindow(1);
        makeScreenshot();
        String caminhoDownload = System.getProperty("user.dir") + "\\src\\downloads";
        driver.get(caminhoDownload);
        switchToWindow(2);

        NoDriverUtils.await(4);


        NoDriverUtils.await(4);
//        driver.switchTo().activeElement();
        Actions actions = new Actions(driver);
        NoDriverUtils.await(3);

        actions.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).build().perform();


        for (int i = 0; i < 6; i++) {
            actions.sendKeys(Keys.TAB).perform();
            NoDriverUtils.await(1);
        }


        NoDriverUtils.await(2);

//        switchToWindow(2);
        NoDriverUtils.await(2);
        actions.keyDown(Keys.CONTROL).pause(Duration.ofMillis(200)).sendKeys("l")
                .pause(Duration.ofMillis(200)).keyUp(Keys.CONTROL).build().perform();


        NoDriverUtils.await(3);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.dispatchEvent(new KeyboardEvent('keydown', {'key': 'tab'}));");


        actions.sendKeys(Keys.RIGHT).perform();


        //        //Pressiona 6x TABS até chegar no campo de caminho da pasta a sera baixado o PDF
        for (int i = 0; i < 6; i++) {
            actions.sendKeys(Keys.TAB).perform();
            NoDriverUtils.await(1);
        }


        NoDriverUtils.await(2);
        actions.sendKeys(Keys.ENTER);

        NoDriverUtils.await(4);


//
//        // Pressiona Ctrl + L para focar na barra de endereços
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_L);
//        robot.keyRelease(KeyEvent.VK_L);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//
//        NoDriverUtils.await(2);
//
//        // Pressiona Ctrl + C para copiar a URL
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_C);
//        robot.keyRelease(KeyEvent.VK_C);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        makeScreenshot();
//
//
//        //Retorna à página do PDF
//        switchToWindow(2);
//        makeScreenshot();
//
//        NoDriverUtils.await(4);
//
//        //Pressiona as teclas CTRL + S
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_S);
//        robot.keyRelease(KeyEvent.VK_S);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        makeScreenshot();
//
//        NoDriverUtils.await(4);
//
//        //Pressiona 6x TABS até chegar no campo de caminho da pasta a sera baixado o PDF
//        for (int i = 0; i < 6; i++){
//            robot.keyPress(KeyEvent.VK_TAB);
//            NoDriverUtils.await(1);
//        }
//        robot.keyPress(KeyEvent.VK_ENTER);
//        NoDriverUtils.await(2);
//        robot.keyPress(KeyEvent.VK_BACK_SPACE);
//        NoDriverUtils.await(2);
//
//        // Pressiona Ctrl + V para colar o caminho específico da pasta a receber o PDF baixado
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//
//
//        NoDriverUtils.await(1);
//        robot.keyPress(KeyEvent.VK_ENTER);
//
//
//        //Pressiona 9x a tecla TAB até chegar no botão Salvar
//        for (int x = 0; x < 9; x++){
//            robot.keyPress(KeyEvent.VK_TAB);
//            NoDriverUtils.await(1);
//        }
//        robot.keyPress(KeyEvent.VK_ENTER);
//
//        //Clicar em subtituir arquivo, caso já exista outro com o mesmo nome.
//        robot.keyPress(KeyEvent.VK_TAB);
//        NoDriverUtils.await(1);
//        robot.keyPress(KeyEvent.VK_ENTER);
//
//        NoDriverUtils.await(6);
//        makeScreenshot();

    }

    public void setDiretorioDowloads() {
        // Define the download path
//        String downloadFilepath = "C:\\your\\download\\directory";
        String downloadFilepath = "C:\\automation\\repositories\\oes\\ssb_brasil_quotation\\src\\downloads";

        // Configure Chrome options for automatic download
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // Set preferences for download
        prefs.put("download.default_directory", downloadFilepath);
        prefs.put("download.prompt_for_download", false); // Don't ask for download location
//        prefs.put("plugins.always_open_pdf_externally", true); // Force PDFs to download instead of opening in new tab
        prefs.put("safebrowsing.enabled", false); // Force PDFs to download instead of opening in new tab
        prefs.put("profile.default_content_settings_values.automatic_downloads", 1); //

        options.setExperimentalOption("prefs", prefs);

//        // Initialize WebDriver
//        BrowserProvider(WebDriver, driver);
//        driver = new EdgeDriver(options);

        try {

            // Click the download button (using the ID you provided in the image)
//            d.findElement(By.id(PDF_AUTO_BUTTON_XPATH)).click();
//            clickByJS(getPdfAutoButton());


            // Wait for the download to complete (adjust as necessary for larger files)
            Thread.sleep(5000); // 5-second wait

//            System.out.println("PDF download completed.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToWindow(int index) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
    }

    public void abriNovaGuia() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.open();");
    }


    //WEB_ELEMENTS
    private WebElement getFormaPagamentoButton() {
        return driver.findElement(By.xpath(FORMA_PAGAMENTO_BUTTON_XPATH));
    }

    private WebElement getBoletoBancarioButton() {
        return driver.findElement(By.xpath(BOLETO_BANCARIO_BUTTON_XPATH));
    }

    public WebElement getNumeroCotacaoAuto() {
        scrollToElement(TEXT_NUMERO_COTACAO_AUTO_XPATH);
        makeScreenshot();
        return driver.findElement(By.xpath(TEXT_NUMERO_COTACAO_AUTO_XPATH));
    }

    public String getNumeroCotacao() {
        return driver.findElement(By.xpath(TEXT_NUMERO_COTACAO_AUTO_XPATH)).getText();
    }
    public String getNumeroOperacao() {
        return driver.findElement(By.xpath(NUM_OPERACAO_XPATH)).getText();
    }

    private WebElement getPdfAutoButton() {
        return driver.findElement(By.xpath(PDF_AUTO_BUTTON_XPATH));
    }

}
