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
 * Servlet implementation class LoginSvl
 */
@WebServlet("/loginUsuario")
public class LoginSvl extends HttpServlet {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSvl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    
    public void init() {
    	usuarioDAO = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		
		
		
		try {
			Usuario usuario = usuarioDAO.getUsuarioByUsername(username);
			
			if(usuario != null) {
				
				if (usuario.getPassword().equals(password)) {
					String nombreCompleto = usuario.getNombre();
					String nombreUsuario = nombreCompleto.split(" ")[0];
					
					session.setAttribute("usuario", usuario);
					session.setAttribute("nombreUsuario", nombreUsuario);
					response.sendRedirect("index.jsp?form=adminIF");
				} else {
                    session.setAttribute("mensaje", "Contraseña incorrecta. Intente nuevamente.");
                    session.setAttribute("tipoMensaje", "error");
                    response.sendRedirect("index.jsp?form=login"); 
				}
			} else {
				 session.setAttribute("mensaje", "El usuario no existe. Debe registrarse.");
                 session.setAttribute("tipoMensaje", "error");
                 response.sendRedirect("index.jsp?form=login");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("error", "db_error");
			response.sendRedirect("index.jsp?form=login");
		}
	}
	  

}
