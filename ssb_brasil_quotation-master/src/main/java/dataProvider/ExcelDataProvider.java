package dataProvider;

import data.CotacaoAutoData;
import data.CotacaoResidenceData;
import data.EmpresarialData;
import data.VidaData;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {

    private static String caminhoPastaPlanilhaMassaDados = "src/test/resources/arquivos_excel/";

    public static Iterator<Object[]> choseSheetData(String numplanilha, String nomeAba, String tipoProduto) throws IOException {
        List<Object[]> listDados = new ArrayList<>();

        String caminhoArquivo;
        switch (numplanilha) {
            case "1":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "VeiculosRPA.xlsx";
                break;
            case "2":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "VeiculosRPA_OK.xlsx";
                break;
            case "3":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "CenariosRPA_TarifasAuto1211.xlsx";
                break;
            case "4":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "Solicitacao_RPA_Residencia_Integracao.xlsx";
                break;
            case "5":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "Solicitacao_RPA_Vida Global Tradicional_Integracao.xlsx";
                break;
            case "6":
                caminhoArquivo = caminhoPastaPlanilhaMassaDados + "Solicitacao_RPA_Empresarial_Integracao.xlsx";
                break;
            default:
                throw new IllegalArgumentException("Número de planilha de massas não localizado.");
        }

        FileInputStream arquivo = new FileInputStream(caminhoArquivo);
        Workbook planilha = WorkbookFactory.create(arquivo);
        Sheet abaPlanilha = planilha.getSheet(nomeAba);

        ArrayList<String> cabecalho = new ArrayList<>();

        DataFormatter df = new DataFormatter();

        for (Row linha : abaPlanilha) {
            int lastColumn = Math.max(linha.getLastCellNum(), abaPlanilha.getRow(0).getLastCellNum());

            //Atualizar conforme novas necessidades
            CotacaoAutoData dados_auto = null;
            CotacaoResidenceData dados_residencia = null;
            VidaData dados_vida = null;
            EmpresarialData dados_empresarial = null;

            //Preenche o cabeçalho da planilha de acordo com o tipo de cenário
            if (linha.getRowNum() == 0) {
                for (int colNum = 0; colNum < lastColumn; colNum++) {
                    Cell celula = linha.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cabecalho.add(df.formatCellValue(celula).replaceAll("\\n", "&"));
                }

            } else if (linha.getRowNum() > 0) {
                for (int colNum = 0; colNum < lastColumn; colNum++) {
                    Cell celula = linha.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String valorCelula = df.formatCellValue(celula).replaceAll("\\n", "&");

                    // Preenche o objeto correto conforme o tipo de produto
                    switch (tipoProduto) {
                        case "auto":
                            if (dados_auto == null) dados_auto = new CotacaoAutoData();
                            dados_auto.definirValor(cabecalho.get(colNum), valorCelula);
                            break;
                        case "residencia":
                            if (dados_residencia == null) dados_residencia = new CotacaoResidenceData();
                            dados_residencia.definirValor(cabecalho.get(colNum), valorCelula);
                            break;
                        case "vida":
                            if (dados_vida == null) dados_vida = new VidaData();
                            dados_vida.definirValor(cabecalho.get(colNum), valorCelula);
                            break;
                        case "empresa":
                            if (dados_empresarial == null) dados_empresarial = new EmpresarialData();
                            dados_empresarial.definirValor(cabecalho.get(colNum), valorCelula);
                            break;
                        default:
                            throw new IllegalArgumentException("O tipo de produto informado '" + tipoProduto + "' não está cadastrado. Informe um válido.");
                    }
                }

                // Adiciona o objeto correto à lista de dados com base no tipo de produto
                switch (tipoProduto) {
                    case "auto":
                        listDados.add(new Object[]{dados_auto});
                        break;
                    case "residencia":
                        listDados.add(new Object[]{dados_residencia});
                        break;
                    case "vida":
                        listDados.add(new Object[]{dados_vida});
                        break;
                    case "empresa":
                        listDados.add(new Object[]{dados_empresarial});
                        break;
                }
            }
        }

        planilha.close();
        arquivo.close();

        return listDados.iterator();
    }

    public Iterator<Object[]> readExcelData(String numplanilha, String nomeAbaPlanilha, String tipoProduto) {
        try {
            return choseSheetData(numplanilha, nomeAbaPlanilha, tipoProduto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}