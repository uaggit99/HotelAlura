package jdbc.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import views.UsuarioLogin;

public class Envio {
	private UsuarioLogin usuariologin;
	private  String destino = usuariologin.textFieldCorreo.getText()  ;

	public void enviar() {
		
			try {
				Properties prop = new Properties();
				prop.setProperty("mail.smtp.host", "smtp.gmail.com");
				prop.setProperty("mail.smtp.starttls.enable", "true");
				prop.setProperty("mail.smtp.port", "587");
				prop.setProperty("mail.smtp.auth", "true");

				Session session = Session.getDefaultInstance(prop);
				String correoRemitente = "ace77ey@gmail.com";
				String passwordRemitente = "ZXCVuag741*";
				
				System.out.println(destino);
				String asunto = "Registro de Usuario";
				String mensaje = "Felicidades Registro exitoso en HOTEL ALURA";

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(correoRemitente));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
				message.setSubject(asunto);
				message.setText(mensaje);
				Transport t = session.getTransport("smtp");
				t.connect(correoRemitente, passwordRemitente);
				t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));

				JOptionPane.showMessageDialog(null,String.format("Envio de correo exitoso"));

			} catch (AddressException ex) {
				Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);

			} catch (MessagingException ex) {

				Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);

			}


	}

}
