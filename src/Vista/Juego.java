/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GestorArtefactos;
import FACILIDADES.Aliado;
import Controlador.GestorLaberinto;
import java.util.Scanner;
import Model.*;
import Vista.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class Juego {

    private boolean inicio = true;
    
    private boolean fin = false;
    private boolean meMovi;
    private PantallaBienvenida pantallaInicio;
    //private GestorLaberinto glab = new GestorLaberinto(new HojaSprites("asdadsa", 243, 342));
    private int nivelActual = 0;
            private Avatar pj = new Avatar();
            private Vector<Laberinto> niveles = new Vector<>();
            private ArrayList<Arma> armas = new ArrayList<>();
            private ArrayList<Armadura> armaduras = new ArrayList<>();
            private ArrayList<Pocion> pociones = new ArrayList<>();
            
    /**
     * @param args the command line arguments
     */
    //Setteo cantidad de niveles, tambien debe ser modificado en gestor laberinto
    static final int CANT_NIVELES = 3;

    public Juego() {
        //niveles = glab.crear(20, 20, pj);
        
        
    }

    public void cargarArtefactos(ArrayList<Arma> armas, ArrayList<Armadura> armaduras, ArrayList<Pocion> pociones) {
//        GestorArtefactos g = new GestorArtefactos();
//        try {
//            XStream xstream = new XStream(new DomDriver());
//            FileReader fr = new FileReader("..\\src\\artefactos.txt");
//            g = (GestorArtefactos) xstream.fromXML(fr);
//            fr.close();
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
//        for (int i = 0; i < g.getObjetos().size(); i++) {
//            if (g.getObjetos().get(i) instanceof Arma) {
//                Arma a = (Arma) g.getObjetos().get(i);
//                armas.add(a);
//            } else if (g.getObjetos().get(i) instanceof Armadura) {
//                Armadura a = (Armadura) g.getObjetos().get(i);
//                armaduras.add(a);
//            } else if (g.getObjetos().get(i) instanceof Pocion) {
//                Pocion a = (Pocion) g.getObjetos().get(i);
//                pociones.add(a);
//            }
//        }
    }

    public void jugar() throws IOException {
        setPantallaInicio(new PantallaBienvenida());
//        while (!fin) {
//            boolean salir = false;
//            int nivelActual = 0;
//            Scanner sc = new Scanner(System.in);
//            GestorLaberinto glab = new GestorLaberinto();
//            Avatar pj = new Avatar();
//            Vector<Laberinto> niveles = new Vector<>();
//            ArrayList<Arma> armas = new ArrayList<>();
//            ArrayList<Armadura> armaduras = new ArrayList<>();
//            ArrayList<Pocion> pociones = new ArrayList<>();
//            //cargar los enemigos y objetos
//            ArrayList<Enemigo> enemigos = cargarEnemigos("..\\src\\enemigos.txt");
//
//            ////PARA FORMAR EL ARCHIVO XML serializado    
//            //            ArrayList<Arma> arm = diablito.cargarArmas("..\\src\\armas.txt");
//            //            ArrayList<Armadura> armad = diablito.cargarArmaduras("..\\src\\armaduras.txt");
//            //            ArrayList<Pocion> poc = diablito.cargarPociones("..\\src\\pociones.txt");
//            //            for (int i = 0; i < arm.size(); i++) {
//            //                gart.getObjetos().add(arm.get(i));
//            //            }
//            //            for (int i = 0; i < armad.size(); i++) {
//            //                gart.getObjetos().add(armad.get(i));
//            //            }
//            //            for (int i = 0; i < poc.size(); i++) {
//            //                gart.getObjetos().add(poc.get(i));
//            //            }
//            //// 1. Escribir el archivo
//            //            try {
//            //                XStream xs = new XStream();
//            //
//            //                FileWriter fw = new FileWriter("artefactos.txt");
//            //                fw.write(xs.toXML(gart));
//            //                fw.close();
//            //            } catch (IOException e) {
//            //                System.out.println(e.toString());
//            //            }
//            cargarArtefactos(armas, armaduras, pociones);
//
////MODIFICACION :CARGO LA LISTA DE ALIADOS
//            //ArrayList<Aliado> aliados = cargarAliado("..\\src\\Aliados.txt", pociones, armas, armaduras);
//            /*for (int i = 0; i < aliados.size(); i++) {
//                diablito.ordenarConsejos(aliados.get(i));
//            }*/
//// se llama al menu
//            menu.MenuPrincipal();
//           
//
////  CREO AL ALIADO
//            char opcion = '0';
//            while (opcion != '1' && opcion != '2' && opcion != '3') {
//                opcion = sc.next().charAt(0);
//            }
//            if ((opcion == '1' || opcion == '3') && !salir) {
//                // aqui ira menu de partidas proximamente(para cargar partida o comenzar una
//                // Se crea los laberintos(niveles)
//                int x = 0, y = 0;
//                if (opcion == '1') {
//                    menu.historia();
//
//                    niveles = glab.crear(20, 20, pj);
//                    //settear enemigos en el mapa
//                    agregarEnemigos(enemigos, niveles);
//                    agregarArmas(armas, niveles);
//                    agregarArmaduras(armaduras, niveles);
//                    agregarPociones(pociones, niveles);
//
//                    // se coloca inicio y fin en cada laberinto
//                    // coloco enemigos y objetos en laberinto
//                    // se crea al personaje
//                    pj = crearAvatar(armas.get(0));
//
//                    //ubica al personaje en el mapa del juego;
//                    colocarAvatarInicio(niveles.elementAt(nivelActual), pj);
//
//                    // coloca aliado  
//                    //for (int i = 0; i < niveles.size(); i++) {
//                    //Aliado al = new Aliado();
//                    //escojo un aliado de la lista
//                    //  Random rnaliado = new Random();
//                    //int indicealiado = (int) (rnaliado.nextDouble() * aliados.size());
//                    //al = aliados.get(indicealiado);
//                    //colocarAliado(al, niveles, i);
//                    //}
//                } else {
//                    try {
//                        XStream xstream = new XStream(new DomDriver());
//                        FileReader fr = new FileReader("juegoGuardado.txt");
//                        niveles = (Vector<Laberinto>) xstream.fromXML(fr);
//                        fr.close();
//                    } catch (IOException e) {
//                        System.out.println(e.toString());
//                    }
//                    for (int i = 0; i < niveles.size(); i++) {;
//                        if (niveles.elementAt(i).hallarPosXAvatar() >= 0) {
//                            nivelActual = i;
//                            x = niveles.elementAt(i).hallarPosXAvatar();
//                            y = niveles.elementAt(i).hallarPosYAvatar();
//                            if (niveles.elementAt(i).getCeldaLaberinto(x, y).getObjEntidad() instanceof Avatar) {
//                                pj = (Avatar) niveles.elementAt(i).getCeldaLaberinto(x, y).getObjEntidad();
//                            }
//                            break;
//                        }
//                    }
//                }
//                // se muestra  mapa
//                Dibujador dbj = new Dibujador(7, 7);
//
//                char dir = ' ';
//
//                //if(opcion == '1')
//                meMovi = true;
//                //else
//                //    meMovi = false;
//
//                while (!salir && !pj.muerto()) {   // empieza juego movimientos e interaccion del avatar
//                    x = 0;
//                    y = 0;
//                    Celda celdaActual = new Celda();
//                    Celda celdaSiguiente = new Celda();
//                    Celda celdaFin;
//
//                    // en el nivel en el que estamos  hallamos posicion del avatar y realizamos movimiento
//                    x = niveles.elementAt(nivelActual).hallarPosXAvatar();
//                    y = niveles.elementAt(nivelActual).hallarPosYAvatar();
//                    //AGREGAR CONDICION QUE SOLO SEA POR LOS COMANDOS DE  MOVIMIENTO
//                    if (inicio) {// para que solo se enpiecen a mover despues de comenzado el juego
//                        inicio = false;
//                    } else {
//                        moverEnemigosLaberinto(niveles.elementAt(nivelActual));
//                        moverAliadoLaberinto(niveles.elementAt(nivelActual));
//                    }
//                    //Verifico si paso de nivel
//                    if (meMovi && niveles.elementAt(nivelActual).getCeldaLaberinto(x, y).getEstado().equals("SIGUIENTE")) {
//                        nivelActual++;
//                        if (nivelActual >= CANT_NIVELES) {
//                            //termine el juego
//                            fin = true;
//
//                            //Ganaste
//                            menu.ganaste();
//                            break;
//                        } else {
//                            niveles.elementAt(nivelActual - 1).getCeldaLaberinto(x, y).setObjEntidad(null);
//                            //Se vuelve a calcular la nueva posicion del avatar en el nuevo nivel
//                            colocarAvatarInicio(niveles.elementAt(nivelActual), pj);
//                            x = niveles.elementAt(nivelActual).hallarPosXAvatar();
//                            y = niveles.elementAt(nivelActual).hallarPosYAvatar();
//                        }
//                    } else if (meMovi && niveles.elementAt(nivelActual).getCeldaLaberinto(x, y).getEstado().equals("ANTERIOR")) {
//                        if (nivelActual != 0) {
//                            nivelActual--;
//                            niveles.elementAt(nivelActual + 1).getCeldaLaberinto(x, y).setObjEntidad(null);
//                            //Vuelvo al anterior laberinto
//                            colocarAvatarFinal(niveles.elementAt(nivelActual), pj);
//                            x = niveles.elementAt(nivelActual).hallarPosXAvatar();
//                            y = niveles.elementAt(nivelActual).hallarPosYAvatar();
//                        }
//                    }
//
//                    //Meter a un enemigo
//                    //niveles.elementAt(0).getCeldaLaberinto(1, 2).setObjEntidad(enemigos.get(0));
//                    //dibujo posicion actual                      
//                    dbj.dibujar(niveles.elementAt(nivelActual));
//
//                    //Informacion del avatar
//                    pj.mostrar();
//                    pj.mostrarMochila();
//
//                    System.out.println("Controles:");
//                    System.out.println("w:arriba  s:abajo   d:derecha   a:izquierda   ");
//                    System.out.println("m:Acceder mochila  e:interactuar  g:guardar juego  z:salir   ");// ya no hay opcion atacar para la segunda modificacion
//
//                    celdaActual = niveles.elementAt(nivelActual).getCeldaLaberinto(x, y);
//
//                    String ax = JOptionPane.showInputDialog("Ingrese un comando: ");
//                    JOptionPane.showMessageDialog(null, "El comando ingresado es: " + ax);
//                    char mov = ax.charAt(0);
//
//                    switch (mov) {//detectamos que quiere hacer el jugador
//                        case 'd':// se mueve a la derecha;
//                            dir = mov;
//                            meMovi = true;
//                            mover(niveles.elementAt(nivelActual), nivelActual, celdaActual, pj, x + 1, y);
//                            break;
//                        case 's':
//                            dir = mov;
//                            meMovi = true;
//                            mover(niveles.elementAt(nivelActual), nivelActual, celdaActual, pj, x, y + 1);
//                            break;
//                        case 'w':
//                            dir = mov;
//                            meMovi = true;
//                            mover(niveles.elementAt(nivelActual), nivelActual, celdaActual, pj, x, y - 1);
//                            break;
//                        case 'a':
//                            dir = mov;
//                            meMovi = true;
//                            mover(niveles.elementAt(nivelActual), nivelActual, celdaActual, pj, x - 1, y);
//                            break;
//                        case 'm'://muestra mochila
//                            menuMochila(pj);
//                            meMovi = false;
//                            break;
//                        case 'e':
//                            // INTERACTUAR
//                            meMovi = false;
//                            String cmnd = JOptionPane.showInputDialog("Escribe la direccion: ");
//                            JOptionPane.showMessageDialog(null, "El comando ingresado es: " + cmnd);
//                            dir = cmnd.charAt(0);
//                            interactuar(dir, pj, niveles.elementAt(nivelActual));
//
//                            break;
//                        case 'g':
//                            try {
//                                XStream xs = new XStream();
//
//                                FileWriter fw = new FileWriter("juegoGuardado.txt");
//                                fw.write(xs.toXML(niveles));
//                                fw.close();
//                            } catch (IOException e) {
//                                System.out.println(e.toString());
//                            }
//                            break;
//                        case 'z': // sale del juego (deberia guarda tb)      
//                            salir = true;
//                            break;
//                    }
//
//                    if (salir) {
//                        fin = true;
//                    }
//                }
//                if (pj.muerto()) {
//                    System.out.println();
//                    System.out.println("Estas muerto");
//                    System.out.println("Aun te falta mucho, novato");
//                    System.out.println();
//                }
//            } else if (opcion
//                    == '2') {
//                fin = true;
//            }
////
//        }

    }

    public boolean cambiaDeNivel(Avatar pj, Vector<Laberinto> niveles, int nivelActual, int x, int y) {
        if (niveles.elementAt(nivelActual).getCeldaLaberinto(x, y).getEstado().equals("SIGUIENTE")) {
            nivelActual++;
            if (nivelActual >= CANT_NIVELES) {
                //termine el juego
                return true;
            } else {
                //Se vuelve a calcular la nueva posicion del avatar en el nuevo nivel
                colocarAvatarInicio(niveles.elementAt(nivelActual), pj);
                x = niveles.elementAt(nivelActual).hallarPosXAvatar();
                y = niveles.elementAt(nivelActual).hallarPosYAvatar();
                return false;
            }
        } else if (niveles.elementAt(nivelActual).getCeldaLaberinto(x, y).getEstado().equals("ANTERIOR")) {
            if (nivelActual != 0) {
                nivelActual--;
                //Vuelvo al anterior laberinto
                colocarAvatarFinal(niveles.elementAt(nivelActual), pj);
                x = niveles.elementAt(nivelActual).hallarPosXAvatar();
                y = niveles.elementAt(nivelActual).hallarPosYAvatar();
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
//#PREG 1

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

    public void moverEnemigosLaberinto(Laberinto lab) {
        int x = 0, y;
        int posX = lab.hallarPosXAvatar();
        int posY = lab.hallarPosYAvatar();
        Scanner sc = new Scanner(System.in);

        initEnemigosLab(lab);

        while (x < lab.getM()) {
            y = 0;
            while (y < lab.getN()) {
                if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Enemigo) {
                    Enemigo enemigo = (Enemigo) lab.getCeldaLaberinto(x, y).getObjEntidad();

                    if (!enemigo.isMeMovi()) {
                        enemigo.setMeMovi(true);
                        int sector = hallarSector(posX, posY, x, y);
                        //sc.next();
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

    public void moverAliadoLaberinto(Laberinto lab) {
        int x = 0, y, mover_x = 0, mover_y = 0, dir;
        Celda celdaActual;
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
        while (x < lab.getM()) {
            y = 0;
            while (y < lab.getN()) {
                if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Aliado) {
                    Aliado aliado = (Aliado) lab.getCeldaLaberinto(x, y).getObjEntidad();
                    celdaActual = lab.getCeldaLaberinto(x, y);
                    moverAliado(lab, dir, celdaActual, aliado, x + mover_x, y + mover_y);
                }
                y++;
            }
            x++;
        }

    }

    public void moverAliado(Laberinto lab, int nivel, Celda celdaActual, Aliado comp, int x, int y) {
        Celda celdaSiguiente = new Celda();
        celdaSiguiente = lab.getCeldaLaberinto(x, y);
        //verifico que hay en celda siguiente para saber si se puede mover 
        if ((celdaSiguiente.getEstado().equals("ADENTRO") || celdaSiguiente.getEstado().equals("SIGUIENTE") || celdaSiguiente.getEstado().equals("ANTERIOR")) && (celdaSiguiente.getObjEntidad() == null) && celdaSiguiente.getObjArtefacto() == null) {
            celdaActual.setObjEntidad(null);
            celdaSiguiente.setObjEntidad(comp);
        }
    }

    public void interactuar(char dir, Avatar pj, Laberinto lab) {// CREADO PARA LA SEGUNDA MODIFICACION
        //Requiriendo la direccion para interactuar
        int x = lab.hallarPosXAvatar();
        int y = lab.hallarPosYAvatar();
        switch (dir) {// aluego se identifica si es entidad o objeto 
            case 'd':
                identificarInteractuar(x + 1, y, lab, pj);
                break;
            case 'a':
                identificarInteractuar(x - 1, y, lab, pj);
                break;
            case 'w':
                identificarInteractuar(x, y - 1, lab, pj);
                break;
            case 's':
                identificarInteractuar(x, y + 1, lab, pj);
                break;
        }
    }

    public void identificarInteractuar(int x, int y, Laberinto lab, Avatar pj) {
        Celda celda = lab.getCeldaLaberinto(x, y);
        if (celda.getObjArtefacto() != null) {// si obtiene objeto  quiere decir que debe interactuar con artefacto
            guardar(x, y, lab, pj);
        } else if (celda.getObjEntidad() != null) {//SI OBTIENE ENTIDAD SI ES ENEMIGO O ALIADO
            Entidad e = celda.getObjEntidad();
            if (e instanceof Enemigo) {
                batalla(x, y, lab, pj);
            } else if (e instanceof Aliado) {
                Aliado al = (Aliado) e;
                Random rn = new Random();
                int ind = (int) (rn.nextDouble() * al.getConsejos().size());
                String consejo = al.getConsejos().get(ind);
                System.out.println();
                System.out.println();
                System.out.println("Te encontrate con un aliado");
                ayuda(x, y, lab, pj);
// desaparecemos el aliado luego de dar el consejo para avanzar
                celda.setObjEntidad(null);
            }

        }// si no encuentra nada no hace nada

    }

    public void recoger(char dir, Avatar pj, Laberinto lab) {
        //Requiriendo la direccion para recoger el objeto

        int x = lab.hallarPosXAvatar();
        int y = lab.hallarPosYAvatar();
        switch (dir) {
            case 'd':
                guardar(x + 1, y, lab, pj);
                break;
            case 'a':
                guardar(x - 1, y, lab, pj);
                break;
            case 'w':
                guardar(x, y - 1, lab, pj);
                break;
            case 's':
                guardar(x, y + 1, lab, pj);
                break;
        }
    }

    public void guardar(int x, int y, Laberinto lab, Avatar pj) {
        Celda celda = lab.getCeldaLaberinto(x, y);
        Artefacto obj = celda.getObjArtefacto();
        if (obj != null) {
            if (pj.guardarMochila(obj) > 0) {
                System.out.println("Se guardo en la Mochila:  " + obj.getNombre());
                celda.setObjArtefacto(null);
            }
        }

    }

    public void colocarAvatarInicio(Laberinto lab, Avatar pj) {
        System.out.println("aksjdaksljdkalsjd");
        int x = lab.getIniX();
        int y = lab.getIniY();
        System.out.println("aksjdaksljdkalsjd" + x + "  " + y);
        lab.getCeldaLaberinto(x, y).setObjEntidad(pj);
    }

    public void colocarAvatarFinal(Laberinto lab, Avatar pj) {
        int x = lab.getFinX();
        int y = lab.getFinY();

        lab.getCeldaLaberinto(x, y).setObjEntidad(pj);
    }

    public void mover(Laberinto lab, int nivel, Celda celdaActual, Avatar pj, int x, int y) {
        Celda celdaSiguiente = new Celda();
        celdaSiguiente = lab.getCeldaLaberinto(x, y);
        //verifico que hay en celda siguiente para saber si se puede mover 
        if ((celdaSiguiente.getEstado().equals("ADENTRO") || celdaSiguiente.getEstado().equals("SIGUIENTE") || celdaSiguiente.getEstado().equals("ANTERIOR")) && celdaSiguiente.getObjEntidad() == null) {
            celdaActual.setObjEntidad(null);
            celdaSiguiente.setObjEntidad(pj);
            Celda celdaposibleEnemigo = lab.getCeldaLaberinto(x + 1, y);
            Enemigo e = new Enemigo();
            if (celdaposibleEnemigo.getObjEntidad() instanceof Enemigo) {
                e = (Enemigo) celdaposibleEnemigo.getObjEntidad();
                if (pj.getArmadura() != null) {
                    int puntosDanio = pj.getArmadura().getPuntosDefensa() - e.getFuerza();
                    if (puntosDanio >= 0) {
                        pj.setVidaActual(pj.getVidaActual() - 1);
                    } else {
                        pj.setVidaActual(pj.getVidaActual() + puntosDanio);
                    }

                } else {
                    pj.setVidaActual(pj.getVidaActual() - e.getFuerza());
                }
            }

            celdaposibleEnemigo = lab.getCeldaLaberinto(x - 1, y);
            if (celdaposibleEnemigo.getObjEntidad() instanceof Enemigo) {
                e = (Enemigo) celdaposibleEnemigo.getObjEntidad();
                if (pj.getArmadura() != null) {
                    int puntosDanio = pj.getArmadura().getPuntosDefensa() - e.getFuerza();
                    if (puntosDanio >= 0) {
                        pj.setVidaActual(pj.getVidaActual() - 1);
                    } else {
                        pj.setVidaActual(pj.getVidaActual() + puntosDanio);
                    }

                } else {
                    pj.setVidaActual(pj.getVidaActual() - e.getFuerza());
                }
            }

            celdaposibleEnemigo = lab.getCeldaLaberinto(x, y + 1);
            if (celdaposibleEnemigo.getObjEntidad() instanceof Enemigo) {
                e = (Enemigo) celdaposibleEnemigo.getObjEntidad();
                if (pj.getArmadura() != null) {
                    int puntosDanio = pj.getArmadura().getPuntosDefensa() - e.getFuerza();
                    if (puntosDanio >= 0) {
                        pj.setVidaActual(pj.getVidaActual() - 1);
                    } else {
                        pj.setVidaActual(pj.getVidaActual() + puntosDanio);
                    }

                } else {
                    pj.setVidaActual(pj.getVidaActual() - e.getFuerza());
                }
            }

            celdaposibleEnemigo = lab.getCeldaLaberinto(x, y - 1);
            if (celdaposibleEnemigo.getObjEntidad() instanceof Enemigo) {
                e = (Enemigo) celdaposibleEnemigo.getObjEntidad();
                if (pj.getArmadura() != null) {
                    int puntosDanio = pj.getArmadura().getPuntosDefensa() - e.getFuerza();
                    if (puntosDanio >= 0) {
                        pj.setVidaActual(pj.getVidaActual() - 1);
                    } else {
                        pj.setVidaActual(pj.getVidaActual() + puntosDanio);
                    }

                } else {
                    pj.setVidaActual(pj.getVidaActual() - e.getFuerza());
                }
            }
        }

    }

    public void menuMochila(Avatar pj) {
        char indice, opcion;

        System.out.println();
        System.out.println("MENU MOCHILA");

        while (pj.mostrarMochila() > 0) {
            String ax = JOptionPane.showInputDialog("(1)Seleccionar            (2)Salir");
            JOptionPane.showMessageDialog(null, "El numero ingresado es: " + ax);
            opcion = ax.charAt(0);
            if (opcion == '1') {
                Saco mochila = pj.getMochila();

                while (true) {
                    String cmnd = JOptionPane.showInputDialog("Escriba el indice del objeto que desea:");
                    JOptionPane.showMessageDialog(null, "El numero ingresado es: " + cmnd);
                    indice = cmnd.charAt(0);
                    if ((indice - '0') >= 1 && (indice - '0') <= mochila.getListaDeArtefactos().size()) {
                        break;
                    }
                }
                Artefacto obj = mochila.seleccionarArtefacto(indice - '1');
                obj.mostrarDetalle();
                opcionesObjeto(obj, mochila, pj);//opciones que parecen al seleccionar un objeto
            } else if (opcion == '2') {
                break;
            }
        }
        if (pj.mostrarMochila() == 0) {
            JOptionPane.showMessageDialog(null, "NO PUEDES ENTRAR AL MENU");
        }
    }

    public void opcionesObjeto(Artefacto obj, Saco mochila, Avatar pj) {
        int uso = 1;
        Scanner sc = new Scanner(System.in);
        while (uso < 4 && uso > 0) {// mientras seleccione una opcion correcta
            System.out.println("Que desea hacer con el objeto?");
            if (obj.getTipo().equals("POCION")) {
                System.out.println("(1)Usar      (2)botar     (3)salir");
                uso = sc.nextInt();
                if (uso == 1) {
                    Pocion po = (Pocion) obj;
                    pj.usar(po);
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else if (uso == 2) {
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else {
                    break;
                }
            } else if (obj.getTipo().equals("ARMA") || obj.getTipo().equals("ARMADURA")) {
                System.out.println("(1)Equipar      (2)botar     (3)salir");
                uso = sc.nextInt();
                if (uso == 1) {
                    if (obj instanceof Arma) {
                        Arma arm = (Arma) obj;
                        pj.equipar(arm);
                        uso = 0;
                    } else if (obj instanceof Armadura) {
                        Armadura arm = (Armadura) obj;
                        pj.equipar(arm);
                        uso = 0;
                    }
                } else if (uso == 2) {
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else {
                    break;
                }
            }
        }
    }

    public Avatar crearAvatar(Arma objArma) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cual sera tu nombre Demonio: ");
        //String nombre = sc.nextLine();
        Avatar pj = new Avatar(objArma);
        pj.setNombre("Diablo");

        return pj;
    }

    public int atacar(char dir, Avatar pj, Laberinto lab) {
        int x = lab.hallarPosXAvatar();
        int y = lab.hallarPosYAvatar();
        Enemigo enemigo = new Enemigo();
        System.out.println("Ataca enemigo");
        switch (dir) {
            case 'd':
                batalla(x + 1, y, lab, pj);
                break;
            case 'a':
                batalla(x - 1, y, lab, pj);
                break;
            case 'w':
                batalla(x, y - 1, lab, pj);
                break;
            case 's':
                batalla(x, y + 1, lab, pj);

                break;
        }
        return 0;
    }

    public void ayuda(int x, int y, Laberinto lab, Avatar pj) {

        Aliado aliado = new Aliado();
        if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Aliado) {
            aliado = (Aliado) lab.getCeldaLaberinto(x, y).getObjEntidad();

            Scanner sc = new Scanner(System.in);
            System.out.println("Te encontraste a " + aliado.getNombre());
            boolean salida = false;
            while (!salida) {
                char op, indice;
                System.out.println("[C]onsejo   [O]bjeto    [S]alir");
                op = sc.next().charAt(0);
                switch (op) {
                    case 'c':
                        Random rn = new Random();
                        int ind = (int) (rn.nextDouble() * aliado.getConsejos().size());
                        String consejo = aliado.getConsejos().get(ind);
                        System.out.println(consejo);
                        salida = true;
                        break;
                    case 'o':
                        System.out.println("Escoge uno de mis artefactos si te interesa.");
                        aliado.mostrarMochila();
                        System.out.println("(1)Seleccionar            (2)Salir");
                        char opcion = sc.next().charAt(0);
                        if (opcion == '1') {
                            Saco mochila = aliado.getMochila();
                            while (true) {
                                System.out.println("Escriba el indice del objeto que desea:");
                                indice = sc.next().charAt(0);
                                if ((indice - '0') >= 1 && (indice - '0') <= mochila.getListaDeArtefactos().size()) {
                                    break;
                                }
                            }
                            Artefacto obj = mochila.seleccionarArtefacto(indice - '1');
                            obj.mostrarDetalle();
                            if (pj.guardarMochila(obj) > 0) {
                                System.out.println("Se guardo en la Mochila:  " + obj.getNombre());
                            }
                        } else if (opcion == '2') {
                            break;
                        }
                        salida = true;
                        break;
                    case 's':
                        salida = true;
                        break;

                }
            }
        }
    }

    public void batalla(int x, int y, Laberinto lab, Avatar pj) { //Batalla
        int puntosDanio;

        Enemigo enemigo = new Enemigo();
        if (lab.getCeldaLaberinto(x, y).getObjEntidad() instanceof Enemigo) {//izquierda
            enemigo = (Enemigo) lab.getCeldaLaberinto(x, y).getObjEntidad();

            Scanner sc = new Scanner(System.in);

            int n = pj.calcularDano();
            boolean salida = false;
            System.out.println("Te encontraste a " + enemigo.getNombre());
            while (!pj.muerto() && !enemigo.muerto() && !salida) {
                System.out.println("Avatar");
                pj.mostrar();
                System.out.println();
                System.out.println();
                System.out.println("Enemigo");
                enemigo.mostrar();
                char op;
                System.out.println("[A]tacar   [H]uir    [U]sar");
                op = sc.next().charAt(0);
                switch (op) {
                    case 'a':
                        puntosDanio = enemigo.getDefensa() - n;
                        if (puntosDanio < 0) {
                            enemigo.setVidaActual(enemigo.getVidaActual() + puntosDanio);
                        }
                        if (enemigo.muerto()) {
                            System.out.println("Enemigo esta muerto");
                            lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                        } else if (pj.getArmadura() != null) {
                            puntosDanio = pj.getArmadura().getPuntosDefensa() - enemigo.getFuerza();
                            if (puntosDanio >= 0) {
                                pj.setVidaActual(pj.getVidaActual() - 1);
                            } else {
                                pj.setVidaActual(pj.getVidaActual() + puntosDanio);
                            }

                        } else {
                            pj.setVidaActual(pj.getVidaActual() - enemigo.getFuerza());
                        }
                        break;
                    case 'h':
                        salida = true;
                        break;
                    case 'u':
                        menuMochila(pj);
                        break;
                }

            }

        }

    }

    public ArrayList<Aliado> cargarAliado(String archivo, ArrayList<Pocion> listapoc, ArrayList<Arma> listaArma, ArrayList<Armadura> listArmadura) throws FileNotFoundException, IOException {
        String cadena;
        String palabras[];
        ArrayList<Aliado> aliados = new ArrayList<>();
        int i = 0;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        cadena = b.readLine();
        StringTokenizer st = new StringTokenizer(cadena, ":");
        st.nextToken();//ALIADOS
        int numAliados = Integer.parseInt(st.nextToken());
        for (i = 0; i < numAliados; i++) {
            if ((cadena = b.readLine()) != null) {
                Aliado al = new Aliado();
                StringTokenizer stokenlinea = new StringTokenizer(cadena, ":/.@");
                stokenlinea.nextToken();// ALIADO
                String nombre = stokenlinea.nextToken();//nombre nombrealiado
                StringTokenizer stnombre = new StringTokenizer(nombre, " ");
                stnombre.nextToken();// salto etiqueta nombre
                // si existiera nombre con espacios
                nombre = "";
                while (stnombre.hasMoreTokens()) {
                    nombre.concat(stnombre.nextToken());
                }
                al.setNombre(nombre);
                stokenlinea.nextToken();// CONSEJOS   
                while (stokenlinea.hasMoreTokens()) {
                    al.getNivelconsejos().add(Integer.parseInt(st.nextToken()));
                    al.getConsejos().add(st.nextToken());
                }
                int ind;
                // Ahora lleno la bolsa de objetos del aliado
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 pociones al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listapoc.size());
                    al.getMochila().agregarArtefacto(listapoc.get(ind));

                }
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 armas al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listaArma.size());
                    al.getMochila().agregarArtefacto(listaArma.get(ind));
                }
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 armaduras al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listArmadura.size());
                    al.getMochila().agregarArtefacto(listArmadura.get(ind));
                }
                aliados.add(al);
            }

        }

        b.close();

        return aliados;

    }

    public void ordenarConsejos(Aliado al) {
        String consejo;
        for (int i = 0; i < al.getConsejos().size(); i++) {
            for (int j = 1; j < al.getConsejos().size() - 1; j++) {
                if (al.getNivelconsejos().get(i) > al.getNivelconsejos().get(j)) {
                    consejo = al.getConsejos().get(i);
                    //al.getConsejos().get(i)=al.getConsejos().get(j);
                    //al.getConsejos().get(j). = consejo;

                }
            }
        }
    }

    public void colocarAliado(Aliado al, Vector<Laberinto> niveles, int nivel) {
        boolean colocado = false, salir = false;
        Celda objCelda;
        while (!colocado) {
            Random rndx = new Random();
            Random rndy = new Random();
            int posx = (int) (rndx.nextDouble() * (niveles.elementAt(nivel).getM()));
            int posy = (int) (rndy.nextDouble() * (niveles.elementAt(nivel).getN()));

            objCelda = niveles.elementAt(nivel).getCeldaLaberinto(posx, posy);
            if (objCelda.getEstado().equals("ADENTRO") && objCelda.getObjEntidad() == null && objCelda.getObjArtefacto() == null) {

                // ACA AÑADO EL ALIADO EN EL LABERINTO
                objCelda = niveles.elementAt(nivel).getCeldaLaberinto(posx, posy);
                objCelda.setObjEntidad(al);
                colocado = true;
            }
            if (colocado) {
                break;
            }

        }
    }

    public ArrayList<Enemigo> cargarEnemigos(String archivo) throws FileNotFoundException, IOException {

        Enemigo enemigo;
        ArrayList<Enemigo> lstEnemigos = new ArrayList<Enemigo>();
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
                    lstEnemigos.add(enemigo);
                }
            }
            b.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lstEnemigos;
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

    public void agregarPociones(ArrayList<Pocion> lista, Vector<Laberinto> niveles) {
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
                                celda.setObjArtefacto(lista.get(1));
                            } else {
                                celda.setObjArtefacto(lista.get(0));
                            }
                        }
                    }
                }
            }
        }
    }

    public void agregarArmas(ArrayList<Arma> lista, Vector<Laberinto> niveles) {
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
                                celda.setObjArtefacto(lista.get(prob1));
                            } else {
                                int prob2 = rnd.nextInt(6);
                                celda.setObjArtefacto(lista.get(prob2));
                            }
                        }
                    }
                }
            }
        }
    }

    public void agregarArmaduras(ArrayList<Armadura> lstArmaduras, Vector<Laberinto> niveles) {
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
                                celda.setObjArtefacto(lstArmaduras.get(prob1));
                            } else {
                                int prob2 = rnd.nextInt(6);
                                celda.setObjArtefacto(lstArmaduras.get(prob2));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @return the inicio
     */
    public boolean isInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public boolean isFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(boolean fin) {
        this.fin = fin;
    }

    /**
     * @return the meMovi
     */
    public boolean isMeMovi() {
        return meMovi;
    }

    /**
     * @param meMovi the meMovi to set
     */
    public void setMeMovi(boolean meMovi) {
        this.meMovi = meMovi;
    }

    /**
     * @return the pantallaInicio
     */
    public PantallaBienvenida getPantallaInicio() {
        return pantallaInicio;
    }

    /**
     * @param pantallaInicio the pantallaInicio to set
     */
    public void setPantallaInicio(PantallaBienvenida pantallaInicio) {
        this.pantallaInicio = pantallaInicio;
    }

    

    /**
     * @return the nivelActual
     */
    public int getNivelActual() {
        return nivelActual;
    }

    /**
     * @param nivelActual the nivelActual to set
     */
    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    /**
     * @return the pj
     */
    public Avatar getPj() {
        return pj;
    }

    /**
     * @param pj the pj to set
     */
    public void setPj(Avatar pj) {
        this.pj = pj;
    }

    /**
     * @return the niveles
     */
    public Vector<Laberinto> getNiveles() {
        return niveles;
    }

    /**
     * @param niveles the niveles to set
     */
    public void setNiveles(Vector<Laberinto> niveles) {
        this.niveles = niveles;
    }

    /**
     * @return the armas
     */
    public ArrayList<Arma> getArmas() {
        return armas;
    }

    /**
     * @param armas the armas to set
     */
    public void setArmas(ArrayList<Arma> armas) {
        this.armas = armas;
    }

    /**
     * @return the armaduras
     */
    public ArrayList<Armadura> getArmaduras() {
        return armaduras;
    }

    /**
     * @param armaduras the armaduras to set
     */
    public void setArmaduras(ArrayList<Armadura> armaduras) {
        this.armaduras = armaduras;
    }

    /**
     * @return the pociones
     */
    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    /**
     * @param pociones the pociones to set
     */
    public void setPociones(ArrayList<Pocion> pociones) {
        this.pociones = pociones;
    }

}
