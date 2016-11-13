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
public class Enemigo extends Entidad {

    private int fuerza;
    private int defensa;
    private boolean meMovi;
    
    public Enemigo() {
        super();
        this.setArma(null);
        caracterImp = 'E';
    }

    public Enemigo(int nAvatar) {
        super();
        Random dn = new Random();

        if (nAvatar >= 2) {
            nivel = (int) (dn.nextDouble() * (nAvatar + 2) + (nAvatar - 2));
        } else if (nAvatar >= 1) {
            nivel = (int) (dn.nextDouble() * (nAvatar + 2) + (nAvatar - 1));
        } else {
            nivel = (int) (dn.nextDouble() * (nAvatar + 2));
        }

    }

    /**
     * @return the fuerza
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * @param fuerza the fuerza to set
     */
    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }
    
    public void mostrar(){
        
        System.out.println("Enemigo: " + this.getNombre());
        System.out.println("Nivel: " + this.getNivel());
        this.barrita();
        System.out.println("Vida:"+this.getVidaActual()+"/"+this.getVidaMaxima());
        
    }

    /**
     * @return the defensa
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * @param defensa the defensa to set
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
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
}
