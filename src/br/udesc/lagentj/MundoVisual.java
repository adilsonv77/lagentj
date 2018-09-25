/**
 * @license
 * Learning with AgentJ
 *
 * Copyright 2018 Google Inc.
 * https://sites.google.com/site/adilsonv77
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.udesc.lagentj;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.net.BindException;
import java.net.ServerSocket;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.text.DefaultCaret;

import br.udesc.lagentj.exceptions.MundoException;
import br.udesc.lagentj.objetivos.Objetivo;
import br.udesc.lagentj.objetivos.ObjetivoConfiguracao;
import br.udesc.lagentj.suporte.Exercicio;
import br.udesc.lagentj.suporte.ExercicioFactory;
import br.udesc.lagentj.suporte.LoadImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Adilson Vahldick 
 */
@SuppressWarnings("serial")
public class MundoVisual extends JFrame {

	private static class MySlider extends BasicSliderUI {

	    private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, 
	            BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);
	    
		private Image tartaruga;
		private Image lebre;

	    public MySlider(JSlider b) {
	       super(b);
	       b.setBackground(Color.white);
	       b.setFocusable(false);
	       
	       this.tartaruga = LoadImage.getInstance().getIcon("imagens/tartaruga.png").getImage();
	       this.lebre = LoadImage.getInstance().getIcon("imagens/lebre.png").getImage();
	    }

