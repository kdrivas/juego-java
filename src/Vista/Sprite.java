/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.image.BufferedImage;

/**
 *
 * @author Carlos
 */
public class Sprite {
    public BufferedImage imagen;
    
    private int ancho;
    private int alto;
    
    public Sprite(BufferedImage imagen){
        this.imagen = imagen;
        
        alto = imagen.getHeight();
        ancho = imagen.getWidth();
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
}
