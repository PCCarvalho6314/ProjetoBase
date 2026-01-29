package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.util.Random;
import java.util.logging.Logger;

public class DatosClientePage extends PageObjectBase {

    private static final String INPUT_CELULAR_TOMADOR_ID = "DomicilioClienteBeanTomador$celular";
    private static final String INPUT_PHONE_TOMADOR_ID = "DomicilioClienteBeanTomador$telefone2";
    private static final String INPUT_PHONE_TOMADOR2_ID = "DomicilioClienteBeanTomador$telefone";
    private static final String INPUT_PHONE_ASEGURADO1_ID = "DomicilioClienteBeanAsegurado$telefone";
    private static final String INPUT_PHONE_ASEGURADO2_ID = "DomicilioClienteBeanAsegurado$telefone2";
    private static final String INPUT_MOBILE_PHONE_ASEGURADO_ID = "DomicilioClienteBeanAsegurado$celular";
    private static final String MAIL_TOMADOR_TEXTBOX_ID = "DomicilioClienteBeanTomador$email1";
    private static final String NOMBRE_TEXTBOX_ID = "RepresentanteLegalClienteBean$nombre";
    private static final String NOME_MAE_INPUT_ID = "OutrosDadosClienteBeanTomador$nomeMae";
    private static final String NOME_TEXTBOX_ID = "DadosBeneficiarioBean$nombre";
    private static final String NOME_SOCIAL_INPUT_ID = "DadosBeneficiarioBean$nombreSocialBenf";
    private static final String DATA_NASCIMENTO_TEXTBOX_ID = "DadosBeneficiarioBean$dataNascimento";
    private static final String DDM_VIA_ID = "DomicilioClienteBeanTomador$moradatype";
    private static final String INPUT_STREET_ID = "DomicilioClienteBeanTomador$moradaaddressPart2";
    private static final String INPUT_NUM_ID = "DomicilioClienteBeanTomador$numero";
    private static final String INPUT_NUM_ID_2 = "DadosBeneficiarioBean$numero";
    private static final String INPUT_CEP_ID = "DomicilioClienteBeanTomador$codigoPostal";
    private static final String INPUT_CEP_ID_2 = "DadosBeneficiarioBean$codigoPostal";
    private static final String INPUT_BARRIO_ID = "DomicilioClienteBeanTomador$subCodigoPostal";
    private static final String AVANZAR_BUTTON_ID = "btnAceptar";
    private static final String INPUT_FANTASIA_ID = "OutrosDadosClienteBeanTomador$nomeFantasia";
    private static final String INPUT_ACTIVITY_ID = "OutrosDadosClienteBeanTomador$actividadePrincipal";
    private static final String DDM_NATUREZA_ID = "OutrosDadosClienteBeanTomador$naturezaRendimento";
    private static final String DDM_FACTURAMENTO_ID = "OutrosDadosClienteBeanTomador$faturamento";
    private static final String DDM_QTDE_FUNCIONARIOS_ID = "OutrosDadosClienteBeanTomador$numeroFuncionarios";
    private static final String INPUT_TELEFONE2_ID = "DomicilioClienteBeanTomador$telefone2";
    private static final String OPTION_NATUREZA = "//*[@id='OutrosDadosClienteBeanTomador$naturezaRendimento']//option[contains(text(), '%s')]";

    private static final String OPTION_OCUPACAO = "//*[@id='OutrosDadosClienteBeanTomador$ocupacao']//option[contains(text(), '%s')]";
    private static final String DESTINATARIO_SELECT_ID = "DocumentacionBean$envioDoc";

    //LOCATORS - XPATH
    private static final String CHECK_DADOS_EMISSAO_XPATH = "//td[@id='13' and contains(text(), 'D. Emissão')]";
    private static final String DATOS_PERSONALES_TOMADOR_TITLE_XPATH = "//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'dados pessoais do tomador')]";
    private static final String DADOS_BENEFICIARIOS_TITLE_XPATH = "(//td[@class='sectionTitleContent'])[2]";

