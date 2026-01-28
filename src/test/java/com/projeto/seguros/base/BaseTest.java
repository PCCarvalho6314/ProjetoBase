package com.projeto.seguros.base;

import com.projeto.seguros.driver.WebDriverManager;
import com.projeto.seguros.utils.ConfigReader;
import com.projeto.seguros.utils.Screenshoter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public abstract class BaseTest {

    /**
     * Executa antes de cada teste
     * Inicializa o WebDriver
     */
    @BeforeEach
    public void setUp() {
        String navegador = ConfigReader.obterNavegador();
        WebDriverManager.inicializarDriver(navegador);
    }

    /**
     * Executa após cada teste
     * Fecha o WebDriver
     */
    @AfterEach
    public void tearDown() {
        WebDriverManager.fecharDriver();
    }

    /**
     * Captura screenshot em caso de falha
     */
    protected void capturarScreenshotEmCasoDeErro(String nomeTeste) {
        Screenshoter.capturarTela(nomeTeste, "erro");
    }
}
