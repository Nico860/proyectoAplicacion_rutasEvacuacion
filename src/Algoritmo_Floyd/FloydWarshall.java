/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmo_Floyd;

import Clases.Lugar;
import Clases.Plano;
import Clases.Tramo;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class FloydWarshall {
    
    public double[][] calcular(Plano plano) {
        int n = plano.cantidadLugares();
        double[][] dist = new double[n][n];

        // Inicializar la matriz de distancias con infinito en todo, 
        // y 0.0 en la diagonal principal
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
            dist[i][i] = 0.0;
        }

        //Rellenar con los pesos iniciales de los tramos directos existentes y habilitados.
        for (int u = 0; u < n; u++) {
            Lugar origen = plano.getLugar(u); 
            
            List<Tramo> tramos = plano.tramosDe(u);
            if (tramos != null) {
                for (Tramo tramo : tramos) {
                    // Si el tramo está bloqueado, se ignora en el cálculo
                    if (!tramo.isHabilitada()) {
                        continue; 
                    }
                    
                    Lugar destino = tramo.otroExtremo(origen);
                    int v = plano.indiceDe(destino);
                    double peso = tramo.getDistancia();
                    
                    // Si hay múltiples tramos o para asegurar el menor peso directo
                    if (v != -1 && peso < dist[u][v]) {
                        dist[u][v] = peso;
                    }
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        // k es el nodo intermedio evaluado
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Evitamos desbordamientos o sumar con Infinity
                    if (dist[i][k] != Double.POSITIVE_INFINITY && dist[k][j] != Double.POSITIVE_INFINITY) {
                        double nuevaDistancia = dist[i][k] + dist[k][j];
                        if (nuevaDistancia < dist[i][j]) {
                            dist[i][j] = nuevaDistancia;
                        }
                    }
                }
            }
        }

        return dist;
    }
}
