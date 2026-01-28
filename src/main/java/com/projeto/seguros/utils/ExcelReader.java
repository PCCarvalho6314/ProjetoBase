package com.projeto.seguros.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    private Workbook workbook;
    private Sheet sheet;

    /**
     * Carrega um arquivo Excel
     */
    public ExcelReader(String caminhoArquivo) {
        try {
            File file = new File(caminhoArquivo);
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao abrir arquivo Excel: " + caminhoArquivo, e);
        }
    }

    /**
     * Seleciona a aba pelo nome
     */
    public ExcelReader selecionarAba(String nomeAba) {
        sheet = workbook.getSheet(nomeAba);
        if (sheet == null) {
            throw new RuntimeException("Aba não encontrada: " + nomeAba);
        }
        return this;
    }

    /**
     * Seleciona a aba pelo índice (0-based)
     */
    public ExcelReader selecionarAbaPorIndice(int indice) {
        sheet = workbook.getSheetAt(indice);
        return this;
    }

    /**
     * Obtém o valor de uma célula
     */
    public String obterValor(int linha, int coluna) {
        Row row = sheet.getRow(linha);
        if (row == null) return "";

        Cell cell = row.getCell(coluna);
        if (cell == null) return "";

        return obterValorCelula(cell);
    }

    /**
     * Obtém o valor de uma célula por nome de coluna
     */
    public String obterValor(int linha, String nomeColuna) {
        int coluna = obterIndiceColuna(nomeColuna);
        return obterValor(linha, coluna);
    }

    /**
     * Obtém o índice de uma coluna pelo nome (primeira linha)
     */
    public int obterIndiceColuna(String nomeColuna) {
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell != null && obterValorCelula(cell).equalsIgnoreCase(nomeColuna)) {
                return i;
            }
        }
        throw new RuntimeException("Coluna não encontrada: " + nomeColuna);
    }

    /**
     * Lê todas as linhas como Map (usando primeira linha como cabeçalho)
     */
    public List<Map<String, String>> lerDados() {
        List<Map<String, String>> dados = new ArrayList<>();
        Row headerRow = sheet.getRow(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> linhaMap = new LinkedHashMap<>();
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                String chave = obterValorCelula(headerRow.getCell(j));
                String valor = obterValorCelula(row.getCell(j));
                linhaMap.put(chave, valor);
            }
            dados.add(linhaMap);
        }

        return dados;
    }

    /**
     * Obtém o número total de linhas
     */
    public int obterTotalLinhas() {
        return sheet.getLastRowNum() + 1;
    }

    /**
     * Obtém o número total de colunas
     */
    public int obterTotalColunas() {
        return sheet.getRow(0).getLastCellNum();
    }

    /**
     * Fechá o workbook
     */
    public void fechar() {
        try {
            workbook.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar workbook: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para obter valor da célula
     */
    private String obterValorCelula(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
