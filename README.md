# PDV-ponto-de-venda
Aplicação desktop focada em banco de dados com pradão DAO e sintaxe nativa em SQL.

## Ambiente usado
- Netbeans(8.0,11.1)
- MYSQL(5.7)
- Linux(Ubuntu 18.04)
- Java(OpenJDK-1.8.0_232)

## Bibiotecas
- [Junit-4.12](https://mvnrepository.com/artifact/junit/junit/4.12)
- [MyBatis-3.5.3](https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.3)
- [Hamcrest 1.3](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core/1.3)
- [MySQL Connector/J-8.0.18](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.18)

## Pré-execução
1. Altere `conexao.DatabasesURL.java` para configurar a sua conexão com o banco de dados.

2. execute o script fornecido: `resource/ponto_de_venda.sql` antes de executar a aplicação.
   - faça o mesmo para o script `resource/ponto_de_venda_teste.sql` se deseja executar os casos de teste.
