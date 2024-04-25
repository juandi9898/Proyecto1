/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: Cancion.java,v 1.9 2006/08/10 20:04:47 da-romer Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n8_discotienda 
 * Autor: Nicol�s L�pez - 06/12/2005 
 * Autor: Jorge Villalobos - 29/07/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package Logica;

import java.io.Serializable;

/**
 * Es la clase que representa a una canci�n en MP3 que hace parte de un disco. <br>
 * <b>inv: </b> <br>
 * nombre != null, nombre != "" <br>
 * minutos >= 0 <br>
 * 0 <= segundos < 60 <br>
 * minutos + segundos > 0 <br>
 * tamano > 0 <br>
 * calidad > 0 <br>
 * precio > 0 <br>
 * unidadesVendidas >= 0
 */
public class Zapato implements Serializable
{
      private static final long serialVersionUID = 200L;

	  
	    private String nombre;
	    
	    private String marca;
	    private int talla;
	   
	    private double precio;
	    
	    private String color;
	    
	    private int unidadesVendidas;

	   
	    
	    public Zapato( String nombreZ,String marcaZ, int tallaZ,double precioZ,String colorZ, int cantidad )
	    {
	        nombre = nombreZ;
	        marca=marcaZ;
	        talla=tallaZ;
	        precio = precioZ;
	        color=colorZ;        
	        unidadesVendidas = cantidad;

	       
	    }

	 
	    public String darNombre( )
	    {
	        return nombre;
	    }
	    
	    public String darMarca() {
	    	return marca;
	    }
	    public int darTalla() {
	    	return talla;
	    }
	   
	    public double darPrecio( )
	    {
	        return precio;
	    }
	    public String darColor() {
	    	return color;
	    }

	    
	  
	    public int darUnidadesVendidas( )
	    {
	        return unidadesVendidas;
	    }

	   
	    public void vender( )
	    {
	        unidadesVendidas++;
	    }

	  
	    public boolean equals( String nombreZapato )
	    {
	        return nombre.equalsIgnoreCase( nombreZapato );
	    }
    private void verificarInvariante( )
    {
        assert nombre != null && !nombre.equals( "" ) : "El nombre es inv�lido";
        assert precio > 0 : "El precio debe ser un valor positivo";
        assert unidadesVendidas >= 0 : "El n�mero de unidades vendidas debe ser un entero positivo";
    }
}
