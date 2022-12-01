package com.valko.SpringMusic.Controller;

import com.valko.SpringMusic.Service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Почта", description = "Отправка сообщения на почту")
@RestController
@RequestMapping(value = "/mail")
public class MailAppController {

    @Autowired
    EmailService emailService;

    @GetMapping
    public void sendMail(){
        emailService.send("korovaabc@gmail.com","SpringMusic","Test message from SpringMusic app");
    }
}
