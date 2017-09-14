import java.awt.*;
import java.util.*;


public class Retangulo extends Figura{
	
	 	protected Ponto inicio;

	    protected int largura, altura;
	    
	    protected Color corFundo;
		
	    public Retangulo (int x, int y, int largura, int altura)
	    {
	        this (x, y, largura, altura, Color.BLACK, Color.WHITE);
	    }
		
	    public Retangulo (int x, int y, int largura, int altura, Color cor, Color cor2)
	    {
	        super (cor);
	        
	        corFundo = cor2;
	        
	        this.inicio = new Ponto (x,y);

	        this.largura  = largura;	  
	        this.altura   = altura;
	        
	    }

	    public Retangulo (String s)
	    {
	        StringTokenizer quebrador = new StringTokenizer(s,":");

	        quebrador.nextToken();

	        int   x   = Integer.parseInt(quebrador.nextToken());
	        int   y   = Integer.parseInt(quebrador.nextToken());

	        int   largura  = Integer.parseInt(quebrador.nextToken());
	        
	        int   altura  = Integer.parseInt(quebrador.nextToken());	  

	        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
	                               Integer.parseInt(quebrador.nextToken()),  // G
	                               Integer.parseInt(quebrador.nextToken())); // B
	        
	        Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
					                    Integer.parseInt(quebrador.nextToken()),  // G
					                    Integer.parseInt(quebrador.nextToken())); // B

	        this.inicio   = new Ponto (x,y,cor);
	        this.largura  = largura;
	        this.altura   = altura;
	        this.cor      = cor;
	        this.corFundo = corFundo;
	    }

	    public void setInicio (int x, int y)
	    {
	        this.inicio = new Ponto (x,y,this.getCor());
	    }

	    public void setLargura (int largura)
	    {
	        this.largura = largura;	        
	    }	  

	    public void setAltura (int altura)
	    {
	        this.altura = altura;	        
	    }
	    
	    public Ponto getInicio ()
	    {
	        return this.inicio;
	    }

	    public int getLargura ()
	    {
	        return this.largura;	        
	    }

	    public int getAltura ()
	    {	    	
	    	return this.altura;
	    }
	    
	    	    public void torneSeVisivel (Graphics g)
	    {

	    	g.setColor(this.corFundo);
	        g.fillRect(this.inicio.getX(), this.inicio.getY(), largura, altura);	
	        g.setColor (this.cor);	        
	        g.drawRect (this.inicio.getX(), this.inicio.getY(), largura, altura);
	        
	    }

	    public String toString()
	    {
	        return "e:" +
	               this.inicio.getX() +
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
	               this.getCor().getBlue() +
	               ":" +
	               this.corFundo.getRed() +
	               ":" +
	               this.corFundo.getGreen() +
	               ":" +
	               this.corFundo.getBlue();
	    }

}
