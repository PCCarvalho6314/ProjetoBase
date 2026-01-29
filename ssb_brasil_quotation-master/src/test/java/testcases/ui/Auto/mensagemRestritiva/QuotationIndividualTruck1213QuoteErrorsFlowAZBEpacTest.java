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

public class QuotationIndividualTruck1213QuoteErrorsFlowAZBEpacTest extends Base {


    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("1", "caminhaoErros", "auto");
    }

    private static final String TEST_CASE_NAME = "quotationIndividualTruck1213QuoteErrorsFlowAZBEpac";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPA = "BPA - BR_GER SIST AUTOMOVEL";

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS, name = TEST_CASE_NAME)
    @Owner(SQUAD_BPA)
    public void quotationIndividualTruck1213QuoteErrorsFlowAZBEpac(CotacaoAutoData data) {

        String pathCaptura = screenshotPath + "caminhao/" + data.getChassi() + "/";

        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosCotacaoPage dadosCotacaoPage = new DadosCotacaoPage(getDriver());

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.TRUCK, data.getCorretor());

        //QUOTATION PAGE
        Asserts.assertTrueScreenshot(dadosCotacaoPage.isContactDisplayed(), true);
        dadosCotacaoPage.selectTipoSeguro(data.getTipoSeguro());
        dadosCotacaoPage.selectClasseBonus(data.getClasseBonus());
        dadosCotacaoPage.inputNumeroCI(data.getNumeroCI());
        dadosCotacaoPage.inputCpfCnpj(data.getCpfcnpj());
        dadosCotacaoPage.inputCep(data.getCep());
        dadosCotacaoPage.clickMotoristaIndeterminado(data.getMotoristaIndeterminado());
        dadosCotacaoPage.inputCpfCondutor(data.getMotoristaIndeterminado(), data.getCpfcnpj(), data.getCpfCondutor());
        dadosCotacaoPage.selectEstadoCivil(data.getEstadoCivil());
        dadosCotacaoPage.inputChassi(data.getChassi());
        dadosCotacaoPage.clickUsoComercial(data.getUsoComercial(), data.getTransportadora());
        dadosCotacaoPage.clickVeiculo0Km(data.getVeiculo0Km());
        dadosCotacaoPage.inputAcessoriosCaminhao(data.getAcessorios(), data.getAcessoriosValor());
        dadosCotacaoPage.selectMotoristaPrincipal(data.getMotoristaPrincipal());
        dadosCotacaoPage.selectSeguroCarga(data.getSeguroCarga());
        dadosCotacaoPage.clickVeiculoAlienado(data.getAlienacao());
        dadosCotacaoPage.selectRegiaoCirculacao(data.getRegiaoCirculacao());
        dadosCotacaoPage.clickGaragemServico(data.getGaragemServico());
        dadosCotacaoPage.clickDirigeApos22h(data.getDirige22h());
        dadosCotacaoPage.selectGerenciamentoRisco(data.getPossuiGerenciamentoRisco(), data.getTipoGerenciamentoRisco());
        dadosCotacaoPage.inputCargaFrequente(data.getCargaFrequente());
        dadosCotacaoPage.clickFranquia(data.getFranquia());
        dadosCotacaoPage.clickAssistencia(data.getAssistencia24h());
        dadosCotacaoPage.clickVidros(data.getVidros());
        String numOperacao = dadosCotacaoPage.getNumeroOperacao();
        dadosCotacaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "dados-cotacao");
        dadosCotacaoPage.clickAvancar();
        dadosCotacaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura, "erro-apresentado");

        // Gerar dados para relatório
        List<String> errosRecebidosList = dadosCotacaoPage.getListMensagemErro();
        String formatedErrosRecebidosList = String.valueOf(errosRecebidosList).replace("[", "").replace("]", "");
        CsvReport.appendToFile_Auto("Caminhao", "Mensagens de Erros", data.getChassi(), numOperacao,
                data.getErroEsperado(), formatedErrosRecebidosList);

        //Validação de Resultados
        Asserts.assertEqualsScreenshot(formatedErrosRecebidosList, data.getErroEsperado(), "Validando mensagem de erro obtida:", true);
    }
}
