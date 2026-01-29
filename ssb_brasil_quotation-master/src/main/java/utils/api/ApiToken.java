package utils.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiToken {

    public enum AuthProduct {
        PARCEIRO_SANTANDER,
        PARCEIRO_SICRED,
        SINISTRO,
        PME,
        RESIDENCIA,
        RESIDENCIA_INTEGRACAO,
        PME_INTEGRACAO,
        CONDOMINIUM,
        CLIENTS,
        SEGURADO
    }

    public String getBearerToken(AuthProduct tipoAutenticacao, String numCPF) {
        if (numCPF != null) {
//            System.out.println("Param 1 " + tipoAutenticacao + ", Param 2: " + numCPF);
        } else {
//            System.out.println("Param 1 " + tipoAutenticacao);
        }

        String BASE_URL_COMPLETA;
        Map<String, String> headers = new HashMap<>();
        Map<String, String> formParams = new HashMap<>();

        switch (tipoAutenticacao) {
            case PARCEIRO_SANTANDER:
                BASE_URL_COMPLETA = "https://wwwf.am.intrallianz.com/rws-oauth-authorization-server/oauth/token";
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("epac-company-id", "BRA");
                formParams.put("client_id", "ECLIENT-REGISTRY");
                formParams.put("client_secret", "5e0a00bb-bab1-4d2d-bec4-ec691f3a4b50");
                formParams.put("grant_type", "client_credentials");
                break;

            case PARCEIRO_SICRED:
                BASE_URL_COMPLETA = "https://eu-pre-internal-brazil.apis.allianz/api-in-partnership-azb/accesstoken?grant_type=client_credentials";
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", "Basic RHBoZXhBd01NNEdZajBKV1U4YTVhNUR2TE9EbWQ4bUQ6RVpldlduV2lSTlRJYUczRw==");
                formParams.put("grant_type", "client_credentials");
                break;

            case SINISTRO:
            case PME:
            case RESIDENCIA:
            case CONDOMINIUM:
                BASE_URL_COMPLETA = "https://wwwf.br.intrallianz.com/rws-oauth-authorization-server/oauth/token";
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("epac-company-id", "BRA");
                formParams.put("client_id", "INTRANET");
                formParams.put("username", "BRINSGI0");
                formParams.put("password", "despwd");
                formParams.put("client_secret", "f8739f23-61ea-4df7-b70b-853e9b6eb0f3");
                formParams.put("grant_type", "password");
                break;

            case RESIDENCIA_INTEGRACAO:
            case PME_INTEGRACAO:
                BASE_URL_COMPLETA = "https://eu-uat-brazil.apis.allianz.com/oauth/v1/accesstoken?grant_type=cert";
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", "Basic YVBxc2pGMGhoQWo3YjZ4WVpJRTRQQTZqZXpubDRFUFQ6T0FmV2ZOU25qNVhoY2Rabw==");
                formParams.put("username", "BA261870");
                formParams.put("password", "despwd");
                formParams.put("grant_type", "password");
                break;

            case CLIENTS:
                BASE_URL_COMPLETA = "https://eu-pre-internal-brazil.apis.allianz/v1/oauth-clientes/accesstoken";
                headers.put("Authorization", "Basic Ylc4NkRaNWg1aUc4OUlBcUhPWHhnOHlJOXM5cnlIS0c6Mm9LNEZTUmVoOWREYlV2Yw==");
                headers.put("tipoIdentificacao", "");
                headers.put("senha", "teste123");
                headers.put("identificacao", numCPF);
                formParams.put("grant_type", "client_credentials");
                break;

            case SEGURADO:
                BASE_URL_COMPLETA = "https://eu-pre-internal-brazil.apis.allianz/v1/oauth-segurados/accesstoken";
                headers.put("Authorization", "Basic VWZCUlZ6TjNtZE1pZ2NjMUV5Mm1IcGMwTUVxRFpRcUc6NE81Y21xaGhHQWg4d0J3TA==");
                headers.put("tipoIdentificacao", "");
                headers.put("senha", "teste123");
                headers.put("identificacao", numCPF);
                formParams.put("grant_type", "client_credentials");
                break;

            default:
                throw new IllegalArgumentException("Tipo de autenticação não suportado: " + tipoAutenticacao);
        }

        Response response = RestAssured.given()
                .relaxedHTTPSValidation()
                .headers(headers)
                .formParams(formParams)
                .post(BASE_URL_COMPLETA)
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
//        System.out.println("Token: " + response.jsonPath().getString("access_token"));
        return response.jsonPath().getString("access_token");
    }

    public String getBearerToken(AuthProduct tipoAutenticacao) {
        return getBearerToken(tipoAutenticacao, null);
    }
}
