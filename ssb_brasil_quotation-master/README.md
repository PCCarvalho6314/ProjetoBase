# Projeto de Cotação de Seguros em Massa

## Descrição do Projeto

Este projeto tem como objetivo realizar cotações de seguro em massa dos produtos Auto, Moto e Caminhão, além de realizar validações de erros configuradas no sistema/radar live.

## Entrada de Dados

A entrada de dados é um arquivo .xlsx fornecido pela equipe de negócios de Auto, que deve ser armazenado na pasta `src/test/resources/arquivos_excel`.

## Saída de Dados

A saída de dados será gerada no arquivo `report.xlsx`, localizado na pasta `src/test/resources/report`. As capturas de tela serão organizadas por produto e chassi na pasta `src/test/resources/report/capturas`. Além disso, os arquivos também estarão disponíveis no arquivo `Report.zip` na mesma pasta, facilitando o envio para as partes interessadas.

## Execução dos Testes

Para executar os testes, utilize os seguintes comandos:

- Ambiente Pré-Produção: `mvn tests`
- Ambiente Integração: `mvn tests -Denv=INT`
