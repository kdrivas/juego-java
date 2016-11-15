/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.*;
import Model.*;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author Carlos
 */
public class VentanaInformacion {
    private HojaSprites hoja;
    
    public VentanaInformacion(HojaSprites hoja){
        this.hoja = hoja;
    }
    
    public void dibujar(Graphics g, GestorAvatar gestorAvatar){
        g.setColor(Color.decode("#751C09"));
        g.fillRect(200, Constantes.ALTO_VENTANA - 135, 600, 150);
        
        g.setColor(Color.red);
        for(int i=0; i<gestorAvatar.getAvatar().getVidaActual(); i++){
            g.drawRect(400 + i, Constantes.ALTO_VENTANA - 50, 50, 50);
        }
        
        g.drawImage(hoja.SpriteKey("MENU_SUPERIOR"), 170, 0, null);
        g.drawImage(hoja.SpriteKey("MENU_INFORMACION"), 0, Constantes.ALTO_VENTANA - 175, null);
    }
}
