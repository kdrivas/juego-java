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




///SI QUIERES AÑADIR MAS SPRITES VE A LA CLASE HOJASPRITES, AHI ESTA EL HASHMAP
//EL ERROR DEL KEY_LISTENER ESTA MAS ABAJO
//CAMBIE EL GESTOR_LABERINTO, PERO SOLO LE AÑADI UN MOSTRAR




public class VentanaJuego {
    
    private boolean funcionando;
    
    private String titulo;
    private int ancho;
    private int alto;
    
    private Renderizador sd;
    //Esta es la ventana como me la pediste, creada con la plantilla por defecto de java
    private PantallaJuego ventana;
    private GestorJuego ge;
    
    public VentanaJuego(String titulo, int ancho, int alto){
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void iniciarJuego(){
        //Todo  comienza con esta parte
        //setteo la variable para que no se salga el programa
        funcionando = true;
        
        //Inicializo todos mis gestores
        inicializar();
        //De este bucle no sale hasta que el jugador pierda --> ve al bucle aca esta todo el funcionamiento
        buclePrincipal();
    }
    
    //Los inicializo mis gestores
    private void inicializar(){
        //PRIMERO ENTRA A ESTE CONTRUCTOR, ACA ESTA EL KEYLISTENER
        sd = new Renderizador(ancho, alto);
        
        //creo la ventana
        ventana = new PantallaJuego(titulo, sd);
        ge = new GestorJuego();
    }
    
    //Basicamente llamo a una funcion actualizar del gestor juego por el momento contiene cosas relacionandas al mapa
    //y al movimiento del jugador
    private void actualizar(){
        //ESTO NO FUNCIONA COMO DEBERIA, DE HECHO NI SIQUIERA ENTRA SEGUN LOS PRINTF QUE HICE
        GestorTeclado.objTeclado.actualizar();
        
        
        //Aca si entra, esto llama a mi actualizador del gestor juego
        ge.actualizar();
    }
    
    //Lo mismo que actualizar(), contiene mi sd, que basicamente es mi renderizador donde se dibujara todo
    private void dibujar(){
        //Aca dibuja :D, esto si funca
        sd.dibujar(ge);
    }
    
    //Aca esta como funciona --> solo debes saber que este bucle dibujara y actualizara 60 veces por segundo
    //los calculos y variables que aparecen aca basicamente controlan eso, no cambies nada de esto sino el juego
    //ira mas rapido o mas lento
    private void buclePrincipal(){
        ////////////////////////////////////////////////////////////////////
        //Fija numero de actulizaciones del frame pos 1 seg
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
