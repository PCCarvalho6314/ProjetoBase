package pages.epac;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.api.ApiToken;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;


public class ConfirmacaoEmissaoPage extends PageObjectBase {


    //Locators - ids
    private static final String BUTTON_PROPOSTA_ID = "proposal";


    //Locators - xpaths
    private static final String CHECK_CONFIRM_EMISSAO = "//*[contains(text(), 'Obrigado por efetuar a aquisição') or contains(text(), 'Apólice emitida com sucesso!')]";
    private static final String NUM_APOLICETXT = "//*[text()='Número da apólice']//following-sibling::p";
    private static final String NUM_PROPOSTA = "//*[contains(text(), 'Número de proposta')]";

    private static final String NUM_APOLICETXT_2 = "//*[@id='idLabelNumApoliceProposta']";

    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";


    public ConfirmacaoEmissaoPage(WebDriver driver) {
        super(driver);
    }


    //CHECKS
    public boolean isConfirmacionEmissaoDisplayed() {
        return isElementVisible(CHECK_CONFIRM_EMISSAO);
    }

    public boolean isPDFOpen(int window) {
        waitForNumberOfWindows(window);
        changeToNextWindow();
        makeScreenshot();
        return driver.getCurrentUrl().toLowerCase().contains("pdf");
    }

    public String getApoliceTxt() {
        return getText(NUM_APOLICETXT, false);
    }

    public String getApoliceTxt_2() {
        return getText(NUM_APOLICETXT_2, false);
    }

    public String getPropostaTxt() {
        return getText(NUM_PROPOSTA, false).replace("Número de proposta : ", "");
    }

    //Actions

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

    public boolean isRunningOnJenkins() {
        return System.getenv("JENKINS_HOME") != null;
    }

    public void baixarPDFViaAPI(String numEmissao, String tipoEmissao, String pdfPathByCenario) {
        //Baixar PDFS - Somente se não estiver executando no Jenkins
        if (!isRunningOnJenkins()) {

            ApiToken apiToken = new ApiToken();
            String textNumeroEmissao;

            switch (tipoEmissao) {
                case "Cotação":
                    textNumeroEmissao = "quotationNumber=";
                    break;
                case "Proposta":
                    textNumeroEmissao = "proposal=";
                    break;
                case "Apólice":
                    textNumeroEmissao = "policyNumber=";
                    break;
                default:
                    throw new IllegalArgumentException("Opção de tipo de emissão para baixar pdf é inválida.");
            }

            //Api de geração de documentos em PDF
            String pdfUrl = "https://eu-uat-brazil.apis.allianz.com/api-in-household-azb/v1/documentos?" + textNumeroEmissao + numEmissao;

            // Caminho onde será salvo o arquivo PDF gerado
            String destinoDownloadPdf = "src/test/resources/pdf/";

            //Para esta requisição, é necessário o uso do proxy abaixo:
            RestAssured.proxy("127.0.0.1", 9000);

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            Response response = RestAssured.given()
                    .when().relaxedHTTPSValidation()
                    .header("Authorization", "Bearer " + apiToken.getBearerToken(ApiToken.AuthProduct.RESIDENCIA_INTEGRACAO))
                    .header("parceiro", "BP002621")
                    .header("usuario", "BA262930")
                    .header("mediador", "617120")
                    .header("Content-Type", "application/json")
                    .get(pdfUrl)
                    .then()
                    .statusCode(HttpStatus.SC_OK) // Garante que a resposta é OK
                    .extract()
                    .response();

//         System.out.println("Response: " + response.getBody().asPrettyString());

            // Extraindo o base64 do campo document
            String base64Content = response.path("printResponse.return.value.document").toString();

//        System.out.println("byteArrayPDF: " + base64Content);

            // Decodificando o base64 extraído para bytes
            byte[] pdfBytes = Base64.getDecoder().decode(base64Content);

            // Salvando os bytes em um arquivo PDF
            salvarArquivoPDF(destinoDownloadPdf, pdfBytes, numEmissao, tipoEmissao);
        }
    }

    private static void salvarArquivoPDF(String destinoDonwlPdf, byte[] pdfBytes, String numEmissao, String tipoEmissao) {
        try {
            Files.write(Paths.get(destinoDonwlPdf + tipoEmissao + "_" + numEmissao + ".pdf"), pdfBytes);
            System.out.println("O arquivo PDF da " + tipoEmissao + " de nº " + numEmissao + " foi gerado com sucesso no caminho " + destinoDonwlPdf);
        } catch (Exception e) {
            System.err.println("Erro ao salvar o arquivo PDF devido: " + e.getMessage());
        }
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
