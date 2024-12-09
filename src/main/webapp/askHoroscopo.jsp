<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri ="jakarta.tags.core" prefix="c" %>
 
<!DOCTYPE html>
<html>
  <div class="alert alert-info text-center">
     <h3>Conoce a tu animal del Horóscopo Chino:</h3>
       <p>Tu animal es <strong>${requestScope.animal}</strong>.</p>
            <!-- Botón Volver -->
        <form action="index.jsp" method="GET">
           <input type="hidden" name="view" value="adminIF"/>
           <button type="submit" class="btn btn-secondary mt-3">Volver</button>
        </form>
   </div>
</html>