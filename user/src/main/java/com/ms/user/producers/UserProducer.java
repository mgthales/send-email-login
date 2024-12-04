package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
     private String routingKey;

    public void publishMessageEmail(UserModel userModel){

        String subject = "Cadastro realizado com sucesso!";
        String text = String.format(
                "%s, seja bem-vindo(a)!\n\n" +
                        "Agradecemos o seu cadastro e esperamos que aproveite ao máximo nossos serviços.\n\n" +
                        "Se precisar de qualquer ajuda, não hesite em nos contatar.\n\n" +
                        "Atenciosamente,\nEquipe Thales",
                userModel.getName()
        );

        // Configuração do DTO de e-mail
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject(subject);
        emailDto.setText(text);


        // Envio da mensagem para a fila RabbitMQ
        //exchange padrao precisa somente passar uma "string vazia" que por padrao ja sabem que vamos utilizar a default
        rabbitTemplate.convertAndSend("", routingKey, emailDto);

    }

}
