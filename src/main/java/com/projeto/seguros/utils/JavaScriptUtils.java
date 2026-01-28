package com.projeto.seguros.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.projeto.seguros.driver.BrowserDriverManager;

/**
 * Utilitários para executar scripts JavaScript
 */
public class JavaScriptUtils {

    /**
     * Executa um script JavaScript
     */
    public static Object executar(String script) {
        WebDriver driver = BrowserDriverManager.obterDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }

    /**
     * Executa um script JavaScript com argumentos
     */
    public static Object executar(String script, Object... args) {
        WebDriver driver = BrowserDriverManager.obterDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    /**
     * Scroll até um elemento
     */
    public static void scrollParaElemento(WebElement elemento) {
        WebDriver driver = BrowserDriverManager.obterDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    /**
     * Scroll até posição específica
     */
    public static void scrollParaPosicao(int x, int y) {
        executar(String.format("window.scrollTo(%d, %d);", x, y));
    }

    /**
     * Click via JavaScript (para elementos ocultos)
     */
    public static void clickViaJavaScript(WebElement elemento) {
        executar("arguments[0].click();", elemento);
    }

    /**
     * Enviar texto via JavaScript
     */
    public static void enviarTextoViaJavaScript(WebElement elemento, String texto) {
        executar("arguments[0].value = '" + texto + "';", elemento);
    }

    /**
     * Obtém o valor de um atributo
     */
    public static String obterAtributo(WebElement elemento, String atributo) {
        return (String) executar("return arguments[0].getAttribute('" + atributo + "');", elemento);
    }

    /**
     * Verifica se página está pronta
     */
    public static boolean paginaPronta() {
        Object ready = executar("return document.readyState");
        return ready.toString().equalsIgnoreCase("complete");
    }

    /**
     * Aguarda jQuery estar carregado
     */
    public static void aguardarJQuery() {
        WebDriver driver = BrowserDriverManager.obterDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return jQuery != undefined", "");
    }

    /**
     * Aguarda AJAX estar completo
     */
    public static void aguardarAjax() {
        Object resultado = executar("return (typeof jQuery != 'undefined') ? jQuery.active == 0 : true");
        while (!(boolean) resultado) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            resultado = executar("return (typeof jQuery != 'undefined') ? jQuery.active == 0 : true");
        }
    }

    /**
     * Obtém o texto visível do elemento
     */
    public static String obterTextoVisivelJavaScript(WebElement elemento) {
        return (String) executar("return arguments[0].innerText;", elemento);
    }
}
