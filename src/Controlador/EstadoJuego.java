/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Graphics;

/**
 *
 * @author Carlos
 */
public interface  EstadoJuego {
    void actualizar();
    
    void dibujar(final Graphics g);
}
