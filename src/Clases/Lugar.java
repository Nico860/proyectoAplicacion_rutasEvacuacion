/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public abstract class Lugar {
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
    
    //metodos abstractos
    public abstract String tipoDePunto();
    
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
