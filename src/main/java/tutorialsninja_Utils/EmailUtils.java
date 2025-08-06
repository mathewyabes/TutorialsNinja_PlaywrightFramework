package tutorialsninja_Utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class EmailUtils {
    public static void sendReport(String reportPath) {
        final String senderMail = "mathewyabes997@gmail.com";
        final String appPassword = "ldyckrzwwjgevtmt";
        final String recipientMail = "mathewyabes997@gmail.com";
        final String receiverMail = "ponyabes@gmail.com";
        final String receiverMail1 = "ndmprao2001@gmail.com";

        //SMTP server properties
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");

        //Create session with authentication
        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderMail, appPassword);
            }
        });
        session.setDebug(true);

        try {
            //create a Email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMail));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(receiverMail));
            message.setSubject("Automation Test Report");
//            message.setText("Hello \n This is a email form java \n Regards, \n QA team");

            //Email body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hi Team, \n\n Please find the attached automation test report.\n Regards, \n QA team ");

            //Attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            System.out.println("Attachment file path" + reportPath);
            attachmentPart.attachFile(new File(reportPath));

            //Combine email body and attachment
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            //send Email
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
