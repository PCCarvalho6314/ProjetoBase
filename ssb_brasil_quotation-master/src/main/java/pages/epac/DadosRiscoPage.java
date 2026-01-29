package pages.epac;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.util.Random;

public class DadosRiscoPage extends PageObjectBase {

    public DadosRiscoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - XPATH
    private static final String CHECK_DADOS_RISCO_PAGE_PRESENTATION = "//*[contains(text(), 'A Allianz tem o seguro certo p') or contains(text(), 'Data do Início')]";
    private static final String INPUT_CPF_CNPJ = "//*[@id='nx-input-6' or @id='cpfCnpjSegurado']";
    private static final String INPUT_NOME_SEGURADO_ESTIPULANTE = "//*[@id='nx-input-6']";
    private static final String INPUT_RAMO_ATIVIDADE = "//*[@id='nx-input-8']";
    private static final String SELECT_TIPO_SEGURO = "//*[@placeholder='Selecione' or @class='btn-group bootstrap-select block']";
    private static final String SELECT_TIPO_SEGURO_BY_OPTION = "//span[contains(text(),'%s')]";
    private static final String BUTTON_AVANCAR = "//button[contains(text(), 'Avançar') or contains(normalize-space(.), 'Avançar')]";
    private static final String INPUT_NUMERO_VIDAS_EMPREGADOS = "//input[@id='nx-input-0']";
    private static final String INPUT_CAPITAL_INDIVIDUAL_EMPREGADOS = "//*[@id='nx-input-1']";
    private static final String INPUT_NUMERO_VIDAS_SOCIOS = "//input[@id='nx-input-3' and @formcontrolname = 'numberLives']";
    private static final String INPUT_CAPITAL_INDIVIDUAL_SOCIOS = "//*[@id='nx-input-4']";
    private static final String ABA_ESCOLHER_ASSITENCIAS = "//*[@id='nx-tab-label-1-1']";

    private static final String RADIO_FUNERAL = "//*[@class='nx-switcher__toggle']";
    private static final String SELECT_TIPO_COBERTURA_ASSISTENCIA = "//*[@formcontrolname='dropFuneralTipo']";
    private static final String OPTION_TIPO_COBERTURA_ASSISTENCIA = "//*[@id='nx-dropdown-modal-3']//*[contains(text(), '%s')]";
    private static final String SELECT_VALOR_COBERTURA_ASSISTENCIA = "//*[@formcontrolname='dropFuneralValue']";
    private static final String OPTION_VALOR_COBERTURA_ASSISTENCIA = "//*[@id='nx-dropdown-modal-5']//*[contains(text(), '%s')]";
    private static final String INPUT_DATA_INICIO_VIGENCIA = "//input[@id='nx-input-9']";
    private static final String NUMERO_OPERACAO = "//div[contains(text(), 'Número da operação')]//following-sibling::span";

    private static final String CHECK_MSG_RESTRICAO_INICIO_VIGENCIA_SUPERIOR_30_DIAS_PRESENTATION = "//div[contains(text(), 'Data de emissão futura não pode ser superior a 30 dia(s). ')]";
    private static final String OPTION_ISENCAO_IOF = "//button[@id='idbtnHasClientIOF%s']";
    private static final String INPUT_DANOS_MATERIAIS = "//input[@id='materialDamage']";
    private static final String OPTION_CONTRATO_ADICIONAL = "//button[contains(normalize-space(.),'%s') and @class='btn alz-wm-btn alz-wm-btn-grey alz-wm-button-pull']";
    private static final String INPUT_CEP = "//*[@id='CEP']";
    private static final String INPUT_NUMERO = "//*[@id='f_numero']";
    private static final String INPUT_CATEGORIA_RISCO = "//*[@id='descCatRisk']";
    private static final String DDM_OBJETO_SEGURO_XPATH = "(//button[contains(normalize-space(.),'%s') and @class='btn alz-wm-btn alz-wm-btn-grey alz-wm-button-pull'])[1]";
    private static final String OPTION_ISOPANEL_XPATH = "//*[@id='btnIsoPainel%s']";
    private static final String OPTION_TIPO_CONSTRUCAO = "(//button[contains(normalize-space(.),'%s') and @class='btn alz-wm-btn alz-wm-btn-grey alz-wm-button-pull'])[1]";
    private static final String OPTION_INDENIZACAO_VALOR_NOVO = "//button[@id='btnIndenizacaoValor%s']";
    private static final String OPTION_PROTECAO_RISCO = "//label[contains(text(), '%s')]";

    //CHECKS
    public boolean isDadosRiscoDisplayed() {
        return isElementVisible(CHECK_DADOS_RISCO_PAGE_PRESENTATION);
    }

    public boolean isMsgRestricaoInicioVigenciaSuperior30Dias() {
        return isElementVisible(CHECK_MSG_RESTRICAO_INICIO_VIGENCIA_SUPERIOR_30_DIAS_PRESENTATION);
    }

