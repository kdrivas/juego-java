/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Canvas;
import Controlador.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
/**
 *
 * @author Carlos
 */
public class Renderizador extends Canvas{
    private int ancho;
    private int alto;
    
    public GestorTeclado teclado;
    
    public Renderizador(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        teclado = new GestorTeclado();
        setPreferredSize(new Dimension(getAncho(), getAlto()));
        addKeyListener(teclado);
        setFocusable(true);
        requestFocus();
    }
    
    public void dibujar(GestorJuego ge){
        BufferStrategy buffer = getBufferStrategy();
        
        if(buffer == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = buffer.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getAncho(), getAlto());
        
        ge.dibujar(g);
        
        g.dispose();
        
        buffer.show();
    }

    /**
     * @return the ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * @return the alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }
}
