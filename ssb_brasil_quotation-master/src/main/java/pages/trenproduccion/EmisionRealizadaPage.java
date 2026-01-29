package pages.trenproduccion;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.api.ApiToken;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;

public class EmisionRealizadaPage extends PageObjectBase {

    private static final String NUMERO_PROPOSTA_ID = "CabeceraBean$sucursal";
    private static final String NUMERO_APOLICE_ID = "CabeceraBean$sucursalSUSEP";
    private static final String VALIDAR_BUTTON_ID = "btnValida";
    private static final String ACEITAR_BUTTON_ID = "btnAceptar";
    private static final String POLIZA_CORRECTAMENTE_EMITIDA_TITLE_XPATH = "//td[text()='APÓLICE CORRETAMENTE EMITIDA']";
    private static final String POLIZA_SUJEITA_A_APROVACAO_XPATH = "//*[text()='El proyecto estará pendiente de aceptación' or text()='A apólice estará sujeita a aceitação']";
    private static final String AVISO_EMISSAO_SERA_REALIZADA_XPATH = "//*[contains(text(), 'A apólice será emitida.')]";
    private static final String AVISO_ENDOSSO_SERA_REALIZADO_XPATH = "//*[contains(text(), 'O endosso será emitido.')]";
    private static final String EMISSAO_REALIZADA_TITLE_XPATH = "//td[contains(text(), 'EMISSÃO REALIZADA')]";
    private static final String BLOQUEOS_TITLE_XPATH = "//td[text()='PEDIDO PENDENTE DE ACEITAÇÃO']";
    private static final String SUCESSO_PAGNET_TITLE_XPATH = "//*[text()='Solicitação finalizada com sucesso e aprovador notificado.']";
    private static final String ENDOSSO_EMITIDO_TITLE_XPATH = "//td[text()='ENDOSSO CORRETAMENTE EMITIDO']";
    private static final String ALERTA_TEXT_XPATH = "//td[contains(text(),'Alerta')]";
    private static final String PDF_PROPOSTA_XPATH = "//*[contains(text(),'Proposta / Proposta Detalhada')]";
    private static final String PDF_APOLICE_XPATH = "//*[contains(text(),'Apólice de Seguro')]";
    private static final String PDF_APOLICE_RESUMIDA_XPATH = "//*[contains(text(),'Apólice Resumida de Seguro')]";
    private static final String PDF_PROPOSTA_PROPOSTA_DETALHADA_XPATH = "//*[contains(text(),'Proposta / Proposta Detalhada')]";
    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";


    public EmisionRealizadaPage(WebDriver driver) {
        super(driver);
    }

    //CHECKS

    public boolean isPolizaEmitida() {
        return isElementVisible(POLIZA_CORRECTAMENTE_EMITIDA_TITLE_XPATH, 10);
    }

    public boolean isApolicePendenteOuEmitida() {
        return (isElementVisible(POLIZA_CORRECTAMENTE_EMITIDA_TITLE_XPATH, 10) ||
                isElementVisible(BLOQUEOS_TITLE_XPATH, 10));
    }

    public boolean isSolicitacaoFinalizadaSucesso() {
        return isElementVisible(SUCESSO_PAGNET_TITLE_XPATH);
    }

    public boolean isPolizaBloqueada() {
        return isElementVisible(BLOQUEOS_TITLE_XPATH);
    }

    public boolean isApolicePendente() {
        return isElementVisible(POLIZA_SUJEITA_A_APROVACAO_XPATH);
    }

    public boolean isApoliceSeraRealizada() {
        return isElementVisible(AVISO_EMISSAO_SERA_REALIZADA_XPATH);
    }

    public boolean isEndossoSeraEmitido() {
        return isElementVisible(AVISO_ENDOSSO_SERA_REALIZADO_XPATH);
    }

    public boolean isEmissaoRealizada() {
        return isElementVisible(EMISSAO_REALIZADA_TITLE_XPATH);
    }

    public boolean isAlertaDisplayed() {
        return isElementVisible(ALERTA_TEXT_XPATH);
    }

