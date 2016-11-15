/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author alulab14
 */
public class Entidad extends ObjetoDeJuego {

    protected int vidaMaxima;
    protected int vidaActual;
    protected int nivel;
    protected Arma arma;
    

    public Entidad() {
        super();
        vidaMaxima = 50;
        vidaActual = 50;
        arma= new Arma();
        nivel=1;
    }  
    
    public Entidad(Arma arma) {
        super();
        vidaMaxima = 50;
        vidaActual = 50;
        nivel=1;
        
        this.arma= new Arma();
        //copiando arma recibida hacia el arma del avatar
        this.arma.setCaracterImp(arma.getCaracterImp());
        this.arma.setDanoMax(arma.getDanoMax());
        this.arma.setDanoMin(arma.getDanoMin());
        this.arma.setEspacio(arma.getEspacio());
        this.arma.setNombre(arma.getNombre());
        this.arma.setTipo(arma.getTipo());
    }  

    /**
     * @return the vidaMaxima
     */
    public int getVidaMaxima() {
        return vidaMaxima;
    }

    /**
     * @param vidaMaxima the vidaMaxima to set
     */
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    /**
     * @return the vidaActual
     */
    public int getVidaActual() {
        return vidaActual;
    }

    /**
     * @param vidaActual the vidaActual to set
     */
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }
    
    public int calcularDano() {
        Random dn = new Random();
        double dano = dn.nextDouble() * (getArma().getDanoMax() - getArma().getDanoMin()) + getArma().getDanoMin();
        return (int)dano;
    }

    public boolean muerto() {       
        return (this.getVidaActual() <= 0);
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

    /**
     * @return the arma
     */
    public Arma getArma() {
        return arma;
    }

    /**
     * @param arma the arma to set
     */
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void barrita()
    {
        int num = (int)(this.vidaActual*80/this.vidaMaxima);
        for(int i = 0; i < num; i++)
            System.out.print('\3');
        System.out.println();
    }
}
