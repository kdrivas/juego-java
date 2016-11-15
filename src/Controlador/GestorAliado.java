/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import FACILIDADES.*;
import Model.Arma;
import Model.Armadura;
import Model.Artefacto;
import Model.Avatar;
import Model.Celda;
import Model.Laberinto;
import Model.Pocion;
import Vista.HojaSprites;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author alulab14
 */
public class GestorAliado implements Runnable {

    private ArrayList<Aliado> aliados;

    private Graphics graficos;

    private Thread thread;

    private volatile GestorLaberinto gestorMapa;

    public GestorAliado() {
        aliados = new ArrayList<Aliado>();
    }

    public void inicializar(GestorLaberinto gestorMapa, GestorArtefactos gestorArtefacto) {

        aliados = cargarAliado("..//src/aliados.txt", gestorArtefacto.getPociones(), gestorArtefacto.getArmas(), gestorArtefacto.getArmaduras());

        
        for (int i = 0; i < 3; i++) {            
            Random rn =new Random();
            int a = (int) (rn.nextDouble() *aliados.size());
            colocarAliado(aliados.get(a), gestorMapa.arrLaberintos, i);
            
        }

        this.gestorMapa = gestorMapa;
        thread = new Thread(this, "Aliados");
        thread.start();

    }

    public ArrayList<Aliado> cargarAliado(String archivo, ArrayList<Pocion> listapoc, ArrayList<Arma> listaArma, ArrayList<Armadura> listArmadura) {

        String cadena;
        String palabras[], nombre[], frase[], consejos[];
        ArrayList<Aliado> lstAliados = new ArrayList<Aliado>();

        Scanner sc = new Scanner(System.in);

        int i = 0;
        int cantAliados, ranking;
        Aliado aliado;
        Consejo consejo;
        try {
            FileReader f = new FileReader(archivo);
            BufferedReader b = new BufferedReader(f);

            cadena = b.readLine();
            palabras = cadena.split(":");
            cantAliados = Integer.parseInt(palabras[1]); //Lee la cantidad de aliados
            while ((cadena = b.readLine()) != null) {
                aliado = new Aliado();
                ArrayList<Consejo> lstConsejos = new ArrayList<Consejo>();
                palabras = cadena.split("/");
                nombre = palabras[0].split(":");

                aliado.setNombre(nombre[1]);
                //System.out.println("Nombre: "+nombre[1]);
                frase = palabras[1].split(":");
                StringTokenizer st = new StringTokenizer(frase[1], ".");
                while (st.hasMoreTokens()) {
                    consejo = new Consejo();
                    consejos = st.nextToken().split("@");
                    //Lee el ranking
                    ranking = Integer.parseInt(consejos[0]);
                    consejo.setPuntos(ranking);
                    consejo.setMensaje(consejos[1]);
                    lstConsejos.add(consejo);
                    //System.out.println("Consejos: "+consejos[1]+" Numero: "+consejos[0]);

                }
                Collections.sort(lstConsejos, Consejo.puntosCs);
                aliado.setConsejos(lstConsejos);

                int ind;
                //Ahora lleno la bolsa de objetos del aliado
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 pociones al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listapoc.size());
                    aliado.getMochila().agregarArtefacto(listapoc.get(ind));

                }
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 armas al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listaArma.size());
                    aliado.getMochila().agregarArtefacto(listaArma.get(ind));
                }
                for (int j = 0; j < 3; j++) {// aleatoriamiente agrego 3 armaduras al aliado
                    Random rn = new Random();
                    ind = (int) (rn.nextDouble() * listArmadura.size());
                    aliado.getMochila().agregarArtefacto(listArmadura.get(ind));
                }
                lstAliados.add(aliado);
                //            for(Consejo str: lstConsejos){
                //			System.out.println(str);
                //	   }

            }

            b.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return lstAliados;

    }

    public void colocarAliado(Aliado al, Vector<Laberinto> niveles, int nivel) {
        boolean colocado = false, salir = false;
        Celda objCelda;
        while (!colocado) {
            Random rndx = new Random();
            Random rndy = new Random();
            int posx = (int) (rndx.nextDouble() * (niveles.get(nivel).getM()));
            int posy = (int) (rndy.nextDouble() * (niveles.get(nivel).getN()));

            objCelda = niveles.get(nivel).getCeldaLaberinto(posx, posy);
            if (objCelda.getEstado().equals("ADENTRO") && objCelda.getObjEntidad() == null && objCelda.getObjArtefacto() == null) {

//MODIFICACION 1 ACA AÑADO EL ALIADO EN EL LABERINTO
                objCelda = niveles.get(nivel).getCeldaLaberinto(posx, posy);
                objCelda.setObjEntidad(al);
                colocado = true;
            }
            if (colocado) {
                break;
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
        while (true) {

            //////////////////////////////////////////////////////////////
            //controla actulizaciones del frame
            final long inicioBucle = System.nanoTime();

            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;

            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

            while (delta >= 1) {
                //Aqui actualizo los comandos
                actualizar();
                delta--;
            }
            ///////////////////////////////////////////////////////////////

            if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
                referenciaContador = System.nanoTime();
            }
        }
    }

    public void actualizar() {
        Random rn = new Random();

        int mover_x = 0, mover_y = 0;

        for (int x = 0; x < gestorMapa.arrLaberintos.get(0).getM(); x++) {
            for (int y = 0; y < gestorMapa.arrLaberintos.get(0).getN(); y++) {
                mover_x = 0;
                mover_y = 0;
                Celda celda = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y);
                if (celda.getObjArtefacto() != null && celda.getObjEntidad() instanceof Aliado) {
                    int dir = (int) (rn.nextDouble() * 4);
                    switch (dir) {
                        case 1://derecha
                            mover_x += 1;
                            break;
                        case 2://izquierda
                            mover_x -= 1;
                            break;
                        case 3://arriba
                            mover_y -= 1;
                            break;
                        case 4://abajo
                            mover_y += 1;
                            break;
                    }// recorro laberinto y por cada enemigo veo intento mover
                    if (x + mover_x >= 0 && x + mover_x < gestorMapa.arrLaberintos.get(0).getM()
                            && y + mover_y >= 0 && y + mover_y < gestorMapa.arrLaberintos.get(0).getN()) {
                        Celda celdaSiguiente = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x + mover_x, y + mover_y);
                        Celda celdaActual = gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y);

                        if (!celdaActual.getObjEntidad().isMeMovi() && celdaSiguiente.getEstado() == "ADENTRO" && celdaSiguiente.getObjEntidad() == null && celdaSiguiente.getObjArtefacto() == null) {
                            celdaActual.getObjEntidad().setMeMovi(true);
                            celdaSiguiente.setObjEntidad(celdaActual.getObjEntidad());
                            celdaActual.setObjEntidad(null);
                        }
                    }
                }
            }
        }

        for (int x = 0; x < gestorMapa.arrLaberintos.get(0).getM(); x++) {
            for (int y = 0; y < gestorMapa.arrLaberintos.get(0).getN(); y++) {
                if (gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y).getObjEntidad() != null) {
                    gestorMapa.arrLaberintos.get(0).getCeldaLaberinto(x, y).getObjEntidad().setMeMovi(false);
                }
            }
        }
        gestorMapa.arrLaberintos.get(0).imprimir();
    }

}
