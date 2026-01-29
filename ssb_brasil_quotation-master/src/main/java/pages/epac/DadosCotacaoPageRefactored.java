package pages.epac;

import bases.PageLocatorsBase;
import data.CotacaoAutoData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

/**
 * Page Object refatorado para a página de Dados de Cotação.
 * 
 * REFATORAÇÃO:
 * - ANTES: 798 linhas com locadores misturados
 * - DEPOIS: ~150 linhas focadas em lógica de negócio
 * - Locadores comuns herdados de PageLocatorsBase
 * - Métodos de alto nível com @Step para Allure
 * 
 * Responsabilidade: Preencher formulário de dados iniciais da cotação
 */
public class DadosCotacaoPage extends PageLocatorsBase {
    
    // ========================================================================
    // LOCADORES ESPECÍFICOS DESTA PÁGINA
    // ========================================================================
    
    private static final String INPUT_CHASSI_ID = "chassis";
    private static final String POPUP_SELECT_VEICULO_ID = "FipeListLightbox_row_1";
    private static final String INPUT_NUMERO_CI_XPATH = "//input[@id='numeroCI']";
    private static final String BUTTON_CLASSE_BONUS_XPATH = "//select[@id='classeBonus']//parent::div";
    private static final String INPUT_CPF_SEGURADO_XPATH = "//input[@id='cpfCnpjSegurado']";
    private static final String INPUT_NOME_SEGURADO_XPATH = "//*[@id='insuredName']";
    private static final String INPUT_CPF_CONDUTOR_XPATH = "//input[@id='cpfCnpjCondutor']";
    private static final String BUTTON_ESTADO_CIVIL_XPATH = "//*[@name='comboCivilEstado']/parent::div";
    private static final String BUTTON_FINALIDADE_USO_XPATH = "//select[@id='comboTipoFinalidadeUso1211']//parent::div";
    private static final String BUTTON_VEICULO_ZERO_KM_XPATH = "//button[@id='BKM0SimM']";
    private static final String INPUT_VLR_BLINDAGEM_XPATH = "//input[@id='valorBlindagem']";
    private static final String BUTTON_COBERTURA_18A25_XPATH = "//select[@id='valor17a25VersaoNova']//parent::div";
    private static final String BUTTON_RESIDENCIA_CONDUTOR_XPATH = "//*[@name='valorResideEm']/parent::div";
    private static final String BUTTON_ACESSORIOS_XPATH = "//button[@id='BAcessoriosSimM']";
    private static final String INPUT_ACESSORIOS_MOTO_XPATH = "//input[@id='valorAcessorios_m']";
    private static final String INPUT_ACESSORIOS_CAMINHAO_XPATH = "//input[@id='valorAcessorios_C']";
    private static final String BUTTON_TRABALHA_XPATH = "//button[@id='BTrabalhaSimM']";
    private static final String BUTTON_UTILIZA_TRABALHO_SIM_XPATH = "//button[@id='BUsaParaTrabalharSimM']";
    private static final String BUTTON_GARAGEM_TRABALHO_SIM_XPATH = "//button[@id='BGaragemTrabalhoSimM']";
    private static final String BUTTON_ESTUDA_XPATH = "//button[@id='BEstudaSimM']";
    private static final String BUTTON_UTILIZA_ESTUDO_SIM_XPATH = "//button[@id='BUsaParaEstudarSimM']";
    private static final String BUTTON_GARAGEM_ESTUDO_SIM_XPATH = "//button[@id='BGaragemEstudoSimM']";
    private static final String BUTTON_GARAGEM_RESIDENCIA_SIM_XPATH = "//button[@id='BGaragemResidenciaSimM']";
    private static final String CBOX_MOTORISTA_INDETERMINADO_XPATH = "//label[@id='chkMotoristaIndeterminado2111_label']";
    
    // ========================================================================
    // CONSTRUTOR
    // ========================================================================
    
    public DadosCotacaoPage(WebDriver driver) {
        super(driver);
    }
    
    // ========================================================================
    // MÉTODOS DE ALTO NÍVEL - Fluxos completos
    // ========================================================================
    
