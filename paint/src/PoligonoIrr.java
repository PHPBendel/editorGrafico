import java.util.*;
import java.awt.*;

public class PoligonoIrr extends Figura {

	protected int nPontos;
	protected Color corFundo;
	protected int[] pontosX = new int[10], pontosY = new int[10];

	public PoligonoIrr(int[] x, int[] y, int pontos) {
		this(x, y, pontos, Color.BLACK, Color.WHITE);
	}

	public PoligonoIrr(int[] x, int[] y, int pontos, Color cor, Color corFundo) {
		super(cor);

		this.pontosX = x;
		this.pontosY = y;
		this.nPontos = pontos;
		this.corFundo = corFundo;
	}

	public void setPontosX(int[] x) {
		this.pontosX = x;
	}

	public void setPontosY(int[] y) {
		this.pontosY = y;
	}

	public void setPontos(int pontos) {
		this.nPontos = pontos;
	}

	public int[] getPontosX() {
		return this.pontosX;
	}

	public int[] getPontosY() {
		return this.pontosY;
	}

	public int getPontos() {
		return this.nPontos;
	}

	public void torneSeVisivel(Graphics g) {

		g.setColor(this.corFundo);
		g.fillPolygon(this.pontosX, this.pontosY, nPontos);
		g.setColor(this.cor);
		g.drawPolygon(this.pontosX, this.pontosY, nPontos);

	}

	public PoligonoIrr(String s) {
		StringTokenizer quebrador = new StringTokenizer(s, ":");

		quebrador.nextToken();

		int nPontos = Integer.parseInt(quebrador.nextToken());

		int[] x = new int[10];
		int[] y = new int[10];

		for (int i = 0; i < nPontos; i++) {
			x[i] = Integer.parseInt(quebrador.nextToken());
		}
		for (int i = 0; i < nPontos; i++) {
			y[i] = Integer.parseInt(quebrador.nextToken());
		}

		Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		Color corFundo = new Color(Integer.parseInt(quebrador.nextToken()), // R
				Integer.parseInt(quebrador.nextToken()), // G
				Integer.parseInt(quebrador.nextToken())); // B

		this.pontosX = x;
		this.pontosY = y;
		this.nPontos = nPontos;
		this.cor = cor;
		this.corFundo = corFundo;

	}

	public String toString() {

		String retorno = "i:" + this.nPontos + ":";

		for (int i = 0; i <= nPontos - 1; i++) {
			retorno += pontosX[i] + ":";

		}

		for (int i = 0; i <= nPontos - 1; i++) {
			retorno += pontosY[i] + ":";

		}

		retorno += this.getCor().getRed() + ":" + this.getCor().getGreen() + ":" + this.getCor().getBlue() + ":"
				+ this.corFundo.getRed() + ":" + this.corFundo.getGreen() + ":" + this.corFundo.getBlue();

		return retorno;
	}
	
	public int hashCode() {
		int ret = 14;

		ret = ret * 7 + new Integer(this.nPontos).hashCode();

		for (int i = 0; i <= this.nPontos - 1; i++)
			ret = ret * 7 + new Integer(this.pontosX[i]).hashCode();

		for (int i = 0; i <= this.nPontos - 1; i++)
			ret = ret * 7 + new Integer(this.pontosY[i]).hashCode();

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

		PoligonoIrr p = (PoligonoIrr) obj;

		if (this.nPontos != p.nPontos)
			return false;

		for (int i = 0; i <= this.nPontos - 1; i++) {
			if (this.pontosX[i] != p.pontosX[i])
				return false;
		}

		for (int i = 0; i <= this.nPontos - 1; i++) {
			if (this.pontosY[i] != p.pontosY[i])
				return false;
		}

		if (this.cor != p.cor)
			return false;

		if (this.corFundo != p.corFundo)
			return false;
		return true;
	}

	public PoligonoIrr(PoligonoIrr modelo) throws Exception {
		if (modelo == null)
			throw new Exception("Modelo ausente");

		this.nPontos = modelo.nPontos;
		this.cor = modelo.cor;
		this.corFundo = modelo.corFundo;

		for (int i = 0; i <= this.nPontos - 1; i++)
			this.pontosX[i] = modelo.pontosX[i];

		for (int i = 0; i <= this.nPontos - 1; i++)
			this.pontosY[i] = modelo.pontosY[i];

	}

	public Object clone() {

		PoligonoIrr ret = null;

		try {
			ret = new PoligonoIrr(this);
		} catch (Exception erro) {
		}

		return ret;

	}
}
