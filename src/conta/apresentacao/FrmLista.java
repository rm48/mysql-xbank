package conta.apresentacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;

import conta.modelo.DAO.ClienteDAO;
import conta.util.ModeloGrade;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class FrmLista extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static JTable table;
	private JPanel pnTabela;
	private JComboBox cbCampo;
	private JTextField txtValor;
	private static String cash = null;
	Random bonus = new Random();

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

	public FrmLista() {
		janela();
		tabela();
		atualizarGrade();
		populaCombo();
		bonus();
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
		
		cbCampo = new JComboBox();
		cbCampo.setBounds(30, 28, 144, 24);
		cbCampo.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
		panel.add(cbCampo);
		
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
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pesquisa(""+cbCampo.getSelectedItem(), txtValor.getText());				
			}
			});
			
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
		btnEditar.addActionListener(new btnEditarListener());
	}
	public void tabela(){
			table = new JTable(new ModeloGrade());
			//table.setForeground(Color.BLUE);
			table.setFont(new Font("Liberation Sans", Font.PLAIN, 15));
			table.setToolTipText("Clique em uma linha para selecionar");
			table.setCursor(new Cursor(Cursor.HAND_CURSOR));
			JScrollPane rolagem = new JScrollPane(table);
			pnTabela.add(rolagem);
	}
	public static void atualizarGrade() {
		ResultSet rs = new ClienteDAO().carregarGrade();
		table.setModel(new ModeloGrade(rs, new String[] {"Código","Nome"}));
		table.getColumnModel().getColumn(0).setMaxWidth(60);
	}
	public void populaCombo() {
		List<String> campos = new ClienteDAO().nomeCampos();
		DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbCampo.getModel();
		for (String campo: campos)
			dcm.addElement(campo);
	}
	
	public void bonus() {
		cash = Integer.toString(bonus.nextInt(30)*5000 + 50000);
	}
	
	
	public static String getCash() {
		return cash;
	}

	public static void setCash(String cash) {
		FrmLista.cash = cash;
	}

	public void pesquisa(String campo, String valor) {
		ResultSet rs = new ClienteDAO().pesquisa(campo, valor);
		table.setModel(new ModeloGrade(rs, new String[] {"Código","Nome"}));
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}
	
	public class btnEditarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//ContaCliente cli = new ContaCliente();
			int linhaSelecionada = -1;
			linhaSelecionada = table.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int id_cliente = (int)table.getValueAt(linhaSelecionada, 0);
				ContaCliente cli = new ContaCliente(id_cliente,linhaSelecionada);
				cli.setVisible(true);
			}
			else {
				ContaCliente cli = new ContaCliente();
				cli.setVisible(true);
			}
//			cli.addWindowListener(new WindowAdapter() {
//				public void windowClosed(WindowEvent e) {
//					atualizarGrade();
//				}
//			});
//			
			
		}
	}
}
