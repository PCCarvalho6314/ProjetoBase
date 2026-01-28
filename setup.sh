#!/bin/bash

# Script para configurar e compilar o projeto

echo "========================================"
echo "Configurando Projeto Selenium + Maven"
echo "========================================"
echo ""

# Verifica se Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven não está instalado!"
    echo "Instale Maven em: https://maven.apache.org/download.cgi"
    exit 1
fi

# Verifica se Java está instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java não está instalado!"
    echo "Instale Java 11+ em: https://www.oracle.com/java/technologies/"
    exit 1
fi

echo "✅ Verificando versões..."
mvn --version
echo ""
java -version
echo ""

echo "📦 Instalando dependências..."
mvn clean install -DskipTests

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Projeto configurado com sucesso!"
    echo ""
    echo "Próximos passos:"
    echo "1. Edite src/test/resources/config.properties"
    echo "2. Execute os testes: mvn clean test"
    echo "3. Gere relatório: mvn allure:serve"
else
    echo ""
    echo "❌ Erro ao configurar o projeto!"
    exit 1
fi
