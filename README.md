# ProjetoFaculdade
Apresentação semestral da faculdade.

---
## Proposta do trabalho
- Nesse semestre nos foi proposto a criação de uma aplicação que contabilizasse o tempo de volta 
de um objeto (que no nosso caso é um carro de controle remoto) e armazena o tempo em um banco de dados.

- A nossa aplicação consiste em uma plataforma Arduino que tem um sensor ultrassônico que capta 
a distância do objeto, mas utilizamos disso e criamos uma aplicação feita em Java que consome a resposta 
do Arduino, transformamos isso em um valor booleano e então utilizamos para fazer um cronômetro para saber a
quantidade de tempo de volta do objeto.

---

## Como utilizar nosso programa

- Para a utilização do nosso projeto é necessário duas dependências:
  - [jSerialComm](https://github.com/Fazecast/jSerialComm) - essa dependência faz a conexão do Arduino com a aplicação Java.
  - [MySQL Connector Java](https://mvnrepository.com/artifact/mysql/mysql-connector-java) - essa dependência faz a manipulacão do banco de dados.