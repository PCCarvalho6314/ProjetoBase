package testcases.ui.Auto.mensagemRestritiva;

import bases.Base;
import data.CotacaoAutoData;
import dataProvider.ExcelDataProvider;
import generateReport.CsvReport;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.epac.DadosCotacaoPage;
import pages.epac.login.PortalProductIssuancePage;
import pages.epac.login.PortalsPage;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.util.Iterator;
import java.util.List;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class QuotationIndividualMoto1212QuoteErrorsFlowAZBEpacTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("1", "motoErros", "auto");
    }

    private static final String TEST_CASE_NAME = "quotationIndividualMoto1212QuoteErrorsFlowAZBEpac";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPA = "BPA - BR_GER SIST AUTOMOVEL";

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS, name = TEST_CASE_NAME)
    @Owner(SQUAD_BPA)
    public void quotationIndividualMoto1212QuoteErrorsFlowAZBEpac(CotacaoAutoData data) {

        String pathCaptura = screenshotPath + "moto/" + data.getChassi() + "/";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosCotacaoPage dadosCotacaoPage = new DadosCotacaoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.MOTO, data.getCorretor());

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
        dadosCotacaoPage.clickUsoComercial(data.getUsoComercial());
        dadosCotacaoPage.clickMotoristaIndeterminado(data.getMotoristaIndeterminado());
        dadosCotacaoPage.clickVeiculo0Km(data.getVeiculo0Km());
        dadosCotacaoPage.inputAcessoriosMoto(data.getAcessorios(), data.getAcessoriosValor());
        dadosCotacaoPage.clickCondutor18a25(data.getCondutor18a25());
        dadosCotacaoPage.clickCondutorTrabaha(
                data.getCondutorTrabalha(), data.getUtilizaIrTrabalho(), data.getGaragemTrabalho());
        dadosCotacaoPage.clickCondutorEstuda(
                data.getCondutorEstuda(), data.getUtilizaIrEstudo(), data.getGaragemEscola());
        dadosCotacaoPage.selectResidencia(data.getTipoResidencia());
        dadosCotacaoPage.clickGaragemResidencia(data.getGaragemResidencia());
        dadosCotacaoPage.clickFranquia(data.getFranquia());
        dadosCotacaoPage.clickAssistencia(data.getAssistencia24h());
        dadosCotacaoPage.clickLanternasFarois(data.getVidros());
        String numOperacao = dadosCotacaoPage.getNumeroOperacao();

        dadosCotacaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "dados-cotacao");
        dadosCotacaoPage.clickAvancar();
        dadosCotacaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "erro-apresentado");

        // Gerar dados para relatório
        List<String> errosRecebidosList = dadosCotacaoPage.getListMensagemErro();
        String formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");
        CsvReport.appendToFile_Auto("Moto", "Mensagens de Erros", data.getChassi(),numOperacao,
                data.getErroEsperado(),formatedErrosRecebidosList);

        //Validação de Resultados
        Asserts.assertEqualsScreenshot(formatedErrosRecebidosList, data.getErroEsperado(),"Validando mensagem de erro obtida:", true);
    }
}
