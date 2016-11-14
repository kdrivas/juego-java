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
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
/**
 *
 * @author Carlos
 */
public class Renderizador extends Canvas{
    private int ancho;
    private int alto;
    
    public Renderizador(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;     
        
        //Inicializo el canvas, que es mi lienzo donde dibujare
        setPreferredSize(new Dimension(getAncho(), getAlto()));
        
        //ESTO DEBERIA FUNCIONAR, PERO CREO QUE NO
        addKeyListener(GestorTeclado.objTeclado);
        ////////////////////////////////////7
        System.out.println("CONTROL");
        //Esto es solo por si nos piden de modificacion en el lab, crea un cursor, no pruebes esto, es secundario
        setCursor(GestorCursor.objCursor.cursor);
        setFocusable(true);
        requestFocusInWindow();
        requestFocus();
    }
    
    //Solo debes saber que esto crea un buffer y dibuja lo que hay en gestor juego,,,,, ve a ge.dibujar(g)
    public void dibujar(GestorJuego ge){
        BufferStrategy buffer = getBufferStrategy();
        
        if(buffer == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = buffer.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, getAncho(), getAlto());
        
        //ACAAA
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
