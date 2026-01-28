package com.projeto.seguros.tests;

import com.projeto.seguros.base.BaseTest;
import com.projeto.seguros.pages.LoginPage;
import com.projeto.seguros.pojos.Usuario;
import com.projeto.seguros.utils.ConfigReader;
import com.projeto.seguros.utils.Screenshoter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.projeto.seguros.driver.WebDriverManager.obterDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Login")
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Deve fazer login com credenciais válidas")
    public void testarLoginComCredenciaisValidas() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());
        
        Usuario usuario = new Usuario.Builder()
                .email("user@example.com")
                .senha("senha123")
                .build();

        // Act
        LoginPage loginPage = new LoginPage();
        loginPage.fazerLogin(usuario.getEmail(), usuario.getSenha());

        // Assert
        // Adicione suas asserções aqui
        Screenshoter.capturarTela("testarLoginComCredenciaisValidas", "sucesso");
    }

    @Test
    @DisplayName("Deve exibir erro com credenciais inválidas")
    public void testarLoginComCredenciaisInvalidas() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        LoginPage loginPage = new LoginPage();
        loginPage.fazerLogin("usuario_invalido@example.com", "senha_errada");

        // Assert
        assertTrue(loginPage.verificarMensagemErro(), "Mensagem de erro não foi exibida");
        Screenshoter.capturarTela("testarLoginComCredenciaisInvalidas", "erro");
    }

    @Test
    @DisplayName("Deve abrir página de recuperação de senha")
    public void testarLinkEsqueciSenha() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        LoginPage loginPage = new LoginPage();
        loginPage.clicarEsqueciSenha();

        // Assert
        // Adicione suas asserções aqui para validar a página de recuperação
        Screenshoter.capturarTela("testarLinkEsqueciSenha", "resultado");
    }
}
