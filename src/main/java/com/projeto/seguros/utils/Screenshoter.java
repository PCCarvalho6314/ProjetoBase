package com.projeto.seguros.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.projeto.seguros.driver.BrowserDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshoter {

    private static final String SCREENSHOT_FOLDER = "target/screenshots";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    static {
        criarDiretorioSeNecessario();
    }

    /**
     * Cria o diretório de screenshots se não existir
     */
    private static void criarDiretorioSeNecessario() {
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_FOLDER));
        } catch (IOException e) {
            System.err.println("Erro ao criar diretório de screenshots: " + e.getMessage());
        }
    }

    /**
     * Captura a tela e salva com nome automatizado
     */
    public static String capturarTela(String nomeTeste) {
        if (!ConfigReader.habilitarScreenshot()) {
            return null;
        }

        WebDriver driver = BrowserDriverManager.obterDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String nomeArquivo = nomeTeste + "_" + LocalDateTime.now().format(formatter) + ".png";
        String caminhoCompleto = SCREENSHOT_FOLDER + File.separator + nomeArquivo;

        try {
            Files.copy(screenshot.toPath(), Paths.get(caminhoCompleto));
            System.out.println("Screenshot capturado: " + caminhoCompleto);
            return caminhoCompleto;
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Captura a tela com nome customizado
     */
    public static String capturarTela(String nomeTeste, String descricao) {
        if (!ConfigReader.habilitarScreenshot()) {
            return null;
        }

        WebDriver driver = BrowserDriverManager.obterDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String nomeArquivo = nomeTeste + "_" + descricao + "_" + LocalDateTime.now().format(formatter) + ".png";
        String caminhoCompleto = SCREENSHOT_FOLDER + File.separator + nomeArquivo;

        try {
            Files.copy(screenshot.toPath(), Paths.get(caminhoCompleto));
            return caminhoCompleto;
        } catch (IOException e) {
            System.err.println("Erro ao salvar screenshot: " + e.getMessage());
            return null;
        }
    }
}
