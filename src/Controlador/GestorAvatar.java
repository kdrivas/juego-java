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
import java.awt.Rectangle;
/**
 *
 * @author Carlos
 */
public class GestorAvatar {
    private static final int FRAMES = 30;
    
    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - 22, Constantes.CENTRO_VENTANA_Y - 5, Constantes.ANCHO_JUGADOR - 6, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - 22, Constantes.CENTRO_VENTANA_Y + 31, Constantes.ANCHO_JUGADOR - 6, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - 24, Constantes.CENTRO_VENTANA_Y - 2, 1, Constantes.ALTO_JUGADOR / 2);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + 20, Constantes.CENTRO_VENTANA_Y - 2, 1, Constantes.ALTO_JUGADOR / 2);
    
    
    private double posicionX;
    private double posicionY;
    
    private char direccion = 'n';
    private String nombreSprite = "JUGADOR_ARRIBA";
    
    private HojaSprites hoja;
    
    private int animacion = 0;
    private boolean enMovimiento = false;
    
    private GestorLaberinto lab;
    
    private Avatar avatar;
    
    public GestorAvatar(HojaSprites hoja, double posicionX, double posicionY){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        
        this.hoja = hoja;
        this.lab = lab;
        
        avatar = new Avatar();
    }
    
    public void modificaPosicionAvatarEnLaberinto(GestorLaberinto gt, int nivel, int posFuturaX, int posFuturaY){
        Celda celdaSiguiente = gt.arrLaberintos.get(nivel).getCeldaLaberinto(posFuturaX / 48, posFuturaY / 64);
        Celda celdaActual = gt.arrLaberintos.get(nivel).getCeldaLaberinto((int)posicionX / 48, (int)posicionY / 64);
        celdaActual.setObjEntidad(null);
        celdaSiguiente.setObjEntidad(getAvatar());
        
        gt.arrLaberintos.get(nivel).imprimir();
    }
    
    public void actualizar(Teclado teclado,  GestorLaberinto gt, int nivel){
        if (animacion < 32767) {
            animacion++;
        } else {
            animacion = 0;
        }
        
        if(teclado.arriba){          
            direccion = 'n';
            if(!enColision(gt, nivel, (int)posicionX, (int)(posicionY - 64))){
                setEnMovimiento(true);
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int)posicionX, (int)(posicionY - 64)); 
                posicionY -= 64;
                              
            }
            else{
                setEnMovimiento(false);
            }
        }
        else if(teclado.abajo){
            direccion = 's';
            
            if(!enColision(gt, nivel, (int)posicionX, (int)(posicionY + 64))){
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int)posicionX , (int)(posicionY + 64)); 
                posicionY += 64;
                setEnMovimiento(true);               
            }
            else{
                setEnMovimiento(false);
            }
        }
        else if(teclado.izquierda){
            direccion = 'o';
            
            if(!enColision(gt, nivel, (int)(posicionX - 48), (int)posicionY)){
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int)posicionX - 48, (int)posicionY); 
                posicionX -= 48;
                setEnMovimiento(true);
                  
            }
            else{
                setEnMovimiento(false);
            }
        }
        else if(teclado.derecha){
            direccion = 'e';
            
            if(!enColision(gt, nivel, (int)(posicionX + 48), (int)posicionY)){
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int)posicionX + 48, (int)posicionY); 
                posicionX += 48;
                setEnMovimiento(true);
                  
            }
            else{
                setEnMovimiento(false);
            }
        }
        else{
            setEnMovimiento(false);
        }
        
        escogeSprite();
    }
    
    public void dibujar(Graphics g){
        int centroX = Constantes.ANCHO_VENTANA / 2 - Constantes.ANCHO_JUGADOR / 2;
        int centroY = Constantes.ALTO_VENTANA / 2 - Constantes.ALTO_JUGADOR / 2;
    
        g.setColor(Color.green);
        //g.fillRect(centroX, centroY, ANCHO_SPRITE, ALTO_SPRITE);
        g.drawImage(hoja.SpriteKey(nombreSprite), centroX, centroY, null);
        
        
        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);

    }

    //Aca escojo el nombre de la imagen que se escribira
    private void escogeSprite(){
        if (direccion == 'n') {
            nombreSprite = "JUGADOR_ARRIBA";
            if (isEnMovimiento()) {
                System.out.println("lalalalalala");
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "SIGUIENTE";
                } else {
                    nombreSprite = "ANTERIOR";
                }
            }
        } else if (direccion == 's') {
            nombreSprite = "JUGADOR_ABAJO";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "SIGUIENTE";
                } else {
                    nombreSprite = "ANTERIOR";
                }
            }
        } else if (direccion == 'o') {
            nombreSprite = "JUGADOR_IZQUIERDA";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "SIGUIENTE";
                } else {
                    nombreSprite = "ANTERIOR";
                }
            }
        } else if (direccion == 'e') {
            nombreSprite = "JUGADOR_DERECHA";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "SIGUIENTE";
                } else {
                    nombreSprite = "ANTERIOR";
                }
            }
        }
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
    
    private boolean enColision(GestorLaberinto gtr, int nivel, int posFuturaX, int posFuturaY){
        
        
        //CAMBIAR ACA CUANDO ESTEN LISTOS LOS ENEMIGOS
        
        Celda celda = gtr.arrLaberintos.get(nivel).getCeldaLaberinto(posFuturaX / 48, posFuturaY / 64);
        if(celda.getEstado().equals("PARED")){
            return true;
        }
        else{
            return false;
        }
        //return gtr.rec.intersects(LIMITE_ARRIBA) || gtr.rec.intersects(LIMITE_ABAJO) || gtr.rec.intersects(LIMITE_IZQUIERDA);
       
    }

    /**
     * @return the avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the enMovimiento
     */
    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    /**
     * @param enMovimiento the enMovimiento to set
     */
    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }
}
