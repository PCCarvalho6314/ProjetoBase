package com.projeto.seguros.pages;

import com.projeto.seguros.driver.WebDriverManager;
import com.projeto.seguros.utils.WaitUtils;
import com.projeto.seguros.utils.ActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object - Exemplo de página com múltiplas funcionalidades
 * Demonstra uso de fluent interface (padrão builder)
 */
public class HomePage {

    private WebDriver driver;

    // Seletores
    private static final By BOTAO_SAIR = By.id("logoutButton");
    private static final By NOME_USUARIO = By.className("userName");
    private static final By MENU_PRINCIPAL = By.id("mainMenu");
    private static final By ITEM_MENU = By.xpath("//nav[@id='mainMenu']//a[text()='%s']");
    private static final By NOTIFICACOES = By.id("notifications");
    private static final By CONTADOR_NOTIFICACOES = By.className("notificationCount");

    public HomePage() {
        this.driver = WebDriverManager.obterDriver();
    }

    /**
     * Verifica se o usuário está logado
     */
    public boolean estaLogado() {
        try {
            WaitUtils.esperarElementoVisivel(NOME_USUARIO);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o nome do usuário logado
     */
    public String obterNomeUsuario() {
        WebElement elemento = WaitUtils.esperarElementoVisivel(NOME_USUARIO);
        return elemento.getText();
    }

    /**
     * Clica em um item do menu
     */
    public HomePage clicarNoMenu(String itemMenu) {
        By seletor = By.xpath(String.format("//nav[@id='mainMenu']//a[text()='%s']", itemMenu));
        WebElement elemento = WaitUtils.esperarElementoClicavel(seletor);
        ActionUtils.moveEClica(elemento);
        return this;
    }

    /**
     * Faz hover no menu
     */
    public HomePage hoverNoMenu() {
        WebElement menu = WaitUtils.esperarElementoVisivel(MENU_PRINCIPAL);
        ActionUtils.hover(menu);
        return this;
    }

    /**
     * Obtém o número de notificações
     */
    public int obterNumeroNotificacoes() {
        try {
            WebElement elemento = WaitUtils.esperarElementoVisivel(CONTADOR_NOTIFICACOES);
            String texto = elemento.getText();
            return Integer.parseInt(texto);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verifica se há notificações
     */
    public boolean temNotificacoes() {
        return obterNumeroNotificacoes() > 0;
    }

    /**
     * Clica nas notificações
     */
    public HomePage clicarNotificacoes() {
        WebElement elemento = WaitUtils.esperarElementoClicavel(NOTIFICACOES);
        elemento.click();
        return this;
    }

    /**
     * Faz logout
     */
    public void fazerLogout() {
        WebElement botao = WaitUtils.esperarElementoClicavel(BOTAO_SAIR);
        botao.click();
    }

    /**
     * Verifica se elemento é visível
     */
    public boolean estaVisivel(By seletor) {
        try {
            WaitUtils.esperarElementoVisivel(seletor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Aguarda carregamento da página
     */
    public HomePage aguardarCarregamento() {
        WaitUtils.esperarElementoVisivel(MENU_PRINCIPAL);
        return this;
    }

    /**
     * Volta para home
     */
    public void voltarParaHome() {
        driver.navigate().to(driver.getCurrentUrl().split("/(?=[^/]*$)")[0]);
    }
}
