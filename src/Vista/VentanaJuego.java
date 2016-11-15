/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.*;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Carlos
 */




///SI QUIERES AÑADIR MAS SPRITES VE A LA CLASE HOJASPRITES, AHI ESTA EL HASHMAP
//EL ERROR DEL KEY_LISTENER ESTA MAS ABAJO
//CAMBIE EL GESTOR_LABERINTO, PERO SOLO LE AÑADI UN MOSTRAR




public class VentanaJuego extends Canvas implements Runnable{
    
    private volatile boolean funcionando = false;
    
    private String titulo;
    private int ancho;
    private int alto;

    //Esta es la ventana como me la pediste, creada con la plantilla por defecto de java

    private GestorJuego ge;
    private Teclado teclado;
    private JFrame ventana;
    private Thread thread;
    
    public VentanaJuego(String titulo, int ancho, int alto){
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
        
        setPreferredSize(new Dimension(ancho, alto));
        teclado = new Teclado();
        addKeyListener(teclado);
        funcionando = true;
        setFocusable(true);
        
        ImageIcon icono = new ImageIcon("../src/imagenes/icono.png");
        
        ventana = new JFrame(titulo);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setIconImage(icono.getImage());
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
       
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    //Los inicializo mis gestores
    public synchronized void inicializar(){
        //PRIMERO ENTRA A ESTE CONTRUCTOR, ACA ESTA EL KEYLISTENER
    
        ge = new GestorJuego();
        thread = new Thread(this, "Graficos");
        thread.start();

    }    
        
    private synchronized void detener(){
        funcionando = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    
    //Basicamente llamo a una funcion actualizar del gestor juego por el momento contiene cosas relacionandas al mapa
    //y al movimiento del jugador
    private void actualizar(){
        //ESTO NO FUNCIONA COMO DEBERIA, DE HECHO NI SIQUIERA ENTRA SEGUN LOS PRINTF QUE HICE
        teclado.actualizar();           
        
        //Aca si entra, esto llama a mi actualizador del gestor juego
        ge.actualizar(teclado);
    }
    
    //Lo mismo que actualizar(), contiene mi sd, que basicamente es mi renderizador donde se dibujara todo
    private void dibujar(){
        //Aca dibuja :D, esto si funca
        BufferStrategy buffer = getBufferStrategy();
        
        if(buffer == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = buffer.getDrawGraphics();
        
        g.setColor(Color.black);
        g.fillRect(0, 0, ancho, alto);
        
        //ACAAA
        ge.dibujar(g);
        
        Toolkit.getDefaultToolkit().sync();
        
        g.dispose();
        
        buffer.show();
    }
    
    //Aca esta como funciona --> solo debes saber que este bucle dibujara y actualizara 60 veces por segundo
    //los calculos y variables que aparecen aca basicamente controlan eso, no cambies nada de esto sino el juego
    //ira mas rapido o mas lento

    @Override
    public void run(){
        ////////////////////////////////////////////////////////////////////
        //Fija numero de actulizaciones del frame pos 1 seg
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 10;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        ///////////////////////////////////////////////////////////////////
        
        //Ajustar el foco para que el jugador no tenga que hacer click otra vez dentro de la pantalla para que empiece a jugar
        requestFocus();
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