    public boolean isEndossoEmitido() {
        return isElementVisible(ENDOSSO_EMITIDO_TITLE_XPATH);
    }

    public Boolean isApoliceNumberDisplayed() {
        return isElementVisible(NUMERO_APOLICE_ID);
    }
    public Boolean isPropostaNumberDisplayed() {
        return isElementVisible(NUMERO_PROPOSTA_ID);
    }


    //AUX METHODS
    public String getNumProposta() {
        return getPropostaText().getText().split("/")[0].trim();
    }

    public String getNumApolice() {
        return getApoliceText().getText().split("/")[0].trim().replace("-", "");
    }


    //ACTIONS
    @Step("Se hace click en el botón 'Avanzar'")
    public void clickAvanzarButton2() {
        click(VALIDAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Aceitar'")
    public void clickAceitarButton() {
        click(ACEITAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarButton() {
        click(ACEITAR_BUTTON_ID);
        NoDriverUtils.await(6);
        makeScreenshot();
    }

    @Step("Abrir PDF de Proposta")
    public void abrirPDFPropostaButton(int window) {
        click(PDF_PROPOSTA_XPATH);
        waitForNumberOfWindows(window);
        changeToNextWindow();
        makeScreenshot();
    }

    @Step("Abrir PDF de Apólice Resumida")
    public void abrirPDFApoliceResumida(int window) {
        click(PDF_APOLICE_RESUMIDA_XPATH);
        waitForNumberOfWindows(window);
        changeToNextWindow();
        makeScreenshot();
    }

    public void baixarPDFViaAPI(String numEmissao, String tipoEmissao, String pdfPathByCenario) {
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

        // Caminho onde o arquivo será salvo o arquivo PDF gerado
        String destinoDownloadPdf = "src/test/resources/pdf/";

        //Para esta requisição é necessário o uso do proxy abaixo:
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

        // System.out.println("Response: " + response.getBody().asPrettyString());
        // System.out.println("byteArrayPDF: " + base64Content);

        // Extraindo o base64 do campo document
        String base64Content = response.path("printResponse.return.value.document").toString();

        // Decodificando o base64 extraído para bytes
        byte[] pdfBytes = Base64.getDecoder().decode(base64Content);

        // Salvando os bytes em um arquivo PDF
        salvarArquivoPDF(destinoDownloadPdf, pdfBytes, numEmissao, tipoEmissao);
    }

    private static void salvarArquivoPDF(String destinoDonwlPdf, byte[] pdfBytes, String numEmissao, String tipoEmissao) {
        try {
            Files.write(Paths.get(destinoDonwlPdf + tipoEmissao+ "_" + numEmissao + ".pdf"), pdfBytes);
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


    @Step("Abrir PDF de Proposta / Proposta Detalhada")
    public void abrirPDF_Proposta_Proposta_Detalhada(int window) {
        click(PDF_PROPOSTA_PROPOSTA_DETALHADA_XPATH);
        waitForNumberOfWindows(window);
        changeToNextWindow();
        makeScreenshot();
    }

    public void changetoWindow(String windowName) {
        closeCurrentWindowAndChangeTo(windowName);
        makeScreenshot();
    }


    @Step("Abrir PDF de Apólice")
    public void abrirPDFApoliceButton(int window) {
        click(PDF_APOLICE_XPATH);
        waitForNumberOfWindows(window);
        changeToNextWindow();
        makeScreenshot();
    }

    @Step("Gerar Nº de PROPOSTA")
    public void exibeNumeroPropostaAuto() {
        String numeroPropostaAuto = getNumProposta();
        Allure.step("Proposta nº: " + numeroPropostaAuto);
    }

    @Step("Gerar Nº de APÓLICE")
    public void exibeNumeroApoliceAuto() {
        String numeroApoliceAuto = getNumApolice();
        Allure.step("Apólice nº: " + numeroApoliceAuto);
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

    private WebElement getPropostaText() {
        return driver.findElement(By.id(NUMERO_PROPOSTA_ID));
    }

    private WebElement getApoliceText() {
        return driver.findElement(By.id(NUMERO_APOLICE_ID));
    }
}





