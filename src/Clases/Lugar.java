/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public abstract class Lugar implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //se dfinen los atributos
    private String idLugar;
    private String nombre;
    
    //se define el constructor

    public Lugar(String idLugar, String nombre) {
        setIdLugar(idLugar);
        setNombre(nombre);
    }
    
    //metodos
    public boolean tieneIdLugar(String IdLugar){
        return (this.idLugar.trim().equalsIgnoreCase(IdLugar));
    }
    
    public boolean esTipoDePunto(String tipo){
        return tipoDePunto().equalsIgnoreCase(tipo); 
    }
    
    public boolean esTipoDeLugar(String tipo){
        return tipoLugar().equalsIgnoreCase(tipo);
    }
    
    public String lineaArchivo(){
        return ("LUGAR;"+idLugar+";"+nombre+";"+tipoLugar());
    }
    
    //metodos abstractos
    public abstract String tipoDePunto();
    public abstract String tipoLugar();
    
    //getter y setters
    private void setIdLugar(String id){
        if(id.trim().isEmpty() || id.toLowerCase().contains(";")){
            throw new IllegalArgumentException("El id no puede estar vacío ni puede tener (;)");
        }
        this.idLugar = id;
    }
    
    public String getIdLugar() {
        return idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        //se valida que no este vacío y que no tenga ;
        if(nombre.trim().isEmpty() || nombre.toLowerCase().contains(";")){
            throw new IllegalArgumentException("El nombre no puede estar vacío o con (;)");
        }
        
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return idLugar + " · " + nombre;
    }
}
