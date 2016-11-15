/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author alulab14
 */
public class GestorArtefactos implements Runnable{

    private ArrayList<Artefacto>objetos;
    private ArrayList<Arma> armas;
    private ArrayList<Armadura> armaduras;
    private ArrayList<Pocion> pociones;
    
    private Graphics graficos;
    
    private Thread thread;
    
    private volatile GestorLaberinto gestorMapa;
    
    public GestorArtefactos() {
        objetos = new ArrayList<>();
        armas = new ArrayList<Arma>();
        armaduras = new ArrayList<Armadura>();
        pociones = new ArrayList<Pocion>();
        
    }

    public void inicializar(GestorLaberinto gestorMapa){
        cargarArtefactos();
        agregarArmaduras(gestorMapa.arrLaberintos);
        agregarArmas(gestorMapa.arrLaberintos);
        agregarPociones(gestorMapa.arrLaberintos);
        
        this.gestorMapa = gestorMapa;
        
        thread = new Thread(this, "Artefactos");
        thread.start();
    }
    
    public void cargarArtefactos() {
        GestorArtefactos g = new GestorArtefactos();
        try {
            XStream xstream = new XStream(new DomDriver());
            FileReader fr = new FileReader("..//src/artefactos.txt");
            g = (GestorArtefactos) xstream.fromXML(fr);
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        
        for (int i = 0; i < g.getObjetos().size(); i++) {
            if (g.getObjetos().get(i) instanceof Arma) {
                Arma a = (Arma) g.getObjetos().get(i);
                getArmas().add(a);
            } else if (g.getObjetos().get(i) instanceof Armadura) {
                Armadura a = (Armadura) g.getObjetos().get(i);
                getArmaduras().add(a);
            } else if (g.getObjetos().get(i) instanceof Pocion) {
                Pocion a = (Pocion) g.getObjetos().get(i);
                getPociones().add(a);
            }
        }
    }
    
    public void agregarPociones(Vector<Laberinto> niveles) {
        double por_aparicion = 0.09, por_pocion_max = 0.3;

        Laberinto lab;
        for (int k = 0; k < niveles.size(); k++) {
            lab = niveles.get(k);
            for (int i = 0; i < lab.getM(); i++) {
                for (int j = 0; j < lab.getN(); j++) {
                    Celda celda = lab.getCeldaLaberinto(i, j);
                    if (celda.getEstado().equals("ADENTRO") && (celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        Random rnd = new Random();
                        if (rnd.nextDouble() < por_aparicion) {
                            //Agregar Pocion
                            if (rnd.nextDouble() < por_pocion_max) {
                                celda.setObjArtefacto(pociones.get(1));
                            } else {
                                celda.setObjArtefacto(pociones.get(0));
                            }
                        }
                    }
                }
            }
        }
    }

    public void agregarArmas(Vector<Laberinto> niveles) {
        double por_aparicion = 0.09;

        Laberinto lab;
        for (int k = 0; k < niveles.size(); k++) {
            lab = niveles.get(k);
            for (int i = 1; i < lab.getM(); i++) {
                for (int j = 1; j < lab.getN(); j++) {
                    Celda celda = lab.getCeldaLaberinto(i, j);
                    if (celda.getEstado().equals("ADENTRO") && (celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        Random rnd = new Random();
                        if (rnd.nextDouble() < por_aparicion) {
                            //Agregar Arma
                            if (k == (niveles.size() - 1)) {
                                int prob1 = rnd.nextInt(3) + 5;
                                celda.setObjArtefacto(armas.get(prob1));
                            } else {
                                int prob2 = rnd.nextInt(6);
                                celda.setObjArtefacto(armas.get(prob2));
                            }
                        }
                    }
                }
            }
        }
    }

    public void agregarArmaduras(Vector<Laberinto> niveles) {
        double por_aparicion = 0.09;

        Laberinto lab;
        for (int k = 0; k < niveles.size(); k++) {
            lab = niveles.get(k);
            for (int i = 1; i < lab.getM(); i++) {
                for (int j = 1; j < lab.getN(); j++) {
                    Celda celda = lab.getCeldaLaberinto(i, j);
                    if (celda.getEstado().equals("ADENTRO") && (celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        Random rnd = new Random();
                        if (rnd.nextDouble() < por_aparicion) {
                            //Agregar Pocion
                            if (k == (niveles.size() - 1)) {
                                int prob1 = (int) (rnd.nextDouble() * 5 + 3);
                                celda.setObjArtefacto(armaduras.get(prob1));
                            } else {
                                int prob2 = rnd.nextInt(6);
                                celda.setObjArtefacto(armaduras.get(prob2));
                            }
                        }
                    }
                }
            }
        }
    }
    
    public ArrayList<Artefacto> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Artefacto> objetos) {
        this.objetos = objetos;
    }

    public ArrayList<Arma> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<Arma> armas) {
        this.armas = armas;
    }

    public ArrayList<Armadura> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(ArrayList<Armadura> armaduras) {
        this.armaduras = armaduras;
    }

    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    public void setPociones(ArrayList<Pocion> pociones) {
        this.pociones = pociones;
    }
    
    @Override
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 1;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        ///////////////////////////////////////////////////////////////////
        
        //Ajustar el foco para que el jugador no tenga que hacer click otra vez dentro de la pantalla para que empiece a jugar
        while(true){
            
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
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                referenciaContador = System.nanoTime();
            }
        }
    }
    
    public void actualizar(){
        Random rn = new Random();
       
        int mover_x  = 0, mover_y = 0;
        
        for(int x = 0; x < gestorMapa.arrLaberintos.get(0).getM(); x++){
            for(int y = 0; y < gestorMapa.arrLaberintos.get(0).getN(); y++){
                mover_x = 0;
                mover_y = 0;
                Celda celda = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y);
                if(celda.getObjArtefacto() != null && celda.getObjArtefacto() instanceof Artefacto){
                    int dir = (int) (rn.nextDouble() * 4);
                    switch (dir) {
                        case 1://derecha
                            mover_x += 1;
                            break;
                        case 2://izquierda
                            mover_x -= 1;
                            break;
                        case 3://arriba
                            mover_y -=1;
                            break;
                        case 4://abajo
                            mover_y += 1;
                            break;
                    }// recorro laberinto y por cada enemigo veo intento mover
                    if(x + mover_x >= 0 && x + mover_x < gestorMapa.arrLaberintos.get(0).getM() 
                            && y + mover_y >= 0 && y + mover_y < gestorMapa.arrLaberintos.get(0).getN()){
                        Celda celdaSiguiente = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x + mover_x, y + mover_y);
                        Celda celdaActual = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y);

                        if (!celdaActual.getObjArtefacto().isMeMovi() && celdaSiguiente.getEstado() == "ADENTRO" && celdaSiguiente.getObjEntidad() == null && celdaSiguiente.getObjArtefacto() == null) {
                            celdaActual.getObjArtefacto().setMeMovi(true);
                            celdaSiguiente.setObjArtefacto(celdaActual.getObjArtefacto());
                            celdaActual.setObjArtefacto(null);
                        }
                    }
                }              
            }
        }
        
        for(int x = 0; x < gestorMapa.arrLaberintos.get(0).getM(); x++){
            for(int y = 0; y < gestorMapa.arrLaberintos.get(0).getN(); y++){
                if(gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y).getObjArtefacto() != null)
                    gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y).getObjArtefacto().setMeMovi(false);
            }
        }
        gestorMapa.arrLaberintos.get(0).imprimir();
    }
}
