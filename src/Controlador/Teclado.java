/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Carlos
 */
public class Teclado implements KeyListener{

    //Fije numero de teclas que se pueden presionar, pudo haber sido 4 pero porsiaca meti muchas mas teclas
    private final static int numeroTeclas = 256;
   //Dentro del array se indicaran que teclas se presionaron, cada letra se guardara en el espacio de su codigo ascii dentro de teclas
    private final boolean[] teclas = new boolean[numeroTeclas];
    
    public boolean arriba;
    public boolean abajo;
    public boolean izquierda;
    public boolean derecha;
    public boolean salir;
    public boolean mochila;
    public boolean atacar;
    public boolean huir;
    public boolean interactuar;
    
    public Teclado(){
        System.out.println("Cree teclado");
    }
    
    //Settea las teclas presionadas y liberadas dentro del arreglo de teclas
    public void actualizar(){
        System.out.println("Estoy actualizando teclado");
        
        arriba = teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_Z];
        derecha = teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_A];
        mochila = teclas[KeyEvent.VK_M];
        atacar = teclas[KeyEvent.VK_X];
        huir = teclas[KeyEvent.VK_H];
        interactuar = teclas[KeyEvent.VK_I];
        salir = teclas[KeyEvent.VK_Q];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Presione teclado");
        teclas[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Libere teclado");
        teclas[e.getKeyCode()] = false;
    }
    
}
