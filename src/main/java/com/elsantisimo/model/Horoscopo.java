package com.elsantisimo.model;

import java.time.LocalDate;

public class Horoscopo {
	private String animal;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public Horoscopo(String animal, LocalDate fechaInicio, LocalDate fechaFin) {
		this.animal = animal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public String getAnimal() { return animal; }
	public LocalDate getFechaInicio() { return fechaInicio; }
	public LocalDate getFechaFin() { return fechaFin; }
	
}
