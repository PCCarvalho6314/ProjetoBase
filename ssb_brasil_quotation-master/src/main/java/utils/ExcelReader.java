package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Leitor genérico de arquivos Excel (.xlsx) para massa de testes.
 * 
 * Padrão E → E → S:
 * ENTRADA: Arquivo Excel + Sheet Name
 * EXECUÇÃO: Parse via Apache POI + Reflection para popular POJOs
 * SAÍDA: Lista de objetos tipados (CotacaoAutoData, etc)
 * 
 * Benefícios vs implementação antiga:
 * - Genérico: funciona com qualquer POJO
 * - Sem switch/case: path configurável
 * - Reflection: mapeia colunas → setters automaticamente
 * - Type-safe: retorna lista tipada
 */
public class ExcelReader {
    
    private static final String RESOURCES_PATH = "src/test/resources/data/";
    
    // ========================================================================
    // LEITURA GENÉRICA
    // ========================================================================
    
    /**
     * Lê arquivo Excel e retorna lista de objetos do tipo especificado.
     * 
     * @param fileName Nome do arquivo Excel (ex: "cotacao_auto.xlsx")
     * @param sheetName Nome da aba a ser lida
     * @param clazz Classe do POJO de destino
     * @param <T> Tipo do objeto de retorno
     * @return Lista de objetos populados com dados do Excel
     * @throws IOException Se houver erro na leitura do arquivo
     * @throws ReflectiveOperationException Se houver erro no mapeamento
     */
    public static <T> List<T> readExcel(String fileName, String sheetName, Class<T> clazz) 
            throws IOException, ReflectiveOperationException {
        
        Path filePath = Paths.get(RESOURCES_PATH, fileName);
        
        try (FileInputStream fis = new FileInputStream(filePath.toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Aba não encontrada: " + sheetName);
            }
            
            return parseSheet(sheet, clazz);
        }
    }
    
    /**
     * Lê arquivo Excel com caminho absoluto.
     * 
     * @param fullPath Caminho completo do arquivo
     * @param sheetName Nome da aba
     * @param clazz Classe do POJO
     * @param <T> Tipo do objeto
     * @return Lista de objetos populados
     */
    public static <T> List<T> readExcelFullPath(String fullPath, String sheetName, Class<T> clazz) 
            throws IOException, ReflectiveOperationException {
        
        try (FileInputStream fis = new FileInputStream(fullPath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Aba não encontrada: " + sheetName);
            }
            
            return parseSheet(sheet, clazz);
        }
    }
    
    // ========================================================================
    // PARSING DO SHEET
    // ========================================================================
    
    /**
     * Faz o parse de uma aba Excel para lista de objetos.
     * 
     * Linha 0: Cabeçalhos (nomes das propriedades)
     * Linhas 1+: Dados
     * 
     * @param sheet Aba do Excel
     * @param clazz Classe do POJO
     * @param <T> Tipo do objeto
     * @return Lista de objetos populados
     */
    private static <T> List<T> parseSheet(Sheet sheet, Class<T> clazz) 
            throws ReflectiveOperationException {
        
        List<T> dataList = new ArrayList<>();
        
        Iterator<Row> rowIterator = sheet.iterator();
        if (!rowIterator.hasNext()) {
            return dataList; // Sheet vazia
        }
        
        // Linha 0: Cabeçalhos
        Row headerRow = rowIterator.next();
        List<String> headers = extractHeaders(headerRow);
        
        // Linhas 1+: Dados
        while (rowIterator.hasNext()) {
            Row dataRow = rowIterator.next();
            
            // Pula linhas vazias
            if (isRowEmpty(dataRow)) {
                continue;
            }
            
            T dataObject = mapRowToObject(dataRow, headers, clazz);
            dataList.add(dataObject);
        }
        
        return dataList;
    }
    
    /**
     * Extrai os cabeçalhos (nomes das colunas) da primeira linha.
     * 
     * @param headerRow Linha de cabeçalho
     * @return Lista de nomes das colunas
     */
    private static List<String> extractHeaders(Row headerRow) {
        List<String> headers = new ArrayList<>();
        
        for (Cell cell : headerRow) {
            String header = getCellValueAsString(cell).trim();
            headers.add(header);
        }
        
        return headers;
    }
    
