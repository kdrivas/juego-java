/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Model.Avatar;

import Model.Laberinto;
import java.util.Vector;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author alulab14
 */
public class GestorLaberinto {

    static final int CANT_LABERINTOS = 3;
    private Vector<Laberinto> arrLaberintos;

    public GestorLaberinto() {
        arrLaberintos = new Vector<Laberinto>();
    }
    
    public Vector<Laberinto> crear(int M, int N, Avatar personaje) {
        
        for (int i = 0; i < CANT_LABERINTOS; i++) {
            Laberinto objLab = new Laberinto();
            Random rd = new Random();
            //objLab.inicializar(2 * (int)(rd.nextDouble()*10 + M) + 1, 2 * (int)(rd.nextDouble()*10 + N) + 1, personaje);
			objLab.inicializar(11,11,personaje);
            generadorMapa(objLab);
            objLab.setPct_enemigo(0.09);
            //objLab.imprimir();
            arrLaberintos.add(objLab);
        }
        return arrLaberintos;
    }

    private void generadorMapa(Laberinto objLab) {

        generaParedes(objLab);
        //Algoritmo de DFS
        generaCaminos(objLab);
    }

    private void generaParedes(Laberinto objLab) {
        for (int j = 0; j < objLab.getN(); j += 2) {
            for (int i = 0; i < objLab.getM(); i++) {
                objLab.setCeldaLaberinto(i, j, "PARED");
            }
        }

        for (int j = 0; j < objLab.getM(); j += 2) {
            for (int i = 0; i < objLab.getN(); i++) {
                objLab.setCeldaLaberinto(j, i, "PARED");
            }
        }
    }

    private void generaCaminos(Laberinto objLab) {
        //posicion actual, inicio y final en el mapa
        int x, y, iniX, iniY, finX, finY;
        //variable para controlar los espacios recorridos dentro de algoritmo de dfs
        int cantEspacios, caminoLargo, indiceCaminoLargo;
        
        //vectores para guardar esquinas correspondientes al laberinto con su respectiva distancia medida desde el inicio
        Vector<Integer> espacioHastaEsquina = new Vector<Integer>();
        Vector<Integer> esquinasX = new Vector<Integer>();
        Vector<Integer> esquinasY = new Vector<Integer>();
        
        LinkedList<Integer> pilaX = new LinkedList<Integer>();
        LinkedList<Integer> pilaY = new LinkedList<Integer>();
        Random md = new Random();

        //Genero las coordenadas aleatorias en el mapa, tienen que ser impares
        do {
            x = (int) (md.nextDouble() * (objLab.getM() - 1));
        } while (!esImpar(x));

        do {
            y = (int) (md.nextDouble() * (objLab.getN() - 1));
        } while (!esImpar(y));

        objLab.setIniX(x);
        objLab.setIniY(y);
        
        pilaX.addFirst(x);
        pilaY.addFirst(y);

        //Settear posicion inicial X e Y como adentro
        objLab.setCeldaLaberinto(x, y, "ADENTRO");

        //Backtracking para generar la ruta
        cantEspacios = 0;
        trazaRuta(objLab, x, y, pilaX, pilaY, espacioHastaEsquina, esquinasX, esquinasY, cantEspacios);
        
        //setteo la celda inicio del laberinto
        
        objLab.setCeldaLaberinto(objLab.getIniX(), objLab.getIniY(), "ANTERIOR");
        
        //setteo la celda de fin del laberinto
        caminoLargo = 0;
        indiceCaminoLargo = 0;
        for(int i=0; i<espacioHastaEsquina.size(); i++){
            if(espacioHastaEsquina.elementAt(i) > caminoLargo){
                caminoLargo = espacioHastaEsquina.elementAt(i);
                indiceCaminoLargo = i;
            }
        }
        objLab.setCeldaLaberinto(esquinasX.elementAt(indiceCaminoLargo), esquinasY.elementAt(indiceCaminoLargo), "SIGUIENTE");
        
        objLab.setFinX(esquinasX.elementAt(indiceCaminoLargo));
        objLab.setFinY(esquinasY.elementAt(indiceCaminoLargo));
        
    }

