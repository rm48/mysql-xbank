package conta.apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.JTextField;

import conta.modelo.DAO.ClienteDAO;
import conta.util.ModeloGrade;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class FrmLista extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JPanel pnTabela;
	private JComboBox cbCampos;
	private JTextField txtValor;

	String [] colunas = {"id", "Nome", "Saldo", "Credito"};
	
	Object [][] dados = {
			{"1","Julio","976675","100000"},
			{"2","Elias","88955","200000"},
			{"3","Jairo","23930","150000"},
	};
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
					FrmLista frame = new FrmLista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLista() {
		janela();
		tabela();
		atualizarGrade();
	}
	public void janela() {
		setTitle("Lista de clientes");
		setBounds(100, 100, 677, 483);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(12, 12, 653, 64);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtValor = new JTextField();
		txtValor.setBounds(207, 28, 180, 24);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		cbCampos = new JComboBox();
		cbCampos.setBounds(30, 28, 144, 24);
		cbCampos.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		panel.add(cbCampos);
		
		JLabel lblCampo = new JLabel("Campo");
		lblCampo.setForeground(Color.WHITE);
		lblCampo.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		lblCampo.setBounds(30, 11, 60, 17);
		panel.add(lblCampo);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setForeground(Color.WHITE);
		lblValor.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		lblValor.setBounds(208, 10, 60, 17);
		panel.add(lblValor);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		btnPesquisar.setBounds(445, 27, 105, 27);
		panel.add(btnPesquisar);
		
		pnTabela = new JPanel();
		pnTabela.setBounds(12, 88, 653, 252);
		getContentPane().add(pnTabela);
		pnTabela.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		JPanel pnBotoes = new JPanel();
		pnBotoes.setBounds(12, 352, 653, 91);
		getContentPane().add(pnBotoes);
		pnBotoes.setLayout(null);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		btnEditar.setBounds(35, 32, 105, 27);
		pnBotoes.add(btnEditar);
	}
	public void tabela(){
			table = new JTable(new ModeloGrade());
//			table = new JTable(dados, colunas);
			JScrollPane rolagem = new JScrollPane(table);
			pnTabela.add(rolagem);
	}
	public void atualizarGrade() {
		ResultSet rs = new ClienteDAO().carregarGrade();
		table.setModel(new ModeloGrade(rs, new String[] {"Código","Nome"}));
		table.getColumnModel().getColumn(0).setMaxWidth(60);
	}
}
