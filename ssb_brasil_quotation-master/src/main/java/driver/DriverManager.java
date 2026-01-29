package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Gerenciador de WebDriver com suporte a testes paralelos via ThreadLocal.
 * 
 * Padrão Singleton + ThreadLocal:
 * - Cada thread de teste tem sua própria instância de WebDriver
 * - Previne conflitos em execução paralela
 * - Gerenciamento automático de lifecycle (criação/destruição)
 * 
 * Padrão E → E → S:
 * ENTRADA: Configuração de browser (Chrome, Firefox, Edge)
 * EXECUÇÃO: ThreadLocal cria/armazena driver por thread
 * SAÍDA: WebDriver isolado para cada teste
 */
public class DriverManager {
    
    // ThreadLocal garante que cada thread tenha seu próprio WebDriver
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    
    // Configuração padrão de browser
    private static BrowserType defaultBrowser = BrowserType.CHROME;
    private static boolean headless = false;
    
    // ========================================================================
    // ENUM DE BROWSERS SUPORTADOS
    // ========================================================================
    
    public enum BrowserType {
        CHROME,
        FIREFOX,
        EDGE
    }
    
    // ========================================================================
    // CONFIGURAÇÃO
    // ========================================================================
    
    /**
     * Define o browser padrão a ser usado.
     * @param browser Tipo de browser (CHROME, FIREFOX, EDGE)
     */
    public static void setDefaultBrowser(BrowserType browser) {
        defaultBrowser = browser;
    }
    
    /**
     * Define se o browser deve executar em modo headless.
     * @param headless true para executar sem interface gráfica
     */
    public static void setHeadless(boolean headless) {
        DriverManager.headless = headless;
    }
    
    // ========================================================================
    // GERENCIAMENTO DE DRIVER
    // ========================================================================
    
    /**
     * Obtém o WebDriver da thread atual.
     * Se não existir, cria um novo com as configurações padrão.
     * 
     * @return WebDriver da thread atual
     */
    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver(defaultBrowser));
        }
        return driverThreadLocal.get();
    }
    
    /**
     * Obtém o WebDriver da thread atual especificando o browser.
     * 
     * @param browser Tipo de browser desejado
     * @return WebDriver da thread atual
     */
    public static WebDriver getDriver(BrowserType browser) {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver(browser));
        }
        return driverThreadLocal.get();
    }
    
    /**
     * Cria uma nova instância de WebDriver baseado no tipo especificado.
     * Usa WebDriverManager para gerenciamento automático de binários.
     * 
     * @param browser Tipo de browser
     * @return Nova instância de WebDriver
     */
    private static WebDriver createDriver(BrowserType browser) {
        WebDriver driver;
        
        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                configureChromeOptions(chromeOptions);
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                configureFirefoxOptions(firefoxOptions);
                driver = new FirefoxDriver(firefoxOptions);
                break;
                
            case EDGE:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                configureEdgeOptions(edgeOptions);
                driver = new EdgeDriver(edgeOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Browser não suportado: " + browser);
        }
        
        // Configurações comuns a todos os browsers
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        return driver;
    }
    
    /**
     * Fecha o WebDriver da thread atual e remove da ThreadLocal.
     * SEMPRE chamar este método no @AfterEach ou @AfterMethod.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Erro ao fechar driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
    
    // ========================================================================
    // CONFIGURAÇÕES DE OPÇÕES DOS BROWSERS
    // ========================================================================
    
    /**
     * Configura opções específicas do Chrome.
     * @param options ChromeOptions a ser configurado
     */
    private static void configureChromeOptions(ChromeOptions options) {
        if (headless) {
            options.addArguments("--headless=new"); // Novo modo headless do Chrome
        }
        
        // Opções de performance e estabilidade
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-blink-features=AutomationControlled");
        
        // Desabilitar logs desnecessários
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-logging"});
        
        // Preferências do Chrome
        options.setExperimentalOption("prefs", java.util.Map.of(
            "download.prompt_for_download", false,
            "download.default_directory", System.getProperty("user.dir") + "/downloads",
            "safebrowsing.enabled", true
        ));
    }
    
    /**
     * Configura opções específicas do Firefox.
     * @param options FirefoxOptions a ser configurado
     */
    private static void configureFirefoxOptions(FirefoxOptions options) {
        if (headless) {
            options.addArguments("--headless");
        }
        
        // Opções de performance
        options.addArguments("--disable-gpu");
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("geo.enabled", false);
    }
    
    /**
     * Configura opções específicas do Edge.
     * @param options EdgeOptions a ser configurado
     */
    private static void configureEdgeOptions(EdgeOptions options) {
        if (headless) {
            options.addArguments("--headless=new");
        }
        
        // Opções similares ao Chrome
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
    }
    
    // ========================================================================
    // MÉTODOS UTILITÁRIOS
    // ========================================================================
    
    /**
     * Verifica se existe um driver ativo na thread atual.
     * @return true se o driver existe e está ativo
     */
    public static boolean hasActiveDriver() {
        return driverThreadLocal.get() != null;
    }
    
    /**
     * Obtém o tipo de browser do driver ativo.
     * @return Nome do browser ou "UNKNOWN" se não houver driver ativo
     */
    public static String getCurrentBrowserType() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            String driverClass = driver.getClass().getSimpleName();
            return driverClass.replace("Driver", "").toUpperCase();
        }
        return "UNKNOWN";
    }
}
