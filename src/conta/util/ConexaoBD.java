package conta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {
	public static Connection conexao = null;
	public static Connection getConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/siscliente?useTimezone=true&serverTimezone=UTC", "admin", "12345");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;		
	}
	public static void main(String[] args ) {
		new ConexaoBD().getConexao();
		JOptionPane.showMessageDialog(null, "Conectado");	
	}
}
