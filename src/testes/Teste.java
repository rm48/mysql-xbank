package testes;

import java.util.List;

import conta.modelo.DAO.ClienteDAO;
import conta.modelo.beans.Cliente;

public class Teste {
	public static void main(String[] args) {
		
		/* INCLUIR */
		Cliente c = new Cliente();
		c.setNome("Buiu Sebastião");
		c.setSenha("1234");
		c.setSaldo("00");
		c.setCredito("2978");	
		ClienteDAO dao = new ClienteDAO();
		dao.incluir(c);
		System.out.println("Incluído.");
		
		
		/* ALTERAR 
		Cliente c = new Cliente();
		c.setId(5);	
		c.setNome("Milo Maracutaia");
		c.setSaldo("000");
		c.setCredito("5000");
		c.setSenha("1234");
		ClienteDAO dao = new ClienteDAO();
		dao.alterar(c);	
		System.out.println("Alterado.");
		*/
		
		/* EXCLUIR 
		Cliente c = new Cliente();
		c.setId(3);	
		ClienteDAO dao = new ClienteDAO();
		dao.excluir(3);
		System.out.println("Excluído.");	
		*/
		
		/* LISTAR */
		ClienteDAO dao2 = new ClienteDAO();
		List<Cliente> lst = dao2.listar();
//		System.out.println("Id  Cliente\t\t  Saldo");
		for(Cliente cliente : lst) {		
//			System.out.println(cliente.getId()+" | "+cliente.getNome()+ "\t| "+cliente.getSaldo());
			System.out.println(cliente);
		}
		
		
		/*	PESQUISAR ID
		Cliente c = new Cliente();
		ClienteDAO dao2 = new ClienteDAO();
		c = dao2.PesquisarPorId(2);
		System.out.println(c.getId()+" | "+c.getNome()+ " | "+c.getSaldo())
		 */
		
		/*	PESQUISAR NOME 
		ClienteDAO dao2 = new ClienteDAO();
		List<Cliente> lst = dao2.pesquisarPorNome("pa");
		for(Cliente c : lst) {
			System.out.println(c.getId()+" | "+c.getNome()+ " | "+c.getSaldo());
		}
		*/
	}
}
