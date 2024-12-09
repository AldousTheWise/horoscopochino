<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Datos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
</head>
<body class="d-flex flex-column min-vh-100">
   <header>
      <jsp:include page="menu.jsp" />
   </header>
   <main class="flex-grow-1 my-4">
    <div class="container mt-3">
        <h2>Actualiza los datos</h2>
        <jsp:include page="formUsuario.jsp">
            <jsp:param name="mostrarPassword" value="${mostrarPassword}"/>
        </jsp:include>
        <form action="index.jsp" method="GET">
           <input type="hidden" name="view" value="adminIF"/>
           <button type="submit" class="btn btn-secondary my-2">Volver</button>
        </form>
    </div>
    </main>
    <footer class="bg-dark text-white py-3 mt-auto">    
      <jsp:include page="footer.jsp"/>
    </footer>
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
