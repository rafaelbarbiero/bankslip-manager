[![Build Status](https://travis-ci.com/rafaelbarbiero/bankslip-manager.svg?branch=master)](https://travis-ci.com/rafaelbarbiero/bankslip-manager) [![codecov](https://codecov.io/gh/rafaelbarbiero/bankslip-manager/branch/master/graph/badge.svg)](https://codecov.io/gh/rafaelbarbiero/bankslip-manager) 
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=code_smells) 
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=bugs)
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=duplicated_lines_density)
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=sqale_rating)
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=sqale_index)
![](https://sonarcloud.io/api/project_badges/measure?project=rafaelbarbiero_bankslip-manager&metric=reliability_rating)
# Bankslip Manager Application

### Tecnologias
* Java 8
* Maven
* Spring Boot
* Spring Data H2
* JUnit
* Swagger
* JaCoCo
* SonarLint
### Execução local
* **Requisitos**
    1. Java 8 
    2. Maven 3.5.3

* **Instruções**
    1. Navegar até o diretório raiz do projeto
    2. Executar o comando Maven para a construção da aplicação **mvn clean install**
    3. Navegar até o diretório **/bankslip-api/target/**
    3. Executar o comando de inicialização **java -jar bankslip-api-0.0.1-SNAPSHOT.jar --server.port=PORT**
    6. Se a porta não for informada, a aplicação iniciará recebendo as requisições na porta padrão 8080
    4. É possível alterar a porcentagem nas regras de cálculo com os argumentos **--interest.percentsDaysUpTo=DOUBLE VALUE(padrão 0.5) --interest.percentsGreaterThan=DOUBLE VALUE (padrão 1.0)**
    5. É possível alterar a quantidade de dias para as regras de cálculo com o argumento **--interest.daysUpTo=INT VALUE(padrão 10 dias)** 

### Consumindo a API
_Para visualizar a documentação completa da API, inicie a aplicação e navegue até a URL:_ **http://IP_ADDRESS:PORT/swagger-ui.html**
# **Endpoints**

* #### GET /rest/bankslips
    ##### Listar todos os boletos      
    * 200: Sucesso 
* #### GET /rest/bankslips/{id}
    ##### Detalhes do boleto referente ao id     
    * 200: Ok
    * 404: Boleto referente ao id não encontrado   
* #### POST /rest/bankslips 
    ##### Cria um novo boleto com os dados recebidos no corpo da requisição   
    * 201: Boleto criado com sucesso
    * 400: Corpo da requisição sem dados do boleto
    * 422: Corpo da requisição com dados do boleto inválidos
    * **Content-Type:** application/json
    * **Content-body:** `{"due_date":"2018-01-01", "total_in_cents":"100000", "customer":"Trillian Company"}`

* #### POST /rest/bankslips/{id}/payments  
    ##### Altera o status do boleto referente ao id para **"PAID"**
    * 204: No content
    * 404: Boleto referente ao id não encontrado  
* #### DELETE /rest/bankslips/{id} 
    ##### Altera o status do boleto referente ao id para **"CANCELED"**
    * 204: No content
    * 404: Boleto referente ao id não encontrado

### Consumindo o serviço em nuvem
Este projeto está hospedado no serviço de computação em nuvem **Amazon AWS** disponibilizado na seguinte URL: **http://bankslip-manager.ddns.net:8080/swagger-ui.html**
