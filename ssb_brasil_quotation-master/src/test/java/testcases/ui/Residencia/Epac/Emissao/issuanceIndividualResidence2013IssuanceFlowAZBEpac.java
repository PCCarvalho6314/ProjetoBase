package testcases.ui.Residencia.Epac.Emissao;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.CotacaoResidenceData;
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

public class issuanceIndividualResidence2013IssuanceFlowAZBEpac extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("4", "Emissao_Epac", "residencia");
    }

    private static final String TEST_CASE_NAME = "issuanceIndividualResidence2013IssuanceFlowAZBEpac";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPG = "BR_GER SIST CORPORATE, P&C, TRANSPORTES, AG";
    String numApolice, numProposta, numOperacao, numCotacao;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;
    private static Integer tentativas;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS)
    @Owner(SQUAD_BPG)
    public void issuanceIndividualResidence2013IssuanceFlowAZBEpac(CotacaoResidenceData data) {
        String pathCaptura = screenshotPath + "residencia/" + data.getCenario() + "/";
        numOperacao = "";
        numCotacao = "";
        numApolice = "";
        numProposta = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosCotacaoPage dadosCotacaoPage = new DadosCotacaoPage(getDriver());
        ValorSeguroPage valorSeguroPage = new ValorSeguroPage(getDriver());
        DadosComplementaresPage dadosComplementaresPage = new DadosComplementaresPage(getDriver());
        PagamentoPage pagamentoPage = new PagamentoPage(getDriver());
        ConfirmacaoEmissaoPage confirmacionEmisionPage = new ConfirmacaoEmissaoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.RESIDENCIA, data.getCorretor());

        //Dados Básicos
        Asserts.assertTrueScreenshot(dadosCotacaoPage.isCotacaoDisplayed(), true);
        dadosCotacaoPage.selectTipoSeguroResidencia(data.getTipoSeguro());
        dadosCotacaoPage.inputCpfCnpj_Residencia(data.getCpfcnpj());
        dadosCotacaoPage.inputCep_Residence(data.getCep());
        dadosCotacaoPage.waitLoadingToFinish();
        dadosCotacaoPage.selectTipoImovel(data.getTipoImovel());
        dadosCotacaoPage.selectTipoConstrucao(data.getTipoConstrucao());
        dadosCotacaoPage.selectUsoImovel(data.getUsoImovel());
        dadosCotacaoPage.selectIsoPainel(data.getTelhadoIsoPainel());
        dadosCotacaoPage.selectTipoAssistencia(data.getTipoAssistencia());
        dadosCotacaoPage.inputValorGarantido(data.getValorGarantido());
        dadosCotacaoPage.selectObjetoDoSeguro(data.getObjetoSeguro());
        dadosCotacaoPage.selectPersonalisarCoberturas(data.getPersonalizacaoCobertura());

        for (tentativas = 0; tentativas < 4; tentativas++) {
            dadosCotacaoPage.inputNumero_Residence();
            dadosCotacaoPage.clickAvancar();
            if (!dadosCotacaoPage.isRiscoDuplicidadeByNumberDisplayed()) {
                break;
            }
        }

        //Valor do Seguro
        valorSeguroPage.isValorSeguroPageDisplayed();
        valorSeguroPage.clickOpcoesPagamentoButtonByPackage(data.getPacote());
        valorSeguroPage.selectFormaPagamento_E_NumParcelas(data.getFormaPagamento(), data.getQtdParcelas());
        valorSeguroPage.clickComprarByNumberPackage(data.getPacote());

        //Dados Complementares
        dadosComplementaresPage.isDadoComplementaresPageDisplayed();
        dadosComplementaresPage.inputMailAseg(data.getEmail());
        dadosComplementaresPage.inputDataNascimento(data.getDataNascimento());
        dadosComplementaresPage.inputSexo(data.getSexo());
        dadosComplementaresPage.inputCelularAseg(data.getCelular());
        dadosComplementaresPage.inputProfAseg(data.getProfissao());
        dadosComplementaresPage.inputRenda(data.getRendaMensal());
        dadosComplementaresPage.clickAvancarButton();

        //Pagamento
        pagamentoPage.isPagamentPageDisplayed();

        numOperacao = pagamentoPage.getNumeroOperacao();
        numCotacao = pagamentoPage.getNumeroCotacao();
        pagamentoPage.clickAvancar();

        try {
            pagamentoPage.clickEmitirButton();

            //Validações
            Asserts.assertTrueScreenshot(confirmacionEmisionPage.isConfirmacionEmissaoDisplayed(), true);
            confirmacionEmisionPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Emissão realizada com sucesso");

            numApolice = confirmacionEmisionPage.getApoliceTxt();

            System.out.println("OK");

            //Baixar PDFS
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_02, "Cotação", numCotacao);
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_02, "Apólice", numApolice);

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_02, data.getCenario(), numCotacao, numProposta, numApolice, "Passed");

        } catch (AssertionError e) {

            System.out.println("Não OK!");

            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_02, "Cotação", numCotacao);

            confirmacionEmisionPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Erro ao tentar emitir");

            errosRecebidosList = confirmacionEmisionPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_02, data.getCenario(), numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + confirmacionEmisionPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}