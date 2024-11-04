/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author Lenovo
 */
public class TableroGrande {

    private MiniTablero[][] tableroGrande;
    private String[] coordenadas = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3"};

    public TableroGrande() {
        int pos = 0;
        tableroGrande = new MiniTablero[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tableroGrande[i][j] = new MiniTablero();
                tableroGrande[i][j].setCordenada(coordenadas[pos]);
                pos++;
            }
        }
    }

    /**
     * @return the tableroGrande
     */
    public MiniTablero[][] getTableroGrande() {
        return tableroGrande;
    }

    public MiniTablero getMiniTablero(int fila, int columna) {
        return tableroGrande[fila][columna];
    }

    /**
     * @param tableroGrande the tableroGrande to set
     */
    public void setTableroGrande(MiniTablero[][] tableroGrande) {
        this.tableroGrande = tableroGrande;
    }

    /**
     * @return the coordenadas
     */
    public String[] getCoordenadas() {
        return coordenadas;
    }

    /**
     * @param coordenadas the coordenadas to set
     */
    public void setCoordenadas(String[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public MiniTablero obtenerTablero(int fila, int columna) {
        MiniTablero mat = null;
        for (int i = 0; i < getTableroGrande().length; i++) {
            for (int j = 0; j < getTableroGrande()[0].length; j++) {
                if (i == fila && j == columna) {
                    mat = getTableroGrande()[i][j];
                }
            }
        }
        return mat;

    }

    public void marcar(int fila, int columna, int filaMiniTablero, int columnaMiniTablero, char simbolo) {

        MiniTablero miniTablero = getMiniTablero(filaMiniTablero, columnaMiniTablero);
        miniTablero.marcar(fila, columna, simbolo);

    }

    public boolean verificarJugadaJuego(int fila, int columna, int filaMiniTablero, int columnaMiniTablero) {
        boolean valida = true;
        if ((fila > tableroGrande.length || fila < 0) || (columna < 0 || columna > tableroGrande[0].length - 1)) {
            valida = false;
        }
        if (tableroGrande[filaMiniTablero][columnaMiniTablero].estaOcupado(fila, columna)) {
            valida = false;
        }
        if (tableroGrande[filaMiniTablero][columnaMiniTablero].getGanador() != '-') {
            valida = false;
        }
        return valida;
    }

    public boolean hayGanador() {
        boolean ganador = false;
        int i = 0;
        while (i < tableroGrande.length && !ganador) {
            ganador = tableroGrande[i][0].getGanador() != '-' && tableroGrande[i][0].getGanador() == tableroGrande[i][1].getGanador() && tableroGrande[i][1].getGanador() == tableroGrande[i][2].getGanador();
            if (!ganador) {
                i++;
            }
        }
        if (!ganador) {
            i = 0;
            while (i < tableroGrande[0].length && !ganador) {
                ganador = tableroGrande[0][i].getGanador() != '-' && tableroGrande[0][i].getGanador() == tableroGrande[1][i].getGanador() && tableroGrande[1][i].getGanador() == tableroGrande[2][i].getGanador();
                if (!ganador) {
                    i++;
                }
            }
            if (!ganador) {
                ganador=tableroGrande[0][2].getGanador() != '-' && tableroGrande[0][2].getGanador() == tableroGrande[1][1].getGanador() && tableroGrande[1][1].getGanador() == tableroGrande[2][0].getGanador();
                if(!ganador)
                {
                    ganador=tableroGrande[0][0].getGanador() != '-' && tableroGrande[0][0].getGanador() == tableroGrande[1][1].getGanador() && tableroGrande[1][1].getGanador() == tableroGrande[2][2].getGanador();
                
                }
            
            }

        }
        return ganador;
    }

    public boolean hayEmpate() {
        boolean casilleroVacio=false;
        int fila=0;
       while(fila<tableroGrande.length && !casilleroVacio)
       {
           int columna=0;
           while(columna<tableroGrande[0].length && !casilleroVacio)
           {
           
             casilleroVacio=!(tableroGrande[fila][columna].getGanador()!='-' || tableroGrande[fila][columna].estaLleno());
             columna++;
           }
           fila++;
       }
        return !casilleroVacio;
    }

    public int contador(char simbolo) {
        int contador=0;
        for(int fila=0;fila<3;fila++)
        {
            for(int columna=0;columna<3;columna++)
            {
                if(tableroGrande[fila][columna].getGanador()==simbolo)
                    contador++;
            }
        }
        return contador;
    }

    public boolean estaLleno(int filaMiniTablero, int columnaMiniTablero) {
        return tableroGrande[filaMiniTablero][columnaMiniTablero].estaLleno();
    }

    public char ganadorMinitablero(int fila, int columna) {
        return tableroGrande[fila][columna].getGanador();
    }

    public void movimientoMagico(int fila, int columna) {
        tableroGrande[fila][columna].movimientoMagico();
        
    }

    public boolean casilleroDisponible(int filaMiniTablero, int columnaMiniTablero, int fila, int columna) {
        return !tableroGrande[filaMiniTablero][columnaMiniTablero].estaOcupado(fila, columna);
    }
}
