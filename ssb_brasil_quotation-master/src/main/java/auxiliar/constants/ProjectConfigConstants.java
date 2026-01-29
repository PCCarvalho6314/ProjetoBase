package auxiliar.constants;

public interface ProjectConfigConstants {
    //Paths
    String csvFileName = "src/test/resources/report/csvReport.csv";
    String pathReportTemp = "src/test/resources/report/temp/";
    String screenshotPath = "src/test/resources/report/capturas/";
    String reportPath = "src/test/resources/report/";

    //Line firts of Report according request of product and scenario
    String txtStartLineCsvFileEmissaoApolices = "casoTeste;produto;cenario;chassis;numOperacao;numCotacao;numProposta;numApolice;erroRecebido";
    String txtStartLineCsvFileMensagemErros = "produto;cenario;chassis;numOperacao;erroEsperado;erroRecebido";
    String txtStartLineCsvFileEmissao_Residence = "produto;cenario;numCotacao;numProposta;numApolice;resultadoObtido";
    String txtStartLineCsvFileEmissao_Empresarial = "produto;cenario;numCotacao;numProposta;numApolice;resultadoObtido";
    String txtStartLineCsvFileEmissao_Vida = "produto;cenario;numCotacao;numProposta;numApolice;resultadoObtido";
//    String txtStartLineCsvFileMensagemErros_Residence = "produto;cenario;erroEsperado;erroRecebido";

    //outputs
    String xlsFile = "src/test/resources/report/Report.xlsx";
    String xlsxPath = "src/test/resources/arquivos_excel";
    String zipFile = "src/test/resources/report/Report.zip";
}