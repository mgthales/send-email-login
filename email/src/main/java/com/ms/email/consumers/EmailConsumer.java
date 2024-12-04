package com.ms.email.consumers;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

// Isso significa que o Spring a detectará automaticamente e a registrará como um bean no contexto da aplicação.
@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    // A anotação @RabbitListener indica que este método será usado para ouvir mensagens de uma fila do RabbitMQ.
    // O atributo "queues" especifica o nome da fila que será escutada.
    // O valor "${broker.queue.email.name}" é uma propriedade que será carregada do arquivo de configuração (application.properties).
    @RabbitListener(queues = "${broker.queue.email.name}")
    public void receiveEmail(@Payload EmailRecordDto emailRecordDto) { // O parâmetro @Payload indica que o conteúdo da mensagem recebida será mapeado para esta variável 'string'.
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        //ENVIAR Email
        emailService.sendEmail(emailModel);
    }
}

