package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Gerenciador de configurações centralizado.
 * Carrega propriedades do ambiente selecionado e fornece acesso type-safe.
 * 
 * Padrão E → E → S:
 * ENTRADA: Arquivo .properties do ambiente
 * EXECUÇÃO: Carregamento via Properties
 * SAÍDA: Acesso centralizado às configurações
 */
public class Config {
    
    private static final String CONFIG_PATH = "src/main/resources/environments/";
    private static Properties properties;
    private static Environment currentEnvironment;
    
    static {
        loadConfiguration();
    }
    
    // ========================================================================
    // CARREGAMENTO DE CONFIGURAÇÃO
    // ========================================================================
    
    /**
     * Carrega as configurações do ambiente atual.
     */
    private static void loadConfiguration() {
        currentEnvironment = Environment.getCurrentEnvironment();
        properties = new Properties();
        
        String configFile = Paths.get(CONFIG_PATH, currentEnvironment.getPropertiesFile()).toString();
        
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
            System.out.println("✓ Configuração carregada: " + currentEnvironment);
        } catch (IOException e) {
            System.err.println("✗ Erro ao carregar configuração: " + configFile);
            throw new RuntimeException("Falha ao carregar configuração", e);
        }
    }
    
    /**
     * Recarrega as configurações (útil para testes).
     */
    public static void reload() {
        loadConfiguration();
    }
    
    /**
     * Força o uso de um ambiente específico.
     * @param environment Ambiente a ser usado
     */
    public static void setEnvironment(Environment environment) {
        System.setProperty("env", environment.name().toLowerCase());
        loadConfiguration();
    }
    
    // ========================================================================
    // ACESSO ÀS PROPRIEDADES
    // ========================================================================
    
    /**
     * Obtém propriedade como String.
     * @param key Chave da propriedade
     * @return Valor da propriedade ou null se não existir
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Obtém propriedade com valor padrão.
     * @param key Chave da propriedade
     * @param defaultValue Valor padrão se a chave não existir
     * @return Valor da propriedade ou valor padrão
     */
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Obtém propriedade como Integer.
     * @param key Chave da propriedade
     * @return Valor convertido para Integer
     */
    public static Integer getInt(String key) {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : null;
    }
    
    /**
     * Obtém propriedade como Integer com valor padrão.
     * @param key Chave da propriedade
     * @param defaultValue Valor padrão
     * @return Valor convertido para Integer ou valor padrão
     */
    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }
    
    /**
     * Obtém propriedade como Boolean.
     * @param key Chave da propriedade
     * @return Valor convertido para Boolean
     */
    public static Boolean getBoolean(String key) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value) : null;
    }
    
    /**
     * Obtém propriedade como Boolean com valor padrão.
     * @param key Chave da propriedade
     * @param defaultValue Valor padrão
     * @return Valor convertido para Boolean ou valor padrão
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
    
    // ========================================================================
    // PROPRIEDADES ESPECÍFICAS - Type-safe access
    // ========================================================================
    
    /**
     * Obtém a URL base da aplicação.
     * @return URL base
     */
    public static String getBaseUrl() {
        return get("base.url");
    }
    
    /**
     * Obtém o usuário de login.
     * @return Usuário
     */
    public static String getUsername() {
        return get("login.username");
    }
    
    /**
     * Obtém a senha de login.
     * @return Senha
     */
    public static String getPassword() {
        return get("login.password");
    }
    
    /**
     * Obtém o timeout padrão para esperas (em segundos).
     * @return Timeout em segundos
     */
    public static int getDefaultTimeout() {
        return getInt("timeout.default", 30);
    }
    
    /**
     * Obtém o timeout para esperas longas (em segundos).
     * @return Timeout longo em segundos
     */
    public static int getLongTimeout() {
        return getInt("timeout.long", 60);
    }
    
    /**
     * Obtém o timeout para esperas curtas (em segundos).
     * @return Timeout curto em segundos
     */
    public static int getShortTimeout() {
        return getInt("timeout.short", 10);
    }
    
    /**
     * Verifica se screenshots devem ser tirados em falhas.
     * @return true se screenshots estão habilitados
     */
    public static boolean isScreenshotOnFailureEnabled() {
        return getBoolean("screenshot.onFailure", true);
    }
    
    /**
     * Verifica se modo headless está habilitado.
     * @return true se modo headless está ativo
     */
    public static boolean isHeadlessMode() {
        return getBoolean("browser.headless", false);
    }
    
    /**
     * Obtém o browser padrão configurado.
     * @return Nome do browser (chrome, firefox, edge)
     */
    public static String getBrowser() {
        return get("browser.type", "chrome");
    }
    
    /**
     * Obtém o ambiente atual.
     * @return Ambiente configurado
     */
    public static Environment getCurrentEnvironment() {
        return currentEnvironment;
    }
    
    /**
     * Verifica se uma propriedade existe.
     * @param key Chave da propriedade
     * @return true se a propriedade existe
     */
    public static boolean hasProperty(String key) {
        return properties.containsKey(key);
    }
    
    /**
     * Lista todas as propriedades carregadas (útil para debug).
     */
    public static void printAll() {
        System.out.println("========================================");
        System.out.println("Configurações - Ambiente: " + currentEnvironment);
        System.out.println("========================================");
        properties.forEach((key, value) -> 
            System.out.println(key + " = " + value));
        System.out.println("========================================");
    }
}
