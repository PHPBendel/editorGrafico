import java.awt.*;
import java.util.*;

public class Texto extends Figura{

		protected Ponto inicio;
		
		protected int largura, altura;
		
		protected Font fonte;
		
		protected String conteudo;
	
	
	    public Texto (int x, int y, Color cor, String conteudo, Font fonte)
	    {
	        super (cor);	        	      
	        
	        this.inicio = new Ponto (x,y);
	      
	        this.conteudo = conteudo;
	        
	        this.fonte    = fonte;
	        
	    }
	    
	    public Texto (String s)
	    {
	    	StringTokenizer quebrador = new StringTokenizer(s,":");

	        quebrador.nextToken();
	
	        int   x   = Integer.parseInt(quebrador.nextToken());
	        int   y   = Integer.parseInt(quebrador.nextToken());
	
	        int   largura  = Integer.parseInt(quebrador.nextToken());
	        
	        int   altura  = Integer.parseInt(quebrador.nextToken());
	        
	        String conteudo = quebrador.nextToken();
	
	        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
	                               Integer.parseInt(quebrador.nextToken()),  // G
	                               Integer.parseInt(quebrador.nextToken())); // B
	
	        this.inicio = new Ponto (x,y,cor);
	        this.largura = largura;
	        this.altura = altura;
	        this.conteudo = conteudo;
	        this.cor   = cor;
		    		    
	    }
	    
	    public void torneSeVisivel (Graphics g)
	    {	    
	    	g.setColor(this.cor);
	    	g.setFont(this.fonte);
	        g.drawString(this.conteudo, this.inicio.getX(), this.inicio.getY());
	        
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
	    
	    public void setConteudo (String conteudo)
	    {
	    	this.conteudo = conteudo;
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
	    
	    public String getConteudo()
	    {
	    	return this.conteudo;
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
	               this.conteudo +
	               ":" +
	               this.getCor().getRed() +
	               ":" +
	               this.getCor().getGreen() +
	               ":" +
	               this.getCor().getBlue();
	    }
	
}
