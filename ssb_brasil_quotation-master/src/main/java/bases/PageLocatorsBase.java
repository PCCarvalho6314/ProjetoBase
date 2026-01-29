package bases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe base que centraliza todos os locadores reutilizáveis entre as páginas.
 * Reduz duplicação de código de ~600 locadores para fonte única de verdade.
 * 
 * Padrão: ENTRADA → EXECUÇÃO → SAÍDA
 * - ENTRADA: Locadores centralizados
 * - EXECUÇÃO: Métodos compartilhados (click, wait, fill)
 * - SAÍDA: Ação executada com wait integrado
 */
public abstract class PageLocatorsBase extends Base {
    
    // ========================================================================
    // LOCADORES GLOBAIS - Reutilizáveis em múltiplas páginas
    // ========================================================================
    
    // Botões de navegação
    protected static final String BUTTON_AVANCAR_XPATH = "//button[@id='NextButton' or @id='nextPagePrice']";
    protected static final String BUTTON_VOLTAR_XPATH = "//button[@id='BackButton']";
    protected static final String BUTTON_COTIZAR_XPATH = "//button[@id='btnCotizar']";
    protected static final String BUTTON_EMITIR_XPATH = "//button[@id='btnEmitir']";
    
    // Indicadores de loading
    protected static final String LOADING_BACKGROUND_XPATH = "//*[@id='tblDialogProcess']";
    protected static final String LOADING_SPINNER_XPATH = "//div[@class='spinner']";
    
    // Mensagens de erro
    protected static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";
    protected static final String ALERT_ERROR_XPATH = "//div[@class='alert alert-danger']";
    protected static final String MODAL_ERRO_XPATH = "//div[@class='modal-body']//span[@class='error']";
    
    // Botões Sim/Não (perguntas comuns)
    protected static final String BUTTON_CPF_NAO_XPATH = "//button[@id='BCPFNaoM']";
    protected static final String BUTTON_CPF_SIM_XPATH = "//button[@id='BCPFSimM']";
    protected static final String BUTTON_COMERCIAL_SIM_XPATH = "//button[@id='BComercialSimM']";
    protected static final String BUTTON_COMERCIAL_NAO_XPATH = "//button[@id='BComercialNaoM']";
    protected static final String BUTTON_BLINDAGEM_SIM_XPATH = "(//button[@id='BBlindagemSimM'])[1]";
    protected static final String BUTTON_BLINDAGEM_NAO_XPATH = "(//button[@id='BBlindagemNaoM'])[1]";
    protected static final String BUTTON_KIT_GAS_SIM_XPATH = "//button[@id='BKitGasSimM']";
    protected static final String BUTTON_KIT_GAS_NAO_XPATH = "//button[@id='BKitGasNaoM']";
    
    // Campos comuns
    protected static final String INPUT_CEP_ID = "CEPRiesgo";
    protected static final String INPUT_NUMERO_ID = "NumeroRiesgo";
    protected static final String INPUT_COMPLEMENTO_ID = "ComplementoRiesgo";
    
    // Dropdowns comuns
    protected static final String SELECT_UF_ID = "DepartamentoRiesgo";
    protected static final String SELECT_CIDADE_ID = "MunicipioRiesgo";
    
    // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    public PageLocatorsBase(WebDriver driver) {
        super(driver);
    }
    
    // ========================================================================
    // MÉTODOS COMPARTILHADOS - Ações + Waits integrados
    // ========================================================================
    
    /**
     * Avança para a próxima página do fluxo.
     * Aguarda automaticamente o loading desaparecer.
     */
    protected void avancarProxima() {
        clickElement(By.xpath(BUTTON_AVANCAR_XPATH));
        aguardarCarregamento();
    }
    
    /**
     * Volta para a página anterior do fluxo.
     */
    protected void voltarAnterior() {
        clickElement(By.xpath(BUTTON_VOLTAR_XPATH));
        aguardarCarregamento();
    }
    
    /**
     * Aguarda o loading/spinner desaparecer.
     * Timeout padrão: 30 segundos.
     */
    protected void aguardarCarregamento() {
        waitForElementInvisible(By.xpath(LOADING_BACKGROUND_XPATH));
    }
    
    /**
     * Aguarda o loading com timeout customizado.
     * @param timeoutSeconds Timeout em segundos
     */
    protected void aguardarCarregamento(int timeoutSeconds) {
        waitForElementInvisible(By.xpath(LOADING_BACKGROUND_XPATH), timeoutSeconds);
    }
    
    /**
     * Obtém todos os erros exibidos na tela.
     * @return Lista de mensagens de erro
     */
    protected List<String> obterErrosExibidos() {
        waitForElementVisible(By.xpath(LISTA_ERROS_XPATH));
        List<WebElement> elementos = driver.findElements(By.xpath(LISTA_ERROS_XPATH));
        return elementos.stream()
                .map(WebElement::getText)
                .filter(text -> text != null && !text.trim().isEmpty())
                .collect(Collectors.toList());
    }
    
    /**
     * Verifica se há erros na página.
     * @return true se houver erros visíveis
     */
    protected boolean existemErros() {
        return isElementPresent(By.xpath(LISTA_ERROS_XPATH));
    }
    
    /**
     * Seleciona opção Sim/Não em botões modais.
     * @param botaoSimBy Locator do botão "Sim"
     * @param botaoNaoBy Locator do botão "Não"
     * @param opcao "sim" ou "não"
     */
    protected void selecionarSimNao(By botaoSimBy, By botaoNaoBy, String opcao) {
        if (opcao == null) return;
        
        if ("sim".equalsIgnoreCase(opcao.trim())) {
            waitForElementClickable(botaoSimBy);
            clickElement(botaoSimBy);
        } else if ("não".equalsIgnoreCase(opcao.trim()) || "nao".equalsIgnoreCase(opcao.trim())) {
            waitForElementClickable(botaoNaoBy);
            clickElement(botaoNaoBy);
        }
    }
    
    /**
     * Preenche CEP e aguarda carregamento dos dados de endereço.
     * @param cep CEP a ser preenchido
     */
    protected void preencherCEP(String cep) {
        if (cep == null || cep.trim().isEmpty()) return;
        
        fillText(By.id(INPUT_CEP_ID), cep);
        aguardarCarregamento();
    }
    
    /**
     * Preenche endereço completo (número e complemento).
     * @param numero Número do endereço
     * @param complemento Complemento do endereço
     */
    protected void preencherEndereco(String numero, String complemento) {
        if (numero != null && !numero.trim().isEmpty()) {
            fillText(By.id(INPUT_NUMERO_ID), numero);
        }
        if (complemento != null && !complemento.trim().isEmpty()) {
            fillText(By.id(INPUT_COMPLEMENTO_ID), complemento);
        }
    }
    
    /**
     * Seleciona estado (UF) no dropdown.
     * @param uf Sigla do estado (ex: "SP", "RJ")
     */
    protected void selecionarUF(String uf) {
        if (uf == null || uf.trim().isEmpty()) return;
        
        selectByVisibleText(By.id(SELECT_UF_ID), uf);
        aguardarCarregamento();
    }
    
    /**
     * Seleciona cidade no dropdown.
     * @param cidade Nome da cidade
     */
    protected void selecionarCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) return;
        
        selectByVisibleText(By.id(SELECT_CIDADE_ID), cidade);
    }
    
    /**
     * Seleciona uso comercial (Sim/Não).
     * @param opcao "sim" ou "não"
     */
    protected void selecionarUsoComercial(String opcao) {
        selecionarSimNao(
            By.xpath(BUTTON_COMERCIAL_SIM_XPATH),
            By.xpath(BUTTON_COMERCIAL_NAO_XPATH),
            opcao
        );
    }
    
    /**
     * Seleciona blindagem (Sim/Não).
     * @param opcao "sim" ou "não"
     */
    protected void selecionarBlindagem(String opcao) {
        selecionarSimNao(
            By.xpath(BUTTON_BLINDAGEM_SIM_XPATH),
            By.xpath(BUTTON_BLINDAGEM_NAO_XPATH),
            opcao
        );
    }
    
    /**
     * Seleciona kit gás (Sim/Não).
     * @param opcao "sim" ou "não"
     */
    protected void selecionarKitGas(String opcao) {
        selecionarSimNao(
            By.xpath(BUTTON_KIT_GAS_SIM_XPATH),
            By.xpath(BUTTON_KIT_GAS_NAO_XPATH),
            opcao
        );
    }
    
    /**
     * Clica no botão Cotizar.
     */
    protected void cotizar() {
        clickElement(By.xpath(BUTTON_COTIZAR_XPATH));
        aguardarCarregamento();
    }
    
    /**
     * Clica no botão Emitir.
     */
    protected void emitir() {
        clickElement(By.xpath(BUTTON_EMITIR_XPATH));
        aguardarCarregamento();
    }
    
    // ========================================================================
    // MÉTODOS UTILITÁRIOS ABSTRATOS - Implementados em Base.java
    // ========================================================================
    
    protected abstract void clickElement(By by);
    protected abstract void fillText(By by, String text);
    protected abstract void selectByVisibleText(By by, String text);
    protected abstract void waitForElementVisible(By by);
    protected abstract void waitForElementInvisible(By by);
    protected abstract void waitForElementInvisible(By by, int timeoutSeconds);
    protected abstract void waitForElementClickable(By by);
    protected abstract boolean isElementPresent(By by);
}
