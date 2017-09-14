import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import say.swing.JFontChooser;
import java.awt.image.BufferedImage;

public class Janela extends JFrame {
	protected static final long serialVersionUID = 1L;

	protected JButton btnPonto = new JButton("Ponto"), btnLinha = new JButton("Linha"),
			btnCirculo = new JButton("Circulo"), btnElipse = new JButton("Elipse"),
			btnQuadrado = new JButton("Quadrado"), btnRetangulo = new JButton("Retangulo"),
			btnPoligono = new JButton("Pol�gono"), btnPoligonoI = new JButton("Pol�gono Irr."),
			btnFonte = new JButton("Fonte"), btnTexto = new JButton("Texto"), btnCores = new JButton("Cor de Contorno"),
			btnCorFundo = new JButton("Cor de Fundo"), btnAbrir = new JButton("Abrir"),
			btnSalvar = new JButton("Salvar"), btnApagar = new JButton("Apagar"), btnSair = new JButton("Sair"),
			btnSelecionar = new JButton("Selecionar"), btnMover = new JButton("Mover"),
			btnAproximar = new JButton("Aproximar"), btnLimpar = new JButton("Limpar");

	protected Font fonteAtual = new Font("MS Mincho", Font.PLAIN, 12);

	protected JFontChooser fonte = new JFontChooser();

	protected MeuJPanel pnlDesenho = new MeuJPanel();

	protected JLabel statusBar1 = new JLabel("Mensagem:"), statusBar2 = new JLabel("Coordenada:");

	protected boolean esperaPonto, esperaInicioReta, esperaFimReta, esperaInicioCirculo, esperaFimCirculo,
			esperaInicioElipse, esperaFimElipse, esperaInicioQuadrado, esperaFimQuadrado, esperaInicioRetangulo,
			esperaFimRetangulo, esperaInicioTexto, esperaFimTexto, esperaInicioPoligono, esperaFimPoligono,
			esperaInicioPoligonoI, esperaFimPoligonoI, esperaDrag, esperaInicioPoligonoII, esperaSelecionar,
			esperaMover, esperaMover2, esperaApagar, esperaApagar2, esperaAproximar, salvo = true;

	protected int raio, raio2, lado, lado2, largura, altura, inicioX, inicioY, ladosPoligono, tPoligono, selecionado,
			movendo = -1;

	protected int nPontos = 0;

	protected double circunferencia, teta;

	protected int[] pontosX = new int[10], pontosY = new int[10];

	protected Color corAtual = Color.BLACK, corFundo = Color.WHITE;
	protected Ponto p1, p2;

	protected String classe;

	protected JTextField texto = new JTextField();
	protected Vector<Figura> figuras = new Vector<Figura>();
	protected Vector<Shape> shapes = new Vector<Shape>();

