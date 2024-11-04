/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import Dominio.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class Tateti {

    static Sistema sis = new Sistema();

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String texto = "BIENVENIDOS";

        for (int i = 0; i < texto.length(); i++) {

            System.out.print(texto.charAt(i));

            Thread.sleep(260);
        }
        System.out.println();

        interfazMenu();
    }

    public static void interfazMenu() {

        mostrarMenu();

    }

    public static void mostrarMenu() {
        Scanner in = new Scanner(System.in);
        String opcion = " ";
        while (!opcion.equals("e")) {
            System.out.println("a) Registrar un jugador");
            System.out.println("b) Jugar al Gran Tateti entre 2 personas");
            System.out.println("c) Jugar al Gran Tateti vs la Computadora");
            System.out.println("d) Ranking");
            System.out.println("e)  Fin");
            opcion = in.nextLine();
            String opcionMayus = opcion.toUpperCase();
            validarOpcion(opcionMayus);
        }
    }

    public static void validarOpcion(String opcion) {
        String opciones = "ABCDE";
        boolean cumple = false;
        for (int i = 0; i < opciones.length() && !cumple; i++) {
            if (opciones.charAt(i) == opcion.charAt(0)) {
                cumple = true;
            }
        }
        if (!cumple) {
            mostrarMenu();
        }
        if (cumple) {
            opcionElegida(opcion);
        }
    }

    public static void opcionElegida(String opcion) {
        switch (opcion) {
            case "A":
                registrarJugador();
                break;
            case "B":
                jugar2personas();
                break;
            case "C":
                jugarComputadora();
                break;
            case "D":
                ranking();
                break;
            case "E":
                fin();
                break;
        }
    }

    public static void registrarJugador() {
        Scanner in = new Scanner(System.in);
        boolean entradaValida = false;
        int edad = 0;
        while (!entradaValida) {
            System.out.print("Por favor ingrese una edad: ");
            try {
                edad = in.nextInt();
                in.nextLine();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("No es una edad valida, porfavor ingrese numero");
                in.nextLine();
            }
        }
        System.out.println();

        System.out.println("Ingrese nombre");
        String nombre = in.nextLine();

        System.out.println("Ingrese alias");
        String alias = in.nextLine();

        boolean loAgrego = sis.registrarJugador(nombre, edad, alias);
        if (!loAgrego) {
            System.out.println("Alias ya ocupado");
        } else {
            System.out.println("Jugador agregado");
        }
    }

    public static void jugar2personas() {
        Scanner in = new Scanner(System.in);

        ArrayList<Jugador> listaJugadores = sis.getJugadores();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println(listaJugadores.get(i).getNombre());
        }

        String jugador1 = "--";
        String jugador2 = "--";

        System.out.println("Ingrese alias del jugador 1");
        jugador1 = in.nextLine();

        System.out.println("Ingrese alias del jugador 2");
        jugador2 = in.nextLine();

        if (jugador1.equals(jugador2)) {
            boolean distintos = false;
            while (!distintos) {
                System.out.println("Por favor no repetir jugadores, ingrese de nuevo alias de jugador 2");
                jugador2 = in.nextLine();
                if (!jugador1.equals(jugador2)) {
                    distintos = true;
                }
            }
        }

        boolean estan = sis.verificarJugador(jugador1, jugador2);
        boolean sePerdio = false;
        if (estan) {

            sis.crearPartida(jugador1, jugador2);

            //boolean sigueJuego = true;
            System.out.println("Seleccione minitablero, ejemplo 'A1','B1' ");
            String jugada = in.nextLine();
            String jugadaAnterior = jugada;
            //while (sigueJuego) {

            int filaMiniTablero = sis.obtenerFila(jugadaAnterior);
            int columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);

            while (!sis.verificarJugadaPrimera(filaMiniTablero, columnaMiniTablero)) {
                System.out.println("Jugada no valida, porfavor ingrese jugada valida");
                jugada = in.nextLine();
                jugadaAnterior = jugada;
                filaMiniTablero = sis.obtenerFila(jugadaAnterior);
                columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
            }

            boolean empatados = true;
            while (empatados) {
                int fila = 0;
                int columna = 0;
                //MiniTablero mat = sis.obtenerTablero(filaMiniTablero, columnaMiniTablero
                System.out.println("Ingrese jugada en el mini tablero");
                jugada = in.nextLine();
                if (jugada.toUpperCase().equals("M")) {
                    System.out.println("Ingrese jugada en el  tablero grande");
                    jugada = in.nextLine();
                    fila = sis.obtenerFila(jugada);
                    columna = sis.obtenerColumna(jugada);
                    while (!sis.verificarJugadaPrimera(fila, columna)) {
                        System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                        jugada = in.nextLine();
                        fila = sis.obtenerFila(jugada);
                        columna = sis.obtenerColumna(jugada);

                    }
                    boolean sePudo = sis.jugadaMagica(fila, columna);
                    if (!sePudo) {
                        System.out.println("No te queda mas movimiento magico");

                        while (!sis.verificarJugadaPrimera(fila, columna) || !sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {

                            if (!sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                                System.out.println("El casillero seleccionado esta ocupado");
                            } else {
                                System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                            }

                            jugada = in.nextLine();
                            fila = sis.obtenerFila(jugada);
                            columna = sis.obtenerColumna(jugada);

                        }

                    }
                } else {
                    fila = sis.obtenerFila(jugada);
                    columna = sis.obtenerColumna(jugada);

                    while (!sis.verificarJugadaPrimera(fila, columna) || !sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {

                        if (!sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                            System.out.println("El casillero seleccionado esta ocupado");
                        } else {
                            System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                        }

                        jugada = in.nextLine();
                        fila = sis.obtenerFila(jugada);
                        columna = sis.obtenerColumna(jugada);

                    }
                }
                jugadaAnterior = jugada;
                if (!sis.estaLleno(filaMiniTablero, columnaMiniTablero)) {
                    sis.marcar(fila, columna, filaMiniTablero, columnaMiniTablero);

                    if (sis.getPartida().isTurno1()) {

                        System.out.println("Truno del jugador 1");
                    } else {

                        System.out.println("Turno del jugador 2");
                    }

                    filaMiniTablero = sis.obtenerFila(jugadaAnterior);
                    columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
                    mostrarTablero(sis.getPartida(), filaMiniTablero, columnaMiniTablero);
                    empatados = sis.continuarJugando();
                } else {
                    empatados = false;
                    sePerdio = true;
                }

            }
            if (sis.getPartida().getGanador() != null) {
                Jugador jugador = sis.getPartida().getGanador();
                System.out.println("El ganador es " + jugador.getAlias());
                jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
            } else {
                if (sePerdio) {
                    Jugador jugador = sis.ganadorContador();
                    if (jugador == null) {
                        System.out.println("Se empato la partida");
                    } else {
                        System.out.println("El ganador es " + jugador.getAlias());
                        jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
                    }
                } else {
                    System.out.println("Se empato la partida");
                }
            }

        } else {
            System.out.println("Algun jugador no esta en el sistema");
        }

    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String verde = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    public static void mostrarTablero(Partida partida, int fila, int columna) {

        char[][] mat = partida.mat();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                char c = mat[i][j];
                if (c == 'X') {
                    char ganador = partida.ganadorMinitablero(i, j);
                    if (ganador != '-') {
                        if (ganador == 'X') {
                            System.out.print(ANSI_RED + c + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_BLUE + c + ANSI_RESET);
                        }
                    } else {
                        System.out.print(ANSI_RED + c + ANSI_RESET);
                    }
                } else if (c == 'O') {
                    char ganador = partida.ganadorMinitablero(fila, columna);
                    if (ganador != '-') {
                        if (ganador == 'X') {
                            System.out.print(ANSI_RED + c + ANSI_RESET);
                        } else {
                            System.out.print(ANSI_BLUE + c + ANSI_RESET);
                        }
                    } else {
                        System.out.print(ANSI_BLUE + c + ANSI_RESET);
                    }
                } else {

                    if (c == '*') {
                        //if (i == 0 || i == 6 || i == 12 || i == 18) {
                        if (fila == 0 && (i <= 6)) {
                            mostrarAsterisco(columna, j);

                        } else {
                            if (fila == 1 && (i >= 6 && i <= 12)) {
                                mostrarAsterisco(columna, j);
                            } else {
                                if (fila == 2 && (i >= 12 && i <= 18)) {
                                    mostrarAsterisco(columna, j);
                                } else {
                                    System.out.print(verde + c + ANSI_RESET);

                                    //    }
                                    // } else if (j == 0 || j == 6 || j == 12 || j == 18) {
                                }
                            }
                        }

                    } else {
                        System.out.print(c);
                    }
                }

            }
            System.out.println("");
        }
    }

    
