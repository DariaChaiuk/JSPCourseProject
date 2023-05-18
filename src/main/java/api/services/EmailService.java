package api.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class EmailService {
    public static void sendEmail(String host, String port, String user, String password, String address,
                                 String topic, String content) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        Authenticator auth = new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        };

        Session session = Session.getInstance(properties, auth);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("offerRequest@gmail.com"));
        InternetAddress[] addresses = { new InternetAddress(address) };

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("whcompany@gmail.com"));
        message.setSubject(topic);
        message.setSentDate(new Date());
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html; charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}
