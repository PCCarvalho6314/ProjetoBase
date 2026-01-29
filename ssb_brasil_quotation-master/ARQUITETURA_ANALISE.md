# ANÁLISE ARQUITETURAL - SSB BRASIL QUOTATION

## Modelo: Entrada → Execução → Saída

---

## 1. VISÃO GERAL DO PROJETO

**Propósito**: Sistema de automação de cotações de seguros em massa (Auto, Moto, Caminhão, Residência, Vida, Empresarial)

**Stack**: Java 17 | Maven | Selenium | TestNG | Allure Reports | POI (Excel)

**Padrão Principal**: Page Object Model (POM) com camadas de responsabilidade clara

---

## 2. FLUXO GERAL: ENTRADA → EXECUÇÃO → SAÍDA

``
┌─────────────────────────────────────────────────────────────────┐
│                    ENTRADA (INPUT LAYER)                        │
├─────────────────────────────────────────────────────────────────┤
│  • Arquivo Excel (.xlsx) com dados de cotação                   │
│  • Configurações de ambiente (dev, int, pre, uat)               │
│  • Seleção de produto e tipo de teste                           │
└──────────────────┬──────────────────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────────────────┐
│                  EXECUÇÃO (EXECUTION LAYER)                     │
├─────────────────────────────────────────────────────────────────┤
│  1. Parse e Validação de Dados                                  │
│  2. Inicialização do Driver Selenium                            │
│  3. Navegação e Preenchimento de Formulários                    │
│  4. Validação de Resultados e Erros                             │
│  5. Captura de Evidências (Screenshots)                         │
│  6. Consulta a Banco de Dados (Mainframe)                       │
└──────────────────┬──────────────────────────────────────────────┘
                   │
                   ▼
┌─────────────────────────────────────────────────────────────────┐
│                    SAÍDA (OUTPUT LAYER)                         │
├─────────────────────────────────────────────────────────────────┤
│  • Relatório Excel (.xlsx) com resultados                       │
│  • Arquivo ZIP com evidências consolidadas                      │
│  • Relatório Allure (integração CI/CD)                          │
│  • Logs estruturados por produto/chassi                         │
└─────────────────────────────────────────────────────────────────┘
``

---

## 3. CAMADAS DE RESPONSABILIDADE

### 3.1 ENTRADA - Data Input Layer

#### 3.1.1 `ExcelDataProvider` (Porta de Entrada Primária)

**Responsabilidade**: Ler e transformar dados Excel em objetos de domínio

``
INPUT:  Arquivo .xlsx (caminho configurável)
        ↓
PARSE:  Apache POI → Workbook → Sheet → Linhas/Colunas
        ↓
MAP:    Cabeçalho → Atributos de Objeto
        ↓
OUTPUT: Iterator<Object[]> com objetos CotacaoAutoData, etc
``

**Especificações**:

- Suporta 6 variações de arquivo Excel (identificadas por número)
- Estratégia de seleção por `switch`: mapeia número → caminho do arquivo
- Dinâmica: extrai cabeçalho na linha 0, processa dados a partir da linha 1
- Transformação de texto: substitui quebras de linha (`\n` → `&`)

#### 3.1.2 `CotacaoAutoData` / `CotacaoResidenceData` / `VidaData` / `EmpresarialData`

**Responsabilidade**: Modelo de dados tipado (Data Transfer Object)

``
ESTRUTURA GENÉRICA:
├─ Identificadores: cenario, corretor, tipoSeguro
├─ Dados de Risco: cpf, chassi, cep, endereco
├─ Configurações: coberturas, franquia, blindagem
├─ Validações: erroEsperado (cenários de falha)
└─ Getters/Setters: acesso padronizado aos atributos
``

**Métodos-chave**:

- `definirValor(chave, valor)`: setter genérico por nome de atributo
- Getters: acesso aos 40+ atributos específicos do produto

#### 3.1.3 `DataHelper` (Transformador de Entrada)

**Responsabilidade**: Normalizar dados de entrada para formato esperado pela UI

``
ENTRADA:        TRANSFORMAÇÃO:              SAÍDA:
"Novo"          → tipoSeguroTxt()          → "Seguro Novo"
"casado"        → estadoCivilTxt()         → "Casado[a] ou convive..."
"taxi"          → usoComercialTxt()        → "Táxi"
"apt"           → residenciaTxt()          → "Apartamento"
``

