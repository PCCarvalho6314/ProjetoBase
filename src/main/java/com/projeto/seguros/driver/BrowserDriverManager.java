package com.projeto.seguros.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserDriverManager {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    /**
     * Inicializa o WebDriver para o navegador especificado
     */
    public static void inicializarDriver(String browser) {
        if (driverThread.get() == null) {
            driverThread.set(criarDriver(browser));
        }
    }

    /**
     * Cria uma instância do WebDriver
     */
    private static WebDriver criarDriver(String browser) {
        WebDriver driver;
        
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browser);
        }
        
        return driver;
    }

    /**
     * Obtém a instância do WebDriver da thread atual
     */
    public static WebDriver obterDriver() {
        WebDriver driver = driverThread.get();
        if (driver == null) {
            throw new RuntimeException("WebDriver não foi inicializado. Chame inicializarDriver() primeiro.");
        }
        return driver;
    }

    /**
     * Fecha o WebDriver e remove da thread
     */
    public static void fecharDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    /**
     * Limpa todos os dados da thread
     */
    public static void limparThreadLocal() {
        driverThread.remove();
    }
}
