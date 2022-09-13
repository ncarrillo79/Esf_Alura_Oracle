package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import domain.Huesped;
import factory.ConnectionFactory;

public class HuespedDAO {
	 private static final String SQL_SELECT = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
	 private static final String SQL_INSERT = "INSERT INTO huespedes(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?,?,?,?,?,?)"; 
	 private static final String SQL_DELETE = "DELETE FROM huespedes WHERE ID_RESERVA = ?";
	 private static final String SQL_UPDATE = "UPDATE HUESPEDES SET NOMBRE = ?, apellido = ?,  FECHA_dE_NACIMIENTO = ?,  NACIONALIDAD = ?,  TELEFONO = ? WHERE ID = ?";
	 
	 public List<Huesped> listarHuespedes() throws SQLException{
		 java.sql.Connection con;
			con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			ResultSet rs = null;
			Huesped huesped = null;
			List<Huesped> huespedes = new ArrayList<>();

			try {
				
				Pstmt = con.prepareStatement(SQL_SELECT);
				rs = Pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					 String nombre = rs.getString("nombre");
					 String apellido = rs.getString("apellido");
					 int fechaNacimiento = rs.getInt("fecha_de_nacimiento");
					 String Nacionalidad = rs.getString("Nacionalidad");
					 int telefono = rs.getInt("telefono");
					 int idReserva = rs.getInt("id_reserva");
					huesped = new Huesped(id, nombre, apellido, fechaNacimiento, Nacionalidad, telefono, idReserva);
					huespedes.add(huesped);
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}finally {
				
				rs.close();
				Pstmt.close();
				con.close();
			}
		 
		 return huespedes;
	 }
	 
	 public int insertarHuesped(Huesped huesped) throws SQLException {
		 	java.sql.Connection con;
			con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			int registros = 0;
			try {
				Pstmt = con.prepareStatement(SQL_INSERT);
				Pstmt.setString(1, huesped.getNombre());
				Pstmt.setString(2, huesped.getApellido());
				Pstmt.setInt(3, huesped.getFechaNacimiento());
				Pstmt.setString(4, huesped.getNacionalidad());
				Pstmt.setInt(5, huesped.getTelefono());
				Pstmt.setInt(6, huesped.getIdReserva());
				
				registros = Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Datos incompletos o incorrectos", "Mensaje", JOptionPane.WARNING_MESSAGE);
				e.printStackTrace(System.out);
			}finally {
				Pstmt.close();
				con.close();
			}
		 return registros;
	 }
	 

	 public void eliminarHuesped(int idReserva) throws SQLException {
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
	 public List<Huesped> listaAvanzada(String data) throws SQLException{
		 java.sql.Connection con;
			con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			ResultSet rs = null;
			Huesped huesped = null;
			List<Huesped> huespedes = new ArrayList<>();
			try {
				Pstmt = con.prepareStatement("SELECT * FROM HUESPEDES WHERE APELLIDO LIKE '%" + data +  "%'");
				rs = Pstmt.executeQuery();				
				while (rs.next()) {
					int id = rs.getInt("id");
					 String nombre = rs.getString("nombre");
					 String apellido = rs.getString("apellido");
					 int fechaNacimiento = rs.getInt("fecha_de_nacimiento");
					 String Nacionalidad = rs.getString("Nacionalidad");
					 int telefono = rs.getInt("telefono");
					 int idReserva = rs.getInt("id_reserva");
					huesped = new Huesped(id, nombre, apellido, fechaNacimiento, Nacionalidad, telefono, idReserva);
					huespedes.add(huesped);
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}finally {
				
				rs.close();
				Pstmt.close();
				con.close();
			}
			return huespedes;
	 }
	 public void actualizarHuesped(String nombre, String apellido, int fecha, String nacionalidad, int telefono, int id) throws SQLException {
		 java.sql.Connection con;
			con = new ConnectionFactory().recuperaConexion();
			PreparedStatement Pstmt = null;
			try {
				Pstmt = con.prepareStatement(SQL_UPDATE);
				Pstmt.setString(1, nombre);
				Pstmt.setString(2, apellido);
				Pstmt.setInt(3, fecha);
				Pstmt.setString(4, nacionalidad);
				Pstmt.setInt(5, telefono);
				Pstmt.setInt(6, id);
				Pstmt.executeUpdate(); //ACTUALIZA EL ESTADO EN LA BASE DE DATOS (INSERT/UPDATE/DELETE)	
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}finally {
				Pstmt.close();
				con.close();
			}
	 }
	 
	 
	 
	 
}
