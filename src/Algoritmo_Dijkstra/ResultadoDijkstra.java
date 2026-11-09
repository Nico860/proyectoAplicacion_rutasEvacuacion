/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmo_Dijkstra;

import Clases.Lugar;
import Clases.Plano;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class ResultadoDijkstra {
    //se definen los metodos
    private final Plano plano;
    private final double[] distancia;
    private final int[] anterior;
    private final List<String> pasos;
    
    //se define el constructor

    public ResultadoDijkstra(Plano plano, double[] distancia, int[] anterior, List<String> pasos) {
        this.plano = plano;
        this.distancia = distancia;
        this.anterior = anterior;
        this.pasos = pasos;
    }
    
    //metodos
    public double distanciaHacia(Lugar destino){
        return distancia[plano.indiceDe(destino)];
    }
    
    public Ruta rutaHacia(Lugar destino) {
        int i = plano.indiceDe(destino);
        if (Double.isInfinite(distancia[i])) {
            return new Ruta(new ArrayList<Lugar>(), Double.POSITIVE_INFINITY);
        }
        List<Lugar> camino = new ArrayList<>();
        while (i != -1) {
            camino.add(plano.getLugar(i));
            i = anterior[i];
        }
        Collections.reverse(camino); 
        return new Ruta(camino, distanciaHacia(destino));
    }
    
    public String getPasosComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (String p : pasos) {
            sb.append(p).append('\n');
        }
        return sb.toString();
    }    
    
}
