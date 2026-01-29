package pages.trenproduccion;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.web.page.PageObjectBase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class DatosRiesgoPage extends PageObjectBase {

    //localizadores ID
    private static final String NOME_SOCIAL_BENEFICIARIO_INPUT_ID = "DadosBeneficiarioPropRuralBean$nomeSocialBen";
    private static final String DISPOSITIVO_ATUAL_SELECT_ID = "InfVeicBean$dispositivoAtual";
    private static final String TIPO_COBERTURA_RESIDENCIA_SELECT_ID = "InformacoesComplementares$tpCobertura";
    private static final String TIPO_ASSISTENCIA_RESIDENCIA_SELECT_ID = "InformacoesComplementares$tpAssistencia";
    private static final String DATA_NASCIMENTO_AUTO_INPUT_ID = "QuestAutoBean$dtaNascimento";
    private static final String DATA_NASCIMENTO_MOTO_INPUT_ID = "QuestMotoBean$dtaNascimento";
    private static final String DATA_NASCIMENTO_CAMINHAO_INPUT_ID = "QuestCaminhaoBean$dtaNascimento";
    private static final String GENERO_CONDUTOR_AUTO_SELECT_ID = "QuestAutoBean$respostaSexo";
    private static final String GENERO_CONDUTOR_MOTO_SELECT_ID = "QuestMotoBean$respostaSexo";
    private static final String GENERO_CONDUTOR_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaSexo";
    private static final String CARGA_FREQUENTE_BPA_SELECT_ID = "CargasBean$codigoCarga";
    private static final String ADICIONAR_BUTTON_ID = "button";
    private static final String CONDUTOR_RESIDE_AUTO_SELECT_ID = "QuestAutoBean$respostaResid";
    private static final String CONDUTOR_RESIDE_MOTO_SELECT_ID = "QuestMotoBean$respostaResid";
    private static final String VEICULO_EM_GARAGEM_RESIDENCIA_MOTO_SELECT_ID = "QuestMotoBean$respostaGarResid";
    private static final String VEICULO_EM_GARAGEM_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaGaragem";
    private static final String MOTORISTA_PRINCIPAL_UTILIZADO_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaMotorista";
    private static final String POSSUI_SEGURO_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaSegCarga";
    private static final String VEICULO_ALIENADO_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaAlienado";
    private static final String CIRCULACAO_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaCirculacao";
    private static final String GER_RISCO_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaRisco";
    private static final String DIRIGE_APOS_22h_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaDirige22h";
    private static final String VEICULO_EM_GARAGEM_TRABALHO_MOTO_SELECT_ID = "QuestMotoBean$respostaGarTrab";
    private static final String VEICULO_EM_GARAGEM_FACULDADE_MOTO_SELECT_ID = "QuestMotoBean$respostaGarFacul";
    private static final String PLANO_VIDROS_MOTO_SELECT_ID = "ServicosBean$codigoPlanoVidros";
    private static final String ESTADO_CIVIL_AUTO_SELECT_ID = "QuestAutoBean$respostaEstCivil";
    private static final String ESTADO_CIVIL_MOTO_SELECT_ID = "QuestMotoBean$respostaEstCivil";
    private static final String ESTADO_CIVIL_CAMINHAO_SELECT_ID = "QuestCaminhaoBean$respostaEstCivil";
    private static final String RESPOSTA_FILHOS_AUTO_SELECT_ID = "QuestAutoBean$respostaFilhos";
    private static final String RESPOSTA_FILHOS_MOTO_SELECT_ID = "QuestMotoBean$respostaFilhos";
    private static final String MUNICIPIO_SELECT_ID = "InfoVeicFrotaBean$municipio";
    private static final String GUARDA_TIPO_SELECT_ID = "DadosLocalGuardaBean$tipo";
    private static final String TIPO_PROPULSION_SELECT_ID = "DadosEmbarcacaoBean$tipoPropulsao";
    private static final String UTILIZACION_SELECT_ID = "DadosEmbarcacaoBean$utilizacaoEmbarcacao";
    private static final String UTILIZACAO_AUTO_SELECT_ID = "InfVeicBean$finalidadeUso";
    private static final String ZEROKM_SELECT_ID = "InfVeicBean$zeroKM";
    private static final String TIPO_ADQUISICION_AGRICOLA_SELECT_ID = "DadosPropriedadeRuralBean$tipoAquisicao";
    private static final String TIPO_AQUISICAO_AGRICOLA_SELECT_ID = "DadosFinanBean$tipoAquisicao";
    private static final String NOME_TOMADOR_TEXTBOX_ID = "IntervinientesBean$nomFigTomadorValue";
    private static final String CLAUSULA_BENEF_DOCUM_TEXTBOX_ID = "ClausulaBeneficiariaRdBean$cpf_doc";
    private static final String CLAUSULA_BENEF_NOME_SOCIAL_TEXTBOX_ID = "ClausulaBeneficiariaRdBean$nomeSocialBen";
    private static final String MATERIAL_CASCO_SELECT_ID = "DadosEmbarcacaoBean$materialCasco";
    private static final String AREA_NAVEGACION_SELECT_ID = "DadosNavegacaoBean$areaNavegacao";
    private static final String DDM_FRANQUICIA_ID = "ValorEmbarcacaoBean$tpFranquia";
    private static final String DDM_MULT_FRANQUIA_ID = "DadosEquipBean$multiploFranquia";
    private static final String DDM_AREA_COBERTURA_ID = "DadosNavegacaoBean$perimetroCobertura";
    private static final String CEP_TEXTBOX_ID = "COD_BR";
    private static final String DIGITO_CEP_TEXTBOX_ID = "SUB_COD_BR";
    private static final String CPF_BENEFICIARIO_AGRICOLA_TEXTBOX_ID = "DadosBeneficiarioPropRuralBean$cpfCnpjBeneficiario_doc";
    private static final String CPF_BENEFICIARIO_AGRICOLA_2_ID = "DadosBenBean$cpfcnpjBenf_doc";
    private static final String INPUT_NUM_ID_2 = "DadosBeneficiarioBean$numero";
    private static final String METROS_TEXTBOX_ID = "DadosEmbarcacaoBean$metros";
    private static final String NOMBRE_EMBARCACION_TEXTBOX_ID = "DadosEmbarcacaoBean$nomeEmbarcacao";
    private static final String NOMBRE_BENEFICIARIO_AGRICOLA_TEXTBOX_ID = "DadosBeneficiarioPropRuralBean$nomeBeneficiario";
    private static final String NOME_BENEFICIARIO_EQUIP_AGRICOLA_INPUT_ID = "DadosBenBean$beneficiario";
    private static final String NOMBRE_SOCIAL_AGRICOLA_TEXTBOX_ID = "DadosBenBean$nomeSocialBen";
    private static final String NUMERO_DIRECCION_AGRICOLA_TEXTBOX_ID = "EnderecoRiscoRuralBean$numero";
    private static final String PLACA_TEXTBOX_ID = "InfoVeicFrotaBean$placa";
    private static final String CHASIS_LUPA_BUTTON_ID = "consultarChassi";
    private static final String LUPA_BUSCA_CEP_BUTTON = "COD_BRAjaxLocFinderImg";
    private static final String VOLTAR_BUTTON2_ID = "o_2";
    private static final String AVANCAR_BUTTON2_ID = "o_3";
    private static final String AVANZAR_BUTTON_ID = "btnAceptar";
    private static final String VOLTAR_BUTTON_ID = "btnAnterior";
    private static final String TIPO_CONSTRUCCION_SELECT_ID = "DadosPropriedadeRuralBean$tipoConstrucao";
    private static final String DDM_CAT_RISCO_NAUTICA_ID = "CategoriaRiesgoNauBean$catRiesgo";
    private static final String DDM_QUANTIDADE_ELEVADORES_ID = "DadosRiscoCondBean$qtdElevadores";
    private static final String DDM_IDADE_IMOVEL_ID = "DadosRiscoCondBean$idadeImovel";
    private static final String DDM_CONGENERE2_ID = "DadosGeraisEmpPmeBean$congenere";
    private static final String DDM_SEGURADORA_ID = "DadosRenovacaoResiBean$seguradora";
    private static final String DDM_QUANTIDADE_ANDARES_ID = "DadosRiscoCondBean$faixaQtdAndares";
    private static final String NUMERO_CHASIS_INPUT_ID = "DadosEquipamentoRDBean$nSerieChassi";
    private static final String VALOR_EQUIPAMIENTO_INPUT_ID = "DadosEquipamentoRDBean$valorEquip";
    private static final String MARCA_INPUT_ID = "DadosEquipamentoRDBean$marcaEquip";
    private static final String MODELO_INPUT_ID = "DadosEquipamentoRDBean$modeloEquip";
    private static final String ANNO_FABRICACION_INPUT_ID = "DadosEquipamentoRDBean$anoFabricacaoEquip";
    private static final String CAPITAL_COMBO_ID = "CapitaisDiferenciadosCondBean$comboCapitais";
    private static final String ADICIONAR_CAPITAIL_BUTTON_ID = "CapitaisDiferenciadosCondBean$btnAdicionar";
    private static final String INDENIZASAO_VALOR_SEGURO_NOVO_CHECK_ID = "ContratacoesAdicionaisCondBean$contratacaoValorNovo1";
    private static final String VALOR_EQUIPAMIENTO2_INPUT_ID = "DadosEquipBean$valorRisco";
    private static final String NUMERO_CHASIS2_INPUT_ID = "DadosEquipBean$numChassiSerie";
    private static final String CEP2_TEXTBOX_ID = "DadosLocalRiscoBean$CEP";
    private static final String DANOS_MATERIAIS_RESIDENCIA_ID = "InformacoesComplementares$valorGarantido";
    private static final String MODELO_CARRO_ID = "row_0_tabelaChassi";
    private static final String SELECT_ASSISTENCIA24h_ID = "ServicosBean$codTpAssist";
    private static final String INICIO_BUTTON_ID = "imgOptMenuBackHome";
    private static final String INPUT_COD_COMPANHIA_ID = "PesquisaCongenereBean$codigoCongenere";
    private static final String INPUT_NUM_FILIAL_ID = "InfoApoAnteriorBean$filial";
    private static final String NUMERO_APOLICE_ID = "InfoApoAnteriorBean$nmrApolice";
    private static final String INPUT_NUMERO_CI_ID = "InfoApoAnteriorBean$nmrCI";
    private static final String NUM_RAMO_XPATH = "//select[@id='InfoApoAnteriorBean$ramo']";
    private static final String INPUT_NUM_ITENS_ID = "InfoApoAnteriorBean$nmrItem";
    private static final String INPUT_DATA_FIM_VIGENCIA_ID = "InfoApoAnteriorBean$fimVigencia";
    private static final String EQUIPAMENTO_INPUT_ID = "DadosEquipBean$nomeEquipamento";
    private static final String MARCA_EQUIPAMENTO_TEXTBOX_ID = "DadosEquipBean$nomeFabricaEquipamento";
    private static final String MODELO_EQUIPAMENTO_INPUT_ID = "DadosEquipBean$nomeModeloEquipamento";
    private static final String VALOR_EQUIPAMENTO_INPUT_ID = "DadosEquipBean$valorRisco";
    private static final String ANYO_FABRICACION_EQUIPAMENTO_INPUT_ID = "DadosEquipBean$anoEquipamento";
    private static final String CAT_RISCO_EQUIPAMENTO_INPUT_ID = "CatRiscoBrBean$codCategoriaRisco";
    private static final String DDM_CAT_RIESGO_RURAL_ID = "CatRiscoRuralBean$famCatRiesgo";

    //localizadores XPATH

    private static final String DDM_TIPO_SEGURO_ID = "DatosGeneralesBean$tipoSeguro";


    private static final String CHECK_DADOS_EMISSAO_XPATH = "//td[@id='13' and contains(text(), 'D. Emissão')]";

    private static final String ACEITAR_BUTTON_XPATH = "//div[@id='o_3' and contains(text(), 'Aceitar')]";
    private static final String NUMERO_ENDERESO_TEXTBOX_XPATH = "//input[contains(@id,'Bean$numero')]";
    private static final String SEGURO_NUEVO_OPTION_XPATH = "//option[text()='Seguro Novo']";
    private static final String SEGURO_NOVO_OPTION_XPATH = "//option[text()='Seguro novo']";
    private static final String SEGURO_NOVO_EQUIPAMIENTOS_OPTION_XPATH = "//select[@id='DadosGeraisBean$tipoSegNovo']/option[contains(text(),'Seguro novo')]";
    private static final String DDM_LOCAL_UTILI_XPATH = "//select[@id='DadosEquipamentoRDBean$localUtilizacao']";
    private static final String ARTESANAL_NO_RADIOBUTTON_XPATH = "//input[@id='DadosEmbarcacaoBean$construcaoArtesanal2' and @value='N']";
    private static final String ULTIMOSANYOS_NO_RADIOBUTTON_XPATH = "//input[@id='SinistroBean$sinistroUltimos4anos2']";
    private static final String CREDITO_RURAL_NO_RADIOBUTTON_XPATH = "//input[@id='DadosPropriedadeRuralBean$garantiaCreditoRural' and @value='N']";
    private static final String ALERTA_NUMERO_ENDERESO_REPETIDO_XPATH = "//td[contains(text(),'em duplicidade com a proposta')]";
    private static final String ALERTA_PATRIMONIO_HISTORICO_XPATH = "//td[contains(text(),'Matriz Patrimônio Histórico selecionado para emissão')]";
    private static final String CAPITAL_COMBO_FIRSTOPTION_XPATH = "//select[@id='CapitaisDiferenciadosCondBean$comboCapitais']/option[@value!='0'][1]";
    private static final String VALOR_EMBARCACION_TEXTBOX_XPATH = "//input[@id='ValorEmbarcacaoBean$vlrEmbarcacao']";
    private static final String DATOS_DE_SEGURO_TITLE_XPATH = "//td[text()='DADOS DO SEGURO']";
    private static final String EQUIPAMENTO_OPTION_XPATH = "//option[text()='Equipamento']";
    private static final String COLHEITADEIRA_OPTION_XPATH = "//option[text()='COLHEITADEIRA']";
    private static final String CASE_BRASIL_OPTION_XPATH = "//option[text()='CASE BRASIL']";
    private static final String MODELO_100_OPTION_XPATH = "//option[text()='100']";
    private static final String ANYO_TEXTBOX_XPATH = "//input[@id='DadosEquipBean$anoEquipamento']";
    private static final String EQUIPAMENTO_0KM_NO_RADIOBUTTON_XPATH = "//input[@id='DadosEquipBean$equipNovoN' and @value='N']";
    private static final String EQUIPAMENTO_CEDIDO_NO_RADIOBUTTON_XPATH = "//input[@id='DadosEquipBean$equipAlugado2' and @value='N']";
    private static final String ENDERESO_TEXTBOX_XPATH = "//input[@id='DadosLocalRiscoBean$enderecoRisco']";
    private static final String NUMERO_ENDERESO2_TEXTBOX_XPATH = "//input[contains(@id,'DadosLocalRiscoBean$numeroRisco')]";
    private static final String COMPLEMENTO_TEXTBOX_XPATH = "//input[@id='DadosLocalRiscoBean$complementoRisco']";
    private static final String RECURSOS_PROPIOS_OPTION_XPATH = "//option[text()='Adquirido através de recursos próprios']";
    private static final String CHECK_DADOS_DE_RISCO_XPATH = "//td[contains(translate(., 'ACDEeIORS', 'acdooiors'),'dados do risco')]";
    private static final String CHECK_FORA_DE_ACEITACAO_XPATH = "//td[contains(translate(., 'FORADPLECITÇ', 'foradplecitç'),'fora da política de aceita')]";
    private static final String CHECK_NOME_ALTERADO_XPATH = "//td[contains(text(), 'Nome alterado conforme a receita federal, favor avaliar')]";
    private static final String CHECK_ACEITACAO_XPATH = "//td[contains(text(), 'Aceitação')]";
    private static final String CHECK_RESTRICAO_TECNICA_XPATH = "//td[contains(text(), 'Restrição técnica para o veículo')]";
    private static final String CEP_PERNOITE_XPATH = "//input[@id='InfVeicBean$cepPernoite']";
    private static final String INPUT_NUMERO_XPATH = "//input[@id='DadosRiscoResiDigital$numero' or @id='EnderecoRiscoEmpBean$numero' or @id = 'EnderecoRiscoEmpPmeBean$numero' or @id='EnderecoBean$numero']";
    private static final String CHECK_SEM_NUMERO = "//*[@id='f_check_number']";

    private static final String DDM_TIPO_XPATH = "//select[@id='EnderecoBean$tipo']";
    private static final String LOGRADOURO_TEXTBOX_XPATH = "//input[@id='EnderecoBean$logradouro']";
    private static final String DADOS_GERAIS_TITLE_XPATH = "//td[contains(text(),'DADOS GERAIS')]";
    private static final String TXT_ALERTA_ERRO_XPATH = "//tr[@class='rowAppErrorTextBlock']/td/b[contains(text(), 'Erro')]";
    private static final String TXT_MENSAGEM_ERRO_XPATH = "//tr[@class='rowAppErrorTextBlock']/td[2]";
    private static final String CHASSI_TEXTBOX_XPATH = "//input[@id='InfVeicBean$chassi']";
    private static final String OPTION_ISOPANEL_XPATH = "//select[@id='InformacoesComplementares$isopainel']//option[text()='%s']";
    private static final String OPTION_INDENIZACAO_A_VALOR_DE_NOVO_EMPRESARIAL = "//input[@name = 'IndenizacaoValorNovoEmpPmeBean$indenizacaoValorNovo' and @value='%s']";
    private static final String OPTION_ISOPANEL_EMPRESARIAL = "//input[@name='IsopainelEmpPmeBean$isopainel' and @value='%s']";


    private static final String INSPECAO_RISCO_RADIO_BUTTON_XPATH = "//input[contains(@id, 'DadosRiscoCondBean$inspRisco')";
    private static final String INDENIZACAO_A_VALOR_DE_NOVO_RADIO_BUTTON_XPATH = "//input[contains(@id, 'ContratacoesAdicionaisCondBean$contratacaoValorNovo')";
    private static final String AVANCAR_BUTTON_XPATH = "//*[contains(@id, 'Aceptar') or contains(@id, 'o_3') and contains(text(), 'Avançar') or contains(text(), 'Aceitar')]";
    private static final String TXT_ALERTA_ERRO_2_XPATH = "//td[@class='cellAppErrorTextBlock cellNoImage']";
    private static final String TXT_FORA_POLITICA_CIA_XPATH = "//*[contains(text(),'ora da política de aceitação da Cia.')]";
    private static final String TXT_ERROR_APOLICE_JA_ENDOSSADA_XPATH = "//*[contains(text(),'Não é possível emitir mais de uma movimentação no mesmo dia')]";
    private static final String TXT_ERROR_APOLICE_JA_CANCELADA_XPATH = "//*[contains(text(), 'Data início endosso maior a data de cancelamento')]";
    private static final String AVANZAR_BUTTON_XPATH = "//*[@id='NextButton' or @id='nextButton' or @id='btnAvancar' or @id='btnAvanzar' or @id='o_3' or @id='btnAceptar']";
    private static final String CATEGORIA_RISCO_COMBO_XPATH =
            "//*[@id='CatRiscoEmpCorpBean$codCategoriaRisco'" +  //Enterprise
                    "or @id='CatRiscoRuralBean$famCatRiesgo']"; //Rural
    private static final String DDM_CLASSE_BONUS_XPATH = "//select[@id='DadosCompBean$classeBonus']";
    private static final String DDM_OBJETO_SEGURO_XPATH = "//select[@id='ValoresRiscoEmpCorpBean$objetoSeguro' or  @id='ValoresRiscoEmpPmeBean$objetoSeguro']";
    private static final String INPUT_VALOR_VR_XPATH = "//input[@id = 'ValoresRiscoEmpCorpBean$totalDanosMateriais' or @id ='ValoresRiscoEmpPmeBean$totalDanosMateriais']";
    private static final String TIPO_CONSTRUCAO_XPATH = "//select[@id ='TipoConstrucaoEmpCorpBean$tipoConstrucao' or @id = 'TipoConstrucaoEmpPmeBean$tipoConstrucao']//option[contains(text(), '%s')]";
    private static final String DDM_CAT_RISCO_ENTERPRISE_XPATH = "//select[@id='CatRiscoEmpCorpBean$codCategoriaRisco' or @id='CatRiscoEmpPmeBean$codCategoriaRisco']";
    private static final String VALOR_GARANTIDO_INPUT_XPATH = "//input[@id='InformacoesComplementares$valorGarantido']";
    private static final String OPTION_TIPO_COBERTURA_XPATH = "//select[@id='InformacoesComplementares$tpCobertura']//option[text()='%s']";
    private static final String OPTION_TIPO_ASSISTENCIA_XPATH = "//select[@id='InformacoesComplementares$tpAssistencia']//option[text()='%s']";
    private static final String ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH = "//td[contains(text(),'em duplicidade com a proposta')]";
    private static final String ALERTA_CRITICA_CALCULO_XPATH = "//td[contains(text(),'%s')]";
    private static final String LISTA_ERROS_XPATH = "//td[@class='cellAppErrorTextBlock cellNoImage' or @class='rowAppErrorInfoTextBlock cellNoImage'] ";
    private static final String OPTION_PROTECAO_RISCO = "//label[contains(text(), '%s')]";


    public DatosRiesgoPage(WebDriver driver) {
        super(driver);
    }

    //CHECKS
    public boolean isDadosEmissaoDisplayed() {
        return isElementVisible(CHECK_DADOS_EMISSAO_XPATH);
    }

    public boolean is_AlertCriticaCalculo_Displayed(String critica) {
        return isElementVisible(String.format(ALERTA_CRITICA_CALCULO_XPATH, critica), 6);
    }

    public boolean isDadosDeRiscoDisplayed() {
        return isElementVisible(CHECK_DADOS_DE_RISCO_XPATH, 8);
    }

    public boolean isAlertaAceitacaoDisplayed() {
        return isElementPresent(CHECK_FORA_DE_ACEITACAO_XPATH, 4);
    }

    public boolean isNomeAlteradoDisplayed() {
        return (isElementVisible(CHECK_NOME_ALTERADO_XPATH, 2) ||
                isElementVisible(CHECK_ACEITACAO_XPATH, 2));
    }

    public boolean isRestricaoTecnicaDisplayed() {
        return isElementVisible(CHECK_RESTRICAO_TECNICA_XPATH, 20);
    }

    public boolean isRiscoDuplicidadeByNumberDisplayed() {
        return isElementVisible(ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH, 6);
    }

    //ACTIONS
    @Step("Se introducen el CEP {0}, Barrio {1}, Numero Endereso {2} y Valor de capital {3}")
    public void inputDatosCondominio(String cep, String barrio, String numero, String valor) {
        inputCepDigito(cep, barrio);
        inputNumeroEndereso(numero);
        addCapital(valor);
        clickIndenizasaoSeguroNovoCheck();
        makeScreenshot();
    }

    @Step("Preencher o campo Nome do Tomador {0}")
    public void inputNomeTomador(String name) {
        typeText(NOME_TOMADOR_TEXTBOX_ID, name, true);
        makeScreenshot();
    }

    @Step("Preencher 'Cláusula Beneficiária a Favor de': {0}")
    public void inputClausulaAFavorDe(String name) {
        typeTextSlowly(NOME_TOMADOR_TEXTBOX_ID, name, true);
        makeScreenshot();
    }

    @Step("Preencher Cláusula Beneficiária 'CPF / CNPJ': {0}")
    public void inputClausulaDoc(String doc) {
        typeTextSlowly(CLAUSULA_BENEF_DOCUM_TEXTBOX_ID, doc, true);
        makeScreenshot();
    }

    @Step("Preencher Cláusula Beneficiária 'Nome Social': {0}")
    public void inputClausulaNomeSocial(String name) {
        typeTextSlowly(CLAUSULA_BENEF_NOME_SOCIAL_TEXTBOX_ID, name, true);
        makeScreenshot();
    }


    @Step("Preenche os dados de equipamento com: marca {0}, modelo {1}, valorEquipamento {2}, e ano de fabricação {3}")
    public void inputDadoEquipamentos(String equipamento, String marca, String modelo, String valorEquip, String yearBuild) {
        typeText(EQUIPAMENTO_INPUT_ID, equipamento, false);
        NoDriverUtils.await(2);
        typeAppendText(EQUIPAMENTO_INPUT_ID, Keys.DOWN);
        typeAppendText(EQUIPAMENTO_INPUT_ID, Keys.ENTER);
        performBackgroundClick();

        typeText(MARCA_EQUIPAMENTO_TEXTBOX_ID, marca, false);
        typeAppendText(MARCA_EQUIPAMENTO_TEXTBOX_ID, Keys.DOWN);
        typeAppendText(MARCA_EQUIPAMENTO_TEXTBOX_ID, Keys.ENTER);
        NoDriverUtils.await(2);

        typeText(MODELO_EQUIPAMENTO_INPUT_ID, modelo, false);
        typeText(VALOR_EQUIPAMENTO_INPUT_ID, valorEquip, false);
        typeText(ANYO_FABRICACION_EQUIPAMENTO_INPUT_ID, yearBuild, false);
        makeScreenshot();
    }

    @Step("Para 'Inspeção de risco para este item', clicar na opção '{0}'")
    public void clickInspecaoDeRiscoRadioButtonPorValue(String value) { //Use 'S' ou 'N' para 'value'
        click(INSPECAO_RISCO_RADIO_BUTTON_XPATH + " and @value='" + value + "']");
        makeScreenshot();
    }

    @Step("Preenche o campo Numero com {0}")
    public void inputNumero(String numero) {
        typeText(INPUT_NUMERO_XPATH, (numero), true);
        makeScreenshot();
    }

    @Step("Preenche o campo Numero com {0}")
    public void inputNumeroRandomico_Empresarial() {
        Random random = new Random();
        int novoNumero = random.nextInt(1000);

        NoDriverUtils.await(4);

        sendKeysJS(INPUT_NUMERO_XPATH, String.valueOf(novoNumero));
        makeScreenshot();
    }

    @Step("Preenche o campo Numero com {0}")
    public void inputNumeroRandomico() {
        Random random = new Random();
        int novoNumero = random.nextInt(1000);

        NoDriverUtils.await(10);

        if (driver.findElement(By.xpath(CHECK_SEM_NUMERO)).isSelected()) {
            clickByJS(CHECK_SEM_NUMERO);
        }
        sendKeysJS(INPUT_NUMERO_XPATH, String.valueOf(novoNumero));
        makeScreenshot();
    }

    @Step("Preenche o campo Numero com {0} caso seja exibida mesnagem de restrição devido duplicidade")
    public void inputNumeroNovamente() {
        Random random = new Random();
        int novoNumero = random.nextInt(100);

        performBackgroundClick();
        waitForVisible(CHECK_SEM_NUMERO);
        if (driver.findElement(By.xpath(CHECK_SEM_NUMERO)).isSelected()) {
            click(CHECK_SEM_NUMERO);
        }
        typeText(INPUT_NUMERO_XPATH, String.valueOf(novoNumero), false);
        performBackgroundClick();
        makeScreenshot();
    }

    @Step("Para 'Indenização à Valor de Novo', clicar na opção '{0}'")
    public void clickIndenizacaoAValorDeNovoButtonPorValue(String value) { //Use 'S' ou 'N' para 'value'
        click(INDENIZACAO_A_VALOR_DE_NOVO_RADIO_BUTTON_XPATH + " and @value='" + value + "']");
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} para o campo 'Indenização à Valor de Novo'")
    public void selectIndenizacaoValoNovo(String option) {
        if (option.contains("S") || (option.contains("im"))) {
            option = "S";
        } else {
            option = "N";
        }
        click(String.format(OPTION_INDENIZACAO_A_VALOR_DE_NOVO_EMPRESARIAL, option, true));
        makeScreenshot();
    }


    @Step("Se introducen los Metros {0}")
    public void inputMetros(String metros) {
        typeText(METROS_TEXTBOX_ID, metros, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Endereço' com: {0}")
    public void inputLogradouro(String text) {
        if (LOGRADOURO_TEXTBOX_XPATH.isBlank()) {
            typeTextSlowly(LOGRADOURO_TEXTBOX_XPATH, text, true);
            makeScreenshot();
        }
    }

    @Step("Se introducen el Chasis {0}")
    public void inputChasis(String chasis) {
        typeText(CHASSI_TEXTBOX_XPATH, chasis, true);
        makeScreenshot();
    }

    @Step("Preencher e buscar o 'Chassi': {0}")
    public void inputChassi(String chassi) {
        String window = getWindowHandle();
        typeText(CHASSI_TEXTBOX_XPATH, chassi, true);
        clickChassiLupaButton();

        if (driver.getWindowHandles().size() == 3) {
            changeToNextWindow();
            getPrimeiroVeiculo().click();
            acceptAlert(3);
            driver.close();
            changeToWindow(window);
        }
        makeScreenshot();
    }

    @Step("Preencher o campo 'CEP de pernoite' com: {0}")
    public void inputCepPernoite(String cep) {
        typeText(CEP_PERNOITE_XPATH, cep, true);
        makeScreenshot();
    }

    @Step("Se introducen el Numero de Endereso: {0}")
    public void inputNumeroEndereso(String numero) {
        typeText(NUMERO_ENDERESO_TEXTBOX_XPATH, numero, true);
        makeScreenshot();
    }


    @Step("Preencher o campo 'Valor Garantido com: {0}")
    public void inputDanosMateriaisResidencia(String valor) {
        typeText(DANOS_MATERIAIS_RESIDENCIA_ID, valor, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'VR Total de danos materiais' com '{0}'")
    public void inputVlrDanosMateriais(String vlrDanosMaterias) {
        typeText(INPUT_VALOR_VR_XPATH, vlrDanosMaterias, false);
        makeScreenshot();
    }

    @Step("Seleciona a/as proteção/ões de risco '{0}'")
    public void selectProtecoesRisco(String option) {
        click(String.format(OPTION_PROTECAO_RISCO, option));
        makeScreenshot();
    }

    @Step("Preencher CEP com: {0}, e o dígito com: {1}")
    public void inputCepDigito(String cep, String digito) {
        typeText(CEP_TEXTBOX_ID, cep, false);
        typeText(DIGITO_CEP_TEXTBOX_ID, digito, true);
        click(LUPA_BUSCA_CEP_BUTTON);
        makeScreenshot();
    }

    @Step("Se introduce el CPF Beneficiario Agricola {0}")
    public void inputCPFBeneficiarioAgricola(String cpf) {
        typeText(CPF_BENEFICIARIO_AGRICOLA_TEXTBOX_ID, cpf, true);
        makeScreenshot();
    }

    @Step("Preenche o Campo CPF/CNPJ do beneficiário com {0}")
    public void inputCPFBeneficiarioAgricola2(String cpf) {
        typeText(CPF_BENEFICIARIO_AGRICOLA_2_ID, cpf, true);
        makeScreenshot();
    }

    @Step("Se introduce el Nombre Beneficiario Agricola {0}")
    public void inputNombreBeneficiarioAgricola(String nom) {
        typeText(NOMBRE_BENEFICIARIO_AGRICOLA_TEXTBOX_ID, nom, true);
        makeScreenshot();
    }

    @Step("Preenche o campo Beneficiário com {0}")
    public void inputNomeBeneficiarioEquipamentosAgricola(String nomeBeneficario) {
        typeText(NOME_BENEFICIARIO_EQUIP_AGRICOLA_INPUT_ID, nomeBeneficario, true);
        makeScreenshot();
    }

    @Step("Preenche o Campo Nome Social com {0}")
    public void inputNombreSocial(String nome) {
        typeText(NOMBRE_SOCIAL_AGRICOLA_TEXTBOX_ID, nome, true);
        makeScreenshot();
    }

    @Step("Preenche o Campo Nome Social com {0}")
    public void inputNomeSocial(String nome) {
        typeText(NOME_SOCIAL_BENEFICIARIO_INPUT_ID, nome, true);
        makeScreenshot();
    }

    @Step("Se introduce el Número Beneficiario Agricola {0}")
    public void inputNumeroDireccionAgricola(String num) {
        typeText(NUMERO_DIRECCION_AGRICOLA_TEXTBOX_ID, num, true);
        makeScreenshot();
    }

    @Step("Se introduce la Placa {0}")
    public void inputPlaca(String placa) {
        typeText(PLACA_TEXTBOX_ID, placa, true);
        makeScreenshot();
    }

    @Step("Se introduce el Nombre de la Embarcación: {0}")
    public void inputNombreEmbarcacion(String nombre) {
        typeText(NOMBRE_EMBARCACION_TEXTBOX_ID, nombre, true);
        makeScreenshot();
    }

    @Step("Se introduce el año de fabricación: {0}")
    public void inputBuildingYearBoat(String year) {
        typeText(NOMBRE_EMBARCACION_TEXTBOX_ID, year, true);
        makeScreenshot();
    }

    public Boolean isDatosdeSeguroDisplayed() {
        return isElementVisible(DATOS_DE_SEGURO_TITLE_XPATH);
    }

    @Step("Clicar no botão Início a fim de retornar para a tela inical de Produção [Intranet ]")
    public void clicarInicioButton() {
        click(INICIO_BUTTON_ID);
    }

    @Step("Se selecciona la opción 'Seguro Nuevo'")
    public void selectSeguroNuevo() {
        click(SEGURO_NUEVO_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Equipamento'")
    public void selectEquipamento() {
        click(EQUIPAMENTO_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'COLHEITADEIRA'")
    public void selectColheitadeira() {
        click(COLHEITADEIRA_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'CASE BRASIL'")
    public void selectCaseBrasil() {
        click(CASE_BRASIL_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la opción '100'")
    public void selectModelo100() {
        click(MODELO_100_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se introduce el valor de 'Anyo'")
    public void inputAnyo(String anyo) {
        typeText(ANYO_TEXTBOX_XPATH, anyo, true);
        makeScreenshot();
    }

    @Step("Se introduce el 'Valor del Equipamiento' {0}")
    public void inputValorEquipamiento2(String valorEquipamiento) {
        typeText(VALOR_EQUIPAMIENTO2_INPUT_ID, valorEquipamiento, true);
        makeScreenshot();
    }

    @Step("Se introduce el 'Numero de Chasis' {0}")
    public void inputNumeroChasis2(String numChasis) {
        typeText(NUMERO_CHASIS2_INPUT_ID, numChasis, true);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Equipamento 0KM - Nao'")
    public void clickEquipamento0KMRadioButton() {
        click(EQUIPAMENTO_0KM_NO_RADIOBUTTON_XPATH);
        getEquipamento0KMRadioButton().click();
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Equipamento poderá ser cedido - Nao'")
    public void clickEquipamentoCedidoRadioButton() {
        click(EQUIPAMENTO_CEDIDO_NO_RADIOBUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Se introduce el CEP {0}")
    public void inputCEP2(String cep) {
        typeText(CEP2_TEXTBOX_ID, cep, true);
        makeScreenshot();
    }

    @Step("Se introduce el valor de 'Endereso'")
    public void inputEndereso(String valor) {
        typeText(ENDERESO_TEXTBOX_XPATH, valor, true);
        makeScreenshot();
    }

    @Step("Se introducen el Numero de Endereso: {0}")
    public void inputNumeroEndereso2(String numero) {
        typeText(NUMERO_ENDERESO2_TEXTBOX_XPATH, numero, true);
        makeScreenshot();
    }

    @Step("Se introduce el valor de 'Complemento'")
    public void inputComplemento(String valor) {
        typeText(COMPLEMENTO_TEXTBOX_XPATH, valor, true);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Adquirido através de recursos próprios'")
    public void selectRecursosPropios() {
        click(RECURSOS_PROPIOS_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Seguro Novo'")
    public void selectSeguroNovo() {
        click(SEGURO_NOVO_OPTION_XPATH);
        makeScreenshot();
    }

    public void selectTipoSeguro(String option) {
        selectOptionDDM(DDM.SEA, DDM_TIPO_SEGURO_ID, option);
        clickByJS(DDM_TIPO_SEGURO_ID);
        makeScreenshot();
    }

    @Step("Selecionar a opção 'Seguro Novo', quando produto é ''Equipamentos")
    public void selectSeguroNovoEquipamentos() {
        click(SEGURO_NOVO_EQUIPAMIENTOS_OPTION_XPATH);
        makeScreenshot();
    }

    @Step("Se selecciona la 'Categoria Riesco': " + "{0}")
    public void selectCategoriaRiesgoEnterprise(String option) {
        Select st = new Select(driver.findElement(By.xpath(DDM_CAT_RISCO_ENTERPRISE_XPATH)));
        st.selectByVisibleText(option);
        makeScreenshot();
    }

    @Step("Se selecciona la 'Categoria Riesco': " + "{0}")
    public void selectCategoriaRiesgoNautica(String option) {
        selectOptionDDM(DDM.SEA, DDM_CAT_RISCO_NAUTICA_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona el Tipo de Franquia: {0}")
    public void selectFranquicia(String option) {
        selectOptionDDM(DDM.SEA, DDM_FRANQUICIA_ID, option);
        makeScreenshot();
    }

    @Step("Seleciona a opção de multiplaFranquia: {0}")
    public void selectMultFranquia(String option) {
        selectOptionDDM(DDM.SEA, DDM_MULT_FRANQUIA_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona el Perimetro de Cobertura: {0}")
    public void selectAreaCobertura(String option) {
        selectOptionDDM(DDM.SEA, DDM_AREA_COBERTURA_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Objeto do Seguro' a opção '{0}'")
    public void selectObjetoSeguro(String option) {
        selectOptionDDM(DDM.SEA, DDM_OBJETO_SEGURO_XPATH, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Categoria do risco' a opção '{0}'")
    public void selectCatRisco(String option) {
        selectOptionDDM(DDM.SEA, CAT_RISCO_EQUIPAMENTO_INPUT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Quantidade de elevadores' a opção '{0}'")
    public void selectQuantidadeElevadores(String option) {
        selectOptionDDM(DDM.SEA, DDM_QUANTIDADE_ELEVADORES_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Idade do imóvel' a opção '{0}'")
    public void selectIdadeImovel(String option) {
        selectOptionDDM(DDM.SEA, DDM_IDADE_IMOVEL_ID, option);
        makeScreenshot();
    }

    //Condomínio
    @Step("Selecionar no combo 'Congênere' a opção '{0}'")
    public void selectCongenere(String option) {
        selectOptionDDM(DDM.SEA, DDM_IDADE_IMOVEL_ID, option);
        makeScreenshot();
    }

    //Empresa PME [2024]
    @Step("Selecionar no combo 'Congênere' a opção '{0}'")
    public void selectCongenere2(String option) {
        selectOptionDDM(DDM.SEA, DDM_CONGENERE2_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Seguradora' a opção '{0}'")
    public void selectSeguradora(String option) {
        selectOptionDDM(DDM.SEA, DDM_SEGURADORA_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar no combo 'Quantidade de andares' a opção '{0}'")
    public void selectQuantidadeAndares(String option) {
        selectOptionDDM(DDM.SEA, DDM_QUANTIDADE_ANDARES_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la opción {0}")
    public void selectGuardaTipoResidenciaHabitual(String option) {
        selectOptionDDM(DDM.SEA, GUARDA_TIPO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la opción '{0}'")
    public void selectLocalIndustria(String option) {
        selectOptionDDM(DDM.SEA, DDM_LOCAL_UTILI_XPATH, option);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Propulsión a Motor' {0}")
    public void selectTipoPropulsionAMotor(String option) {
        selectOptionDDM(DDM.SEA, TIPO_PROPULSION_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Area de Navegación' {0}")
    public void selectAreaNavegacion(String option) {
        selectOptionDDM(DDM.SEA, AREA_NAVEGACION_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar, no combo 'Tipo de Contrução', a opção '{0}'") //Combo objeto do seguro Epac
    public void selectTipoConstrucaoResidencia(String option) {
        click(String.format(OPTION_TIPO_COBERTURA_XPATH, option));
        makeScreenshot();
    }

    @Step("Selecionar, no combo 'Tipo de Contrução', a opção '{0}'") //Combo objeto do seguro Epac
    public void selectTipoConstrucaoEmpresarial(String option) {
        click(String.format(TIPO_CONSTRUCAO_XPATH, option));
        makeScreenshot();
    }

    @Step("Selecionar, no combo 'Tipo de Assistência', a opção '{0}'") //Combo objeto do seguro Epac
    public void selectTipoAssistenciaResidencia(String option) {
        click(String.format(OPTION_TIPO_ASSISTENCIA_XPATH, option));
        makeScreenshot();
    }

    @Step("Se selecciona material casco {0}")
    public void selectMaterialCasco(String option) {
        selectOptionDDM(DDM.SEA, MATERIAL_CASCO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la utilizacion {0}")
    public void selectUtilizacion(String option) {
        selectOptionDDM(DDM.SEA, UTILIZACION_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a utilização {0}")
    public void selectUtilizacaoAuto(String option) {
        selectOptionDDM(DDM.SEA, UTILIZACAO_AUTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a utilização {0}")
    public void selectZeroKm(String option) {
        if (getZeroKMDDM().isEnabled()) {
            selectOptionDDM(DDM.SEA, ZEROKM_SELECT_ID, option);
            makeScreenshot();
        }
    }

    @Step("Selecionar a opção '{0}' no combo 'Estado Civil'")
    public void selectEstadoCivilAuto(String option) {
        selectOptionDDM(DDM.SEA, ESTADO_CIVIL_AUTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Estado Civil'")
    public void selectEstadoCivilMoto(String option) {
        selectOptionDDM(DDM.SEA, ESTADO_CIVIL_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Estado Civil'")
    public void selectEstadoCivilCaminhao(String option) {
        selectOptionDDM(DDM.SEA, ESTADO_CIVIL_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Deseja ampliar cobertura...'")
    public void selectAmpliarCobertura18a25Auto(String option) {
        selectOptionDDM(DDM.SEA, RESPOSTA_FILHOS_AUTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Deseja ampliar cobertura...'")
    public void selectAmpliarCobertura18a25Moto(String option) {
        selectOptionDDM(DDM.SEA, RESPOSTA_FILHOS_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Dispositivo Atual'")
    public void selectDispositivoAtual(String option) {
        selectOptionDDM(DDM.SEA, DISPOSITIVO_ATUAL_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Classe de Bônus' a opção: '{0}'")
    public void selectClasseBonus(String option) {
        selectOptionDDM(DDM.SEA, DDM_CLASSE_BONUS_XPATH, option);
        makeScreenshot();
    }

    @Step("Seleciona no combo 'Tipo' a opção: '{0}'")
    public void selectTipoEndereco(String option) {
        selectOptionDDM(DDM.SEA, DDM_TIPO_XPATH, option);
        makeScreenshot();
    }

    @Step("Insere o número do 'ramo' '{0}' como opção")
    public void inputNumRamo(String ramo) {
        performBackgroundClick();
        selectOptionDDM(DDM.SEA, NUM_RAMO_XPATH, ramo);
        makeScreenshot();
    }

    @Step("Preencher a data '{0}' no campo 'Data de nascimento'")
    public void inputDataNascimentoAuto(String data) {
        typeText(DATA_NASCIMENTO_AUTO_INPUT_ID, data, true);
        makeScreenshot();
    }

    @Step("Preencher a data '{0}' no campo 'Data de nascimento'")
    public void inputDataNascimentoMoto(String data) {
        typeText(DATA_NASCIMENTO_MOTO_INPUT_ID, data, true);
        makeScreenshot();
    }

    @Step("Preencher a data '{0}' no campo 'Data de nascimento'")
    public void inputDataNascimentoCaminhao(String data) {
        typeText(DATA_NASCIMENTO_CAMINHAO_INPUT_ID, data, true);
        makeScreenshot();
    }

    @Step("Selecionar o gênero '{0}' no combo 'Sexo do Condutor'")
    public void selectSexoDoCondutorAuto(String option) {
        selectOptionDDM(DDM.SEA, GENERO_CONDUTOR_AUTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar o gênero '{0}' no combo 'Sexo do Condutor'")
    public void selectSexoDoCondutorMoto(String option) {
        selectOptionDDM(DDM.SEA, GENERO_CONDUTOR_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar o gênero '{0}' no combo 'Carga mais frequente transportada' e clicar em 'ADICIONAR'")
    public void selectCargaMaisFrequenteBPA(String option) {
        selectOptionDDM(DDM.SEA, CARGA_FREQUENTE_BPA_SELECT_ID, option);
        click(ADICIONAR_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Selecionar o gênero '{0}' no combo 'Sexo do Condutor'")
    public void selectSexoDoCondutorCaminhao(String option) {
        selectOptionDDM(DDM.SEA, GENERO_CONDUTOR_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar o local '{0}' no combo 'O principal condutor reside em'")
    public void selectCondutorResideAuto(String option) {
        selectOptionDDM(DDM.SEA, CONDUTOR_RESIDE_AUTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar o local '{0}' no combo 'O principal condutor reside em'")
    public void selectCondutorResideMoto(String option) {
        selectOptionDDM(DDM.SEA, CONDUTOR_RESIDE_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Mantém o veículo em garagem - Residência'")
    public void selectVeiculoEmGaragemResidenciaMoto(String option) {
        selectOptionDDM(DDM.SEA, VEICULO_EM_GARAGEM_RESIDENCIA_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Mantém o veículo em garagem quando não está em serviço?'")
    public void selectVeiculoEmGaragemCaminhao(String option) {
        selectOptionDDM(DDM.SEA, VEICULO_EM_GARAGEM_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Motorista Principal Utilizado'")
    public void selectMotoristaPrincipalCaminhao(String option) {
        selectOptionDDM(DDM.SEA, MOTORISTA_PRINCIPAL_UTILIZADO_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Possui seguro da carga transportada durante a viagem'")
    public void selectPossuiSeguroCaminhao(String option) {
        selectOptionDDM(DDM.SEA, POSSUI_SEGURO_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'O Veiculo está alienado?'")
    public void selectVeiculoAlienadoCaminhao(String option) {
        selectOptionDDM(DDM.SEA, VEICULO_ALIENADO_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Regiões de circulação mais frequentes'")
    public void selectRegioesDeCirculacaoCaminhao(String option) {
        selectOptionDDM(DDM.SEA, CIRCULACAO_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Sistema de gerenciamento de risco'")
    public void selectSistemaGerRiscoCaminhao(String option) {
        selectOptionDDM(DDM.SEA, GER_RISCO_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Dirige após 22h por mais de 15% do tempo semanal?'")
    public void selectDirigeApos22hCaminhao(String option) {
        selectOptionDDM(DDM.SEA, DIRIGE_APOS_22h_CAMINHAO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Mantém o veículo em garagem - Trabalho'")
    public void selectVeiculoEmGaragemTrabalhoMoto(String option) {
        selectOptionDDM(DDM.SEA, VEICULO_EM_GARAGEM_TRABALHO_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Mantém o veículo em garagem - Faculdade'")
    public void selectVeiculoEmGaragemFaculdadeMoto(String option) {
        selectOptionDDM(DDM.SEA, VEICULO_EM_GARAGEM_FACULDADE_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Lant.Faróis e Retrovisores'")
    public void selectPlanoVidrosMoto(String option) {
        selectOptionDDM(DDM.SEA, PLANO_VIDROS_MOTO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Selecionar a opção '{0}' no combo 'Assistência 24 horas'")
    public void selectPlanoAssistencia24h(String option) {
        selectOptionDDM(DDM.SEA, SELECT_ASSISTENCIA24h_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona la opción 'Adquirido Consorcio'")
    public void selectAdquiridoConsorcio(String option) {
        selectOptionDDM(DDM.SEA, TIPO_ADQUISICION_AGRICOLA_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Seleciona a opção 'Adiquirido através de recursos próprios'")
    public void selectAdquiridoRecursosProprios(String option) {
        selectOptionDDM(DDM.SEA, TIPO_ADQUISICION_AGRICOLA_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} como tipo de aquisição")
    public void selectTipoAquiscicao(String option) {
        selectOptionDDM(DDM.SEA, TIPO_AQUISICAO_AGRICOLA_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona tipo construccion {0}")
    public void selectTipoConstruccion(String option) {
        selectOptionDDM(DDM.SEA, TIPO_CONSTRUCCION_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se selecciona el municipio {0}")
    public void selectMunicipio(String option) {
        selectOptionDDM(DDM.SEA, MUNICIPIO_SELECT_ID, option);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Credito Rural No'")
    public void clickCreditoRuralNoRadioButton() {
        click(CREDITO_RURAL_NO_RADIOBUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Ultimos Anyos No'")
    public void clickNoSiniestersLast4Years() {
        click(ULTIMOSANYOS_NO_RADIOBUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Se hace click en la opción 'Artesanal'")
    public void clickArtesanalRadioButton() {
        click(ARTESANAL_NO_RADIOBUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Aceitar', quando o produto for 'Equipamentos'")
    public void clickAceptarEquipamientosButton() {
        click(ARTESANAL_NO_RADIOBUTTON_XPATH);
        makeScreenshot();

    }

    @Step("Clicar na 'Lupa' do campo Chassi")
    public void clickChassiLupaButton() {
        waitForClickable(CHASIS_LUPA_BUTTON_ID);
        scrollToElement(CHASIS_LUPA_BUTTON_ID);
        clickByJS(CHASIS_LUPA_BUTTON_ID);
        acceptAlert(2);
        makeScreenshot();
    }

    @Step("Se hace click en el botón 'Avancar' y se comprueba la alerta")
    public void clickAvancar2Button(String endereso) {
        click(AVANCAR_BUTTON_XPATH);
        incrementoNumEnderesoIfAlertaIsDisplayed(endereso);
        clickAvancarButtonIfHistoricoAlertaIsDisplayed();
        makeScreenshot();
    }

    @Step("Se incrementa en 1 el Número de Endereso {0} si aparece la Alerta de que ya está en uso dicho número")
    public void incrementoNumEnderesoIfAlertaIsDisplayed(String endereso) {
        if ((isElementVisible(ALERTA_NUMERO_ENDERESO_REPETIDO_XPATH, 5))) {
            int num = Integer.parseInt(endereso);
            typeText(NUMERO_ENDERESO_TEXTBOX_XPATH, String.valueOf(num + 1), true);
            click(AVANCAR_BUTTON_XPATH);
        }
        makeScreenshot();
    }

    @Step("Se incrementa en 1 el Número de Endereso {0} si aparece la Alerta de que ya está en uso dicho número")
    public void validarRiscoDuplicidadeporCep() {
        if ((isElementVisible(ALERTA_DUPLICIDADE_NUMERO_ENDERECO_jA_SEGURADO_XPATH, 3))) {
            NoDriverUtils.await(5);
            inputNumeroNovamente();
            clickAvancarButton();
        }
        makeScreenshot();
    }

    @Step("Aumentando o valor garantido em {0} a mais")
    public void increaseGarantidoValue(String valor) {
        NoDriverUtils.await(2);
        click(VALOR_GARANTIDO_INPUT_XPATH);
        NoDriverUtils.await(1);

        String actualValueString = getValueGaratidoInput().getAttribute("value").replace(".", "").replace(",", "");
        int valorAtual = Integer.parseInt(actualValueString);
        int valueAserAcrescido = Integer.parseInt(valor);
        int valorNovoAcrescido = valorAtual + valueAserAcrescido;

        getValueGaratidoInput().clear();
        NoDriverUtils.await(1);
        typeText(VALOR_GARANTIDO_INPUT_XPATH, String.valueOf(valorNovoAcrescido), true);
        makeScreenshot();
    }

    @Step("Insere o valor garantido com {0}")
    public void inputValorGarantido(String valor) {
        if (!valor.isEmpty()) {
            typeText(VALOR_GARANTIDO_INPUT_XPATH, (valor), true);
            makeScreenshot();
        }
    }

    @Step("Se hace click en el botón 'Avancar' mientras la alerta 'Patrimonio Historico' esté visible")
    public void clickAvancarButtonIfHistoricoAlertaIsDisplayed() {
        if ((isElementVisible(ALERTA_PATRIMONIO_HISTORICO_XPATH, 5))) {
            click(AVANCAR_BUTTON_XPATH);
            makeScreenshot();
        }
    }

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancarButton() {
        click(AVANCAR_BUTTON2_ID);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Voltar'")
    public void clickVoltarButton() {
        click(VOLTAR_BUTTON2_ID);
        makeScreenshot();
    }


    public List<String> getListMensagemErro() {
        List<String> listaErros = new ArrayList<>();
        List<WebElement> erros;

        erros = driver.findElements(By.xpath(LISTA_ERROS_XPATH));

        for (WebElement erro : erros) {
            listaErros.add(erro.getText());
        }
        return listaErros;
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

    @Step("Clicar no botão 'Avançar'")
    public void clickAvancar2Button() {
        click(AVANCAR_BUTTON_XPATH);
        makeScreenshot();
    }

    @Step("Se introduce el 'Numero de Chasis' {0}")
    public void inputNumeroChasis(String numChasis) {
        typeText(NUMERO_CHASIS_INPUT_ID, numChasis, true);
        makeScreenshot();
    }

    @Step("Se introduce el 'Valor del Equipamiento' {0}")
    public void inputValorEquipamiento(String valorEquipamiento) {
        typeText(VALOR_EQUIPAMIENTO_INPUT_ID, valorEquipamiento, true);
        makeScreenshot();
    }

    @Step("Se introduce la 'Marca' {0}")
    public void inputMarca(String marca) {
        typeText(MARCA_INPUT_ID, marca, true);
        makeScreenshot();
    }

    @Step("Se introduce la 'Modelo' {0}")
    public void inputModelo(String modelo) {
        typeText(MODELO_INPUT_ID, modelo, true);
        makeScreenshot();
    }

    @Step("Se introduce el 'Año de Fabricación' {0}")
    public void inputAnnoFabricacion(String annoFabricacion) {
        typeText(ANNO_FABRICACION_INPUT_ID, annoFabricacion, true);
        makeScreenshot();
    }

    @Step("Clicar no botão 'Aceitar'")
    public void clickAceitar() {
        click(ACEITAR_BUTTON_XPATH, true);
        makeScreenshot();
    }


    @Step("Se selecciona la categoria riesgo {0}")
    public void selectCatRiesgoRural(String option) {
        selectOptionDDM(DDM.SEA, DDM_CAT_RIESGO_RURAL_ID, option);
        makeScreenshot();
    }

    private String getMensagemErroForaPoliticaCIA() {
        return getTxtErroForaPoliticaCIA().getText();
    }

    private String getMensagemErroApoliceJaendossadaNoDiaAtual() {
        return getTxtErroApoliceJaendossadaNoDiaAtual().getText();
    }

    private String getMensagemErroApoliceJaCancelada() {
        return getTxtErroApoliceJaCancelada().getText();
    }

    public String getNumeroChassi() {
        if (!isNomeAlteradoDisplayed()) {
            getBackButton().click();
        }
        String chassi = getChassisInput().getAttribute("value");
        clickAceitar();
        return chassi;
    }


    @Step("Se introduce el valor de la embarcacion {0}")
    public void inputValorEmbarcacion(String valor) {
        typeText(VALOR_EMBARCACION_TEXTBOX_XPATH, valor, true);
        makeScreenshot();
    }

    @Step("Selecionar Capital 'Cobertura Básica', inclui o valor {0}, e clicar no botão 'Adicionar Capital'")
    public void addCapital(String valor) {
        click(CAPITAL_COMBO_ID);
        typeText(CAPITAL_COMBO_FIRSTOPTION_XPATH, valor, true);
        click(ADICIONAR_CAPITAIL_BUTTON_ID);
        makeScreenshot();
    }

    @Step("Se hace click en el check 'Si' de la opcion 'Indenizasao Seguro Novo'")
    public void clickIndenizasaoSeguroNovoCheck() {
        click(INDENIZASAO_VALOR_SEGURO_NOVO_CHECK_ID);
        makeScreenshot();
    }

    @Step("Clicar em 'Avançar' quando o nome for alterado sistematicamente")
    public void clickAceitarNomeAlterado() {
        if (isNomeAlteradoDisplayed()) {
            clickAceitar();
        }
    }

    @Step("Clicar em 'Avançar' quando o veículo possuir restrição técnica")
    public void clickAceitarRestricaoTecnica() {
        if (isRestricaoTecnicaDisplayed()) {
            clickAceitar();
        }
    }

    @Step("Selecionar a opção '{0}' do combo 'Categoria do Risco'")
    public void selectCategoriaRiscoOption(String option) {
        selectOptionDDM(DDM.SEA, CATEGORIA_RISCO_COMBO_XPATH, option);
        makeScreenshot();
    }

    @Step("Seleciona a opção {0} para o campo isoPainel")
    public void selectIsoPainel(String option) {
        NoDriverUtils.await(10);
        if (!option.isEmpty()) {
            click(String.format(OPTION_ISOPANEL_XPATH, option));
            makeScreenshot();
        }
    }

    @Step("Seleciona a opção {0} para o campo isoPainel")
    public void selectIsoPainel_Empresarial(String option) {
        if (option.contains("S") || (option.contains("im"))) {
            option = "S";
        } else {
            option = "N";
        }
        click(String.format(OPTION_ISOPANEL_EMPRESARIAL, option, true));
    }

    @Step("Selecionar, no combo 'Tipo de Construção', a opção '{0}'") //Combo objeto do seguro Epac
    public void selectTipoConstrucao(String option) {
        selectOptionDDM(DDM.SEA, TIPO_CONSTRUCAO_XPATH, option);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Código de Companhia' com: {0}")
    public void inputCodigoCompanhia(String numCodCompanhia) {
        typeText(INPUT_COD_COMPANHIA_ID, numCodCompanhia, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'Filial' com: {0}")
    public void inputNumFilial(String numFilial) {
        typeText(INPUT_NUM_FILIAL_ID, numFilial, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'No.Ci' com: {0}")
    public void inputNumCI(String numCI) {
        typeText(INPUT_NUMERO_CI_ID, numCI, true);
        makeScreenshot();
    }

    @Step("Preencher o campo 'No. Item' com: {0}")
    public void inputNumItens(String numItens) {
        typeText(INPUT_NUM_ITENS_ID, numItens, true);
        makeScreenshot();
    }


    public String getMensagemErroTxt() {
        return getMensagemErro().getText();
    }

    //WEBELEMENTS
    private WebElement getChassisInput() {
        return driver.findElement(By.xpath(CHASSI_TEXTBOX_XPATH));
    }

    private WebElement getBackButton() {
        return driver.findElement(By.id(VOLTAR_BUTTON_ID));
    }

    private WebElement getZeroKMDDM() {
        return driver.findElement(By.id(ZEROKM_SELECT_ID));
    }

    private WebElement getEquipamento0KMRadioButton() {
        return driver.findElement(By.xpath(EQUIPAMENTO_0KM_NO_RADIOBUTTON_XPATH));
    }

    private WebElement getPrimeiroVeiculo() {
        return driver.findElement(By.id(MODELO_CARRO_ID));
    }

    private WebElement getMensagemErro() {
        return driver.findElement(By.xpath(TXT_MENSAGEM_ERRO_XPATH));
    }

    private WebElement getTxtErroForaPoliticaCIA() {
        return driver.findElement(By.xpath(TXT_FORA_POLITICA_CIA_XPATH));
    }

    private WebElement getTxtErroApoliceJaendossadaNoDiaAtual() {
        return driver.findElement(By.xpath(TXT_ERROR_APOLICE_JA_ENDOSSADA_XPATH));
    }

    private WebElement getTxtErroApoliceJaCancelada() {
        return driver.findElement(By.xpath(TXT_ERROR_APOLICE_JA_CANCELADA_XPATH));
    }

    private WebElement getValueGaratidoInput() {
        return driver.findElement(By.xpath(VALOR_GARANTIDO_INPUT_XPATH));
    }
}
