package pages.epac;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class DadosComplementaresPage extends PageObjectBase {

    //Locators - ID
    private static final String INPUT_MAIL_ASEGURADO = "//input[@id='emailInsured' or @id='email']";
    private static final String INPUT_CELULAR = "//input[@id='cellphoneInsured' or @id='celular']";
    private static final String INPUT_TELEFONE = "//input[@id='telefone1']";
    private static final String INPUT_NOME_FANTASIA = "//input[@id='nomeFantasia']";
    private static final String INPUT_ADDRESS_NUM_ID = "f_numero";
    private static final String INPUT_ATIVIDADE_PRINCIPAL = "//input[@id='atividadePrincipal']";

    private static final String OPTION_COMBO_NATUREZA = "//button[@data-id='comboNatureza']//following-sibling::div[@class='dropdown-menu open']//span[contains(text(), '%s')]";
    private static final String OPTION_COMBO_OCUPACAO = "//button[@data-id='comboOcupacao']//following-sibling::div[@class='dropdown-menu open']//span[contains(text(), '%s')]";
    private static final String OPTION_COMBO_FATURAMENTO = "//button[@data-id='comboFaturamento']//following-sibling::div[@class='dropdown-menu open']//span[contains(text(), '%s')]";
    private static final String OPTION_COMBO_PATRIMONIO = "//button[@data-id='comboPatrimonio']//following-sibling::div[@class='dropdown-menu open']//span[contains(text(), '%s')]";
    private static final String DATA_NASCIMENTO = "birthDay";
    private static final String NO_NUMBER_CHECK_ID = "noNumber_label";
    private static final String BUTTON_ACEPTAR_ID = "NextButton";


    //Locators - xpaths
    private static final String OPTION_RENTA_ASEGURADO_XPATH = "//*[@class='text'][text()='De R$ 3.001,00 Até R$5.000,00']";
    private static final String OPTION_RENDA_ATE1500_XPATH = "//span[contains(text(), 'Até R$1.500,00')]";
    private static final String OPTION_RENDA_DE_5001_ATE10000_XPATH = "//span[contains(text(), 'De R$ 5.001,00 Até R$10.000,00')]";
    private static final String OPTION_RENDA_ACIMA_10000_XPATH = "//span[contains(text(), 'Acima de R$10.000,00')]";

    private static final String OPTION_RENDA_ATE120000_XPATH = "//span[contains(text(), 'Até R$120.000,00')]";

    private static final String DDM_RENDA_XPATH = "//*[@data-id='monthlyIncomeInsured']";
    private static final String INPUT_CEP_XPATH = "//*[@id='CEP']";
    private static final String INPUT_PROF_ASEGURADO_XPATH = "//div[@class='dropdown-menu open']//span[text()='Advogado']";
    private static final String INPUT_DATA_SAIDA_NF_CARRO_0KM_XPATH = "//input[@id='nfSaida']";
    private static final String SELECT_SEXO = "//div[@class='dropdown-menu open']//span[text()='%s']";
    private static final String BUTTON_AVANCAR = "//*[@id='nextPagePrice' or @id='btnAceptar']";

    public DadosComplementaresPage(WebDriver driver) {
        super(driver);
    }

    //Checks
    public boolean isDadoComplementaresPageDisplayed() {
        return isElementVisible(INPUT_MAIL_ASEGURADO, 5);
    }

    //Actions
    @Step("Preenche o campo 'e-mail do segurado' com '{0}'")
    public void inputMailAseg(String mail) {
        typeText(INPUT_MAIL_ASEGURADO, mail, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Data de Nasimento' com '{0}'")
    public void inputDataNascimento(String dataNascimento) {
        typeText(DATA_NASCIMENTO, dataNascimento, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Sexo' com '{0}'")
    public void inputSexo(String sexo) {
        clickByJS(String.format(SELECT_SEXO, sexo));
        makeScreenshot();
    }

    @Step("Preenche o campo 'CEP de Correspondência' com '{0}'")
    public void inputCep(String cep) {
        typeText(INPUT_CEP_XPATH, cep, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Celular' com '{0}'")
    public void inputCelularAseg(String celular) {
        typeText(INPUT_CELULAR, celular, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Telefone' com '{0}'")
    public void inputTelefone(String telefone) {
        typeText(INPUT_TELEFONE, telefone, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Nome fantasia' com '{0}'")
    public void inputNomeFantasia(String nomeFantasia) {
        typeText(INPUT_NOME_FANTASIA, nomeFantasia, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Profissão' com '{0}'")
    public void inputProfAseg(String prof) {
        clickByJS(String.format(INPUT_PROF_ASEGURADO_XPATH, prof));
        makeScreenshot();
    }

    @Step("Preenche a renda do segurado com {0}")
    public void inputRenda(String renda) {
        click(DDM_RENDA_XPATH);

        switch (renda) {
            case "1500":
                getRendaAte1500Option().click(); //Até 1.500,00
                break;
            case "5001":
                getRendaAte5001Ate10000option().click();
                break;
            case "10000":
            case "Acima de R$10.000,00":
                getRendaAcima10milption().click();
                break;
            case "120000":
                getRendaAte120milption().click();
                break;
            default:
                throw new IllegalArgumentException("Tabela não localizada no Schema SMOKE_BRA.");
        }
        makeScreenshot();
    }

    @Step("Preenche o campo 'Número' com '{0}'")
    public void inputAddressNum(String num) {
        if (!getAddressNumInput().isEnabled()) {
            getNoNumberCheckbox().click();
        }
        typeText(INPUT_ADDRESS_NUM_ID, num, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Atividade Principal' com {0}")
    public void inputAtividadePrincipal(String atividadePrincipal) {
        typeText(INPUT_ATIVIDADE_PRINCIPAL, atividadePrincipal, true);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} do combo 'Natureza'")
    public void selectComboNatureza(String option) {
        clickByJS(String.format(OPTION_COMBO_NATUREZA, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} do combo 'Ocupação'")
    public void selectComboOcupacao(String option) {
        clickByJS(String.format(OPTION_COMBO_OCUPACAO, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} do combo 'Faturamento'")
    public void selectComboFaturamento(String option) {
        clickByJS(String.format(OPTION_COMBO_FATURAMENTO, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} do combo 'Patrimônio'")
    public void selectPatrimonio(String option) {
        clickByJS(String.format(OPTION_COMBO_PATRIMONIO, option));
        makeScreenshot();
    }

    @Step("Preenche o campo 'Data de saída da nota fiscal do seu carro 0km' com '{0}'")
    public void inputDataSaidaVaiculo0km(String dataSaida) {
        if (dataSaida != "") {
            typeText(INPUT_DATA_SAIDA_NF_CARRO_0KM_XPATH, dataSaida, true);
            makeScreenshot();
        }
    }

    @Step("Clica no botão 'Avançar'")
    public void clickNextButton() {
        NoDriverUtils.await(2);
        click(BUTTON_ACEPTAR_ID, true);
        makeScreenshot();
    }

    @Step("Clica no botão 'Avançar'")
    public void clickAvancarButton() {
        click(BUTTON_AVANCAR);
        makeScreenshot();
    }


    // WebElements
    private WebElement getRendaAte1500Option() {
        return driver.findElement(By.xpath(OPTION_RENDA_ATE1500_XPATH));
    }

    private WebElement getRendaAte5001Ate10000option() {
        return driver.findElement(By.xpath(OPTION_RENDA_DE_5001_ATE10000_XPATH));
    }

    private WebElement getRendaAte120milption() {
        return driver.findElement(By.xpath(OPTION_RENDA_ATE120000_XPATH));
    }

    private WebElement getRendaAcima10milption() {
        return driver.findElement(By.xpath(OPTION_RENDA_ACIMA_10000_XPATH));
    }

    private WebElement getAddressNumInput() {
        return driver.findElement(By.id(INPUT_ADDRESS_NUM_ID));
    }

    private WebElement getNoNumberCheckbox() {
        return driver.findElement(By.id(NO_NUMBER_CHECK_ID));
    }

    private WebElement getRendaDDM() {
        return driver.findElement(By.xpath(DDM_RENDA_XPATH));
    }
}
