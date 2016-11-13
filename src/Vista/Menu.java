/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


/**
 *
 * @author Carlos
 */
public class Menu  {

    public Menu() {

    }

    public void MenuPrincipal() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("▓█████▄  ██▓   ▄▄▄      ▄▄▄▄     ██     ██▓▄▄█████▓  ▒█████");
        System.out.println("▒██▀ ██ ▓██  ▒▒████▄   ▓█████▄  ▓██▒   ▓██▒▓  ██▒ ▓▒▒██▒  ██▒");
        System.out.println("░██   █ ▒██ ▒▒██  ▀█▄  ▒██▒ ▄██ ▒██░   ▒██▒▒ ▓██░ ▒░▒██░  ██▒");
        System.out.println("░▓█▄   █░██ ░░██▄▄▄██  ▒██░█▀   ▒██░   ░██░░  ██▓ ░ ▒██    ██░");
        System.out.println("░▒████▓ ░██░ ▓█   ▓██▒░▓█  ▀█▓  ░█████▒░██░  ▒██▒     ░████▓▒░");
        System.out.println("▒▒▓  ▒ ░▓   ▒▒    ▓▒█░░▒▓███▀▒░ ▒░▓  ░░▓    ▒ ░░   ░ ▒░▒░▒░ ");
        System.out.println("░ ▒  ▒  ▒ ░  ▒   ▒▒ ░▒░▒   ░ ░ ░ ▒  ░ ▒ ░    ░      ░ ▒ ▒░ ");
        System.out.println("░ ░  ░  ▒ ░  ░   ▒    ░    ░   ░ ░    ▒ ░  ░      ░ ░ ░ ▒  ");
        System.out.println("░     ░        ░  ░ ░          ░  ░ ░               ░ ░  ");
        System.out.println("░                          ░                    ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                               (1) Ingresar al juego");
        System.out.println("                               (2) Salir");
        System.out.println("                               (3) Cargar partida");
        System.out.println();
        System.out.println("Ingrese opcion:");
    }

    public void historia() {
        System.out.println("AL inicio de los tiempos, se crearon 3 universos paralelos, conocidos como");
        System.out.println("Cielo, Juicio e Infierno, y 3 razas creadas especificamente para dominar ");
        System.out.println("dichos universos. Los angeles y los demonios eran superiores, fuertes y");
        System.out.println("orgullosos, y eran los encargados del Cielo y el Infierno, respectivamente.");
        System.out.println("Por otro lado, los hombres eran una raza inferior, que esperaban ser juzgados");
        System.out.println("a su muerte para pasar a alguno de los otros universos. Si una persona es ");
        System.out.println("bondadosa a lo largo de su vida, es enviada al Cielo, para vivir con los");
        System.out.println("angeles, rodeado de riquezas y belleza. Si es un pecador, es enviado a lo");
        System.out.println("que los mismo demonios llamaban \"Nueve Anillos del Infierno\", lugar al que se");
        System.out.println("exiliaba a los demonios traidores y criminales, ademas de a los humanos");
        System.out.println("pecadores. Este lugar es un recinto terrorifico, oscuro, lleno de muerte y ");
        System.out.println("sufrimiento, formado por una serie de laberintos, cada uno resguardado por un");
        System.out.println("demonio superior.");
        System.out.println();
        System.out.println("Diablo, el rey de los demonios, tenia varias hijos, y entre ellos habia uno");
        System.out.println("que no era de casta noble. Por ello, Diablo no quizo darle un nombre ni ");
        System.out.println("reconocerlo como suyo, y lo mantuvo escondido durante toda su vida en un");
        System.out.println("calabozo. Eventualmente, el joven demonio escapo y armo una rebelion en todo ");
        System.out.println("el imperio, saqueando ciudades y atacando al ejercito del rey. Finalmente, ");
        System.out.println("el rey Diablo logro vencer la rebelion, capturo a su hijo, y publicamente lo");
        System.out.println("desterro al inframundo, a los Nueve Anillos del Infierno.");
        System.out.println();
        System.out.println();

    }

    public void ganaste() {

        for (int i = 0; i < 33; i++) {
            if (i == 16) {
                System.out.println("                                   GANASTE");
            } else {
                System.out.println();
            }
        }
    }
}
