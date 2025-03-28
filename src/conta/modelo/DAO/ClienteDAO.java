package conta.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conta.modelo.beans.Cliente;
import conta.util.Conexao;

public class ClienteDAO {
	private Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	public ClienteDAO() {
		this.con = Conexao.getConexao();
	}
	
	public boolean incluir(Cliente cliente) {
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
			return true;
		} catch (SQLException e) {
		//throw new RuntimeException(e);
			return false;
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
	
	public List<String> popular() {
		List<String> list = new ArrayList<String>();
		try {
		String sql = "SELECT id_cliente, nome FROM cliente";
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
			
			while(rs.next()) {
				String linha = rs.getString("id_cliente");
				linha = linha + "  ";
				linha = linha + rs.getString("nome");
				list.add(linha);
			}
			
				stmt.close();
			
			rs.close();
			con.close();	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;		
	}
	
	public List<Cliente> listar2() {
		List<Cliente> lst = new ArrayList<Cliente>();
		
		try {
			stmt = con.prepareStatement("SELECT id_cliente, nome FROM cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				
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
				cliente.setSaldo(rs.getString(3));
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
	public ResultSet carregarGrade() {
		try {
			stmt = con.prepareStatement("SELECT id_cliente, nome FROM cliente");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rs;
	}
	
	public List<String> nomeCampos(){
		List<String> campos = new ArrayList<>();
		try {
			stmt = con.prepareStatement("SELECT * FROM cliente LIMIT 1");
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1; i<=2; i++)//rsmd.getColumnCount(); i++)
				campos.add(rsmd.getColumnName(i));			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return campos;	
	}

	public ResultSet pesquisa(String campo, String valor) {
		String sql = "SELECT id_cliente, nome FROM cliente WHERE " + campo + " like '%" + valor + "%' ";
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return rs;
	}
}

