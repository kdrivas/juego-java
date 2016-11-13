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
public class Pocion extends Artefacto {

    private int puntosDeCuracion;

    public Pocion() {
        super();
        puntosDeCuracion = 0;
        this.setTipo("POCION");
    }

    /**
     * @return the puntosDeCuracion
     */
    public int getPuntosDeCuracion() {
        return puntosDeCuracion;
    }

    /**
     * @param puntosDeCuracion the puntosDeCuracion to set
     */
    public void setPuntosDeCuracion(int puntosDeCuracion) {
        this.puntosDeCuracion = puntosDeCuracion;
    }
    @Override
    public void   mostrarDetalle() {
        System.out.println("Nombre:" + this.getNombre());
        System.out.println("Tipo:" + this.getTipo());
        System.out.println("Puntos de Curacion:"+puntosDeCuracion);
        System.out.println("Espacio:" + this.getEspacio());
    }
}
