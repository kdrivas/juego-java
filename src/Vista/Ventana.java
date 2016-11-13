/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Carlos
 */
public class Ventana extends JFrame{
    private String titulo;
    
    public Ventana(String titulo, Renderizador sd){
        this.titulo = titulo;
        System.out.println("#kasjdkasdasjdka");
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(sd, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
