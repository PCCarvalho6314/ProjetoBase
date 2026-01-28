package com.projeto.seguros.selectors;

import org.openqa.selenium.By;

/**
 * Seletores da página de login
 * Armazena apenas as definições dos elementos, sem lógica de ação
 */
public class LoginPageSelectors {

    public static final By CAMPO_EMAIL = By.id("email");
    public static final By CAMPO_SENHA = By.id("password");
    public static final By BOTAO_LOGIN = By.id("loginButton");
    public static final By MENSAGEM_ERRO = By.className("error-message");
    public static final By LINK_ESQUECI_SENHA = By.linkText("Esqueci a senha");
    public static final By TITULO_PAGINA = By.tagName("h1");
}
