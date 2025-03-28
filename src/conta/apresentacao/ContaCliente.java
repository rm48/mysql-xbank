package conta.apresentacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import conta.modelo.DAO.ClienteDAO;
import conta.modelo.beans.Cliente;
import conta.util.LimitaCaracteres;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;	
	private JTextField tfConta;
	private JTextField tfSenha;
	private JTextField tfValor;
	
	private JButton btnSignup;
	private JButton btnLogin;
	private JButton btnExcluir; 
	private JButton btnTransferir;
	private JButton btnSacar;
	private JButton btnDepositar;
	private JButton btnLogout ;
	private JButton btnFechar;
	private JButton btnVoltar;
	
	private JComboBox comboBox;
	private JTextArea textArea;
	
	Cliente cliente;
	Cliente clienteLogado;
	ClienteDAO dao;
	Random bonus = new Random();
	boolean logado = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {				
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					ContaCliente frame = new ContaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ContaCliente() {
		
		Janela();
		tfNome.setDocument(new LimitaCaracteres(30, LimitaCaracteres.TipoEntrada.NOME));
		tfConta.setDocument(new LimitaCaracteres(3, LimitaCaracteres.TipoEntrada.NUMEROINTEIRO));
//		tfConta.setEnabled(false);
		tfSenha.setDocument(new LimitaCaracteres(4, LimitaCaracteres.TipoEntrada.NUMEROINTEIRO));
//		tfSenha.setEnabled(false);
		tfValor.setDocument(new LimitaCaracteres(5, LimitaCaracteres.TipoEntrada.NUMERODECIMAL));
//		tfValor.setEnabled(false);
		
		btnLogin.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnTransferir.setEnabled(false);
		btnSacar.setEnabled(false);
		btnDepositar.setEnabled(false);
		btnLogout.setEnabled(false);
		btnFechar.setEnabled(false);
//		btnSignup.setEnabled(false);
		
		popularComboBox();
	}
	
public ContaCliente(int id, int linha) {
		
		Janela();
		tfNome.setDocument(new LimitaCaracteres(30, LimitaCaracteres.TipoEntrada.NOME));
		//tfConta.setText(Integer.toString(id));
		//tfConta.setText(""+id);
		tfSenha.setDocument(new LimitaCaracteres(4, LimitaCaracteres.TipoEntrada.NUMEROINTEIRO));
		tfValor.setDocument(new LimitaCaracteres(5, LimitaCaracteres.TipoEntrada.NUMERODECIMAL));
		btnSignup.setEnabled(false);
		//mostrarDados(id);
		popularComboBox();
		comboBox.setSelectedIndex(linha);
		btnTransferir.setEnabled(false);
		btnSacar.setEnabled(false);
	
		btnLogout.setEnabled(false);
		btnFechar.setEnabled(false);
}
	
	public void Janela() {
//		if (logado)
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		else
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbHead = new JLabel("X-BANK");
		lbHead.setFont(new Font("Courier 10 Pitch", Font.BOLD, 38));
		lbHead.setBounds(152, 0, 151, 48);
		contentPane.add(lbHead);
		
		Font fonte = new Font("Liberation Sans", 0, 15);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setFont(fonte);
		panel.setBounds(12, 60, 445, 313);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbNome = new JLabel("Nome");
		lbNome.setFont(fonte);
		lbNome.setBounds(12, 12, 54, 17);
		panel.add(lbNome);
		
		JLabel lbConta = new JLabel("Conta");
		lbConta.setFont(fonte);
		lbConta.setBounds(12, 56, 54, 17);
		panel.add(lbConta);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setFont(fonte);
		lbSenha.setBounds(151, 56, 54, 17);
		panel.add(lbSenha);
		
		JLabel lbClientes = new JLabel("Clientes");
		lbClientes.setFont(fonte);
		lbClientes.setBounds(12, 105, 54, 17);
		panel.add(lbClientes);
		
		JLabel lbValorT = new JLabel("Valor");
		lbValorT.setFont(fonte);
		lbValorT.setBounds(12, 141, 43, 17);
		panel.add(lbValorT);
		
		tfNome = new JTextField();
		tfNome.setFont(fonte);
		tfNome.setBounds(75, 10, 205, 32);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		tfConta = new JTextField();
		tfConta.setFont(fonte);
		tfConta.setBounds(75, 52, 58, 25);
		panel.add(tfConta);
		tfConta.setColumns(10);
		tfConta.setEditable(false);
				
		tfSenha = new JTextField();
		tfSenha.setFont(fonte);
		tfSenha.setColumns(10);
		tfSenha.setBounds(223, 52, 57, 25);
		panel.add(tfSenha);
		
		tfValor = new JTextField();
		tfValor.setFont(fonte);
		tfValor.setColumns(10);
		tfValor.setBounds(74, 137, 85, 24);
		panel.add(tfValor);
		
		btnLogin = new JButton("Acessar");
		btnLogin.setFont(fonte);
		btnLogin.setBounds(305, 12, 128, 24);
		panel.add(btnLogin);
		btnLogin.addActionListener(new btnLoginListener());
		
		btnSignup = new JButton("Cadastrar");
		btnSignup.setFont(fonte);
		btnSignup.setBounds(305, 54, 128, 24);
		panel.add(btnSignup);
		btnSignup.addActionListener(new btnSignupListener());
		
		
		btnExcluir = new JButton("Excluir conta");
		btnExcluir.setFont(fonte);
		btnExcluir.setBounds(305, 103, 128, 24);
		panel.add(btnExcluir);	
		btnExcluir.addActionListener(new btnExcluirListener());
		
		btnTransferir = new JButton("Transferir");
		btnTransferir.setFont(fonte);
		btnTransferir.setBounds(181, 138, 99, 24);
		panel.add(btnTransferir);
		
		btnSacar = new JButton("Sacar");
		btnSacar.setFont(fonte);
		btnSacar.setBounds(74, 174, 85, 24);
		panel.add(btnSacar);
		
		btnDepositar = new JButton("Depositar");
		btnDepositar.setFont(fonte);
		btnDepositar.setBounds(181, 174, 99, 24);
		panel.add(btnDepositar);
		btnDepositar.addActionListener(new btnDepositarListener());
		
		btnLogout = new JButton("Sair da conta");
		btnLogout.setFont(fonte);
		btnLogout.setBounds(306, 139, 127, 24);
		panel.add(btnLogout);
		btnLogout.addActionListener(new btnLogoutListener());
		
		btnFechar = new JButton("Sair e Fechar");
		btnFechar.setFont(fonte);
		btnFechar.setBounds(305, 175, 128, 24);
		panel.add(btnFechar);
		btnFechar.addActionListener(new btnFecharListener());
		
		btnVoltar = new JButton("<");
		btnVoltar.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		btnVoltar.setBounds(12, 173, 37, 24);
		panel.add(btnVoltar);
		btnVoltar.addActionListener(new btnVoltarListener());
		
		comboBox = new JComboBox();
		comboBox.setFont(fonte);
		comboBox.setBounds(75, 100, 205, 26);
		panel.add(comboBox);
		comboBox.addActionListener(new comboBoxListener());
		
		
		
		textArea = new JTextArea();
		textArea.setFont(fonte);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setEditable(false);
		textArea.setBounds(12, 210, 421, 91);
		panel.add(textArea);
		textArea.setText("Cash: "+ FrmLista.getCash() );
		
		
				
	}
	
	public class comboBoxListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String opcao = (String) comboBox.getSelectedItem();			
			int conta =	Integer.parseInt(opcao.substring(0,3).trim());
			mostrarDados (conta);
			btnSignup.setEnabled(false);
			btnDepositar.setEnabled(true);
			if (!logado) {
				btnExcluir.setEnabled(true);
				btnLogin.setEnabled(true);
			}
			
		}
		
	}
	
	public class btnDepositarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String numConta = (String) comboBox.getSelectedItem();			
			int contaDestino =	Integer.parseInt(numConta.substring(0,3).trim());
			//if (conta > 0) {
			Cliente favorecido = new ClienteDAO().PesquisarPorId(contaDestino);
			
			if (!"".equals(tfValor.getText())) {
				double valor = Double.parseDouble(tfValor.getText());
				double cash = Double.parseDouble(FrmLista.getCash());
				cash -=valor;
				if (cash >=0) {
					double saldoDestino = Double.parseDouble(favorecido.getSaldo());
					saldoDestino+=valor;
					favorecido.setSaldo(Double.toString(saldoDestino));
					FrmLista.setCash(Double.toString(cash));
					dao = new ClienteDAO();	
					dao.depositar(favorecido);
					if(logado)
						 {
						clienteLogado = new ClienteDAO().PesquisarPorId(clienteLogado.getId());
						double total2;
						double saldo2 = Double.parseDouble(clienteLogado.getSaldo());
						double credito2 = Double.parseDouble(clienteLogado.getCredito());
						//double cash = Double.parseDouble(FrmLista.getCash());
						total2 = saldo2 + credito2 + cash;
						textArea.setText("Cash: "+ cash + "\t\tConta: "+ clienteLogado.getId() 
										+"\nSaldo: " +saldo2+"\t\tCliente: "+ clienteLogado.getNome()
										+"\nCrédito: "+ credito2
										+"\nTotal: " + total2);
					} else
						textArea.setText("Cash: "+ cash);
					JOptionPane.showMessageDialog(null, "Depositado na conta: "+favorecido.getNome());
				}
				else 
					JOptionPane.showMessageDialog(null, "Digite um valor menor");
			}
			else
				JOptionPane.showMessageDialog(null, "Digite um valor");
			
		}		
	}
	
	public class btnExcluirListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String[]opcoes = {"Sim", "Nao"};
			String numConta = (String) comboBox.getSelectedItem();			
			int conta =	Integer.parseInt(numConta.substring(0,3).trim());
			//if (conta > 0) {
