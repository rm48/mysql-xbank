package conta.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conta.modelo.beans.Cliente;
import conta.util.ConexaoBD;

public class ClienteDAO {
	private Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	public ClienteDAO() {
		this.con = new ConexaoBD().getConexao();
	}
	
	public void incluir(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome,saldo,credito,senha) value(?,?,?,?)";
		try {
			int i=0;
			stmt = con.prepareStatement(sql);
			stmt.setString(++i,cliente.getNome());	
			stmt.setString(++i,cliente.getSaldo());
			stmt.setString(++i,cliente.getCredito());
			stmt.setString(++i,cliente.getSenha());
			stmt.execute();
			con.close();
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
			
	}
	
	public void alterar (Cliente cliente) {
		String sql = "UPDATE cliente SET nome=?,saldo=?,credito=?,senha=? where id_cliente=? ";
		try {
			int i=0;
			stmt = con.prepareStatement(sql);
			stmt.setString(++i,cliente.getNome());
			stmt.setString(++i,cliente.getSaldo());
			stmt.setString(++i,cliente.getCredito());
			stmt.setString(++i,cliente.getSenha());
			stmt.setInt(++i,cliente.getId());
			stmt.execute();		
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void excluir (int id) {
		try {
			stmt = con.prepareStatement("DELETE FROM cliente WHERE id_Cliente=?");
			stmt.setInt(1,  id);;
			stmt.execute();			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Cliente> listar() {
		List<Cliente> lst = new ArrayList<Cliente>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSaldo(rs.getString(3));
				cliente.setCredito(rs.getString(4));
				cliente.setSenha(rs.getString(5));
				
				lst.add(cliente);
			}
			stmt.close();
			rs.close();
			con.close();	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lst;		
	}
	
	public Cliente PesquisarPorId(int id) {
		Cliente cliente = new Cliente();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?",
			ResultSet.TYPE_SCROLL_INSENSITIVE, 
            ResultSet.CONCUR_UPDATABLE);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			rs.first();

			cliente.setId(rs.getInt(1));
			cliente.setNome(rs.getString(2));
			cliente.setSaldo(rs.getString(3));
			cliente.setCredito(rs.getString(4));
			cliente.setSenha(rs.getString(5));
			
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return cliente;
	}
	
	public List<Cliente> pesquisarPorNome(String valor) {
		List<Cliente> lst = new ArrayList<Cliente>();

		try {
			stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
			stmt.setString(1, '%' + valor + '%');
			rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSaldo(rs.getString(3));
				cliente.setCredito(rs.getString(4));
				cliente.setSenha(rs.getString(5));
				lst.add(cliente);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lst;
	}
}
