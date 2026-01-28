# Automação de Testes - Seguros

Projeto de automação de testes com **Selenium** e **Maven** utilizando a arquitetura de **Page Objects** com **JUnit 5**.

## Estrutura do Projeto

```
src
├── main/java/com/projeto/seguros
│   ├── driver/             # Gerenciamento do ThreadLocal WebDriver
│   ├── selectors/          # Mapeamento puro de elementos (By)
│   ├── pages/              # Page Objects (Ações e Verificações - sem By fixo)
│   ├── pojos/              # POJOs (Modelagem da Massa de Dados)
│   ├── utils/              # ExcelReader, Waits, Screenshoter
│   └── doc/                # Geração de Word (Apache POI)
├── test/java/com/projeto/seguros
│   ├── tests/              # Scripts de Teste (Orquestração)
│   ├── base/               # Setup e Teardown
│   └── providers/          # Leitura do Excel para o JUnit 5
└── test/resources
    ├── data/               # Planilhas (.xlsx)
    ├── junit-platform.properties # Config de Paralelismo
    └── config.properties   # URLs e Timeouts
```

## Requisitos

- **Java**: 11 ou superior
- **Maven**: 3.6.0 ou superior
- **WebDriver**: Chrome, Firefox, Edge ou Safari

## Dependências Principais

- **Selenium WebDriver** 4.15.0
- **JUnit 5** 5.10.0
- **WebDriverManager** 5.7.0
- **Apache POI** 5.2.3 (Excel/Word)
- **Allure Reports** 2.21.0

## Executar os Testes

### Executar todos os testes

```bash
mvn clean test
```

### Executar apenas uma classe de teste

```bash
mvn clean test -Dtest=NomeDoTesteTest
```

### Executar com paralelismo

Os testes estão configurados para rodar em paralelo por padrão. Ajuste em `junit-platform.properties` se necessário.

## Gerar Relatório Allure

```bash
mvn allure:serve
```

## Estrutura de Pacotes

### `driver/` - Gerenciamento de WebDriver

- ThreadLocal para gerenciar instâncias do WebDriver
- Inicialização e fechamento do navegador
- Sincronização entre threads

### `selectors/` - Mapeamento de Elementos

- Classes com constantes de seletores (By)
- Sem lógica de ação, apenas definições

### `pages/` - Page Objects

- Classes que representam páginas/componentes
- Métodos de ação e verificação
- Encapsulamento de seletores

### `pojos/` - Modelos de Dados

- Classes que representam entidades
- Mapeamento de dados do Excel
- Builders para facilitar testes com diferentes dados

### `utils/` - Utilitários

- `ExcelReader`: Leitura de planilhas
- `WaitUtils`: Esperas explícitas customizadas
- `Screenshoter`: Captura de telas
- `ConfigReader`: Leitura de propriedades

### `doc/` - Geração de Documentos

- Criação de relatórios em Word
- Integração com Apache POI

### `tests/` - Testes

- Classes de teste com JUnit 5
- Orquestração de testes

### `base/` - Base para Testes

- Classe abstrata com setup/teardown
- Hooks do JUnit 5

### `providers/` - Data Providers

- Leitura de dados do Excel
- Integração com parametrização do JUnit 5

## Exemplo de Uso

```java
@Test
public void testarLogin() {
    LoginPage loginPage = new LoginPage();
    loginPage.preencherEmail("user@example.com");
    loginPage.preencherSenha("senha123");
    loginPage.clicarBotaoLogin();
    
    assertTrue(loginPage.verificarLoginSucesso());
}
```

## Configuração de Ambiente

Edite `src/test/resources/config.properties` para:

- URL base da aplicação
- Tipo de navegador
- Timeouts
- Diretório de screenshots

## Contato

Para mais informações, entre em contato com o time de QA.
