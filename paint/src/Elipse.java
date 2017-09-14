import java.awt.*;
import java.util.*;

public class Elipse extends Figura {
	protected Ponto centro;

	protected int raio1, raio2;

	protected Color corFundo;

	public Elipse(int x, int y, int r1, int r2) {
		this(x, y, r1, r2, Color.BLACK, Color.WHITE);
	}

	public Elipse(int x, int y, int r1, int r2, Color cor, Color cor2) {
		super(cor);

		corFundo = cor2;

		this.centro = new Ponto(x, y);

		this.raio1 = r1;
		this.raio2 = r2;
	}

	public Elipse(String s) {
		StringTokenizer quebrador = new StringTokenizer(s, ":");

		quebrador.nextToken();

		int x = Integer.parseInt(quebrador.nextToken());
		int y = Integer.parseInt(quebrador.nextToken());

		int r1 = Integer.parseInt(quebrador.nextToken());
		int r2 = Integer.parseInt(quebrador.nextToken());

		Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		Color corFundo = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		this.centro = new Ponto(x, y, cor);
		this.raio1 = r1;
		this.raio2 = r2;
		this.cor = cor;
		this.corFundo = corFundo;
	}

	public void setCentro(int x, int y) {
		this.centro = new Ponto(x, y, this.getCor());
	}

	public void setRaio1(int r1) {
		this.raio1 = r1;
	}

	public void setRaio2(int r2) {
		this.raio2 = r2;
	}

	public Ponto getCentro() {
		return this.centro;
	}

	public int getRaio1() {
		return this.raio1;
	}

	public int getRaio2() {
		return this.raio2;
	}

	public void torneSeVisivel(Graphics g) {
		g.setColor(this.corFundo);
		g.fillOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);
		g.setColor(this.cor);
		g.drawOval(this.centro.getX() - raio1, this.centro.getY() - raio2, 2 * raio1, 2 * raio2);

	}

	public String toString() {
		return "e:" + this.centro.getX() + ":" + this.centro.getY() + ":" + this.raio1 + ":" + this.raio2 + ":"
				+ this.getCor().getRed() + ":" + this.getCor().getGreen() + ":" + this.getCor().getBlue() + ":"
				+ this.corFundo.getRed() + ":" + this.corFundo.getGreen() + ":" + this.corFundo.getBlue();
	}

	public int hashCode() {

		int ret = 14;

		ret = ret * 7 + new Integer(this.raio1).hashCode();
		ret = ret * 7 + new Integer(this.raio2).hashCode();
		ret = ret * 7 + new Integer(this.cor.getBlue()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getRed()).hashCode();
		ret = ret * 7 + new Integer(this.cor.getGreen()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getBlue()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getRed()).hashCode();
		ret = ret * 7 + new Integer(this.corFundo.getGreen()).hashCode();
		ret = ret * 7 + new Integer(this.centro.getX()).hashCode();
		ret = ret * 7 + new Integer(this.centro.getY()).hashCode();

		return ret;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (obj == this)
			return true;

		if (obj.getClass() != this.getClass())
			return false;

		Elipse e = (Elipse) obj;

		if (e.centro != this.centro)
			return false;

		if (e.cor != this.cor)
			return false;

		if (e.corFundo != this.corFundo)
			return false;

		if (e.raio1 != this.raio1)
			return false;

		if (e.raio2 != this.raio2)
			return false;

		return true;

	}

	public Elipse(Elipse modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente");

		this.centro = modelo.centro;

		this.cor = modelo.cor;
		this.corFundo = modelo.corFundo;
		this.raio1 = modelo.raio1;
		this.raio2 = modelo.raio2;

	}

	public Object clone() {

		Elipse ret = null;

		try {
			ret = new Elipse(this);

		} catch (Exception erro) {
		}

		return ret;
	}

}