    private Boolean esImpar(int value) {
        if (value % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    private int calcNuevaPosicion(int direccion, int actPos, int opcion) {
        int sigPos;

        if (opcion == 0) {
            if (direccion == 1) {
                sigPos = actPos + 2;
            } else if (direccion == 0 || direccion == 2) {
                sigPos = actPos;
            } else {
                sigPos = actPos - 2;
            }
            return sigPos;
        } else {
            if (direccion == 0) {
                sigPos = actPos - 2;
            } else if (direccion == 1 || direccion == 3) {
                sigPos = actPos;
            } else {
                sigPos = actPos + 2;
            }
            return sigPos;
        }
    }

    private Boolean estaDisponible(Laberinto objLab, int direccion, int actX, int actY) {
        int sigX, sigY;

        sigX = calcNuevaPosicion(direccion, actX, 0);
        sigY = calcNuevaPosicion(direccion, actY, 1);

        if (0 < sigX && 0 < sigY && sigX < objLab.getM() && sigY < objLab.getN() && objLab.getCeldaLaberinto(sigX, sigY).getEstado().equals("AFUERA")) {
            return true;
        } else {
            return false;
        }
    }

    private void uneCeldas(Laberinto objLab, int direccion, int x, int y) {
        if (direccion == 0) {
            objLab.setCeldaLaberinto(x, y - 1, "ADENTRO");
            objLab.setCeldaLaberinto(x, y - 2, "ADENTRO");
        } else if (direccion == 1) {
            objLab.setCeldaLaberinto(x + 1, y, "ADENTRO");
            objLab.setCeldaLaberinto(x + 2, y, "ADENTRO");
        } else if (direccion == 2) {
            objLab.setCeldaLaberinto(x, y + 1, "ADENTRO");
            objLab.setCeldaLaberinto(x, y + 2, "ADENTRO");
        } else {
            objLab.setCeldaLaberinto(x - 1, y, "ADENTRO");
            objLab.setCeldaLaberinto(x - 2, y, "ADENTRO");
        }
    }

    private void trazaRuta(Laberinto objLab, int x, int y, LinkedList<Integer> pilaX, LinkedList<Integer> pilaY, Vector<Integer> espacioHastaEsquina, Vector<Integer> esquinasX, Vector<Integer> esquinasY, int cantEspacios) {
        Random md = new Random();
        //Vector para almacenar las direcciones encontradas
        Vector<Integer> coordEncontradas = new Vector<Integer>();
        //La direccion define en que cuadrado 
        int direccion, cantPosDisp = 4, sigX = 0, sigY = 0;

        if (pilaX.isEmpty() && pilaY.isEmpty()) {
            return;
        }

        do {
            do {
                direccion = (int) (md.nextDouble() * 4);
            } while (coordEncontradas.contains(direccion));

            coordEncontradas.add(direccion);
            cantPosDisp--;
        } while (!estaDisponible(objLab, direccion, x, y) && cantPosDisp != 0);
        /*
         Evaluo si hubo un espacio disponible para moverse, si no hay entonces desapilo y vuelvo a la posicion anterior, si se
         encontro entonces apilo
         */
       
        if (estaDisponible(objLab, direccion, x, y)) {
            cantEspacios+=2;
            //Dibujo nuevo camino
            uneCeldas(objLab, direccion, x, y);
            //calculo nuevas posiciones
            x = calcNuevaPosicion(direccion, x, 0);
            y = calcNuevaPosicion(direccion, y, 1);
            //Anado elementos al inicio de las pilas
            pilaX.addFirst(x);
            pilaY.addFirst(y);
        } else {
            //desapilo y guardo el ultimo elemento que entro                      
            x = pilaX.removeFirst();
            y = pilaY.removeFirst();
            
            if(esUltimaCasilla(objLab, x, y)){
                espacioHastaEsquina.add(cantEspacios);
                esquinasX.add(x);
                esquinasY.add(y);
            }     
  
            cantEspacios-=2;
        }
        trazaRuta(objLab, x, y, pilaX, pilaY, espacioHastaEsquina, esquinasX, esquinasY, cantEspacios);
    }
    
    private boolean esUltimaCasilla(Laberinto objLab, int x, int y){
        if(objLab.getCeldaLaberinto(x+1, y).getEstado().equals("ADENTRO") && 
                objLab.getCeldaLaberinto(x-1, y).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y+1).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y-1).getEstado().equals("PARED")){
            return true;
        }
        else if(objLab.getCeldaLaberinto(x+1, y).getEstado().equals("PARED") && 
                objLab.getCeldaLaberinto(x-1, y).getEstado().equals("ADENTRO")&&
                objLab.getCeldaLaberinto(x, y+1).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y-1).getEstado().equals("PARED")){
            return true;
        }
        else if(objLab.getCeldaLaberinto(x+1, y).getEstado().equals("PARED") && 
                objLab.getCeldaLaberinto(x-1, y).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y+1).getEstado().equals("ADENTRO")&&
                objLab.getCeldaLaberinto(x, y-1).getEstado().equals("PARED")){
            return true;
        }
        else if(objLab.getCeldaLaberinto(x+1, y).getEstado().equals("PARED") && 
                objLab.getCeldaLaberinto(x-1, y).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y+1).getEstado().equals("PARED")&&
                objLab.getCeldaLaberinto(x, y-1).getEstado().equals("ADENTRO")){
            return true;
        }
        else{
            return false;
        }
    }
}