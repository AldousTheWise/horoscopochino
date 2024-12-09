package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;


import com.elsantisimo.DAO.UsuarioDAO;
import com.elsantisimo.model.Usuario;

/**
 * Servlet implementation class addUserSvl
 */

@WebServlet("/addUsuario")
public class AddUsuarioSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    Usuario usuario;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String form = request.getParameter("form");
    	
    		
    		if ("register".equals(form)) {
    			request.setAttribute("form", "register");
				request.setAttribute("mostrarPassword", true);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
               response.sendRedirect("index.jsp");
			}
    		
     }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession();
        
        usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
        usuario.setPassword(password);
        
        boolean isAdded = false;
        
		try {
			isAdded = usuarioDAO.addUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        if (isAdded) {
        	session.setAttribute("mensaje", "Registro correcto.");
        	session.setAttribute("tipoMensaje", "success");
        	
        	response.sendRedirect("index.jsp?form=login");
		} else {
			session.setAttribute("mensaje", "Error al registrar usuario.");
		    session.setAttribute("tipoMensaje", "error");
		 
		    response.sendRedirect("index.jsp?form=login"); 
		}
    }
}

