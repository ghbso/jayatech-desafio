
# API de conversão de moedas

 ## Requisitos da API
1. Realizar a conversão entre duas moedas 
 - **O que deve fazer ?** Realizar a conversão entre duas moedas do tipo BRL, USD, EUR ou JPY utilizando taxas de conversões atualizadas de um serviço externo.  As transações de conversão devem ser persistidas no banco de dados contendo:
	 -	ID do usuário;
	 -	Moeda origem;
	 -	Valor origem;
	 -	Moeda destino;
	 -	Taxa de conversão utilizada;
	 -	Data/Hora UTC;
 - **O que deve ser retornado?** Um JSON contendo os seguintes dados:
	 -  ID da transação;
	 - 	ID do usuário;
	 -	Moeda origem;
	 -	Valor origem;
	 -	Moeda destino;
	 -	Valor destino;
	 -	Taxa de conversão utilizada;
	 -	Data/Hora UTC;

2. Listagem de transações por usuário
 - **O que deve fazer ?** Listar as transações realizadas por um usuário.
 - **O que deve ser retornado?** Uma lista com os dados de todas as transações do usuário.
 
 ## Tecnologias/Frameworks/Bibliotecas
Este projeto foi implementado em Java, utilizando o framework Spring Boot v2.5.5. Os testes de unidade foram realizados utilizando o JUnit 5 e as rotas foram documentadas com Swagger.  Os logs foram armazenados utilizando o framework de logs padrão no projeto do Spring Boot.
 
 ## Estruturação do projeto
 
O código fonte da aplicação encontra-se dentro da pasta **src/main**. 

O código fonte dos testes de unidade encontra-se dentro da pasta **src/test**. 

O projeto foi desenvolvido utilizando, em geral, quatro camadas: **camada de recursos, serviço, modelo, e banco de dados**. Na camada de recursos encontram-se os endpoints disponíveis pela API.  A camada de serviço é responsável pela lógica do negócio, fazendo uso da camada de modelo para representar as entidades e a camada de banco de dados para persisti-las.

 ## Testes de unidade dos requisitos do sistema

Os testes unitários realizados neste projeto cobrem as camadas de recurso e serviço.  

## Executando a API

 1. A documentação completa da API encontra-se disponível no seguinte endereço: https://jaya-demo.herokuapp.com/swagger-ui/
 2. Segue abaixo exemplos de reqquest e responses para cada endpoint:
    - https://jaya-demo.herokuapp.com/exchange
        - Método HTTP: POST
        -  Request body:
                    ```{ "userdID":  1, fomCurrency":  "BRL",	"toCurrency":  "USD",
                        "value":  1	}```
        -  Response body:
                    ```{"transactionID":  1,"userdID":  1,"fromCurrency":  "BRL",
"toCurrency":  "USD","value":  0.18,"exchangeRate":  0.18,
"dateTime":  "2021-10-07T21:57:22.614701"}```
    - https://jaya-demo.herokuapp.com//exchange/list?userID=1
        - Método HTTP: GET 
        -  Response body:
                    ```[{"transactionID":  1, "userdID":  1, "fromCurrency":  "BRL",
"toCurrency":  "USD", "value":  0.18, "exchangeRate":  0.18, "dateTime":  "2021-10-07T21:57:22.614701"}]```
