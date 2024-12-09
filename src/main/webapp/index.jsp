<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horoscopo chino</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
</head>
<body class = "d-flex flex-column min-vh-100">

   <header>
     <jsp:include page="menu.jsp" />
   </header>
   
    <main class = "flex-grow-1">
        <div class="container my-5">
            <c:choose>
                <c:when test="${empty sessionScope.usuario}">
                     <c:choose>
                        <c:when test="${param.form == 'register'}">
                           <jsp:include page="addUsuario.jsp"/>
                        </c:when>
                        <c:otherwise>
                           <jsp:include page="loginUsuario.jsp"/>
                        </c:otherwise>
                     </c:choose>
                </c:when >
                <c:otherwise>
                   <jsp:include page="adminIF.jsp"/>
                </c:otherwise>
           </c:choose>    
        </div>
      </main>
      <footer class="bg-dark text-white mt-5 py-1">
          <jsp:include page="footer.jsp"/>     
      </footer>
  
  <% 
    String mensaje = (String) session.getAttribute("mensaje");
    String tipoMensaje = (String) session.getAttribute("tipoMensaje");
    String logout = request.getParameter("logout");
    
    if (mensaje != null) { 
%>
    <%@ include file="modalMensaje.jsp" %>
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function() {
            var mensajeModal = new bootstrap.Modal(document.getElementById('mensajeModal'));
            mensajeModal.show();
            
            <% if("true".equals(logout)) {  %>
             setTimeout(function(){
            	 window.location.href = "<%= request.getContextPath() %>/logout";
             },3000);
            <% } %>
        });
    </script>
<%
    session.removeAttribute("mensaje");
    session.removeAttribute("tipoMensaje");
}
%>
  
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>