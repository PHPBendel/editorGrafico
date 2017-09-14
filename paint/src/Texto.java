import java.awt.*;
import java.util.*;

public class Texto extends Figura {

	protected Ponto inicio;

	protected int largura, altura;

	protected Font fonte;

	protected String conteudo;

	public Texto(int x, int y, Color cor, String conteudo, Font fonte) {
		super(cor);

		this.inicio = new Ponto(x, y);

		this.conteudo = conteudo;

		this.fonte = fonte;

	}

	public Texto(String s) {
		StringTokenizer quebrador = new StringTokenizer(s, ":");

		quebrador.nextToken();

		int x = Integer.parseInt(quebrador.nextToken());
		int y = Integer.parseInt(quebrador.nextToken());

		String nomeFonte = quebrador.nextToken();
		int tipoFonte = Integer.parseInt(quebrador.nextToken());
		int tamanhoFonte = Integer.parseInt(quebrador.nextToken());

		String conteudo = quebrador.nextToken();

		Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		this.inicio = new Ponto(x, y, cor);
		this.conteudo = conteudo;
		this.cor = cor;
		this.fonte = new Font(nomeFonte, tipoFonte, tamanhoFonte);
	}

	public void torneSeVisivel(Graphics g) {
		g.setColor(this.cor);
		g.setFont(this.fonte);
		g.drawString(this.conteudo, this.inicio.getX(), this.inicio.getY());

	}

	public void setInicio(int x, int y) {
		this.inicio = new Ponto(x, y, this.getCor());
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Ponto getInicio() {
		return this.inicio;
	}

	public int getLargura() {
		return this.largura;
	}

	public int getAltura() {
		return this.altura;
	}

	public String getConteudo() {
		return this.conteudo;
	}

	public String toString() {
		return "t:" + this.inicio.getX() + ":" + this.inicio.getY() + ":" + this.fonte.getFontName() + ":"
				+ this.fonte.getStyle() + ":" + this.fonte.getSize() + ":" + this.conteudo + ":"
				+ this.getCor().getRed() + ":" + this.getCor().getGreen() + ":" + this.getCor().getBlue();
	}

	public int hashCode() {
		int ret = 14;

		ret = ret * 7 + new Integer(this.altura).hashCode();
		ret = ret * 7 + new Integer(this.conteudo).hashCode();
		ret = ret * 7 + new Integer(this.largura).hashCode();
		ret = ret * 7 + new Integer(this.inicio.getX()).hashCode();
		ret = ret * 7 + new Integer(this.inicio.getY()).hashCode();
		ret = ret * 7 + new Integer(this.fonte.getFamily()).hashCode();
		ret = ret * 7 + new Integer(this.fonte.getStyle()).hashCode();
		ret = ret * 7 + new Integer(this.fonte.getSize()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getBlue()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getRed()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getGreen()).hashCode();

		return ret;

	}

	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != this.getClass())
			return false;

		Texto t = (Texto) obj;

		if (this.cor != t.cor)
			return false;

		if (this.conteudo != t.conteudo)
			return false;

		if (this.altura != t.altura)
			return false;

		if (this.fonte != t.fonte)
			return false;

		if (this.inicio != t.inicio)
			return false;

		if (this.largura != t.largura)
			return false;

		return true;
	}

	public Texto(Texto modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente");

		this.cor = modelo.cor;
		this.conteudo = modelo.conteudo;
		this.altura = modelo.altura;
		this.largura = modelo.largura;
		this.fonte = modelo.fonte;
		this.inicio = modelo.inicio;

	}

	public Object clone() {
		Texto ret = null;

		try {

			ret = new Texto(this);
		} catch (Exception erro) {
		}

		return ret;
	}
}
