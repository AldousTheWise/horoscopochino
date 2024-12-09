<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ include file="menu.jsp" %> <!-- Incluir el menú -->

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eliminar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <h2>Eliminar Usuario</h2>
    
    <form action="deleteUsuario" method="POST">
			<input type="hidden" name="id" value="${usuarioId}" />
			<p>¿Estás seguro de que deseas eliminar este usuario?</p>
          <button type="submit" class="btn btn-danger btn-lg">Eliminar</button>
          <br>
          <a href="showUsuarios" class="btn btn-secondary btn-lg mt-2">Cancelar</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
