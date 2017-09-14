import java.awt.*;
import java.util.*;

public class Quadrado extends Figura{

	 protected Ponto inicio;

	    protected int lado;
	    
	    protected Color corFundo;
		
	    public Quadrado (int x, int y, int lado)
	    {
	        this (x, y, lado, Color.BLACK, Color.WHITE);
	    }
		
	    public Quadrado (int x, int y, int lado, Color cor, Color cor2)
	    {
	        super (cor);
	        
	        corFundo = cor2;
	        
	        this.inicio = new Ponto (x,y);

	        this.lado  = lado;	        
	    }

	    public Quadrado (String s)
	    {
	        StringTokenizer quebrador = new StringTokenizer(s,":");

	        quebrador.nextToken();

	        int   x   = Integer.parseInt(quebrador.nextToken());
	        int   y   = Integer.parseInt(quebrador.nextToken());

	        int   lado  = Integer.parseInt(quebrador.nextToken());	       

	        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
	                               Integer.parseInt(quebrador.nextToken()),  // G
	                               Integer.parseInt(quebrador.nextToken())); // B
	        
	        Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
					                    Integer.parseInt(quebrador.nextToken()),  // G
					                    Integer.parseInt(quebrador.nextToken())); // B

	        this.inicio   = new Ponto (x,y,cor);
	        this.lado     = lado;	       
	        this.cor      = cor;
	        this.corFundo = corFundo;
	    }

	    public void setInicio (int x, int y)
	    {
	        this.inicio = new Ponto (x,y,this.getCor());
	    }

	    public void setLado (int lado)
	    {
	        this.lado = lado;
	    }	  

	    public Ponto getInicio ()
	    {
	        return this.inicio;
	    }

	    public int getLado ()
	    {
	        return this.lado;
	    }

	    	    public void torneSeVisivel (Graphics g)
	    {

	    	g.setColor(this.corFundo);
	        g.fillRect(this.inicio.getX(), this.inicio.getY(), lado, lado);	
	        g.setColor (this.cor);	        
	        g.drawRect (this.inicio.getX(), this.inicio.getY(), lado, lado);
	        
	    }

	    public String toString()
	    {
	        return "e:" +
	               this.inicio.getX() +
	               ":" +
	               this.inicio.getY() +
	               ":" +
	               this.lado +
	               ":" +	             
	               this.getCor().getRed() +
	               ":" +
	               this.getCor().getGreen() +
	               ":" +
	               this.getCor().getBlue()+ 
	               ":" +	             
	               this.corFundo.getRed() +
	               ":" +
	               this.corFundo.getGreen() +
	               ":" +
	               this.corFundo.getBlue();
	    }
}
