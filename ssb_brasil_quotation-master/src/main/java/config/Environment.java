package config;

/**
 * Enum para ambientes de teste.
 * Centraliza os ambientes disponíveis e seus arquivos de configuração.
 */
public enum Environment {
    LOCAL("local.properties"),
    DEV("dev.properties"),
    INT("int.properties"),
    PRE("pre.properties"),
    UAT("uat.properties");
    
    private final String propertiesFile;
    
    Environment(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }
    
    public String getPropertiesFile() {
        return propertiesFile;
    }
    
    /**
     * Obtém o ambiente atual baseado em variável de sistema ou padrão.
     * Ordem de prioridade:
     * 1. System property: -Denv=dev
     * 2. Environment variable: ENV=dev
     * 3. Padrão: DEV
     * 
     * @return Ambiente configurado
     */
    public static Environment getCurrentEnvironment() {
        String envName = System.getProperty("env", System.getenv("ENV"));
        
        if (envName != null) {
            try {
                return Environment.valueOf(envName.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.err.println("Ambiente inválido: " + envName + ". Usando DEV como padrão.");
            }
        }
        
        return DEV;
    }
}
