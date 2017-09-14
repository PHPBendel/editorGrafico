import java.util.*;
import java.awt.*;

/*public class Poligono extends Figura{
		
	protected Ponto inicio;
	protected int nPontos, raio;
	protected Color corFundo;
	
	
	public Poligono (int x, int y, int raio, int pontos)
	{
		this (x, y, raio, pontos, Color.BLACK, Color.WHITE);
	}
	
	public Poligono (int x, int y, int raio, int pontos, Color cor, Color corFundo)
	{
		super(cor);
		
		this.inicio.setX(x);
		this.inicio.setY(y);
		this.raio     = raio;
		this.nPontos  = pontos;
		this.corFundo = corFundo; 
	}
	
	public Poligono (String s)
	{
		 StringTokenizer quebrador = new StringTokenizer(s,":");

	        quebrador.nextToken();

	        int     x   = Integer.parseInt(quebrador.nextToken());
	        int     y   = Integer.parseInt(quebrador.nextToken());

	        int   largura  = Integer.parseInt(quebrador.nextToken());
	        
	        int   altura  = Integer.parseInt(quebrador.nextToken());	  

	        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
	                               Integer.parseInt(quebrador.nextToken()),  // G
	                               Integer.parseInt(quebrador.nextToken())); // B

	        this.inicio = new Ponto (x,y,cor);
	        this.largura = largura;
	        this.altura = altura;
	        this.cor   = cor;
		
	}
	
	  public String toString()
	    {
	        return "e:" +
	               this.pontosX.getX() +
	               ":" +
	               this.inicio.getY() +
	               ":" +
	               this.largura +
	               ":" +
	               this.altura +
	               ":" +
	               this.getCor().getRed() +
	               ":" +
	               this.getCor().getGreen() +
	               ":" +
	               this.getCor().getBlue();
	    }
}*/
