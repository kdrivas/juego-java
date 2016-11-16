/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.HojaSprites;
import Vista.VentanaInformacion;
import java.awt.Graphics;

/**
 *
 * @author alulab14
 */
public class GestorInteractuar implements EstadoJuego {

    private VentanaInformacion ventanaInformacion;

    private GestorLaberinto gestorMapa;
    private GestorAvatar gestorAvatar;
    //Como funciona la hoja de sprites es secundario, si funciona y solo carga un bufferedImage y lo corta
    private HojaSprites hoja = new HojaSprites();

    public static boolean finInteractuar = true;

    public GestorInteractuar(HojaSprites hoja, GestorLaberinto gestorMapa, GestorAvatar gestorAvatar, VentanaInformacion ventanaInformacion) {
        this.hoja = hoja;
        this.gestorMapa = gestorMapa;
        this.gestorAvatar = gestorAvatar;
        this.ventanaInformacion = ventanaInformacion;

    }

    @Override
    public void dibujar(Graphics g) {
        ventanaInformacion.dibujar(g, gestorAvatar);
    }

    @Override
    public void actualizar(Teclado teclado) {
        finInteractuar = gestorAvatar.interactuar(gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));
        System.out.println(gestorAvatar.getDireccion());
        if (!finInteractuar) {
            System.out.println("Estoy en interaccion");
            switch (gestorAvatar.getDireccion()) {
                case 'n':
                    finInteractuar = gestorAvatar.batalla(teclado, (int) gestorAvatar.getPosicionX() / 48, ((int) gestorAvatar.getPosicionY() - 64) / 64, gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));
                    break;
                case 's':
                    finInteractuar = gestorAvatar.batalla(teclado, (int) gestorAvatar.getPosicionX() / 48, ((int) gestorAvatar.getPosicionY() + 64) / 64, gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));
                    break;
                case 'e':
                    finInteractuar = gestorAvatar.batalla(teclado, (int)(gestorAvatar.getPosicionX() + 48) / 48, (int) gestorAvatar.getPosicionY() / 64, gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));
                    break;
                case 'o':
                    finInteractuar = gestorAvatar.batalla(teclado, (int)(gestorAvatar.getPosicionX() - 48) / 48, (int) gestorAvatar.getPosicionY() / 64, gestorMapa.arrLaberintos.get(gestorMapa.getNivel()));
                    break;
            }
        }
    }

}
