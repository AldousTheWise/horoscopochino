package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.elsantisimo.DAO.UsuarioDAO;
import com.elsantisimo.model.Usuario;



/**
 * Servlet implementation class UpdateUserSvl
 */
@WebServlet("/updateUsuario")
public class UpdateUsuarioSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	Usuario usuario;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsuarioSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
		String usuarioIdParam = request.getParameter("id");
		Usuario usuarioAEditar = null;
		
		if (usuarioIdParam != null) {
			 try {
				int usuarioId = Integer.parseInt(usuarioIdParam);
				usuarioAEditar = usuarioDAO.getUsuarioById(usuarioId);	
			} catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("index.jsp?form=adminIF");
				return;
			}	
		}
		
		if(usuarioAEditar == null) {
			response.sendRedirect("index.jsp?form=adminIF");
			return;
		}
		
		request.setAttribute("usuario", usuarioAEditar);
		
		if (usuarioSesion.getId() == usuarioAEditar.getId()) {
			request.setAttribute("mostrarPassword", true);
			request.setAttribute("esUsuarioSesion", true);
		} else {
			request.setAttribute("mostrarPassword", false);
			request.setAttribute("esUsuarioSesion", false);
		}
		
		request.getRequestDispatcher("updateUsuario.jsp").forward(request, response);
	}  
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         HttpSession session = request.getSession();	
		
		
	try {
			int idUsuario = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			
			String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
			LocalDate fechaNacimiento = null;
			
			if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
				fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
			} 
			
			String password = request.getParameter("password");
			
			usuario = usuarioDAO.getUsuarioById(idUsuario);
			if (usuario != null) {
				usuario.setNombre(nombre);
			    usuario.setUsername(username);
			    usuario.setEmail(email);
			    usuario.setFechaNacimiento(fechaNacimiento);
			    
			      if (password != null && !password.isEmpty()) {
				   usuario.setPassword(password);  
			     }

			    boolean actualizado = usuarioDAO.updateUsuario(usuario);
			    
			    if(actualizado) {
			    	session.setAttribute("mensaje", "Usuario actualizado correctamente.");
	                session.setAttribute("tipoMensaje", "success");	
			    } else {
			    	session.setAttribute("mensaje", "Error al actualizar el usuario.");
	                session.setAttribute("tipoMensaje", "error");
			    }
			} else {
				session.setAttribute("mensaje", "Usuario no encontrado.");
	            session.setAttribute("tipoMensaje", "error");
			}

			response.sendRedirect("showUsuarios");
		} catch (Exception e) {
			session.setAttribute("mensaje", "Error en la actualización.");
            session.setAttribute("tipoMensaje", "error");
            response.sendRedirect("showUsuarios");
		}    
	       
	}
}




	
