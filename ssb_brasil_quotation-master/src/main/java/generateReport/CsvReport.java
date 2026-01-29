package generateReport;

import auxiliar.constants.ProjectConfigConstants;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReport {
    static String csvFileName = ProjectConfigConstants.csvFileName;
    static String pathReportTemp = ProjectConfigConstants.pathReportTemp;
    static String linhaInicial;
    static String product;


    public static String createFile() {

        int min = 100000;
        int max = 999999;
        String random = String.valueOf(Math.floor(Math.random() * (max - min + 1) + min)).replaceAll(".0", "");
        String filename = pathReportTemp + random + ".txt";

        FileUtil.createFolder(pathReportTemp);

        try {
            File txtFile = new File(filename);
            if (txtFile.createNewFile()) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }

    public static void appendToFileEmissaoApolice(String casoTeste,
                                                  String produto,
                                                  String cenario,
                                                  String chassis,
                                                  String numOperacao, String numCotacao, String numProposta, String numApolice, String erroRecebido) {

        String fileName = createFile();

        File csvFile = new File(fileName);
        String line = casoTeste + ';' + produto + ';' + cenario + ";" +
                chassis + ";" + numOperacao + ";" + numCotacao + ";" + numProposta + ";" + numApolice + ";" + erroRecebido;

        try {
            PrintWriter out = new PrintWriter(csvFile);
            try {
                out.println(line);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        product = produto;
    }


    public static void appendToFile_Auto(String produto,
                                         String cenario,
                                         String chassis,
                                         String numOperacao,
                                         String erroEsperado,
                                         String erroRecebido) {

        String fileName = createFile();

        File csvFile = new File(fileName);
        String line = produto + ';' + cenario + ";" +
                chassis + ";" + numOperacao + ";" + erroEsperado + ";" + erroRecebido;

        try {
            PrintWriter out = new PrintWriter(csvFile);
            try {
                out.println(line);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        product = produto;
    }

    public static void appendToFile_Residence(String produto,
                                              String cenario,
                                              String erroEsperado,
                                              String erroRecebido) {

        String fileName = createFile();

        File csvFile = new File(fileName);
        String line = produto + ';' + cenario + ";" + erroEsperado + ";" + erroRecebido;

        try {
            PrintWriter out = new PrintWriter(csvFile);
            try {
                out.println(line);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        product = produto;
    }

    public static void appendToFile_Residence_Vida_and_Empresarial_With_PDF(String produto,
                                                                            String cenario,
                                                                            String numCotacao,
                                                                            String numProposta,
                                                                            String numApolice,
                                                                            String resultadoObtido) {

        String fileName = createFile();

        File csvFile = new File(fileName);
        String line = produto + ';' + cenario + ";" + numCotacao + ";" + numProposta + ";" + numApolice + ";" + resultadoObtido;

        try {
            PrintWriter out = new PrintWriter(csvFile);
            try {
                out.println(line);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        product = produto;
    }

    public static void createExcelReport() {
        joinFiles(csvFileName, product);
        XlsxTools.convertCsvToXlsx(csvFileName);
    }

    public static void joinFiles(String arquivoDestino, String product) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino, false))) {
            File diretorio = new File(pathReportTemp);
            File[] arquivos = diretorio.listFiles();

            //Escolhendo quais linhas que serão criadas, de acordo como o cenário.
            switch (product) {
                case "Auto": {
                    linhaInicial = ProjectConfigConstants.txtStartLineCsvFileEmissaoApolices;
                }
                break;
                case "Residencia": {
                    linhaInicial = ProjectConfigConstants.txtStartLineCsvFileEmissao_Residence;
                }
                break;
                case "Empresarial": {
                    linhaInicial = ProjectConfigConstants.txtStartLineCsvFileEmissao_Empresarial;
                }
                break;
                case "Vida": {
                    linhaInicial = ProjectConfigConstants.txtStartLineCsvFileEmissao_Vida;
                }
                break;
                case "null": {
                    linhaInicial = ProjectConfigConstants.txtStartLineCsvFileMensagemErros;
                }
                break;
                default:
                    throw new IllegalArgumentException("O nome do Produto dos teste executados não foi informado corretamente na planilha de dados");
            }

            byte[] bytes = linhaInicial.getBytes(StandardCharsets.UTF_8);
            String cabecalho = new String(bytes, StandardCharsets.UTF_8);

            writer.write(cabecalho);
            writer.newLine();

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                            String linha;
                            while ((linha = reader.readLine()) != null) {
                                byte[] linhaBytes = linha.getBytes(StandardCharsets.UTF_8);
                                String linhaConvertida = new String(linhaBytes, StandardCharsets.UTF_8);

                                writer.write(linhaConvertida);
                                writer.newLine();
                            }
                        }
                        arquivo.delete();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortReport(arquivoDestino);
    }

    public static void sortReport(String file) {
        File csvFile = new File(file);

        try {
            List<String> lines = Files.readAllLines(Paths.get(String.valueOf(csvFile)), Charset.defaultCharset());

            if (!lines.isEmpty()) {

                List<String> sortedLines = lines.stream()
                        .skip(1)
                        .sorted()
                        .collect(Collectors.toList());

                csvFile.delete();
                Files.write(Paths.get(String.valueOf(csvFile)), Collections.singleton(lines.get(0)), StandardCharsets.UTF_8);
                Files.write(Paths.get(csvFile.toString()), sortedLines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