    /**
     * Preenche todos os campos do formulário de cotação.
     * Método principal que orquestra o preenchimento completo.
     * 
     * @param data Dados da cotação
     */
    @Step("Preencher dados da cotação")
    public void preencherFormulario(CotacaoAutoData data) {
        preencherDadosVeiculo(data);
        preencherDadosSegurado(data);
        preencherDadosCondutor(data);
        preencherDadosComplementares(data);
    }
    
    /**
     * Preenche apenas os dados do veículo.
     * @param data Dados da cotação
     */
    @Step("Preencher dados do veículo")
    public void preencherDadosVeiculo(CotacaoAutoData data) {
        if (data.getChassi() != null) {
            preencherChassi(data.getChassi());
        }
        
        if (data.getVeiculo0Km() != null) {
            selecionarVeiculoZeroKm(data.getVeiculo0Km());
        }
        
        if (data.getBlindagem() != null) {
            selecionarBlindagem(data.getBlindagem());
            if ("sim".equalsIgnoreCase(data.getBlindagem()) && data.getValorBlindagem() != null) {
                preencherValorBlindagem(data.getValorBlindagem());
            }
        }
        
        if (data.getAcessorios() != null) {
            selecionarAcessorios(data.getAcessorios());
            if ("sim".equalsIgnoreCase(data.getAcessorios()) && data.getAcessoriosValor() != null) {
                preencherValorAcessorios(data.getAcessoriosValor());
            }
        }
    }
    
    /**
     * Preenche dados do segurado.
     * @param data Dados da cotação
     */
    @Step("Preencher dados do segurado")
    public void preencherDadosSegurado(CotacaoAutoData data) {
        if (data.getCpfcnpj() != null) {
            preencherCPFSegurado(data.getCpfcnpj());
        }
        
        if (data.getNumeroCI() != null) {
            preencherNumeroCI(data.getNumeroCI());
        }
        
        if (data.getClasseBonus() != null) {
            selecionarClasseBonus(data.getClasseBonus());
        }
        
        if (data.getCep() != null) {
            preencherCEP(data.getCep());
        }
        
        if (data.getNumEndereco() != null) {
            preencherEndereco(data.getNumEndereco(), null);
        }
    }
    
    /**
     * Preenche dados do condutor.
     * @param data Dados da cotação
     */
    @Step("Preencher dados do condutor")
    public void preencherDadosCondutor(CotacaoAutoData data) {
        if (data.getCpfCondutor() != null) {
            preencherCPFCondutor(data.getCpfCondutor());
        }
        
        if (data.getEstadoCivil() != null) {
            selecionarEstadoCivil(data.getEstadoCivil());
        }
        
        if (data.getMotoristaIndeterminado() != null && 
            "sim".equalsIgnoreCase(data.getMotoristaIndeterminado())) {
            marcarMotoristaIndeterminado();
        }
        
        if (data.getTipoResidencia() != null) {
            selecionarTipoResidencia(data.getTipoResidencia());
        }
        
        if (data.getCondutor18a25() != null) {
            selecionarCobertura18a25(data.getCondutor18a25());
        }
    }
    
    /**
     * Preenche dados complementares (uso, garagens, etc).
     * @param data Dados da cotação
     */
    @Step("Preencher dados complementares")
    public void preencherDadosComplementares(CotacaoAutoData data) {
        if (data.getUsoComercial() != null) {
            selecionarUsoComercial(data.getUsoComercial());
        }
        
        if (data.getFinalidadeUso() != null) {
            selecionarFinalidadeUso(data.getFinalidadeUso());
        }
        
        preencherDadosTrabalho(data);
        preencherDadosEstudo(data);
        preencherDadosGaragem(data);
    }
    
