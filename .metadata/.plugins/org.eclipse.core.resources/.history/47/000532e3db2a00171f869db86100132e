import java.awt.*;
import java.util.*;

public class Circulo extends Figura
{
    protected Ponto centro;
    protected int   raio;
    protected Color corFundo;
	
    public Circulo (int x, int y, int r)
    {
        this (x, y, r, Color.BLACK, Color.WHITE);
    }
	
    public Circulo (int x, int y, int r, Color cor, Color cor2)
    {
        super (cor);

        corFundo = cor2;
        
        this.centro = new Ponto (x,y);
        this.raio   = r;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
        
        Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                					Integer.parseInt(quebrador.nextToken()),  // G
                					Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto (x,y,cor);
        this.raio   = r;
        this.cor    = cor;
        this.corFundo = corFundo;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public void setRaio (int r)
    {
        this.raio = r;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int setRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel (Graphics g)
    {
	g.setColor(this.corFundo);
        g.fillOval(this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
        g.setColor (this.cor);
        g.drawOval (this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);        
			
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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