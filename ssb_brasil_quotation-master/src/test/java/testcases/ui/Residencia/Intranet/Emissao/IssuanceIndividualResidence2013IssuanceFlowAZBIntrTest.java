package testcases.ui.Residencia.Intranet.Emissao;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.CotacaoResidenceData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.intra.login.Portal;
import pages.intra.login.PortalsPage;
import pages.trenproduccion.*;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class IssuanceIndividualResidence2013IssuanceFlowAZBIntrTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("4", "Emissao_Intranet", "residencia");
    }

    private static final String TEST_CASE_NAME = "issuanceIndividualResidence2013IssuanceFlowAZBIntr";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPG = "BR_GER SIST CORPORATE, P&C, TRANSPORTES, AG";
    String numCotacao, numApolice, numProposta;
    List<String> errosRecebidosList;
    String formatedErrosRecebidosList;
    private static Integer tentativas;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.INTRANET)
    @Owner(SQUAD_BPG)
    public void issuanceIndividualResidence2013IssuanceFlowAZBIntr(CotacaoResidenceData data) {
        String pathCaptura = screenshotPath + "residencia/" + data.getCenario() + "/";
        numCotacao = "";
        numApolice = "";
        numProposta = "";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        SeleccionRamoPage seleccionRamoPage = new SeleccionRamoPage(getDriver());
        DatosBasicosPage datosBasicosPage = new DatosBasicosPage(getDriver());
        DatosRiesgoPage datosRiesgoPage = new DatosRiesgoPage(getDriver());
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
        seleccionRamoPage.inputRamoMediador(ConstantsAzb.RAMO_RESIDENCIA_DIGITAL, ConstantsAzb.MEDIADOR2);
        seleccionRamoPage.clickSelecionar();
        seleccionRamoPage.selectOperativa(ConstantsAzb.OPERATIVA);

        //Dados básicos
        Asserts.assertTrueScreenshot(datosBasicosPage.isDBasicosPageDisplayed(), true);
        datosBasicosPage.inputDocTomador(data.getCpfcnpj());
        datosBasicosPage.inputNomeTomador(data.getNomeTomador());
        datosBasicosPage.selectCategoriaRisco(data.getCategoriaRisco());
        datosBasicosPage.clickAvanzarButton();

        //Dados de risco
        Asserts.assertTrueScreenshot(datosRiesgoPage.isDadosDeRiscoDisplayed(), true);
        datosBasicosPage.selectTipoSeguro(data.getTipoSeguro());
        datosRiesgoPage.inputCepDigito(data.getCep().substring(0, 5), data.getCep().substring(5, 8));
        datosRiesgoPage.selectIsoPainel(data.getTelhadoIsoPainel());
        datosRiesgoPage.inputValorGarantido(data.getValorGarantido());
        datosRiesgoPage.selectTipoConstrucaoResidencia(data.getObjetoSeguro());
        datosRiesgoPage.selectTipoAssistenciaResidencia(data.getTipoAssistencia());
        tablaPreciosPage.selectPacotePersonalisado(data.getPersonalizacaoCobertura());

        for (tentativas = 0; tentativas < 4; tentativas++) {
            System.out.println("teste Intra - " + tentativas);
            datosRiesgoPage.inputNumeroRandomico();
            datosRiesgoPage.clickAvancarButton();
            if (!datosRiesgoPage.isRiscoDuplicidadeByNumberDisplayed()) {
                break;
            }
        }

        //Tabela de Preços
        Asserts.assertTrueScreenshot(tablaPreciosPage.isTablaPrecios(), true);
        tablaPreciosPage.isqtdPacotesIsOk(data.getQtdPacotes());
        tablaPreciosPage.selectPacote(data.getPacote());
        tablaPreciosPage.selectFormaPagamento(data.getFormaPagamento());
        tablaPreciosPage.inputDiaDePagamento(data.getDiaPagamento());
        tablaPreciosPage.clickCalcularButton();
        tablaPreciosPage.clickSalvarCotacao();
        tablaPreciosPage.inputNome("RPA Tester");
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
            emisionRealizadaPage.baixarPDFViaAPI(numCotacao, "Cotação", data.getCenario());

            if (emisionRealizadaPage.isPropostaNumberDisplayed()) {
                numProposta = emisionRealizadaPage.getNumProposta();
                emisionRealizadaPage.baixarPDFViaAPI(numProposta, "Proposta", data.getCenario());
            }
            if (emisionRealizadaPage.isApoliceNumberDisplayed()) {
                numApolice = emisionRealizadaPage.getNumApolice();
                emisionRealizadaPage.baixarPDFViaAPI(numApolice, "Apólice", data.getCenario());
            }

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_02, data.getCenario(), numCotacao, numProposta, numApolice, "Passed");

        } catch (
                AssertionError e) {
            emisionRealizadaPage.baixarPDFViaAPI(numCotacao, "Cotação", data.getCenario());

            errosRecebidosList = emisionRealizadaPage.getListMensagemErro();
            formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");

            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(data.getCenario(), ConstantsAzb.PRODUTO_02, numCotacao, "", "", formatedErrosRecebidosList);
            Allure.step("A apólice não foi gerada devido o erro" + emisionRealizadaPage.getListMensagemErro() + "ser apresentado");
            throw e;
        }
    }
}