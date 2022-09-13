package user_aplication;

import java.sql.SQLException;

import factory.ConnectionFactory;

public class Connection {
	public static void main(String[] args) throws SQLException {
		java.sql.Connection con;
		con = (java.sql.Connection) new ConnectionFactory().recuperaConexion();
		
		
		System.out.println("Cerrando la conexion");
		
		((java.sql.Connection) con).close();
		
	}
	
}
