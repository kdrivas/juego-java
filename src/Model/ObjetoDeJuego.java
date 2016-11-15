/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author alulab14
 */
public abstract class ObjetoDeJuego {
    protected String nombre;
    protected char caracterImp;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the caracterImp
     */
    public char getCaracterImp() {
        return caracterImp;
    }

    /**
     * @param caracterImp the caracterImp to set
     */
    public void setCaracterImp(char caracterImp) {
        this.caracterImp = caracterImp;
    }
    
}