public static void jugarComputadora() {
        Scanner in = new Scanner(System.in);

        ArrayList<Jugador> listaJugadores = sis.getJugadores();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println(listaJugadores.get(i).getNombre());
        }

        String jugador1 = "--";
        String jugador2 = "--";

        System.out.println("Ingrese alias del jugador 1");
        jugador1 = in.nextLine();
        
        

        boolean estan = sis.verificarJugadorVsCompu(jugador1);
        boolean sePerdio = false;
        if (estan) {

            sis.crearPartidaCPU(jugador1);

            //boolean sigueJuego = true;
            System.out.println("Seleccione minitablero, ejemplo 'A1','B1' ");
            String jugada = in.nextLine();
            String jugadaAnterior = jugada;
            //while (sigueJuego) {

            int filaMiniTablero = sis.obtenerFila(jugadaAnterior);
            int columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);

            while (!sis.verificarJugadaPrimera(filaMiniTablero, columnaMiniTablero)) {
                System.out.println("Jugada no valida, porfavor ingrese jugada valida");
                jugada = in.nextLine();
                jugadaAnterior = jugada;
                filaMiniTablero = sis.obtenerFila(jugadaAnterior);
                columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
            }

            boolean empatados = true;
            while (empatados) {
                int fila = 0;
                int columna = 0;
                //MiniTablero mat = sis.obtenerTablero(filaMiniTablero, columnaMiniTablero
                jugada=solicitarJugada(sis);
                if (jugada.toUpperCase().equals("M")) {
                    System.out.println("Ingrese jugada en el  tablero grande");
                    jugada = solicitarJugada(sis);
                    fila = sis.obtenerFila(jugada);
                    columna = sis.obtenerColumna(jugada);
                    while (!sis.verificarJugadaPrimera(fila, columna)) {
                        System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                        jugada = in.nextLine();
                        fila = sis.obtenerFila(jugada);
                        columna = sis.obtenerColumna(jugada);

                    }
                    boolean sePudo = sis.jugadaMagica(fila, columna);
                    if (!sePudo) {
                        System.out.println("No te queda mas movimiento magico");

                        while (!sis.verificarJugadaPrimera(fila, columna) || !sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                            if(sis.getPartida().isTurno1())
                            {
                                if (!sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                                    System.out.println("El casillero seleccionado esta ocupado");
                                } else {
                                    System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                                }
                            }
                            jugada = solicitarJugada(sis);
                            fila = sis.obtenerFila(jugada);
                            columna = sis.obtenerColumna(jugada);

                        }

                    }
                } else {
                    fila = sis.obtenerFila(jugada);
                    columna = sis.obtenerColumna(jugada);

                    while (!sis.verificarJugadaPrimera(fila, columna) || !sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                        if(sis.getPartida().isTurno1())
                        {
                            if (!sis.casilleroDisponible(filaMiniTablero, columnaMiniTablero, fila, columna)) {
                                System.out.println("El casillero seleccionado esta ocupado");
                            } else {
                                System.out.println("Ingrese de nuevo ya que ha sido ingresada incorrectamente");
                            }
                        }
                        jugada = solicitarJugada(sis);
                        fila = sis.obtenerFila(jugada);
                        columna = sis.obtenerColumna(jugada);

                    }
                }
                jugadaAnterior = jugada;
                if (!sis.estaLleno(filaMiniTablero, columnaMiniTablero)) {
                    sis.marcar(fila, columna, filaMiniTablero, columnaMiniTablero);

                    if (sis.getPartida().isTurno1()) {

                        System.out.println("Truno del jugador 1");
                    } else {

                        System.out.println("Turno del CPU");
                    }

                    filaMiniTablero = sis.obtenerFila(jugadaAnterior);
                    columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
                    mostrarTablero(sis.getPartida(), filaMiniTablero, columnaMiniTablero);
                    empatados = sis.continuarJugando();
                } else {
                    empatados = false;
                    sePerdio = true;
                }

            }
            if (sis.getPartida().getGanador() != null) {
                Jugador jugador = sis.getPartida().getGanador();
                System.out.println("El ganador es " + jugador.getAlias());
                jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
            } else {
                if (sePerdio) {
                    Jugador jugador = sis.ganadorContador();
                    if (jugador == null) {
                        System.out.println("Se empato la partida");
                    } else {
                        System.out.println("El ganador es " + jugador.getAlias());
                        jugador.setPartidasGanadas(jugador.getPartidasGanadas() + 1);
                    }
                } else {
                    System.out.println("Se empato la partida");
                }
            }

        } else {
            System.out.println("Algun jugador no esta en el sistema");
        }

    }    
    
    
