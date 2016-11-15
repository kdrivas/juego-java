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
public class GestorJuego{

    GestorLaberinto gestorMapa;
    GestorAvatar gestorAvatar;
    //Como funciona la hoja de sprites es secundario, si funciona y solo carga un bufferedImage y lo corta
    HojaSprites hoja = new HojaSprites("../src/imagenes/todos.png", 819, 460);
    
    public GestorJuego(){
        gestorMapa = new GestorLaberinto(hoja); 
        gestorAvatar = new GestorAvatar(hoja, gestorMapa.arrLaberintos.get(0).getIniX() * Constantes.ANCHO_JUGADOR, gestorMapa.arrLaberintos.get(0).getIniY() * Constantes.ALTO_JUGADOR);
        gestorMapa.setNivel(0);
    }

    
    
    public void actualizar(Teclado teclado) {
        
        //ESTO NO FUNCIONA, AL PARECER EL KEY LISTENER NO SE AÑADE CORRECTAMENTE A LA PANTALLA      
        gestorMapa.actualizar(gestorAvatar);
        gestorAvatar.actualizar(teclado, gestorMapa, gestorMapa.getNivel());       
    }

    //Dibuja todas las cosas, por el momento llama a gestorMapa y gestorAvatar
    public void dibujar(Graphics g) {
        gestorMapa.dibujar(g, (int)gestorAvatar.getPosicionX(), (int)gestorAvatar.getPosicionY());
        gestorAvatar.dibujar(g);
        
        g.setColor(Color.green);
    }
    
    
}
