package pages.trenproduccion;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

public class CoberturasPage extends PageObjectBase {

    //LOCATORS - ID
    private static final String VALOR_TEXTBOX_ID = "PartidasAsegurablesBean$lineasPartidas[1].capitales[0].capital";
    private static final String IMPORTANCIA_TEXTBOX_ID = "PartidasAsegurablesBean$lineasPartidas[1].capitales[0].capital";
    private static final String VALOR_DANYOS_ELECTRICOS_TEXTBOX_XPATH = "//label[contains(text(),'Danos Elétricos')]/ancestor::td[1]/following::td[1]/input";
    private static final String CASCO_SINISTRO_TEXTBOX_ID = "reservaInput11";
    private static final String CASCO_SINISTRO_TEXTBOX2_ID = "reservaInput10";
    private static final String TASA_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[0].capitales[0].tasa";
    private static final String TASA2_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[1].capitales[0].tasa";
    private static final String TASA3_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[2].capitales[0].tasa";
    private static final String TASA4_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[3].capitales[0].tasa";
    private static final String TASA5_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[4].capitales[0].tasa";
    private static final String TASA6_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[5].capitales[0].tasa";
    private static final String TASA7_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[6].capitales[0].tasa";
    private static final String TASA8_TEXTBOX_ID = "CoberturasBean$lineasCoberturas[7].capitales[0].tasa";
    private static final String TASA_MANUAL_RADIOBUTTON_ID = "OpcionesCapitalesBean$tasaManual";
    private static final String FIRST_COBERTURA_RADIOBUTTON_ID = "CoberturasBean$lineasCoberturas[0].selecCobertura";
    private static final String GUARDAR_BUTTON_ID = "btnGuardarPartidas";
    private static final String CALCULAR_BUTTON_ID = "btnCalcular";
    private static final String CONTINUAR_BUTTON_ID = "btnContinuar";
    private static final String DDM_PRIMA1_ID = "CoberturasBean$lineasCoberturas[1].capitales[0].capital";
    private static final String INPUT_LMG_ID = "PartidasAsegurablesBean$lineasPartidas[0].capitales[0].capital";
    private static final String INPUT_IS_TOTO_ID = "PartidasAsegurablesBean$lineasPartidas[1].capitales[0].capital";
    private static final String ACEITAR_BUTTON_SINISTRO_ID = "buttonAccept";
    private static final String INPUT_LMI_ID = "PartidasAsegurablesBean$lineasPartidas[0].capitales[0].capital";
    private static final String RESPONSABILIDADE_CIVIL_DM_DC_RADIOBUTTON_ID = "CoberturasBean$lineasCoberturas[3].selecCobertura";
    private static final String ACIDENTE_VIAGEM_ENTREGA_RADIOBUTTON_ID = "CoberturasBean$lineasCoberturas[5].selecCobertura";
    private static final String QUEDA_PARREIRAL_RADIOBUTTON_ID = "CoberturasBean$lineasCoberturas[2].selecCobertura";

    //LOCATORS - XPATH
    private static final String DANYOS_ELECTRICOS_RADIOBUTTON_XPATH = "//label[contains(text(),'Danos Elétricos')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String AVANZAR_BUTTON_XPATH = "//*[@class='footerButton' and @id='btnAceptar']";
    private static final String COBERTURAS_TITLE_XPATH = "//td[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'coberturas')]";
    private static final String TEXT_ITEMS_SEGURAVEIS_XPATH = "//td[text()='VALORES SEGURADOS']";
    private static final String INCENDIO_INPUT_XPATH = "//*[@id=\"CoberturasBean$lineasCoberturas[0].capitales[0].capital\"]";
    private static final String ACCIDENTES_PERSONALES_RADIOBUTTON_XPATH = "//*[@id=\"CoberturasBean$lineasCoberturas[7].selecCobertura\"]";
    private static final String ACCIDENTES_PERSONALES_INPUT_XPATH = "//*[@id=\"CoberturasBean$lineasCoberturas[7].capitales[0].capital\"]";
    private static final String EQUIPAM_ELETRONICOS_RADIOBUTTON_XPATH = "//label[contains(text(),'Equipamentos Eletrônicos')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String VALOR_EQUIPAM_ELETRONICOS_TEXTBOX_XPATH = "//label[contains(text(),'Equipamentos Eletrônicos')]/ancestor::td[1]/following::td[1]/input";
    private static final String INPUT_VALOR_SINISTRO_XPATH = "//input[@id='reservaInput%s']";

