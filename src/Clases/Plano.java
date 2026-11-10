/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Plano {
    //se definen los metodos
    private List<Lugar> lugares = new ArrayList<>();
    private List<Tramo> tramos = new ArrayList<>();
    private Map<String,Integer> indices = new HashMap<>();
    private List<List<Tramo>> adyacencia = new ArrayList<>();
    
    //metodos
    public void agregarLugar(Lugar lugar){
        if (indices.containsKey(lugar.getIdLugar())) {
            throw new IllegalArgumentException("Id repetido: " + lugar.getIdLugar());
        }
        indices.put(lugar.getIdLugar(), lugares.size());
        lugares.add(lugar);
        adyacencia.add(new ArrayList<Tramo>());
    }
    
    public void agregarTramo(String idlugarA, String idLugarB, double distancia){
        Lugar a = buscarLugar(idlugarA);
        Lugar b = buscarLugar(idLugarB);
        validarExtremos(a, b, null);
        
        //el constructor de Tramo valida que la distancia sea mayor que cero
        Tramo tramo = new Tramo(a, b, distancia);
        tramos.add(tramo);
        // Se registra en AMBOS extremos porque el grafo es no dirigido.
        adyacencia.get(indiceDe(a)).add(tramo);
        adyacencia.get(indiceDe(b)).add(tramo);
    }
    
    //devuelve el tramo que une a y b (en cualquier orden) o null si no existe
    public Tramo buscarTramo(Lugar a, Lugar b){
        for (Tramo tramo : tramos) {
            if(tramo.tieneLugares(a, b)) return tramo;
        }
        return null;
    }
    
    public void eliminarTramo(Tramo tramo){
        if(!tramos.remove(tramo)){
            throw new IllegalArgumentException("El tramo no existe en el plano");
        }
        adyacencia.get(indiceDe(tramo.getOrigen())).remove(tramo);
        adyacencia.get(indiceDe(tramo.getDestino())).remove(tramo);
    }
    
    //Reemplaza un tramo por otro con nuevos extremos y/o distancia, conservando si estaba bloqueado.
    //Todo se valida antes de tocar el plano, así que si falla el tramo original queda intacto.
    public Tramo modificarTramo(Tramo tramo, String idLugarA, String idLugarB, double distancia){
        Lugar a = buscarLugar(idLugarA);
        Lugar b = buscarLugar(idLugarB);
        validarExtremos(a, b, tramo);
        
        Tramo nuevo = new Tramo(a, b, distancia);
        if(!tramo.isHabilitada()) nuevo.bloquear();
        
        //se conserva la posición para que la tabla no cambie de orden
        tramos.set(tramos.indexOf(tramo), nuevo);
        adyacencia.get(indiceDe(tramo.getOrigen())).remove(tramo);
        adyacencia.get(indiceDe(tramo.getDestino())).remove(tramo);
        adyacencia.get(indiceDe(a)).add(nuevo);
        adyacencia.get(indiceDe(b)).add(nuevo);
        return nuevo;
    }
    
    //matriz[i][j] = tramo que une los lugares i y j, o null si no hay conexión
    public Tramo[][] matrizAdyacencia(){
        int n = lugares.size();
        Tramo[][] matriz = new Tramo[n][n];
        for (Tramo tramo : tramos) {
            int i = indiceDe(tramo.getOrigen());
            int j = indiceDe(tramo.getDestino());
            matriz[i][j] = tramo;
            matriz[j][i] = tramo; //no dirigido: A–B equivale a B–A
        }
        return matriz;
    }
    
    //validaciones comunes de los extremos de un tramo. 'ignorar' es el tramo que se está modificando
    private void validarExtremos(Lugar a, Lugar b, Tramo ignorar){
        if (a == null || b == null) {
            throw new IllegalArgumentException("Los dos lugares del tramo deben existir en el plano");
        }
        if (a == b) {
            throw new IllegalArgumentException("Un tramo no puede unir un lugar consigo mismo");
        }
        Tramo existente = buscarTramo(a, b);
        if (existente != null && existente != ignorar) {
            throw new IllegalArgumentException("Ya existe un tramo entre " + a.getNombre() + " y " + b.getNombre()
                    + " (A–B equivale a B–A)");
        }
    }
    
    public Lugar buscarLugar(String idLugar){
        Integer i = indices.get(idLugar);
        return i == null ? null : lugares.get(i);
    }
    
    public int indiceDe(Lugar lugar){
        return indices.get(lugar.getIdLugar());
    }
    
    public List<Tramo> tramosDe(int indice){
        return adyacencia.get(indice);
    }
    
    public Lugar getLugar(int indice){
        return lugares.get(indice);
    }
    
    public int cantidadLugares(){
        return lugares.size();
    }
    
    //getters y setters

    public List<Lugar> getLugares() {
        return lugares;
    }

    public List<Tramo> getTramos() {
        return tramos;
    }
    
}
