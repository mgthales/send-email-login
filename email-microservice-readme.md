# Microsserviço de Email

## Descrição
Este microsserviço é responsável pelo processamento e envio de emails após a criação de usuários, utilizando RabbitMQ para comunicação assíncrona.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- RabbitMQ
- JavaMail
- Enum para status de email

## Pré-requisitos
- Java 17 ou superior
- Maven
- RabbitMQ instalado e configurado
- Servidor de email configurado

## Configuração

### Dependências
Adicione as seguintes dependências no `pom.xml`:
- Spring Web
- Spring Data JPA
- Spring AMQP
- Spring Mail
- Banco de dados (ex: H2, PostgreSQL)

### Configuração de Aplicação
No arquivo `application.properties` ou `application.yml`, configure:
```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/emaildb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Configurações RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
broker.queue.email.name=email-queue

# Configurações de Email
spring.mail.host=smtp.seu-provedor.com
spring.mail.port=587
spring.mail.username=seu-email
spring.mail.password=sua-senha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Funcionalidades
- Consumir mensagens de email da fila RabbitMQ
- Processar e enviar emails
- Persistir histórico de emails enviados
- Gerenciar status de envio de emails

## Fluxo de Processamento
1. Recebe mensagem de email da fila do RabbitMQ
2. Processa informações do email
3. Tenta enviar email
4. Salva status de envio no banco de dados
5. Trata possíveis falhas de envio

## Enums de Status de Email
- `SENT`: Email enviado com sucesso
- `ERROR`: Falha no envio do email

## Como Executar
```bash
# Clonar o repositório
git clone seu-repositorio

# Navegar até o diretório
cd email-microservice

# Compilar o projeto
mvn clean package

# Executar a aplicação
java -jar target/email-microservice.jar
```

## Contribuição
- Faça um fork do projeto
- Crie sua feature branch (`git checkout -b feature/nova-funcionalidade`)
- Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
- Push para a branch (`git push origin feature/nova-funcionalidade`)
- Abra um Pull Request
