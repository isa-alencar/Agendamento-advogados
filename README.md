# Sistema adv-agendamentos

Para acessar o sistema em sua máquina, siga as instruções abaixo.

## Pré-requisitos
* **Java JDK** (versão 17 ou superior recomendada)
* **Eclipse IDE for Enterprise Java and Web Developers** (ou IDE parecida)
* **Apache Tomcat v10.1** 
* **Banco de dados relacional** (crie os arquivos de configuração como seus exemplos)
---

## Importar para IDE
* importe a pasta como um projeto Maven
* configure o servidor Tomcat v10.1*
* vincule o servidor ao projeto

## Banco de dados
Para abrir o projeto, é necessário um banco de dados por conta da persistência dos dados.

* crie um banco de dados sistema_adv com MySQL
* conecte no banco de dados criado
* execute os SQL scripts da pasta SQL deste repositório
* nas configurações, altere os arquivos persisence.xml e hibernate.cfg.xml com as credenciais do seu banco de dados

## Executar o sistema
* acesse o localhost e entre no sistema

