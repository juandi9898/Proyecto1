/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author juand
 */
public class Venta {
    
    String email;
    String nombre;
    String cedula;
    
    /**
     *
     * @param email
     * @param nombre1
     * @param cedula1
     */
    
    
    
    public Venta() {
    }

    public Venta(String email, String nombre, String cedula) {
        this.email = email;
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    

    

    
    
    
    
    
    
}
