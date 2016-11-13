/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import Model.Artefacto;
import Model.Entidad;

/**
 *
 * @author alulab14
 */
public class Celda {
    private String estado;
    private Artefacto objArtefacto;
    private Entidad objEntidad;
    /* 0 = AFUERA, 1 = ADENTRO, 2 = ANTERIOR, 3 = SIGUIENTE, 4 = PARED,  */
    
    public Celda(){
        objArtefacto = new Artefacto();
        objEntidad = new Entidad();
        
        objArtefacto = null;
        objEntidad = null;
        estado = "SIGUIENTE";
    }
    
    public Celda(String estado){
        objArtefacto = new Artefacto();
        objEntidad = new Entidad();
        
        objArtefacto = null;
        objEntidad = null;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Artefacto getObjArtefacto() {
        return objArtefacto;
    }

    public void setObjArtefacto(Artefacto objArtefacto) {
        this.objArtefacto = objArtefacto;
    }

    public Entidad getObjEntidad() {
        return objEntidad;
    }

    public void setObjEntidad(Entidad objEntidad) {
        this.objEntidad = objEntidad;
    }
}
