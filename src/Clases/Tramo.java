/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Tramo {
    //se definen los metodos
    private Lugar origen;
    private Lugar destino;
    private double distancia;
    private boolean habilitada;
    
    //Se define el constructor

    public Tramo(Lugar origen, Lugar destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.setDistancia(distancia);
        this.habilitada = true;
    }
    
    //metodos
    public String lineaArchivo(){
        return ("TRAMO;"+origen.getIdLugar()+";"+destino.getIdLugar()+";"+distancia);
    }
    
    public boolean tieneLugares(Lugar a, Lugar b){
        return (origen == a && destino == b) || (origen == b && destino == a);
    }
    
    public void habilitar(){
        habilitada = true;
    }
    
    public void bloquear(){
        habilitada = false;
    }
    
    public Lugar otroExtremo(Lugar desde){
        return desde == origen ? desde : origen;
    }
    
    //getters y setters

    public Lugar getOrigen() {
        return origen;
    }

    public Lugar getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        //se valida que la distancia sea mayor que cero
        if(distancia < 1)
            throw new IllegalArgumentException("La distancia no puede ser menor o igual a cero");
        
        this.distancia = distancia;
    }

    public boolean isHabilitada() {
        return habilitada;
    }
    
    //metodos sobreescritos
    @Override
    public String toString() {
        return origen.getNombre() + " – " + destino.getNombre()
                + String.format(" (%.1f m)", distancia)
                + (habilitada ? "" : " [CERRADA]");
    }
}
