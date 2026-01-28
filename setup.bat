@echo off
REM Script para configurar e compilar o projeto no Windows

echo ========================================
echo Configurando Projeto Selenium + Maven
echo ========================================
echo.

REM Verifica se Maven está instalado
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo X Maven não está instalado!
    echo Instale Maven em: https://maven.apache.org/download.cgi
    exit /b 1
)

REM Verifica se Java está instalado
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo X Java não está instalado!
    echo Instale Java 11+ em: https://www.oracle.com/java/technologies/
    exit /b 1
)

echo + Verificando versões...
mvn --version
echo.
java -version
echo.

echo Instalando dependências...
mvn clean install -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo + Projeto configurado com sucesso!
    echo.
    echo Próximos passos:
    echo 1. Edite src\test\resources\config.properties
    echo 2. Execute os testes: mvn clean test
    echo 3. Gere relatório: mvn allure:serve
) else (
    echo.
    echo X Erro ao configurar o projeto!
    exit /b 1
)

pause
