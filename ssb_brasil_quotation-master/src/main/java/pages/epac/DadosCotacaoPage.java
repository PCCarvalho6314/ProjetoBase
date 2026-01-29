package pages.epac;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import static dataHelper.DataHelper.*;

public class DadosCotacaoPage extends PageObjectBase {

    public DadosCotacaoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - ID
    private static final String INPUT_CHASIS_ID = "chassis";
    private static final String POPUP_SELECT_VEH_ID = "FipeListLightbox_row_1";
    private static final String CBOX_MOTORISTA_INTEDERMINADO_XPATH = "//label[@id='chkMotoristaIndeterminado2111_label']";

    //LOCATORS - XPATH
    private static final String CONTACT_XPATH = "//*[@id='cotacaoForm']//h2";
    private static final String BUTTON_TIPO_SEGURO_XPATH = "//select[@id='comboSeguro']//parent::div";
    private static final String OPTION_TIPO_SEGURO_RESIDENCE_XPATH_ = "//span[contains(text(),'Seguro Novo')]";
    private static final String OPTION_TIPO_IMOVEL__XPATH = "(//button[@class='btn alz-wm-btn alz-wm-btn-grey alz-wm-button-pull']//input[@value ='APARTAMENTO'])[1]";

    private static final String OPTION_BY_VALUE_XPATH = "(//button[@class='btn alz-wm-btn alz-wm-btn-grey alz-wm-button-pull']//input[@value ='%s'])[1]";
    private static final String OPTION_BY_VALUE_XPATH_2 = "(//input[@value='%s'])[2]";
    private static final String OPTION_BY_VALUE_XPATH_1 = "(//input[@value='%s'])[1]";

    private static final String OPTION_ISOPANEL_XPATH = "//*[@id='BTIsoPainel%s']";

    private static final String INPUT_VALOR_GARANTIDO_XPATH = "//*[@id='sumInsured']";
    private static final String OPTION_PERSONALISAR_COBERTURAS_XPATH = "//*[@id='BTPersonalizar%s']";
    private static final String BUTTON_CLASSE_BONUS_XPATH = "//select[@id='classeBonus']//parent::div";
    private static final String INPUT_NUMERO_CI_XPATH = "//input[@id='numeroCI']";
    private static final String CPF_SEGURO_TEXTBOX_XPATH = "//input[@id='cpfCnpjSegurado']";
    private static final String CPF_CNPJ_INPUT_RESIDENCE_XPATH = "//input[@id='cpfCnpj']";
    private static final String INPUT_NOME_SEGURADO_XPATH = "//*[@id='insuredName']";

