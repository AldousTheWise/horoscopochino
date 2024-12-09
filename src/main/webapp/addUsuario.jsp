<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>



<div class="container mt-5">
<h2>Registrate</h2>
  <jsp:include page="formUsuario.jsp">
      <jsp:param name="mostrarPassword" value="${mostrarPassword}"/>
  </jsp:include>
  <br>
  <p>¿Ya tienes cuenta? <a href="index.jsp?form=login">Inicia sesión aquí</a>.</p>
</div>
