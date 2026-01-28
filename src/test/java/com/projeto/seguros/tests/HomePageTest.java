package com.projeto.seguros.tests;

import com.projeto.seguros.base.BaseTest;
import com.projeto.seguros.pages.HomePage;
import com.projeto.seguros.utils.ConfigReader;
import com.projeto.seguros.utils.Screenshoter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;

import static com.projeto.seguros.driver.BrowserDriverManager.obterDriver;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Homepage")
public class HomePageTest extends BaseTest {

    @Test
    @DisplayName("Deve exibir homepage com navegação principal")
    @Disabled("Requer servidor web rodando")
    public void testarExibicaoHomePage() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        HomePage homePage = new HomePage().aguardarCarregamento();

        // Assert
        assertTrue(homePage.estaVisivel(org.openqa.selenium.By.id("mainMenu")), 
                   "Menu principal não está visível");
        
        Screenshoter.capturarTela("testarExibicaoHomePage");
    }

    @Test
    @DisplayName("Deve navegar para menu específico")
    @Disabled("Requer servidor web rodando")
    public void testarNavegacaoMenu() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        HomePage homePage = new HomePage()
                .aguardarCarregamento()
                .clicarNoMenu("Produtos");

        // Assert
        assertNotNull(driver.getCurrentUrl());
        Screenshoter.capturarTela("testarNavegacaoMenu");
    }

    @Test
    @DisplayName("Deve exibir número de notificações")
    @Disabled("Requer servidor web rodando")
    public void testarNotificacoes() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        HomePage homePage = new HomePage().aguardarCarregamento();
        int numNotificacoes = homePage.obterNumeroNotificacoes();

        // Assert
        assertTrue(numNotificacoes >= 0, "Número de notificações deve ser >= 0");
        Screenshoter.capturarTela("testarNotificacoes");
    }

    @Test
    @DisplayName("Deve fazer logout da página")
    @Disabled("Requer servidor web rodando")
    public void testarLogout() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());

        // Act
        HomePage homePage = new HomePage().aguardarCarregamento();
        homePage.fazerLogout();

        // Assert
        // Adicione validações de redirecionamento para login page
        Screenshoter.capturarTela("testarLogout", "apos_logout");
    }
}
