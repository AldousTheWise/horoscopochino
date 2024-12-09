package com.elsantisimo.model;

import java.time.LocalDate;

public class Usuario {
     private int id;
     private String nombre, username, password, email, animal;
     private LocalDate fechaNacimiento;
     
     public Usuario(String nombre, String username, String email, LocalDate fechaNacimiento, String password) {
    	    this.nombre = nombre;
    	    this.username = username;
    	    this.email = email;
    	    this.fechaNacimiento = fechaNacimiento;
    	    this.password = password;
    	}
  
	public Usuario(int id, String nombre, String username, String email, LocalDate fechaNacimiento, String password, String animal) {
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
		this.animal = animal;
	}

	public Usuario() {
	}

	public int getId() { return id; }
    public String getNombre() { return nombre;}
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getPassword() { return password; }
    public String getAnimal() { return animal; }

	public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) {this.email = email; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setPassword(String password) { this.password = password; }
	public void setAnimal(String animal) { this.animal = animal; }

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", animal=" + animal + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	        
}
