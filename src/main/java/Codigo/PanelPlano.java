package Codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;

public class PanelPlano extends JPanel
{
    /* Variables */
    private Graphics g;
    private int w, h, escala, x = 0, y = 0;
    private String eje = "Original";
    
    /* Constructor */
    public PanelPlano()
    {
        componentes();
    }
    
    /* Componentes Panel */
    private void componentes()
    {
        /* Panel Inicio */
        setSize(560, 400);
        setLayout(null);
        setBackground(Color.lightGray);
        w = getWidth();
        h = getHeight();
        escala = 30;
        addMouseWheelListener((MouseWheelEvent e) ->
        {
            /* Acercar o Alejar Plano */
            if(e.getUnitsToScroll() > 0)
            {
                escala -= 2;
            }
            else
            {
                escala += 2;
            }
            
            /* Verifico que no Pase de Negativos */
            if(escala <= 0)
            {
                escala = 1;
            }
            repaint();
        });
        
    }
    public void dibujarLinea(int[] x)
    {
        g.setColor(Color.BLUE);
        g.drawLine(w / 2, h / 2, w / 2 + x[0] * escala, h / 2 - x[1] * escala);
    }
    
    public void reflexionX(int xy[])
    {
        int arregloOriginal[] = xy;
        int arregloMultiplicadoX[][] = new int[2][2];
        int reflexionX[] = new int[2];
        
        int matrizX[][] =
        {
            {1, 0},
            {0 , -1}
        };
        for(int a = 0; a < matrizX[0].length; a++)
        {
            for(int b = 0;b < matrizX.length; b++)
            {
                arregloMultiplicadoX[a][b] = matrizX[a][b] * arregloOriginal[a];
                reflexionX[a] = arregloMultiplicadoX[a][a];
            }
        }
        dibujarLinea(reflexionX);
    }
    
    public void reflexionY(int xy[])
    {
        int arregloOriginal[] = xy;
        int arregloMultiplicadoY[][] = new int[2][2];
        int reflexionY[] = new int[2];
        
        int[][] matrizY =
        {
            {-1, 0},
            {0 , 1}
        };
        for(int a = 0; a < matrizY[0].length; a++)
        {
            for(int b = 0;b < matrizY.length; b++)
            {
                arregloMultiplicadoY[a][b] = matrizY[a][b] * arregloOriginal[a];
                reflexionY[a] = arregloMultiplicadoY[a][a];
            }
        }
        dibujarLinea(reflexionY);
    }
    
    public void pintarEje(Graphics g, String eje, int x, int y)
    {
        int arregloOriginal[] = {x, y};
        
        switch(eje)
        {
            case "Original" ->
            {
                dibujarLinea(arregloOriginal);
                break;
            }
            case "Eje X" ->
            {
                reflexionX(arregloOriginal);
                break;
            }
            case "Eje Y" ->
            {
                reflexionY(arregloOriginal);
                break;
            }
            case "Todos" ->
            {
                dibujarLinea(arregloOriginal);
                reflexionX(arregloOriginal);
                reflexionY(arregloOriginal);
                break;
            }
        }
    }
    
    public void setDatos(String eje, int x, int y)
    {
        this.eje = eje;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        this.g = g;
        
        /* Lineas */
        g.setColor(Color.black);
        
        /* Lineas Verticales */
        int x2 = w / 2;
        for(int a = w / 2; a < w; a += escala)
        {
            g.drawLine(a, 0, a, h);
            g.drawLine(x2, 0, x2, h);
            x2 = x2 - escala;
        }
        
        /* Lineas Horizontales */
        int y2 = h / 2;
        for(int a = h / 2; a < h; a += escala)
        {
            g.drawLine(0, a, w, a);
            g.drawLine(0, y2, w, y2);
            y2 = y2 - escala;
        }
        
         /* Lineas Centrales */
        g.setColor(Color.red);
        g.drawLine(w / 2, 0, w / 2, h); // Linea Vertical
        g.drawLine(0, h / 2, w, h / 2); // Linea Horizontal
        
        /* Dibujar Lineas */
        pintarEje(g, eje, x, y);
    }
}

/*
    \____/\
    /\``/\
*/