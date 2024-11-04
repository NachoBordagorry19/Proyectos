/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author Lenovo
 */
public class MiniTablero {

    private char[][] tableroChico;
    private char ganador;
    private String cordenada;

    public MiniTablero() {
        tableroChico = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroChico[i][j] = ' ';
            }
        }
        ganador = '-';
        cordenada = "--";
    }

    public char[][] getTableroChico() {
        return tableroChico;
    }

    public void setTableroChico(char[][] tableroChico) {
        this.tableroChico = tableroChico;
    }

    public char getGanador() {
        return ganador;
    }

    public void setGanador(char ganador) {
        this.ganador = ganador;
    }

    public String getCordenada() {
        return cordenada;
    }

    public void setCordenada(String cordenada) {
        this.cordenada = cordenada;
    }

    public void marcar(int fila, int columna, char simbolo) {
        tableroChico[fila][columna] = simbolo;
        tieneGanador();
    }

    public boolean estaOcupado(int fila, int columna) {
        return tableroChico[fila][columna] != ' ';
    }

    private void tieneGanador() {
        boolean fila = false;
        int i = 0;
        while (i < tableroChico.length && !fila) {
            fila = tableroChico[i][0] != ' ' && tableroChico[i][0] == tableroChico[i][1] && tableroChico[i][1] == tableroChico[i][2];
            if (!fila) {
                i++;
            }
        }
        if (i < tableroChico.length) {
            ganador = tableroChico[i][0];
        } else {
            boolean columna = false;
            i = 0;
            while (i < tableroChico[0].length && !columna) {
                columna = tableroChico[0][i] != ' ' && tableroChico[0][i] == tableroChico[1][i] && tableroChico[2][i] == tableroChico[1][i];
                if (!columna) {
                    i++;
                }
            }
            if (i < tableroChico[0].length) {
                ganador = tableroChico[i][0];
            } else {

                boolean diagonal = tableroChico[1][1] != ' ' && tableroChico[1][1] == tableroChico[2][0] && tableroChico[1][1] == tableroChico[0][2];
                if (diagonal) {
                    ganador = tableroChico[1][1];
                } else {
                    diagonal = tableroChico[1][1] != ' ' && tableroChico[1][1] == tableroChico[0][0] && tableroChico[1][1] == tableroChico[2][2];
                    if (diagonal) {
                        ganador = tableroChico[1][1];
                    }
                }

            }
        }

    }

    public boolean estaLleno() {
        boolean casilleroVacio = false;
        int fila = 0;
        while (fila < tableroChico.length && !casilleroVacio) {
            int columna = 0;
            while (columna < tableroChico[0].length && !casilleroVacio) {
                casilleroVacio = tableroChico[fila][columna] == ' ';
                columna++;
            }
            fila++;
        }
        return !casilleroVacio;
    }

    public void movimientoMagico() {
        resetear();
        
    }

    private void resetear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroChico[i][j] = ' ';
            }
        }
    }

}
