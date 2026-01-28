package com.projeto.seguros.providers;

import com.projeto.seguros.utils.ExcelReader;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Data Provider para leitura de dados do Excel
 * Integrado com JUnit 5 Parametrized Tests
 */
public class ExcelDataProvider implements ArgumentsProvider {

    private final String caminhoArquivo;
    private final String nomeAba;

    public ExcelDataProvider(String caminhoArquivo, String nomeAba) {
        this.caminhoArquivo = caminhoArquivo;
        this.nomeAba = nomeAba;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        ExcelReader excel = new ExcelReader(caminhoArquivo);
        excel.selecionarAba(nomeAba);

        List<Arguments> argumentos = new ArrayList<>();
        List<Map<String, String>> dados = excel.lerDados();

        for (Map<String, String> linha : dados) {
            argumentos.add(Arguments.of(linha));
        }

        excel.fechar();
        return argumentos.stream();
    }
}
