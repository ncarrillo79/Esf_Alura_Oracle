package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.HuespedDAO;
import data.ReservaDAO;
import domain.Huesped;
import domain.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JButton btnBuscar;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Busqueda() throws SQLException {
		setTitle("Panel de Búsquedas - Hotel-Alura");
		HuespedDAO huespedDAO = new HuespedDAO();
		ReservaDAO reservasDAO = new ReservaDAO();
		TbhuespedesNoeditable tbH = new TbhuespedesNoeditable();
		TbreservasNoeditable tbR = new TbreservasNoeditable();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);
		
		tbHuespedes = new JTable(tbH);
		tbHuespedes.setForeground(new Color(0, 0, 102));
		tbHuespedes.setRowHeight(20);
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
//		JScrollPane js = new JScrollPane(tbHuespedes);
		DefaultTableModel modelHuespedes = (DefaultTableModel) tbHuespedes.getModel();
		modelHuespedes.addColumn("ID");
		modelHuespedes.addColumn("Nombre");
		modelHuespedes.addColumn("Apellido");		
		modelHuespedes.addColumn("Fecha de nacimiento");
		modelHuespedes.addColumn("NACIONALIDAD");
		modelHuespedes.addColumn("TELÉFONO");
		modelHuespedes.addColumn("ID_RESERVA");
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);
//		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), js, null);
		
		
		JTable tbReservas = new JTable(tbR);
		tbReservas.setForeground(new Color(0, 0, 153));
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		tbReservas.setBounds(10, 205, 760, 280);
		JScrollPane jsr = new JScrollPane(tbReservas);	
		
		DefaultTableModel modelReservas = (DefaultTableModel) tbReservas.getModel();
		modelReservas.addColumn("ID");
		modelReservas.addColumn("Fecha Entrada");
		modelReservas.addColumn("Fecha Salida");		
		modelReservas.addColumn("Valor");
		modelReservas.addColumn("Forma de Pago");
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);
		//--------------------------------------------BUSCAR-----------------------------------------------------------------
		
		btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String Dato = txtBuscar.getText();
					if(tbHuespedes.isVisible()){
						try {
							modelHuespedes.getDataVector().removeAllElements();
							tbHuespedes.updateUI();
							modelHuespedes.addRow(new Object[] {"ID", "Nombre", "Apellido", "Nacimiento", "Nacionalidad", "Telefono", "Id_Reserva"});
							List<Huesped> huespedes =  huespedDAO.listaAvanzada(Dato);							
							huespedes.forEach(huesped -> modelHuespedes.addRow(new Object[] {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad()
									,huesped.getTelefono(), huesped.getIdReserva()
							}) );
							
						} catch (SQLException e2) {
							e2.printStackTrace(System.out);
						}
						
					}else if(tbReservas.isVisible()) {
						try {
							modelReservas.getDataVector().removeAllElements();
							tbReservas.updateUI();
							modelReservas.addRow(new Object[] {"ID", "Fecha de Entrada", "Fecha de Salida", "Valor", "Forma de pago"});
							List<Reserva> reservas = reservasDAO.listarAvanzadaReservas(Dato);
							reservas.forEach(reserva -> modelReservas.addRow(new Object[] {
									reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getFormadePago()
								}));
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "Error: Debe ingresar el numero de reserva", "Mensaje", JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (SQLException e1) {					
					e1.printStackTrace(System.out);
				}
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);		
		//------------------------------ EDITAR PARA HUESPEDES -------------		
		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);		
		btnEditar.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tbHuespedes.isVisible()) {
					int row = tbHuespedes.getSelectedRow();
					int idSelected = (int) tbHuespedes.getValueAt(row, 0);
					String nombreSelected = tbHuespedes.getValueAt(row, 1).toString();
					String apellidoSelected = tbHuespedes.getValueAt(row, 2).toString();
					int fechaSelected = Integer.parseInt(tbHuespedes.getValueAt(row, 3).toString());
					String paisSelected = tbHuespedes.getValueAt(row, 4).toString();
					int telSelected = Integer.parseInt(tbHuespedes.getValueAt(row, 5).toString());
					try {
						huespedDAO.actualizarHuesped(nombreSelected, apellidoSelected, fechaSelected, paisSelected, telSelected, idSelected);
						JOptionPane.showMessageDialog(null, "Registro editado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}else if(tbReservas.isVisible()) {
					int row = tbReservas.getSelectedRow();
					int idReserva = (int) tbReservas.getValueAt(row, 0);
					int fechaentrada = (int) tbReservas.getValueAt(row, 1);
					int fechaSalida = (int) tbReservas.getValueAt(row, 2);
					int valor = Integer.parseInt(tbReservas.getValueAt(row, 3).toString());
					String formadepago = tbReservas.getValueAt(row, 4).toString();
					try {
						reservasDAO.actualizarReserva(idReserva, fechaentrada, fechaSalida, valor, formadepago);
						JOptionPane.showMessageDialog(null, "Registro editado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		//------------------------------ EDITAR PARA RESERVAS -------------
		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);
		//-------------------------------------------------------ELIMINAR-------------------------------------------------------
		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(tbHuespedes.isVisible()) {
					int row = tbHuespedes.getSelectedRow();
					int reservaSelected = (int) tbHuespedes.getValueAt(row, 6);
					try {
						int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro?", "Ventana de confirmacion", JOptionPane.YES_NO_OPTION);
						if(resp == 0) {
							huespedDAO.eliminarHuesped(reservaSelected);
							reservasDAO.eliminarRegistro(reservaSelected);
							modelHuespedes.removeRow(tbHuespedes.getSelectedRow());	
							JOptionPane.showMessageDialog(null, "Registro eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
						}	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
				else if(tbReservas.isVisible()) {
					int row = tbReservas.getSelectedRow();
					int reservaSelected = (int) tbReservas.getValueAt(row, 0);
					try {
						int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar el registro?", "Ventana de confirmacion", JOptionPane.YES_NO_OPTION);
						if(resp == 0) {
							huespedDAO.eliminarHuesped(reservaSelected);
							reservasDAO.eliminarRegistro(reservaSelected);
							modelReservas.removeRow(tbReservas.getSelectedRow());
							JOptionPane.showMessageDialog(null, "Registro eliminado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
						}	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		//-------------------------------------------------------ELIMINAR-------------------------------------------------------
		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la operación?", "Alerta", JOptionPane.YES_NO_OPTION);
				if(resp == 0) {
					MenuUsuario usuario = new MenuUsuario();
					usuario.setVisible(true);
					dispose();	
				}
								
			}
		});		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);	
		JLabel lblNewLabel = new JLabel("Apellido de Huésped o Numero de Reserva");
		lblNewLabel.setForeground(new Color(102, 204, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(535, 29, 349, 45);
		contentPane.add(lblNewLabel);
		setResizable(false);
	}
	
	public class TbhuespedesNoeditable extends DefaultTableModel{
		   private static final long serialVersionUID = 1L;
		   
		   public boolean isCellEditable(int row, int column) {
			   if(row == 0) {
				   return false;
			   }
			   if (column == 0) {
					return false;
				}
				if (column == (this.getColumnCount() - 1)) {
					return false;
				}
				return true;		      
		   }
		}
	public class TbreservasNoeditable extends DefaultTableModel{

		   private static final long serialVersionUID = 1L;
		   
		   public boolean isCellEditable(int row, int column) {
			   if(row == 0) {
				   return false;
			   }
				if (column == 0 || column == 1 || column == 2) {
					return false;
				}
				return true;		      
		   }
		}
}

