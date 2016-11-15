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
    int nivel;
    boolean cambioNivel = true;
    
    public GestorJuego(){
        gestorMapa = new GestorLaberinto(hoja); 
        gestorAvatar = new GestorAvatar(hoja, gestorMapa.arrLaberintos.get(0).getIniX() * Constantes.ANCHO_JUGADOR, gestorMapa.arrLaberintos.get(0).getIniY() * Constantes.ALTO_JUGADOR);
        nivel = 0;
    }

    private int actualizoNivel(){
        Laberinto laberinto = gestorMapa.arrLaberintos.get(nivel);
        Celda celdaInicio   = laberinto.getCeldaLaberinto(laberinto.getIniX(), laberinto.getIniY());
        Celda celdaFin      = laberinto.getCeldaLaberinto(laberinto.getFinX(), laberinto.getFinY());
        
        if(celdaFin.getObjEntidad() instanceof Avatar){
            if(nivel < 3){
                cambioNivel = true;
                return 1;
            }
            else
                return 0;
        }
        else
            return 0;
    }
    
    private void colocarAvatar(){
        if(cambioNivel){
            Laberinto laberinto = gestorMapa.arrLaberintos.get(nivel);
            laberinto.getCeldaLaberinto(laberinto.getIniX(), laberinto.getIniY()).setObjEntidad(gestorAvatar.getAvatar());
            gestorAvatar.setPosicionX(laberinto.getIniX() * Constantes.ANCHO_JUGADOR);
            gestorAvatar.setPosicionY(laberinto.getIniY() * Constantes.ALTO_JUGADOR);
            cambioNivel = false;
        }
    }
    
    public void actualizar(Teclado teclado) {
        
        //ESTO NO FUNCIONA, AL PARECER EL KEY LISTENER NO SE AÑADE CORRECTAMENTE A LA PANTALLA
        colocarAvatar();
        nivel += actualizoNivel();
        gestorAvatar.actualizar(teclado, gestorMapa, nivel);       
    }

    //Dibuja todas las cosas, por el momento llama a gestorMapa y gestorAvatar
    public void dibujar(Graphics g) {
        gestorMapa.dibujar(g, nivel, (int)gestorAvatar.getPosicionX(), (int)gestorAvatar.getPosicionY());
        gestorAvatar.dibujar(g);
        
        g.setColor(Color.green);
    }
    
    
}
