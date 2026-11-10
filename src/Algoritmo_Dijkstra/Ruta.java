/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmo_Dijkstra;

import Clases.Lugar;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Ruta implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //se definen los metodos
    private final List<Lugar> lugares;
    private final double distancia;
    
    //se defiene el constructor

    public Ruta(List<Lugar> lugares, double distancia) {
        this.lugares = lugares;
        this.distancia = distancia;
    }
    
    public boolean existe() {
        return !Double.isInfinite(distancia);
    }
    
    //getters

    public List<Lugar> getLugares() {
        return lugares;
    }

    public double getDistancia() {
        return distancia;
    }
    
    @Override
    public String toString() {
        if (!existe()) {
            return "Sin camino";
        }
        StringBuilder sb = new StringBuilder();
        for (Lugar n : lugares) {
            if (sb.length() > 0) {
                sb.append(" → ");
            }
            sb.append(n.getNombre());
        }
        return sb.append(String.format(" (%.1f km)", distancia)).toString();
    }    
}
