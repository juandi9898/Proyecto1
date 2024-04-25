<%-- 
    Document   : agregarModelo
    Created on : 1/04/2024, 7:37:24 p. m.
    Author     : juand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
  <link rel="stylesheet" href="style.css">
    <title>Document</title>
</head>
<body class="cuerpo2">
     <!--Comienza Nav-->
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
        
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!--Termina Nav-->
       <div class="contenido1">
        <h2>Agregar Modelo</h2>
        <form action="SvModelo" method="Post" enctype="multipart/form-data">
            <div class="input-box">
                <input type="text" placeholder="Nombre" name="nombre" required>
                <i class='bx bxs-lock-alt'></i>
            </div>
            <div class="input-box">
                <input type="text" placeholder="Tipo de Zapato" name="tipoZapato" required>
                <i class='bx bxs-lock-alt'></i>
            </div>
            <!-- Este input permite al usuario seleccionar un archivo de imagen -->
            <div class="input-box">
                <input type="file" id="imagen" name="imagen" accept="image/*" required>
                <i class='bx bxs-lock-alt'></i>
            </div>
            <button type="submit" class="btn">Agregar</button>
            <br>
            <br>
            <a href="index.jsp#seccion2">Volver</a>
        </form>
    </div>
</body>
</html>
