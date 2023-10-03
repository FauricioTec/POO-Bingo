package logicadenegocios;

import java.util.Properties;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

  private final String usuario;
  private final String contrasenna;
  private final Properties properties;

  public Email(String pUsuario, String pContrasenna) {
    this.usuario = pUsuario;
    this.contrasenna = pContrasenna;

    properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
  }

  public void enviarEmailConImagen(String[] pDestinatario, String pAsunto, String pPathImagen,
      String pBody) throws MessagingException, java.io.IOException {

    Message mensaje = iniciarMensaje(pDestinatario, pAsunto);

    MimeMultipart multipart = new MimeMultipart();

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(pBody, "text/html");
    multipart.addBodyPart(messageBodyPart);

    MimeBodyPart imageBodyPart = new MimeBodyPart();
    FileDataSource fileDataSource = new FileDataSource(pPathImagen);
    imageBodyPart.attachFile(fileDataSource.getFile());
    imageBodyPart.setHeader("Content-ID", "<image>");
    multipart.addBodyPart(imageBodyPart);

    mensaje.setContent(multipart);

    Transport.send(mensaje);
  }

  public void enviarEmail(String[] pDestinatario, String pAsunto, String pBody)
      throws MessagingException {
    Message mensaje = iniciarMensaje(pDestinatario, pAsunto);

    mensaje.setContent(pBody, "text/html");

    Transport.send(mensaje);
  }

  private Message iniciarMensaje(String[] pDestinatarios, String pAsunto) throws MessagingException {
    Session session = Session.getInstance(properties, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, contrasenna);
      }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(usuario));

    Address[] toAddresses = new Address[pDestinatarios.length];
    for (int i = 0; i < pDestinatarios.length; i++) {
      toAddresses[i] = new InternetAddress(pDestinatarios[i]);
    }
    message.setRecipients(Message.RecipientType.TO, toAddresses);

    message.setSubject(pAsunto);

    return message;
  }
}