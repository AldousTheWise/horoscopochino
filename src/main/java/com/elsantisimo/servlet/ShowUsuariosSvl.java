package com.elsantisimo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.elsantisimo.DAO.UsuarioDAO;
import com.elsantisimo.model.Usuario;


/**
 * Servlet implementation class FindUserSvl
 */
@WebServlet("/showUsuarios")
public class ShowUsuariosSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUsuariosSvl() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String criterio = request.getParameter("criterio");
		List<Usuario> usuarios;
		try {
			if(criterio == null || criterio.trim().isEmpty()) {
			usuarios = usuarioDAO.listarUsuarios();
			} else {
				usuarios = usuarioDAO.buscarUsuario(criterio.trim());
			}
			
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("showUsuarios.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

}