	public Janela() {
		super("Editor Gr�fico");

		// CARREGANDO AS IMAGENS PARA OS BOT�ES

		try {
			Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
			btnPonto.setIcon(new ImageIcon(btnPontoImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo ponto.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
			btnLinha.setIcon(new ImageIcon(btnLinhaImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo linha.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
			btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo circulo.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
			btnElipse.setIcon(new ImageIcon(btnElipseImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo elipse.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnFonteImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
			btnFonte.setIcon(new ImageIcon(btnFonteImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivos quadrado.png n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
			btnCores.setIcon(new ImageIcon(btnCoresImg));
			btnCorFundo.setIcon(new ImageIcon(btnCoresImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo cores.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
			btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo abrir.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
			btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo salvar.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
			btnApagar.setIcon(new ImageIcon(btnApagarImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo apagar.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		try {
			Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
			btnSair.setIcon(new ImageIcon(btnSairImg));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo sair.jpg n�o foi encontrado", "Arquivo de imagem ausente",
					JOptionPane.WARNING_MESSAGE);
		}

		// LISTENERS PARA OS BOT�ES

		btnAbrir.addActionListener(new Abrir());
		btnSalvar.addActionListener(new Salvar());
		btnPonto.addActionListener(new DesenhoDePonto());
		btnLinha.addActionListener(new DesenhoDeReta());
		btnCirculo.addActionListener(new DesenhoDeCirculo());
		btnElipse.addActionListener(new DesenhoDeElipse());
		btnQuadrado.addActionListener(new DesenhoQuadrado());
		btnRetangulo.addActionListener(new DesenhoDeRetangulo());
		btnFonte.addActionListener(new Fontes());
		btnTexto.addActionListener(new cTexto());
		btnCores.addActionListener(new Cores());
		btnCorFundo.addActionListener(new CorFundo());
		btnPoligono.addActionListener(new DesenhoPoligono());
		btnPoligonoI.addActionListener(new DesenhoPoligonoI());
		btnSelecionar.addActionListener(new Selecionar());
		btnMover.addActionListener(new Mover());
		btnApagar.addActionListener(new Apagar());
		btnLimpar.addActionListener(new Limpar());
		btnAproximar.addActionListener(new Aproximar());

		// CONSTRUINDO O LAYOUT

		// BOT�ES USAM O FLOW LAYOUT

		JPanel pnlBotoes = new JPanel();
		pnlBotoes.setLayout(new WrapLayout());

		pnlBotoes.add(btnAbrir);
		pnlBotoes.add(btnSalvar);
		pnlBotoes.add(btnPonto);
		pnlBotoes.add(btnLinha);
		pnlBotoes.add(btnCirculo);
		pnlBotoes.add(btnElipse);
		pnlBotoes.add(btnQuadrado);
		pnlBotoes.add(btnRetangulo);
		pnlBotoes.add(btnPoligono);
		pnlBotoes.add(btnPoligonoI);
		pnlBotoes.add(btnFonte);
		pnlBotoes.add(btnTexto);
		pnlBotoes.add(btnSelecionar);
		pnlBotoes.add(btnMover);
		pnlBotoes.add(btnAproximar);
		pnlBotoes.add(btnCores);
		pnlBotoes.add(btnCorFundo);
		pnlBotoes.add(btnApagar);
		pnlBotoes.add(btnLimpar);
		pnlBotoes.add(btnSair);

		// O RODAP� FICA COMO GRID DE UMA LINHA E DUAS COLUNAS
		JPanel pnlStatus = new JPanel();
		GridLayout grdStatus = new GridLayout(1, 2);
		pnlStatus.setLayout(grdStatus);

		pnlStatus.add(statusBar1);
		pnlStatus.add(statusBar2);

		Container cntForm = this.getContentPane();
		cntForm.setLayout(new BorderLayout());
		cntForm.add(pnlBotoes, BorderLayout.NORTH);
		cntForm.add(pnlDesenho, BorderLayout.CENTER);
		cntForm.add(pnlStatus, BorderLayout.SOUTH);

		this.addWindowListener(new FechamentoDeJanela());

		this.setSize(700, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener

	{
		int x, y;
		Dimension Dimensao = Toolkit.getDefaultToolkit().getScreenSize();

		private BufferedImage Buffered_da_Imagem = new BufferedImage((int) Dimensao.getWidth(),
				(int) Dimensao.getHeight(), BufferedImage.TYPE_INT_RGB);

		private BufferedImage Buffered_da_Reta = new BufferedImage((int) Dimensao.getWidth(),
				(int) Dimensao.getHeight(), BufferedImage.TYPE_INT_RGB);

		public MeuJPanel() {
			super();

			Graphics g_Imagem = Buffered_da_Imagem.createGraphics();
			g_Imagem.setColor(Color.WHITE);
			g_Imagem.fillRect(0, 0, Buffered_da_Imagem.getWidth(), Buffered_da_Imagem.getHeight());
			g_Imagem.dispose();
			this.addMouseListener(this);
			this.addMouseMotionListener(this);
			this.addKeyListener(this);

		}

		public void paint(Graphics g) {
			super.paintComponent(g);

			for (int i = 0; i < figuras.size(); i++)
				figuras.get(i).torneSeVisivel(g);
		}

		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (SwingUtilities.isRightMouseButton(e)) {
				if (esperaFimPoligonoI) {

					for (int i = 0; i < nPontos - 1; i++) {
						figuras.remove(figuras.size() - 1);
					}

					figuras.add(new PoligonoIrr(pontosX, pontosY, nPontos, corAtual, corFundo));
					shapes.add(new Polygon(pontosX, pontosY, nPontos));

					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

					pontosX = new int[10];
					pontosY = new int[10];
					nPontos = 0;

					esperaInicioPoligonoI = true;
					esperaInicioPoligonoII = false;
					esperaFimPoligonoI = false;
					esperaDrag = true;
					salvo = false;

				}
			}

			if (SwingUtilities.isLeftMouseButton(e)) {
				if (esperaPonto) {
					figuras.add(new Ponto(e.getX(), e.getY(), corAtual));
					shapes.add(new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY()));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
					new DesenhoDePonto();
				} else if (esperaInicioReta) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
					esperaFimReta = true;
					// repaint();
					// esperaInicioReta = false;
					// esperaFimReta = true;
					// statusBar1.setText("Mensagem: clique o ponto final da
					// reta");
				}

				else if (esperaInicioCirculo) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					// esperaInicioCirculo = false;
					// esperaFimCirculo = true;
					// statusBar1.setText("Mensagem: clique o ponto final do
					// c�rculo");
				} else if (esperaFimCirculo) {
					// raio = (int) Math.sqrt((int) Math.pow((e.getX() -
					// p1.getX()), 2) + (int) Math.pow((e.getY() - p1.getY()),
					// 2));
					// figuras.add (new Circulo(p1.getX(), p1.getY(), raio,
					// corAtual, corFundo));
					// figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
					// esperaInicioCirculo = true;
					// esperaFimCirculo = false;
				} else if (esperaInicioElipse) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					// esperaInicioElipse = false;
					// esperaFimElipse = true;
					// statusBar1.setText("Mensagem: clique o ponto final do
					// elipse");
				} else if (esperaFimElipse) {
					// raio = e.getX() - p1.getX();
					// if (raio<0)
					// raio = raio * -1;
					// raio2 = e.getY() - p1.getY();
					// if (raio2 < 0)
					// raio2 = raio2 * -1;
					// figuras.add (new Elipse(p1.getX(), p1.getY(), raio,
					// raio2, corAtual, corFundo));
					// figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
					// esperaInicioElipse = true;
					// esperaFimElipse = false;
				}

				else if (esperaInicioQuadrado) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					// esperaInicioQuadrado = false;
					// esperaFimQuadrado = true;
					// statusBar1.setText("Mensagem: clique o ponto para definir
					// o lado do quadrado");
				} else if (esperaFimQuadrado) {

					// if (e.getX() < p1.getX()) {
					// lado = p1.getX() - e.getX();
					// p1.setX(e.getX());
					// } else {
					// lado = e.getX() - p1.getX();
					// }
					//
					// if (e.getY() < p1.getY()) {
					// lado2 = p1.getY() - e.getY();
					// p1.setY(e.getY());
					// } else {
					// lado2 = e.getY() - p1.getY();
					// }
					//
					// if (lado > lado2 || lado == lado2)
					// figuras.add (new Quadrado(p1.getX(), p1.getY(), lado,
					// corAtual, corFundo));
					// else
					// figuras.add (new Quadrado(p1.getX(), p1.getY(), lado2,
					// corAtual, corFundo));
					// figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
					// esperaInicioQuadrado = true;
					// esperaFimQuadrado = false;
				} else if (esperaInicioRetangulo) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					// esperaInicioRetangulo = false;
					// esperaFimRetangulo = true;
					// statusBar1.setText("Mensagem: clique o ponto para definir
					// a largura do retangulo");
				} else if (esperaFimRetangulo) {
					// if (e.getX() < p1.getX())
					// {
					// largura = p1.getX() - e.getX();
					// p1.setX(e.getX());
					// }
					// else
					// {
					// largura = e.getX() - p1.getX();
					// }
					//
					//
					// if (e.getY() < p1.getY())
					// {
					// altura = p1.getY() - e.getY();
					// p1.setY(e.getY());
					// }
					// else
					// {
					// altura = e.getY() - p1.getY();
					// }
					//
					// figuras.add (new Retangulo(p1.getX(), p1.getY(), largura,
					// altura, corAtual, corFundo));
					// figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
					// esperaInicioRetangulo = true;
					// esperaFimRetangulo = false;

				} else if (esperaInicioPoligono) {
					// p1 = new Ponto (e.getX(), e.getY(), corAtual);
					// nPontos++ ;
					// pontosX[nPontos-1] = p1.getX();
					// pontosY[nPontos-1] = p1.getY();
					// esperaInicioPoligono = false;
					// esperaFimPoligono = true;
					// statusBar1.setText("Mensagem: clique o ponto para definir
					// a largura dos lados");
				} else if (esperaFimPoligono) {
					// int x2 = e.getX();
					// int y2 = e.getY();
					// Polygon p2 = new Polygon();
					// for (int i = 0; i < ladosPoligono; i++)
					// p2.addPoint((int) (x2 + 150 * Math.cos(i * 2 * Math.PI /
					// ladosPoligono)),
					// (int) (y2 + 150 * Math.sin(i * 2 * Math.PI /
					// ladosPoligono)));
					//
					// figuras.add(new
					// Poligono(p2.xpoints,p2.ypoints,p2.npoints,corAtual,corFundo));
					// figuras.get(figuras.size()-1).torneSeVisivel((pnlDesenho.getGraphics()));
					//
					// esperaFimPoligono = false;
					// esperaInicioPoligono = true;
				} else if (esperaInicioPoligonoI) {
					p1 = new Ponto(e.getX(), e.getY(), corAtual);
					nPontos++;
					pontosX[nPontos - 1] = p1.getX();
					pontosY[nPontos - 1] = p1.getY();

					figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));

					esperaInicioPoligonoI = false;
					esperaInicioPoligonoII = true;
					esperaFimPoligonoI = true;
					esperaDrag = true;

					statusBar1.setText("Mensagem: clique com o bot�o direito para desenhar o pol�gono");

				} else if (esperaInicioPoligonoII) {
					esperaDrag = true;
					esperaFimPoligonoI = false;
					figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
				} else if (esperaInicioTexto) {

					texto = new JTextField();

					texto.setFont(fonteAtual);
					p1 = new Ponto(e.getX(), e.getY());
					texto.setBounds(e.getX(), e.getY(), 300, fonteAtual.getSize());
					texto.setText("");
					add(texto);
					texto.requestFocus();
					setLayout(new BorderLayout());
					esperaInicioTexto = false;
					esperaFimTexto = true;
					statusBar1.setText("Mensagem: Clique fora da caixa de Texto para Concluir!");
				} else if (esperaFimTexto) {
					texto.setFont(new Font(fonte.getSelectedFontFamily(), fonte.getSelectedFontStyle(),
							fonte.getSelectedFontSize()));
					String txt = texto.getText();

					texto.setBackground(getBackground());
					texto.setForeground(corAtual);
					setLayout(new BorderLayout());
					getGraphics().clearRect(pnlDesenho.getX(), pnlDesenho.getY(), pnlDesenho.getWidth(),
							pnlDesenho.getHeight());

					remove(texto);
					repaint();

					p1.setY(p1.getY() + texto.getHeight() - 5);
					figuras.add(new Texto(p1.getX(), p1.getY(), corAtual, txt, fonteAtual));
					statusBar1.setText("a " + p1.getX() + p1.getY());

					pnlDesenho.setFont(fonteAtual);
					Ponto guardaPonto = new Ponto(pnlDesenho.getFontMetrics(fonteAtual).stringWidth(txt),
							pnlDesenho.getFontMetrics(fonteAtual).getHeight());
					shapes.add(new Rectangle(p1.getX(), p1.getY() - guardaPonto.getY(), guardaPonto.getX(),
							guardaPonto.getY()));

					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

					esperaInicioTexto = true;
					esperaFimTexto = false;
				} else if (esperaSelecionar) {

					for (int i = shapes.size() - 1; i >= 0; i--) {
						if (shapes.get(i).contains(e.getX(), e.getY())) {
							selecionado = i;
							esperaApagar2 = true;
							statusBar1.setText("Mensagem: " + selecionado);
							break;
						}
					}

					esperaSelecionar = true;
				} else if (esperaMover) {

					for (int i = shapes.size() - 1; i >= 0; i--) {
						if (shapes.get(i).contains(e.getX(), e.getY())) {
							movendo = i;

							break;
						}
					}

					if (movendo != -1) {
						classe = figuras.get(movendo).getClass().getName();
						esperaMover2 = true;
						statusBar1.setText("Mensagem: haha " + classe);
					}

				} else if (esperaApagar) {
					for (int i = shapes.size() - 1; i >= 0; i--) {
						if (shapes.get(i).contains(e.getX(), e.getY())) {
							selecionado = i;
							esperaApagar2 = true;
							statusBar1.setText("Mensagem: " + selecionado);
							break;
						}
					}

				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (esperaFimReta) {
				figuras.remove(figuras.size() - 1);
				figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));

				shapes.add(new Line2D.Double(p1.getX(), p1.getY(), e.getX(), e.getY()));

				figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaInicioReta = true;
				salvo = false;
			}
			if (esperaFimRetangulo) {
				figuras.remove(figuras.size() - 1);
				pnlDesenho.repaint();
				int a = Math.abs(p1.getX() - e.getX());
				int b = Math.abs(p1.getY() - e.getY());
				figuras.add(new Retangulo(p1.getX(), p1.getY(), a, b, corAtual, corFundo));

				shapes.add(new Rectangle(p1.getX() - a, p1.getY() - b, 2 * a, 2 * b));

				figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaFimRetangulo = false;
				esperaInicioRetangulo = true;
				salvo = false;
			}
			if (esperaFimPoligono) {
				// Graphics2D g_pf = Buffered_da_Reta.createGraphics();
				// g_pf.drawImage(Buffered_da_Imagem, 0, 0, null);
				// g_pf.setColor(corAtual);
				//
				// g_pf.setStroke(new BasicStroke(2.0f));
				//
				// int x2 = e.getX();
				// int y2 = e.getY();
				//
				// Polygon p2 = new Polygon();
				//
				// for (int i = 0; i < ladosPoligono; i++) {
				// p2.addPoint(
				// (int) (x + (Math.max(x, x2) - Math.min(x, x2)) * Math.cos(i *
				// 2 * Math.PI / ladosPoligono)),
				// (int) (x + (Math.max(x, x2) - Math.min(x, x2)) * Math.sin(i *
				// 2 * Math.PI / ladosPoligono)));
				// }
				// figuras.add(new Poligono(p2.xpoints, p2.ypoints, p2.npoints,
				// corAtual, corFundo));
				// figuras.get(figuras.size() - 1).torneSeVisivel((Graphics2D)
				// g_pf);
				// g_pf.dispose();
				esperaFimPoligono = false;
				esperaInicioPoligono = true;
				salvo = false;
			}
			if (esperaFimCirculo) {
				figuras.remove(figuras.size() - 1);
				raio = (int) Math
						.sqrt((int) Math.pow((e.getX() - p1.getX()), 2) + (int) Math.pow((e.getY() - p1.getY()), 2));
				figuras.add(new Circulo(p1.getX(), p1.getY(), raio, corAtual, corFundo));
				shapes.add(new Ellipse2D.Double(p1.getX() - raio, p1.getY() - raio, raio * 2, raio * 2));

				figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaFimCirculo = false;
				esperaInicioCirculo = true;
				salvo = false;
			}
			if (esperaFimElipse) {
				figuras.remove(figuras.size() - 1);
				figuras.add(new Elipse(p1.getX(), p1.getY(), raio, raio2, corAtual, corFundo));
				shapes.add(new Ellipse2D.Double(p1.getX() - raio, p1.getY() - raio2, raio * 2, raio2 * 2));
				figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaInicioElipse = true;
				esperaFimElipse = false;
				salvo = false;
			}
			if (esperaFimQuadrado) {
				figuras.remove(figuras.size() - 1);
				pnlDesenho.repaint();
				statusBar1.setText("Mensagem: clique o ponto para definir o lado do quadrado");
				int a = Math.abs(p1.getX() - e.getX());
				figuras.add(new Retangulo(p1.getX(), p1.getY(), a, a, corAtual, corFundo));
				shapes.add(new Rectangle(p1.getX() - a, p1.getY() - a, a * 2, a * 2));
				figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaInicioQuadrado = true;
				esperaFimQuadrado = false;
				salvo = false;
			}
			if (esperaInicioPoligonoII) {

				p1 = new Ponto(e.getX(), e.getY(), corAtual);
				nPontos++;
				pontosX[nPontos - 1] = p1.getX();
				pontosY[nPontos - 1] = p1.getY();

				esperaInicioPoligonoI = false;
				esperaDrag = false;
				esperaFimPoligonoI = true;

				statusBar1.setText("Mensagem: clique com o bot�o direito para desenhar o pol�gono");
			}
			if (esperaMover2) {

				esperaMover2 = false;
				esperaMover = true;
				movendo = -1;
				statusBar1.setText("Mensagem: clique e arraste uma figura para mov�-la");

			}
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				if (esperaInicioPoligono) {

					Graphics2D g_pf = Buffered_da_Reta.createGraphics();
					g_pf.drawImage(Buffered_da_Imagem, 0, 0, null);
					g_pf.setColor(corAtual);

					g_pf.setStroke(new BasicStroke(2.0f));

					int x2 = e.getX();
					// int y2 = e.getY();

					Polygon p2 = new Polygon();

					for (int i = 0; i < ladosPoligono; i++) {
						p2.addPoint(
								(int) (x + (Math.max(x, x2) - Math.min(x, x2))
										* Math.cos(i * 2 * Math.PI / ladosPoligono)),
								(int) (x + (Math.max(x, x2) - Math.min(x, x2))
										* Math.sin(i * 2 * Math.PI / ladosPoligono)));
					}

					figuras.add(new Poligono(p2.xpoints, p2.ypoints, p2.npoints, corAtual, corFundo));
					shapes.add(p2);
					figuras.get(figuras.size() - 1).torneSeVisivel((Graphics2D) g_pf);
					g_pf.dispose();
					esperaInicioPoligono = false;
					esperaFimPoligono = true;
				}
				if (esperaFimPoligono) {
					figuras.remove(figuras.size() - 1);
					shapes.remove(shapes.size() - 1);
					pnlDesenho.repaint();
					Graphics2D g_pf = Buffered_da_Reta.createGraphics();
					g_pf.drawImage(Buffered_da_Imagem, 0, 0, null);
					g_pf.setColor(corAtual);

					g_pf.setStroke(new BasicStroke(2.0f));

					int x2 = e.getX();
					// int y2 = e.getY();

					Polygon p2 = new Polygon();

					for (int i = 0; i < ladosPoligono; i++) {
						p2.addPoint(
								(int) (x + (Math.max(x, x2) - Math.min(x, x2))
										* Math.cos(i * 2 * Math.PI / ladosPoligono)),
								(int) (y + (Math.max(x, x2) - Math.min(x, x2))
										* Math.sin(i * 2 * Math.PI / ladosPoligono)));
					}

					figuras.add(new Poligono(p2.xpoints, p2.ypoints, p2.npoints, corAtual, corFundo));
					shapes.add(p2);
					figuras.get(figuras.size() - 1).torneSeVisivel((Graphics2D) g_pf);
					g_pf.dispose();
				}

				if (esperaInicioRetangulo) {
					statusBar1.setText("Mensagem: clique o ponto para definir a largura do retangulo");
					if (e.getX() < p1.getX()) {
						largura = p1.getX() - e.getX();
						// p1.setX(e.getX());
					} else {
						largura = e.getX() - p1.getX();
					}

					if (e.getY() < p1.getY()) {
						altura = p1.getY() - e.getY();
						// p1.setY(e.getY());
					} else {
						altura = e.getY() - p1.getY();
					}
					figuras.add(new Retangulo(p1.getX(), p1.getY(), largura, altura, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
					esperaFimRetangulo = true;
					esperaInicioRetangulo = false;
				}
				if (esperaFimRetangulo) {

					figuras.remove(figuras.size() - 1);
					pnlDesenho.repaint();
					int a = Math.abs(p1.getX() - e.getX());
					int b = Math.abs(p1.getY() - e.getY());
					figuras.add(new Retangulo(p1.getX(), p1.getY(), a, b, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				}
				if (esperaFimReta) {
					figuras.remove(figuras.size() - 1);
					figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));

					repaint();
				}
				if (esperaInicioCirculo) {

					raio = (int) Math.sqrt(
							(int) Math.pow((e.getX() - p1.getX()), 2) + (int) Math.pow((e.getY() - p1.getY()), 2));
					figuras.add(new Circulo(p1.getX(), p1.getY(), raio, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
					esperaInicioCirculo = false;
					esperaFimCirculo = true;
				}
				if (esperaFimCirculo) {
					figuras.remove(figuras.size() - 1);
					pnlDesenho.repaint();
					raio = (int) Math.sqrt(
							(int) Math.pow((e.getX() - p1.getX()), 2) + (int) Math.pow((e.getY() - p1.getY()), 2));
					figuras.add(new Circulo(p1.getX(), p1.getY(), raio, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				}
				if (esperaInicioElipse) {
					raio = e.getX() - p1.getX();
					if (raio < 0)
						raio = raio * -1;
					raio2 = e.getY() - p1.getY();
					if (raio2 < 0)
						raio2 = raio2 * -1;
					figuras.add(new Elipse(p1.getX(), p1.getY(), raio, raio2, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
					esperaInicioElipse = false;
					esperaFimElipse = true;
				}
				if (esperaFimElipse) {
					figuras.remove(figuras.size() - 1);
					pnlDesenho.repaint();
					raio = e.getX() - p1.getX();
					if (raio < 0)
						raio = raio * -1;
					raio2 = e.getY() - p1.getY();
					if (raio2 < 0)
						raio2 = raio2 * -1;
					figuras.add(new Elipse(p1.getX(), p1.getY(), raio, raio2, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
				}

				if (esperaInicioQuadrado) {
					esperaInicioQuadrado = false;
					esperaFimQuadrado = true;
					statusBar1.setText("Mensagem: clique o ponto para definir o lado do quadrado");

					if (lado > lado2 || lado == lado2)
						figuras.add(new Quadrado(p1.getX(), p1.getY(), lado, corAtual, corFundo));
					else
						figuras.add(new Quadrado(p1.getX(), p1.getY(), lado2, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

				}
				if (esperaFimQuadrado) {
					figuras.remove(figuras.size() - 1);
					pnlDesenho.repaint();
					statusBar1.setText("Mensagem: clique o ponto para definir o lado do quadrado");
					int a = Math.abs(p1.getX() - e.getX());
					figuras.add(new Retangulo(p1.getX(), p1.getY(), a, a, corAtual, corFundo));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

				}
				if (esperaDrag) {
					figuras.remove(figuras.size() - 1);
					figuras.add(new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual));
					figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());

					repaint();

				}
				if (esperaMover2) {

					if (movendo != -1) {
						Ponto pontoAux;
						switch (classe) {

						case "Circulo":
							Circulo circulo = (Circulo) figuras.get(movendo);

							circulo.setCentro(e.getX(), e.getY());

							pontoAux = circulo.getCentro();
							Ellipse2D elipseda = new Ellipse2D.Double(pontoAux.getX() - circulo.getRaio(),
									pontoAux.getY() - circulo.getRaio(), circulo.getRaio() * 2, circulo.getRaio() * 2);
							figuras.remove(movendo);
							figuras.add(movendo, circulo);
							shapes.remove(movendo);
							shapes.add(movendo, elipseda);

							repaint();
							break;

						case "Elipse":
							Elipse elipse = (Elipse) figuras.get(movendo);

							elipse.setCentro(e.getX(), e.getY());

							pontoAux = elipse.getCentro();
							Ellipse2D elipsedaa = new Ellipse2D.Double(pontoAux.getX() - elipse.getRaio1(),
									pontoAux.getY() - elipse.getRaio2(), elipse.getRaio1() * 2, elipse.getRaio2() * 2);

							figuras.remove(movendo);
							figuras.add(movendo, elipse);
							shapes.remove(movendo);
							shapes.add(movendo, elipsedaa);

							repaint();

							break;

						case "Retangulo":

							Retangulo retangulo = (Retangulo) figuras.get(movendo);

							retangulo.setCentro(e.getX(), e.getY());
							pontoAux = retangulo.getCentro();

							Rectangle retanguloo = new Rectangle(pontoAux.getX() - retangulo.getRaio1(),
									pontoAux.getY() - retangulo.getRaio2(), retangulo.getRaio1() * 2,
									retangulo.getRaio2() * 2);
							figuras.remove(movendo);
							figuras.add(movendo, retangulo);
							shapes.remove(movendo);
							shapes.add(movendo, retanguloo);

							repaint();
							break;
						case "Poligono":

							Poligono poligono = (Poligono) figuras.get(movendo);

							int[] pontosX = poligono.getPontosX(), pontosY = poligono.getPontosY();
							int aux;

							if (pontosX[0] < e.getX()) {
								aux = e.getX() - pontosX[0];
								for (int i = 0; i <= poligono.nPontos - 1; i++)
									pontosX[i] += aux;
							} else {
								aux = pontosX[0] - e.getX();
								for (int i = 0; i <= poligono.nPontos - 1; i++)
									pontosX[i] -= aux;
							}

							if (pontosY[0] < e.getY()) {
								aux = e.getY() - pontosY[0];
								for (int i = 0; i <= poligono.nPontos - 1; i++)
									pontosY[i] += aux;
							} else {
								aux = pontosY[0] - e.getY();
								for (int i = 0; i <= poligono.nPontos - 1; i++)
									pontosY[i] -= aux;
							}

							poligono.setPontosX(pontosX);
							poligono.setPontosY(pontosY);

							Polygon p2 = new Polygon(pontosX, poligono.getPontosY(), poligono.getPontos());

							figuras.remove(movendo);
							figuras.add(movendo, poligono);
							shapes.remove(movendo);
							shapes.add(movendo, p2);

							repaint();
							break;
						case "PoligonoIrr":
							PoligonoIrr poligonoIrr = (PoligonoIrr) figuras.get(movendo);

							pontosX = poligonoIrr.getPontosX();
							pontosY = poligonoIrr.getPontosY();

							if (pontosX[0] < e.getX()) {
								aux = e.getX() - pontosX[0];
								for (int i = 0; i <= poligonoIrr.nPontos - 1; i++)
									pontosX[i] += aux;
							} else {
								aux = pontosX[0] - e.getX();
								for (int i = 0; i <= poligonoIrr.nPontos - 1; i++)
									pontosX[i] -= aux;
							}

							if (pontosY[0] < e.getY()) {
								aux = e.getY() - pontosY[0];
								for (int i = 0; i <= poligonoIrr.nPontos - 1; i++)
									pontosY[i] += aux;
							} else {
								aux = pontosY[0] - e.getY();
								for (int i = 0; i <= poligonoIrr.nPontos - 1; i++)
									pontosY[i] -= aux;
							}

							poligonoIrr.setPontosX(pontosX);
							poligonoIrr.setPontosY(pontosY);

							Polygon P3 = new Polygon(pontosX, poligonoIrr.getPontosY(), poligonoIrr.getPontos());

							figuras.remove(movendo);
							figuras.add(movendo, poligonoIrr);
							shapes.remove(movendo);
							shapes.add(movendo, P3);

							repaint();

							break;

						}

					}
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
			statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
			requestFocusInWindow();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (esperaApagar2) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					figuras.remove(selecionado);
					shapes.remove(selecionado);

					repaint();
					esperaApagar2 = false;
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	protected class DesenhoDePonto implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = true;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique o local do ponto desejado");
		}
	}

	protected class DesenhoDeReta implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = true;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique o ponto inicial da reta");
		}
	}

	protected class DesenhoDeCirculo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = true;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique no ponto do meio do c�rculo");
		}
	}

	protected class DesenhoDeElipse implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = true;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique no ponto do meio do elipse");
		}
	}

	protected class DesenhoQuadrado implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = true;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique para definir o ponto inicial do quadrado");
		}
	}

	protected class DesenhoDeRetangulo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = true;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaApagar = false;
			esperaMover = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique para definir o ponto superior esquerdo do retangulo");
		}
	}

	protected class DesenhoPoligono implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = true;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaApagar = false;
			esperaMover = false;
			esperaAproximar = false;

			ladosPoligono = Integer
					.parseInt((JOptionPane.showInputDialog(pnlDesenho, "Quantos lados o pol�gono deve ter?", null)));
			statusBar1.setText("Mensagem: clique para definir o ponto superior esquerdo do pol�gono");

		}
	}

	protected class Aproximar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaMover = false;
			esperaApagar = false;
			esperaSelecionar = false;
			esperaAproximar = true;
		}
	}

	protected class DesenhoPoligonoI implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = false;
			esperaFimTexto = false;
			esperaInicioPoligonoI = true;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaMover = false;
			esperaApagar = false;
			esperaSelecionar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique para definir o ponto inicial do pol�gono");

		}
	}

