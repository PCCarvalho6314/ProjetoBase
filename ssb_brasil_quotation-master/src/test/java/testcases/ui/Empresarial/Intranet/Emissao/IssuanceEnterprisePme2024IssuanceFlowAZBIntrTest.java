package testcases.ui.Empresarial.Intranet.Emissao;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.EmpresarialData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.intra.login.Portal;
import pages.intra.login.PortalsPage;
import pages.trenproduccion.*;
import utils.api.ApisPrint_Pdf;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class IssuanceEnterprisePme2024IssuanceFlowAZBIntrTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("6", "Emissao_Intranet", "empresa");
    }

    private static final String TEST_CASE_NAME = "IssuanceEnterprisePme2024IssuanceFlowAZBIntr";
    private static final String DATA_PROVIDER_NAME = "dadosEmpresarialPME";
    private static final String SQUAD_BPG = "BR_GER SIST CORPORATE, P&C, TRANSPORTES, AG";
    String numCotacao, numApolice, numProposta;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;
    private static Integer tentativas;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.INTRANET)
    @Owner(SQUAD_BPG)
    public void IssuanceEnterprisePme2024IssuanceFlowAZBIntrTest(EmpresarialData data) {
        String pathCaptura = screenshotPath + "Empresarial/" + data.getCenario() + "/";
        numCotacao = "";
        numApolice = "";
        numProposta = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        SeleccionRamoPage seleccionRamoPage = new SeleccionRamoPage(getDriver());
        DatosBasicosPage datosBasicosPage = new DatosBasicosPage(getDriver());
        DatosRiesgoPage datosRiesgoPage = new DatosRiesgoPage(getDriver());
        CoberturasPage coberturasPage = new CoberturasPage(getDriver());
        TablaPreciosPage tablaPreciosPage = new TablaPreciosPage(getDriver());
        InDatosEmisionPage datosEmisionPage = new InDatosEmisionPage(getDriver());
        DatosClientePage datosClientePage = new DatosClientePage(getDriver());
        DatosComunesPage datosComunesPage = new DatosComunesPage(getDriver());
        ClausulasPage clausulasPage = new ClausulasPage(getDriver());
        EmisionRealizadaPage emisionRealizadaPage = new EmisionRealizadaPage(getDriver());

        //Access application
        portalsPage.accessApplication(Portal.PRODUCAO);

        //Menu Produção
        Asserts.assertTrueScreenshot(seleccionRamoPage.isMenuProducaoDisplayed(), true);
        seleccionRamoPage.inputRamoMediador(ConstantsAzb.RAMO_EMPRESARIAL_PME, ConstantsAzb.MEDIADOR2);
        seleccionRamoPage.clickSelecionar();
        seleccionRamoPage.selectOperativa(ConstantsAzb.OPERATIVA);

        //Dados básicos
        Asserts.assertTrueScreenshot(datosBasicosPage.isDBasicosPageDisplayed(), true);
        datosBasicosPage.inputDocTomador(data.getCpfcnpj());
        datosBasicosPage.inputNomeTomador(data.getNomeTomador());
        datosBasicosPage.clickAvanzarButton();

        //Dados de risco
        Asserts.assertTrueScreenshot(datosRiesgoPage.isDadosDeRiscoDisplayed(), true);
        datosBasicosPage.selectTipoSeguro(data.getTipoSeguro());
        datosBasicosPage.selectCategoriaRisco(data.getCategoriaRisco());
        datosRiesgoPage.inputCepDigito(data.getCep().substring(0, 5), data.getCep().substring(5, 8));
        datosRiesgoPage.selectObjetoSeguro(data.getObjetoSeguro());
        datosRiesgoPage.inputVlrDanosMateriais(data.getValorDanosMateriais());
        datosRiesgoPage.selectIndenizacaoValoNovo(data.getIndenizacaoValorNovo());
        datosRiesgoPage.selectIsoPainel_Empresarial(data.getTelhadoIsoPainel());
        datosRiesgoPage.selectTipoConstrucaoEmpresarial(data.getTipoConstrucao());
        datosRiesgoPage.selectProtecoesRisco(data.getProtecaoRisco());

        for (tentativas = 0; tentativas < 4; tentativas++) {
            System.out.println("teste Intra - " + tentativas);
            datosRiesgoPage.inputNumeroRandomico_Empresarial();
            datosRiesgoPage.clickAvancarButton();
            if (!datosRiesgoPage.isRiscoDuplicidadeByNumberDisplayed()) {
                break;
            }
        }

        //Coberturas
        Asserts.assertTrueScreenshot(coberturasPage.isCoberturas(),true);
        coberturasPage.clickCoberturaDanosEletricos();  //adicionar data.get
        coberturasPage.clickCalcularButton();
        coberturasPage.clickAvanzarButton();

        // CLÁUSULAS
        Asserts.assertTrueScreenshot(clausulasPage.isClausulasOperacionales(),true);
        clausulasPage.clickAvanzarButton();

        //Tabela de Preços
        Asserts.assertTrueScreenshot(tablaPreciosPage.isTablaPrecios(), true);
        tablaPreciosPage.selectFormaPagamento(data.getFormaPagamento());
        tablaPreciosPage.inputDiaDePagamento(data.getDiaPagamento());
        tablaPreciosPage.clickCalcularButton();
        tablaPreciosPage.clickSalvarCotacao();
        tablaPreciosPage.inputNome("Nome Test - RPA");
        tablaPreciosPage.clickSalvar();

        //Valida Número da Cotação
        Asserts.assertTrueScreenshot(tablaPreciosPage.isNumeroCotacaoDisplayed(), true);
        tablaPreciosPage.exibeNumeroCotacao();
        numCotacao = String.valueOf(tablaPreciosPage.getNumeroCotacao());
        tablaPreciosPage.clickEmitirButton();

        //D.Emissao
        Asserts.assertTrueScreenshot(datosEmisionPage.isDadosEmissaoDisplayed(), true);
        datosEmisionPage.selectDestinatario(data.getDestinatario());
        datosEmisionPage.clickAceptarButton();

        //Datos Cliente
        Asserts.assertTrueScreenshot(datosClientePage.isDatosPersonalesTomadorDisplayed(), true);
        datosClientePage.inputTelefono2(data.getTelefone());
        datosClientePage.inputCelularTomador(data.getCelular());
        datosClientePage.inputMail(data.getEmail());
        datosClientePage.inputNombreFantasia(data.getNomeFantasia());
        datosClientePage.inputActividadPrincipal(data.getAtividadePrincipal());

        datosClientePage.selectNatureza(data.getNatureza());
        datosClientePage.selectOcupacao(data.getOcupacao());

        datosClientePage.selectFacturamento(data.getFaturamento());
        datosClientePage.selectPatrimonioTomador(data.getPatrimonio());


        datosClientePage.clickAvanzarButton();

        //Dados Comuns
        datosComunesPage.isDatosComunes();
        datosClientePage.clickAvanzarButton();

        //Cláusulas Operacionais
        Asserts.assertTrueScreenshot(clausulasPage.isClausulasOperacionales(), true);
        datosBasicosPage.clickAvanzarButton();

        //Emissão
        Asserts.assertTrueScreenshot(emisionRealizadaPage.isApoliceSeraRealizada(), true);
        datosBasicosPage.clickAvanzarButton();

        //Validações
        try {
            Asserts.assertTrueScreenshot(emisionRealizadaPage.isApolicePendenteOuEmitida(), true);
            emisionRealizadaPage.exibeNumeroPropostaAuto();
            emisionRealizadaPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "Emissão realizada com sucesso");

            //Baixar PDFS
            ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Cotação", numCotacao);

            if (emisionRealizadaPage.isPropostaNumberDisplayed()) {
                numProposta = emisionRealizadaPage.getNumProposta();
                ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Proposta", numProposta);

            }
            if (emisionRealizadaPage.isApoliceNumberDisplayed()) {
                numApolice = emisionRealizadaPage.getNumApolice();
                ApisPrint_Pdf.baixarPDFViaAPI(ConstantsAzb.PRODUTO_04, "Apólice", numApolice);
            }

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_04, data.getCenario(), numCotacao, numProposta, numApolice, "Passed");

        } catch (
                AssertionError e) {
            emisionRealizadaPage.baixarPDFViaAPI(numCotacao, "Cotação", data.getCenario());

            errosRecebidosList = emisionRealizadaPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(data.getCenario(), ConstantsAzb.PRODUTO_04, numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + emisionRealizadaPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}