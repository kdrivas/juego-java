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
public class Artefacto extends ObjetoDeJuego {

    protected int espacio;
    protected String tipo;

    /**
     * @return the nombre
     */
    public Artefacto() {
        super();
        this.espacio = 0;
        this.caracterImp = 'A';
    }

    /**
     * @return the espacio
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * @param espacio the espacio to set
     */
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void mostrarDetalle() { 

    }
    

}
