import java.awt.*;
import java.util.*;

public class Ponto extends Figura {
	protected int x, y;

	public Ponto(int x, int y) {
		this(x, y, Color.BLACK);
	}

	public Ponto(int x, int y, Color cor) {
		super(cor);

		this.x = x;
		this.y = y;
	}

	public Ponto(String s) {
		StringTokenizer quebrador = new StringTokenizer(s, ":");

		quebrador.nextToken();

		this.x = Integer.parseInt(quebrador.nextToken());
		this.y = Integer.parseInt(quebrador.nextToken());

		this.cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void torneSeVisivel(Graphics g) {
		g.setColor(this.cor);
		g.drawLine(this.x, this.y, this.x, this.y);
	}

	public String toString() {
		return "d:" + this.x + ":" + this.y + ":" + this.getCor().getRed() + ":" + this.getCor().getGreen() + ":"
				+ this.getCor().getBlue();
	}

	public int hashCode() {
		int ret = 14;

		ret = ret * 7 + new Integer(this.x).hashCode();
		ret = ret * 7 + new Integer(this.y).hashCode();
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

		Ponto p = (Ponto) obj;

		if (this.cor != p.cor)
			return false;

		if (this.x != p.x)
			return false;

		if (this.y != p.y)
			return false;

		return true;
	}

	public Ponto(Ponto modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente");

		this.cor = modelo.cor;
		this.x = modelo.x;
		this.y = modelo.y;

	}

	public Object clone() {
		Ponto ret = null;

		try {
			ret = new Ponto(this);

		} catch (Exception erro) {
		}
		return ret;
	}

}