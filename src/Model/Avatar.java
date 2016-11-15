/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Iterator;
import Model.Arma;

/**
 *
 * @author alulab14
 */
public class Avatar extends Entidad {

    //private int magia;
    //int fuerza;
    //int destreza;// si agregamos habilidades
    private int experiencia;
    private int defensa;
    //String tipoArma;
    //int dinero;
    private Saco mochila;
    private Armadura armadura;

    public Avatar() {
        super();
        experiencia = 0;
        defensa = 0;
        mochila = new Saco();
        armadura = new Armadura();
        caracterImp = 'X';
        armadura = null;
        arma = null;
    }

    public Avatar(Arma arma) {
        super(arma);
        experiencia = 0;
        defensa = 0;
        mochila = new Saco();
        armadura = new Armadura();
        caracterImp = 'X';
        armadura = null;
    }

    /**
     * @return the experiencia
     */
    public int getExperiencia() {
        return experiencia;
    }

    /**
     * @param experiencia the experiencia to set
     */
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
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
     * @return the mochila
     */
    public Saco getMochila() {
        return mochila;
    }

    /**
     * @param mochila the mochila to set
     */
    public void setMochila(Saco mochila) {
        this.mochila = mochila;
    }

    /**
     * @return the armadura
     */
    public Armadura getArmadura() {
        return armadura;
    }

    /**
     * @param armadura the armadura to set
     */
    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    //
    public int mostrarMochila() {
        Iterator<Artefacto> it = mochila.getListaDeArtefactos().iterator();
        int indice = 0;

        if (!mochila.getListaDeArtefactos().isEmpty()) {
            System.out.println("---------------------MOCHILA----------------");
            while (it.hasNext()) {
                indice++;
                Artefacto obj = it.next();
                System.out.println(indice + ").- " + obj.getNombre() + " " + obj.getTipo());
            }
            System.out.println("--------------------------------------------");
            return 1;
        } else {
            System.out.println("---------------------MOCHILA----------------");
            System.out.println("Mochila vacia");
            System.out.println("--------------------------------------------");
            return 0;
        }
        
    }

    // Debe retornar tipo int y validar si mochila esta llena
    public int guardarMochila(Artefacto obj) {
        return this.mochila.agregarArtefacto(obj);
    }

    //Metodo que usar pocion, la validacion se realiza afuera del metodo
    public int usar(Pocion elixir) {
        int vida;

        if(getVidaActual() != getVidaMaxima()){
            vida = getVidaActual() + elixir.getPuntosDeCuracion();
            if(vida >= getVidaMaxima()){
                System.out.println("Ya estas curado totalmente");
                setVidaActual(getVidaMaxima());
            }
            else{
                setVidaActual(vida);
            }
            return 1;
        }
        else{
            return 0;
        }
    }

    public Arma equipar(Arma arma) {
        if (this.arma == null) {
            this.setArma(arma);
            this.mochila.botarArtefacto(arma);
            return null;
        } else {

            int espacioMochila = mochila.getEspacioActual();
            if (this.arma.espacio <= espacioMochila) {
                mochila.agregarArtefacto(this.arma);
                this.setArma(arma);
                this.mochila.botarArtefacto(arma);
                return null;
            } else {
                this.mochila.botarArtefacto(arma);
                return this.arma;
            }
        }

    }

    public Armadura equipar(Armadura armadura) {
        if (this.armadura == null) {
            this.setArmadura(armadura);
            this.setDefensa(armadura.getPuntosDefensa());
            this.mochila.botarArtefacto(armadura);
            return null;
        } else {

            int espacioMochila = mochila.getEspacioActual();
            if (this.armadura.espacio <= espacioMochila) {
                mochila.agregarArtefacto(this.armadura);
                this.setArmadura(armadura);
                this.setDefensa(armadura.getPuntosDefensa());
                this.mochila.botarArtefacto(armadura);
                return null;
            } else {
                this.mochila.botarArtefacto(armadura);
                return this.armadura;
            }
        }
    }

    public void mostrar() {
        System.out.println("Nombre: " + this.getNombre());
        this.barrita();
        System.out.println("Vida: " + this.getVidaActual() + "/" + this.getVidaMaxima());

        if (this.getArma() != null) {
            System.out.println("Arma: " + this.getArma().getNombre());
        } else {
            System.out.println("Arma: -");
        }

        if (this.getArmadura() != null) {
            System.out.println("Armadura: " + this.getArmadura().getNombre());
        } else {
            System.out.println("Armadura: -");
        }
    }
}
