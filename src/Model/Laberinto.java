/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Avatar;
import Model.Celda;

import java.util.*;

/**
 *
 * @author alulab14
 */
public class Laberinto {

    private int M;
    private int N;
    //posiciones de inicio y fin del laberinto
    private int iniX;
    private int iniY;
    private int finX;
    private int finY;
    private double pct_enemigo;
    private Vector<Integer> niveles_enemigo;
    private Vector<Vector<Celda>> mat_laberinto;

    public Laberinto() {
        niveles_enemigo = new Vector<Integer>();
        mat_laberinto = new Vector<Vector<Celda>>();
    }

    public void imprimir() {
        String a;
        for (int i = 0; i < this.getN(); i++) {
            for (int j = 0; j < this.getM(); j++) {
                a = this.getCeldaLaberinto(j, i).getEstado();
                if (a.equals("ADENTRO")) {
                    if(this.getCeldaLaberinto(j, i).getObjEntidad() instanceof Avatar){
                        System.out.print('H');
                    }
                    else if(this.getCeldaLaberinto(j, i).getObjArtefacto() != null){
                        System.out.print('A');
                    }
                    else
                        System.out.print('O');
                } else if (a.equals("ANTERIOR")) {
                    System.out.print('I');
                } else if (a.equals("SIGUIENTE")) {
                    System.out.print('F');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println(" ");
        }
//        System.out.println(this.iniX + "  " + this.iniY);
//        System.out.println(this.finX + "  " + this.finY);
    }

    public void inicializar(int M, int N, Avatar personaje) {

        //estableciendo tamanos del laberinto M = X, N = Y
        this.M = M;
        this.N = N;

        //setteando posibles niveles de los enemigos
        if (personaje.getNivel() != 1) {
            niveles_enemigo.add(personaje.getNivel() - 1);
        }
        niveles_enemigo.add(personaje.getNivel());
        niveles_enemigo.add(personaje.getNivel() + 1);

        pct_enemigo = 0.23;

        //separando espacios e memoria para matriz del laberinto
        for (int i = 0; i < M; i++) {
            Vector<Celda> arrayCelda = new Vector<Celda>(N);

            //Lleno todos los espacios del laberinto como AFUERA
            for (int j = 0; j < N; j++) {
                Celda espacio = new Celda("AFUERA");
                arrayCelda.add(espacio);
            }
            mat_laberinto.add(arrayCelda);
        }
    }

    // Settear celda con determinado estado
    public void setCeldaLaberinto(int x, int y, String estado) {
        Celda espacio = new Celda(estado);
        //Obtengo referencia a memoria del array celda de posicion X
        Vector<Celda> arrayCelda = this.mat_laberinto.get(x);

        //Anado elementos por indice
        arrayCelda.setElementAt(espacio, y);
    }

    public Celda getCeldaLaberinto(int x, int y) {
        Vector<Celda> arrayCelda = this.mat_laberinto.get(x);

        return arrayCelda.get(y);
    }

    public int hallarPosXAvatar() {
        int x = 0, y = 0;
        while (x < M) {
            y = 0;
            while (y < N) {
                if (getCeldaLaberinto(x, y).getObjEntidad() instanceof Avatar) {
                    return x;
                }
                y++;
            }
            x++;
        }
        return -1;
    }

    public int hallarPosYAvatar() {
        int x = 0, y = 0;
        while (x < M) {
            y = 0;
            while (y < N) {
                if (getCeldaLaberinto(x, y).getObjEntidad() instanceof Avatar) {
                    return y;
                }
                y++;
            }
            x++;
        }
        return -1;
    }

   

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public double getPct_enemigo() {
        return pct_enemigo;
    }

    public void setPct_enemigo(double pct_enemigo) {
        this.pct_enemigo = pct_enemigo;
    }

    public int getIniX() {
        return iniX;
    }

    public void setIniX(int iniX) {
        this.iniX = iniX;
    }

    public int getIniY() {
        return iniY;
    }

    public void setIniY(int iniY) {
        this.iniY = iniY;
    }

    public int getFinX() {
        return finX;
    }

    public void setFinX(int finX) {
        this.finX = finX;
    }

    public int getFinY() {
        return finY;
    }

    public void setFinY(int finY) {
        this.finY = finY;
    }

    /**
     * @return the niveles_enemigo
     */
    public Vector<Integer> getNiveles_enemigo() {
        return niveles_enemigo;
    }

    /**
     * @param niveles_enemigo the niveles_enemigo to set
     */
    public void setNiveles_enemigo(Vector<Integer> niveles_enemigo) {
        this.niveles_enemigo = niveles_enemigo;
    }

}
