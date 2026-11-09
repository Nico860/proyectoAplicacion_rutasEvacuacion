/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simulaciones;

import Algoritmo_Dijkstra.Ruta;
import Clases.Lugar;
import Clases.Tramo;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Simulacion implements Reportable{
    //se definen los atributos
    private LocalDateTime fechaYHora;
    private List<Tramo> tramosBloqueados;
    private List<Lugar> salones;
    private List<Lugar> salidas;
    private List<Ruta> rutas;
    private List<Double> metros;
    
    //se construye el constructor

    public Simulacion(LocalDateTime fechaYHora, List<Tramo> tramosBloqueados, List<Lugar> salones, List<Lugar> salidas, List<Ruta> rutas, List<Double> metros) {
        this.fechaYHora = fechaYHora;
        this.tramosBloqueados = tramosBloqueados;
        this.salones = salones;
        this.salidas = salidas;
        this.rutas = rutas;
        this.metros = metros;
    }
    
    //se sobreescriben los metodos
    @Override
    public String generarReporteDetallado(){
        //se inicializa una varriable para almacenar grandes cantidades de texto
        StringBuilder sb = new StringBuilder();
        //se recorre cada lista y se va generando el reporte
        sb.append("Fecha de simulación: ");
        sb.append("Hora en la que se realizo la simulación: ");
        sb.append("---------------------------------------------------------");
        sb.append("Tramos bloqueados durante la simulación: ");
        
        for (Tramo tramo : tramosBloqueados) {
            sb.append(tramo.toString());
        }
        
        sb.append("---------------------------------------------------------");
        sb.append("Ruta de evacuación de cada salón: ");
        for (int i = 0; i < rutas.size(); i++) {
            sb.append("Salón (" + salones.get(i).toString() + "): ");
            sb.append(rutas.get(i).toString());
        }
        
        sb.append("---------------------------------------------------------");
        sb.append("Distancia de evacuación entre cada salón: ");
        for (int i = 0; i < salones.size(); i++) {
            sb.append(salones.get(i).toString() + " -> " + salidas.get(i).toString() + " - distancia" + 
                    String.format(" (%.1f m)", metros.get(i)));
        }
        
        return sb.toString();
    }
    
    //getters

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public List<Tramo> getTramosBloqueados() {
        return tramosBloqueados;
    }

    public List<Lugar> getSalones() {
        return salones;
    }

    public List<Lugar> getSalidas() {
        return salidas;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public List<Double> getMetros() {
        return metros;
    }
    
}
