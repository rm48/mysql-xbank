package conta.apresentacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.border.BevelBorder;

public class TelaJogo extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBotoes;
    private JPanel pnNumeros;
	private final int range = 60, apostaMinima = 6, apostaMaxima = 15;
    int palpite = 0,bola = 0;
    int[] numerosMega, acertos, sorteados;
    private boolean proxConc = false;   
    private Random random = new Random();
    private Icon[] bluenIcons;
	private Icon[] blueIcons; 
	private Icon[] redIcons;
	private Icon[] goldIcons;
    private JButton btnNumeros[];
    
    private JButton btnApostar;
    private JButton btnLimpar;
    private JButton btnChances;
    private JButton btnSair;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				try {				
//					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//						if ("Nimbus".equals(info.getName())) {
//							javax.swing.UIManager.setLookAndFeel(info.getClassName());
//							break;
//						}
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Liberation Sans", Font.PLAIN, 15)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					TelaJogo frame = new TelaJogo();
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
	public TelaJogo() {
		Janela();
		
		
	}
	public void Janela() {
		Font fonte = new Font("Liberation Sans", 0, 15);
		setTitle("x-bank-quina 0.4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		pnBotoes = new JPanel();
		pnBotoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnBotoes.setBounds(12, 12, 709, 76);
		contentPane.add(pnBotoes);
		
		btnApostar = new JButton("Apostar");
		btnApostar.setIcon(new ImageIcon(TelaJogo.class.getResource("/conta/img/jogar.gif")));
		btnApostar.setFont(fonte);
		btnApostar.setBounds(12, 13, 152, 51);
		btnApostar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				apostar();
			}
		});

		btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(TelaJogo.class.getResource("/conta/img/limpar.gif")));
		btnLimpar.setFont(fonte);
		btnLimpar.setBounds(187, 13, 152, 51);
		btnLimpar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();			
			}
		});
		
		btnChances = new JButton("Chances");
		btnChances.setIcon(new ImageIcon(TelaJogo.class.getResource("/conta/img/aosorteio.png")));
		btnChances.setFont(fonte);
		btnChances.setBounds(359, 13, 152, 51);
		btnChances.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				probabilidades();			
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(TelaJogo.class.getResource("/conta/img/sair.gif")));
		btnSair.setFont(fonte);
		btnSair.setBounds(536, 13, 152, 51);
		btnSair.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		pnBotoes.setLayout(null);
		pnBotoes.add(btnApostar);
		pnBotoes.add(btnLimpar);
		pnBotoes.add(btnChances);
		pnBotoes.add(btnSair);
		
		pnNumeros = new JPanel();
		pnNumeros.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnNumeros.setBounds(12, 100, 709, 421);
		contentPane.add(pnNumeros);
		pnNumeros.setLayout(new GridLayout(6,10)); 

		acertos = new int[apostaMinima];
        sorteados = new int[apostaMinima];           
        numerosMega = new int[range];
        bluenIcons = new Icon[range];
        blueIcons  = new Icon[range];
        redIcons = new Icon[range];
        goldIcons = new Icon[range];
		btnNumeros = new JButton[range];
		for (int i = 0; i < range; i++) {
			blueIcons[ i ] = new ImageIcon(getClass().getResource("/conta/img/blue/"+ ( i + 1 ) + ".gif"));
	        bluenIcons [ i ] = new ImageIcon(getClass().getResource( "/conta/img/bluen/"+ ( i + 1 ) + ".gif"));
	        redIcons [ i ] = new ImageIcon(getClass().getResource( "/conta/img/red/"+ ( i + 1 ) + ".gif"));
	        goldIcons [ i ] = new ImageIcon(getClass().getResource( "/conta/img/gold/"+ ( i + 1 ) + ".gif"));
	        this.btnNumeros[ i ] = new JButton();
	        this.btnNumeros[ i ].setName(Integer.toString( i ));
	        btnNumeros[ i ].setIcon(bluenIcons[ i ]); 
	        btnNumeros[ i ].setActionCommand(btnNumeros[ i ].getName());                          
	        btnNumeros[ i ].addActionListener(this);
	        pnNumeros.add( btnNumeros[ i ] );
		}
		
	}
	private void apostar() {		
			int contaAcertos =0;
            //concursoAtual++;
            if (palpite < apostaMinima)
            	JOptionPane.showMessageDialog(pnNumeros, "A aposta mínima é de 6 números.");
            else{
            for (int i = 0; i < apostaMinima; i++) {
                do {
                    bola = random.nextInt(range);
                } while (numerosMega[bola] % 2 == 1);
                if (numerosMega[bola] == 2) {
                    numerosMega[bola] = 3;
                    acertos[contaAcertos] = bola + 1;                      
                    contaAcertos++;
                } else {
                    numerosMega[bola] = 1;
                }
                sorteados[i] = bola + 1;
                 }
            pintar();
            }		
		}

		
		private void marcar(int i){
	        if(!proxConc){
//	          reset
	                    if (numerosMega[i] == 2) {
	                        btnNumeros[i].setIcon(bluenIcons[i]);
	                        numerosMega[i] = 0;
	                        palpite--;
	                    }
	                    else if (palpite >= apostaMaxima)
	                        JOptionPane.showMessageDialog(pnNumeros, "Atingiu aposta máxima.");
//	          aposta
	                    else {   
	                       btnNumeros[i].setIcon(blueIcons[i]);
	                        numerosMega[i] = 2;
	                        palpite++;                
	                    }
	            }
	            else
	                {
	                  limpar();
	                  //proxConc = false;
	            }
	        }

		private void pintar() {
			if(!proxConc){
		         pnNumeros.setBackground(Color.lightGray);
		        for (int i = 0; i < numerosMega.length; i++) {                           
		                            if (numerosMega[i] == 2) {
		                                numerosMega[i] = 0;
		                            } else if (numerosMega[i] == 3) {
		                                btnNumeros[i].setIcon(goldIcons[i]);
		                                numerosMega[i] = 0;                                                            
		                            } else if (numerosMega[i] == 1) {
		                                btnNumeros[i].setIcon(redIcons[i]);
		                                numerosMega[i] = 0;     
		                            } else {
		                                btnNumeros[i].setEnabled(false);
		                            }
		                        }
		        proxConc = true;
		        }
		     else
		            {
		              limpar();
		              //proxConc = false;
		        }			
		}

		private void limpar() {
			 pnNumeros.setBackground(Color.darkGray);
             for (int i = 0; i < numerosMega.length; i++) {
            	 numerosMega[i] = 0;
            	 btnNumeros[i].setIcon(bluenIcons[i]);
            	 btnNumeros[i].setEnabled(true);
             }         
             palpite = 0;
             proxConc = false;		
		}
		
		private void probabilidades(){
	        JOptionPane.showMessageDialog(contentPane, "Conforme a aposta, uma chance em:\n" +
	        		"\n" +
	        		"Núm.----Quina--------Quadra-----terno----Duque\n" +                    
	        		" 5:    24.040.016         64.106      866        36\n" +
	        		" 6:      4.006.669         21.658      445        25\n" +
	        		" 7:      1.144.763           9.409      261        18\n" +
	        		" 8:         429.286           4.770      168        14\n" +
	        		" 9:         190.794           2.687      115        12\n" +
	        		"10:          95.396           1.635       82          9\n" +
	        		"11:          52.035           1.056       62          8\n" +
	        		"12:          30.354              714       48          7\n" +
	        		"13:          18.679              502       38          6\n" +
	        		"14:          12.008              364       31         5,8\n" +
	        		"15:            8.005              271       25         5,2\n\n"+
	        		"\t    \t    \t    x-Bank-quina-4.0 - 03/2025 \n\t    \t    \t    reinaldo589@hotmail.com","Probabilidades",1);        		    
	   }

		@Override
		public void actionPerformed(ActionEvent e) {
			int i=Integer.parseInt(e.getActionCommand());        
            marcar(i);			
		}	
}
	

