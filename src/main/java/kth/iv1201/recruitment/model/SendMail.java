package kth.iv1201.recruitment.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@SpringBootApplication
public class SendMail {

    /**
     * Consructor for this object
     */
    public SendMail(){

    }

    /**
     *
     * @param recipient The reciver of the mail
     * @param content the Content of the mail
     * @throws AddressException if address not found
     * @throws MessagingException
     * @throws IOException
     */
    public void sendmail(String recipient,String content) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("iv1201.group5@gmail.com", "IV1201++");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("IV1201.group5@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        msg.setSubject("Password reset");
        msg.setContent("Your knew password: " + content, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");


        Transport.send(msg);
    }



}