import java.util.*;
import java.awt.*;

public class PoligonoIrr extends Figura{
		
	protected int nPontos;
	protected Color corFundo;
	protected int[] pontosX, pontosY;
	
	
	public PoligonoIrr (int[] x, int[] y, int pontos)
	{
		this (x, y, pontos, Color.BLACK, Color.WHITE);
	}
	
	public PoligonoIrr (int[] x, int[] y, int pontos, Color cor, Color corFundo)
	{
		super(cor);
		
		this.pontosX       = x;
		this.pontosY       = y;
		this.nPontos  = pontos;
		this.corFundo = corFundo; 
	}
	
	 public void setPontosX (int[] x)
	    {
	        this.pontosX = x;
	    }

	    public void setPontosY (int[] y)
	    {
	    	this.pontosY = y;
	    }	  

	    public void setPontos (int pontos)
	    {
	        this.nPontos = pontos;   
	    }
	    
	    public int[] getPontosX ()
	    {
	        return this.pontosX;
	    }

	    public int[] getPontosY ()
	    {
	        return this.pontosY;	        
	    }

	    public int getPontos ()
	    {	    	
	    	return this.nPontos;
	    }
	    
	    
	    public void torneSeVisivel (Graphics g)
	    {

	    	g.setColor		(this.corFundo);
	        g.fillPolygon	(this.pontosX, this.pontosY, nPontos);	
	        g.setColor 		(this.cor);	        
	        g.drawPolygon 	(this.pontosX, this.pontosY, nPontos);
	        
	    }
	
	public PoligonoIrr (String s)
	{
		 StringTokenizer quebrador = new StringTokenizer(s,":");

	        quebrador.nextToken();

	        int [] x = new int[10];
	        int [] y = new int[10];
	        
	        for (int i = 0; i<=10; i++){
	        	x[i] = Integer.parseInt(quebrador.nextToken());	        	
	        }
	        for (int i = 0; i<=10; i++){
	        	y[i] = Integer.parseInt(quebrador.nextToken());	        	
	        }
	        	       
	        int nPontos       = Integer.parseInt(quebrador.nextToken());
	        
	        Color cor = new Color ( Integer.parseInt(quebrador.nextToken()),  // R
					        		Integer.parseInt(quebrador.nextToken()),  // G
					        		Integer.parseInt(quebrador.nextToken())); // B
	        
        Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
					        		Integer.parseInt(quebrador.nextToken()),  // G
					        		Integer.parseInt(quebrador.nextToken())); // B

	        this.pontosX = x;
	        this.pontosY = y;
	        this.nPontos = nPontos;
	        this.cor   = cor;
	        this.corFundo = corFundo; 
		
	}
	
	  public String toString()
	    {
	        return "e:" +
	               this.pontosX +
	               ":" +
	               this.pontosY +
	               ":" +
	               this.nPontos +
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