**Padrão**: Reconhecimento case-insensitive com validação parcial de strings

---

### 3.2 EXECUÇÃO - Execution Layer

#### 3.2.1 `Base` (Estrutura de Testes)

**Responsabilidade**: Configuração e ciclo de vida de testes

``
CICLO DE VIDA:
┌─────────────────────────────┐
│  @BeforeSuite()             │
│  ├─ Limpar pasta de saída   │
│  └─ Inicializar contexto    │
├─────────────────────────────┤
│  @BeforeMethod()            │
│  ├─ Obter URL dinâmica      │
│  ├─ Instanciar WebDriver    │
│  ├─ Inicializar DBMainframe │
│  └─ Preparar SeleniumUtils  │
├─────────────────────────────┤
│  @Test (executado)          │
├─────────────────────────────┤
│  @AfterSuite()              │
│  ├─ Gerar relatório Excel   │
│  ├─ Compactar em ZIP        │
│  └─ Limpar arquivos temp    │
└─────────────────────────────┘
``

**Injeções**:

- `WebDriver`: gerenciado por `DriverManager` (framework Walle)
- `DBMainframe`: acesso ao banco mainframe (Database.AZB_EPAC)
- `SeleniumUtils`: utilitários de interação com elementos

#### 3.2.2 `DadosCotacaoPage` (Page Object - Orquestrador Principal)

**Responsabilidade**: Encapsular interações UI (formulário de cotação)

``
ESTRUTURA PADRÃO PAGE OBJECT:
├─ Locadores (constantes): ID, XPATH, CSS Selector
├─ Constructor: injeção de WebDriver
├─ Métodos de Interação:
│  ├─ preencherChassi(valor)
│  ├─ selecionarTipoSeguro(tipo)
│  ├─ preencherDadosRisco(dados)
│  └─ avançarProxima()
└─ Métodos de Validação:
   ├─ validarCarregamentoPágina()
   ├─ obterErrosExibidos()
   └─ verificarResultados()
``

**Comportamentos-chave**:

- Uso de `@Step` (Allure): cada ação registrada
- Captura automática com `Shutterbug`: screenshot no sucesso/erro
- Esperas implícitas e explícitas: `waitFor()` (framework Walle)
- Tratamento dinâmico: seletores com `%s` para parametrização

#### 3.2.3 Páginas Especializadas por Produto

``
epac/
├─ DadosCotacaoPage       → Tela de entrada de dados
├─ DadosRiscoPage         → Tela de fatores de risco
├─ CoberturasPage         → Seleção de coberturas
├─ ValorSeguroPage        → Cálculo de prêmios
├─ PagamentoPage          → Dados de pagamento
├─ ConfirmacaoEmissaoPage → Confirmação de apólice
└─ ResultadosPage         → Cotações disponíveis

trenproduccion/ (versão espanhola/produção)
├─ DatosBasicosPage
├─ DatosClientePage
├─ ...

intra/ (sistema interno)
``

**Princípio**: Uma página = um responsabilidade de negócio

#### 3.2.4 Camada de Execução de Testes

**Padrão**: DataProvider → Teste → Página → Validação

``
@DataProvider(name = "cotacaoAutoData")
public Iterator<Object[]> dataProvider() {
    return ExcelDataProvider.choseSheetData("1", "planilha", "auto");
}

@Test(dataProvider = "cotacaoAutoData")
public void testarCotacaoAuto(CotacaoAutoData dados) {
    // 1. Inicializar página
    DadosCotacaoPage page = new DadosCotacaoPage(driver);

    // 2. Executar ações
    page.preencherChassi(dados.getChassi());
    page.selecionarTipoSeguro(DataHelper.tipoSeguroTxt(dados.getTipoSeguro()));
    
    // 3. Validar resultados
    List<String> erros = page.obterErrosExibidos();
    assertEquals(erros.isEmpty(), true);
}
``

#### 3.2.5 `ApiHelper` / `ApiToken` (Integrações Externas)

**Responsabilidade**: Chamadas HTTP e autenticação

``
FLUXO:
Teste → ApiToken.obterToken() → autenticacao
      → ApiHelper.fazer(request) → resposta JSON
      → validar status/dados
``

---

### 3.3 SAÍDA - Output Layer

