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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class VentanaInformacion {

    private HojaSprites hoja;

    public VentanaInformacion(HojaSprites hoja) {
        this.hoja = hoja;
    }

    public void dibujar(Graphics g, GestorAvatar gestorAvatar) {

        g.drawImage(hoja.SpriteKey("MENU_LATERAL"), Constantes.ANCHO_VENTANA - 300, 0, null);

        g.setColor(Color.white);
        g.drawString("NOMBRE    : " + gestorAvatar.getAvatar().getNombre(), Constantes.ANCHO_VENTANA + 5 - 300, 80);
        if (gestorAvatar.getAvatar().getArma() == null) {
            g.drawString("ARMA  : Sin Arma", Constantes.ANCHO_VENTANA + 5 - 300, 100);
        } else {
            g.drawString("ARMA  : " + gestorAvatar.getAvatar().getArma(), Constantes.ANCHO_VENTANA + 5 - 300, 100);
        }        
        if (gestorAvatar.getAvatar().getArmadura() == null) {
            g.drawString("ARMADURA  : Sin Armadura", Constantes.ANCHO_VENTANA + 5 - 300, 120);
        } else {
            g.drawString("ARMADURA  : " + gestorAvatar.getAvatar().getArmadura(), Constantes.ANCHO_VENTANA + 5 - 300, 120);
        }

        g.drawString("VIDA      : " + gestorAvatar.getAvatar().getVidaActual(), Constantes.ANCHO_VENTANA + 5 - 300, 140);

        ArrayList<Artefacto> lstArtefacto = gestorAvatar.getAvatar().getMochila().getListaDeArtefactos();
        
        if(lstArtefacto.size() >= 0 && lstArtefacto.size() < 9){
            g.drawString("MOCHILA :", Constantes.ANCHO_VENTANA + 5 - 300, 160);
        }
        else{
            g.drawString("MOCHILA : (Mochila Llena)", Constantes.ANCHO_VENTANA + 5 - 300, 160);
        }
        g.drawString("(Presionar las teclas numericas para interactuar con",Constantes.ANCHO_VENTANA + 5 - 300,180);
        g.drawString("los artefactos)",Constantes.ANCHO_VENTANA + 5 - 300,200);
        
        if (lstArtefacto.size() == 0) {
            g.drawString("Mochila vacia", Constantes.ANCHO_VENTANA + 5 - 300, 220);
        } else {
            for (int i = 0; i < lstArtefacto.size(); i++) {
                g.drawString("" + (i+1)+ ") " + lstArtefacto.get(i).getNombre(), Constantes.ANCHO_VENTANA + 5 - 300, 220 + i * 20);
            }
        }

        //g.drawImage(hoja.SpriteKey("MENU_SUPERIOR"), 170, 0, null);
        //g.drawImage(hoja.SpriteKey("MENU_INFORMACION"), 0, Constantes.ALTO_VENTANA - 175, null);
    }
}
