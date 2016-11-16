/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.*;
import java.awt.Graphics;
import Vista.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Carlos
 */
public class GestorJuego implements EstadoJuego{

    VentanaInformacion ventanaInformacion;    
    GestorLaberinto gestorMapa;
    GestorAvatar gestorAvatar;
    //Como funciona la hoja de sprites es secundario, si funciona y solo carga un bufferedImage y lo corta
    HojaSprites hoja = new HojaSprites();
    
    public GestorJuego(HojaSprites hoja, GestorLaberinto gestorMapa, GestorAvatar gestorAvatar, VentanaInformacion ventanaInformacion){
        this.hoja = hoja;
        this.gestorMapa = gestorMapa;
        this.gestorAvatar = gestorAvatar;
        this.ventanaInformacion = ventanaInformacion;   
    }
   
    @Override
    public void actualizar(Teclado teclado) {        
        //ESTO NO FUNCIONA, AL PARECER EL KEY LISTENER NO SE AÑADE CORRECTAMENTE A LA PANTALLA      
        gestorMapa.actualizar(gestorAvatar);
        gestorAvatar.actualizar(teclado, gestorMapa, gestorMapa.getNivel());       
    }

    //Dibuja todas las cosas, por el momento llama a gestorMapa y gestorAvatar
    @Override
    public void dibujar(Graphics g) {
        gestorMapa.dibujar(g, (int)gestorAvatar.getPosicionX(), (int)gestorAvatar.getPosicionY());
        gestorAvatar.dibujar(g);
        ventanaInformacion.dibujar(g, gestorAvatar);            
        
        g.setColor(Color.green);
    }
    
    
}
