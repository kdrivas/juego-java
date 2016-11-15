/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Model.Avatar;
import Model.Celda;

import Model.Laberinto;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Vector;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import Vista.*;
import Model.*;
import java.awt.Color;
import java.awt.Rectangle;
/**
 *
 * @author alulab14
 */
public class GestorLaberinto {
    private static final int MARGEN_X = Constantes.ANCHO_VENTANA / 2 - Constantes.ANCHO_JUGADOR / 2;
    private static final int MARGEN_Y = Constantes.ALTO_VENTANA / 2 - Constantes.ALTO_JUGADOR / 2;
    
    public static final int CANT_LABERINTOS = 3;
    
    public Vector<Laberinto> arrLaberintos;
    private HojaSprites hoja;
    
    //temporal
    public Rectangle rec;
    
    private int nivel;
    private boolean cambioNivel = true;
    private boolean avanzaNivel = true;
    private boolean retrocedeNivel = false;
    
    public GestorLaberinto(HojaSprites hoja) {
        Avatar personaje = new Avatar();
        
        arrLaberintos = new Vector<Laberinto>();
        crear(11, 11, personaje);
        this.hoja = hoja;
    }
    
    public void crear(int M, int N, Avatar personaje) {
        
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
    
    private int actualizoNivel(GestorAvatar gestorAvatar){
        Laberinto laberinto = arrLaberintos.get(getNivel());
        Celda celdaInicio   = laberinto.getCeldaLaberinto(laberinto.getIniX(), laberinto.getIniY());
        Celda celdaFin      = laberinto.getCeldaLaberinto(laberinto.getFinX(), laberinto.getFinY());
        if(gestorAvatar.isEnMovimiento()){
        if(celdaFin.getObjEntidad() instanceof Avatar){
            if( getNivel() < 2){
                avanzaNivel = true;
                cambioNivel = true;
                return 1;
            }
            else
                return 0;
        }
        else if(celdaInicio.getObjEntidad() instanceof Avatar){
            if( getNivel() > 0){
                retrocedeNivel = true;
                cambioNivel = true;
                return -1;
            }
            else
                return 0;
        }
        else
            return 0;
        }
        else
            return 0;
    }
    
    private void colocarAvatar(GestorAvatar gestorAvatar){
        if(cambioNivel){
            Laberinto laberinto = arrLaberintos.get(getNivel());
            if(avanzaNivel){
                laberinto.getCeldaLaberinto(laberinto.getIniX(), laberinto.getIniY()).setObjEntidad(gestorAvatar.getAvatar());
                gestorAvatar.setPosicionX(laberinto.getIniX() * Constantes.ANCHO_JUGADOR);
                gestorAvatar.setPosicionY(laberinto.getIniY() * Constantes.ALTO_JUGADOR);      
            }
            else if(retrocedeNivel){
                laberinto.getCeldaLaberinto(laberinto.getFinX(), laberinto.getFinY()).setObjEntidad(gestorAvatar.getAvatar());
                gestorAvatar.setPosicionX(laberinto.getFinX() * Constantes.ANCHO_JUGADOR);
                gestorAvatar.setPosicionY(laberinto.getFinY() * Constantes.ALTO_JUGADOR);   
            }      
            avanzaNivel = false;
            retrocedeNivel = false;
            cambioNivel = false;
        }
    }
    
    public void actualizar(GestorAvatar gestorAvatar){
        colocarAvatar(gestorAvatar);
        setNivel(getNivel() + actualizoNivel(gestorAvatar));
    }
    
    public void dibujar(Graphics g, int posicionX, int posicionY){
        
        //Se recorre todo el vector de tiles
        for(int y = 0; y < this.arrLaberintos.elementAt(nivel).getN(); y++){
            for(int x = 0; x < this.arrLaberintos.elementAt(nivel).getM(); x++){
                String estado = obtenerEstado(x, y, nivel);
           
                int posX = x * Constantes.ANCHO_JUGADOR - posicionX + MARGEN_X;
                int posY = y * Constantes.ALTO_JUGADOR - posicionY + MARGEN_Y;
                
                
                g.setColor(Color.red);
                g.drawImage(hoja.SpriteKey(estado), posX, posY, null);
                if(x == 3 && y == 3){
                    rec = new Rectangle(posX, posY, 48, 64);
                    g.drawRect(rec.x, rec.y, rec.width, rec.height);
                }
                
            }
        }       
    }
    
    public String obtenerEstado(int x, int y, int nivel){
        if(x >= 0 && x < arrLaberintos.get(nivel).getM() && y >= 0 && y < arrLaberintos.get(nivel).getN()){
            Celda celda = arrLaberintos.get(nivel).getCeldaLaberinto(x, y);      
            return celda.getEstado();
        }
        else{
            return "VACIO";
        }
    }
    
    public Rectangle obtenerBorde( int posicionX, int posicionY, int anchoJugador, int altoJugador){
        int x = MARGEN_X - posicionX + anchoJugador;
        int y = MARGEN_Y - posicionY + altoJugador;
        
        return new Rectangle();
    }

    /**
     * @return the nivel
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
