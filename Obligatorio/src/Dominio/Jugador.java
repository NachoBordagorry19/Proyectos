/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author Lenovo
 */
public class Jugador {
    private int edad;
    private String nombre;
    private String alias;
    private int partidasGanadas;

    public Jugador(int edad, String nombre, String alias) {
        this.edad = edad;
        this.nombre = nombre;
        this.alias = alias;
        this.partidasGanadas = 0;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    @Override
    public String toString() {
        return "Jugador{" + "edad=" + edad + ", nombre=" + nombre + ", alias=" + alias + ", partidasGanadas=" + partidasGanadas + '}';
    }
    
   
}