//				int id_cliente = (int)comboBox.getValueAt
						//table.getValueAt(linhaSelecionada, 0);
			int opcao = JOptionPane.showOptionDialog(rootPane, "Deseja excluir este registro?","Confirmação",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null, opcoes, opcoes[1]);
			if (JOptionPane.OK_OPTION == opcao) {
				new ClienteDAO().excluir(conta);
				FrmLista.atualizarGrade();
				popularComboBox();
				numConta = (String) comboBox.getSelectedItem();
				conta =	Integer.parseInt(numConta.substring(0,3).trim());
				mostrarDados(conta);
				JOptionPane.showMessageDialog(null, "Registro excluido");	
			} else {
				JOptionPane.showMessageDialog(null, "Cancelado");
			}
			setVisible(false);
			dispose();
		}
		
	}
	
	public class btnLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String numConta = (String) comboBox.getSelectedItem();			
			int conta =	Integer.parseInt(numConta.substring(0,3).trim());
			clienteLogado = new ClienteDAO().PesquisarPorId(conta);
			if (clienteLogado.getSenha().equals(tfSenha.getText())) {
				logado = true;
				btnLogin.setEnabled(false);
				btnLogout.setEnabled(true);
				btnExcluir.setEnabled(false);
				btnTransferir.setEnabled(true);
				btnSacar.setEnabled(true);
				btnFechar.setEnabled(true);
				double total;
				double saldo = Double.parseDouble(clienteLogado.getSaldo());
				double credito = Double.parseDouble(clienteLogado.getCredito());
				double cash = Double.parseDouble(FrmLista.getCash());
				total = saldo + credito + cash;
				textArea.setText("Cash: "+ FrmLista.getCash()+ "\t\tConta: "+ clienteLogado.getId() 
								+"\nSaldo: " +saldo+"\t\tCliente: "+ clienteLogado.getNome()
								+"\nCrédito: "+ credito
								+"\nTotal: " + total);
				JOptionPane.showMessageDialog(null, "Logado");
			}
			else
				JOptionPane.showMessageDialog(null, "Senha incorreta");
			
		}
		
	}
	public class btnLogoutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cliente = null;
			btnLogin.setEnabled(true);
			btnLogout.setEnabled(false);
			btnExcluir.setEnabled(true);
			btnTransferir.setEnabled(false);
			btnSacar.setEnabled(false);
			btnFechar.setEnabled(false);
			System.out.println(cliente);
			logado = false;
			textArea.setText("Cash: "+ FrmLista.getCash());
			JOptionPane.showMessageDialog(null, "Saiu.");
			
		}
		
	}
	public class btnSignupListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String opcao = null;
			String nome = null;
			nome = tfNome.getText();
			if (!"".equals(nome)) {
				cliente = new Cliente();			
				cliente.setNome(tfNome.getText());
				cliente.setSenha(tfSenha.getText());
				cliente.setSaldo("0");
				cliente.setCredito(Integer.toString(bonus.nextInt(30)*5000 + 50000));
				boolean quatro = true;
				while (quatro) {
					if (tfSenha.getText().matches("[0-9]{4,4}")){
						quatro = false;
					}
					else
					{
						opcao=(JOptionPane.showInputDialog("Digite uma senha de quatro números: "));
						try {
							if (opcao.length()==4) {
								tfSenha.setText(opcao);
								cliente.setSenha(tfSenha.getText());
							}
						} catch (Exception e1) {
							System.out.println("Cancelou");
							break;
						}
					}
				}
				if(!quatro) {
					dao = new ClienteDAO();	
					boolean resultado = dao.incluir(cliente);
					if(resultado) {
						JOptionPane.showMessageDialog(null, "O cliente foi cadastrado.");
						FrmLista.atualizarGrade();
					}
					else 
						JOptionPane.showMessageDialog(null, "Não foi possível fazer o cadastro!");
				}
				else 
					JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
			}
			setVisible(false);
			dispose();
		}		
	}
	
	public class btnFecharListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cliente = null;
//			btnLogin.setEnabled(true);
//			btnLogout.setEnabled(false);
//			btnExcluir.setEnabled(true);
//			btnTransferir.setEnabled(false);
//			btnSacar.setEnabled(false);
//			btnFechar.setEnabled(false);
			System.out.println(cliente);
			logado = false;
//			JOptionPane.showMessageDialog(null, "Saiu.");
			setVisible(false);
			dispose();
			
		}
		
	}
	
	private class btnVoltarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cliente = null;
			System.out.println(cliente);
			logado = false;
			setVisible(false);
			dispose();			
		}
		
	}
	
	public void mostrarDados (int id) {
		cliente = new ClienteDAO().PesquisarPorId(id);
		tfConta.setText(""+cliente.getId());
		tfNome.setText(cliente.getNome());
		
	}
	
	public void popularComboBox() {
		List<String> strList = new ArrayList<>();
		strList = new ClienteDAO().popular();
		DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(strList.toArray());
		comboBox.setModel(defaultComboBox);
	}
}
