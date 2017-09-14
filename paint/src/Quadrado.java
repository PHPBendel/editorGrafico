import java.awt.*;
import java.util.*;

public class Quadrado extends Figura {

	protected Ponto inicio;

	protected int lado;

	protected Color corFundo;

	public Quadrado(int x, int y, int lado) {
		this(x, y, lado, Color.BLACK, Color.WHITE);
	}

	public Quadrado(int x, int y, int lado, Color cor, Color cor2) {
		super(cor);

		corFundo = cor2;

		this.inicio = new Ponto(x, y);

		this.lado = lado;
	}

	public Quadrado(String s) {
		StringTokenizer quebrador = new StringTokenizer(s, ":");

		quebrador.nextToken();

		int x = Integer.parseInt(quebrador.nextToken());
		int y = Integer.parseInt(quebrador.nextToken());

		int lado = Integer.parseInt(quebrador.nextToken());

		Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		Color corFundo = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		this.inicio = new Ponto(x, y, cor);
		this.lado = lado;
		this.cor = cor;
		this.corFundo = corFundo;
	}

	public void setInicio(int x, int y) {
		this.inicio = new Ponto(x, y, this.getCor());
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public Ponto getInicio() {
		return this.inicio;
	}

	public int getLado() {
		return this.lado;
	}

	public void torneSeVisivel(Graphics g) {

		g.setColor(this.corFundo);
		g.fillRect(this.inicio.getX(), this.inicio.getY(), lado, lado);
		g.setColor(this.cor);
		g.drawRect(this.inicio.getX(), this.inicio.getY(), lado, lado);

	}

	public String toString() {
		return "q:" + this.inicio.getX() + ":" + this.inicio.getY() + ":" + this.lado + ":" + this.getCor().getRed()
				+ ":" + this.getCor().getGreen() + ":" + this.getCor().getBlue() + ":" + this.corFundo.getRed() + ":"
				+ this.corFundo.getGreen() + ":" + this.corFundo.getBlue();
	}

	public int hashCode() {

		int ret = 14;

		ret = ret * 7 + new Integer(this.lado).hashCode();
		ret = ret * 7 + new Integer(this.inicio.getX()).hashCode();
		ret = ret * 7 + new Integer(this.inicio.getY()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getBlue()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getRed()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getGreen()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getBlue()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getRed()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getGreen()).hashCode();

		return ret;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != this.getClass())
			return false;

		Quadrado q = (Quadrado) obj;

		if (this.cor != q.cor)
			return false;
		if (this.corFundo != q.corFundo)
			return false;

		if (this.inicio != q.inicio)
			return false;

		if (this.lado != q.lado)
			return false;

		return true;
	}

	public Quadrado(Quadrado modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente");

		this.cor = modelo.cor;
		this.corFundo = modelo.corFundo;
		this.inicio = modelo.inicio;
		this.lado = modelo.lado;

	}

	public Object clone() {
		Quadrado ret = null;

		try {
			ret = new Quadrado(this);
		} catch (Exception erro) {
		}

		return ret;
	}
}