#### 3.3.1 `CsvReport` (Gerador de Relatório Principal)

**Responsabilidade**: Consolidar resultados em formato estruturado

``
ENTRADA:        Dados de teste + resultados
                ↓
PROCESSO:       appendToFileEmissaoApolice()
                ├─ gera arquivo temporário (padrão: timestamp)
                ├─ escreve linha CSV formatada
                └─ mantem lista interna de resultados
                ↓
SAÍDA:          Múltiplos arquivos .txt consolidados
``

**Formato de Linha**:

``
casoTeste;produto;cenario;chassis;numOperacao;numCotacao;numProposta;numApolice;erroRecebido
``

#### 3.3.2 `XlsxTools` (Transformador CSV → Excel)

**Responsabilidade**: Converter relatórios temporários em Excel formatado

``
INPUT:  Arquivos .txt (resultado de CsvReport)
        ↓
PARSE:  Leitura linha por linha
        ↓
FORMAT: Aplicar styles (cores, fonts, borders)
        ↓
OUTPUT: report.xlsx com abas por produto
``

#### 3.3.3 `ZipUtils` (Compactor de Evidências)

**Responsabilidade**: Empacotar relatório + screenshots

``
ESTRUTURA:
Report.zip
├─ report.xlsx
├─ capturas/
│  ├─ Auto/
│  │  ├─ chassi_001/
│  │  │  ├─ step_01.png
│  │  │  ├─ step_02.png
│  │  │  └─ error.png
│  │  └─ chassi_002/
│  ├─ Residencia/
│  └─ Vida/
└─ allure-results/
``

#### 3.3.4 `FileUtil` (Utilitários de Sistema de Arquivos)

**Responsabilidade**: Gerenciar ciclo de vida de pastas e arquivos

``
OPERAÇÕES:
├─ createFolder(path)       → criar diretório recursivamente
├─ deleteDirectory(path)    → remover diretório + conteúdo
├─ deleteFolder(path)       → alias para deleteDirectory
└─ salvarScreenshot(path)   → persistir imagem capturada
``

**Garantia**: Sincronização com estrutura de saída esperada

---

## 4. CAMADA DE CONFIGURAÇÃO

### 4.1 `ProjectConfigConstants` (Centralização de Configurações)

**Responsabilidade**: Valores constantes e caminhos do sistema

``
CATEGORIAS:
├─ Paths: reportPath, csvFileName, pathReportTemp
├─ URLs: URL_BASE, AMBIENTE (resolvida dinamicamente)
├─ Timeouts: WAIT_TIME (esperas do Selenium)
├─ Credenciais: USER, PASSWORD (por ambiente)
└─ Filtros: lista de cenários habilitados
``

### 4.2 Environment Properties

``
src/main/resources/environments/
├─ dev.properties      → Desenvolvimento local
├─ int.properties      → Integração
├─ pre.properties      → Pré-produção
├─ uat.properties      → User Acceptance Test
└─ local.properties    → Máquina do desenvolvedor
``

**Seleção**: Via parâmetro `-Denv` ou padrão pré-definido

### 4.3 `PortalAccessBR` / `ConstantsAzb` (Constantes de Negócio)

**Responsabilidade**: Valores específicos de domínio

``
├─ Tipos de Produto: AUTO, MOTO, CAMINHAO, RESIDENCIA, VIDA, EMPRESARIAL
├─ Cenários de Teste: lista de validações esperadas
├─ Mapeamentos: valores UI ↔ valores banco de dados
└─ Ranges: limites aceitáveis de prêmios
``

---

## 5. REGRAS DE OURO (PADRÕES E PRINCÍPIOS)

### 5.1 Responsabilidade Única (Single Responsibility)

✅ **Aplicado**:

- `ExcelDataProvider`: APENAS lê Excel
- `DataHelper`: APENAS transforma dados
- `DadosCotacaoPage`: APENAS UI de cotação
- `CsvReport`: APENAS gera CSV
- `XlsxTools`: APENAS converte para Excel

### 5.2 Separação de Camadas

✅ **Aplicado**:

``
Input Layer      → Carrega + Transforma dados
Execution Layer  → Navega + Interage com UI
Output Layer     → Formata + Exporta resultados
``

❌ **Anti-padrão evitado**: Lógica de negócio espalhada nas páginas

