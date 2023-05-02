package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import jdbc.controller.UsuarioController;
import jdbc.model.Usuarios;

public class UsuarioLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JTextField textFieldClave;
	private static UsuarioController usuarioController;
	public static JTextField textFieldCorreo;

	private static String emailFrom = "ace77ey@gmail.com";
	private static String claveFrom = "gknxkhrqytwrityh";
	private String emailTo;
	private String Subject = "Registro de usuario";
	private String content = "! FELICIDADES ! registro exitoso en Hotel Alura";

	private Properties p;
	private Session session;
	private MimeMessage message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioLogin frame = new UsuarioLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UsuarioLogin() {
		this.usuarioController = new UsuarioController();

		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(UsuarioLogin.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel.setBounds(193, 11, 108, 100);
		contentPane.add(lblNewLabel);

		JLabel lblusuario = new JLabel("Ingrese su Usuario");
		lblusuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblusuario.setForeground(new Color(105, 105, 105));
		lblusuario.setBackground(new Color(0, 120, 215));
		lblusuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblusuario.setBounds(20, 139, 181, 39);
		contentPane.add(lblusuario);

		JLabel lblIngreseSuPassword = new JLabel("Ingrese su Password");
		lblIngreseSuPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngreseSuPassword.setForeground(new Color(105, 105, 105));
		lblIngreseSuPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngreseSuPassword.setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		lblIngreseSuPassword.setBounds(20, 214, 181, 39);
		contentPane.add(lblIngreseSuPassword);

		textFieldUsuario = new JTextField();
		textFieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldUsuario.setForeground(new Color(80, 102, 207));
		textFieldUsuario.setBounds(227, 139, 201, 39);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		textFieldClave = new JTextField();
		textFieldClave.setFont(new Font("SimSun", Font.PLAIN, 16));
		textFieldClave.setForeground(new Color(80, 102, 207));
		textFieldClave.setColumns(10);
		textFieldClave.setBounds(227, 215, 201, 39);
		contentPane.add(textFieldClave);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setForeground(new Color(80, 102, 207));
		textFieldCorreo.setFont(new Font("SimSun", Font.PLAIN, 16));
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(227, 292, 201, 39);
		contentPane.add(textFieldCorreo);

		JButton btnguardar = new JButton("GUARDAR");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String nombrer = textFieldUsuario.getText();
				String claver = textFieldClave.getText();
				String correor = textFieldCorreo.getText();
				Usuarios usuarios = new Usuarios(nombrer, claver);
				UsuarioController controllerR = new UsuarioController();
				List dato2 = controllerR.validacion(nombrer, claver);
				System.out.println(nombrer);
				System.out.println(claver);

				if (dato2 != null) {
					JOptionPane.showMessageDialog(null, "El usuario ya esta Registrado");
					textFieldUsuario.setText("");
					textFieldClave.setText("");
					textFieldCorreo.setText("");

				} else {
					Usuarios usuario = new Usuarios(nombrer, claver, correor);
					usuarioController.guardar(usuario);
					// crearEmail();
					// sendEmail();
					Login login = new Login();
					login.setVisible(true);
					dispose();

				}

			}
		});
		btnguardar.setForeground(new Color(255, 255, 255));
		btnguardar.setBackground(SystemColor.textHighlight);
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnguardar.setBounds(300, 410, 128, 48);
		contentPane.add(btnguardar);

		JLabel lblIngreseCorreo = new JLabel("Ingrese su Correo");
		lblIngreseCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngreseCorreo.setForeground(SystemColor.controlDkShadow);
		lblIngreseCorreo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngreseCorreo.setBackground(SystemColor.textHighlight);
		lblIngreseCorreo.setBounds(20, 291, 181, 39);
		contentPane.add(lblIngreseCorreo);

	}

	private void crearEmail() {
		emailTo = textFieldCorreo.getText();
		p = new Properties();
		p.setProperty("mail.smtp.host", "smtp.gmail.com");
		// p.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.setProperty("mail.smtp.starttls.enable", "true");
		p.setProperty("mail.smtp.port", "587");
		p.setProperty("mail.smtp.user", "emailFrom");
		// p.setProperty("mail.smtp,ssl.protocols", "TLSv1.2");
		p.setProperty("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(p);

		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setSubject(Subject);
			message.setText(content);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendEmail() {
		try {
			Transport t = session.getTransport("smtp");
			t.connect(emailFrom, claveFrom);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();
			JOptionPane.showMessageDialog(null, "Correo Enviado");
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