    /**
     * Mapeia uma linha do Excel para um objeto usando reflection.
     * 
     * Estratégia:
     * 1. Cria instância do objeto via reflection
     * 2. Para cada coluna, busca método setter correspondente
     * 3. Invoca setter com valor da célula
     * 
     * @param dataRow Linha de dados
     * @param headers Lista de cabeçalhos
     * @param clazz Classe do objeto
     * @param <T> Tipo do objeto
     * @return Objeto populado
     */
    private static <T> T mapRowToObject(Row dataRow, List<String> headers, Class<T> clazz) 
            throws ReflectiveOperationException {
        
        // Se for Builder Pattern, usa builder()
        Object builder = null;
        Method buildMethod = null;
        
        try {
            Method builderMethod = clazz.getMethod("builder");
            builder = builderMethod.invoke(null);
            buildMethod = builder.getClass().getMethod("build");
        } catch (NoSuchMethodException e) {
            // Não tem builder, usa construtor padrão
        }
        
        T instance = (builder != null) ? null : clazz.getDeclaredConstructor().newInstance();
        
        // Mapeia cada coluna para o setter correspondente
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            
            if (cell == null) {
                continue;
            }
            
            String value = getCellValueAsString(cell);
            
            // Normaliza nome do header para nome do método
            String methodName = normalizeHeaderToMethodName(header);
            
            try {
                if (builder != null) {
                    // Builder Pattern
                    Method method = builder.getClass().getMethod(methodName, String.class);
                    method.invoke(builder, value);
                } else {
                    // Setter tradicional
                    String setterName = "set" + capitalize(methodName);
                    Method setter = clazz.getMethod(setterName, String.class);
                    setter.invoke(instance, value);
                }
            } catch (NoSuchMethodException e) {
                System.err.println("Método não encontrado: " + methodName + " para header: " + header);
            }
        }
        
        // Se usou builder, chama build()
        if (builder != null) {
            instance = (T) buildMethod.invoke(builder);
        }
        
        return instance;
    }
    
    // ========================================================================
    // UTILITÁRIOS
    // ========================================================================
    
    /**
     * Obtém valor da célula como String, independente do tipo.
     * 
     * @param cell Célula do Excel
     * @return Valor como String
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
                
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Remove .0 de números inteiros
                    double numValue = cell.getNumericCellValue();
                    if (numValue == (long) numValue) {
                        return String.valueOf((long) numValue);
                    } else {
                        return String.valueOf(numValue);
                    }
                }
                
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
                
            case FORMULA:
                return cell.getCellFormula();
                
            case BLANK:
                return "";
                
            default:
                return "";
        }
    }
    
    /**
     * Verifica se a linha está vazia.
     * 
     * @param row Linha do Excel
     * @return true se todas as células estiverem vazias
     */
    private static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                String value = getCellValueAsString(cell);
                if (!value.isEmpty()) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Normaliza nome do header para nome de método.
     * 
     * Exemplos:
     * "CPF/CNPJ" → "cpfcnpj"
     * "Tipo Seguro" → "tipoSeguro"
     * "0KM" → "veiculo0Km"
     * 
     * @param header Nome do cabeçalho
     * @return Nome do método em camelCase
     */
    private static String normalizeHeaderToMethodName(String header) {
        // Remove caracteres especiais e espaços
        String normalized = header.replaceAll("[^a-zA-Z0-9]", " ");
        
        // Converte para camelCase
        String[] words = normalized.trim().split("\\s+");
        StringBuilder result = new StringBuilder(words[0].toLowerCase());
        
        for (int i = 1; i < words.length; i++) {
            result.append(capitalize(words[i].toLowerCase()));
        }
        
        return result.toString();
    }
    
    /**
     * Capitaliza primeira letra da string.
     * 
     * @param str String a capitalizar
     * @return String com primeira letra maiúscula
     */
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    // ========================================================================
    // MÉTODOS DE CONVENIÊNCIA
    // ========================================================================
    
    /**
     * Retorna dados do Excel como Iterator para integração com TestNG.
     * 
     * @param fileName Nome do arquivo Excel
     * @param sheetName Nome da aba
     * @param clazz Classe do POJO
     * @param <T> Tipo do objeto
     * @return Iterator de Object[] para TestNG @DataProvider
     */
    public static <T> Iterator<Object[]> getDataProvider(String fileName, String sheetName, Class<T> clazz) {
        try {
            List<T> dataList = readExcel(fileName, sheetName, clazz);
            
            List<Object[]> dataProvider = new ArrayList<>();
            for (T data : dataList) {
                dataProvider.add(new Object[]{data});
            }
            
            return dataProvider.iterator();
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler Excel: " + fileName + " - " + sheetName, e);
        }
    }
}
