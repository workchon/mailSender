/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */

package mensajeria;

/**
 *
 * @author Naveen
 */
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
public class EmailSend {

    public static void main(String args[]){
        try{
            String host ="smtp.gmail.com" ;
            String user = "faustobg132@gmail.com";
            String pass = "x";
            String to = "faustobg132@gmail.com";
            String from = "faustobg132@gmail.com";
            String subject = "This is a test, a tasty test";
            String messageText = "Holo";
            boolean sessionDebug = true;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            

            Session mailSession = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("Jesusantonio4567890@hotmail.com","123456789jesus");
                        }
}
                    
);
            
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress("Jesusantonio4567890@hotmail.com")};
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("fausto132@live.com.mx"));
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport.send(msg);
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

//        final String username = "fausto132@live.com.mx";
//		final String password = "key42AltU2";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.live.com");
//		props.put("mail.smtp.port", "587");
//
//		Session session = Session.getInstance(props,
//		  new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		  });
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("fausto132@live.com.mx"));
//			message.setRecipients(Message.RecipientType.TO,
//				InternetAddress.parse("fausto132@live.com.mx"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler,"
//				+ "\n\n No spam to my email, please!");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}

    }
}
