<%-- 
    Document   : agregarZapato
    Created on : 1/04/2024, 7:39:32 p. m.
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
<body class="cuerpo3">
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
      
        </ul>
      </div>
    </div>
  </nav>
  <!--Termina Nav-->
  <div class="contenido2">
    <h2>Agregar Zapato</h2>
    <form action="">
        <div class="input-box">
            <input type="text" placeholder="Nombre" required><!--El placeholder es para que ese texto aparezca ahi hasta quer nosotros escribamos algo-->
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <div class="input-box">
            <input type="text" placeholder="Marca" required><!--El placeholder es para que ese texto aparezca ahi hasta quer nosotros escribamos algo-->
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <div class="input-box">
            <input type="text" placeholder="Talla" required><!--El placeholder es para que ese texto aparezca ahi hasta quer nosotros escribamos algo-->
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <div class="input-box">
            <input type="text" placeholder="Precio" required><!--El placeholder es para que ese texto aparezca ahi hasta quer nosotros escribamos algo-->
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <div class="input-box">
            <input type="text" placeholder="Color" required><!--El placeholder es para que ese texto aparezca ahi hasta quer nosotros escribamos algo-->
            <i class='bx bxs-lock-alt' ></i>
        </div>
        <a href="index.jsp#seccion2">Volver</a>
        <button type="submit" class="btn">Agregar</button>
    
    </form>
  </div>
</body>
</html>