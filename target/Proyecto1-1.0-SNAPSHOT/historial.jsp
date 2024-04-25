x<%-- 
    Document   : historial
    Created on : 1/04/2024, 7:46:51 p. m.
    Author     : juand
--%>
<%@page import="java.util.List"%>
<%@page import="Logica.Venta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <title>Document</title>

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
    
          <a href="index.html" class="navbar-brand"><span class="text-primary">Tienda</span>Zapatos</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarS"
            aria-controls="navbarS" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarS">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a href="index.html" class="nav-link">Inicio</a>
              </li>
          
            </ul>
          </div>
        </div>
      </nav>

    <div class="contenido4">
        <h1><b>Facturas de Compra</b></h1>
        
        <%          
          List<Venta> listaVentas = (List) request.getSession().getAttribute("listaVentas");
          
          if (listaVentas != null && !listaVentas.isEmpty()) {
              int conteo = 1;
              for (Venta usu : listaVentas) {
        %>
        <p><b>Venta N° <%= conteo %></b></p>
        <p>Correo: <%= usu.getEmail() %></p>
        <p>Nombres: <%= usu.getNombre() %></p>
        <p>Cedula: <%= usu.getCedula() %></p>
        <p>--------------------------------</p>
        <% conteo = conteo + 1;
              }
          } else {
        %>
        <p>No hay modelos</p>
        <%}%>
            
    <a href="index.jsp#seccion2">Volver</a>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"></script>
</body>