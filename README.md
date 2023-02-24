# NOME DOS INTEGRANTES
- LUCAS RACHID (SCRUM)
- GABRIELE SIQUEIRA (P.O.)
- MARCOS FELIPE (DEV)
- VINICIUS KOITE (DEV)
- JOILE JUNIOR (DEV)
- YURI GAUZE (DEV)

# Ojetivo do Software

O intuito  do software  é de facilitar o agendamento de serviços estéticos masculinos, buscando praticidade de modo online, facilitando a vida dos barbeiros e dos clientes. O cliente poderá agendar o horário que preferir e estiver à disposição quando quiser, não importa o horário ou o dia. Com isso, o atendimento não fica limitado ao funcionamento da sua barbearia sem que isso prejudique a sua vida pessoal. Outro ponto a ser considerado, é a flexibilização na agenda do barbeiro ou cliente, quando houver a necessidade de remarcar o horário do serviço.


# PASSOS PARA EXECUTAR O PROJETO

### 1 - Você deve criar em sua máquina local, um banco no MySQL, com nome de barbearia.
Sendo que o usuário para acesso, será root e a senha 'barbearia', caso você já tenha
instalado o MySQL com outra senha, você deve entrar na pasta `src/main/resources/application.yml`
e adaptar as infos do seu banco local.

#### 1.1 - OBS
Atualmente o sistema está sendo desenvolvido, então o banco não terá informações, 
será necessário rodar um script para popular o banco, mas ele ainda será desenvolvido.

### 2 - Ao realizar o clone do projeto, você deve dar um 'clean, install' com maven
para realizar o download das dependências do projeto.

#### 2.1 - OBS
É necessário ter instalado em sua máquina o Java SDK 17.

### 3 - Pode startar o serviço. Na pasta 'resources', também possui um arquivo, que você pode
importar no seu postman, com a coleção de requisições que poderão ser realizadas dentro do projeto.

### 4 - Também poderá visualizar a documentação do projeto através do Swagger, no link:
`http://localhost:8081/swagger-ui.html#/`.

### 5 - Rodar o Front-End
Você deve acessar a pasta raiz do projeto front, que se encontra em `front-end`, e executar o comando
`npm install`, para realizar o download das dependências. Após realizar o download, executar o comando
`npm start` para rodar o projeto.

#### 5.1 OBS
Para realizar o acesso no front, acessar `localhost:4200`

# - Qualquer dúvida, pode entrar em contato pelo discord Rachid#1337.