    private static final String CEP_TEXTBOX_XPATH = "//input[@id='CEP']";
    private static final String NUMERO_ADDRESS_RESIDENCE_XPATH = "//input[@id='addressNumber']";
    private static final String LOADING_BACKGROUND_ID = "//*[@id='tblDialogProcess']";
    private static final String CEP_TEXTBOX_RESIDENCE_XPATH = "//input[@id='zipCodeNumber']";
    private static final String BUTTON_CPF_CONDUTOR_NAO_XPATH = "//button[@id='BCPFNaoM']";
    private static final String INPUT_CPF_CONDUTOR_XPATH = "//input[@id='cpfCnpjCondutor']";
    private static final String BUTTON_ESTADO_CIVIL_XPATH = "//*[@name='comboCivilEstado']/parent::div";
    private static final String BUTTON_USO_COMERCIAL_XPATH = "//button[@id='BComercialSimM']";
    private static final String BUTTON_FINALIDADE_USO_XPATH = "//select[@id='comboTipoFinalidadeUso1211']//parent::div";
    private static final String BUTTON_VEICULO_ZERO_KM_XPATH = "//button[@id='BKM0SimM']";
    private static final String BUTTON_SIM_BLINDAGEM_XPATH = "(//button[@id='BBlindagemSimM'])[1]";
    private static final String INPUT_VLR_BLINDAGEM_XPATH = "//input[@id='valorBlindagem']";
    private static final String BUTTON_COBERTURA_18A25_XPATH = "//select[@id='valor17a25VersaoNova']//parent::div";
    private static final String BUTTON_RESIDENCIA_CONDUTOR_XPATH = "//*[@name='valorResideEm']/parent::div";
    private static final String BUTTON_AVANCAR_XPATH = "//button[@id='NextButton' or @id='nextPagePrice']";
    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";
    private static final String TITULO_RESULTADOS_PAGE_XPATH = "//h2[contains(text(),'Da mais básica a mais completa, temos a oferta perfeita para o seu cliente.')]";
    private static final String BUTTON_ACESSORIOS_XPATH = "//button[@id='BAcessoriosSimM']";
    private static final String INPUT_ACESSORIOS_MOTO_XPATH = "//input[@id='valorAcessorios_m']";
    private static final String INPUT_ACESSORIOS_CAMINHAO_XPATH = "//input[@id='valorAcessorios_C']";
    private static final String BUTTON_TRABALHA_XPATH = "//button[@id='BTrabalhaSimM']";
    private static final String BUTTON_UTILIZA_TRABALHO_SIM_XPATH = "//button[@id='BUsaParaTrabalharSimM']";
    private static final String BUTTON_UTILIZA_TRABALHO_NAO_XPATH = "//button[@id='BUsaParaTrabalharNaoM']";
    private static final String BUTTON_GARAGEM_TRABALHO_SIM_XPATH = "//button[@id='BGaragemTrabalhoSimM']";
    private static final String BUTTON_GARAGEM_TRABALHO_NAO_XPATH = "//button[@id='BGaragemTrabalhoNaoM']";
    private static final String BUTTON_ESTUDA_XPATH = "//button[@id='BEstudaSimM']";
    private static final String BUTTON_UTILIZA_ESTUDO_SIM_XPATH = "//button[@id='BUsaParaEstudarSimM']";
    private static final String BUTTON_UTILIZA_ESTUDO_NAO_XPATH = "//button[@id='BUsaParaEstudarNaoM']";
    private static final String BUTTON_GARAGEM_ESTUDO_SIM_XPATH = "//button[@id='BGaragemEstudoSimM']";
    private static final String BUTTON_GARAGEM_ESTUDO_NAO_XPATH = "//button[@id='BGaragemEstudoNaoM']";
    private static final String BUTTON_GARAGEM_RESIDENCIA_NAO_XPATH = "//button[@id='BGaragemResidenciaNao']";
    private static final String BUTTON_TRANSPORTADORA_SIM_XPATH = "//button[@id='BIsTransportadoraSimM']";
    private static final String BUTTON_TRANSPORTADORA_NAO_XPATH = "//button[@id='BIsTransportadoraNaoM']";
    private static final String SELECT_MOTORISTA_PRINCIPAL_XPATH = "//select[@id='valorMotoristaPrinicipal']/parent::div";
    private static final String BUTTON_SEGURO_CARGA_XPATH = "//button[@data-id='possuiSeguroCarga']";
    private static final String BUTTON_VEICULO_ALIENADO_XPATH = "//button[@id='BVeiculoAlienadoSim']";
    private static final String BUTTON_REGIAO_CIRCULACAO_XPATH = "//button[@data-id='valorRegiaoCirculacao']";
    private static final String BUTTON_MANTEM_GARAGEM_NAO_XPATH = "//button[@id='BMantemEmGaragemNao']";
    private static final String BUTTON_DIRIGE_APOS_22H_SIM_XPATH = "//button[@id='BDirigeApos22hsSim']";
    private static final String BUTTON_GERENCIAMENTO_RISCO_SIM_XPATH = "//button[@id='BGerenciamentoDeRiscoSim']";
    private static final String SELECT_GERENCIAMENTO_RISCO_XPATH = "//button[@data-id='valorGerenciamentoDeRisco']";
    private static final String INPUT_CARGA_FREQUENTE_XPATH = "//input[@id='cargaTransportada_1']";
    private static final String NUM_OPERACAO_XPATH = "//label[@id='sectionHeader4']";
    private static final String TXT_TIPO_IMOVEL_XPATH = "//*[@id='lblHousingType']";
    private static final String ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH = "//span[contains(text(),'Risco em duplicidade com a proposta')]";
    private static final String LOADING_BACKGROUND_2 = "//*[@id='divDialogProcess']";


