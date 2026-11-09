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
        if (a == null || b == null) {
            throw new IllegalArgumentException("Lugar inexistente: " + idlugarA + " o " + idLugarB);
        }
        
        //se valida que aun no exista un tramo con esos lugares
        for (Tramo tramo : tramos) {
            if(tramo.tieneLugares(a, b)){
                throw new IllegalArgumentException("Ya existe un tramo con esos lugares, registre un nuevo tramo");
            }
        }
        
        Tramo tramo = new Tramo(a, b, distancia);
        tramos.add(tramo);
        // Se registra en AMBOS extremos porque el grafo es no dirigido.
        adyacencia.get(indiceDe(a)).add(tramo);
        adyacencia.get(indiceDe(b)).add(tramo);
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