### 5.3 DRY (Don't Repeat Yourself)

✅ **Aplicado**:

- `DataHelper`: métodos reutilizáveis de transformação
- Locadores constantes em `static final`
- `SeleniumUtils`: utilitários centralizados (framework Walle)

### 5.4 Page Object Model (POM)

✅ **Aplicado rigorosamente**:

``
Teste        → Não conhece locadores
Página       → Encapsula locadores + ações
WebDriver    → Abstrato (DriverManager)
``

### 5.5 Fluxo de Dados Unidirecional

✅ **Aplicado**:

``
Excel → Data Object → DataHelper → Page → Validação → CSV → Excel
``

Sem feedback loops ou estado compartilhado incorreto

### 5.6 Configuração vs Código

✅ **Aplicado**:

``
Código:   Lógica de teste (*.java)
Config:   Caminhos, URLs, timeouts (*.properties)
Data:     Massa de teste (*.xlsx)
``

Mudanças sem recompilação: apenas alterar arquivo

### 5.7 Tratamento de Erros Explícito

✅ **Aplicado**:

- `erroEsperado` em data: cenários de falha definidos
- `List<String> erros`: captura erros da UI
- Logs estruturados: rastreamento de execução
- Screenshots em falha: evidência automática

### 5.8 Idempotência de Testes

✅ **Aplicado**:

- Limpeza de pasta: `@BeforeSuite` → delete report
- Renovação de driver: `@BeforeMethod` → novo browser
- Sem dependências entre testes: independência garantida

---

## 6. FLUXO DETALHADO DE UM TESTE

### Exemplo: Cotação de Auto (Motor)

``
FASE 1: ENTRADA
────────────────

1. Ler arquivo: src/test/resources/arquivos_excel/VeiculosRPA.xlsx
2. Buscar aba: "Dados" (nome configurável)
3. Extrair linha: |cenario|corretor|tipoSeguro|chassi|...| → CotacaoAutoData
4. Normalizar: DataHelper.tipoSeguroTxt("novo") → "Seguro Novo"

FASE 2: EXECUÇÃO
────────────────
5. @BeforeSuite: limpar pasta /report
6. @BeforeMethod:

- Obter URL da pool: TargetUrlFactory.provideFrontUrl()
- Iniciar WebDriver Chrome/Firefox
- Conectar ao mainframe: DBMainframe.getInstance()

1. @Test(cotacaoAutoData):

   - Instanciar: DadosCotacaoPage page = new DadosCotacaoPage(driver)
   - Ação 1: page.preencherChassi("ABC1234")
     ├─ Localizar input: By.id("chassis")
     ├─ Limpar campo: clear()
     ├─ Escrever valor: sendKeys()
     ├─ Captura: Shutterbug.takeScreenshot()
     └─ Log Allure: @Step("Preencher chassi ABC1234")
   - Ação 2: page.selecionarTipoSeguro("Seguro Novo")
     ├─ Clicar dropdown
     ├─ Esperar opção: waitForElementVisible()
     ├─ Selecionar valor
     └─ Captura
   - ... (5-15 ações dependendo do cenário)
   - Validação 1: List (String) erros = page.obterErrosExibidos()
     ├─ Se não vazio: teste FALHA com mensagem de erro
     └─ Se vazio: avançar
   - Ação Final: page.avançarProxima() → página resultados
   - Validação 2: ResultadosPage.verificarCotacoes()
     ├─ Consultar banco: dbMainframe.obterCotacao(chassi)
     ├─ Comparar prêmios
     └─ Registrar número de apólice

FASE 3: SAÍDA
────────────────
8. Registrar resultado: CsvReport.appendToFileEmissaoApolice(
   "Test_01", "AUTO", "novo_cliente", "ABC1234",
   "123456", "789", "456789", "654321", "")
9. Capturar evidências: salvar screenshots em:
   /report/capturas/Auto/ABC1234/
10. @AfterSuite:
    - XlsxTools.createExcelReport() → report.xlsx
    - ZipUtils.generateZipReport() → Report.zip
    - FileUtil.deleteFolder(pathReportTemp)

