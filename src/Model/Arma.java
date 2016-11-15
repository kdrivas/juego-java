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
public class Arma extends Artefacto {

    private double danoMin;
    private double danoMax;

    // beneficio en caso usemos habilidades;
    public Arma() {
        super();
        danoMin = 0;
        danoMax = 0;
        this.setTipo("ARMA"); 
    }
    
    /**
     * @return the danoMin
     */
    public double getDanoMin() {
        return danoMin;
    }

    /**
     * @param danoMin the danoMin to set
     */
    public void setDanoMin(double danoMin) {
        this.danoMin = danoMin;
    }

    /**
     * @return the danoMax
     */
    public double getDanoMax() {
        return danoMax;
    }

    /**
     * @param danoMax the danoMax to set
     */
    public void setDanoMax(double danoMax) {
        this.danoMax = danoMax;
    }
    
    @Override
    public void mostrarDetalle(){
        System.out.println("Nombre:"+this.getNombre());
        System.out.println("Tipo:"+this.getTipo());
        System.out.println("Daño Minimo:"+danoMin);
        System.out.println("Daño Maximo:"+danoMax);
        System.out.println("Espacio:"+this.getEspacio());
    }

}
