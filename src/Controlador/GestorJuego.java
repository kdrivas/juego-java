/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Graphics;
import Vista.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author Carlos
 */
public class GestorJuego implements EstadoJuego{

    GestorLaberinto gestorMapa;
    HojaSprites hoja = new HojaSprites("../src/imagenes/todos.png", 819, 460);
    
    @Override
    public void actualizar() {
    }

    @Override
    public void dibujar(Graphics g) {
        BufferedImage imagen = hoja.SpriteKey("PARED");
        g.drawImage(imagen, 100, 100, null);
    }
    
    
}
