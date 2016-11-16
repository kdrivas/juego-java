/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import FACILIDADES.Aliado;
import Vista.*;
import Model.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class GestorAvatar {

    private static final int FRAMES = 30;

    private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - 22, Constantes.CENTRO_VENTANA_Y - 5, Constantes.ANCHO_JUGADOR - 6, 1);
    private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - 22, Constantes.CENTRO_VENTANA_Y + 31, Constantes.ANCHO_JUGADOR - 6, 1);
    private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - 24, Constantes.CENTRO_VENTANA_Y - 2, 1, Constantes.ALTO_JUGADOR / 2);
    private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + 20, Constantes.CENTRO_VENTANA_Y - 2, 1, Constantes.ALTO_JUGADOR / 2);

    private double posicionX;
    private double posicionY;

    private char direccion = 'n';
    private String nombreSprite = "JUGADOR_ARRIBA";

    private HojaSprites hoja;

    private int animacion = 0;
    private boolean enMovimiento = false;

    private GestorLaberinto lab;

    private Avatar avatar;

    private static Enemigo enemigo = null;

    public GestorAvatar(HojaSprites hoja, double posicionX, double posicionY, Arma arma) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;

        this.hoja = hoja;
        this.lab = lab;

        avatar = new Avatar(arma);
    }

    public void modificaPosicionAvatarEnLaberinto(GestorLaberinto gt, int nivel, int posFuturaX, int posFuturaY) {
        Celda celdaSiguiente = gt.arrLaberintos.get(nivel).getCeldaLaberinto(posFuturaX / 48, posFuturaY / 64);
        Celda celdaActual = gt.arrLaberintos.get(nivel).getCeldaLaberinto((int) posicionX / 48, (int) posicionY / 64);
        celdaActual.setObjEntidad(null);
        celdaSiguiente.setObjEntidad(getAvatar());

        gt.arrLaberintos.get(nivel).imprimir();
    }

    public void actualizar(Teclado teclado, GestorLaberinto gt, int nivel) {
        if (animacion < 32767) {
            animacion++;
        } else {
            animacion = 0;
        }

        if (teclado.arriba) {
            setDireccion('n');
            if (!enColision(gt, nivel, (int) posicionX, (int) (posicionY - 64))) {
                setEnMovimiento(true);
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int) posicionX, (int) (posicionY - 64));
                posicionY -= 64;

            } else {
                setEnMovimiento(false);
            }
        } else if (teclado.abajo) {
            setDireccion('s');

            if (!enColision(gt, nivel, (int) posicionX, (int) (posicionY + 64))) {
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int) posicionX, (int) (posicionY + 64));
                posicionY += 64;
                setEnMovimiento(true);
            } else {
                setEnMovimiento(false);
            }
        } else if (teclado.izquierda) {
            setDireccion('o');

            if (!enColision(gt, nivel, (int) (posicionX - 48), (int) posicionY)) {
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int) posicionX - 48, (int) posicionY);
                posicionX -= 48;
                setEnMovimiento(true);

            } else {
                setEnMovimiento(false);
            }
        } else if (teclado.derecha) {
            setDireccion('e');

            if (!enColision(gt, nivel, (int) (posicionX + 48), (int) posicionY)) {
                modificaPosicionAvatarEnLaberinto(gt, nivel, (int) posicionX + 48, (int) posicionY);
                posicionX += 48;
                setEnMovimiento(true);

            } else {
                setEnMovimiento(false);
            }
        } else {
            setEnMovimiento(false);
        }

        escogeSprite();
    }

    public void dibujar(Graphics g) {
        int centroX = Constantes.ANCHO_VENTANA / 2 - Constantes.ANCHO_JUGADOR / 2;
        int centroY = Constantes.ALTO_VENTANA / 2 - Constantes.ALTO_JUGADOR / 2;

        g.setColor(Color.green);
        //g.fillRect(centroX, centroY, ANCHO_SPRITE, ALTO_SPRITE);
        g.drawImage(hoja.SpriteKey(nombreSprite), centroX, centroY, null);

//        g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
//        g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
//        g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
//        g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
    }

    //Aca escojo el nombre de la imagen que se escribira
    private void escogeSprite() {
        if (getDireccion() == 'n') {
            nombreSprite = "JUGADOR_ARRIBA";
            if (isEnMovimiento()) {
                System.out.println("lalalalalala");
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "JUGADOR_ARRIBA";
                } else {
                    nombreSprite = "JUGADOR_ARRIBA";
                }
            }
        } else if (getDireccion() == 's') {
            nombreSprite = "JUGADOR_ABAJO";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "JUGADOR_ABAJO";
                } else {
                    nombreSprite = "JUGADOR_ABAJO";
                }
            }
        } else if (getDireccion() == 'o') {
            nombreSprite = "JUGADOR_IZQUIERDA";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "JUGADOR_IZQUIERDA";
                } else {
                    nombreSprite = "JUGADOR_IZQUIERDA";
                }
            }
        } else if (getDireccion() == 'e') {
            nombreSprite = "JUGADOR_DERECHA";
            if (isEnMovimiento()) {
                if (animacion % FRAMES > FRAMES / 2) {
                    nombreSprite = "JUGADOR_DERECHA";
                } else {
                    nombreSprite = "JUGADOR_DERECHA";
                }
            }
        }
    }

    private boolean enColision(GestorLaberinto gtr, int nivel, int posFuturaX, int posFuturaY) {

        //CAMBIAR ACA CUANDO ESTEN LISTOS LOS ENEMIGOS
        Celda celda = gtr.arrLaberintos.get(nivel).getCeldaLaberinto(posFuturaX / 48, posFuturaY / 64);
        if (celda.getEstado().equals("PARED") || celda.getObjEntidad() != null) {
            return true;
        } else {
            return false;
        }
        //return gtr.rec.intersects(LIMITE_ARRIBA) || gtr.rec.intersects(LIMITE_ABAJO) || gtr.rec.intersects(LIMITE_IZQUIERDA);

    }

    public void opcionesObjeto(Artefacto obj, Saco mochila, Avatar pj) {
        int uso = 1;
        Scanner sc = new Scanner(System.in);
        while (uso < 4 && uso > 0) {// mientras seleccione una opcion correcta
            System.out.println("Que desea hacer con el objeto?");
            if (obj.getTipo().equals("POCION")) {
                System.out.println("(1)Usar      (2)botar     (3)salir");
                uso = sc.nextInt();
                if (uso == 1) {
                    Pocion po = (Pocion) obj;
                    pj.usar(po);
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else if (uso == 2) {
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else {
                    break;
                }
            } else if (obj.getTipo().equals("ARMA") || obj.getTipo().equals("ARMADURA")) {
                System.out.println("(1)Equipar      (2)botar     (3)salir");
                uso = sc.nextInt();
                if (uso == 1) {
                    if (obj instanceof Arma) {
                        Arma arm = (Arma) obj;
                        pj.equipar(arm);
                        uso = 0;
                    } else if (obj instanceof Armadura) {
                        Armadura arm = (Armadura) obj;
                        pj.equipar(arm);
                        uso = 0;
                    }
                } else if (uso == 2) {
                    mochila.botarArtefacto(obj);
                    uso = 0;
                } else {
                    break;
                }
            }
        }
    }

    public void menuMochila(Avatar pj) {
        char indice, opcion;

        System.out.println();
        System.out.println("MENU MOCHILA");

        while (pj.mostrarMochila() > 0) {
            String ax = JOptionPane.showInputDialog("(1)Seleccionar            (2)Salir");
            JOptionPane.showMessageDialog(null, "El numero ingresado es: " + ax);
            opcion = ax.charAt(0);
            if (opcion == '1') {
                Saco mochila = pj.getMochila();

                while (true) {
                    String cmnd = JOptionPane.showInputDialog("Escriba el indice del objeto que desea:");
                    JOptionPane.showMessageDialog(null, "El numero ingresado es: " + cmnd);
                    indice = cmnd.charAt(0);
                    if ((indice - '0') >= 1 && (indice - '0') <= mochila.getListaDeArtefactos().size()) {
                        break;
                    }
                }
                Artefacto obj = mochila.seleccionarArtefacto(indice - '1');
                obj.mostrarDetalle();
                opcionesObjeto(obj, mochila, pj);//opciones que parecen al seleccionar un objeto
            } else if (opcion == '2') {
                break;
            }
        }
        if (pj.mostrarMochila() == 0) {
            JOptionPane.showMessageDialog(null, "NO PUEDES ENTRAR AL MENU");
        }
    }
// recibe posiciones futuras

    public boolean batalla(Teclado teclado, int x, int y, Laberinto lab) { //Batalla
        int puntosDanio;

        if (enemigo != null) {
            if (teclado.mochila) {
                return false;

            } else if (teclado.huir) {
                System.out.println("Estoy huyendo");
                return true;

            } else if (teclado.atacar) {
                if (!avatar.muerto() && !enemigo.muerto()) {
                    int n = avatar.calcularDano();
                    System.out.println("Estoy atacando");
                    puntosDanio = enemigo.getDefensa() - n;
                    if (puntosDanio < 0) {
                        enemigo.setVidaActual(enemigo.getVidaActual() + puntosDanio);
                    }
                    if (enemigo.muerto()) {
                        System.out.println("Enemigo esta muerto");
                        lab.getCeldaLaberinto(x, y).setObjEntidad(null);
                        return true;
                    } else if (avatar.getArmadura() != null) {
                        puntosDanio = avatar.getArmadura().getPuntosDefensa() - enemigo.getFuerza();
                        if (puntosDanio >= 0) {
                            avatar.setVidaActual(avatar.getVidaActual() - 1);
                        } else {
                            avatar.setVidaActual(avatar.getVidaActual() + puntosDanio);
                        }

                    } else {
                        avatar.setVidaActual(avatar.getVidaActual() - enemigo.getFuerza());
                    }
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return true;

    }

    public void guardar(int x, int y, Laberinto lab) {
        Celda celda = lab.getCeldaLaberinto(x, y);
        Artefacto obj = celda.getObjArtefacto();
        if (obj != null) {
            if (avatar.guardarMochila(obj) > 0) {
                celda.setObjArtefacto(null);
            }
        }

    }

    public boolean identificarInteractuar(int x, int y, Laberinto lab) {
        Celda celda = lab.getCeldaLaberinto(x, y);

        if (celda.getObjArtefacto() != null) {// si obtiene objeto  quiere decir que debe interactuar con artefacto
            enemigo = null;
            guardar(x, y, lab);
            return true;
        } else if (celda.getObjEntidad() != null) {//SI OBTIENE ENTIDAD SI ES ENEMIGO O ALIADO
            Entidad e = celda.getObjEntidad();
            if (e instanceof Enemigo) {
                System.out.println("Encontre un enemigui");
                enemigo = (Enemigo) e;
                return false;
            } else if (e instanceof Aliado) {
                enemigo = null;
                Aliado al = (Aliado) e;
                Random rn = new Random();
                int ind = (int) (rn.nextDouble() * al.getConsejos().size());
                String consejo = al.getConsejos().get(ind).getMensaje();

                celda.setObjEntidad(null);
                return true;
            }

        }
        return true;
    }

    public boolean interactuar(Laberinto lab) {// CREADO PARA LA SEGUNDA MODIFICACION
        //Requiriendo la direccion para interactuar
        int x = lab.hallarPosXAvatar();
        int y = lab.hallarPosYAvatar();
        switch (getDireccion()) {// aluego se identifica si es entidad o objeto 
            case 'e':
                return identificarInteractuar(x + 1, y, lab);
            case 'o':
                return identificarInteractuar(x - 1, y, lab);
            case 'n':
                return identificarInteractuar(x, y - 1, lab);
            default:
                return identificarInteractuar(x, y + 1, lab);
        }
    }

    public double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(double posicionX) {
        this.posicionX = posicionX;
    }

    public double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(double posicionY) {
        this.posicionY = posicionY;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }

    /**
     * @return the direccion
     */
    public char getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }
}
