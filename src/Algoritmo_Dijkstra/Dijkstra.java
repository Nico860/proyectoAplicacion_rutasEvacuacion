/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmo_Dijkstra;

import Clases.Lugar;
import Clases.Plano;
import Clases.Tramo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Dijkstra {
    
    public ResultadoDijkstra calcular(Plano plano, Lugar origen) {
        int n = plano.cantidadLugares();
        double[] distancia = new double[n];
        int[] anterior = new int[n];
        boolean[] visitado = new boolean[n];
        List<String> pasos = new ArrayList<>();

        // 1) Inicializar: todo en infinito, el origen en 0, nadie tiene anterior.
        Arrays.fill(distancia, Double.POSITIVE_INFINITY);
        Arrays.fill(anterior, -1);
        distancia[plano.indiceDe(origen)] = 0;
        pasos.add("Inicio en " + origen.getNombre() + " (distancia 0, el resto ∞)");

        for (int iteracion = 0; iteracion < n; iteracion++) {
            // 2) Elegir el nodo NO visitado con la menor distancia conocida.
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visitado[i] && (u == -1 || distancia[i] < distancia[u])) {
                    u = i;
                }
            }
            // Si el menor es infinito, lo que queda es inalcanzable: se termina.
            if (Double.isInfinite(distancia[u])) {
                pasos.add("Los nodos restantes no son alcanzables.");
                break;
            }
            visitado[u] = true;
            Lugar actual = plano.getLugar(u);
            pasos.add(String.format("Visitar %s (%.1f)", actual.getNombre(), distancia[u]));

            // 3) Relajar: ¿llegar al vecino pasando por u es mas corto?
            for (Tramo tramo : plano.tramosDe(u)) {
                if (!tramo.isHabilitada()) {
                    continue; // una arista cerrada no se usa en el calculo
                }
                int v = plano.indiceDe(tramo.otroExtremo(actual));
                double candidata = distancia[u] + tramo.getDistancia();
                if (!visitado[v] && candidata < distancia[v]) {
                    pasos.add(String.format("   %s: %s -> %.1f (por %s)",
                            plano.getLugar(v).getNombre(),
                            formatear(distancia[v]), candidata, actual.getNombre()));
                    distancia[v] = candidata;
                    anterior[v] = u;
                }
            }
        }

        pasos.add("");
        pasos.add("Distancias finales desde " + origen.getNombre() + ":");
        for (int i = 0; i < n; i++) {
            pasos.add(String.format("   %-24s %s", plano.getLugar(i).getNombre(), formatear(distancia[i])));
        }
        return new ResultadoDijkstra(plano, distancia, anterior, pasos);
    }

    private String formatear(double valor) {
        return Double.isInfinite(valor) ? "∞" : String.format("%.1f", valor);
    }    
}
