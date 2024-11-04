/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Lenovo
 */
public class Sistema {

    private ArrayList<Jugador> listajugadores;
    private ArrayList<Partida> Listapartidas;
    private Partida partida;

    public Sistema() {
        listajugadores = new ArrayList<>();
        registrarJugador("A", 0, "A");
        registrarJugador("B", 0, "B");
        Listapartidas = new ArrayList<>();

    }

    public ArrayList<Jugador> getJugadores() {
        return listajugadores;
    }

    public boolean registrarJugador(String nombre, int edad, String alias) {

        boolean agrego = true;
        Jugador nuevoJugador = new Jugador(edad, nombre, alias);
        boolean repetido = false;
        for (int i = 0; i < this.listajugadores.size() && !repetido; i++) {
            if (nuevoJugador.getAlias().equals(listajugadores.get(i).getAlias())) {
                repetido = true;
            }
        }
        if (repetido) {
            agrego = false;
        } else {
            listajugadores.add(nuevoJugador);
        }
        return agrego;
    }

    public boolean verificarJugador(String jugador1, String jugador2) {
        boolean estan = false;
        boolean j1 = false;
        boolean j2 = false;
        for (int i = 0; i < this.listajugadores.size() && !estan; i++) {
            if (jugador1.equals(listajugadores.get(i).getAlias())) {
                j1 = true;
            }
            if (jugador2.equals(listajugadores.get(i).getAlias())) {
                j2 = true;
            }
            if (j1 && j2) {
                estan = true;
            }
        }
        return estan;
    }
    
    public boolean verificarJugadorVsCompu(String alias){
        boolean esta = false;
        for (int i = 0; i < this.listajugadores.size() && !esta; i++){
            if (alias.equals(listajugadores.get(i).getAlias())){
                esta = true;
            }
        }
        return esta;
    }

    
    public boolean verificarJugadaPrimera(int fila, int columna) {
        boolean valida = true;
        if (fila < 0 || fila >= 3) {
            valida = false;
        }
        if (columna < 0 || columna >= 3) {
            valida = false;
        }
        return valida;
    }

    public MiniTablero obtenerTablero(int fila, int columna) {
        return partida.obtenerTablero(fila,columna);
        
    }

    public boolean verificarJugadaJuego(int fila, int columna,int filaMiniTablero,int  columnaMiniTablero) {
       
        return partida.verificarJugadaJuego(fila,columna,filaMiniTablero,columnaMiniTablero);
       
    }

    public boolean tieneGanador(MiniTablero mat) {
        boolean tiene = false;
        if (mat.getGanador() != '-') {
            tiene = true;
        }
        return tiene;
    }
    
    public int obtenerFila(String jugada) {
        jugada = jugada.toLowerCase();
        int fila = -1;
        if (jugada.charAt(0) == 'a') {
            fila = 0;
        }

        if (jugada.charAt(0) == 'b') {
            fila = 1;
        }

        if (jugada.charAt(0) == 'c') {
            fila = 2;
        }
        return fila;
    }

    public int obtenerColumna(String jugada) {
        int columna = -1;
        jugada = jugada.toLowerCase();
        if (jugada.charAt(1) == '1') {
            columna = 0;
        }

        if (jugada.charAt(1) == '2') {
            columna = 1;
        }

        if (jugada.charAt(1) == '3') {
            columna = 2;
        }
        return columna;
    }

    public boolean ocupado(MiniTablero mat, int fila, int columna) {
        boolean si = false;
        for (int i = 0; i < mat.getTableroChico().length && !si; i++) {
            for (int j = 0; j < mat.getTableroChico()[0].length && !si; j++) {
                if (i == fila && j == columna) {
                    if (mat.getTableroChico()[i][j] == 'X' || mat.getTableroChico()[i][j] == 'O') {
                        si = true;
                    }
                }
            }
        }
        return si;
    }

    public void marcar(int fila, int columna,  int filaMiniTablero, int columnaMiniTablero) {
       partida.marcar(fila,columna,filaMiniTablero,columnaMiniTablero);
        
        
        
    }

    public void crearPartida(String jugador1, String jugador2) {
        Jugador jug1 = buscarJugador(jugador1);
        Jugador jug2 = buscarJugador(jugador2);

        partida = new Partida(jug1, jug2);
    }
    
     public void crearPartidaCPU(String jugador1) {
        Jugador jug1 = buscarJugador(jugador1);
        Jugador jug2 = new Computadora();

        partida = new Partida(jug1, jug2);
    }
    
    
    public void crearPartidaPC (String jugador, Computadora cpu){
        Jugador jug = buscarJugador(jugador);
        
        partida = new Partida(jug, cpu);
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jugador buscarJugador(String alias) {
        Jugador jugador = null;
        int i = 0;
        while (i < this.listajugadores.size() && jugador == null) {
            Jugador aux = listajugadores.get(i);
            if (alias.equals(aux.getAlias())) {
                jugador = aux;
            }
            i++;
        }
        return jugador;
    }

    public boolean continuarJugando() {
        return partida.continuarJugando();
    }
    
     public Comparator<Jugador> getCriterioPartidasGanadas() {
    return new Comparator<Jugador>() {
        @Override
        public int compare(Jugador jugador1, Jugador jugador2) {
            return Integer.compare(jugador2.getPartidasGanadas(), jugador1.getPartidasGanadas());
      }
    };
     }

    public boolean estaLleno(int filaMiniTablero, int columnaMiniTablero) {
        return partida.estaLleno(filaMiniTablero,columnaMiniTablero);
    }

    public Jugador ganadorContador() {
        return partida.ganadorContador();
    }

    public boolean jugadaMagica(int fila, int columna) {
        return partida.jugadaMagica(fila,columna);
    }

    public boolean casilleroDisponible(int filaMiniTablero, int columnaMiniTablero, int fila, int columna) {
        return partida.casilleroDisponible(filaMiniTablero,columnaMiniTablero,fila,columna);
    }

    public String solicitarCPU() {
        return partida.solicitarCPU();
    }

}
