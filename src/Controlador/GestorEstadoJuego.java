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
public class GestorEstadoJuego {
    private EstadoJuego[] estados;
    private EstadoJuego estadoActual;
    
    public GestorEstadoJuego(){
        iniciarEstados();
        iniciarEstadoActual();
    }
    
    private void iniciarEstados(){
        estados = new EstadoJuego[1];
        estados[0] = new GestorJuego();
    }
    
    private void iniciarEstadoActual(){
        estadoActual = estados[0];
    }
    
    public void actualizar(){
        estadoActual.actualizar();
    }
    
    public void dibujar(Graphics g){
        estadoActual.dibujar(g);
    }
    
    public void cambiarEstado(int index){
        estadoActual = estados[index];
    }
}