    private static final String REPRESENTANTES_LEGALES_TITLE_XPATH = "//td[text()='REPRESENTANTES LEGAIS']";
    private static final String MAIL1_TOMADOR_TEXTBOX_XPATH = "//input[@name='DomicilioClienteBeanTomador$email1']";
    private static final String AVANZAR1_BUTTON_XPATH = "//div[@id='btnAceptar']";
    private static final String DDM_RENDA_TOMADOR_ID = "OutrosDadosClienteBeanTomador$rendaMensal";
    private static final String DDM_RENDA_CLIENTE_ID = "OutrosDadosClienteBeanCentro$rendaMensal";
    private static final String DDM_PATRIMONIO_TOMADOR_ID = "OutrosDadosClienteBeanTomador$patrimonio";
    private static final String DDM_PROFESAO_TOMADOR_ID = "OutrosDadosClienteBeanTomador$profissao";
    private static final String INPUT_BIRTHDATE_TOMADOR_ID = "DadosGeraisClienteBeanTomador$dataNascimento";
    private static final String TEXT_DATOS_CLIENTE_XPATH = "//td[text()='DADOS PESSOAIS DO TOMADOR']";
    private static final String BUTTON_OK_XPATH = "//*[@class='footerButton'][contains(text(),'OK')]";
    private static final String INPUT_CEP_XPATH = "//input[@id ='DadosBeneficiarioBean$codigoPostal' or @id = 'DomicilioClienteBeanTomador$codigoPostal']";
    private static final String MSG_ALERT_TEL2_INVALID_XPATH = "//td[@class='cellAppErrorTextBlock cellNoImage']";
    private static final String DDM_SEXO_TOMADOR_XPATH = "//*[@id='OutrosDadosClienteBeanTomador$sexo' or @id='DadosBeneficiarioBean$sexo']";
    private static final String DDM_EST_CIVIL_TOMADOR_XPATH = "//*[@id='OutrosDadosClienteBeanTomador$estadoCivil' or @id='DadosBeneficiarioBean$estadoCivil']";
    private static final String ALERTA_TELEFONE_2_INVALIDO_XPATH = "//td[contains(text(),'Telefone 2 não válido')]";
    private static final String INPUT_SUBCEP_XPATH = "//input[@id = 'DomicilioClienteBeanTomador$subCodigoPostal' or @id = 'DadosBeneficiarioBean$subCodigoPostal']";

    //Logger
    private static final Logger logger = Logger.getLogger(DatosClientePage.class.getName());

    public DatosClientePage(WebDriver driver) {
        super(driver);
    }

    //CHECKS
    public boolean isDatosClienteDisplayed() {
        return isElementVisible(TEXT_DATOS_CLIENTE_XPATH);
    }

    public boolean isDatosPersonalesTomadorDisplayed() {
        waitForJSandJqueryFinish();
        return isElementVisible(DATOS_PERSONALES_TOMADOR_TITLE_XPATH);
    }

    public boolean isDatosBeneficiarioDisplayed() {
        waitForJSandJqueryFinish();
        return isElementVisible(DADOS_BENEFICIARIOS_TITLE_XPATH);
    }

    public boolean isRepresentantesLegalesDisplayed() {
        return isElementVisible(REPRESENTANTES_LEGALES_TITLE_XPATH, 6);
    }

    public boolean msgTelefoneInvalidoisDisplayed() {
        return isElementVisible(MSG_ALERT_TEL2_INVALID_XPATH, 6);
    }

    public boolean isDadosEmissaoDisplayed() {
        return isElementVisible(CHECK_DADOS_EMISSAO_XPATH);
    }

