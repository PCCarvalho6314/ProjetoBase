package com.projeto.seguros.pages;

import com.projeto.seguros.driver.BrowserDriverManager;
import com.projeto.seguros.selectors.LoginPageSelectors;
import com.projeto.seguros.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object para a página de Login
 * Encapsula ações e verificações sem expor seletores
 */
public class LoginPage {

    private WebDriver driver;

    public LoginPage() {
        this.driver = BrowserDriverManager.obterDriver();
    }

    /**
     * Preenche o campo de email
     */
    public LoginPage preencherEmail(String email) {
        WebElement campoEmail = WaitUtils.esperarElementoVisivel(LoginPageSelectors.CAMPO_EMAIL);
        campoEmail.clear();
        campoEmail.sendKeys(email);
        return this;
    }

    /**
     * Preenche o campo de senha
     */
    public LoginPage preencherSenha(String senha) {
        WebElement campoSenha = WaitUtils.esperarElementoVisivel(LoginPageSelectors.CAMPO_SENHA);
        campoSenha.clear();
        campoSenha.sendKeys(senha);
        return this;
    }

    /**
     * Clica no botão de login
     */
    public void clicarBotaoLogin() {
        WebElement botao = WaitUtils.esperarElementoClicavel(LoginPageSelectors.BOTAO_LOGIN);
        botao.click();
    }

    /**
     * Verifica se a mensagem de erro está visível
     */
    public boolean verificarMensagemErro() {
        try {
            WaitUtils.esperarElementoVisivel(LoginPageSelectors.MENSAGEM_ERRO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o texto da mensagem de erro
     */
    public String obterTextoMensagemErro() {
        WebElement mensagem = WaitUtils.esperarElementoVisivel(LoginPageSelectors.MENSAGEM_ERRO);
        return mensagem.getText();
    }

    /**
     * Clica no link de esqueci a senha
     */
    public void clicarEsqueciSenha() {
        WebElement link = WaitUtils.esperarElementoClicavel(LoginPageSelectors.LINK_ESQUECI_SENHA);
        link.click();
    }

    /**
     * Realiza o login com email e senha
     */
    public void fazerLogin(String email, String senha) {
        preencherEmail(email);
        preencherSenha(senha);
        clicarBotaoLogin();
    }
}
