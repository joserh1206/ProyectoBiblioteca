package Usuario;

import javax.mail.*; //Import de la clase necesaria para enviar un correo
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase que maneja la lectura y escritura de los archivos en ClasesBiblioteca
 * @author Randall Delgado
 * @author José Luis Rodríguez
 * @author Óscar Cortés
 */

public class Correo {
    private String correo;
    private String password;
    private String cuerpo;
    private String destinatario;
    private String asunto;
    
    public Correo(){
        this("", "");
    }
    
    public Correo(String correo, String password){
        this.correo = correo;
        this.password = password;
        this.destinatario = "";
        this.cuerpo = "";
        this.asunto = "";
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCorreo() {
        return correo;
    }
    
    public boolean enviarCorreo(){
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.socketFactory.port","465");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.put("mail.smtp.auth", true);
        p.put("mail.smtp.port", true);
        
        Session sesion = Session.getDefaultInstance(p,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(correo, password);
                }
            }
        );
        
        System.out.println(sesion);
        
        try{
            Message message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(this.correo, this.password));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.destinatario));
            message.setSubject(this.asunto);
            message.setText(this.cuerpo);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
