<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<div class="container-fluid">
    
   <c:choose>
       <c:when test="${not empty requestScope.animal}">
           <jsp:include page="askHoroscopo.jsp"/>
       </c:when> 
       
   <c:otherwise>
        <header class="text-center my-4">
            <h2>¿Qué deseas hacer, ${sessionScope.nombreUsuario}?</h2>
        </header>

        <!-- Botones -->
        <div class="row justify-content-center">
        
           <!-- Botón para desplegar askHoroscopo -->
            <div class="col-12 col-md-3 mb-4">
              <form action="askHoroscopo" method = "POST" class="w-100">
                <button class="btn btn-primary w-100 py-4">Conoce tu animal</button>
              </form>
            </div>
            
            <!-- Botón para desplegar listUsuarios -->
            <div class="col-12 col-md-3 mb-4">
               <form action="showUsuarios" method="GET" class="w-100">
                    <button class="btn btn-secondary w-100 py-4">Buscar usuarios</button>
               </form>
            </div>
            
            <!-- Botón para abrir updateUsuario -->
            <div class="col-12 col-md-3 mb-4">
               <form action="updateUsuario" method="GET" class="w-100">
                <input type="hidden" name="id" value="${sessionScope.usuario.id}"/> 
                <input type="hidden" name="desdeAdmin" value="true"/>              
                <button class="btn btn-warning w-100 py-4">Modificar datos</button>
               </form>
            </div>
            
            <!-- Botón para eliminar cuenta -->
            <div class="col-12 col-md-3 mb-4">
             <form action="deleteUsuario" method="get">
                <input type="hidden" name="id" value="${usuario.id}"> 
                <button type="submit" class="btn btn-danger w-100 py-4">Eliminar cuenta</button>
             </form>
            </div>
        </div>
       </c:otherwise> 
  </c:choose>
    </div>
</html>