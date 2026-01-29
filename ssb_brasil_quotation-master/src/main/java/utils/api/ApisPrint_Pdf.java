package utils.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


public class ApisPrint_Pdf {
    public static boolean isRunningOnJenkins() {
        return System.getenv("JENKINS_HOME") != null;
    }

    public static void baixarPDFViaAPI(String produto, String tipoDocumento, String numEmissao) {
        ApiToken apiToken = new ApiToken();
        String textNumeroEmissao;
        String baseUrl_Pdf;
        String basePath;
        String pathCampoBase64Pdf;

        //Baixar PDFS - Somente se não estiver executando no Jenkins
        if (!isRunningOnJenkins()) {

            switch (produto) {
                case "Empresarial":
                    baseUrl_Pdf = "https://eu-uat-brazil.apis.allianz.com/empresa/v2/documentos/";

                    switch (tipoDocumento) {
                        case "Cotação":
                            tipoDocumento = "COTACAO";
                            textNumeroEmissao = "numeroCotacao=";
                            break;
                        case "Proposta":
                            tipoDocumento = "PROPOSTA";
                            textNumeroEmissao = "numeroProposta=";
                            break;
                        case "Apólice":
                            tipoDocumento = "APOLICE";
                            textNumeroEmissao = "numeroApoliceSusep=";
                            break;
                        default:
                            throw new IllegalArgumentException("Opção de tipo de documento para baixar pdf é inválida.");
                    }
                    basePath = tipoDocumento + "?" + textNumeroEmissao + numEmissao;
                    pathCampoBase64Pdf = "arquivoPDF";
                    break;

                case "Residencia":
                case "Vida":
                    baseUrl_Pdf = "https://eu-uat-brazil.apis.allianz.com/api-in-household-azb/v1/documentos?";

                    switch (tipoDocumento) {
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
                            throw new IllegalArgumentException("Opção de tipo de documento para baixar pdf é inválida.");
                    }
                    basePath = textNumeroEmissao + numEmissao;
                    pathCampoBase64Pdf = "printResponse.return.value.document";
                    break;
                default:
                    throw new IllegalArgumentException("A opção inserida '" + produto + "' como produto é inválida.");
            }

            // Caminho onde será salvo o arquivo PDF gerado
            String destinoDownloadPdf = "src/test/resources/pdf/";

            //Para esta requisição, é necessário o uso do proxy abaixo:
            RestAssured.proxy("127.0.0.1", 9000);

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            Response response = RestAssured.given()
                    .when().relaxedHTTPSValidation()
                    .header("Authorization", "Bearer " + apiToken.getBearerToken(ApiToken.AuthProduct.PME_INTEGRACAO))
                    .header("parceiro", "BP002621")
                    .header("usuario", "BA262930")
                    .header("mediador", "617120")
                    .header("Content-Type", "application/json")
                    .get(baseUrl_Pdf + basePath)
                    .then()
                    .statusCode(HttpStatus.SC_OK) // Garante que a resposta é OK
                    .extract()
                    .response();

         System.out.println("Response: " + response.getBody().asPrettyString());

            // Extraindo o base64 do campo document
            String base64Content = response.path(pathCampoBase64Pdf).toString();

//        System.out.println("byteArrayPDF: " + base64Content);

            // Decodificando o base64 extraído para bytes
            byte[] pdfBytes = Base64.getDecoder().decode(base64Content);

            // Salvando os bytes em um arquivo PDF
            salvarArquivoPDF(destinoDownloadPdf, pdfBytes, numEmissao, tipoDocumento);
        }
    }

    private static void salvarArquivoPDF(String destinoDonwlPdf, byte[] pdfBytes, String numEmissao, String
            tipoDocumento) {
        try {
            Files.write(Paths.get(destinoDonwlPdf + tipoDocumento + "_" + numEmissao + ".pdf"), pdfBytes);
            System.out.println("O arquivo PDF da " + tipoDocumento + " de nº " + numEmissao + " foi gerado com sucesso no caminho " + destinoDonwlPdf);
        } catch (Exception e) {
            System.err.println("Erro ao salvar o arquivo PDF devido: " + e.getMessage());
        }
    }
}