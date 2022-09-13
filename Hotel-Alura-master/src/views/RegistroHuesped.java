package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import data.HuespedDAO;
import data.ReservaDAO;
import domain.Huesped;
import domain.Reserva;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					RegistroHuesped frame = new RegistroHuesped();
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
	public RegistroHuesped() {
		setTitle("Registro de Huésped - Hotel-Alura");
		Reservas reservas = new Reservas();
		ReservaDAO reservaDAO = new ReservaDAO();
		HuespedDAO huespedDao = new HuespedDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/persona.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBounds(576, 150, 255, 33);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBounds(576, 217, 255, 33);
		contentPane.add(txtApellido);
		
		JDateChooser txtFechaN = new JDateChooser();
		txtFechaN.setBounds(576, 281, 255, 33);
		contentPane.add(txtFechaN);
		
		JComboBox<?> txtNacionalidad = new JComboBox();
		txtNacionalidad.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"Afganistán ",	"Albania",	"Alemania",	"Andorra",	"Angola",	"Antigua y Barbuda",	"Arabia Saudita ",	"Argelia",	"Argentina ",	"Armenia",	"Australia",	"Austria",	"Azerbaiyán",	"Bahamas ",	"Bahrein",	"Bangladesh",	"Barbados",	"Belarús",	"Bélgica",	"Belice",	"Benin",	"Bhután",	"Bolivia ",	"Bosnia y Herzegovina",	"Botswana",	"Brasil ",	"Brunei Darussalam",	"Bulgaria",	"Burkina Faso",	"Burundi",	"Cabo Verde",	"Camboya",	"Camerún ",	"Canadá ",	"Chad ",	"Chequia",	"Chile",	"China",	"Chipre",	"Colombia",	"Comoras ",	"Congo ",	"Costa Rica",	"Côte d’Ivoire",	"Croacia",	"Cuba",	"Dinamarca",	"Djibouti",	"Dominica",	"Ecuador ",	"Egipto",	"El Salvador",	"Emiratos Árabes Unidos ",	"Eritrea",	"Eslovaquia",	"Eslovenia",	"España",	"Estados Unidos de América ",	"Estonia",	"Eswatini",	"Etiopía",	"Federación de Rusia ",	"Fiji",	"Filipinas",	"Finlandia",	"Francia",	"Gabón ",	"Gambia",	"Georgia",	"Ghana",	"Granada",	"Grecia",	"Guatemala",	"Guinea",	"Guinea Ecuatorial",	"Guinea-Bissau",	"Guyana",	"Haití",	"Honduras",	"Hungría",	"India ",	"Indonesia",	"Irán ",	"Iraq ",	"Irlanda",	"Islandia",	"Islas Cook ",	"Islas Marshall ",	"Islas Salomón ",	"Israel",	"Italia",	"Jamaica",	"Japón ",	"Jordania",	"Kazajstán",	"Kenya",	"Kirguistán",	"Kiribati",	"Kuwait",	"Lesotho",	"Letonia",	"Líbano ",	"Liberia",	"Libia",	"Liechtenstein",	"Lituania",	"Luxemburgo",	"Madagascar",	"Malasia",	"Malawi",	"Maldivas",	"Malí",	"Malta",	"Marruecos",	"Mauricio",	"Mauritania",	"México",	"Micronesia ",	"Mónaco",	"Mongolia",	"Montenegro",	"Mozambique",	"Myanmar",	"Namibia",	"Nauru",	"Nepal",	"Nicaragua",	"Níger ",	"Nigeria",	"Niue",	"Noruega",	"Nueva Zelandia",	"Omán",	"Países Bajos ",	"Pakistán ",	"Palau",	"Panamá",	"Papua Nueva Guinea",	"Paraguay ",	"Perú ",	"Polonia",	"Portugal",	"Qatar",	"Reino Unido de Gran Bretaña e Irlanda del Norte ",	"República Árabe Siria ",	"República Centroafricana ",	"República de Corea ",	"República de Macedonia del Norte ",	"República de Moldova",	"República Democrática del Congo ",	"República Democrática Popular Lao ",	"República Dominicana ",	"República Popular Democrática de Corea",	"República Unida de Tanzanía ",	"Rumania",	"Rwanda",	"Saint Kitts y Nevis",	"Samoa",	"San Marino",	"San Vicente y las Granadinas",	"Santa Lucía",	"Santa Sede ",	"Santo Tomé y Príncipe",	"Senegal ",	"Serbia",	"Seychelles",	"Sierra Leona",	"Singapur",	"Somalia",	"Sri Lanka",	"Sudáfrica",	"Sudán del Sur",	"Sudán ",	"Suecia",	"Suiza",	"Suriname",	"Tailandia",	"Tayikistán",	"Timor-Leste",	"Togo ",	"Tonga",	"Trinidad y Tabago",	"Túnez",	"Turkmenistán",	"Turquía",	"Tuvalu",	"Ucrania",	"Uganda",	"Uruguay ",	"Uzbekistán",	"Vanuatu",	"Venezuela ",	"Viet Nam",	"Yemen ",	"Zambia",	"Zimbabwe"}));		
		txtNacionalidad.setBounds(576, 350, 255, 33);
		contentPane.add(txtNacionalidad);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(578, 125, 253, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(576, 194, 255, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(576, 256, 255, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nacionalidad");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(576, 325, 255, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		lblNewLabel.setBounds(10, 0, 493, 584);
		contentPane.add(lblNewLabel);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					reservaDAO.cancelarReserva(Reserva.id);
					JOptionPane.showMessageDialog(null, "Reserva cancelada:  " + Reserva.id, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					reservas.idGenerado = (int) (Math.random() * 999999) + 1;
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace(System.out);
				}
			}
			
		});
		btnCancelar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(697, 543, 54, 41);
		contentPane.add(btnCancelar);
		
		String pattern = "ddMMyyyy";
		SimpleDateFormat simpledateFormat = new SimpleDateFormat(pattern);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreIngresado = txtNombre.getText();
				String apellidoIngresado = txtApellido.getText();
				java.util.Date date = txtFechaN.getDate();
				String fechaIngresada = simpledateFormat.format(date);
				String nacionalidadIngresada = txtNacionalidad.getSelectedItem().toString();
				String telefonoIngresado = txtTelefono.getText();
				Huesped huesped = new Huesped(nombreIngresado, apellidoIngresado, Integer.valueOf(fechaIngresada), nacionalidadIngresada, Integer.valueOf(telefonoIngresado), Reservas.idGenerado);
				try {
					huespedDao.insertarHuesped(huesped);
					
					Exito exito = new Exito();
					exito.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error: Datos incompletos o incorrectos", "Mensaje", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace(System.out);
				}
			}
		});
		btnGuardar.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/disquete.png")));
		btnGuardar.setBackground(SystemColor.menu);
		btnGuardar.setBounds(616, 543, 54, 41);
		contentPane.add(btnGuardar);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea salir de la aplicacion?", "Alerta", JOptionPane.YES_NO_OPTION);
				if(resp == 0) {
					System.exit(0);
				}
			}
		});
		
		
		btnSalir.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setBackground(SystemColor.menu);
		btnSalir.setBounds(777, 543, 54, 41);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel_1_2 = new JLabel("Teléfono");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(578, 394, 253, 14);
		contentPane.add(lblNewLabel_1_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBounds(576, 419, 255, 33);
		contentPane.add(txtTelefono);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(780, 11, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Registro de Huésped");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_4.setBounds(576, 74, 198, 42);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Número de Reserva");
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(578, 455, 253, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		txtNreserva = new JTextField();
		txtNreserva.setText(String.valueOf(Reserva.id));
		txtNreserva.setEnabled(false);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBounds(576, 480, 255, 33);
		contentPane.add(txtNreserva);
	}
}