    @Step("Preencher o campo 'Telefone 1' {0}")
    public void inputTelefonoTomador1(String telefono) {
        typeText(INPUT_PHONE_TOMADOR_ID, telefono, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Telefone 2' {0}")
    public void inputTelefonoTomador2(String telefono) {
        typeText(INPUT_PHONE_TOMADOR2_ID, telefono, true);
        makeScreenshot();
    }

    @Step("Preenche os campos 'Telefone' e 'Telefone 2' com os números randômicos {0} e {1}")
    public void inputTelefonesTomadorRandom(String telefone1, String telefone2) {

        NoDriverUtils.await(2);
        char[] ch = new char[7];
        Random randNumber = new Random();

        telefone1.getChars(0, 7, ch, 0);
        String telefonoAfter1 = String.valueOf(ch);

        telefone2.getChars(0, 7, ch, 0);
        String telefonoAfter2 = String.valueOf(ch);

        int numRandom1 = randNumber.nextInt(9999) + 1000;
        int numRandom2 = randNumber.nextInt(9999) + 1000;

        telefonoAfter1 = telefonoAfter1 + numRandom1;
        telefonoAfter2 = telefonoAfter2 + numRandom2;

        typeText(INPUT_PHONE_TOMADOR2_ID, telefonoAfter1, true);
        typeText(INPUT_PHONE_TOMADOR_ID, telefonoAfter2, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Celular' do tomador: {0}")
    public void inputCelularTomador(String telefono) {
        typeText(INPUT_CELULAR_TOMADOR_ID, telefono, true);
        makeScreenshot();
    }


    @Step("Se introduce el Telefono 1 del asegurado: {0}")
    public void inputTelefonoAsegurado1(String telefono) {
        typeText(INPUT_PHONE_ASEGURADO1_ID, telefono, true);
        makeScreenshot();
    }


    @Step("Se introduce el Telefono 2 del asegurado: {0}")
    public void inputTelefonoAsegurado2(String telefono) {
        typeText(INPUT_PHONE_ASEGURADO2_ID, telefono, true);
        makeScreenshot();
    }


    @Step("Se introduce el Celular del asegurado: {0}")
    public void inputCelularAsegurado(String telefono) {
        typeText(INPUT_MOBILE_PHONE_ASEGURADO_ID, telefono, true);
        makeScreenshot();
    }

    @Step("Se introduce el Nombre del representante: {0}")
    public void inputNombreRepresentanteLegal(String nombre) {
        typeText(NOMBRE_TEXTBOX_ID, nombre, true);
        makeScreenshot();
    }

    @Step("Preencher o nome da Mãe: {0}")
    public void inputNomeMae(String nomeMae) {
        typeText(NOME_MAE_INPUT_ID, nomeMae, true);
        makeScreenshot();
    }

    @Step("Se introduce el tipo de Via: {0}")
    public void selectVia(String text) {
        if (getViaDDM().isEnabled()) {
            selectOptionDDM(DDM.SEA, DDM_VIA_ID, text);
            makeScreenshot();
        }
    }

    @Step("Preencher o campo 'Cep' com: '{0}'; Preencher o campo 'Subcep' com: '{1}'")
    public void inputCepSubcep(String cep, String subcep) {
        typeText(INPUT_CEP_XPATH, cep, true);
        typeText(INPUT_SUBCEP_XPATH, subcep, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'CEP' {0}")
    public void inputCEP1(String text) {
        typeText(INPUT_CEP_ID, text.substring(0, 5), true);
        typeText(INPUT_BARRIO_ID, text.substring(6, 9), true);
        makeScreenshot();
    }

    @Step("Prenche o campo 'e-mail' com o e-mail aleatório: {0}")
    public void inputMailRandom(String mail) {
        Random randNumber = new Random();
        int numRandom = randNumber.nextInt(100);

        typeText(MAIL1_TOMADOR_TEXTBOX_XPATH, numRandom + mail, true);
        makeScreenshot();
    }

    @Step("Prenche o campo 'e-mail' com o e-mail")
    public void inputMail(String mail) {
        typeText(MAIL1_TOMADOR_TEXTBOX_XPATH, mail, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar' e aceita o alerta")
    public void clickAvanzarButton() {
        NoDriverUtils.await(2);
        click(AVANZAR_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Avanzar'")
    public void clickAvanzar1Button() {
        NoDriverUtils.await(2);
        click(AVANZAR1_BUTTON_XPATH);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Selecionar o sexo: {0}")
    public void selectSexoTomador(String text) {
        selectOptionDDM(DDM.SEA, DDM_SEXO_TOMADOR_XPATH, text);
        makeScreenshot();
    }

    @Step("Selecionar a profissão: {0}")
    public void selectProfissaoTomador(String text) {
        selectOptionDDM(DDM.SEA, DDM_PROFESAO_TOMADOR_ID, text);
        makeScreenshot();
    }

    @Step("Verifica se é gerado algum alerta referente ao preenchimento inválido do telefone")
    public void verificarAlertaPreenchTelefone2eAvancar() {
        if (isElementPresent(ALERTA_TELEFONE_2_INVALIDO_XPATH, 5)) {
            click(AVANZAR_BUTTON_ID);
            acceptAlert(4);
            makeScreenshot();
        }
    }

    @Step("Seleciona a opção '{0}' no combo 'Renda Mensal'")
    public void selectRendaMensalTomador(String renda) {
        selectOptionDDM(DDM.SEA, DDM_RENDA_TOMADOR_ID, renda);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} no campo Patrimônio do Tomador")
    public void selectPatrimonioTomador(String text) {
        selectOptionDDM(DDM.SEA, DDM_PATRIMONIO_TOMADOR_ID, text);
        makeScreenshot();
    }

    @Step("Se selecciona el Estado civil {0}")
    public void selectEstadoCivilTomador(String text) {
        selectOptionDDM(DDM.SEA, DDM_EST_CIVIL_TOMADOR_XPATH, text);
        makeScreenshot();
    }

    @Step("Se hace click en inspect locals")
    public void inspectLocals() {
        NoDriverUtils.await(3);
        if (isElementVisible("//*[text()='LOCAIS A SEREM INSPECIONADOS']")) {
            click(BUTTON_OK_XPATH);
            makeScreenshot();
        }
    }

    @Step("Preenche o campo 'Nome Fantasia': {0}")
    public void inputNombreFantasia(String text) {
        typeText(INPUT_FANTASIA_ID, text, true);
        makeScreenshot();
    }

    @Step("Preenche o campo 'Atividade Principal': {0}")
    public void inputActividadPrincipal(String text) {
        typeText(INPUT_ACTIVITY_ID, text, true);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} no campo 'Natureza'")
    public void selectNatureza(String option) {
        click(String.format(OPTION_NATUREZA, option));
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} no campo 'Ocupação'")
    public void selectOcupacao(String option) {
        click(String.format(OPTION_OCUPACAO, option));
        makeScreenshot();
    }

    @Step("Seleciona a opçao {0} no campo 'Faturamento'")
    public void selectFacturamento(String option) {
        selectOptionDDM(DDM.SEA, DDM_FACTURAMENTO_ID, option);
        makeScreenshot();
    }

    @Step("Seleciona a opçao {0} no campo 'Quantidade de Funcionários'")
    public void selectQtdeFuncionarios(String option) {
        selectOptionDDM(DDM.SEA, DDM_QTDE_FUNCIONARIOS_ID, option);
        makeScreenshot();
    }

    @Step("Se introduce el teléfono 2: {0}")
    public void inputTelefono2(String text) {
        typeText(INPUT_TELEFONE2_ID, text, true);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Destinatário'")
    public void selectDestinatario(String option) {
        selectOptionDDM(DDM.SEA, DESTINATARIO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Preenche os campos 'Nome', 'D.Nascimento' e Numero")
    public void inputDadosBeneficiario(String nome, String dataNascim, String numero) {
        typeText(NOME_TEXTBOX_ID, nome, true);
        typeText(DATA_NASCIMENTO_TEXTBOX_ID, dataNascim, true);
        typeText(INPUT_NUM_ID_2, numero, true);
        makeScreenshot();
    }

    @Step("Preenche o campo Numero com {0}")
    public void inputNumero(String numero) {
        typeText(INPUT_NUM_ID_2, numero, true);
        makeScreenshot();
    }

    @Step("Preenche os campos 'Nome', 'D.Nascimento' e Numero")
    public void inputDadosBeneficiario2(String nome, String nomeSocial, String dataNascim, String cep, String subcep) {
        typeText(NOME_TEXTBOX_ID, nome, true);
        typeText(NOME_SOCIAL_INPUT_ID, nomeSocial, true);
        typeText(DATA_NASCIMENTO_TEXTBOX_ID, dataNascim, true);
        typeText(INPUT_CEP_ID_2, cep, true);
        typeText(INPUT_SUBCEP_XPATH, subcep, true);
        makeScreenshot();
    }

    @Step("Preencher número {0} caso não venha preenchido")
    public void preencherNumEndereco(String numero) {
        if (getNumInput().getAttribute("value").isEmpty()) {
            typeText(INPUT_NUM_ID, numero, true);
            makeScreenshot();
        }
    }

    //Webelements
    private WebElement getMailTomadorInput() {
        return driver.findElement(By.id(MAIL_TOMADOR_TEXTBOX_ID));
    }

    private WebElement getMail1TomadorInput() {
        return driver.findElement(By.xpath(MAIL1_TOMADOR_TEXTBOX_XPATH));
    }

    private WebElement getViaDDM() {
        return driver.findElement(By.id(DDM_VIA_ID));
    }

    private WebElement getStreetInput() {
        return driver.findElement(By.id(INPUT_STREET_ID));
    }

    private WebElement getNumInput() {
        return driver.findElement(By.id(INPUT_NUM_ID));
    }

    private WebElement getCepInput() {
        return driver.findElement(By.id(INPUT_CEP_ID));
    }

    private WebElement getBarrioInput() {
        return driver.findElement(By.id(INPUT_BARRIO_ID));
    }

    private WebElement getRendaTomadorDDM() {
        return driver.findElement(By.id(DDM_RENDA_TOMADOR_ID));
    }

    private WebElement getRendaClienteDDM() {
        return driver.findElement(By.id(DDM_RENDA_CLIENTE_ID));
    }

    private WebElement getBithDateTomadorInput() {
        return driver.findElement(By.id(INPUT_BIRTHDATE_TOMADOR_ID));
    }

    public void isTelefone1_OK(String TelefoneNovo) {
        boolean msgAlertIsDisplayed = msgTelefoneInvalidoisDisplayed();
        if (msgAlertIsDisplayed) {
            typeText(INPUT_PHONE_TOMADOR2_ID, TelefoneNovo, true);
            clickAvanzarButton();
            makeScreenshot();
        }
    }

    public void isTelefone2_OK() {
        boolean msgAlertIsDisplayed = msgTelefoneInvalidoisDisplayed();
        if (msgAlertIsDisplayed) {
            clickAvanzarButton();
        }
    }


}


