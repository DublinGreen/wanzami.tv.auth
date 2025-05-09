package tv.wanzami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MailRunner implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) {
        emailService.sendEmail(
            "greendublin007@gmail.com",
            "Test Subject",
            "Hello from Spring Boot and Zoho!"
        );
    }
}