<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>


   <form action='<c:choose>
                   <c:when test="${not empty usuario.id}">updateUsuario</c:when>
                   <c:otherwise>addUsuario</c:otherwise>
                </c:choose>' method="POST">
        <input type="hidden" name="id" value="${usuario.id}"/>
      <div class="mb-2">
         <label for="nombre" class="form-label">Nombre</label>
         <input type="text" class="form-control" id="nombre" name="nombre"
          value="${usuario.nombre != null ? usuario.nombre : ''}" required/> 
      </div>
      <div class="mb-2">
         <label for="username" class="form-label">Username</label>
         <input type="text" class="form-control" id="username" name="username"
          value="${usuario.username != null ? usuario.username : ''}" required/>
      </div>
      <div class="mb-2">
         <label for="email" class="form-label">Email</label>
         <input type="email" class="form-control" id="email" name="email"
          value="${usuario.email != null ? usuario.email : ''}" required/>
      </div>
      <div class="mb-2">
         <label for="fecha_nacimiento" class="form-label">Fecha de Nacimiento</label>
         <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento"
          value="${usuario.fechaNacimiento != null ? usuario.fechaNacimiento : ''}" required/>
      </div>
      
      <c:choose>
         <c:when test="${param.mostrarPassword == 'true'}">
            <div class="mb-2">
              <label for="password" class="form-label">Password</label>
             <input type="password" class="form-control" id="password" name="password" required/>
         </div>
         </c:when>
      </c:choose>
      <button type="submit" class="btn btn-primary mt-2">
               <c:choose>
                  <c:when test="${not empty usuario.id}">Modificar</c:when>
               <c:otherwise>Registrar</c:otherwise>
        </c:choose>
      </button>    
   </form>


