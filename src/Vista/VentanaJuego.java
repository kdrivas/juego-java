/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.*;
/**
 *
 * @author Carlos
 */
public class VentanaJuego {
    
    private boolean funcionando;
    
    private String titulo;
    private int ancho;
    private int alto;
    
    private Renderizador sd;
    private PantallaJuego ventana;
    private GestorJuego ge;
    
    public VentanaJuego(String titulo, int ancho, int alto){
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void iniciarJuego(){
        funcionando = true;
        inicializar();
        buclePrincipal();
    }
    
    private void inicializar(){
        sd = new Renderizador(ancho, alto);
        ventana = new PantallaJuego(titulo, sd);
        ge = new GestorJuego();
    }
    
    private void actualizar(){
        sd.teclado.actualizar();
        ge.actualizar();
    }
    
    private void dibujar(){
        sd.dibujar(ge);
    }
    
    private void buclePrincipal(){
        ////////////////////////////////////////////////////////////////////
        //Fija numero de actulizaciones del frame pos 1 seg,,, gracias stackoverflow :D
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        ///////////////////////////////////////////////////////////////////
        
        //Ajustar el foco para que el jugador no tenga que hacer click otra vez dentro de la pantalla para que empiece a jugar
        
        while(funcionando){
            
            //////////////////////////////////////////////////////////////
            //controla actulizaciones del frame
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while(delta >= 1){
                //Aqui actualizo los comandos
                actualizar();
                delta--;
            }
            ///////////////////////////////////////////////////////////////
            
            //Aqui muestro respecto a lo que atucalice
            dibujar();
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                referenciaContador = System.nanoTime();
            }
        }
        
    }
}
