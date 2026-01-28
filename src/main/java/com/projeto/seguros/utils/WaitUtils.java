package com.projeto.seguros.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.projeto.seguros.driver.WebDriverManager;

import java.time.Duration;

public class WaitUtils {

    private static final long TIMEOUT = ConfigReader.obterTimeoutExplicito();

    /**
     * Aguarda até que um elemento esteja presente no DOM
     */
    public static WebElement esperarElementoPresente(By localizador) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    /**
     * Aguarda até que um elemento esteja visível
     */
    public static WebElement esperarElementoVisivel(By localizador) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }

    /**
     * Aguarda até que um elemento seja clicável
     */
    public static WebElement esperarElementoClicavel(By localizador) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }

    /**
     * Aguarda até que um elemento tenha o texto específico
     */
    public static boolean esperarTextoNoElemento(By localizador, String texto) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(localizador, texto));
    }

    /**
     * Aguarda até que o título da página contenha o texto
     */
    public static boolean esperarTituloPagina(String titulo) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.titleContains(titulo));
    }

    /**
     * Aguarda até que a URL da página seja igual à especificada
     */
    public static boolean esperarUrlPagina(String url) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.urlMatches(url));
    }

    /**
     * Aguarda com tempo customizado
     */
    public static WebElement esperarComTimeout(By localizador, long segundos) {
        WebDriver driver = WebDriverManager.obterDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }
}
