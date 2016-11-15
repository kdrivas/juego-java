/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import FACILIDADES.Aliado;
import Model.Arma;
import Model.Artefacto;
import Model.Celda;
import Model.Enemigo;
import Model.Laberinto;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author alulab14
 */
public class GestorEnemigos implements Runnable{
    
    private ArrayList<Enemigo> enemigos;
    
    private Graphics graficos;
    
    private Thread thread;
    
    private volatile GestorLaberinto gestorMapa;
    
    public GestorEnemigos() {
        enemigos = new ArrayList<>();
    }

    public void inicializar(GestorLaberinto gestorMapa) {
        cargarEnemigos("..//src/enemigos.txt");
        agregarEnemigos(enemigos, gestorMapa.arrLaberintos);
        
        this.gestorMapa = gestorMapa;
        
        thread = new Thread(this, "Enemigos");
        thread.start();
    }
    
    public void cargarEnemigos(String archivo)  {
        
        Enemigo enemigo;
        
        Arma arma = new Arma();
        
        String cadena;
        String palabras[];
        
        int i = 0;
        try {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);
            while ((cadena = b.readLine()) != null) {
                enemigo = new Enemigo();
                StringTokenizer st = new StringTokenizer(cadena, ",");
                while (st.hasMoreTokens()) {
                    enemigo.setNombre(st.nextToken());
                    //arma.setNombre(st.nextToken());
                    //enemigo.setArma(arma);
                    enemigo.setVidaMaxima(Integer.parseInt(st.nextToken()));
                    enemigo.setVidaActual(enemigo.getVidaMaxima());
                    enemigo.setFuerza(Integer.parseInt(st.nextToken()));
                    enemigo.setDefensa(Integer.parseInt(st.nextToken()));
                    enemigo.setNivel(Integer.parseInt(st.nextToken()));
                    enemigos.add(enemigo);
                }
            }
            b.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void agregarEnemigos(ArrayList<Enemigo> lstEnemigos, Vector<Laberinto> niveles) {
        int k;
        Laberinto lab;
        Enemigo enemigoEnLab;

        for (k = 0; k < niveles.size(); k++) {
            lab = niveles.elementAt(k);
            double pct_enemigo = lab.getPct_enemigo();
            Vector<Integer> niveles_enemigo = lab.getNiveles_enemigo();

            for (int i = 0; i < lab.getM(); i++) {
                for (int j = 0; j < lab.getN(); j++) {
                    Celda celda = lab.getCeldaLaberinto(i, j);
                    if (celda.getEstado().equals("ADENTRO") && (celda.getObjArtefacto() == null) && (celda.getObjEntidad() == null)) {
                        Random rnd = new Random();
                        if (rnd.nextDouble() < pct_enemigo) {
                            //Agregar Enemigo
                            int cant_enemigos = lstEnemigos.size();
                            int index_enemigo = rnd.nextInt(cant_enemigos);
                            for (int t = 0; t < niveles_enemigo.size(); t++) {
                                while (lstEnemigos.get(index_enemigo).getNivel() != (k + 1)) {
                                    index_enemigo = rnd.nextInt(cant_enemigos);
                                }
                                //Setteando enemigos en laberinto
                                enemigoEnLab = new Enemigo();
                                enemigoEnLab.setArma(lstEnemigos.get(index_enemigo).getArma());
                                enemigoEnLab.setFuerza(lstEnemigos.get(index_enemigo).getFuerza());
                                enemigoEnLab.setDefensa(lstEnemigos.get(index_enemigo).getDefensa());
                                enemigoEnLab.setNivel(lstEnemigos.get(index_enemigo).getNivel());
                                enemigoEnLab.setNombre(lstEnemigos.get(index_enemigo).getNombre());
                                enemigoEnLab.setVidaActual(lstEnemigos.get(index_enemigo).getVidaActual());
                                enemigoEnLab.setVidaMaxima(lstEnemigos.get(index_enemigo).getVidaMaxima());

                                celda.setObjEntidad(enemigoEnLab);
                            }
                        }
                    }
                }
            }
        }
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
           int x = 0, y;
        Laberinto lab= gestorMapa.arrLaberintos.get(gestorMapa.getNivel());
        int posX =lab.hallarPosXAvatar();
        int posY =lab.hallarPosYAvatar();
  
            
        initEnemigosLab(gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));

         while (x < lab.getM()) {
            y = 0;
            while (y < lab.getN()) {
                if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Enemigo) {
                    Enemigo enemigo = (Enemigo) lab.getCeldaLaberinto(x, y).getObjEntidad();

                    if (!enemigo.isMeMovi()) {
                        enemigo.setMeMovi(true);
                        int sector = hallarSector(posX, posY, x, y);
                        switch (sector) {
                            case 1:
                                if (lab.getCeldaLaberinto(x, y + 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y + 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y + 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y + 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x + 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x + 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x + 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x + 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 2:
                                if (lab.getCeldaLaberinto(x, y + 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y + 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y + 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y + 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x - 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x - 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x - 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x - 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 3:
                                if (lab.getCeldaLaberinto(x, y - 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y - 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y - 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y - 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x - 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x - 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x - 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x - 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 4:
                                if (lab.getCeldaLaberinto(x, y - 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y - 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y - 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y - 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x + 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x + 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x + 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x + 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 5:
                                if (lab.getCeldaLaberinto(x + 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x + 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x + 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x + 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x, y - 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y - 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y - 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y - 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x, y + 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y + 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y + 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y + 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 6:
                                if (lab.getCeldaLaberinto(x, y + 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y + 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y + 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y + 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x - 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x - 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x - 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x - 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x + 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x + 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x + 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x + 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 7:
                                if (lab.getCeldaLaberinto(x - 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x - 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x - 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x - 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x, y - 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y - 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y - 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y - 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x, y + 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y + 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y + 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y + 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                            case 8:
                                if (lab.getCeldaLaberinto(x, y - 1).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x, y - 1).getObjEntidad() == null && lab.getCeldaLaberinto(x, y - 1).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x, y - 1).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x - 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x - 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x - 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x - 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else if (lab.getCeldaLaberinto(x + 1, y).getEstado().equals("ADENTRO") && lab.getCeldaLaberinto(x + 1, y).getObjEntidad() == null && lab.getCeldaLaberinto(x + 1, y).getObjArtefacto() == null) {
                                    lab.getCeldaLaberinto(x + 1, y).setObjEntidad(enemigo);
                                    lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                                } else {
                                    moverEnemigosAleatorio(lab, x, y);
                                }
                                break;
                        }
                    }
                }
                y++;
            }
            x++;
        }

        // reinicio toda la matriz para la siguien la siguiente actualizacion
        for(int i = 0; i < lab.getM(); i++){
            for(int j = 0; j < lab.getN(); j++){
                if(lab.getCeldaLaberinto(i, j).getObjEntidad()!= null)
                    lab.getCeldaLaberinto(i, j).getObjEntidad().setMeMovi(false);
            }
        }
        gestorMapa.arrLaberintos.get(0).imprimir();
    }
     
        public void initEnemigosLab(Laberinto lab) {
        int x = 0, y;
        int posX = lab.hallarPosXAvatar();
        int posY = lab.hallarPosYAvatar();

        while (x < lab.getM()) {
            y = 0;
            while (y < lab.getN()) {
                if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Enemigo) {
                    Enemigo enemigo = (Enemigo) lab.getCeldaLaberinto(x, y).getObjEntidad();
                    enemigo.setMeMovi(false);
                }
                y++;
            }
            x++;
        }
    }
     
    public void moverEnemigosAleatorio(Laberinto lab, int xEnemigo, int yEnemigo) {
        int mover_x = 0, mover_y = 0, dir;
        Enemigo enemigo = (Enemigo) lab.getCeldaLaberinto(xEnemigo, yEnemigo).getObjEntidad();
        Random rn = new Random();// genero direccion aleatoria;
        dir = (int) (rn.nextDouble() * 4);
        switch (dir) {
            case 1://derecha
                mover_x = mover_x + 1;
                break;
            case 2://izquierda
                mover_x = mover_x - 1;
                break;
            case 3://arriba
                mover_y = mover_y - 1;
                break;
            case 4://abajo
                mover_y = mover_y + 1;
                break;
        }// recorro laberinto y por cada enemigo veo intento mover 
        moverEnemigo(lab, dir, lab.getCeldaLaberinto(xEnemigo, yEnemigo), enemigo, xEnemigo + mover_x, yEnemigo + mover_y);
//        if(lab.getCeldaLaberinto(xEnemigo, yEnemigo).getObjEntidad() == null & lab.getCeldaLaberinto(xEnemigo, yEnemigo).getObjArtefacto() == null){
//            lab.getCeldaLaberinto(xEnemigo, yEnemigo).setObjEntidad(null);
//            lab.getCeldaLaberinto(xEnemigo + mover_x, yEnemigo + mover_y).setObjEntidad(enemigo);
//        }
    }

    public int hallarSector(int posXAvatar, int posYAvatar, int xEnemigo, int yEnemigo) {
        if (posXAvatar == xEnemigo & posYAvatar < yEnemigo) {
            return 8;
        } else if (posYAvatar == yEnemigo & posXAvatar < xEnemigo) {
            return 7;
        } else if (posXAvatar == xEnemigo & posYAvatar > yEnemigo) {
            return 6;
        } else if (posYAvatar == yEnemigo & posXAvatar > xEnemigo) {
            return 5;
        } else if (posXAvatar > xEnemigo & posYAvatar < yEnemigo) {
            return 4;
        } else if (posXAvatar < xEnemigo & posYAvatar < yEnemigo) {
            return 3;
        } else if (posXAvatar < xEnemigo & posYAvatar > yEnemigo) {
            return 2;
        } else {
            return 1;
        }
    }

    public void moverEnemigo(Laberinto lab, int nivel, Celda celdaActual, Enemigo ene, int x, int y) {
        Celda celdaSiguiente = new Celda();
        celdaSiguiente = lab.getCeldaLaberinto(x, y);
        //verifico que hay en celda siguiente para saber si se puede mover 
        if ((celdaSiguiente.getEstado().equals("ADENTRO") || celdaSiguiente.getEstado().equals("SIGUIENTE") || celdaSiguiente.getEstado().equals("ANTERIOR")) && (celdaSiguiente.getObjEntidad() == null) && celdaSiguiente.getObjArtefacto() == null) {
            celdaActual.setObjEntidad(null);
            celdaSiguiente.setObjEntidad(ene);
        }
    }

  
    
}