    //ACTIONS
    @Step("Preenche o campo 'CPF/CNPJ' com '{0}'")
    public void inputCpfCnpj(String doc) {
        NoDriverUtils.await(3);
        typeTextSlowly(INPUT_CPF_CNPJ, doc, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Nome Segurado/Estipulante' com '{0}'")
    public void inputNomeSeguradoEstipulante(String nome) {
        typeText(INPUT_NOME_SEGURADO_ESTIPULANTE, nome, true);
        makeScreenshot();
    }

    @Step("Seleciona a opção '{0}' como Ramo de atividade")
    public void selectRamoAtividade(String ramoAtividade) {
        typeTextAndSelect(INPUT_RAMO_ATIVIDADE, ramoAtividade);
        makeScreenshot();
    }

    @Step("Seleciona a opção '{0}' como tipo de seguro")
    public void selectTipoSeguro(String tpSeguro) {
        click(SELECT_TIPO_SEGURO);
        click(String.format(SELECT_TIPO_SEGURO_BY_OPTION, tpSeguro));
        makeScreenshot();
    }

    @Step("Preencher o campo 'Segurado Isento do IOF' com {0}")
    public void selectIsencaoIOF(String option) {
        if (option.contains("S")) {
            option = "Yes";
        } else {
            option = "No";
        }
        click(String.format(OPTION_ISENCAO_IOF, option));
        makeScreenshot();
    }

    @Step("Insere '{0}' como data de início de vigência do seguro")
    public void inputDataInicioVigencia(String dataInicioVigencia) {
        if (dataInicioVigencia != "") {
            driver.findElement(By.xpath(INPUT_DATA_INICIO_VIGENCIA)).sendKeys(Keys.CONTROL + "a");
            driver.findElement(By.xpath(INPUT_DATA_INICIO_VIGENCIA)).sendKeys(Keys.DELETE);
            typeTextSlowly(INPUT_DATA_INICIO_VIGENCIA, dataInicioVigencia, true);
            makeScreenshot();
        }
    }

    @Step("Insere o valor de '{0}' como danos materiais")
    public void inputValorDanosMateriais(String valorDanosMaterias) {
        typeTextAndSelect(INPUT_DANOS_MATERIAIS, valorDanosMaterias);
        makeScreenshot();
    }

    @Step("Insere o cep '{0}'")
    public void inputCep(String cep) {
        typeTextAndSelect(INPUT_CEP, cep);
        waitForDialogCoverHide();
        makeScreenshot();
    }

    @Step("Insere o número de endereço '{0}'")
    public void inputNumeroRandom() {
        Random randNumber = new Random();
        int numero = randNumber.nextInt(1000);
        typeTextAndSelect(INPUT_NUMERO, String.valueOf(numero));
        makeScreenshot();
    }

    @Step("Insere e categoria de riscp '{0}'")
    public void inputCategoriaRisco(String categoriaRisco) {
        typeTextAndSelect(INPUT_CATEGORIA_RISCO, categoriaRisco);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Objeto do Seguro' a opção '{0}'")
    public void selectObjetoSeguro(String option) {
        click(String.format(DDM_OBJETO_SEGURO_XPATH, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} para o campo isoPainel")
    public void selectIsoPainel(String option) {
        if (!option.isEmpty()) {
            if (option.contains("S")) {
                option = "S";
            } else {
                option = "N";
            }
            click(String.format(OPTION_ISOPANEL_XPATH, option));
            makeScreenshot();
        }
    }

    @Step("Seleciona no combo 'Tipo de Construção' a opção '{0}'")
    public void selectTipoConstrucao(String option) {
        click(String.format(OPTION_TIPO_CONSTRUCAO, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção 'Segurado Isento do IOF' com {0}")
    public void selectIndenizacaoValorNovo(String option) {
        if (option.contains("S")) {
            option = "S";
        } else {
            option = "N";
        }
        click(String.format(OPTION_INDENIZACAO_VALOR_NOVO, option));
        makeScreenshot();
    }

    @Step("Seleciona a/as proteção/ões de risco '{0}'")
    public void selectProtecoesRisco(String option) {
        click(String.format(OPTION_PROTECAO_RISCO, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção '{0}' como contratação adicional")
    public void selectContratacaoAdicional(String option) {
        click(String.format(OPTION_CONTRATO_ADICIONAL, option));
        makeScreenshot();
    }

    @Step("Preenche o campo 'Número de Vidas dos Empregados' com '{0}'")
    public void inputNumeroVidasEmpregados(String nmrVidas) {
        typeText(INPUT_NUMERO_VIDAS_EMPREGADOS, nmrVidas, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Capital individual dos empregados' com '{0}'")
    public void InputCapitalIndividualEmpregados(String captalIndividual) {
        typeText(INPUT_CAPITAL_INDIVIDUAL_EMPREGADOS, captalIndividual, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Número de Vidas dos Sócios' com '{0}'")
    public void inputNumeroVidasSocios(String nmrVidas) {
        typeText(INPUT_NUMERO_VIDAS_SOCIOS, nmrVidas, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Capital individual dos sócios' com '{0}'")
    public void InputCapitalIndividualSocios(String captalIndividual) {
        typeText(INPUT_CAPITAL_INDIVIDUAL_SOCIOS, captalIndividual, true);
        makeScreenshot();
    }

    @Step("Acessa a aba 'Escolher Assistências' e seleciona a assisstência '{0}'")
    public void selecionarAssitencia(String assistencia) {
        if (assistencia != "") {
            click(ABA_ESCOLHER_ASSITENCIAS);
            click(RADIO_FUNERAL);
        }
        makeScreenshot();
    }

    @Step("Acessa a aba 'Escolher Assistências' e seleciona a assisstência '{0}'")
    public void selecionarTipoCobertura(String tipoCoberturaAssistencia) {
        if (tipoCoberturaAssistencia != "") {
            click(SELECT_TIPO_COBERTURA_ASSISTENCIA);
            click(String.format(OPTION_TIPO_COBERTURA_ASSISTENCIA, tipoCoberturaAssistencia));
        }
        makeScreenshot();
    }


    @Step("Clicar no botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR);
        makeScreenshot();
    }


}
