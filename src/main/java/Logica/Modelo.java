

package Logica;

import java.io.*;
import java.util.*;

/**
 * Es la clase que representa un disco. <br>
 * <b>inv: </b> <br>
 * canciones != null <br>
 * nombreDisco != null && nombreDisco != "" <br>
 * artista != null && artista != "" <br>
 * genero != null && genero != "" <br>
 * imagen != null && imagen != "" <br>
 * No hay dos canciones con el mismo nombre <br>
 * El precio del disco es igual a la suma de los precios de todas las canciones que incluye
    */
        public class Modelo implements Serializable
        {
            // -----------------------------------------------------------------
            // Constantes
            // -----------------------------------------------------------------

            /**
             * Indicador de versi�n para la serializaci�n
             */
            private static final long serialVersionUID = 100L;

            // -----------------------------------------------------------------
            // Atributos
            // -----------------------------------------------------------------

            /**
             * Es el vector con las canciones del disco
             */
            private ArrayList zapatos;

            /**
             * Es el t�tulo del disco
             */
            private String nombreModelo;

            /**
             * Es el nombre del artista del disco
             */
            private String tipoZapato;



            private String imagen;

            /**
             * Precio total del disco
             */

            // -----------------------------------------------------------------
            // Constructores
            // -----------------------------------------------------------------

            /**
             * Construye un nuevo Disco con los datos suministrados y sin canciones
             * @param nombreModeloD es el nombreDisco del disco - nombreDiscoD != null, nombreDiscoD != ""
             * @param tipoZapatoD es el nombre del artista del disco - artistaD != null, artistaD != ""
             * @param imagenD es la imagen del disco - imagenD != null, imagenD != ""
             */
            public Modelo( String nombreModeloD, String tipoZapatoD, String imagenD)
            {
                zapatos = new ArrayList( );
                nombreModelo = nombreModeloD;
                tipoZapato = tipoZapatoD;
                imagen = imagenD;

                verificarInvariante( );
            }

            // -----------------------------------------------------------------
            // M�todos
            // -----------------------------------------------------------------

            /**
             * Retorna una canci�n del disco dado su nombre.
             * @param nombreZ el nombre de la canci�n a buscar - nombreC != null
             * @return la canci�n cuyo nombre es igual al nombre dado. Si no se encontr� retorna null.
             */
            public Zapato darZapato( String nombreZ )
            {
                for( int i = 0; i < zapatos.size( ); i++ )
                {
                    Zapato c = ( Zapato )zapatos.get( i );
                    if( c.equals( nombreZ ) )
                        return c;
                }
                return null;
            }

            /**
             * Agrega una canci�n al disco. <br>
             * <b>post: </b> La canci�n c ha sido agregada al disco
             * @param z la nueva canci�n que se va a agregar al disco
             * @throws ElementoExisteException si ya exist�a una canci�n con el mismo nombre
             */
            public void agregarZapato( Zapato z ) throws ElementoExisteException
            {
                if( darZapato( z.darNombre( ) ) != null )
                    throw new ElementoExisteException( "La canci�n " + z.darNombre( ) + " ya existe en el disco" );

                zapatos.add( z );

                verificarInvariante( );
            }

            /**
             * Retorna el artista del disco
             * @return Artista del disco
             */
            public String darTipoZapato( )
            {
                return tipoZapato;
            }

            /**
             * Retorna un vector con los nombres de las canciones del disco
             * @return Vector con los nombres de las canciones
             */
            public ArrayList darNombresZapatos( )
            {
                ArrayList nombresCanciones = new ArrayList( );
                for( int i = 0; i < zapatos.size( ); i++ )
                {
                    Zapato c = ( Zapato )zapatos.get( i );
                    nombresCanciones.add( c.darNombre( ) );
                }
                return nombresCanciones;
            }

            /**
             * Retorna el g�nero del disco
             * @return G�nero del disco
             */


            /**
             * Retorna el nombre del disco
             * @return Nombre del disco
             */
            public String darNombreModelo( )
            {
                return nombreModelo;
            }

            /**
             * Retorna el nombre del archivo que contiene la imagen del disco
             * @return Nombre del archivo que contiene la imagen del disco
             */
            public String darImagen( )
            {
                return imagen;
            }

            /**
             * Retorna el precio total del disco
             * @return Precio total del disco
             */


            /**
             * Indica si el disco tiene el nombre que llega como par�metro
             * @param nombre es el nombre de un disco - nombre != null
             * @return True si el disco tiene el nombre que llega como par�metro
             */
            public boolean equals( String nombre )
            {
                return nombreModelo.equalsIgnoreCase( nombre );
            }

            // -----------------------------------------------------------------
            // Invariante
            // -----------------------------------------------------------------

            /**
             * Verifica el invariante de la clase: <br>
             * canciones != null <br>
             * nombreDisco != null && nombreDisco != "" <br>
             * artista != null && artista != "" <br>
             * genero != null && genero != "" <br>
             * imagen != null && imagen != "" <br>
             * No hay dos canciones con el mismo nombre <br>
             * El precio del disco es igual a la suma de los precios de todas las canciones que incluye
             */
            private void verificarInvariante( )
            {
                assert zapatos != null : "La lista de canciones es nula";
                assert nombreModelo != null && !nombreModelo.equals( "" ) : "El nombre del disco es inv�lido";
                assert tipoZapato != null && !tipoZapato.equals( "" ) : "El nombre del artista es inv�lido";
                assert imagen != null && !imagen.equals( "" ) : "El nombre del archivo con la imagen es inv�lido";

                assert !buscarZapatosConElMismoNombre( ) : "Hay dos canciones con el mismo nombre";
            }

            /**
             * Recalcula el precio de un disco sumando los precios de las canciones que contiene
             * @return Precio total del disco
             */


            /**
             * Este m�todo sirve para revisar si hay canciones repetidas dentro del disco.
             * @return Retorna true si hay una canci�n que aparece repetida dentro de la lista de canciones. Retorna false en caso contrario.
             */
            private boolean buscarZapatosConElMismoNombre( )
            {
                for( int i = 0; i < zapatos.size( ); i++ )
                {
                    Zapato c1 = ( Zapato )zapatos.get( i );
                    for( int j = i + 1; j < zapatos.size( ); j++ )
                    {
                        Zapato c2 = ( Zapato )zapatos.get( j );
                        if( c1.equals( c2.darNombre( ) ) )
                            return true;
                    }
                }
                return false;
            }
            
            
            public ArrayList<Zapato> darZapatos(){
                return zapatos;
            }
            
            
            
           

            //Requerimiento 6, junto con el de TiendaZapatos

              
            public Zapato zMasCaroModelo(){
               Zapato zCaro = null;
               double precioCaro = 0; 
               
               
                for(int i = 0; i < zapatos.size(); i++){
                    Zapato c = (Zapato) zapatos.get(i);
                    double precioActual = c.darPrecio();

                    if(precioActual > precioCaro){
                        precioCaro = precioActual;
                        zCaro = c;
                    }
                }

                return zCaro;

            }

        }
