/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

/**
 *
 * @author Lenovo
 */
public class Computadora extends Jugador{
   
   

    public Computadora() {
        super(0,"CPU","CPU");
        
       
    }

     public String obtenerJugadaAleatoria() {
        int fila= (int) (Math.random() * 3); 
        int columna = (int) (Math.random() * 3)+1; 
        fila=fila+65;
        char letra=((char)fila);
        return ""+letra+columna;
    }
    
    
    
}