    private static final String QUEBRA_VIDROS_RADIOBUTTON_XPATH = "//label[contains(text(),'Quebra de Vidros')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String VALOR_QUEBRA_VIDROS_TEXTBOX_XPATH = "//label[contains(text(),'Quebra de Vidros')]/ancestor::td[1]/following::td[1]/input";
    private static final String RESPONSABILIDADE_CIVIL_OPERACOES_RADIOBUTTON_XPATH = "//label[contains(text(),'Responsabilidade Civil - Operações')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String ROUBO_FURTO_RADIOBUTTON_XPATH = "//label[contains(text(),'Roubo e/ ou Furto Qualificado') or contains(text(),'Roubo e/ou Furto Qualificado')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String VALOR_ROUBO_FURTO_TEXTBOX_XPATH = "//label[contains(text(),'Roubo e/ ou Furto Qualificado') or contains(text(),'Roubo e/ou Furto Qualificado')]/ancestor::td[1]/following::td[1]/input";
    private static final String VENDAVAL_GRANIZO_RADIOBUTTON_XPATH = "//label[contains(text(),'Vendaval / Granizo / Fumaça')]/ancestor::td[1]/preceding::td[1]/input";
    private static final String VALOR_VENDAVAL_GRANIZO_TEXTBOX_XPATH = "//label[contains(text(),'Vendaval / Granizo / Fumaça')]/ancestor::td[1]/following::td[1]/input";
    private static final String ALERT_INFO_EM_PROCESSAMENTO_XPATH = " //td[contains(text(),'{0} Informação em processamento')]";

    public CoberturasPage(WebDriver driver) {
        super(driver);
    }

    //CHECKS
    public boolean isCoberturas() {
        return isElementVisible(COBERTURAS_TITLE_XPATH, 3);
    }

    public boolean isValoresAseguradosDisplayed() {
        return isElementVisible(TEXT_ITEMS_SEGURAVEIS_XPATH);
    }


    //ACTIONS
    @Step("Se introduce LMG/Acumulo: {0}")
    public void inputLMG(String value) {
        typeText(INPUT_LMG_ID, value, true);
        makeScreenshot();
    }

    @Step("Inclui valor de LMI {0}")
    public void inputLMI(String value) {
        typeText(INPUT_LMI_ID, value, true);
        makeScreenshot();
    }

    @Step("Se introduce IS total: {0}")
    public void inputIsTotal(String value) {
        typeText(INPUT_IS_TOTO_ID, value, true);
        makeScreenshot();
    }

    @Step("Se introduce el Valor del Capital: {0}")
    public void inputValor(String capital) {
        typeText(VALOR_TEXTBOX_ID, capital, true);
        makeScreenshot();
    }


