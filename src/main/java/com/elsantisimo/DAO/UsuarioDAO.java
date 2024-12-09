package com.elsantisimo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elsantisimo.dbconexion.DBConexion;
import com.elsantisimo.model.Usuario;



public class UsuarioDAO {
	
	/*Registro usuario*/
	public boolean addUsuario(Usuario user) throws SQLException {
		String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password) VALUES (?, ?, ?, ?, ?)";
		try( Connection conn = DBConexion.getConexion();
			 PreparedStatement pstm = conn.prepareStatement(sql)){
			
			pstm.setString(1, user.getNombre());
			pstm.setString(2, user.getUsername());
			pstm.setString(3, user.getEmail());
			pstm.setDate(4, java.sql.Date.valueOf(user.getFechaNacimiento()));
			pstm.setString(5, user.getPassword());
			
		    int result = pstm.executeUpdate();
		    return result > 0;
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		 return false;
	}
    
	//Metodo para la autenticacion.
	public Usuario getUsuarioByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE username = ?";
		
		try(Connection conn = DBConexion.getConexion();
		    PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			pstm.setString(1, username);
			
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
	            Usuario usuario = new Usuario(
	                    rs.getString("nombre"), 
	                    rs.getString("username"), 
	                    rs.getString("email"),
	                    rs.getDate("fecha_nacimiento").toLocalDate(), 
	                    rs.getString("password"));
	            usuario.setId(rs.getInt("id"));
	            return usuario;
	        } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	/*Actualizar animal en usuario*/
	public void actualizarAnimal(int userId, String animal) {
	     String sql = "UPDATE usuarios SET animal = ? WHERE id = ?";
	     
	     try(Connection conn = DBConexion.getConexion();
			 PreparedStatement pstm = conn.prepareStatement(sql)){
	    	 
	    	 pstm.setString(1, animal);
	    	 pstm.setInt(2, userId);
	    	 pstm.executeUpdate();
	    	 
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	
	//Mostrar usuarios en lista.
	public List<Usuario> listarUsuarios() throws SQLException {
	    List<Usuario> usuarios = new ArrayList<>();
	    String sql = "SELECT * FROM usuarios";
	    
	       try(Connection conn = DBConexion.getConexion();
	    		PreparedStatement pstm = conn.prepareStatement(sql);
	    		ResultSet rs = pstm.executeQuery()) {
	    	   
	    	   while (rs.next()) {
	    		   Usuario usuario = new Usuario (
	    				   rs.getInt("id"),
	    				   rs.getString("nombre"),
	    				   rs.getString("username"),
	    				   rs.getString("email"),
	    				   rs.getDate("fecha_nacimiento").toLocalDate(),
	    				   rs.getString("password"),
	    				   rs.getString("animal")
	    				   );
	    		   usuarios.add(usuario);
	    	   }
	       } catch(SQLException e) {
	    	   e.printStackTrace();
	       }
	       
	       return usuarios;
	}
	
	//Método de búsqueda de usuario por id, nombre o username en la vista showUsuarios.jsp
	public List<Usuario> buscarUsuario(String criterio) {
		List<Usuario> listaUsuarios = new ArrayList<>();
		String sql = "SELECT * FROM usuarios WHERE CAST(id AS CHAR) LIKE ? OR LOWER(nombre) LIKE ? OR LOWER(username) LIKE ?";
		try (Connection conn = DBConexion.getConexion();
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			    
		        ps.setString(1, "%" + criterio + "%");
		        ps.setString(2, "%" + criterio.toLowerCase() + "%");
		        ps.setString(3, "%" + criterio.toLowerCase() + "%");
		        
		        ResultSet rs = ps.executeQuery();
		        
		        while (rs.next()) {
		        	Usuario usuario = new Usuario(
		        			rs.getInt("id"),
		                    rs.getString("nombre"),
		                    rs.getString("username"),
		                    rs.getString("email"),
		                    rs.getDate("fecha_nacimiento").toLocalDate(),
		                    rs.getString("password"),
		                    rs.getString("animal")	
		        			);
		        	
		        	listaUsuarios.add(usuario);
		        }
		   }catch (SQLException e) {
		      e.printStackTrace();
		   }
		     return listaUsuarios;    
	}
	
	//metodo auxiliar para obtener usuario por id y editarlo.
	public Usuario getUsuarioById(int id) {
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		Usuario usuario = null;
		
		try(Connection conn = DBConexion.getConexion();
				PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setUsername(rs.getString("username"));
				usuario.setEmail(rs.getString("email"));
				usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
				usuario.setPassword(rs.getString("password"));		
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	
	public boolean updateUsuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET nombre = ?, username = ?, email = ?, fecha_nacimiento = ?, password = ? WHERE id = ?";
		try (Connection conn = DBConexion.getConexion();
			 PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			pstm.setString(1, usuario.getNombre());
			pstm.setString(2, usuario.getUsername());
			pstm.setString(3, usuario.getEmail());
			pstm.setDate(4, java.sql.Date.valueOf(usuario.getFechaNacimiento()));
			pstm.setString(5, usuario.getPassword());
			pstm.setInt(6, usuario.getId());
			
			int filasAfectadas = pstm.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Usuario actualizado exitosamente.");
	            return true;
	        } else {
	            System.out.println("No se pudo actualizar el usuario.");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}
	
	/*Eliminar usuario*/
	public static boolean deleteUsuario(int id) throws SQLException {
		String sql = "DELETE FROM usuarios WHERE id = ?";
		try(Connection conn = DBConexion.getConexion();
			PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, id);
			
			int rowsAffected = pstm.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Error al eliminar el usuario: " + e.getMessage());
			throw e;
		}			
	} 
			
}



