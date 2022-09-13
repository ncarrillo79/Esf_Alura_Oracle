package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Reserva;
import factory.ConnectionFactory;

public class ReservaDAO {

	private static final String SQL_SELECT = "SELECT id, fecha_Entrada, fecha_Salida, Valor, Forma_de_Pago FROM reservas";
	private static final String SQL_INSERT = "INSERT INTO reservas(id, fecha_Entrada, fecha_Salida, Valor, Forma_de_Pago) VALUES (?,?,?,?,?)";
	 private static final String SQL_DELETE = "DELETE FROM reservas WHERE ID = ?";
	 private static final String SQL_UPDATE = "UPDATE RESERVAS SET FECHA_ENTRADA = ?, FECHA_SALIDA = ?, VALOR = ?, FORMA_DE_PAGO = ? WHERE ID = ?";
	 
	 public List<Reserva> listarReservas() throws SQLException{
		 	java.sql.Connection con;
			con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			ResultSet rs = null;
			Reserva reserva = null;
			List<Reserva> reservas = new ArrayList<>();

			try {
				
				Pstmt = con.prepareStatement(SQL_SELECT);
				rs = Pstmt.executeQuery();
				while (rs.next()) {

					int id = rs.getInt("id");
					int fechaEntrada = rs.getInt("fechaEntrada");
					int fechaSalida = rs.getInt("fechaSalida");
					int Valor = rs.getInt("Valor");
					String FormadePago = rs.getString("FormadePago");
					reserva = new Reserva(id, fechaEntrada, fechaSalida, Valor, FormadePago);
					reservas.add(reserva);
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}finally {
				
				rs.close();
				Pstmt.close();
				con.close();
			}
			return reservas;
	 	}
	 
	 public int insertarReserva(Reserva reserva) throws SQLException {
		 	java.sql.Connection con;
			con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			int registros = 0;
			try {
				Pstmt = con.prepareStatement(SQL_INSERT);
				Pstmt.setInt(1, reserva.getId());
				Pstmt.setInt(2, reserva.getFechaEntrada());
				Pstmt.setInt(3, reserva.getFechaSalida());
				Pstmt.setInt(4, reserva.getValor());
				Pstmt.setString(5, reserva.getFormadePago());
				
				registros = Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}finally {
				Pstmt.close();
				con.close();
			}
		 return registros;
	 }
	 
		public String comparacionDias(Date date, Date date2) {
			int comparacion;
			int precioDia = 350;
			String pattern = "D";
			SimpleDateFormat simpledateFormat = new SimpleDateFormat(pattern);
			String Days2 = simpledateFormat.format(date2);
			String Days = simpledateFormat.format(date);
			comparacion = (Integer.valueOf(Days) - Integer.valueOf(Days2)) * precioDia;
			if(comparacion > 0) {
				return String.valueOf(comparacion);
			}else {
				return null;
			}
			
		}
	 
		 public void cancelarReserva(int id) throws SQLException {
			 java.sql.Connection con;
				con = new ConnectionFactory().recuperaConexion();
				PreparedStatement Pstmt = null;
				try {
					Pstmt = con.prepareStatement(SQL_DELETE);
					Pstmt.setInt(1, id);
					Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}finally {
					Pstmt.close();
					con.close();
				}
		 }
		 public void eliminarRegistro(int idReserva) throws SQLException {
			 java.sql.Connection con;
				con = new ConnectionFactory().recuperaConexion();
				PreparedStatement Pstmt = null;
				try {
					Pstmt = con.prepareStatement(SQL_DELETE);
					Pstmt.setInt(1, idReserva);
					Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}finally {
					Pstmt.close();
					con.close();
				}
		 }
		 
		 public List<Reserva> listarAvanzadaReservas(String data) throws SQLException{
			 	java.sql.Connection con;
				con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
				PreparedStatement Pstmt = null;
				ResultSet rs = null;
				Reserva reserva = null;
				int idIngresado = Integer.valueOf(data);
				
				List<Reserva> reservas = new ArrayList<>();

				try {

					Pstmt = con.prepareStatement("SELECT * FROM RESERVAS WHERE ID = '"+ idIngresado +"' ");	
					rs = Pstmt.executeQuery();
					while (rs.next()) {
						int id = rs.getInt("id");
						int fechaEntrada = rs.getInt("Fecha_Entrada");
						int fechaSalida = rs.getInt("Fecha_Salida");
						int Valor = rs.getInt("Valor");
						String FormadePago = rs.getString("Forma_de_Pago");
						reserva = new Reserva(id, fechaEntrada, fechaSalida, Valor, FormadePago);
						reservas.add(reserva);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Error: Datos incompletos o incorrectos", "Mensaje", JOptionPane.WARNING_MESSAGE);
					System.out.println(e);
//					e.printStackTrace(System.out);
				}finally {
					
					rs.close();
					Pstmt.close();
					con.close();
				}
				return reservas;
		 	}
		 
		 public void actualizarReserva(int id, int fechaE, int fechaS, int valor, String FormadePago) throws SQLException {
			 java.sql.Connection con;
				con = new ConnectionFactory().recuperaConexion();
				PreparedStatement Pstmt = null;
				try {
					Pstmt = con.prepareStatement(SQL_UPDATE);
					Pstmt.setInt(1, fechaE);
					Pstmt.setInt(2, fechaS);
					Pstmt.setInt(3, valor);
					Pstmt.setString(4, FormadePago);
					Pstmt.setInt(5, id);
					Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}finally {
					Pstmt.close();
					con.close();
				}
		 }
		 
		 
}
