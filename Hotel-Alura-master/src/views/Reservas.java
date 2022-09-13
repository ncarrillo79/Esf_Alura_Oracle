package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import data.ReservaDAO;
import domain.Huesped;
import domain.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class Reservas extends JFrame {
	public static int idGenerado = (int) (Math.random() * 999999) + 1;

	private JPanel contentPane;
	private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	public Reservas() {
		setTitle("Sistema de Reservas - Hotel-Alura");

		Reserva reserva = new Reserva();
		ReservaDAO reservaDAO = new ReservaDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245,245,245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JDateChooser txtFechaE = new JDateChooser();
		txtFechaE.getJCalendar().setMinSelectableDate(new Date());
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);
		
		JDateChooser txtFechaS = new JDateChooser();
		txtFechaS.getJCalendar().setMinSelectableDate(new Date());
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);
		
		
		//REVISAR, INVOCA 3 VECES A LA FUNCION 
		
		txtFechaS.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					txtValor.setText(reservaDAO.comparacionDias(txtFechaS.getDate(), txtFechaE.getDate()));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		txtValor = new JTextField();

		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);
		
		JComboBox txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"}));
		panel.add(txtFormaPago);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		String pattern = "ddMMyyyy";
		SimpleDateFormat simpledateFormat = new SimpleDateFormat(pattern);
		
		
		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.util.Date date = txtFechaE.getDate();
					String newdateE = simpledateFormat.format(date);
					java.util.Date date2 = txtFechaS.getDate();
					String newdateS = simpledateFormat.format(date2);
					String valorIndicado = txtValor.getText();
					String FormaElegida = txtFormaPago.getSelectedItem().toString();
					reserva.setId(idGenerado);
					reserva.setFechaEntrada(Integer.valueOf(newdateE));
					reserva.setFechaSalida(Integer.valueOf(newdateS));
					reserva.setValor(Integer.valueOf(valorIndicado));
					reserva.setFormadePago(FormaElegida);
					reservaDAO.insertarReserva(reserva);
					RegistroHuesped huesped = new RegistroHuesped();
					huesped.setVisible(true);
					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error datos incompletos o incorrectos", "Mensaje", JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(120, 423, 169, 56);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(new Color(65,105,225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
		
		
		
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
