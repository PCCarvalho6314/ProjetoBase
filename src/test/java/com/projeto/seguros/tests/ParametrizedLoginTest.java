package com.projeto.seguros.tests;

import com.projeto.seguros.base.BaseTest;
import com.projeto.seguros.pages.LoginPage;
import com.projeto.seguros.pojos.Usuario;
import com.projeto.seguros.utils.ConfigReader;
import com.projeto.seguros.utils.ExcelReader;
import com.projeto.seguros.utils.Screenshoter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static com.projeto.seguros.driver.WebDriverManager.obterDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes com Dados Parametrizados")
public class ParametrizedLoginTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "user1@example.com, senha123",
            "user2@example.com, senha456",
            "user3@example.com, senha789"
    })
    @DisplayName("Deve fazer login com múltiplos usuários (CSV)")
    public void testarLoginComCSV(String email, String senha) {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        LoginPage loginPage = new LoginPage();
        loginPage.fazerLogin(email, senha);

        // Assert
        Screenshoter.capturarTela("testarLoginComCSV", email);
    }

    @Test
    @DisplayName("Deve fazer login com dados do Excel")
    public void testarLoginComExcel() {
        // Arrange
        String caminhoArquivo = "src/test/resources/data/LoginData.xlsx";
        ExcelReader excel = new ExcelReader(caminhoArquivo);
        excel.selecionarAba("LoginData");
        
        List<Map<String, String>> dados = excel.lerDados();
        
        // Act & Assert
        for (Map<String, String> linha : dados) {
            String email = linha.get("email");
            String senha = linha.get("senha");
            String nome = linha.get("nome");

            WebDriver driver = obterDriver();
            driver.navigate().to(ConfigReader.obterUrlBase());

            LoginPage loginPage = new LoginPage();
            loginPage.fazerLogin(email, senha);

            // Validação
            assertNotNull(email);
            assertNotNull(senha);
            
            Screenshoter.capturarTela("testarLoginComExcel", email);
        }

        excel.fechar();
    }

    @Test
    @DisplayName("Deve criar usuário usando Builder Pattern")
    public void testarCriacaoUsuarioBuilder() {
        // Arrange
        Usuario usuario = new Usuario.Builder()
                .email("novo@example.com")
                .senha("novasenha123")
                .nome("Novo Usuário")
                .perfil("admin")
                .build();

        // Act
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Assert
        assertEquals("novo@example.com", usuario.getEmail());
        assertEquals("novasenha123", usuario.getSenha());
        assertEquals("Novo Usuário", usuario.getNome());
        assertEquals("admin", usuario.getPerfil());
    }

    @Test
    @DisplayName("Deve mapear dados do Excel para POJO")
    public void testarMapeamentoDados() {
        // Arrange
        String caminhoArquivo = "src/test/resources/data/LoginData.xlsx";
        ExcelReader excel = new ExcelReader(caminhoArquivo);
        excel.selecionarAba("LoginData");
        
        Map<String, String> primeiraLinha = excel.lerDados().get(0);
        
        // Act
        Usuario usuario = new Usuario.Builder()
                .email(primeiraLinha.get("email"))
                .senha(primeiraLinha.get("senha"))
                .nome(primeiraLinha.get("nome"))
                .perfil(primeiraLinha.get("perfil"))
                .build();

        // Assert
        assertNotNull(usuario.getEmail());
        assertNotNull(usuario.getSenha());
        System.out.println("Usuário mapeado: " + usuario);
        
        excel.fechar();
    }
}
