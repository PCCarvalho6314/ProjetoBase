package testcases.ui.Auto.releaseTarifa1211;

import bases.Base;
import data.CotacaoAutoData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.epac.*;
import pages.epac.login.PortalProductIssuancePage;
import pages.epac.login.PortalsPage;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class IssuanceIndividualAuto1211ReleaseTarifasIssuanceFlowAZBEpacTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("3", "Auto", "auto");
    }

    private static final String TEST_CASE_NAME = "issuanceIndividualAuto1211ReleaseTarifasIssuanceFlowAZBEpac";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPA = "BPA - BR_GER SIST AUTOMOVEL";
    String numApolice, numProposta;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS)
    @Owner(SQUAD_BPA)
    public void issuanceIndividualAuto1211ReleaseTarifasIssuanceFlowAZBEpac(CotacaoAutoData data) {
        String pathCaptura = screenshotPath + "auto/" + data.getCenario() + "/";
        numApolice = "";
        numProposta = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosCotacaoPage dadosCotacaoPage = new DadosCotacaoPage(getDriver());
        ResultadosPage resultadosPage = new ResultadosPage(getDriver());
        DadosComplementaresPage dadosComplementaresPage = new DadosComplementaresPage(getDriver());
        ResumoPage resumoPage = new ResumoPage(getDriver());
        PagamentoPage pagamentoPage = new PagamentoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.AUTO, data.getCorretor());

        //Dados Básicos
        Asserts.assertTrueScreenshot(dadosCotacaoPage.isContactDisplayed(), true);
        dadosCotacaoPage.selectTipoSeguro(data.getTipoSeguro());
        dadosCotacaoPage.selectClasseBonus(data.getClasseBonus());
        dadosCotacaoPage.inputNumeroCI(data.getNumeroCI());
        dadosCotacaoPage.inputCpfCnpj(data.getCpfcnpj());
        dadosCotacaoPage.inputCep(data.getCep());
        dadosCotacaoPage.inputCpfCondutor(data.getCpfcnpj(), data.getCpfCondutor());
        dadosCotacaoPage.selectEstadoCivil(data.getEstadoCivil());
        dadosCotacaoPage.inputChassi(data.getChassi());
        dadosCotacaoPage.selectUsoComercial(data.getUsoComercial(), data.getFinalidadeUso());
        dadosCotacaoPage.clickMotoristaIndeterminado(data.getMotoristaIndeterminado());
        dadosCotacaoPage.clickVeiculo0Km(data.getVeiculo0Km());
        dadosCotacaoPage.inputBlindagem(data.getBlindagem(), data.getValorBlindagem());
        dadosCotacaoPage.clickCondutor18a25(data.getCondutor18a25());
        dadosCotacaoPage.selectResidencia(data.getTipoResidencia());
        dadosCotacaoPage.clickFranquia(data.getFranquia());
        dadosCotacaoPage.clickAssistencia(data.getAssistencia24h());
        dadosCotacaoPage.clickCarroReserva(data.getCarroReserva());
        dadosCotacaoPage.clickVidros(data.getVidros());
        dadosCotacaoPage.clickAvancar();

        //Resultados
        Asserts.assertTrueScreenshot(resultadosPage.isNumeroDaOperacaoDisplayed(), true);
        resultadosPage.clickFormaPagamentoBoletoButton();
        String numOperacao = resultadosPage.getNumeroOperacao();
        resultadosPage.clicarSalvarCotacao();
        resultadosPage.clickClosePopUpButton();
        String numCotacao = resultadosPage.getNumeroCotacao();

        resultadosPage.clickComprarPacoteCobertura(data.getPacoteCoberturas());

        //Dados complementares
        Asserts.assertTrueScreenshot(dadosComplementaresPage.isDadoComplementaresPageDisplayed(), true);
        dadosComplementaresPage.inputMailAseg(data.getEmail());
        dadosComplementaresPage.inputCelularAseg(data.getCelular());
        dadosComplementaresPage.inputProfAseg(data.getProfissao());
        dadosComplementaresPage.inputRenda(data.getRenda());
        dadosComplementaresPage.inputCep(data.getCep());
        dadosComplementaresPage.inputAddressNum(data.getNumEndereco());
        dadosComplementaresPage.inputDataSaidaVaiculo0km(data.getDataSaida());
        dadosComplementaresPage.clickNextButton();

//        Resumo
        Asserts.assertTrueScreenshot(resumoPage.isResumenPageDisplayed(), true);
        resumoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Resumo dos dados preenchidos");
        resumoPage.clickAvancarButton();

        //Caso de erro na página de Resumo, salva ao menos o número da operação
        if (resumoPage.isResumenPageDisplayed()) {
            errosRecebidosList = dadosCotacaoPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFileEmissaoApolice(data.getCenario(), "Auto", "Emissao de apólices após deploy de tarifas",
                    data.getChassi(), numOperacao, numCotacao, "", "", formatedErrosRecebidosList);
        }

        //Pagamento
        Asserts.assertTrueScreenshot(pagamentoPage.isPagamentPageDisplayed(), true);
        pagamentoPage.clickEmitirButton();

        //Validações
        try {
            Asserts.assertTrueScreenshot(pagamentoPage.isConfirmationAutoDisplayed(), true);

            if (pagamentoPage.isApoliceNumberDisplayed()) {
                pagamentoPage.exibeNumeroApolice();
                numApolice = pagamentoPage.getApoliceTxt();
                pagamentoPage.exibeNumeroApolice();

            } else {
                pagamentoPage.exibeNumeroProposta();
                numProposta = pagamentoPage.getPropostaTxt();
                pagamentoPage.exibeNumeroProposta();
            }
            pagamentoPage.exibeNumeroOperacao(numOperacao);
            pagamentoPage.exibeNumeroCotacao(numCotacao);
            pagamentoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Emissão realizada com sucesso");

            // Gerar dados para relatórioConstantsAzb
            CsvReport.appendToFileEmissaoApolice(data.getCenario(), "Auto", "Emissao de apólices após deploy de tarifas",
                    data.getChassi(), numOperacao, numCotacao, numProposta, numApolice, "");
        } catch (AssertionError e) {
            errosRecebidosList = dadosCotacaoPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFileEmissaoApolice(data.getCenario(), "Auto", "Emissao de proposta e apólices após deploy de tarifas",
                    data.getChassi(), numOperacao, numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + pagamentoPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}