RESULTADO FINAL:
📦 src/test/resources/report/
   ├─ report.xlsx          (tabela com todos os testes)
   ├─ Report.zip           (evidências comprimidas)
   └─ capturas/
      └─ Auto/
         ├─ ABC1234/
         │  ├─ step_01_chassi.png
         │  ├─ step_02_tipo_seguro.png
         │  ├─ step_03_dados_completos.png
         │  └─ resultado_final.png
         └─ ABC5678/
            └─ ...
``

---

## 7. ESTRUTURA DE PASTAS: RESPONSABILIDADES

``
src/main/java/
│
├─ auxiliar/
│  └─ constants/              [CONFIGURAÇÃO] Valores constantes
│     ├─ ProjectConfigConstants.java
│     ├─ PortalAccessBR.java
│     └─ ConstantsAzb.java
│
├─ bases/
│  └─ Base.java                [ORQUESTRAÇÃO] Ciclo de vida de testes
│
├─ data/
│  ├─ CotacaoAutoData.java     [ENTRADA] POJO de Auto
│  ├─ CotacaoResidenceData.java [ENTRADA] POJO de Residência
│  ├─ VidaData.java             [ENTRADA] POJO de Vida
│  └─ EmpresarialData.java      [ENTRADA] POJO de Empresarial
│
├─ dataHelper/
│  └─ DataHelper.java          [ENTRADA] Transformador de dados
│
├─ dataProvider/
│  └─ ExcelDataProvider.java   [ENTRADA] Leitor de Excel
│
├─ generateReport/
│  ├─ CsvReport.java            [SAÍDA] Gerador CSV
│  ├─ XlsxTools.java            [SAÍDA] Conversor Excel
│  ├─ ZipUtils.java             [SAÍDA] Compactor
│  └─ FileUtil.java             [SAÍDA] Utilitários de FS
│
├─ pages/
│  ├─ epac/                      [EXECUÇÃO] Portal Auto Brasil
│  │  ├─ DadosCotacaoPage.java
│  │  ├─ DadosRiscoPage.java
│  │  ├─ CoberturasPage.java
│  │  ├─ ValorSeguroPage.java
│  │  ├─ PagamentoPage.java
│  │  ├─ ResultadosPage.java
│  │  ├─ login/
│  │  └─ Vida/ (subportais)
│  ├─ intra/                     [EXECUÇÃO] Portal Interno
│  └─ trenproduccion/            [EXECUÇÃO] Portal Espanhol
│
├─ utils/
│  └─ api/                       [EXECUÇÃO] Integrações HTTP
│     ├─ ApiHelper.java
│     ├─ ApiToken.java
│     └─ ApisPrint_Pdf.java
│
└─ resources/
   ├─ allure.properties          [CONFIG] Relatório Allure
   └─ environments/              [CONFIG] Por-ambiente
      ├─ dev.properties
      ├─ int.properties
      ├─ pre.properties
      ├─ uat.properties
      └─ local.properties

src/test/java/
│
└─ testcases/
   └─ ui/
      ├─ Auto/
      │  ├─ releaseTarifa1211/  [TESTE] Cenários de Auto
      │  └─ mensagemRestritiva/
      ├─ Residencia/            [TESTE] Cenários de Residência
      ├─ Vida/                  [TESTE] Cenários de Vida
      └─ Empresarial/           [TESTE] Cenários Empresariais

src/test/resources/
│
├─ arquivos_excel/              [ENTRADA] Massa de dados (.xlsx)
│  ├─ VeiculosRPA.xlsx
│  ├─ VeiculosRPA_OK.xlsx
│  ├─ CenariosRPA_TarifasAuto1211.xlsx
│  ├─ Solicitacao_RPA_Residencia_Integracao.xlsx
│  ├─ Solicitacao_RPA_Vida Global Tradicional_Integracao.xlsx
│  └─ Solicitacao_RPA_Empresarial_Integracao.xlsx
│
├─ allure.properties             [CONFIG] Resultado do Allure
└─ report/                        [SAÍDA] Artefatos gerados
   ├─ report.xlsx
   ├─ Report.zip
   └─ capturas/
      ├─ Auto/
      ├─ Residencia/
      ├─ Vida/
      └─ Empresarial/

suites/
└─ quotation1.xml               [ORQUESTRAÇÃO] Suite de execução TestNG
``

---

## 8. DEPENDÊNCIAS PRINCIPAIS (pom.xml)

``
Framework de Testes:
├─ TestNG 7.x              [Execução de testes]
└─ Allure TestNG 2.x       [Relatórios]

