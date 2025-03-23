package conta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class Conexao {
	public static Connection conexao = null;
	public static ResourceBundle configuracao = ResourceBundle.getBundle(ConstantesBanco.CAMINHOARQUIVO);
	public static Connection getConexao() {
		try {
			Class.forName(configuracao.getString(ConstantesBanco.BANCO_DRIVER));
			conexao = DriverManager.getConnection(configuracao.getString(ConstantesBanco.BANCO_URL),
					configuracao.getString(ConstantesBanco.BANCO_USUARIO),
					configuracao.getString(ConstantesBanco.BANCO_SENHA));					
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
		return conexao;		
	}
}
