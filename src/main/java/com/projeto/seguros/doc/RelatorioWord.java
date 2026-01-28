package com.projeto.seguros.doc;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Gerador de relatórios em Word usando Apache POI
 */
public class RelatorioWord {

    private XWPFDocument document;
    private String nomeRelatorio;

    public RelatorioWord(String nomeRelatorio) {
        this.document = new XWPFDocument();
        this.nomeRelatorio = nomeRelatorio;
    }

    /**
     * Adiciona um título ao relatório
     */
    public RelatorioWord adicionarTitulo(String titulo) {
        XWPFParagraph p = document.createParagraph();
        p.setAlignment(org.apache.poi.xwpf.usermodel.ParagraphAlignment.CENTER);
        XWPFRun run = p.createRun();
        run.setText(titulo);
        run.setBold(true);
        run.setFontSize(14);
        return this;
    }

    /**
     * Adiciona um parágrafo
     */
    public RelatorioWord adicionarParagrafo(String texto) {
        XWPFParagraph p = document.createParagraph();
        XWPFRun run = p.createRun();
        run.setText(texto);
        return this;
    }

    /**
     * Adiciona um parágrafo com formatação
     */
    public RelatorioWord adicionarParagrafo(String texto, boolean negrito, int tamanho) {
        XWPFParagraph p = document.createParagraph();
        XWPFRun run = p.createRun();
        run.setText(texto);
        run.setBold(negrito);
        run.setFontSize(tamanho);
        return this;
    }

    /**
     * Adiciona uma quebra de linha
     */
    public RelatorioWord adicionarQuebra() {
        document.createParagraph();
        return this;
    }

    /**
     * Salva o relatório em arquivo
     */
    public void salvar(String caminhoDestino) {
        try (FileOutputStream fos = new FileOutputStream(caminhoDestino)) {
            document.write(fos);
            System.out.println("Relatório salvo: " + caminhoDestino);
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório: " + e.getMessage());
        }
    }

    /**
     * Salva com nome automatizado incluindo timestamp
     */
    public void salvarComTimestamp(String diretorio) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String nomeArquivo = diretorio + "/" + nomeRelatorio + "_" + timestamp + ".docx";
        salvar(nomeArquivo);
    }

    /**
     * Fecha o documento
     */
    public void fechar() {
        try {
            document.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar documento: " + e.getMessage());
        }
    }
}