    //CHECKS
    public boolean isContactDisplayed() {
        return isElementVisible(CONTACT_XPATH);
    }

    public boolean isCotacaoDisplayed() {
        return isElementVisible(TXT_TIPO_IMOVEL_XPATH);
    }

    public boolean isRiscoDuplicidadeByNumberDisplayed() {
        return isElementVisible(ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH, 6);
    }


    //ACTIONS
    @Step("Seleciona no combo 'Tipo de Seguro' a opção '{0}'")
    public void selectTipoSeguro(String option) {
        waitForClickable(BUTTON_TIPO_SEGURO_XPATH);
        getTipoSeguro().click();
        clickComboOptionByContains(tipoSeguroTxt(option));
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Tipo de Seguro' a opção '{0}'")
    public void selectTipoSeguroResidencia(String option) {
        clickByJS(String.format(OPTION_TIPO_SEGURO_RESIDENCE_XPATH_, option));
        makeScreenshot();
    }


    @Step("Seleciona no combo 'Tipo de Imoóvel' a opção '{0}'")
    public void selectTipoImovel(String option) {
        NoDriverUtils.await(4);
        scrollToElement(OPTION_TIPO_IMOVEL__XPATH);
        clickByJS(String.format(OPTION_BY_VALUE_XPATH_1, option));
        clickByJS(String.format(OPTION_BY_VALUE_XPATH_2, option));
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Tipo de Construção' a opção '{0}'")
    public void selectTipoConstrucao(String option) {
        NoDriverUtils.await(2);
        clickByJS(String.format(OPTION_BY_VALUE_XPATH_1, option));
        clickByJS(String.format(OPTION_BY_VALUE_XPATH_2, option));
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Uso do Imoóvel' a opção '{0}'")
    public void selectUsoImovel(String option) {
        NoDriverUtils.await(5);
        clickByJS(String.format(OPTION_BY_VALUE_XPATH, option));
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Tipo de Assistência' a opção '{0}'")
    public void selectTipoAssistencia(String option) {
        clickByJS(String.format(OPTION_BY_VALUE_XPATH, option));
        makeScreenshot();
    }

    @Step("Preencher o campo 'CEP' com '{0}'")
    public void inputValorGarantido(String valorGarantido) {
        typeText(INPUT_VALOR_GARANTIDO_XPATH, valorGarantido, true);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} para o campo isoPainel")
    public void selectIsoPainel(String option) {
        if (!option.isEmpty()) {
            click(String.format(OPTION_ISOPANEL_XPATH, option));
            makeScreenshot();
        }
    }

    @Step("Seleciona no combo 'Objeto do Seguro' a opção '{0}'")
    public void selectObjetoDoSeguro(String option) {
        clickByJS(String.format(OPTION_BY_VALUE_XPATH, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} para a pergunta 'Gostaria de personalisar as coberturas?")
    public void selectPersonalisarCoberturas(String option) {
        if (!option.isEmpty()) {
            click(String.format(OPTION_PERSONALISAR_COBERTURAS_XPATH, option));
            makeScreenshot();
        }
    }

    @Step("Seleciona no combo 'Tipo de Seguro' a opção '{0}'")
    public void selectClasseBonus(String option) {
        if (option.length() > 0) {
            waitForClickable(BUTTON_TIPO_SEGURO_XPATH);
            getClasseBonus().click();
            clickComboOptionByText(option);
            makeScreenshot();
        }
    }

    @Step("Preenche o campo 'Número da CI' com '{0}'")
    public void inputNumeroCI(String option) {
        if (option.length() > 0) {
            waitForClickable(INPUT_NUMERO_CI_XPATH);
            getNumeroCI().sendKeys(option);
            makeScreenshot();
        }
    }

    @Step("Preencher o campo 'CPF do segurado' com '{0}'")
    public void inputCpfCnpj(String doc) {
        typeText(CPF_SEGURO_TEXTBOX_XPATH, doc, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'CPF do segurado' com '{0}'")
    public void inputNomeSegurado(String nomeSegurado) {
        NoDriverUtils.await(5);
        typeText(INPUT_NOME_SEGURADO_XPATH, nomeSegurado, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'CPF do segurado' com '{0}'")
    public void inputCpfCnpj_Residencia(String doc) {
        NoDriverUtils.await(5);
        typeText(CPF_CNPJ_INPUT_RESIDENCE_XPATH, doc, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'CEP' com '{0}'")
    public void inputCep(String cep) {
        typeText(CEP_TEXTBOX_XPATH, cep, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'CEP' com '{0}'")
    public void inputCep_Residence(String cep) {
        typeText(CEP_TEXTBOX_RESIDENCE_XPATH, cep, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Número'")
    public void inputNumero_Residence() {
        Random randNumber = new Random();
        int numeroAleatorio = randNumber.nextInt(1000);

        typeText(NUMERO_ADDRESS_RESIDENCE_XPATH, String.valueOf(numeroAleatorio), true);

        NoDriverUtils.await(2);

        if (getAttribute(NUMERO_ADDRESS_RESIDENCE_XPATH, "value").isEmpty()) {
            inputNumero_Residence();
        }
        makeScreenshot();
    }

    @Step("Preencher o campo 'Número' com '{0}'")
        public void validarRiscoDuplicidadeporCep() {
            if ((isElementVisible(ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH, 3))) {
                NoDriverUtils.await(5);
                inputNumero_Residence();
                click(BUTTON_AVANCAR_XPATH);
            }
        makeScreenshot();
    }
    @Step("Preencher o campo 'CPF Condutor' com '{1}'")
    public void inputCpfCondutor(String cpf, String condutor) {
        if (!cpf.equalsIgnoreCase(condutor) && !condutor.isEmpty()) {
            clickByJS(BUTTON_CPF_CONDUTOR_NAO_XPATH);

            typeText(INPUT_CPF_CONDUTOR_XPATH, condutor, true);
        }
        makeScreenshot();
    }

    @Step("Preencher o campo 'CPF Condutor' com '{1}'")
    public void inputCpfCondutor(String motoristaIndeterminado, String cpf, String condutor) {
        if (!cpf.equalsIgnoreCase(condutor) && !motoristaIndeterminado.equalsIgnoreCase("sim")) {
            waitForJSandJqueryFinish();
            clickByJS(BUTTON_CPF_CONDUTOR_NAO_XPATH);

            typeText(INPUT_CPF_CONDUTOR_XPATH, condutor, true);
        }
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Estado Civil' a opção '{0}'")
    public void selectEstadoCivil(String estadoCivil) {
        if (estadoCivil.length() > 0) {
            getEstadoCivil().click();
            clickComboOptionByText(estadoCivilTxt(estadoCivil));
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Chassi' com: {0}")
    public void inputChassi(String chassi) {
        getChassiInput().sendKeys(chassi);
        getChassiInput().sendKeys(Keys.TAB);
        waitForJSandJqueryFinish();
        if (isElementVisible(POPUP_SELECT_VEH_ID, 5)) {
            getFirstVehButton().click();
            waitForJSandJqueryFinish();
            performBackgroundClick();
        }
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Uso Comercial' a opção '{1}'")
    public void selectUsoComercial(String utilizaComercialmente, String uso) {
        if (utilizaComercialmente.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_USO_COMERCIAL_XPATH);
            getFinalidadeUsoButton().click();
            clickComboOptionByText(usoComercialTxt(uso));
            makeScreenshot();
        }
    }

    @Step("Seleciona no combo 'Uso Comercial' a opção '{1}'")
    public void clickUsoComercial(String utilizaComercialmente, String transportadora) {
        if (utilizaComercialmente.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_USO_COMERCIAL_XPATH);
            if (transportadora.equalsIgnoreCase("sim")) {
                clickByJS(BUTTON_TRANSPORTADORA_SIM_XPATH);
            } else {
                clickByJS(BUTTON_TRANSPORTADORA_NAO_XPATH);
            }
            makeScreenshot();
        }
    }

    @Step("Clica na opção {0} no campo 'Uso Comercial'")
    public void clickUsoComercial(String utilizaComercialmente) {
        if (utilizaComercialmente.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_USO_COMERCIAL_XPATH);
        }
        makeScreenshot();
    }

    @Step("Clica no checkbox 'Motorista Indeterminado'")
    public void clickMotoristaIndeterminado(String option) {
        if (option.equalsIgnoreCase("sim")) {
            click(CBOX_MOTORISTA_INTEDERMINADO_XPATH);
        }
        makeScreenshot();
    }

    @Step("Clica na opção 'Veículo 0KM'")
    public void clickVeiculo0Km(String option) {
        if (option.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_VEICULO_ZERO_KM_XPATH);
            makeScreenshot();
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Blindadgem'")
    public void inputBlindagem(String option, String valor) {
        if (option.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_SIM_BLINDAGEM_XPATH);
            getBlindagemInput().sendKeys(valor);
        }
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Condutor 18 a 25' a opção '{0}'")
    public void clickCondutor18a25(String option) {
        if (option != "") {
            performBackgroundClick();
            click(BUTTON_COBERTURA_18A25_XPATH);
            if (option.equalsIgnoreCase("sim")) {
                clickComboOptionByText("Sim");
            } else {
                clickComboOptionByText("Não. Estou ciente que não haverá cobertura para condutores entre 18 a 25 anos.");
            }
        }
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Residência' a opção '{0}'")
    public void selectResidencia(String residencia) {
        if (residencia != "") {
            getResidenciaCondutor().click();
            clickComboOptionByText(residenciaTxt(residencia));
        }
        makeScreenshot();
    }

    @Step("Clica na opção '{0}' do campo 'Franquia'")
    public void clickFranquia(String franquia) {
        String locator = "//input[@name='radioFranquia' and @value='" + franquia + "']/parent::button";
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        makeScreenshot();
    }

    @Step("Clica na opção '{0}' do campo 'Assistência 24hs'")
    public void clickAssistencia(String assistencia) {
        String locator = "//input[@name='radioAssistance24' and @value='" + assistencia + "']/parent::button";
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        makeScreenshot();
    }

    @Step("Clica na opção '{0}' do campo 'Carro Reserva'")
    public void clickCarroReserva(String carroReserva) {
        String locator = "//input[@name='radioBkpCar' and @value='" + carroReserva + "']/parent::button";
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        makeScreenshot();
    }

    @Step("Clica na opção '{0}' do campo 'Vidros'")
    public void clickVidros(String vidros) {
        if (!getBlindagemInput().isDisplayed()) {
            String locator = "//input[@name='radioGlassAssistance' and @value='" + vidros + "']/parent::button";
            WebElement element = driver.findElement(By.xpath(locator));
            element.click();
            makeScreenshot();
        }
    }

    @Step("Clica na opção '{0}' do campo 'Lanternas, Faróis e Retrovisores'")
    public void clickLanternasFarois(String option) {
        String locator = "//input[@name='radioGlassAssistanceMoto' and @value='" + option + "']/parent::button";
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
        makeScreenshot();
    }

    @Step("Preenche o campo 'Acessórios'")
    public void inputAcessoriosMoto(String option, String valor) {
        if (option.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_ACESSORIOS_XPATH);
            getAcessoriosMotoInput().sendKeys(valor);
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Acessórios'")
    public void inputAcessoriosCaminhao(String option, String valor) {
        if (option.equalsIgnoreCase("sim")) {
            performBackgroundClick();
            clickByJS(BUTTON_ACESSORIOS_XPATH);
            getAcessoriosCaminhaoInput().sendKeys(valor);
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Condutor Trabalha'")
    public void clickCondutorTrabaha(String trabalha, String utiliza, String garagem) {
        if (trabalha.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_TRABALHA_XPATH);
            if (utiliza.equalsIgnoreCase("sim")) {
                clickByJS(BUTTON_UTILIZA_TRABALHO_SIM_XPATH);
                if (garagem.equalsIgnoreCase("sim")) {
                    clickByJS(BUTTON_GARAGEM_TRABALHO_SIM_XPATH);
                } else {
                    clickByJS(BUTTON_GARAGEM_TRABALHO_NAO_XPATH);
                }
            } else {
                clickByJS(BUTTON_UTILIZA_TRABALHO_NAO_XPATH);
            }
            makeScreenshot();
        }
    }

    @Step("Preenche o campo 'Condutor Estuda'")
    public void clickCondutorEstuda(String estuda, String utiliza, String garagem) {
        if (estuda.equalsIgnoreCase("sim")) {
            clickByJS(BUTTON_ESTUDA_XPATH);
            if (utiliza.equalsIgnoreCase("sim")) {
                clickByJS(BUTTON_UTILIZA_ESTUDO_SIM_XPATH);
                if (garagem.equalsIgnoreCase("sim")) {
                    clickByJS(BUTTON_GARAGEM_ESTUDO_SIM_XPATH);
                } else {
                    clickByJS(BUTTON_GARAGEM_ESTUDO_NAO_XPATH);
                }
            } else {
                clickByJS(BUTTON_UTILIZA_ESTUDO_NAO_XPATH);
            }
            makeScreenshot();
        }
    }

    @Step("Preenche o campo 'Garagem Residência'")
    public void clickGaragemResidencia(String garagem) {
        if (garagem != null) {
            if (!garagem.equalsIgnoreCase("sim")) {
                clickByJS(BUTTON_GARAGEM_RESIDENCIA_NAO_XPATH);
            }
            makeScreenshot();
        }
    }

    @Step("Preenche o campo 'Motorista Principal' com '{0}'")
    public void selectMotoristaPrincipal(String motoristaPrincipal) {
        if (motoristaPrincipal.length() > 0) {
            performBackgroundClick();
            getMotoristaPrincipalButton().click();
            clickComboOptionByText(motoristaQueDirijeTxt(motoristaPrincipal));
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Seguro da Carga' com '{0}'")
    public void selectSeguroCarga(String seguroCarga) {
        clickByJS(BUTTON_SEGURO_CARGA_XPATH);
        clickComboOptionByText(seguroCargaTxt(seguroCarga));
        makeScreenshot();
    }

    @Step("Preenche o campo 'Veículo Alienado'")
    public void clickVeiculoAlienado(String veiculoAlienado) {
        if (veiculoAlienado.equalsIgnoreCase("sim")) {
            getVeiculoAlienadoButton().click();
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Região de Circulação' com '{0}'")
    public void selectRegiaoCirculacao(String regiaoCirculacao) {
        getRegiaoCirculacaoButton().click();
        clickComboOptionByText(regiaoCirculacaoTxt(regiaoCirculacao));
        makeScreenshot();
    }

    @Step("Preenche o campo 'Mantém o veículo em garagem'")
    public void clickGaragemServico(String garagemServico) {
        if (garagemServico.equalsIgnoreCase("não")) {
            getMantemGaragemNaoButton().click();
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Direge após 22h'")
    public void clickDirigeApos22h(String dirigeApos22h) {
        if (dirigeApos22h.equalsIgnoreCase("sim")) {
            getDirigeApos22hSimButton().click();
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Gerenciamento de Risco'")
    public void selectGerenciamentoRisco(String risco, String tipoRisco) {
        if (risco.equalsIgnoreCase("sim")) {
            getGerenciamentoRiscoSimButton().click();
            getGerenciamentoRiscoSelect().click();
            clickComboOptionByText(gerenciamentoRiscoTxt(tipoRisco));
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Carga Transportada mais Frequente' com '{0}'")
    public void inputCargaFrequente(String carga) {
        getCargaFrequenteInput().sendKeys(cargaFrequenteTxt(carga));
        selectCargaFrequente(cargaFrequenteTxt(carga));
        makeScreenshot();
    }

    @Step("Clica no botão 'Avançar'")
    public void clickAvancar() {
        click(BUTTON_AVANCAR_XPATH);
        makeScreenshot();
    }

    public void waitLoadingToFinish() {
        waitForVisible(LOADING_BACKGROUND_2);
        waitforInvisibleHard(LOADING_BACKGROUND_2, 3, 10);
    }

    // Auxiliar Methods
    private void clickComboOptionByText(String option) {
        String selectOption = "//span[text()='" + option + "']";
        WebElement comboItem = driver.findElement(By.xpath(selectOption));
        waitForVisible(selectOption);
        comboItem.click();
    }

    private void clickComboOptionByContains(String option) {
        String selectOption = "//span[contains(text(),'" + option + "')]";
        WebElement comboItem = driver.findElement(By.xpath(selectOption));
        waitForVisible(selectOption);
        comboItem.click();
    }

    public List<String> getListMensagemErro() {
        List<String> listaErros = new ArrayList<>();
        List<WebElement> erros = driver.findElements(By.xpath(LISTA_ERROS_XPATH));
        for (WebElement erro : erros) {
            listaErros.add(erro.getText());
        }
        return listaErros;
    }

    public String getNumeroOperacao() {
        return driver.findElement(By.xpath(NUM_OPERACAO_XPATH)).getText().replace("Número da operação: ", "");
    }

    public void selectCargaFrequente(String carga) {
        WebElement texto = driver.findElement(By.
                xpath("//Strong[contains(text(),'" + carga + "')]"));
        texto.click();
    }

    @Attachment(value = "Screenshot jpg attachment", type = "image/jpg")
    @Step("Taking full screenshot from '{0}'")
    public byte[] fullScreenshot(String url, String path, String name) {

        PageSnapshot pageSnapshot = Shutterbug.shootPage(driver, Capture.FULL);
        pageSnapshot.withName(name);
        pageSnapshot.save(path);
        try {
            return pageSnapshot.getBytes();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception taking full screenshot - {0}", e.getMessage());
        }

        return new byte[0];
    }

    //Webelements
    private WebElement getTipoSeguro() {
        return driver.findElement(By.xpath(BUTTON_TIPO_SEGURO_XPATH));
    }

    private WebElement getClasseBonus() {
        return driver.findElement(By.xpath(BUTTON_CLASSE_BONUS_XPATH));
    }

    private WebElement getNumeroCI() {
        return driver.findElement(By.xpath(INPUT_NUMERO_CI_XPATH));
    }

    private WebElement getCpfSeguroInput() {
        return driver.findElement(By.xpath(CPF_SEGURO_TEXTBOX_XPATH));
    }

    private WebElement getCepInput() {
        return driver.findElement(By.xpath(CEP_TEXTBOX_XPATH));
    }

    private WebElement getCpfCondutorButton() {
        return driver.findElement(By.xpath(BUTTON_CPF_CONDUTOR_NAO_XPATH));
    }

    private WebElement getCpfCondutorInput() {
        return driver.findElement(By.xpath(INPUT_CPF_CONDUTOR_XPATH));
    }

    private WebElement getEstadoCivil() {
        return driver.findElement(By.xpath(BUTTON_ESTADO_CIVIL_XPATH));
    }

    private WebElement getChassiInput() {
        return driver.findElement(By.id(INPUT_CHASIS_ID));
    }

    private WebElement getFirstVehButton() {
        return driver.findElement(By.id(POPUP_SELECT_VEH_ID));
    }

    private WebElement getUsoComercialButton() {
        return driver.findElement(By.xpath(BUTTON_USO_COMERCIAL_XPATH));
    }

    private WebElement getFinalidadeUsoButton() {
        return driver.findElement(By.xpath(BUTTON_FINALIDADE_USO_XPATH));
    }

    private WebElement getMotoristaIndeterminado() {
        return driver.findElement(By.xpath(CBOX_MOTORISTA_INTEDERMINADO_XPATH));
    }

    private WebElement getVeiculo0Km() {
        return driver.findElement(By.xpath(BUTTON_VEICULO_ZERO_KM_XPATH));
    }

    private WebElement getBlindagemSimButton() {
        return driver.findElement(By.xpath(BUTTON_SIM_BLINDAGEM_XPATH));
    }

    private WebElement getBlindagemInput() {
        return driver.findElement(By.xpath(INPUT_VLR_BLINDAGEM_XPATH));
    }

    private WebElement getCobertura18a25() {
        return driver.findElement(By.xpath(BUTTON_COBERTURA_18A25_XPATH));
    }

    private WebElement getResidenciaCondutor() {
        return driver.findElement(By.xpath(BUTTON_RESIDENCIA_CONDUTOR_XPATH));
    }

    private WebElement getAvancarButton() {
        return driver.findElement(By.xpath(BUTTON_AVANCAR_XPATH));
    }

    private WebElement getAcessoriosButton() {
        return driver.findElement(By.xpath(BUTTON_ACESSORIOS_XPATH));
    }

    private WebElement getAcessoriosMotoInput() {
        return driver.findElement(By.xpath(INPUT_ACESSORIOS_MOTO_XPATH));
    }

    private WebElement getAcessoriosCaminhaoInput() {
        return driver.findElement(By.xpath(INPUT_ACESSORIOS_CAMINHAO_XPATH));
    }

    private WebElement getTrabalhaButton() {
        return driver.findElement(By.xpath(BUTTON_TRABALHA_XPATH));
    }

    private WebElement getUtilizaTrabalhoSimButton() {
        return driver.findElement(By.xpath(BUTTON_UTILIZA_TRABALHO_SIM_XPATH));
    }

    private WebElement getUtilizaTrabalhoNaoButton() {
        return driver.findElement(By.xpath(BUTTON_UTILIZA_TRABALHO_NAO_XPATH));
    }

    private WebElement getGaragemTrabalhoSimButton() {
        return driver.findElement(By.xpath(BUTTON_GARAGEM_TRABALHO_SIM_XPATH));
    }

    private WebElement getGaragemTrabalhoNaoButton() {
        return driver.findElement(By.xpath(BUTTON_GARAGEM_TRABALHO_NAO_XPATH));
    }

    private WebElement getEstudaButton() {
        return driver.findElement(By.xpath(BUTTON_ESTUDA_XPATH));
    }

    private WebElement getUtilizaEstudoSimButton() {
        return driver.findElement(By.xpath(BUTTON_UTILIZA_ESTUDO_SIM_XPATH));
    }

    private WebElement getUtilizaEstudoNaoButton() {
        return driver.findElement(By.xpath(BUTTON_UTILIZA_ESTUDO_NAO_XPATH));
    }

    private WebElement getGaragemEstudoSimButton() {
        return driver.findElement(By.xpath(BUTTON_GARAGEM_ESTUDO_SIM_XPATH));
    }

    private WebElement getGaragemEstudoNaoButton() {
        return driver.findElement(By.xpath(BUTTON_GARAGEM_ESTUDO_NAO_XPATH));
    }

    private WebElement getGaragemResidenciaNaoButton() {
        return driver.findElement(By.xpath(BUTTON_GARAGEM_RESIDENCIA_NAO_XPATH));
    }

    private WebElement getTransportadoraSimButton() {
        return driver.findElement(By.xpath(BUTTON_TRANSPORTADORA_SIM_XPATH));
    }

    private WebElement getTransportadoraNaoButton() {
        return driver.findElement(By.xpath(BUTTON_TRANSPORTADORA_NAO_XPATH));
    }

    private WebElement getMotoristaPrincipalButton() {
        return driver.findElement(By.xpath(SELECT_MOTORISTA_PRINCIPAL_XPATH));
    }

    private WebElement getSeguroCargaButton() {
        return driver.findElement(By.xpath(BUTTON_SEGURO_CARGA_XPATH));
    }

    private WebElement getVeiculoAlienadoButton() {
        return driver.findElement(By.xpath(BUTTON_VEICULO_ALIENADO_XPATH));
    }

    private WebElement getRegiaoCirculacaoButton() {
        return driver.findElement(By.xpath(BUTTON_REGIAO_CIRCULACAO_XPATH));
    }

    private WebElement getMantemGaragemNaoButton() {
        return driver.findElement(By.xpath(BUTTON_MANTEM_GARAGEM_NAO_XPATH));
    }

    private WebElement getDirigeApos22hSimButton() {
        return driver.findElement(By.xpath(BUTTON_DIRIGE_APOS_22H_SIM_XPATH));
    }

    private WebElement getGerenciamentoRiscoSimButton() {
        return driver.findElement(By.xpath(BUTTON_GERENCIAMENTO_RISCO_SIM_XPATH));
    }

    private WebElement getGerenciamentoRiscoSelect() {
        return driver.findElement(By.xpath(SELECT_GERENCIAMENTO_RISCO_XPATH));
    }

    private WebElement getCargaFrequenteInput() {
        return driver.findElement(By.xpath(INPUT_CARGA_FREQUENTE_XPATH));
    }
}
