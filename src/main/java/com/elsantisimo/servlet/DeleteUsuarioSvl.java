package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.elsantisimo.DAO.UsuarioDAO;
import com.elsantisimo.model.Usuario;

/**
 * Servlet implementation class DeleteUserSvl
 */
@WebServlet("/deleteUsuario")
public class DeleteUsuarioSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUsuarioSvl() {
            
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioIdParam = request.getParameter("id"); // Aquí obtenemos el id
	    if (usuarioIdParam != null) {
	        // Para asegurarnos de que estamos pasando el id correcto al formulario
	        request.setAttribute("usuarioId", usuarioIdParam); // Pasamos al JSP
	    }
	    request.getRequestDispatcher("deleteUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioIdParam = request.getParameter("id");  // Obtenemos el id desde el formulario
	    HttpSession session = request.getSession();

	    try {
	        if (usuarioIdParam != null && !usuarioIdParam.isEmpty()) {
	            int usuarioId = Integer.parseInt(usuarioIdParam);  // Convertimos a entero
	            
	            // Obtener la sesión
	            Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");  // Usuario en sesión

	            if (usuarioSesion != null && usuarioSesion.getId() == usuarioId) {
	                // Si el id recibido es el del usuario en sesión
	                boolean eliminado = UsuarioDAO.deleteUsuario(usuarioId);
	                

	                if (eliminado) {
	                	session.setAttribute("mensaje", "Cuenta eliminada correctamente");
	                	session.setAttribute("tipoMensaje", "success");
	                    response.sendRedirect("index.jsp?logout=true");  // Redirigir a la página de login
	                } else {
	                    session.setAttribute("error", "No se pudo eliminar el usuario");
	                    session.setAttribute("tipoMensaje", "error");
	                    response.sendRedirect("deleteUsuario.jsp");
	                }
	            } else {
	                // Si no es el usuario en sesión, eliminarlo sin cerrar sesión
	                boolean eliminado = UsuarioDAO.deleteUsuario(usuarioId);
	                
	                if (eliminado) {
	                	session.setAttribute("mensaje", "Cuenta eliminada correctamente");
	                	session.setAttribute("tipoMensaje", "success");
	                    response.sendRedirect("showUsuarios");  // Redirigir a la lista de usuarios
	                } else {
	                    session.setAttribute("error", "No se pudo eliminar el usuario");
	                    session.setAttribute("tipoMensaje", "error");
	                    response.sendRedirect("deleteUsuario.jsp");
	                }
	            }
	        } else {
	            System.out.println("Id inválido o no proporcionado");
	            request.setAttribute("error", "ID no proporcionado o inválido");
	            request.getRequestDispatcher("deleteUsuario.jsp").forward(request, response);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error en la bbdd");
	        request.getRequestDispatcher("deleteUsuario.jsp").forward(request, response);
	        
	    } catch (NumberFormatException e) {
	    	e.printStackTrace();
	        request.setAttribute("error", "Formato de ID inválido");
	        request.getRequestDispatcher("deleteUsuario.jsp").forward(request, response);
	    }
	}
}
