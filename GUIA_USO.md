# Guia de Uso do Projeto Selenium + Maven

## 1. Configuração Inicial

### Pré-requisitos

- Java 11+
- Maven 3.6+
- IDE (VS Code, IntelliJ IDEA, Eclipse)

### Instalação de Dependências

```bash
mvn clean install
```

## 2. Estrutura e Convenções

### Driver (ThreadLocal)

```java
// Inicializar
WebDriverManager.inicializarDriver("chrome");

// Obter instância
WebDriver driver = WebDriverManager.obterDriver();

// Fechar
WebDriverManager.fecharDriver();
```

### Selectors

Armazenam apenas os seletores dos elementos:

```java
public class LoginPageSelectors {
    public static final By CAMPO_EMAIL = By.id("email");
    public static final By BOTAO_LOGIN = By.id("loginButton");
}
```

### Page Objects

Encapsulam ações e verificações:

```java
public class LoginPage {
    public LoginPage preencherEmail(String email) {
        // ação
        return this;
    }
    
    public void clicarLogin() {
        // ação
    }
}
```

### POJOs

Modelam dados de teste:

```java
Usuario usuario = new Usuario.Builder()
    .email("user@example.com")
    .senha("senha123")
    .build();
```

### Utilitários

#### ConfigReader

```java
String url = ConfigReader.obterUrlBase();
long timeout = ConfigReader.obterTimeoutExplicito();
String navegador = ConfigReader.obterNavegador();
```

#### WaitUtils

```java
WebElement elemento = WaitUtils.esperarElementoVisivel(By.id("id"));
WaitUtils.esperarTextoNoElemento(By.id("id"), "Texto");
WaitUtils.esperarUrlPagina("https://exemplo.com");
```

#### ExcelReader

```java
ExcelReader excel = new ExcelReader("src/test/resources/data/dados.xlsx");
excel.selecionarAba("Sheet1");

// Ler todos os dados
List<Map<String, String>> dados = excel.lerDados();

// Ler valor específico
String valor = excel.obterValor(1, "email");

excel.fechar();
```

#### Screenshoter

```java
// Screenshot automático
Screenshoter.capturarTela("nomeTeste");

// Com descrição
Screenshoter.capturarTela("nomeTeste", "passo1");
```

## 3. Escrevendo Testes

### Estrutura Básica

```java
@DisplayName("Descrição do teste")
public class MeuTesteTest extends BaseTest {

    @Test
    @DisplayName("Deve fazer algo")
    public void testarAlgo() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());
        
        // Act
        MeuPage page = new MeuPage();
        page.executarAcao();
        
        // Assert
        assertTrue(page.verificarAlgo());
    }
}
```

### Testes Parametrizados

```java
@ParameterizedTest
@CsvSource({
    "user1@example.com, senha123",
    "user2@example.com, senha456"
})
public void testarComParametros(String email, String senha) {
    // teste
}
```

### Testes com Dados do Excel

```java
@ParameterizedTest
@ArgumentsSource(ExcelDataProvider.class)
public void testarComExcel(Map<String, String> dados) {
    String email = dados.get("email");
    String senha = dados.get("senha");
}
```

## 4. Executando Testes

### Todos os testes

```bash
mvn clean test
```

### Classe específica

```bash
mvn clean test -Dtest=LoginTest
```

### Método específico

```bash
mvn clean test -Dtest=LoginTest#testarLogin
```

### Com paralelismo customizado

Editar `src/test/resources/junit-platform.properties`:

```properties
junit.jupiter.execution.parallel.fixed.parallelism=8
```

## 5. Relatórios

### Allure Report

```bash
# Executar testes gerando dados do Allure
mvn clean test

# Visualizar relatório
mvn allure:serve
```

## 6. Melhores Práticas

1. **Nomeação de Testes**: Use `@DisplayName` para descrições claras
2. **Page Objects**: Um por página/componente
3. **Selectors**: Separe seletores da lógica
4. **Waits**: Use `WaitUtils` em vez de `Thread.sleep()`
5. **Dados**: Use Excel/POJOs para dados de teste
6. **Screenshots**: Capture em pontos críticos e em erros
7. **Logging**: Use SLF4J para rastreamento

## 7. Troubleshooting

### WebDriver não inicializado

```
// Certifique-se que setUp() foi chamado
@BeforeEach
public void setUp() {
    WebDriverManager.inicializarDriver(navegador);
}
```

### Elemento não encontrado

```
// Use WaitUtils ao invés de esperas implícitas
WebElement elemento = WaitUtils.esperarElementoVisivel(By.id("id"));
```

### Timeout em operações

```
// Aumente o timeout em config.properties
explicit.wait=20
```

## 8. Exemplo Completo

```java
@DisplayName("Fluxo de Login")
public class LoginFlowTest extends BaseTest {

    @Test
    @DisplayName("Usuário consegue fazer login com credenciais válidas")
    public void testarFluxoLoginCompleto() {
        // Arrange
        WebDriver driver = obterDriver();
        driver.navigate().to(ConfigReader.obterUrlBase());
        
        Usuario usuario = new Usuario.Builder()
                .email("user@example.com")
                .senha("senha123")
                .build();

        // Act
        LoginPage loginPage = new LoginPage();
        loginPage.preencherEmail(usuario.getEmail());
        loginPage.preencherSenha(usuario.getSenha());
        loginPage.clicarBotaoLogin();

        // Assert
        HomePage homePage = new HomePage();
        assertTrue(homePage.estaLogado());
        
        Screenshoter.capturarTela("testarFluxoLoginCompleto", "sucesso");
    }
}
```

## 9. Próximos Passos

- [ ] Implementar página de buscas
- [ ] Criar testes de carrinho de compras
- [ ] Integrar com CI/CD (Jenkins, GitHub Actions)
- [ ] Adicionar testes de performance
- [ ] Implementar testes de acessibilidade

---

Para dúvidas, consulte o README.md ou entre em contato com o time de QA.
