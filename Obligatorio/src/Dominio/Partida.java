/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author Lenovo
 */
public class Partida {

    private Jugador jugador;
    private Jugador jugador2;
    private boolean magico1;
    private boolean magico2;

    private char[][] matAux;
    private TableroGrande tablero;
    private boolean turno1;
    private Jugador ganador;
    private boolean contraComputadora;
   

    Partida(Jugador jug1, Jugador jug2) {
        jugador = jug1;
        jugador2 = jug2;
        inicializarTablero();
        tablero = new TableroGrande();
        turno1 = true;
        ganador = null;
        magico1 = true;
        magico2 = true;

    }

    Partida(Jugador jug1, Computadora cpu) {
        jugador = jug1;
        
        inicializarTablero();
        tablero = new TableroGrande();
        turno1 = true; // Empieza el jugador 1
        ganador = null;
        contraComputadora = true; // Es contra computadora
        magico1 = true;
        magico2 = true;
    }

    public boolean isTurno1() {
        return turno1;
    }

    public Partida() {
        inicializarTablero();
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    private void inicializarTablero() {
        matAux = new char[19][19];
        for (int i = 0; i < matAux.length; i++) {
            for (int j = 0; j < matAux[0].length; j++) {
                if (i == 0 || i == 6 || i == 12 || i == 18) {
                    matAux[i][j] = '*';
                }
                if (j == 0 || j == 6 || j == 12 || j == 18) {
                    matAux[i][j] = '*';
                }
                if (i == 2 || i == 4 || i == 8 || i == 10 || i == 14 || i == 16) {
                    if (j == 1 || j == 3 || j == 5 || j == 7 || j == 9 || j == 11 || j == 13 || j == 15 || j == 17) {
                        matAux[i][j] = '-';
                    }
                    if (j == 2 || j == 4 || j == 8 || j == 10 || j == 14 || j == 16) {
                        matAux[i][j] = '+';
                    }
                }
                if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15 || i == 17) {
                    if (j == 2 || j == 4 || j == 8 || j == 10 || j == 14 || j == 16) {
                        matAux[i][j] = '|';
                    }
                    if (j == 1 || j == 3 || j == 5 || j == 7 || j == 9 || j == 11 || j == 13 || j == 15 || j == 17) {
                        matAux[i][j] = ' ';
                    }
                }

            }
        }
    }

    public char[][] mat() {
        return matAux;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public MiniTablero obtenerTablero(int fila, int columna) {
        return tablero.obtenerTablero(fila, columna);
    }

    public void marcar(int fila, int columna, int filaMiniTablero, int columnaMiniTablero) {

        char simbolo = obtenerTurno();
        tablero.marcar(fila, columna, filaMiniTablero, columnaMiniTablero, simbolo);

        int filaEnMatAux = (filaMiniTablero * 6) + (fila * 2) + 1;
        int columnaEnMatAux = (columnaMiniTablero * 6) + (columna * 2) + 1;
        matAux[filaEnMatAux][columnaEnMatAux] = simbolo;
        cambiarTurno();

    }

    private char obtenerTurno() {
        char simbolo = 'O';
        if (turno1) {
            simbolo = 'X';
        }
        return simbolo;
    }

    private void cambiarTurno() {
        turno1 = !turno1;
    }

    public boolean verificarJugadaJuego(int fila, int columna, int filaMiniTablero, int columnaMiniTablero) {
        return tablero.verificarJugadaJuego(fila, columna, filaMiniTablero, columnaMiniTablero);
    }

    public boolean continuarJugando() {
        boolean respuesta = tablero.hayGanador();
        if (respuesta) {
            ganador = turnoGanador();
        } else {
            respuesta = tablero.hayEmpate();
        }
        return !respuesta;
    }

    private Jugador turnoGanador() {
        if (turno1) {
            return jugador2;
        } else {
            return jugador;
        }
    }

    public Jugador ganadorContador() {
        int contadorX = tablero.contador('X');
        int contadorO = tablero.contador('O');
        if (contadorX > contadorO) {
            return jugador;
        } else {
            if (contadorX < contadorO) {
                return jugador2;
            } else {
                return null;
            }
        }
    }

    public boolean estaLleno(int filaMiniTablero, int columnaMiniTablero) {
        return tablero.estaLleno(filaMiniTablero, columnaMiniTablero);
    }

    public char ganadorMinitablero(int fila, int columna) {
        int filaGrande;
        int columnaGrande;
        if (fila <= 6) {
            filaGrande = 0;
        } else {
            if (fila <= 12) {
                filaGrande = 1;
            } else {
                filaGrande = 2;
            }
        }
        if (columna <= 6) {
            columnaGrande = 0;
        } else {
            if (columna <= 12) {
                columnaGrande = 1;
            } else {
                columnaGrande = 2;
            }
        }
        return tablero.ganadorMinitablero(filaGrande, columnaGrande);
    }

    public boolean jugadaMagica(int fila, int columna) {
        boolean resultado = false;
        if (turno1) {
            if (magico1) {
               
                tablero.movimientoMagico(fila, columna);
                limpiarTablero(fila,columna);
                magico1 = false;
                resultado = true;
            }

        } else {
            if (magico2) {
                
                tablero.movimientoMagico(fila, columna);
                limpiarTablero(fila,columna);
                magico2 = false;
                resultado = true;
            }

        }
        return resultado;
    }

    private void limpiarTablero(int filaMiniTablero, int columnaMiniTablero) {
        for(int fila=0;fila<3;fila++)
        {
            for(int columna=0;columna<3;columna++)
            {
                int filaEnMatAux = (filaMiniTablero * 6) + (fila * 2) + 1;
               int columnaEnMatAux = (columnaMiniTablero * 6) + (columna * 2) + 1;
               matAux[filaEnMatAux][columnaEnMatAux] = ' ';
            }
        }
    }

    public boolean casilleroDisponible(int filaMiniTablero, int columnaMiniTablero, int fila, int columna) {
        return tablero.casilleroDisponible(filaMiniTablero,columnaMiniTablero,fila,columna);
    }

    public String solicitarCPU() {
        Computadora  computadora=(Computadora)jugador2;
        return computadora.obtenerJugadaAleatoria();
    }

}
