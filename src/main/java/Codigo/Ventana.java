package Codigo;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class Ventana extends JFrame
{
    /* Variables */
    String ejes[] = {"Original", "Eje X", "Eje Y", "Todos"};
    
    PanelPlano pPlano;
    JButton btnGraficar;
    JTextField inputX, inputY;
    JComboBox eje = new JComboBox(ejes);
    
    /* Constructor */
    public Ventana()
    {
        componentes();
    }
    private void componentes()
    {
        /* Ventana */
        setTitle("Graficadora");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        
        /* Panel Inicio */
        pPlano = new PanelPlano();
        add(pPlano);
        pPlano.setLocation(13, 10);
        
        /* Inputs X y Y */
        inputX = new JTextField("3");
        add(inputX);
        inputX.setBounds(100, 425, 75, 25);
        
        inputY = new JTextField("4");
        add(inputY);
        inputY.setBounds(180, 425, 75, 25);
        
        /* Opciones */
        add(eje);
        eje.setBounds(260, 425, 90, 25);
        
        /* Boton Graficar */
        btnGraficar = new JButton("Graficar");
        add(btnGraficar);
        btnGraficar.setBounds(365, 425, 100, 25);
        btnGraficar.addActionListener((ActionEvent e) ->
        {
            String uwu = (String) eje.getModel().getSelectedItem();
            int x = Integer.parseInt(inputX.getText());
            int y = Integer.parseInt(inputY.getText());
            
            pPlano.setDatos(uwu, x, y);
            
            pPlano.repaint();
        });
    }
}

/*
    \____/\
    /\``/\
*/