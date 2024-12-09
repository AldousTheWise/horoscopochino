<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<div>
<h1>Inicia sesión</h1>
<p>Es necesario que inicies sesión para revisar tu horóscopo chino. Si no tienes cuenta, haz click <a href="addUsuario?form=register">aquí</a>.</p>

<form action="loginUsuario" method="POST">
    <div class="form-group">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" class="form-control" required>
    </div>
    <div class="form-group">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" class="form-control" required>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Ingresar</button> 
 </form>
</div>
</html>