Automação Web:
├─ Selenium 4.x            [WebDriver]
├─ Shutterbug 0.x          [Screenshot]
└─ Walle Framework 6.5.5   [Wrapper Selenium + Utilitários]

Processamento de Excel:
└─ Apache POI 5.x          [Leitura/escrita .xlsx]

API HTTP:
└─ (Presumido) HttpClient ou similar em walle.frw

Banco de Dados:
└─ JDBC Mainframe          [DBMainframe]
``

---

## 9. PADRÕES DE EXECUÇÃO

### 9.1 Execução Local (Desenvolvedor)

``bash
mvn clean test -Denv=local
``
Resultado: driver headless, ambiente local.properties, relatório local

### 9.2 Execução CI/CD (Integração)

``bash
mvn clean test -Denv=INT
``
Resultado: driver headless, BD integração, relatório Allure enviado

### 9.3 Execução Pre-Produção

``bash
mvn clean test -Denv=PRE
``
Resultado: validação final antes do go-live

---

## 10. CHECKLIST DE RESPONSABILIDADES

### Entrada ✅

- [ ] `ExcelDataProvider` lê arquivo sem erros
- [ ] `CotacaoAutoData` mapeia todos os 50+ campos
- [ ] `DataHelper` transforma 100% dos valores
- [ ] `ProjectConfigConstants` centraliza paths

### Execução ✅

- [ ] `Base` inicializa/finaliza driver
- [ ] `DadosCotacaoPage` encapsula 30+ locadores
- [ ] Cada página tem responsabilidade única
- [ ] `ApiHelper` isola chamadas HTTP

### Saída ✅

- [ ] `CsvReport` registra todos os testes
- [ ] `XlsxTools` formata com cores/bordas
- [ ] `ZipUtils` compacta com estrutura correta
- [ ] `FileUtil` limpa arquivos temporários

### Qualidade ✅

- [ ] Sem código duplicado entre páginas
- [ ] Constantes em static final
- [ ] Métodos com nomes descritivos
- [ ] Tratamento de exceções explícito

---

## 11. FLUXO DE MANUTENÇÃO

### Adicionar Novo Cenário de Teste

``

1. Adicionar linha ao arquivo Excel: src/test/resources/arquivos_excel/VeiculosRPA.xlsx
2. Criar nova classe Test: src/test/java/testcases/ui/Auto/NovoTesteClass.java
3. Usar @DataProvider: ExcelDataProvider.choseSheetData("1", "AbaDesejada", "auto")
4. Executar: mvn clean test
5. Validar relatório: /report/report.xlsx
``

### Adicionar Nova Transformação de Dados

``

1. Novo método em DataHelper: public static String novoTransformador(String valor)
2. Usar em teste: DataHelper.novoTransformador(dados.getValor())
3. Validar com exemplo
``

### Adicionar Nova Página Web

``

1. Criar ExemplosPage extends PageObjectBase
2. Adicionar @Step para cada ação (Allure)
3. Usar Shutterbug para screenshot
4. Chamar de teste de integração
``

### Mudar Ambiente

``

1. Editar src/main/resources/environments/pre.properties
2. Executar: mvn clean test -Denv=PRE
3. Validar em ProjectConfigConstants qual URL foi carregada
``

---

## CONCLUSÃO

**Modelo Implementado**:

- ✅ **Entrada Clara**: Excel → Data Objects → DataHelper
- ✅ **Execução Estruturada**: Base → Pages → Driver → Banco
- ✅ **Saída Determinística**: CSV → Excel → ZIP → Report
- ✅ **Responsabilidades Separadas**: Cada classe faz UMA coisa bem
- ✅ **Configuração Centralizada**: Sem hardcodes no código
- ✅ **Rastreabilidade**: Allure + Screenshots + Logs

**Pontos Fortes**:

1. Separação clara entre camadas
2. Reutilização de componentes (DataHelper, FileUtil)
3. Escalabilidade (novos produtos = novo Data Object + novo Test)
4. Manutenibilidade (mudança em locador = 1 arquivo)

**Oportunidades de Melhoria**:

1. Consolidar locadores repetidos em base classes
2. Implementar builder pattern para Data Objects
3. Adicionar retry logic para testes flaky
4. Parametrizar timeouts por ambiente
