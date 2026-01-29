package pages.epac;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ResumoPage extends PageObjectBase {

    public ResumoPage(WebDriver driver) {
        super(driver);
    }

    //LOCATORS - ID
    private static final String INPUT_CHASIS_ID = "chassis";
    private static final String POPUP_SELECT_VEH_ID = "FipeListLightbox_row_1";
    private static final String CBOX_MOTORISTA_INTEDERMINADO_ID = "motoristaIndeterminado_label";


    //LOCATORS - XPATH
    private static final String CHECK_RESUMEN_PAGE_XPATH = "//*[contains(text(), 'Resumo dos dados')]";

    private static final String CONTACT_XPATH = "//*[@id='cotacaoForm']//h2";
    private static final String BUTTON_TIPO_SEGURO_XPATH = "//select[@id='comboSeguro']//parent::div";
    private static final String BUTTON_CLASSE_BONUS_XPATH = "//select[@id='classeBonus']//parent::div";
    private static final String INPUT_NUMERO_CI_XPATH = "//input[@id='numeroCI']";
    private static final String CPF_SEGURO_TEXTBOX_XPATH = "//input[@id='cpfCnpjSegurado']";
    private static final String CEP_TEXTBOX_XPATH = "//input[@id='CEP']";
    private static final String BUTTON_CPF_CONDUTOR_NAO_XPATH = "//button[@id='BCPFNaoM']";
    private static final String INPUT_CPF_CONDUTOR_XPATH = "//input[@id='cpfCnpjCondutor']";
    private static final String BUTTON_ESTADO_CIVIL_XPATH = "//*[@name='comboCivilEstado']/parent::div";
    private static final String BUTTON_USO_COMERCIAL_XPATH = "//button[@id='BComercialSimM']";
    private static final String BUTTON_FINALIDADE_USO_XPATH = "//select[@id='comboTipoFinalidadeUso1211']//parent::div";
    private static final String BUTTON_VEICULO_ZERO_KM_XPATH = "//button[@id='BKM0SimM']";
    private static final String BUTTON_BLINDAGEM_XPATH = "//button[@id='BBlindagemSimM']']";
    private static final String INPUT_BLINDAGEM_XPATH = "//input[@id='valorBlindagem']";
    private static final String BUTTON_COBERTURA_18A25_XPATH = "//select[@id='valor17a25VersaoNova']//parent::div";
    private static final String BUTTON_RESIDENCIA_CONDUTOR_XPATH = "//*[@name='valorResideEm']/parent::div";
    private static final String BUTTON_AVANCAR_XPATH = "//*[@id='nextPagePrice' or @id='btnAceptar']";
    private static final String LISTA_ERROS_XPATH = "//div[@id='divContentErrorSection']//p/span";
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



    //CHECKS
    public boolean isResumenPageDisplayed() {
        return isElementVisible(CHECK_RESUMEN_PAGE_XPATH);
    }


    //ACTIONS
    @Step("Clicar em 'Avançar'")
    public void clickAvancarButton() {
        NoDriverUtils.await(2);
        click(BUTTON_AVANCAR_XPATH);
        makeScreenshot();
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
        return driver.findElement(By.id(CBOX_MOTORISTA_INTEDERMINADO_ID));
    }

    private WebElement getVeiculo0Km() {
        return driver.findElement(By.xpath(BUTTON_VEICULO_ZERO_KM_XPATH));
    }

    private WebElement getBlindagemButton() {
        return driver.findElement(By.xpath(BUTTON_BLINDAGEM_XPATH));
    }

    private WebElement getBlindagemInput() {
        return driver.findElement(By.xpath(INPUT_BLINDAGEM_XPATH));
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
