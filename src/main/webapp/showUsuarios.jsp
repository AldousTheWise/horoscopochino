<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuarios</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class ="d.flex flex-column min-vh-100">
     <header>
         <jsp:include page="menu.jsp" />
     </header>
     <main class = "flex-grow-1 my-2">
         <div class="container mt-3">
           <h1 class="mb-4">Lista de Usuarios</h1>
             
             <div class="container my-5">
                <h3>Buscar Usuarios</h3>
                <form action="showUsuarios" method="GET" class="mb-4">  
                   <div class="input-group">
                      <input type="text" name="criterio" class="form-control" placeholder="Buscar por nombre, username o ID">
                      <button type="submit" class="btn btn-primary">Buscar</button>
                   </div>     
                 </form>
                    <div class="alert alert-info" role="alert">
                      La búsqueda es insensible a mayúsculas y minúsculas. Si el nombre o apellido contiene acentos, ingrese el dato con ellos, por favor.
                    </div>
             </div>
    
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Username</th>
            <th class="d-none d-md-table-cell">Email</th>
            <th class="d-none d-md-table-cell">Fecha Nacimiento</th>
            <th class="d-none d-md-table-cell">Animal</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr> 
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
                <td>${usuario.username}</td>
                <td class="d-none d-md-table-cell">${usuario.email}</td>
                <td class="d-none d-md-table-cell">${usuario.fechaNacimiento}</td>
                <td class="d-none d-md-table-cell">${usuario.animal}</td>
                <td class= "d-flex align-items-center gap-1">
                 <a href="updateUsuario?id=${usuario.id}" class="btn btn-primary btn-sm">Editar</a>
                 <form action="deleteUsuario" method="GET">
                     <input type="hidden" name="id" value="${usuario.id}"/>
                     <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                 </form>
                </td>
            </tr>      
         </c:forEach>
       </tbody>
    </table>
    
    <form action="index.jsp" method="GET">
           <input type="hidden" name="view" value="adminIF"/>
           <button type="submit" class="btn btn-secondary my-2">Volver</button>
    </form>   
    </div>
   </main>
   <footer class="bg-dark text-white py-3 mt-auto">
       <jsp:include page="footer.jsp"/>
   </footer>

<%
    String mensaje = (String) session.getAttribute("mensaje");
    String tipoMensaje = (String) session.getAttribute("tipoMensaje"); // Nuevo atributo para tipo de mensaje
    if (mensaje != null) { 
%>
    <%@ include file="modalMensaje.jsp" %>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            var mensajeModal = new bootstrap.Modal(document.getElementById('mensajeModal'));
            mensajeModal.show();
        });
    </script>
<%
    // Limpiamos los atributos después de mostrarlos
    session.removeAttribute("mensaje");
    session.removeAttribute("tipoMensaje");
}
%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