/*    public static void jugarComputadora() {
        Scanner in = new Scanner(System.in);
        Computadora cpu = new Computadora("CPU", 'O');

        ArrayList<Jugador> listaJugadores = sis.getJugadores();
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println(listaJugadores.get(i).getNombre());
        }

        Jugador jugador;
        String alias = " ";
        do {
            System.out.println("Ingrese alias de su jugador");
            alias = in.nextLine();
        } while (!sis.verificarJugadorVsCompu(alias));

        sis.crearPartidaPC(alias, cpu);

        int filaMiniTablero;
        int columnaMiniTablero;
        String jugada;
        String jugadaAnterior;
        do {
            System.out.println("Seleccione minitablero");
            jugada = in.nextLine();
            jugadaAnterior = jugada;
            filaMiniTablero = sis.obtenerFila(jugadaAnterior);
            columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
        } while (!sis.verificarJugadaPrimera(filaMiniTablero, columnaMiniTablero));

        boolean hayGanador = false;
        while (!hayGanador) {
            int fila = 0;
            int columna = 0;
            boolean turnoJugador = true;
            boolean turnoPc = false;
            do {
                System.out.println("Ingrese jugada en el mini tablero");
                jugada = in.nextLine();
                fila = sis.obtenerFila(jugada);
                columna = sis.obtenerColumna(jugada);
            } while (!sis.verificarJugadaPrimera(fila, columna));
            jugadaAnterior = jugada;
            sis.marcar(fila, columna, filaMiniTablero, columnaMiniTablero);

            filaMiniTablero = sis.obtenerFila(jugadaAnterior);
            columnaMiniTablero = sis.obtenerColumna(jugadaAnterior);
            mostrarTablero(sis.getPartida(), filaMiniTablero, columnaMiniTablero);
        }

    }
*/
    public static void ranking() {
        Collections.sort(sis.getJugadores(), sis.getCriterioPartidasGanadas());
        for (int i = 0; i < sis.getJugadores().size(); i++) {
            Jugador aliasdeparticipantes = sis.getJugadores().get(i);

            String contador = "";
            for (int j = 0; j < aliasdeparticipantes.getPartidasGanadas(); j++) {
                contador = contador + "#";
            }

            System.out.println(aliasdeparticipantes.getAlias() + " |" + contador);
        }

    }

    public static void fin() {
        System.out.println("El programa va a finalizar.");
        System.exit(0);  // Termina el programa
    }

    private static void mostrarAsterisco(int columna, int j) {
        if (columna == 0 && (j <= 6) || (columna == 1 && (j >= 6 && j <= 12)) || (columna == 2 && (j >= 12 && j <= 18))) {
            System.out.print(ANSI_YELLOW_BACKGROUND + "*" + ANSI_RESET);
        } else {
            System.out.print(verde + "*" + ANSI_RESET);
        }
    }

    private static String solicitarJugada(Sistema sis) {
        Scanner in =new Scanner(System.in);
        String jugada;
        if(sis.getPartida().isTurno1())
        {
            System.out.println("Ingrese jugada en el mini tablero");
            jugada = in.nextLine();
        }
        else{
            jugada=sis.solicitarCPU();
        }
        return jugada;
                
        
    }

}
