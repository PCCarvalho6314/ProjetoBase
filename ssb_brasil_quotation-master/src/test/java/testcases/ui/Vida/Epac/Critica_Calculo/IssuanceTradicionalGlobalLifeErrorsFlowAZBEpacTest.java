package testcases.ui.Vida.Epac.Critica_Calculo;

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
import pages.epac.Vida.ConfirmacaoEmissaoPage;
import pages.epac.Vida.PrecosCoberturaAssistenciasPage;
import pages.epac.login.PortalProductIssuancePage;
import pages.epac.login.PortalsPage;
import walle.frw.kernel.common.NoDriverUtils;
import walle.frw.kernel.common.enums.Portals;
import walle.frw.kernel.engine.annotations.Config;
import walle.frw.web.asserts.Asserts;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import static auxiliar.constants.ProjectConfigConstants.screenshotPath;

public class IssuanceTradicionalGlobalLifeErrorsFlowAZBEpacTest extends Base {

    @DataProvider(name = DATA_PROVIDER_NAME)
    public Iterator<Object[]> init() {
        return new ExcelDataProvider().readExcelData("5", "Critica_Cotacao_Regra_+30", "vida");
    }

    private static final String TEST_CASE_NAME = "IssuanceTradicionalGlobalLifeIssuanceFlowAZBEpacTest";
    private static final String DATA_PROVIDER_NAME = "dadosCotacao";
    private static final String SQUAD_BPA = "BPD - BR_GER SIST SAUDE, VIDA E AGRO";
    String numCotacao, numProposta, numApolice, numOperacao;

    @Test(groups = {TEST_CASE_NAME}, dataProvider = DATA_PROVIDER_NAME, threadPoolSize = 3)
    @Config(portal = Portals.EPACPLUS)
    @Owner(SQUAD_BPA)
    public void IssuanceTradicionalGlobalLifeErrorsFlowAZBEpac(VidaData data) {
        String pathCaptura = screenshotPath + "Vida Tradicional Global/" + data.getCenario() + "/";
        numCotacao = "";
        numProposta = "";
        numOperacao = "";
        numApolice = "";


        //Instanciar Objetos
        PortalsPage portalsPage = new PortalsPage(getDriver());
        DadosRiscoPage dadosRiscoPage = new DadosRiscoPage(getDriver());
        PrecosCoberturaAssistenciasPage precosCoberturaAssistenciasPage = new PrecosCoberturaAssistenciasPage(getDriver());
        ConfirmacaoEmissaoPage confirmacaoEmissaoPage = new ConfirmacaoEmissaoPage(getDriver());

        LocalDate dataAtual = LocalDate.now();
        ZonedDateTime dataHoraBrasil = dataAtual.atStartOfDay(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime dataFuturaBrasil = dataHoraBrasil.plusDays(31);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String dataFuturaFormatada = dataFuturaBrasil.format(formatador);

        //Acessar Aplicação
        portalsPage.accessIssuanceApplication(PortalProductIssuancePage.VIDA_2, data.getCorretor());

        //Dados de Risco
        Asserts.assertTrueScreenshot(dadosRiscoPage.isDadosRiscoDisplayed(), true);
        dadosRiscoPage.inputCpfCnpj(data.getCnpj_Cpf());
        dadosRiscoPage.selectTipoSeguro(data.getTipoSeguro());

        numOperacao = confirmacaoEmissaoPage.getNumeroOperacao();

        try {
            dadosRiscoPage.inputDataInicioVigencia(dataFuturaFormatada);
            Asserts.assertTrueScreenshot(dadosRiscoPage.isMsgRestricaoInicioVigenciaSuperior30Dias(), false);
            confirmacaoEmissaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura,
                    "Cotação não permitida conforme o esperado devido regra de início de vigência superior a 30 dias");

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_03, data.getCenario(), "", "", "", "Passed");

        } catch (AssertionError e) {
            dadosRiscoPage.inputNumeroVidasEmpregados(data.getNmrVidasEmpregados());
            dadosRiscoPage.inputNumeroVidasSocios(data.getNmrVidasSocios());
            dadosRiscoPage.InputCapitalIndividualEmpregados(data.getCapital_Individual_Empregados());
            dadosRiscoPage.InputCapitalIndividualSocios(data.getCapital_Individual_Socios());
            dadosRiscoPage.clickAvancar();
            NoDriverUtils.await(3);

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
            NoDriverUtils.await(5);
            numCotacao = precosCoberturaAssistenciasPage.getNumeroCotacao();

            confirmacaoEmissaoPage.fullScreenshot(utils.getCurrentUrl(), pathCaptura,
                    "Atenção! a cotação foi gerada de forma indevida, mesmo tendo o início de vigência superior a 30 dias da data atual!");

            // Gerar dados para relatório
            CsvReport.appendToFile_Residence_Vida_and_Empresarial_With_PDF(ConstantsAzb.PRODUTO_03, data.getCenario(), numCotacao, "", "", "A cotação foi gerada de forma indevida mesmo informando o início de vigência superior a 30 dias");

            Allure.step("A cotação " + numCotacao  + " foi gerada de forma indevida mesmo informando o início de vigência superior a 30 dias");
            throw e;
        }
    }
}