	    @Override
	    public void paint(Graphics g, JComponent c) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        
	        g.drawImage(tartaruga, 0, 20, null);
	        g.drawImage(lebre, slider.getWidth()-25, 20, null);
	        super.paint(g, c);
	    }

	    @Override
	    protected Dimension getThumbSize() {
	        return new Dimension(12, 16);
	    }

	    @Override
	    public void paintTrack(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(stroke);
	        g2d.setPaint(Color.BLACK);
	        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
	            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, 
	                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
	        } else {
	            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y, 
	                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
	        }
	        g2d.setStroke(old);
	    }

	    @Override
	    public void paintThumb(Graphics g) { // eh o botao que o usuario clica para acertar a posicao
	        Graphics2D g2d = (Graphics2D) g;
	        int x1 = thumbRect.x + 2;
	        int x2 = thumbRect.x + thumbRect.width - 2;
	        int width = thumbRect.width - 4;
	        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
	        GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
	        shape.moveTo(x1, topY);
	        shape.lineTo(x2, topY);
	        shape.lineTo((x1 + x2) / 2, topY + width);
	        shape.closePath();
	        g2d.setPaint(Color.black); // new Color(81, 83, 186));
	        g2d.fill(shape);
	        Stroke old = g2d.getStroke();
	        g2d.setStroke(new BasicStroke(2f));
	        g2d.setPaint(new Color(125, 125, 125));
	        g2d.draw(shape);
	        g2d.setStroke(old);
	    }

	}
	
	public static void proxMundo() {
		if (euMesmo.mundoAtual < euMesmo.nomesArquivosXML.length-1) {
			euMesmo.mundoAtual++;
			euMesmo.executarProxMundo = true;
		}
	}
	
	
	public static void proxMundo(int indiceMundo) {
		if (indiceMundo >= euMesmo.nomesArquivosXML.length) {
			throw new MundoException("\315ndice do mundo fora da faixa.");
		} else {
			euMesmo.mundoAtual = indiceMundo;
			euMesmo.executarProxMundo = true;
		}
	}

	public static int getMundo() {
		return euMesmo.mundoAtual;
	}

	public static <T> void setAtributo(String nome, T valor) {
		atributos.put(nome, valor);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getAtributo(String nome) {
		return (T) atributos.get(nome);
	}

	public static boolean temAtributo(String nome) {
		return atributos.containsKey(nome);
	}

	public static void removerAtributo(String nome) {
		atributos.remove(nome);
	}
	
	private MundoAgenteJ mundoAgenteJ;

	private MundoVisual(Exercicio exercicio, String autor) throws Exception {
                gerarObjetivos(exercicio.getConfiguracoes());
                
		executou = false;
		euMesmo = this;
                
                exercicio.setMv(euMesmo);
		
		setIconImage(LoadImage.getInstance().getIcon("imagens/agentej-icone.gif").getImage());
		
		mundoAgenteJ = new MundoAgenteJ(exercicio);
                mundoAgenteJ.setMv(euMesmo);
		mundoAgenteJ.addDisseramListener(new DisseramListener(){

			@Override
			public void disse(String texto) {
				console.setText((new StringBuilder(String
						.valueOf(console.getText()))).append(texto).append("\n")
						.toString());
			}

			@Override
			public void limpar() {
				console.setText("");
			}});

		mundoAgenteJ.addFinalizouExecucaoListener(new FinalizouExecucaoListener(){

			@Override
			public void finalizouExecucao() {
				if (executarProxMundo) {
					executarProxMundo = false;
					try {
						executarInterno(ExercicioFactory.create(nomesArquivosXML[mundoAtual], clazz));
					} catch (Exception e) {
						throw new MundoException(e.getMessage());
					}
				} else {
					jbExecutar.setEnabled(true);
					jbParar.setEnabled(false);
					jbRenovar.setEnabled(true);
				}
			}});
		
		initComponents(exercicio, autor);
	}

	public MundoVisual(String autor, Exercicio exercicio, Class<?> clazz,
			String nomesArquivosXML[]) throws Exception {
		this(exercicio, autor);
		this.nomesArquivosXML = nomesArquivosXML;
		this.clazz = clazz;
	}

	private static JButton createSimpleButton(String image, String tooltip) {
		  JButton button = new JButton();
		  button.setIcon(LoadImage.getInstance().getIcon(image));
		  button.setToolTipText(tooltip);
		  button.setForeground(Color.BLACK);
		  button.setBackground(Color.WHITE);
		  button.setFocusable(false);
		  button.setBorder(null);
		  return button;
	}
	
	private static JSlider createSimpleSlider() {
		JSlider slider = new JSlider();
		slider.setUI(new MySlider(slider));
		return slider;
	}	
	
	private void initComponents(Exercicio exercicio, String autor) {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(300, 100));
		String title = "Mundo do AgenteJ v " + AgenteJ.VERSAO; 
		if (autor != null) {
			title = title + " ["+autor+"]";
		}
		setTitle(title);
		
		JPanel jp = getControle(exercicio); 
		getContentPane().add(jp, "North");
                
                
                checkList = new JCheckList();
		checkList.setTitle("Objetivos:");
		for (Objetivo obj: objetivos){
                    checkList.addItem(obj.getDescricao());
                }		
		getContentPane().add(checkList, "East");
		
		getContentPane().add(mundoAgenteJ, "Center");
		console = new JTextArea();
		JScrollPane scroll = new JScrollPane(console);
		console.setEditable(false);
		console.setRows(5);
		
		DefaultCaret caret = (DefaultCaret)console.getCaret();
                caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
	    
		getContentPane().add(scroll, "South");
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				mundoAgenteJ.parar();
			}

		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	


	private JPanel getControle(final Exercicio exercicio) {
		JPanel jp = new JPanel(new BorderLayout());
		jp.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));

		slider = createSimpleSlider();
		jp.add(slider, BorderLayout.WEST);
		

		slider.setMaximum(1000);
		slider.setInverted(true);
		slider.setMinimumSize(new Dimension(10, 10));
		slider.setValue(mundoAgenteJ.getTempoEspera());
		slider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent ev) {
				mundoAgenteJ.setTempoEspera(slider.getValue());
				mundoAgenteJ.requestFocus();
			}

		});

		JPanel jpBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jpBotoes.setBackground(Color.white);
		jp.add(jpBotoes, BorderLayout.CENTER);

		jbExecutar = createSimpleButton("imagens/iconplay.png", "Executar");
		jpBotoes.add(jbExecutar);
		ActionListener action = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				mundoAtual = 0;
				executarProxMundo = false;
				novaSequencia(exercicio);
				executarInterno(exercicio);
			}

		};
		jbExecutar.addActionListener(action);
		jbRenovar = createSimpleButton("imagens/iconreloadcode.png", "Novo");
		jpBotoes.add(jbRenovar);
		jbRenovar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				mundoAgenteJ.parar();
				novaSequencia(exercicio);
				try {
					mundoAgenteJ.setExercicio(exercicio);
					mundoAgenteJ.reiniciar();
					pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
				executou = false;
				mundoAgenteJ.requestFocus();
			}

		});
		jbParar = createSimpleButton("imagens/iconstop.png", "Parar");
		jbParar.setEnabled(false);
		jpBotoes.add(jbParar);
		jbParar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				mundoAgenteJ.parar();
                                checkList.uncheckAllItems();
				jbExecutar.setEnabled(true);
				jbParar.setEnabled(false);
			}

		});
		return jp;
	}

	private void novaSequencia(Exercicio exercicio) {
		atributos.clear();
                checkList.uncheckAllItems();
	}

	private void executarInterno(Exercicio exercicio) {
		if (executou)
			try {
				mundoAgenteJ.setExercicio(exercicio);
				mundoAgenteJ.reiniciar();
				pack();
			} catch (Exception e) {
				e.printStackTrace();
			}
		habilitarBotoesExecucao();
		mundoAgenteJ.executar();
		executou = true;
		mundoAgenteJ.requestFocus();
	}

	private void habilitarBotoesExecucao() {
		jbExecutar.setEnabled(false);
		jbParar.setEnabled(true);
		jbRenovar.setEnabled(false);
	}

	public static void iniciar(String nomeArquivoXML) {
		iniciar(nomeArquivoXML, null, null, false);
	}

	public static void iniciarImediatamente(String nomeArquivoXML) {
		iniciar(nomeArquivoXML, null, null, true);
	}

	public static void iniciar(String nomeArquivoXML, String autor) {
		iniciar(nomeArquivoXML, null, autor, false);
	}
	
	public static void iniciar(String nomeArquivoXML, Class<?> clazz, final String autor, final boolean runImmediatelly) {
		try {
			int socket = MundoVisual.socket;
			if (autor != null) {
				String[] as = autor.split("|");
				if (as.length > 2)
					socket = Integer.parseInt(as[1]);
			}
			ServerSocket server = new ServerSocket(socket);
			final Exercicio exercicio = ExercicioFactory.create(nomeArquivoXML,clazz);
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						
						MundoVisual mundoVisual = new MundoVisual(exercicio, autor);
						if (runImmediatelly) {
							mundoVisual.mundoAgenteJ.executar();
							mundoVisual.mundoAgenteJ.requestFocus();
							mundoVisual.habilitarBotoesExecucao();
						}
						
					} catch (ClassNotFoundException classE) {
						JOptionPane.showMessageDialog(null, (new StringBuilder(
								"Voc\352 precisa criar uma classe de nome "))
								.append(classE.getLocalizedMessage())
								.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
		} catch (BindException be) {
			System.err.println("Existem outros mundos abertos. Feche as execu\347\365es anteriores.");
		} catch (Exception e) {
			System.out.println("Problemas no carregamento do Exerc\355cio");
			e.printStackTrace();
		}

	}
	
	public static void iniciar(String nomeArquivoXML, Class<?> clazz) {
		iniciar(nomeArquivoXML, clazz, null, false);
	}

	public static void iniciar(String nomeArquivoXML, Class<?> clazz, int socket) {
		MundoVisual.socket = socket;
		iniciar(nomeArquivoXML, clazz, null, false);
	}

	public static void iniciarImediatamente(String nomeArquivoXML, Class<?> clazz) {
		iniciar(nomeArquivoXML, clazz, null, true);
	}


	
	public static void iniciarSequencia(Class<?> clazz, String... nomesArquivosXML) {
		iniciarSequencia(null, clazz, nomesArquivosXML);
	}
	
	public static void iniciarSequencia(final String autor, Class<?> clazz, String... nomesArquivosXML) {
		try {
			final Exercicio exercicio = ExercicioFactory.create(
					nomesArquivosXML[0], clazz);
			final String arquivosXML[] = nomesArquivosXML;
			final Class<?> classe = clazz;
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						new MundoVisual(autor, exercicio, classe, arquivosXML);
					} catch (ClassNotFoundException classE) {
						JOptionPane.showMessageDialog(null, (new StringBuilder(
								"Voc\352 precisa criar uma classe de nome "))
								.append(classE.getLocalizedMessage())
								.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
		} catch (Exception e) {
			System.out.println("Problemas no carregamento do Exerc\355cio");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		if (args.length > 0)
			iniciar(args[0]);
		throw new Exception("Faltou informar o nome do arquivo do enunciado.");
	}

	private JTextArea console;
	private boolean executou;
	private String nomesArquivosXML[];
	private int mundoAtual;
	private boolean executarProxMundo;
	private Class<?> clazz;
	private static MundoVisual euMesmo;
	private static HashMap<String, Object> atributos = new HashMap<String, Object>();
	//private JLabel jlEnunciado;
	private JButton jbExecutar;
	private JButton jbParar;
	private JSlider slider;
	private JButton jbRenovar;
        private JCheckList checkList;
	private static int socket = 57391;
        private List<Objetivo> objetivos = new ArrayList();
        
        
        
        private void gerarObjetivos(List<ObjetivoConfiguracao> objetivoConfiguracoes) throws Exception {
		objetivos.clear();
		for (ObjetivoConfiguracao oc: objetivoConfiguracoes) {
			Objetivo obj = oc.gerar();
			obj.setMundo(this);
			objetivos.add( obj );
		}
	}
        
        public void checkObjetivo(int i){
            checkList.checkItem(i);
        }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }
    
    public void fim(){
        if (checkList.isAllGoalsAchieved()){
            JOptionPane.showMessageDialog(null, "Parabéns! Você concluiu todos os objetivos.");
        } else {
            JOptionPane.showMessageDialog(null, "Você não conseguiu completar todos os objetivos. Tente novamente.");
        }
    }
    
    public void verificarObjetivos(String tipo, Object opcoes) {
        for (int i = 0; i < objetivos.size(); i++) {
            Objetivo objetivo = objetivos.get(i);
            if (objetivo.isCompleto(tipo, opcoes)) {
                checkObjetivo(i);
            }
        }
    }
        
        
        
        

}