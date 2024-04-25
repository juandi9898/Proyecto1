
package Logica;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Es la clase que representa la tienda virtual con sus discos. <br>
 * <b>inv: </b> <br>
 * discos != null <br>
 * No hay dos discos con el mismo nombre
 * 
 */
public class TiendaZapatos implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Nombre del archivo de registro de errores del programa
     */
    private static final String LOG_FILE = "./data/error.log";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La lista de discos de la discotienda
     */
    private ArrayList modelos;

    /**
     * Es el nombre del archivo de donde se cargan y salvan los discos
     */
    private String archivoTiendaZapatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye una nueva discotienda. <br>
     * Si el archivo indicado no existe, entonces la discotienda se crea vac�a y su estado se guardar� en el archivo indicado.<br>
     * Si el archivo existe, entonces de �l se saca la informaci�n de los discos y canciones.
     * @param nombreArchivoDiscotienda es el nombre del archivo que contiene los datos de la discotienda - nombreArchivoDiscotienda != null
     * @throws PersistenciaException Se lanza esta excepci�n si se encuentran problemas cargando los datos del archivo
     */
    
    
    public TiendaZapatos( String nombreArchivoDiscotienda ) throws PersistenciaException
    {
        archivoTiendaZapatos = nombreArchivoDiscotienda;
        File archivo = new File( archivoTiendaZapatos );
        if( archivo.exists( ) )
        {
            // El archivo existe: se debe recuperar de all� el estado del modelo del mundo
            try
            {
                ObjectInputStream ois = new ObjectInputStream( new FileInputStream( archivo ) );
                modelos = ( ArrayList )ois.readObject( );
                ois.close( );
            }
            catch( Exception e )
            {
                // Se atrapan en este bloque todos los tipos de excepci�n
                registrarError( e );
                throw new PersistenciaException( "Error fatal: imposible restaurar el estado del programa (" + e.getMessage( ) + ")" );
            }
        }
        else
        {
            // El archivo no existe: es la primera vez que se ejecuta el programa
            modelos = new ArrayList( );
        }
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna un disco de la discotienda dado su nombre.
     * @param nombreModelo el nombre del disco a buscar - nombreDisco != null
     * @return El Disco cuyo nombre es igual al nombre dado. Si no se encontr� retorna null.
     */
    public Modelo darModelo( String nombreModelo )
    {
        for( int i = 0; i < modelos.size( ); i++ )
        {
            Modelo d = ( Modelo )modelos.get( i );
            if( d.equals( nombreModelo ) )
                return d;
        }
        return null;
    }

    /**
     * Retorna un disco de la discotienda dado un nombre, un artista y una canci�n
     * @param nombreModelo El nombre del disco donde deber�a estar la canci�n - nombreDisco != null
     * @param tipoZapato El nombre del artista del disco - nombreArtista != null
     * @param nombreZapato El nombre de la canci�n buscada - nombreCancion != null
     * @return Retorna el disco en el que se encuentra la canci�n buscada. Si no se encuentra retorna null.
     */
    private Modelo darModelo( String nombreModelo, String tipoZapato, String nombreZapato )
    {
        Modelo modeloBuscado = darModelo( nombreModelo );
        if( modeloBuscado != null && modeloBuscado.darTipoZapato( ).equalsIgnoreCase( tipoZapato ) )
            return ( modeloBuscado.darZapato( nombreZapato ) != null ) ? modeloBuscado : null;
        else
            return null;
    }

    /**
     * Agrega un nuevo disco a la discotienda <br>
     * @param nombreModeloD el nombre del disco - nombreDiscoC != null
     * @param tipoZapato el artista del nuevo disco - artistaD != null
     * @param generoD el genero del nuevo disco - generoD != null
     * @param imagenD el nombre del archivo imagen del disco que debe estar en ./data/imagenes - imagenD != null
     * @throws ElementoExisteException Esta excepci�n se lanza si ya existe un disco con el mismo nombre
     */
    public void agregarModelo( String nombreModeloD, String tipoZapato, String imagenD) throws ElementoExisteException
    {
        if( darModelo( nombreModeloD ) != null )
            throw new ElementoExisteException( "El modelo " + nombreModeloD + " ya existe en tiendaZapatos" );

        modelos.add( new Modelo( nombreModeloD, tipoZapato,imagenD ) );
        verificarInvariante( );
    }

    /**
     * Agrega una nueva canci�n al disco
     * @param nombreModelo el nombre del disco para adicionar la canci�n - hay un disco con ese nombre en la discotienda
     * @param nombreZ el nombre de la canci�n a crear - nombreC != null, nombreC != ""
     * @param minutosC el n�mero de minutos de duraci�n de la canci�n - minutosC >= 0
     * @param segundosC el n�mero de segundos de duraci�n de la canci�n - 0 <= segundosC < 60, minutosC + segundosC > 0
     * @param precioC el precio de la canci�n - precioC > 0
     * @param tamanoC el tama�o en Mb de la canci�n - tamanoC > 0
     * @param calidadC la calidad de la canci�n en Kbps - calidadC > 0
     * @throws ElementoExisteException Esta excepci�n se lanza si el ya existe otra zapato en el disco con el mismo nombre
     */
    public void agregarZapatoAModelo( String nombreModeloZ, String nombreZ,String marcaZ,int tallaZ,double precioZ,String colorZ) throws ElementoExisteException
    {
        Modelo d = darModelo( nombreModeloZ );
        d.agregarZapato( new Zapato( nombreZ,marcaZ,tallaZ,precioZ,colorZ, 0 ) );
        verificarInvariante( );
    }

    /**
     * Registra la venta de una canci�n y genera la factura en un archivo nuevo.
     * @param modelo el disco al cual pertenece la canci�n que se va a vender - disco != null
     * @param zapato la canci�n de la cual se va a vender una unidad - cancion != null
     * @param email el email de la persona a la cual se le vendi� la canci�n - email != null, email es un email v�lido (usuario@dominio.ext)
     * @param rutaFactura el directorio donde debe generarse la factura - rutaFactura != null
     * @return Retorna el nombre del archivo en el que se gener� la factura
     * @throws IOException Se genera esta excepci�n si hay problemas salvando el archivo con la factura
     */
    public String venderZapato( Modelo modelo, Zapato zapato, String email, String rutaFactura ) throws IOException
    {
        // Aumenta el n�mero de unidades vendidas de la canci�n
        zapato.vender( );

        // Genera el nombre para la factura
        int posArroba1 = email.indexOf( "@" );
        String login = email.substring( 0, posArroba1 );
        String strTiempo = Long.toString( System.currentTimeMillis( ) );
        String nombreArchivo = login + "_" + strTiempo + ".fac";

        // Guarda el archivo de la factura
        File directorioFacturas = new File( rutaFactura );
        if( !directorioFacturas.exists( ) )
            directorioFacturas.mkdirs( );
        File archivoFactura = new File( directorioFacturas, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoFactura );

        Date fecha = new Date( );
        out.println( "TiendaZapatos - FACTURA" );
        out.println( "Fecha:            " + fecha.toString( ) );
        out.println( "Email:            " + email );

        out.println( "Zapato:          " + zapato.darNombre( ) + " - " + modelo.darTipoZapato( ) );
        out.println( "                  " + modelo.darNombreModelo( ) );
        out.println( "No de Zapatos:  1" );
        DecimalFormat df = new DecimalFormat( "$0.00" );
        out.println( "Valor Total:      " + df.format( zapato.darPrecio( ) ) );
        out.close( );

        return nombreArchivo;
    }

    /**
     * Retorna un vector con los nombres de los discos
     * @return Vector con los nombres de los discos
     */
    public ArrayList darModelos( )
    {
        ArrayList nombresDiscos = new ArrayList( );
        for( int i = 0; i < modelos.size( ); i++ )
        {
            Modelo d = ( Modelo )modelos.get( i );
            nombresDiscos.add( d.darNombreModelo( ) );
        }
        return nombresDiscos;
    }

    /**
     * Actualiza la informaci�n sobre las canciones vendidas a partir de la informaci�n sobre un pedido y genera una factura.<br>
     * El archivo debe tener una l�nea en la cual se encuentra el email de la persona que hizo el pedido y luego debe haber una l�nea por cada canci�n solicitada. <br>
     * Cada l�nea tiene el siguiente formato: <nombre disco>#<nombre artista>#<nombre canci�n>
     * @param archivoPedido el archivo que tiene la informaci�n del pedido - archivoPedido != null
     * @param rutaFactura el directorio donde debe generarse la factura - rutaFactura != null
     * @return El archivo en el que se guard� la factura
     * @throws FileNotFoundException Se lanza esta excepci�n si el archivo del pedido no existe
     * @throws IOException Se lanza esta excepci�n si hay problemas escribiendo el archivo de la factura
     * @throws ArchivoVentaException Se lanza esta excepci�n si el archivo no cumple con el formato esperado
     */
    public String venderListaZapatos( File archivoPedido, String rutaFactura ) throws FileNotFoundException, IOException, ArchivoVentaException
    {
        // Abre el archivo con el pedido. Si no existe, el constructor del flujo lanza la excepci�n FileNotFoundException
        BufferedReader lector = new BufferedReader( new FileReader( archivoPedido ) );

        String email = null;
        try
        {
            // Lee la primera l�nea del archivo (la direcci�n electr�nica) y le suprime los posibles espacios
            email = lector.readLine( );
        }
        catch( IOException e )
        {
            // Hubo un error tratando de leer la primera l�nea del archivo
            throw new ArchivoVentaException( e.getMessage( ), 0 );
        }

        // Hace las verificaciones iniciales.
        if( email == null )
            throw new ArchivoVentaException( "El archivo est� vac�o", 0 );
        if( !validarEmail( email ) )
            throw new ArchivoVentaException( "El email indicado no es v�lido", 0 );

        String pedido = null;
        try
        {
            // Intenta leer del archivo la primera canci�n que se quiere vender
            pedido = lector.readLine( );
        }
        catch( IOException e )
        {
            // Hubo un error tratando de leer la primera canci�n del pedido
            throw new ArchivoVentaException( e.getMessage( ), 0 );
        }

        if( pedido == null )
            throw new ArchivoVentaException( "Debe haber por lo menos un zapato en el pedido", 0 );

        // Inicializa las estructuras de datos necesarias para generar luego la factura
        ArrayList modelosFactura = new ArrayList( );
        ArrayList zapatosFactura = new ArrayList( );
        ArrayList zapatosNoEncontrados = new ArrayList( );
        int cancionesVendidas = 0;

        // Utiliza el patr�n de recorrido de archivos secuenciales
        while( pedido != null )
        {
            // Separa los tres elementos del pedido (disco, artista y canci�n) verificando que el formato pedido se cumpla
            int p1 = pedido.indexOf( "#" );
            if( p1 != -1 )
            {
                // Encontr� el primer separador
                String resto1 = pedido.substring( p1 + 1 );
                int p2 = resto1.indexOf( "#" );
                if( p2 != -1 )
                {
                    // Encontr� el segundo separador
                    String nombreModelo = pedido.substring( 0, p1 );
                    String nombretipoZapato = resto1.substring( 0, p2 );
                    String nombreZapato = resto1.substring( p2 + 1 );

                    Modelo modeloBuscado = TiendaZapatos.this.darModelo( nombreModelo, nombretipoZapato, nombreZapato );
                    if( modeloBuscado != null )
                    {
                        Zapato zapatoPedido = modeloBuscado.darZapato( nombreZapato );
                        zapatoPedido.vender( );
                        modelosFactura.add( modeloBuscado );
                        zapatosFactura.add( zapatoPedido );
                        cancionesVendidas++;
                    }
                    else
                        // La canci�n no existe en la discotienda
                        zapatosNoEncontrados.add( pedido );
                }
                else
                    // El formato es inv�lido: no aparece el primer separador
                    zapatosNoEncontrados.add( pedido );
            }
            else
                // El formato es inv�lido: no aparece el segundo separador
                zapatosNoEncontrados.add( pedido );

            try
            {
                // Lee la siguiente l�nea del archivo
                pedido = lector.readLine( );
            }
            catch( IOException e )
            {
                // Hubo un error tratando de leer la siguiente canci�n del pedido
                generarFactura( modelosFactura, zapatosFactura, zapatosNoEncontrados, email, rutaFactura );
                throw new ArchivoVentaException( e.getMessage( ), cancionesVendidas );
            }
        }

        // Cierra el flujo de entrada
        lector.close( );

        // Genera la factura
        return generarFactura( modelosFactura, zapatosFactura, zapatosNoEncontrados, email, rutaFactura );
    }

    /**
     * Genera la factura de la venta de un conjunto de discos, en un archivo nuevo.
     * @param modelos los discos a los que pertenecen las canciones que se van a vender - discos != null
     * @param zapatos las canciones que se van a vender - canciones != null, por cada cancion, en el par�metro 'discos' se encuentra el disco correspondiente en la misma
     *        posici�n
     * @param noEncontrados vector con las l�neas del pedido que no pudieron ser procesadas porque la canci�n no existe
     * @param email el email de la persona a la cual se le vendieron las canciones - email != null, email es un email v�lido (usuario@dominio.ext)
     * @param rutaFactura el directorio donde debe generarse la factura - rutaFactura != null
     * @return Retorna el nombre del archivo en el que se gener� la factura
     * @throws IOException Se genera esta excepci�n si hay problemas salvando el archivo
     */
    private String generarFactura( ArrayList modelos, ArrayList zapatos, ArrayList noEncontrados, String email, String rutaFactura ) throws IOException
    {
        // Genera el nombre para la factura
        int posArroba1 = email.indexOf( "@" );
        String login = email.substring( 0, posArroba1 );
        String strTiempo = Long.toString( System.currentTimeMillis( ) );
        String nombreArchivo = login + "_" + strTiempo + ".fac";

        // Guarda el archivo de la factura
        File directorioFacturas = new File( rutaFactura );
        if( !directorioFacturas.exists( ) )
            directorioFacturas.mkdirs( );

        File archivoFactura = new File( directorioFacturas, nombreArchivo );
        PrintWriter out = new PrintWriter( archivoFactura );
        Date fecha = new Date( );
        out.println( "tiendaZapatos - FACTURA" );
        out.println( "Fecha:            " + fecha.toString( ) );
        out.println( "Email:            " + email );

        double valorTotal = 0;
        for( int i = 0; i < modelos.size( ); i++ )
        {
            Modelo modelo = ( Modelo )modelos.get( i );
            Zapato zapato = ( Zapato )zapatos.get( i );
            out.println( "Zapato:          " + zapato.darNombre( ) + " - " + modelo.darTipoZapato( ) );
            out.println( "                  " + modelo.darNombreModelo( ) );
            valorTotal += zapato.darPrecio( );
        }
        DecimalFormat df = new DecimalFormat( "$0.00" );
        out.println( "No de Zapatos:  " + zapatos.size( ) );
        out.println( "Valor Total:      " + df.format( valorTotal ) );

        // Incluye en la factura las canciones que no se encontraron
        if( noEncontrados.size( ) > 0 )
        {
            out.println( "\nZapatos no encontrados: " );
            for( int i = 0; i < noEncontrados.size( ); i++ )
            {
                out.println( noEncontrados.get( i ) );
            }
        }
        out.close( );

        return nombreArchivo;
    }

    /**
     * Indica si una direcci�n de correo cumple con el formato esperado.<br>
     * El formato esperado es <login>@<dominio>.<br>
     * El dominio tiene que estar compuesto de por lo menos dos partes separadas por un punto: <parte1>.<parte2>
     * @param email la direcci�n de email que se quiere verificar - email != null
     * @return Retorna true si el email cumple con el formato especificado
     */
    public boolean validarEmail( String email )
    {
        boolean resultado = true;
        int posArroba1 = email.indexOf( "@" );
        if( posArroba1 == -1 )
        {
            resultado = false;
        }
        else
        {
            String dominio = email.substring( posArroba1 + 1 );
            resultado = dominio.indexOf( "." ) != -1 && ! ( dominio.substring( dominio.indexOf( "." ) + 1 ).equals( "" ) );
        }
        return resultado;
    }

    // -----------------------------------------------------------------
    // Persistencia
    // -----------------------------------------------------------------

    /**
     * Salva la discotienda en un archivo binario
     * @throws PersistenciaException Se lanza esta excepci�n si hay problemas guardando los archivos
     */
    public void salvarTiendaZapatos( ) throws PersistenciaException
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( archivoTiendaZapatos ) );
            oos.writeObject( modelos );
            oos.close( );
        }
        catch( IOException e )
        {
            registrarError( e );
            throw new PersistenciaException( "Error al salvar: " + e.getMessage( ) );
        }
    }

    /**
     * Registra en el archivo de log del programa toda la informaci�n referente a una excepci�n, ocurrida durante el proceso de persistencia
     * @param excepcion es la excepci�n que contiene la informaci�n del error
     */
    public void registrarError( Exception excepcion )
    {
        try
        {
            FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "TiendaZapatos.java :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( );
        }
        catch( IOException e )
        {
            excepcion.printStackTrace( );
            e.printStackTrace( );
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Verifica el invariante de la clase: <br>
     * discos != null <br>
     * No hay dos discos con el mismo nombre
     */
    private void verificarInvariante( )
    {
        assert modelos != null : "La lista de modelos es null";
        assert !buscarDiscosConElMismoNombre( ) : "Hay dos modelos con el mismo nombre";
    }

    /**
     * Este m�todo sirve para revisar si hay dos discos con el mismo nombre dentro de la tienda.
     * @return Retorna true si hay un disco que aparece repetido dentro de la lista de discos. Retorna false en caso contrario.
     */
    private boolean buscarDiscosConElMismoNombre( )
    {
        for( int i = 0; i < modelos.size( ); i++ )
        {
            Modelo d1 = ( Modelo )modelos.get( i );
            for( int j = i + 1; j < modelos.size( ); j++ )
            {
                Modelo d2 = ( Modelo )modelos.get( j );
                if( d1.equals( d2.darNombreModelo( ) ) )
                    return true;
            }
        }
        return false;
    }

    
    
   /*public Artista darArtistaConCancionExtensa() {
		
		Artista artistaEncontrado=null;
		Cancion cancionActual;
		boolean encontrado=false;
		for(Artista artistaActual=primerArtista;artistaActual!=null && encontrado==false;artistaActual=artistaActual.darSiguiente()) {

			for( cancionActual=artistaActual.darPrimeraCancion();cancionActual !=null &&encontrado==false;cancionActual.darSiguiente()) {}
				
			if(cancionActual.darLetra().length()>500) {
					artistaActual=artistaEncontrado;
				}
		}
	
		return artistaEncontrado;
	}*/


//Requerimiento Funcional 7
//Recorrido Doble
public String InformeUnidadesVendidas(){
     int unidadesVendidasTotal = 0;
    ArrayList<Modelo> modelos1=modelos;  
    for (Modelo modelo : modelos1) {
        for (Zapato zapato : modelo.darZapatos()) {
            unidadesVendidasTotal += zapato.darUnidadesVendidas();
        }
    }

    if (unidadesVendidasTotal <= 30) {
        return "Regular";
    } else {
        return "Excelente";
    }
}


//Requerimiento Funcional 6

//Recorrido Simple
public String ZapatoCaro(){
    String msj=null;
    double precioMaximo = 0;
    Zapato zapatoMasCaro = null;
    
    
    
    for(int i=0; i<modelos.size(); i++){
        Modelo m = (Modelo) modelos.get(i);
        Zapato zCaroActual = m.zMasCaroModelo();
        double precioCaroActual = zCaroActual.darPrecio();
        
        if(precioCaroActual > precioMaximo){
            precioMaximo = precioCaroActual;
            zapatoMasCaro = zCaroActual;
        }
    }
    
    if(zapatoMasCaro != null){
        msj = "El zapato más caro es: " + zapatoMasCaro.darNombre() + " - Precio: " + precioMaximo;
    } else {
        msj = "No hay zapatos en la tienda.";
    }
    
    return msj;
}
  
    
    
    
    
    /*nmetodo para generar reporte para discos mas caros
     * @param:ExceptionDiscosCarosException -Controlar error de  generar reporte de discos caros
     *FileNotFoundException- Cuando un error se lanza al abrir el archivo
     */
    
  
    

	
    
    
    
    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Es el punto de extensi�n 1
     * @return respuesta 1
     */
    public String metodo1( )
    {
        return "respuesta 1";
    }

    /**
     * Es el punto de extensi�n 2
     * @return respuesta 2
     */
    public String metodo2( )
    {
    	return "Respuesta 2";
    }

    /**
     * Es el punto de extensi�n 3
     * @return respuesta 3
     */
    public String metodo3( )
    {
     return "Respuesta3";
    }

    /**
     * Es el punto de extensi�n 4
     * @return respuesta 4
     */
    public String metodo4( )
    {
        return "respuesta 4";
    }

    /**
     * Es el punto de extensi�n 5
     * @return respuesta 5
     */
    public String metodo5( )
    {
        return "respuesta 5";
    }

    /**
     * Es el punto de extensi�n 6
     * @return respuesta 6
     */
    public String metodo6( )
    {
        return "respuesta 6";
    }
}