	protected class Fontes implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			final JFontChooser fontChooser = new JFontChooser();
			int result = fontChooser.showDialog(null);
			if (result == JFontChooser.OK_OPTION) {
				fonteAtual = fontChooser.getSelectedFont();
			}
		}

	}

	protected class cTexto implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			esperaPonto = false;
			esperaInicioReta = false;
			esperaFimReta = false;
			esperaInicioCirculo = false;
			esperaFimCirculo = false;
			esperaInicioElipse = false;
			esperaFimElipse = false;
			esperaInicioQuadrado = false;
			esperaFimQuadrado = false;
			esperaInicioRetangulo = false;
			esperaFimRetangulo = false;
			esperaInicioPoligono = false;
			esperaFimPoligono = false;
			esperaInicioTexto = true;
			esperaFimTexto = false;
			esperaInicioPoligonoI = false;
			esperaFimPoligonoI = false;
			esperaInicioPoligonoII = false;
			esperaDrag = false;
			esperaSelecionar = false;
			esperaMover = false;
			esperaApagar = false;
			esperaAproximar = false;

			statusBar1.setText("Mensagem: clique para definir o ponto inicial da �rea de texto");
		}
	}

	protected class Cores implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			corAtual = JColorChooser.showDialog(null, "Escolha uma cor", corAtual);
		}

	}

	protected class CorFundo implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			corFundo = JColorChooser.showDialog(null, "Escolha uma cor de fundo", corFundo);
		}

	}

	protected class Selecionar implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (figuras.size() > 0) {
				esperaPonto = false;
				esperaInicioReta = false;
				esperaFimReta = false;
				esperaInicioCirculo = false;
				esperaFimCirculo = false;
				esperaInicioElipse = false;
				esperaFimElipse = false;
				esperaInicioQuadrado = false;
				esperaFimQuadrado = false;
				esperaInicioRetangulo = false;
				esperaFimRetangulo = false;
				esperaInicioPoligono = false;
				esperaFimPoligono = false;
				esperaInicioTexto = false;
				esperaFimTexto = false;
				esperaInicioPoligonoI = false;
				esperaFimPoligonoI = false;
				esperaInicioPoligonoII = false;
				esperaDrag = false;
				esperaSelecionar = true;
				esperaMover = false;
				esperaApagar = false;
				esperaAproximar = false;

				statusBar1.setText("Mensagem: Clique em uma figura para seleciona-la");
			} else {
				statusBar1.setText("Mensagem: Nenhuma figura desenhada");
			}
		}

	}

	protected class Apagar implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (figuras.size() > 0) {
				esperaPonto = false;
				esperaInicioReta = false;
				esperaFimReta = false;
				esperaInicioCirculo = false;
				esperaFimCirculo = false;
				esperaInicioElipse = false;
				esperaFimElipse = false;
				esperaInicioQuadrado = false;
				esperaFimQuadrado = false;
				esperaInicioRetangulo = false;
				esperaFimRetangulo = false;
				esperaInicioPoligono = false;
				esperaFimPoligono = false;
				esperaInicioTexto = false;
				esperaFimTexto = false;
				esperaInicioPoligonoI = false;
				esperaFimPoligonoI = false;
				esperaInicioPoligonoII = false;
				esperaDrag = false;
				esperaSelecionar = false;
				esperaMover = false;
				esperaApagar = true;
				esperaAproximar = false;

				statusBar1.setText("Mensagem: Clique e aperte DEL para apagar");
			} else {
				statusBar1.setText("Mensagem: Nenhuma figura adicionada");
			}
		}

	}

	protected class Limpar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (figuras.size() > 0) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja mesmo apagar todas figuras?");

				if (opcao == JOptionPane.YES_OPTION) {
					figuras.clear();
					shapes.clear();

					repaint();
				}

			} else {
				statusBar1.setText("Mensagem: Nenhuma figura adicionada");
			}

		}
	}

	protected class Mover implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (figuras.size() > 0) {
				esperaPonto = false;
				esperaInicioReta = false;
				esperaFimReta = false;
				esperaInicioCirculo = false;
				esperaFimCirculo = false;
				esperaInicioElipse = false;
				esperaFimElipse = false;
				esperaInicioQuadrado = false;
				esperaFimQuadrado = false;
				esperaInicioRetangulo = false;
				esperaFimRetangulo = false;
				esperaInicioPoligono = false;
				esperaFimPoligono = false;
				esperaInicioTexto = false;
				esperaFimTexto = false;
				esperaInicioPoligonoI = false;
				esperaFimPoligonoI = false;
				esperaInicioPoligonoII = false;
				esperaDrag = false;
				esperaSelecionar = false;
				esperaMover = true;
				esperaApagar = false;
				esperaAproximar = false;

				statusBar1.setText("Mensagem: Clique e arraste para mover a figura");
			} else {
				statusBar1.setText("Mensagem: Nenhuma figura adicionada");
			}
		}
	}

	protected class Salvar2 {

		public Salvar2() {
			try {

				JFileChooser c = new JFileChooser();

				int rVal = c.showSaveDialog(pnlDesenho);

				String nome = c.getSelectedFile().getName();

				for (int i = 0; i <= nome.length() - 1; i++) {
					if (nome.charAt(i) == '.') {
						nome = nome.substring(0, i);
					}

				}

				if (rVal == JFileChooser.APPROVE_OPTION) {

					PrintWriter arqSaida = new PrintWriter(
							new FileOutputStream(c.getCurrentDirectory().toString() + "/" + nome + ".dougras"));
					;
					for (int i = 0; i <= figuras.size() - 1; i++) {

						arqSaida.println(figuras.get(i).toString());
					}
					arqSaida.close();
					salvo = true;
				}
			} catch (IOException err) {
				System.out.println("erro: " + err);
			}
		}
	}

	protected class Salvar implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {

				JFileChooser c = new JFileChooser();

				int rVal = c.showSaveDialog(pnlDesenho);
				if (rVal == JFileChooser.APPROVE_OPTION) {

					String nome = c.getSelectedFile().getName();

					for (int i = 0; i <= nome.length() - 1; i++) {
						if (nome.charAt(i) == '.') {
							nome = nome.substring(0, i);
						}

					}

					File f = new File(c.getCurrentDirectory().toString() + "/" + nome + ".dougras");

					if (f.exists() && !f.isDirectory()) {
						int opcao = JOptionPane.showConfirmDialog(null,
								"Nome de arquivo j� existente. Salvar mesmo assim?");

						if (opcao == JOptionPane.YES_OPTION) {
							PrintWriter arqSaida = new PrintWriter(
									new FileOutputStream(c.getCurrentDirectory().toString() + "/" + nome + ".dougras"));
							;
							for (int i = 0; i <= figuras.size() - 1; i++) {

								arqSaida.println(figuras.get(i).toString());
							}
							arqSaida.close();
							salvo = true;

						} else if (opcao == JOptionPane.NO_OPTION) {
							new Salvar2();
						}

					} else {
						PrintWriter arqSaida = new PrintWriter(
								new FileOutputStream(c.getCurrentDirectory().toString() + "/" + nome + ".dougras"));
						;
						for (int i = 0; i <= figuras.size() - 1; i++) {

							arqSaida.println(figuras.get(i).toString());
						}
						arqSaida.close();
						salvo = true;
					}
				}
			} catch (IOException err) {
				System.out.println("erro: " + err);
			}
		}
	}

	protected class Abrir implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			JFileChooser c = new JFileChooser();

			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Somente zip zop", "dougras");
			c.setFileFilter(filtro);

			int rVal = c.showSaveDialog(pnlDesenho);
			if (rVal == JFileChooser.APPROVE_OPTION) {

				try {
					BufferedReader arqEntrada = new BufferedReader(
							new FileReader(c.getCurrentDirectory().toString() + "/" + c.getSelectedFile().getName()));
					String linha;

					figuras.clear();

					while (arqEntrada.ready()) {
						try {
							linha = arqEntrada.readLine();

							switch (linha.charAt(0)) {
							case 'd':
								figuras.add(new Ponto(linha));
								break;
							case 'c':
								figuras.add(new Circulo(linha));
								break;
							case 'l':
								figuras.add(new Linha(linha));
								break;
							case 'p':
								figuras.add(new Poligono(linha));
								break;
							case 'i':
								figuras.add(new PoligonoIrr(linha));
								break;
							case 'e':
								figuras.add(new Elipse(linha));
								break;
							case 'q':
								figuras.add(new Quadrado(linha));
								break;
							case 'r':
								figuras.add(new Retangulo(linha));
								break;
							case 't':
								figuras.add(new Texto(linha));
								break;
							}

							pnlDesenho.repaint();

						} catch (IOException erro) {
							System.out.println(erro);
						}
					}

					arqEntrada.close();
				} catch (IOException erro) {
					System.out.println(erro);
				}

			}
		}

	}

	protected class FechamentoDeJanela extends WindowAdapter {
		public void windowClosing(WindowEvent e) {

			if (salvo || figuras.size() == 0)
				System.exit(0);
			else {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja salvar antes de sair?");

				if (opcao == JOptionPane.NO_OPTION) {
					System.exit(0);
				} else if (opcao == JOptionPane.YES_OPTION) {
					new Salvar2();
				}

			}
		}
	}
}
