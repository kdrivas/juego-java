/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Graphics;
import Model.*;
import Controlador.*;
/**
 *
 * @author alulab14
 */
public interface EstadoJuego {
    void dibujar(Graphics g);
    
    void actualizar(Teclado teclado);
}
