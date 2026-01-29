package utils.api;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;


public class ApiHelper {
    private final RequestSpecification request;

    //Construtor
    public ApiHelper() {
        request = RestAssured.given();
    }

//------------------------><--------------------------//

    //Setter Base Uri
    @Step("Definindo a URL base:")
    public void setBaseUri(String urlBase) {
        request.baseUri(urlBase);
    }

    //Headers
    @Step("Adicionando headers de autenticação:")
    public ApiHelper adicionarHeadersAutenticacao(Map<String, String> headers) {
        request.headers(headers);
        return this;
    }

    @Step("Adicionando headers:")
    public ApiHelper adicionarHeaders(Map<String, String> headers) {
        request.headers(headers);
        return this;
    }

    //FormParams
    @Step("Adicionando parâmetros de formulário:")
    public ApiHelper adicionarFormParams(Map<String, String> formParams) {
        request.formParams(formParams);
        return this;
    }

    //Params
    @Step("Adicionando parâmetros:")
    public ApiHelper adicionarParams(Map<String, String> params) {
        request.params(params);
        return this;
    }

//-----------------------------------><----------------------------------//

    //Tipos de Métodos de requisições
    @Step("Fazendo a requisição do método GET:")
    public Response fazerGet(String endpoint) {
        return request.relaxedHTTPSValidation()
                .get(endpoint);
    }

    @Step("Fazendo a requisição do método POST")
    public Response fazerPost(String endpoint, String body) {
        return request.relaxedHTTPSValidation()
                .body(body)
                .post(endpoint);
    }

    @Step("Fazendo a requisição do método PUT")
    public Response fazerPut(String endpoint, String body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(endpoint);
    }

    @Step("Fazendo uma requisição DELETE para {endpoint}")
    public Response fazerDelete(String endpoint) {
        return RestAssured
                .given()
                .when()
                .delete(endpoint);
    }

//-------------------------><-----------------------------//

    //Validações
    @Step("Validando o status da resposta")
    public void validarStatus(int statusCodeObtido, int statusCodeEsperado) {
        try {
            Assert.assertEquals(statusCodeObtido, statusCodeEsperado);
        } catch (AssertionError e) {
            System.out.println("Status Code validation failed.");
            e.getMessage();
            throw e;
        }
    }


    @Step("Validando se o corpo da resposta contém o valor: {expectedBody}")
    public void validarCorpo(Response response, String expectedBody) {
        try {
            response.then().body(org.hamcrest.Matchers.containsString(expectedBody));
        } catch (AssertionError e) {
            System.out.println("Validação de corpo do Respose falhou. O valor: '" + expectedBody + "' não foi encontrado no corpo do Response.");
            e.getMessage();
            throw e;
        }
    }

    //Esse método é apenas para validação de campo vazio/nulo,
    // para mostrar conteúdo do campo, usar o próximo método
    @Step("Validando o campo {1} da resposta contém dados (não é vazio ou nulo)")
    public void validarSeCampoNaoVazio(Response response, String caminhoCampo) {
        // Extraindo apenas o nome final do caminho (para uma melhor visualização dos logs no Allure)
        String nomeCampo = caminhoCampo.substring(caminhoCampo.lastIndexOf('.') + 1);

        try {
            // Extraindo o corpo da resposta como JsonPath
            JsonPath jsonPath = response.jsonPath();

            // Obtendo o valor do campo passado no parâmetro
            Object campoValor = jsonPath.get(caminhoCampo);

            // Verificando se o campo não é nulo
            assertThat("O campo " + nomeCampo + " não deve ser nulo", campoValor, is(notNullValue()));

            // Verificando se o campo não é vazio
            if (campoValor instanceof String) {
                assertThat("O campo " + nomeCampo + " não deve ser vazio", ((String) campoValor).isEmpty(), is(false));
            } else if (campoValor instanceof Integer) {
                assertThat("O campo " + nomeCampo + " não deve ser vazio", ((Integer) campoValor).toString().isEmpty(), is(false));
            } else if (campoValor instanceof Collection<?>) {
                assertThat("O campo " + nomeCampo + " não deve ser vazio", ((Collection<?>) campoValor).isEmpty(), is(false));
            } else if (campoValor instanceof Object[]) {
                assertThat("O campo " + nomeCampo + " não deve ser vazio", ((Object[]) campoValor).length, is(greaterThan(0)));
            } else if (campoValor instanceof Map) {
                assertThat("O campo " + nomeCampo + " não deve ser vazio", ((Map<?, ?>) campoValor).size(), is(greaterThan(0)));
            } else {
                throw new IllegalArgumentException("Tipo de campo não suportado: " + campoValor.getClass().getSimpleName());
            }
        } catch (AssertionError e) {
            Allure.step("O campo presente no caminho: " + caminhoCampo + " retornou vazio ou nulo");
            throw e;
        }
    }


    @Step("Validando se o campo {1} da resposta em XML retornou algum conteúdo (não é vazio ou nulo)")
    public void validarSeCampoNaoNuloNaoVazioNaoZerado_Xml(XmlPath response, String caminhoCampo) {
        // Extraindo apenas o nome final do caminho (para uma melhor visualização dos logs no Allure)
        String nomeCampo = caminhoCampo.substring(caminhoCampo.lastIndexOf('.') + 1);

        try {
//            // Obtendo o valor do campo passado no parâmetro
            Object campoValor = response.get(caminhoCampo);

            // Verificando se o campo não é nulo
            assertThat("O campo " + nomeCampo + " não deve ser nulo", campoValor, is(notNullValue()));

            // Verificando se o campo não está vazio
            Assert.assertTrue(!caminhoCampo.isEmpty());

            // Verificando se o campo não é igua a zero
            Assert.assertTrue(!campoValor.equals("0"));

        } catch (AssertionError e) {
            Allure.step("O campo " + nomeCampo + " presente no caminho: " + caminhoCampo + " retornou o valor com zero, vazio ou nulo");
            throw e;
        }
    }

    //Método que traz o valor do campo, além de identificar se é nulo/vazio
    @Step("Validando o campo {1} da resposta contém dados (não é vazio ou nulo)")
    public void validarCampoNaoVazio(Response response, String caminhoCampo) {
        // Extraindo apenas o nome final do caminho (para uma melhor visualização dos logs no Allure)
        String nomeCampo = caminhoCampo.substring(caminhoCampo.lastIndexOf('.') + 1);

        validarSeCampoNaoVazio(response, caminhoCampo);

        // Obtendo o valor do campo especificado
        Object campoValor = response.jsonPath().get(caminhoCampo).toString();
        Allure.step("Conteúdo do campo '" + nomeCampo + "': " + campoValor);
    }
}

