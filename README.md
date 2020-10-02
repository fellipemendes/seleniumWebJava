# Selenium + Java + Cucumber

Projeto foi construido para ser rodar os testes a partir de uma classe main, pois havia a necessidade de um JAR para execução na esteira do Jenkins.

Algumas classes foram renomeadas, pois os nomes originais vieram de uma automação desenvolvida. Será atualizado para ficar pronto para uso. Caso use por agora, necessário recriar as features e classes de steps.

## Setup

1. Java 8+
2. Maven 3.6.0

- Precisa instalar o Java JDK. Caso não tenha o Java instalado
- Após a instalação é necessário adicionar a variável JAVA_HOME nas variáveis de ambiente
- Precisa baixar o Maven. Caso não tenha o Maven instalado
- Após a descompactação é necessário registrá-lo nas variáveis de ambiente

>A instalação do Java e Maven varia de acordo com o sistema operacional (Linux, Windows e Mac)

>Você deve realizar as configurações do Java e Maven na IDE que você irá utilizar (ex: IntelliJ, Eclipse, VsCode e etc)

## Passo a passo

1. Clone o projeto
2. Abra o projeto no seu IDE, no terminal, execute os comandos abaixo para atualizar as dependências e executar o teste:
```shell script
mvn clean install -Dmaven.test.skip=true
```
3. A definação de execução virá a partir do arquivo feature para definir se será DOCKER_CHROME (Selenium Grid) ou CHROME (execução local)
4. Executar a classe com/common/utils/RunnerClass/Runner.java que irá executar os testes de acordo com os arquivos features e tags definidos na configuraçao da classe.
5. Será gerado um relatório na pasta cucumber-html-reports ao final da execução.

## Proposta
 - O projeto visa utilizar Selenium + Java + Cucumber para automação Web. Também está preparado para uma possível necessidade de gerar um JAR para a execução dos testes.
 - Está configurado para execução no Hub do Selenium GRID (local ou exposto na web/rede).
 - Possui algumas classes de apoio que métodos que auxiliam o desenvolvimento para deixar os steps mais enxutos e de fácil entendimento.
