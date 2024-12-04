# Microsserviço de Usuário

## Descrição
Este microsserviço é responsável pelo gerenciamento de usuários e integração com o serviço de envio de emails.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- RabbitMQ
- Jakarta Validation

## Pré-requisitos
- Java 17 ou superior
- Maven
- RabbitMQ instalado e configurado

## Configuração

### Dependências
Adicione as seguintes dependências no `pom.xml`:
- Spring Web
- Spring Data JPA
- Spring AMQP
- Jakarta Validation
- Banco de dados (ex: H2, PostgreSQL)

### Configuração de Aplicação
No arquivo `application.properties` ou `application.yml`, configure:
```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configurações RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
broker.queue.email.name=email-queue
```

## Funcionalidades
- Criação de novos usuários
- Envio de mensagem para fila de email após cadastro de usuário

## Endpoints
- `POST /users`: Cria um novo usuário
  - Corpo da requisição:
    ```json
    {
      "name": "Nome do Usuário",
      "email": "usuario@exemplo.com"
    }
    ```

## Fluxo de Comunicação
1. Usuário é criado no microsserviço de Usuário
2. Após criação, uma mensagem é enviada para a fila do RabbitMQ
3. Microsserviço de Email consome a mensagem e envia email de boas-vindas

## Como Executar
```bash
# Clonar o repositório
git clone seu-repositorio

# Navegar até o diretório
cd user-microservice

# Compilar o projeto
mvn clean package

# Executar a aplicação
java -jar target/user-microservice.jar
```

## Contribuição
- Faça um fork do projeto
- Crie sua feature branch (`git checkout -b feature/nova-funcionalidade`)
- Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
- Push para a branch (`git push origin feature/nova-funcionalidade`)
- Abra um Pull Request