    /**
     * Preenche dados relacionados ao trabalho.
     * @param data Dados da cotação
     */
    private void preencherDadosTrabalho(CotacaoAutoData data) {
        if (data.getCondutorTrabalha() != null && "sim".equalsIgnoreCase(data.getCondutorTrabalha())) {
            clickElement(By.xpath(BUTTON_TRABALHA_XPATH));
            
            if (data.getUtilizaIrTrabalho() != null) {
                String buttonXpath = "sim".equalsIgnoreCase(data.getUtilizaIrTrabalho()) 
                    ? BUTTON_UTILIZA_TRABALHO_SIM_XPATH 
                    : BUTTON_UTILIZA_TRABALHO_SIM_XPATH.replace("Sim", "Nao");
                clickElement(By.xpath(buttonXpath));
            }
            
            if (data.getGaragemTrabalho() != null) {
                String buttonXpath = "sim".equalsIgnoreCase(data.getGaragemTrabalho()) 
                    ? BUTTON_GARAGEM_TRABALHO_SIM_XPATH 
                    : BUTTON_GARAGEM_TRABALHO_SIM_XPATH.replace("Sim", "Nao");
                clickElement(By.xpath(buttonXpath));
            }
        }
    }
    
    /**
     * Preenche dados relacionados ao estudo.
     * @param data Dados da cotação
     */
    private void preencherDadosEstudo(CotacaoAutoData data) {
        if (data.getCondutorEstuda() != null && "sim".equalsIgnoreCase(data.getCondutorEstuda())) {
            clickElement(By.xpath(BUTTON_ESTUDA_XPATH));
            
            if (data.getUtilizaIrEstudo() != null && "sim".equalsIgnoreCase(data.getUtilizaIrEstudo())) {
                clickElement(By.xpath(BUTTON_UTILIZA_ESTUDO_SIM_XPATH));
            }
            
            if (data.getGaragemEscola() != null && "sim".equalsIgnoreCase(data.getGaragemEscola())) {
                clickElement(By.xpath(BUTTON_GARAGEM_ESTUDO_SIM_XPATH));
            }
        }
    }
    
    /**
     * Preenche dados de garagem.
     * @param data Dados da cotação
     */
    private void preencherDadosGaragem(CotacaoAutoData data) {
        if (data.getGaragemResidencia() != null && "sim".equalsIgnoreCase(data.getGaragemResidencia())) {
            clickElement(By.xpath(BUTTON_GARAGEM_RESIDENCIA_SIM_XPATH));
        }
    }
    
    // ========================================================================
    // MÉTODOS ESPECÍFICOS - Ações individuais
    // ========================================================================
    
    /**
     * Preenche o chassi do veículo.
     * @param chassi Número do chassi (17 caracteres)
     */
    public void preencherChassi(String chassi) {
        fillText(By.id(INPUT_CHASSI_ID), chassi);
        aguardarCarregamento();
        
        // Seleciona veículo no popup se aparecer
        if (isElementPresent(By.id(POPUP_SELECT_VEICULO_ID))) {
            clickElement(By.id(POPUP_SELECT_VEICULO_ID));
        }
    }
    
    /**
     * Preenche CPF/CNPJ do segurado.
     * @param cpf CPF ou CNPJ
     */
    public void preencherCPFSegurado(String cpf) {
        fillText(By.xpath(INPUT_CPF_SEGURADO_XPATH), cpf);
        aguardarCarregamento();
    }
    
    /**
     * Preenche CPF do condutor.
     * @param cpf CPF do condutor
     */
    public void preencherCPFCondutor(String cpf) {
        // Verifica se há botão "CPF diferente" e clica
        if (isElementPresent(By.xpath(BUTTON_CPF_CONDUTOR_NAO_XPATH))) {
            clickElement(By.xpath(BUTTON_CPF_CONDUTOR_NAO_XPATH));
        }
        fillText(By.xpath(INPUT_CPF_CONDUTOR_XPATH), cpf);
    }
    
    /**
     * Preenche número da CI (Carteira de Identidade).
     * @param numeroCI Número da CI
     */
    public void preencherNumeroCI(String numeroCI) {
        fillText(By.xpath(INPUT_NUMERO_CI_XPATH), numeroCI);
    }
    
    /**
     * Seleciona classe de bônus.
     * @param classeBonus Classe de bônus (0-10)
     */
    public void selecionarClasseBonus(String classeBonus) {
        clickElement(By.xpath(BUTTON_CLASSE_BONUS_XPATH));
        selectByVisibleText(By.xpath(BUTTON_CLASSE_BONUS_XPATH), classeBonus);
    }
    
