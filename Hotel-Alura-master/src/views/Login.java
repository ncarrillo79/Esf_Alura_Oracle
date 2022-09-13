package views;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private String validUser = "alura";
	private String validPass = "alura2022";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setTitle("Panel de Login- Hotel-Alura");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/perfil-del-usuario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/hotel.png")));
		lblNewLabel.setBounds(-91, -5, 426, 499);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(409, 181, 234, 33);
		contentPane.add(txtUsuario);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(409, 156, 57, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(409, 261, 234, 33);
		contentPane.add(txtContrasena);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(409, 236, 133, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/imagenes/perfil-del-usuario.png")));

		btnLogin.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String User = txtUsuario.getText();
				String Pass = String.valueOf(txtContrasena.getPassword());	
				if(Pass.equals(validPass) && validUser.equals(User)) {
					JOptionPane.showMessageDialog(null, "Inicio de sesion Exitoso");
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Usuario/Contraseña incorrectos", "Error al iniciar sesion", JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		
		btnLogin.setBounds(409, 322, 103, 33);
		contentPane.add(btnLogin);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/imagenes/cerrar-24px.png")));
		btnCancelar.setBounds(523, 322, 103, 33);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea volver al Menu Principal?", "Alerta", JOptionPane.YES_NO_OPTION);
				if(resp == 0) {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					dispose();	
				}
								
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Hotel-Alura");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Genesys\\Documents\\imagenesAluraHotel\\Ha-100px.png"));
		lblNewLabel_1.setBounds(463, 42, 151, 39);
		contentPane.add(lblNewLabel_1);
	}
}
