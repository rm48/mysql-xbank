package conta.apresentacao;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import conta.util.LimitaCaracteres;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class ContaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	
	private JTextField tfConta;
	private JTextField tfSenha;
	private JTextField tfValor;
	private MaskFormatter fmtSenha;
	private MaskFormatter fmtValor;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public ContaCliente() {
		Janela();
		tfNome.setDocument(new LimitaCaracteres(30, LimitaCaracteres.TipoEntrada.NOME));
		tfConta.setDocument(new LimitaCaracteres(3, LimitaCaracteres.TipoEntrada.NUMEROINTEIRO));
		tfSenha.setDocument(new LimitaCaracteres(4, LimitaCaracteres.TipoEntrada.NUMEROINTEIRO));
		tfValor.setDocument(new LimitaCaracteres(5, LimitaCaracteres.TipoEntrada.NUMERODECIMAL));
		
	}
	
	public void Janela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lbConta.setBounds(12, 52, 54, 17);
		panel.add(lbConta);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setFont(fonte);
		lbSenha.setBounds(169, 54, 54, 17);
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
		tfNome.setText(",00");
		tfNome.setFont(fonte);
		tfNome.setBounds(75, 10, 233, 32);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		tfConta = new JTextField();
		tfConta.setFont(fonte);
		tfConta.setBounds(75, 52, 58, 25);
		panel.add(tfConta);
		tfConta.setColumns(10);	
		
//		try {
//			fmtSenha = new MaskFormatter("####");
//			fmtValor = new MaskFormatter("###,###.##");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		tfSenha = new JTextField();
		tfSenha.setFont(fonte);
		tfSenha.setColumns(10);
		tfSenha.setBounds(239, 52, 69, 25);
		panel.add(tfSenha);
		
		tfValor = new JTextField();
		tfValor.setFont(fonte);
		tfValor.setColumns(10);
		tfValor.setBounds(74, 137, 96, 24);
		panel.add(tfValor);
		
		JButton btnLogin = new JButton("Acessar");
		btnLogin.setFont(fonte);
		btnLogin.setBounds(333, 12, 100, 24);
		panel.add(btnLogin);
		
		JButton btnSignin = new JButton("Cadastrar");
		btnSignin.setFont(fonte);
		btnSignin.setBounds(333, 54, 100, 24);
		panel.add(btnSignin);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(fonte);
		btnExcluir.setBounds(333, 103, 100, 24);
		panel.add(btnExcluir);		
		
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.setFont(fonte);
		btnTransferir.setBounds(198, 138, 110, 24);
		panel.add(btnTransferir);
		
		JButton btnSacar = new JButton("Sacar");
		btnSacar.setFont(fonte);
		btnSacar.setBounds(74, 174, 96, 24);
		panel.add(btnSacar);
		
		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.setFont(fonte);
		btnDepositar.setBounds(198, 174, 110, 24);
		panel.add(btnDepositar);
		
		JButton btnLogout = new JButton("Sair");
		btnLogout.setFont(fonte);
		btnLogout.setBounds(334, 139, 99, 24);
		panel.add(btnLogout);
		
		JButton btnSaldo = new JButton("Saldo");
		btnSaldo.setFont(fonte);
		btnSaldo.setBounds(333, 175, 100, 24);
		panel.add(btnSaldo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(fonte);
		comboBox.setBounds(75, 100, 233, 26);
		panel.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(fonte);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setEditable(false);
		textArea.setBounds(12, 210, 421, 91);
		panel.add(textArea);	
	}
}
