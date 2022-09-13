package factory;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	public java.sql.Connection recuperaConexion() throws SQLException {
		
		return DriverManager.getConnection("jdbc:mysql://localhost/huespedes?useTimeZone=true&serverTimeZone=UTC", "root", "leon2022");
		
	}
	public void close(ResultSet rs) throws SQLException {
		rs.close();

	}
	public void close(PreparedStatement Pstmnt) throws SQLException {
		Pstmnt.close();
	}
	public void close(Statement stmn) throws SQLException {
		stmn.close();

	}
}
