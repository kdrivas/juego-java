/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
        gestorAvatar = new GestorAvatar(hoja, 0, 0);
    }

    public void actualizar(Teclado teclado) {
        
        //ESTO NO FUNCIONA, AL PARECER EL KEY LISTENER NO SE AÑADE CORRECTAMENTE A LA PANTALLA
        System.out.println("ENTRE A GESTOR JUEGO");
        if(teclado.arriba){
            System.out.println("asdkjshda");
            gestorAvatar.setPosicionY(gestorAvatar.getPosicionY() - 1);
        }
        else if(teclado.abajo){
            gestorAvatar.setPosicionY(gestorAvatar.getPosicionY() + 1);
        }
        else if(teclado.izquierda){
            gestorAvatar.setPosicionX(gestorAvatar.getPosicionX() - 1);
        }
        else if(teclado.derecha){
            gestorAvatar.setPosicionX(gestorAvatar.getPosicionX() + 1);
        }
        else{
            System.out.println("NO REGISTRO");
        }
    }

    //Dibuja todas las cosas, por el momento llama a gestorMapa y gestorAvatar
    public void dibujar(Graphics g) {
        gestorMapa.dibujar(g, 0, (int)gestorAvatar.getPosicionX(), (int)gestorAvatar.getPosicionY());
        gestorAvatar.dibujar(g);
        
        g.setColor(Color.green);
        
        //INDICADOR PARA VER SI EL AVATAR AVANZA,, NO FUNCA PRUEBALO SE QUEDARA SOLO MARCANDO LA POSICION 1, 1
        System.out.println("X = " + gestorAvatar.getPosicionX());
        System.out.println("Y = " + gestorAvatar.getPosicionY());

    }
    
    
}
