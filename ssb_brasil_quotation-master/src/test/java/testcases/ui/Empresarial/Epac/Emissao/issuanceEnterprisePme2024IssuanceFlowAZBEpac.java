package testcases.ui.Empresarial.Epac.Emissao;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.EmpresarialData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.epac.*;
import pages.epac.login.PortalProductIssuancePage;
import pages.epac.login.PortalsPage;
import utils.api.ApisPrint_Pdf;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class issuanceEnterprisePme2024IssuanceFlowAZBEpac extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("6", "Emissao_Epac", "empresa");
    }

    private static final String TEST_CASE_NAME = "issuanceEnterprisePme2024IssuanceFlowAZBEpac";
    private static final String DATA_PROVIDER_NAME = "dadosEmpresarialPME";
    private static final String SQUAD_BPG = "BR_GER SIST CORPORATE, P&C, TRANSPORTES, AG";
    String numApolice, numProposta, numOperacao, numCotacao;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)

    @Config(portal = Portals.EPACPLUS)
    @Owner(SQUAD_BPG)
    public void issuanceEnterprisePme2024IssuanceFlowAZBEpac(EmpresarialData data) {
        String pathCaptura = screenshotPath + "Empresarial/" + data.getCenario() + "/";
        numOperacao = "";
        numCotacao = "";
        numApolice = "";
        numProposta = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosRiscoPage dadosRiscoPage = new DadosRiscoPage(getDriver());
        CoberturasPage coberturasPage = new CoberturasPage(getDriver());
        ValorSeguroPage valorSeguroPage = new ValorSeguroPage(getDriver());
        DadosComplementaresPage dadosComplementaresPage = new DadosComplementaresPage(getDriver());
        ResumoPage resumoPage = new ResumoPage(getDriver());
        PagamentoPage pagamentoPage = new PagamentoPage(getDriver());
        ConfirmacaoEmissaoPage confirmacionEmisionPage = new ConfirmacaoEmissaoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.EMPRESARIAL_PME, data.getCorretor());

        //Dados Básicos
        Asserts.assertTrueScreenshot(dadosRiscoPage.isDadosRiscoDisplayed(), true);
        dadosRiscoPage.selectTipoSeguro(data.getTipoSeguro());
        dadosRiscoPage.inputCpfCnpj(data.getCpfcnpj());
        dadosRiscoPage.selectIsencaoIOF(data.getIsencaoIOF());
        dadosRiscoPage.inputValorDanosMateriais(data.getValorDanosMateriais());
        dadosRiscoPage.selectContratacaoAdicional(data.getContratacaoAdicional());
        dadosRiscoPage.inputCep(data.getCep());
        dadosRiscoPage.inputNumeroRandom();
        dadosRiscoPage.inputCategoriaRisco(data.getCategoriaRisco());
        dadosRiscoPage.selectObjetoSeguro(data.getObjetoSeguro());
        dadosRiscoPage.selectIsoPainel(data.getTelhadoIsoPainel());
        dadosRiscoPage.selectTipoConstrucao(data.getTipoConstrucao());
        dadosRiscoPage.selectIndenizacaoValorNovo(data.getIndenizacaoValorNovo());
        dadosRiscoPage.selectProtecoesRisco(data.getProtecaoRisco());
        dadosRiscoPage.clickAvancar();

        //Coberturas
        Asserts.assertTrueScreenshot(coberturasPage.isCoberturasPageDisplayed(), true);
        coberturasPage.clickAvancar();

        //Valor do Seguro
        valorSeguroPage.isValorSeguroPageDisplayed();
        valorSeguroPage.selectFormaPagamento_E_NumParcelas(data.getFormaPagamento(), data.getQtdParcelas());
        valorSeguroPage.clickComprar();

        //Dados Complementares
        Asserts.assertTrueScreenshot(dadosComplementaresPage.isDadoComplementaresPageDisplayed(), true);
        dadosComplementaresPage.inputMailAseg(data.getEmail());
        dadosComplementaresPage.inputCelularAseg(data.getCelular());
        dadosComplementaresPage.inputTelefone(data.getTelefone());
        dadosComplementaresPage.inputNomeFantasia(data.getNomeFantasia());
        dadosComplementaresPage.inputAtividadePrincipal(data.getAtividadePrincipal());
        dadosComplementaresPage.selectComboNatureza(data.getNatureza());
        dadosComplementaresPage.selectComboOcupacao(data.getOcupacao());
        dadosComplementaresPage.selectComboFaturamento(data.getFaturamento());
        dadosComplementaresPage.selectPatrimonio(data.getPatrimonio());
        dadosComplementaresPage.clickAvancarButton();

        //Resumo
        Asserts.assertTrueScreenshot(resumoPage.isResumenPageDisplayed(), true);
        resumoPage.clickAvancarButton();

        //Pagamento
        numOperacao = pagamentoPage.getNumeroOperacao();
        numCotacao = pagamentoPage.getNumeroCotacao();
        pagamentoPage.clickAvancar();

        try {
            pagamentoPage.clickEmitirButton();

            //Validações
            Asserts.assertTrueScreenshot(confirmacionEmisionPage.isConfirmacionEmissaoDisplayed(), true);
            confirmacionEmisionPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Emissão realizada com sucesso");

            numProposta = confirmacionEmisionPage.getPropostaTxt();
            numApolice = confirmacionEmisionPage.getApoliceTxt_2();

            System.out.println("OK");
            System.out.println("Numero proposta: " + numProposta);

            //Baixar PDFS
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Cotação", numCotacao);
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Proposta", numProposta);
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Apólice", numApolice);

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_04, data.getCenario(), numCotacao, numProposta, numApolice, "Passed");

        } catch (AssertionError e) {
            System.out.println("Não OK!");

            confirmacionEmisionPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Erro ao tentar emitir");

            errosRecebidosList = confirmacionEmisionPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_04, data.getCenario(), numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + confirmacionEmisionPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}