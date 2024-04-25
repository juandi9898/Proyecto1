<%-- 
    Document   : index
    Created on : 19/03/2024, 1:34:40 p. m.
    Author     : juand
--%>


<%@page import="java.util.List"%>
<%@page import="Logica.Modelo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="style.css">
    </head>
    <body>
      <!--Comienza Nav-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">

      <a href="#" class="navbar-brand"><span class="text-primary">Tienda</span>Zapatos</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarS"
        aria-controls="navbarS" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarS">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a href="#seccion1" class="nav-link">Inicio</a>
          </li>
          <li class="nav-item">
            <a href="#seccion2" class="nav-link">Modelos-Zapatos</a>
          </li>
          <li class="nav-item">
            <a href="#seccion3" class="nav-link">Pedidos</a>
          </li>
          <li class="nav-item">
            <a href="#seccion4" class="nav-link">Informe</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!--Termina Nav-->
  <section id="seccion1">
    <!--Comienza Carousel-->
    <div id="carouselExample" class="carousel slide">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://luckyfeetshoes.com/cdn/shop/articles/Best_Comfort_Shoes_Brands_for_Foot_Pain_and_Heel_Pain_2023.png?v=1701280869&width=1000"
            class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img
            src="https://luckyfeetshoes.com/cdn/shop/articles/Best_Comfort_Shoes_Brands_for_Foot_Pain_and_Heel_Pain_2023.png?v=1701280869&width=1000"
            class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img
            src="https://luckyfeetshoes.com/cdn/shop/articles/Best_Comfort_Shoes_Brands_for_Foot_Pain_and_Heel_Pain_2023.png?v=1701280869&width=1000"
            class="d-block w-100" alt="...">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <!--Termina Carousel-->
  </section>

  
      <% List<Modelo> modelos = (List<Modelo>) session.getAttribute("modelos"); %>
      
      
      
      <!-- Seccion modelo zapatos -->
      
  <section id="seccion2">
    <br>
    <br>
    <h2>Modelos y Zapatos</h2>
    <div class="formularios">
        <div class="formulario1">
            <h2>Modelos</h2>
            <img src="assets/imagenes/j4-2.jpg" alt="" width="200px" height="200px">
            <!--Aqui toca ver como hacemos para que se cambie esta con las que tenemos-->
            <br>
            <form action="" method="post" ><!--Mirar bien lo del accion, no se que se debe hacer ahí-->
                <label>Modelos</label><br><!--Aqui toca ver como hacemos para que se cambie estos con las que tenemos-->
                <div>
                         <% if (modelos != null && !modelos.isEmpty()) { %>
                           <select id="selectElement" name="modelos">
                                                         <%System.out.println("Llegando datos");%>

                         <% for (Modelo modelo : modelos) { %> 
                                <option value="<%= modelo.darNombreModelo() %>"><%= modelo.darNombreModelo() %></option>
                                <%System.out.println("NombreModelo:  "+modelo.darNombreModelo());%>
                                <%System.out.println("TipoZapato: "+modelo.darTipoZapato());%>
                                <%System.out.println("Imagen: "+modelo.darImagen());%>
                                <% } %>
                       </select>
                   <% } else { %>
                       <select id="modelos" name="modelos">
                           <option value="">No hay modelos disponibles</option>
                       </select>
                   <% } %>
                </div>
                <br>
                
                
                <label>Tipo de Zapatos</label><br>
                <input type="text" id="tipoZapato" name="tipoZapato" readonly>
                <br>
                <a href="agregarModelo.jsp" target="_self"><button type="button">Agregar Modelo</button></a>               
                
            </form>
            

               

          
                   
        </div>
        <div class="formulario2">
            <h2>Zapatos</h2>
            <form action="" method="post">
                <label>Zapatos</label><br>
                <div>
                    <select id="zapatos" name="zapatos">
                        <option value="java">Java</option>
                        <option value="python">Python</option>
                        <option value="javascript">JavaScript</option>
                        <option value="csharp">C#</option>
                        <option value="cpp">C++</option>
                    </select>
                </div>
                <br>
                <label>Marca:</label>
                <input type="text" readonly><br>
                <br>
                <label>Talla:</label>
                <input type="text" readonly><br>
                <br>
                <label>Precio:</label>
                <input type="text" readonly><br>
                <br>
                <label>Color:</label>
                <input type="text" readonly><br>
                <br>
                <label>#Ventas</label>
                <input type="text" readonly><br>
                <br>
                <a href="agregarZapato.jsp" target="_self"><button type="button">Agregar Zapato</button></a>               
                <a href="venderZapato.jsp" target="_self"><button type="button">Vender Zapato</button></a>               
              </form>
        </div>
    </div>
                    




    <p>“¡Bienvenido a nuestra tienda de zapatos en línea! Para realizar un pedido, simplemente elige tu par de zapatos favorito, selecciona tu talla, añade al carrito y confirma tu pedido. Recibirás un correo electrónico de confirmación con los detalles de tu pedido. Si tienes alguna pregunta, estamos aquí para ayudarte. ¡Gracias por elegirnos!”</p>
</section>

  
  <section id="seccion3">
    <h2>Pedidos</h2>
  
    <a href="historial.jsp" target="_self"><button type="button">Historial de Pedidos</button></a>               
    <p>“La sección de ‘Historial de Pedidos’ es donde puedes encontrar todos los detalles de tus compras pasadas. Aquí, cada pedido que has realizado se registra con información detallada, incluyendo la fecha de compra, los productos adquiridos, el precio pagado, y la dirección de envío. También puedes ver el estado de tus pedidos actuales, ya sea que estén en proceso, enviados, o entregados. Esta sección te permite tener un seguimiento claro y ordenado de todas tus transacciones, facilitando la administración de tus compras y devoluciones. ¡Es como tu propio archivo personal de compras!”</p>

  </section>

  <section id="seccion4">
    <h2>Informe</h2>
    <br>
    <p>El zapato mas caro es:______.</p>
    <p>De acuerdo a las Unidades Vendidas podemos decir que _______________</p>

  </section>


  <footer>
    <p>@Todos los Derechos Reservados</p>
  </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"></script>
       
    </body>
</html>
