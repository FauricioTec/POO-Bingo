package utilidad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import logicadenegocios.Carton;


/**
 * Clase que representa un email
 */
public class Email {

  private final String usuario;
  private final String contrasenna;
  private final Properties properties;

  /**
   * Constructor de la clase Email
   *
   * @param pUsuario     Usuario del email
   * @param pContrasenna Contrasenna del email
   */
  public Email(String pUsuario, String pContrasenna) {
    this.usuario = pUsuario;
    this.contrasenna = pContrasenna;

    properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
  }

  /**
   * Metodo que envia un correo con los cartones a un jugador
   *
   * @param pCartones Cartones que se van a enviar
   * @param pEmail    Email del jugador
   */
  public static void enviarCorreoConCartones(ArrayList<Carton> pCartones, String pEmail)
      throws javax.mail.MessagingException, IOException {
    Email email = new Email("correosautomaticos673@gmail.com", "dwhv ixzp lnud ljwl");
    ArrayList<String> direccionImagenes = new ArrayList<>();
    StringBuilder bodyHtml = new StringBuilder(
        "<html><head><style>" +
            "h1 { color: #333; font-size: 24px; }" +
            "h3 { color: #666; font-size: 18px; }" +
            "img { max-width: 100%; }" +
            "</style></head><body>" +
            "<h1>Cartones</h1>" +
            "<h3>Los cartones son los siguientes:</h3>");
    int i = 0;
    for (Carton carton : pCartones) {
      direccionImagenes.add(".\\cartones\\" + carton.getId() + ".png");
      bodyHtml.append("<img src=\"cid:image").append(i).append("\">");
      i++;
    }
    email.enviarEmailConImagenes(pEmail, "Asignacion de carton", direccionImagenes,
        bodyHtml.toString());
  }

  public static void enviarCorreoGanador(String pIdCarton, String pPremio, String pEmail)
      throws javax.mail.MessagingException, IOException {
    Email email = new Email("correosautomaticos673@gmail.com", "dwhv ixzp lnud ljwl");
    String bodyHtml = "<h1>Carton ganador </h1>"
        + "<h4>El carton con id: " + pIdCarton + " ha ganado</h4>"
        + "<h4>El premio es de: " + pPremio + "</h4>"
        + "<h4>El carton es el siguiente:</h4>"
        + "<img src=\"cid:image\">";
    email.enviarEmailConImagen(pEmail, "Asignacion de carton",
        ".\\cartones\\" + pIdCarton + ".png", bodyHtml);
  }

  public static boolean esEmailValido(String email) {
    String regex = "^(.+)@(\\S+) $";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Metodo que envia un email con imagenes
   *
   * @param pDestinatario        Destinatario del email
   * @param pAsunto              Asunto del email
   * @param pDireccionesImagenes Direcciones de las imagenes
   * @param pBody                Cuerpo del email
   */
  public void enviarEmailConImagenes(String pDestinatario, String pAsunto,
      ArrayList<String> pDireccionesImagenes,
      String pBody) throws MessagingException, java.io.IOException {

    Message mensaje = iniciarMensaje(pDestinatario, pAsunto);

    MimeMultipart multipart = new MimeMultipart();

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(pBody, "text/html");
    multipart.addBodyPart(messageBodyPart);

    int i = 0;
    for (String pDireccionImagen : pDireccionesImagenes) {
      MimeBodyPart imageBodyPart = new MimeBodyPart();
      FileDataSource fileDataSource = new FileDataSource(pDireccionImagen);
      imageBodyPart.attachFile(fileDataSource.getFile());
      imageBodyPart.setHeader("Content-ID", "<image" + i++ + ">");
      multipart.addBodyPart(imageBodyPart);
    }

    mensaje.setContent(multipart);

    Transport.send(mensaje);
  }

  /**
   * Metodo que envia un email con una imagen
   *
   * @param pString          Destinatario del email
   * @param pAsunto          Asunto del email
   * @param pDireccionImagen Direccion de la imagen
   * @param bodyHtml         Cuerpo del email
   */
  public void enviarEmailConImagen(String pString, String pAsunto,
      String pDireccionImagen, String bodyHtml) throws MessagingException, java.io.IOException {

    Message mensaje = iniciarMensaje(pString, pAsunto);

    MimeMultipart multipart = new MimeMultipart();

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(bodyHtml, "text/html");
    multipart.addBodyPart(messageBodyPart);

    MimeBodyPart imageBodyPart = new MimeBodyPart();
    FileDataSource fileDataSource = new FileDataSource(pDireccionImagen);
    imageBodyPart.attachFile(fileDataSource.getFile());
    imageBodyPart.setHeader("Content-ID", "<image>");
    multipart.addBodyPart(imageBodyPart);

    mensaje.setContent(multipart);

    Transport.send(mensaje);
  }

  /**
   * Metodo que envia un email
   *
   * @param pDestinatario Destinatario del email
   * @param pAsunto       Asunto del email
   * @param pBody         Cuerpo del email
   */
  public void enviarEmail(String pDestinatario, String pAsunto, String pBody)
      throws MessagingException {
    Message mensaje = iniciarMensaje(pDestinatario, pAsunto);

    MimeMultipart multipart = new MimeMultipart();

    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(pBody, "text/html");
    multipart.addBodyPart(messageBodyPart);

    mensaje.setContent(multipart);

    Transport.send(mensaje);
  }

  /**
   * Metodo que inicia la sesion
   *
   * @return Session
   */
  private Session iniciarSesion() {
    return Session.getInstance(properties, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, contrasenna);
      }
    });
  }

  /**
   * Metodo que inicia el mensaje
   *
   * @param pDestinatario Destinatario del email
   * @param pAsunto       Asunto del email
   * @return Message
   */
  private Message iniciarMensaje(String pDestinatario, String pAsunto) throws MessagingException {
    Session session = iniciarSesion();

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(usuario));

    Address[] toAddresses = new Address[1];
    toAddresses[0] = new InternetAddress(pDestinatario);
    message.setRecipients(Message.RecipientType.TO, toAddresses);

    message.setSubject(pAsunto);

    return message;
  }
}