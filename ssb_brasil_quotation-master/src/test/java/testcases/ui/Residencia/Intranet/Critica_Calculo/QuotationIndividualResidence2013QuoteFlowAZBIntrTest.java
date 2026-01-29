package testcases.ui.Residencia.Intranet.Critica_Calculo;

import auxiliar.constants.ConstantsAzb;
import bases.Base;
import data.CotacaoResidenceData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.intra.login.Portal;
import pages.intra.login.PortalsPage;
import pages.trenproduccion.DatosBasicosPage;
import pages.trenproduccion.DatosRiesgoPage;
import pages.trenproduccion.SeleccionRamoPage;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class QuotationIndividualResidence2013QuoteFlowAZBIntrTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("4", "Intranet_Critica_Calculo", "residencia");
    }

    private static final String TEST_CASE_NAME = "quotationIndividualResidence2013quoteFlowAZBIntr";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPG = "BR_GER SIST CORPORATE, P&C, TRANSPORTES, AG";

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.INTRANET)
    @Owner(SQUAD_BPG)
    public void quotationIndividualResidence2013quoteFlowAZBIntr(CotacaoResidenceData data
    ) {
        String pathCaptura = screenshotPath + "residencia/" + data.getCenario() + "/";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        SeleccionRamoPage seleccionRamoPage = new SeleccionRamoPage(getDriver());
        DatosBasicosPage datosBasicosPage = new DatosBasicosPage(getDriver());
        DatosRiesgoPage datosRiesgoPage = new DatosRiesgoPage(getDriver());

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
        datosRiesgoPage.inputNumero(data.getNumero());
        datosRiesgoPage.inputValorGarantido(data.getValorGarantido());
        datosRiesgoPage.selectIsoPainel(data.getTelhadoIsoPainel());
        datosRiesgoPage.selectTipoConstrucaoResidencia(data.getObjetoSeguro());
        datosRiesgoPage.selectTipoAssistenciaResidencia(data.getTipoAssistencia());
        datosRiesgoPage.clickAvancarButton();
        datosRiesgoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "erro-apresentado");

        // Gerar dados para relatório
        List<String> errosRecebidosList = datosRiesgoPage.getListMensagemErro();
        String formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");
        CsvReport.appendToFile_Residence(ConstantsAzb.PRODUTO_02, data.getCenario(), data.getMsgCriticaEsperada(), formatedErrosRecebidosList);

        //Validação de Resultados
        Asserts.assertTrueScreenshot(datosRiesgoPage.is_AlertCriticaCalculo_Displayed(data.getMsgCriticaEsperada()), true);
    }
}
