package com.projeto.seguros.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.projeto.seguros.driver.BrowserDriverManager;

/**
 * Utilitários para interações com mouse e teclado
 */
public class ActionUtils {

    /**
     * Hover (passa o mouse) sobre um elemento
     */
    public static void hover(WebElement elemento) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).moveToElement(elemento).perform();
    }

    /**
     * Duplo clique em um elemento
     */
    public static void duploClique(WebElement elemento) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).doubleClick(elemento).perform();
    }

    /**
     * Clique com botão direito (Context Menu)
     */
    public static void cliqueBotaoDireito(WebElement elemento) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).contextClick(elemento).perform();
    }

    /**
     * Drag and Drop
     */
    public static void dragDrop(WebElement origem, WebElement destino) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).dragAndDrop(origem, destino).perform();
    }

    /**
     * Drag and Drop para posição (x, y)
     */
    public static void dragDropPorOffset(WebElement elemento, int x, int y) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).dragAndDropBy(elemento, x, y).perform();
    }

    /**
     * Pressiona uma tecla
     */
    public static void pressionarTecla(String tecla) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).sendKeys(tecla).perform();
    }

    /**
     * Combinação de teclas (ex: Ctrl+A)
     */
    public static void combinacaoTeclas(String tecla1, String tecla2) {
        WebDriver driver = WebDriverManager.obterDriver();
        Actions actions = new Actions(driver);
        actions.keyDown(tecla1).sendKeys(tecla2).keyUp(tecla1).perform();
    }

    /**
     * Clique e espera
     */
    public static void cliquePausa(WebElement elemento, long ms) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).click(elemento).pause(ms).perform();
    }

    /**
     * Move para elemento e clica
     */
    public static void moveEClica(WebElement elemento) {
        WebDriver driver = WebDriverManager.obterDriver();
        new Actions(driver).moveToElement(elemento).click().perform();
    }

    /**
     * Seleciona múltiplos elementos com Ctrl+Click
     */
    public static void selecionarMultiplos(WebElement... elementos) {
        WebDriver driver = WebDriverManager.obterDriver();
        Actions actions = new Actions(driver);
        
        for (WebElement elemento : elementos) {
            actions.keyDown("control").click(elemento).keyUp("control");
        }
        actions.perform();
    }
}
