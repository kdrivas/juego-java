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
public class Armadura extends Artefacto {

    private int puntosDefensa;

    public Armadura() {
        super();
        puntosDefensa = 0;
        this.setTipo("ARMADURA");
    }

    /**
     * @return the puntosDefensa
     */
    public int getPuntosDefensa() {
        return puntosDefensa;
    }

    /**
     * @param puntosDefensa the puntosDefensa to set
     */
    public void setPuntosDefensa(int puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }
    
    @Override
    public void mostrarDetalle() {
        System.out.println("Nombre:" + this.getNombre());
        System.out.println("Tipo:" + this.getTipo());
        System.out.println("Puntos de defensa:" + puntosDefensa);
        System.out.println("Espacio:" + this.getEspacio());
    }
}
