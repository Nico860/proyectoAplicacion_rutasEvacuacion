/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author CHAPTOPS TACTIC
 */
public class Salida extends Lugar{
    //se define el constructor
    public Salida(String idLugar, String nombre) {
        super(idLugar, nombre);
    }
    
    //se sobre escriben los metodos

    @Override
    public String tipoDePunto() {
        return ("Fin");
    }

    @Override
    public String tipoLugar() {
        return "SALIDA";
    }
}
