package testcases.ui.Vida.Epac.Emissao;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.VidaData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.epac.DadosRiscoPage;
import pages.epac.Vida.*;
import pages.epac.login.PortalProductIssuancePage;
import pages.epac.login.PortalsPage;
import utils.api.ApisPrint_Pdf;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class IssuanceTradicionalGlobalLifeIssuanceFlowAZBEpacTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("5", "Emissao_Epac", "vida");
    }

    private static final String TEST_CASE_NAME = "IssuanceTradicionalGlobalLifeIssuanceFlowAZBEpacTest";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPA = "BPD - BR_GER SIST SAUDE, VIDA E AGRO";
    String numCotacao, numProposta, numApolice, numOperacao;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS)
    @Owner(SQUAD_BPA)
    public void IssuanceTradicionalGlobalLifeIssuanceFlowAZBEpac(VidaData data) {
        String pathCaptura = screenshotPath + "Vida Tradicional Global/" + data.getCenario() + "/";
        numCotacao = "";
        numProposta = "";
        numOperacao = "";
        numApolice = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosRiscoPage dadosRiscoPage = new DadosRiscoPage(getDriver());
        PrecosCoberturaAssistenciasPage precosCoberturaAssistenciasPage = new PrecosCoberturaAssistenciasPage(getDriver());
        DadosComplementaresPage dadosComplementaresPage = new DadosComplementaresPage(getDriver());
        PagamentoPage pagamentoPage = new PagamentoPage(getDriver());
        ResumoPage resumoPage = new ResumoPage(getDriver());
        ConfirmacaoEmissaoPage confirmacaoEmissaoPage = new ConfirmacaoEmissaoPage(getDriver());
        pages.epac.ConfirmacaoEmissaoPage confirmacaoEmissaoPage1 = new pages.epac.ConfirmacaoEmissaoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.VIDA_2, data.getCorretor());

        //Dados de Risco
        Asserts.assertTrueScreenshot(dadosRiscoPage.isDadosRiscoDisplayed(), true);
        dadosRiscoPage.inputCpfCnpj(data.getCnpj_Cpf());
        dadosRiscoPage.selectTipoSeguro(data.getTipoSeguro());
        dadosRiscoPage.inputDataInicioVigencia(data.getDataInicioVigencia());
        dadosRiscoPage.inputNumeroVidasEmpregados(data.getNmrVidasEmpregados());
        dadosRiscoPage.inputNumeroVidasSocios(data.getNmrVidasSocios());
        dadosRiscoPage.InputCapitalIndividualEmpregados(data.getCapital_Individual_Empregados());
        dadosRiscoPage.InputCapitalIndividualSocios(data.getCapital_Individual_Socios());
        dadosRiscoPage.clickAvancar();

        //Preços Coberturas e Assistências
        precosCoberturaAssistenciasPage.isPrecoCoberturasAssistenciaPageDisplayed();
        precosCoberturaAssistenciasPage.selecionarAssitencia(data.getAssistencia());
        precosCoberturaAssistenciasPage.selecionarTipoCoberturaAssistencia(data.getCoberturaAssistencia());
        precosCoberturaAssistenciasPage.selecionarValorAssistencia(data.getVlrAssistencia());
        precosCoberturaAssistenciasPage.inputValorComissao(data.getComissao());
        precosCoberturaAssistenciasPage.inputValorAgenciamento(data.getAgenciamento());
        precosCoberturaAssistenciasPage.clickRecalcular();
        precosCoberturaAssistenciasPage.selectFormaPagamento(data.getFormaPagamento());
        precosCoberturaAssistenciasPage.selectQtdParcelas(data.getQtdParcelas());
        precosCoberturaAssistenciasPage.clickSalvarCotacao();
        numCotacao = precosCoberturaAssistenciasPage.getNumeroCotacao();
        System.out.println(numCotacao);
        precosCoberturaAssistenciasPage.clickFecharModalCotacao();
        precosCoberturaAssistenciasPage.clickAvancar();

        //Dados Complementares
        dadosComplementaresPage.isDadosComplementaresPageDisplayed();
        dadosComplementaresPage.inputCelular(data.getCelular());
        dadosComplementaresPage.clickAvancar();

        //Pagamento
        pagamentoPage.isPagamentoPageDisplayed();
        pagamentoPage.selectDiaVencimento(data.getDiaPagamento());
        pagamentoPage.clickAvancar();
        pagamentoPage.clickSimConfirmarDadosPagamento();

        //Resumo
        resumoPage.isResumoPageDisplayed();

        try {
            resumoPage.clickEmitirButton();

            //Confirmação de Emissão
            confirmacaoEmissaoPage.isConfirmacaoPageDisplayed();
            confirmacaoEmissaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Emissão realizada com sucesso!");
            numProposta = confirmacaoEmissaoPage.getNumeroProposta();
            numOperacao = confirmacaoEmissaoPage.getNumeroOperacao();
            System.out.println(numProposta);
            System.out.println(numOperacao);

            //Baixar PDFS - Se não estiver executando no Jenkins
//                confirmacaoEmissaoPage1.baixarPDFViaAPI(numProposta, "Proposta", data.getCenario());
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_03, "Proposta", numProposta);

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_03, data.getCenario(), numCotacao, numProposta, numApolice, "Passed");

        } catch (AssertionError e) {
            confirmacaoEmissaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Erro ao tentar emitir");

            errosRecebidosList = confirmacaoEmissaoPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_03, data.getCenario(), numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + confirmacaoEmissaoPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}