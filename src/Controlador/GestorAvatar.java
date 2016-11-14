/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.*;
import Model.*;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author Carlos
 */
public class GestorAvatar {
    
    private static final int ANCHO_PANTALLA = 1100;
    private static final int ALTO_PANTALLA = 600;
    
    private static final int ANCHO_SPRITE = 48;
    private static final int ALTO_SPRITE = 64;
    
    private double posicionX;
    private double posicionY;
    
    private HojaSprites hoja;
    
    public GestorAvatar(HojaSprites hoja, double posicionX, double posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        
        this.hoja = hoja;
    }
    
    public void actualizar(Teclado teclado){
        System.out.println("ENTRE A GESTOR JUEGO");
        if(teclado.arriba){
            System.out.println("asdkjshda");
            setPosicionY(getPosicionY() - 1);
        }
        else if(teclado.abajo){
            setPosicionY(getPosicionY() + 1);
        }
        else if(teclado.izquierda){
            setPosicionX(getPosicionX() - 1);
        }
        else if(teclado.derecha){
            setPosicionX(getPosicionX() + 1);
        }
        else{
            System.out.println("NO REGISTRO");
        }
    }
    
    public void dibujar(Graphics g){
        int centroX = ANCHO_PANTALLA / 2 - ANCHO_SPRITE / 2;
        int centroY = ALTO_PANTALLA / 2 - ALTO_SPRITE / 2;
    
        //g.setColor(Color.white);
        //g.fillRect(centroX, centroY, ANCHO_SPRITE, ALTO_SPRITE);
        g.drawImage(hoja.SpriteKey("AVATAR"), centroX, centroY, null);
    }

    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }
}