    /**
     * Seleciona estado civil.
     * @param estadoCivil Estado civil
     */
    public void selecionarEstadoCivil(String estadoCivil) {
        clickElement(By.xpath(BUTTON_ESTADO_CIVIL_XPATH));
        selectByVisibleText(By.xpath(BUTTON_ESTADO_CIVIL_XPATH), estadoCivil);
    }
    
    /**
     * Seleciona finalidade de uso do veículo.
     * @param finalidade Finalidade de uso
     */
    public void selecionarFinalidadeUso(String finalidade) {
        clickElement(By.xpath(BUTTON_FINALIDADE_USO_XPATH));
        selectByVisibleText(By.xpath(BUTTON_FINALIDADE_USO_XPATH), finalidade);
    }
    
    /**
     * Seleciona se o veículo é 0 km.
     * @param opcao "sim" ou "não"
     */
    public void selecionarVeiculoZeroKm(String opcao) {
        if ("sim".equalsIgnoreCase(opcao)) {
            clickElement(By.xpath(BUTTON_VEICULO_ZERO_KM_XPATH));
        }
    }
    
    /**
     * Preenche valor da blindagem.
     * @param valor Valor da blindagem
     */
    public void preencherValorBlindagem(String valor) {
        fillText(By.xpath(INPUT_VLR_BLINDAGEM_XPATH), valor);
    }
    
    /**
     * Seleciona cobertura para condutor de 18 a 25 anos.
     * @param opcao Opção de cobertura
     */
    public void selecionarCobertura18a25(String opcao) {
        clickElement(By.xpath(BUTTON_COBERTURA_18A25_XPATH));
        selectByVisibleText(By.xpath(BUTTON_COBERTURA_18A25_XPATH), opcao);
    }
    
    /**
     * Seleciona tipo de residência do condutor.
     * @param tipoResidencia Tipo de residência
     */
    public void selecionarTipoResidencia(String tipoResidencia) {
        clickElement(By.xpath(BUTTON_RESIDENCIA_CONDUTOR_XPATH));
        selectByVisibleText(By.xpath(BUTTON_RESIDENCIA_CONDUTOR_XPATH), tipoResidencia);
    }
    
    /**
     * Seleciona se possui acessórios.
     * @param opcao "sim" ou "não"
     */
    public void selecionarAcessorios(String opcao) {
        if ("sim".equalsIgnoreCase(opcao)) {
            clickElement(By.xpath(BUTTON_ACESSORIOS_XPATH));
        }
    }
    
    /**
     * Preenche valor dos acessórios.
     * @param valor Valor dos acessórios
     */
    public void preencherValorAcessorios(String valor) {
        // Tenta primeiro moto, depois caminhão
        if (isElementPresent(By.xpath(INPUT_ACESSORIOS_MOTO_XPATH))) {
            fillText(By.xpath(INPUT_ACESSORIOS_MOTO_XPATH), valor);
        } else if (isElementPresent(By.xpath(INPUT_ACESSORIOS_CAMINHAO_XPATH))) {
            fillText(By.xpath(INPUT_ACESSORIOS_CAMINHAO_XPATH), valor);
        }
    }
    
    /**
     * Marca checkbox de motorista indeterminado.
     */
    public void marcarMotoristaIndeterminado() {
        if (isElementPresent(By.xpath(CBOX_MOTORISTA_INDETERMINADO_XPATH))) {
            clickElement(By.xpath(CBOX_MOTORISTA_INDETERMINADO_XPATH));
        }
    }
    
    /**
     * Avança para a próxima página.
     */
    @Step("Avançar para próxima página")
    public void avancar() {
        avancarProxima(); // Herdado de PageLocatorsBase
    }
    
    /**
     * Verifica se há erros de validação.
     * @return true se há erros
     */
    public boolean temErros() {
        return existemErros(); // Herdado de PageLocatorsBase
    }
    
    /**
     * Obtém lista de erros exibidos.
     * @return Lista de mensagens de erro
     */
    public List<String> getErros() {
        return obterErrosExibidos(); // Herdado de PageLocatorsBase
    }
}
