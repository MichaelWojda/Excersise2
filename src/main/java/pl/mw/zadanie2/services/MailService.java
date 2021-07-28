package pl.mw.zadanie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Service class holding methods
 * for email notifications
 *
 * @author Michał Wojda
 * @version alpha
 */
@Service
public class MailService {

    /**
     * Mail Sender Class Object takes params from application.properties
     */
    private JavaMailSender javaMailSender;

    /**
     * Constructor
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    /**
     * Method for sending email notification based on provided params
     *
     * @param to      whom is message sent
     * @param text    body of the message
     * @param subject of the message
     * @param isHtml  formatted in hmtl
     */
    public void sendMail(String subject, String to, String text, boolean isHtml) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(text, isHtml);
            mimeMessageHelper.setSubject(subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //javaMailSender.send(mimeMessage);
        System.out.println("TO: " + to);
        System.out.println("SUBJECT: " + subject);
        System.out.println("TEXT: " + text);


    }


}