    @Step("Se introduce el Valor Danyos Electricos: {0}")
    public void inputValorDanyosElectricos(String valor) {
        if (getDanyosElectricosRadioButton().getAttribute("checked") == null) {
            click(DANYOS_ELECTRICOS_RADIOBUTTON_XPATH);
            typeText(VALOR_DANYOS_ELECTRICOS_TEXTBOX_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona o radio de Equipamentos eletrônicos e inputa o valor {0}")
    public void inputValorEquipamEletronicos(String valor) {
        if (getEquipamEletronicosRadioButton().getAttribute("checked") == null) {
            click(DANYOS_ELECTRICOS_RADIOBUTTON_XPATH);
            typeText(VALOR_EQUIPAM_ELETRONICOS_TEXTBOX_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona o radio de Quebra de vidros e inputa o valor {0}")
    public void inputValorQuebraVidros(String valor) {
        if (getQuebraVidrosRadioButton().getAttribute("checked") == null) {
            click(QUEBRA_VIDROS_RADIOBUTTON_XPATH);
            typeText(VALOR_QUEBRA_VIDROS_TEXTBOX_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona o radio de Responsabilidade Civil Operaçoes e inputa o valor {0}")
    public void inputValorResponsabilidadeCivilOperacoes(String valor) {
        if (getResponsabilidadeCivilOperacoesRadioButton().getAttribute("checked") == null) {
            click(RESPONSABILIDADE_CIVIL_OPERACOES_RADIOBUTTON_XPATH);
            typeText(RESPONSABILIDADE_CIVIL_OPERACOES_RADIOBUTTON_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona o radio de Roubo e furto e inputa o valor {0}")
    public void inputValorRouboeFurto(String valor) {
        if (getRouboeFurtoRadioButton().getAttribute("checked") == null) {
            click(ROUBO_FURTO_RADIOBUTTON_XPATH);
            typeText(VALOR_ROUBO_FURTO_TEXTBOX_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona o radio de Vendaval/Granizo/Fumaça e inputa o valor {0}")
    public void inputValorVendaval_Granizo_Fumaca(String valor) {
        if (getVendavel_Granizo_FumacaRadioButton().getAttribute("checked") == null) {
            click(VENDAVAL_GRANIZO_RADIOBUTTON_XPATH);
            typeText(VALOR_VENDAVAL_GRANIZO_TEXTBOX_XPATH, valor, true);
            makeScreenshot();
        }
    }

    @Step("Seleciona a cobertura Danos Elétricos")
    public void clickCoberturaDanosEletricos() {
        click(DANYOS_ELECTRICOS_RADIOBUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Adicionar valor de '{0}' à cobertura")
    public void inputValorCoberturaDanosEletricos(String valor) {
        typeText(DDM_PRIMA1_ID, valor, true);
        makeScreenshot();
    }

    @Step("Seleciona a cobertura Responsabilidade Civil DM-DC")
    public void clickCoberturaResponsabilidadeCivilDM_DC() {
        click(RESPONSABILIDADE_CIVIL_DM_DC_RADIOBUTTON_ID);
        makeScreenshot();
    }

    @Step("Seleciona a cobertura Acidentes Viagem de Entrega")
    public void clickCoberturaAcidentesViagemEntrega() {
        click(ACIDENTE_VIAGEM_ENTREGA_RADIOBUTTON_ID);
        makeScreenshot();
    }

    @Step("Seleciona a cobertura 'Queda de Parreiral'")
    public void clickCoberturaQuedaParreiral() {
        if (!driver.findElement(By.id(QUEDA_PARREIRAL_RADIOBUTTON_ID)).isSelected()){
            click(QUEDA_PARREIRAL_RADIOBUTTON_ID);
        }
        makeScreenshot();
    }

    @Step("Se introduce la Importancia: {0}")
    public void inputImportancia(String capital) {
        typeText(IMPORTANCIA_TEXTBOX_ID, capital, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 1 ")
    public void inputTasa1(String tasa) {
        typeText(TASA_TEXTBOX_ID, tasa, true);
        acceptAlert(5);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 2 ")
    public void inputTasa2(String tasa) {
        typeText(TASA2_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 3 ")
    public void inputTasa3(String tasa) {
        typeText(TASA3_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 4 ")
    public void inputTasa4(String tasa) {
        typeText(TASA4_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 5 ")
    public void inputTasa5(String tasa) {
        typeText(TASA5_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 6 ")
    public void inputTasa6(String tasa) {
        typeText(TASA6_TEXTBOX_ID, tasa, true);

        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 7 ")
    public void inputTasa7(String tasa) {
        typeText(TASA7_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Inclui o valor {0} de taxa na posição 8 ")
    public void inputTasa8(String tasa) {
        typeText(TASA8_TEXTBOX_ID, tasa, true);
        makeScreenshot();
    }

    @Step("Preencher o valor {0} para a cobertura '640 - Casco - compreensiva - valor de mercado'")
    public void inputValorCascoSinistro(String valor) {
        typeText(CASCO_SINISTRO_TEXTBOX_ID, valor, true);
        makeScreenshot();
    }

    @Step("Preencher o valor {0} para a cobertura '640 - Casco - compreensiva - valor de mercado'")
    public void inputValorCascoSinistro2(String valor) {
        typeText(CASCO_SINISTRO_TEXTBOX2_ID, valor, true);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Tasa Manual'")
    public void clickTasaManualRadioButton() {
        click(TASA_MANUAL_RADIOBUTTON_ID);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Vida - Acidentes Pessoais'")
    public void clickAccidentesPersonalesRadioButton() {
        click(ACCIDENTES_PERSONALES_RADIOBUTTON_XPATH);
        NoDriverUtils.await();
        click(ACCIDENTES_PERSONALES_INPUT_XPATH);
        makeScreenshot();
    }

    public void clickIncendioRadioButton(String incendio) {
        typeText(INCENDIO_INPUT_XPATH, incendio, true);
        makeScreenshot();
    }

    @Step("Clica na primeira opção de cobertura'")
    public void clickCobertura1() {
        click(FIRST_COBERTURA_RADIOBUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Calcular'")
    public void clickCalcularButton() {
        click(CALCULAR_BUTTON_ID);
        if (isElementPresent(ALERT_INFO_EM_PROCESSAMENTO_XPATH, 2)) {
            click(CALCULAR_BUTTON_ID);
        }
        makeScreenshot();
    }

    @Step("Clicar no botão 'Salvar'")
    public void clickGuardarButton() {
        waitForClickable(GUARDAR_BUTTON_ID);
        scrollToElement(GUARDAR_BUTTON_ID);
        clickByJS(GUARDAR_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Continuar'")
    public void clickContinuarButton() {
        click(CONTINUAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvanzarSinistroButton() {
        click(ACEITAR_BUTTON_SINISTRO_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvanzarButton() {
        click(AVANZAR_BUTTON_XPATH);
        makeScreenshot();
        acceptAlert(3);
        makeScreenshot();
    }

    public void preencherCampoValorSinistroPorCobertura(String nomeCobertura, String valor) {
        String numCobertura = "";
        switch (nomeCobertura) {
            case "Morte acidental": {
                numCobertura = "7";
            }
            break;
            case "IPA   INV Permanente Total ou Parcial por accid": {
                numCobertura = "8";
            }
            break;
        }
        typeText(String.format(INPUT_VALOR_SINISTRO_XPATH, numCobertura), valor, true);
        makeScreenshot();
    }

    //WEBELEMENTS
    private WebElement getDanyosElectricosRadioButton() {
        return driver.findElement(By.xpath(DANYOS_ELECTRICOS_RADIOBUTTON_XPATH));
    }

    private WebElement getEquipamEletronicosRadioButton() {
        return driver.findElement(By.xpath(EQUIPAM_ELETRONICOS_RADIOBUTTON_XPATH));
    }

    private WebElement getQuebraVidrosRadioButton() {
        return driver.findElement(By.xpath(QUEBRA_VIDROS_RADIOBUTTON_XPATH));
    }

    private WebElement getResponsabilidadeCivilOperacoesRadioButton() {
        return driver.findElement(By.xpath(RESPONSABILIDADE_CIVIL_OPERACOES_RADIOBUTTON_XPATH));
    }

    private WebElement getRouboeFurtoRadioButton() {
        return driver.findElement(By.xpath(ROUBO_FURTO_RADIOBUTTON_XPATH));
    }

    private WebElement getVendavel_Granizo_FumacaRadioButton() {
        return driver.findElement(By.xpath(VENDAVAL_GRANIZO_RADIOBUTTON_XPATH));
    }
}


