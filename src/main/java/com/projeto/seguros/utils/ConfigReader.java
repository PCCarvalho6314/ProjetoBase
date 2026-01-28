package com.projeto.seguros.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        carregarPropriedades();
    }

    /**
     * Carrega as propriedades do arquivo config.properties
     */
    private static void carregarPropriedades() {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Arquivo config.properties não encontrado");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler config.properties", e);
        }
    }

    /**
     * Obtém uma propriedade do arquivo de configuração
     */
    public static String obter(String chave) {
        String valor = properties.getProperty(chave);
        if (valor == null) {
            throw new RuntimeException("Propriedade não encontrada: " + chave);
        }
        return valor;
    }

    /**
     * Obtém uma propriedade com valor padrão
     */
    public static String obter(String chave, String padrao) {
        return properties.getProperty(chave, padrao);
    }

    /**
     * Obtém a URL base
     */
    public static String obterUrlBase() {
        return obter("base.url");
    }

    /**
     * Obtém o navegador configurado
     */
    public static String obterNavegador() {
        return obter("browser", "chrome");
    }

    /**
     * Obtém o timeout implícito
     */
    public static long obterTimeoutImplicito() {
        return Long.parseLong(obter("implicit.wait", "10"));
    }

    /**
     * Obtém o timeout explícito
     */
    public static long obterTimeoutExplicito() {
        return Long.parseLong(obter("explicit.wait", "15"));
    }

    /**
     * Verifica se screenshot está habilitado
     */
    public static boolean habilitarScreenshot() {
        return Boolean.parseBoolean(obter("screenshot.enabled", "true"));
    }
}
