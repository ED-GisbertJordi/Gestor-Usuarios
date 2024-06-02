package es.progcipfpbatoi.gestorTareas.modelo.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tarea {
	
	private int codigo;
	private String nombre;
	private String descripcion;
	private Prioridad prioridad;
	private LocalDateTime creadoEn;
	private boolean realizada;
	private LocalDate fechaVencimiento;
	private LocalTime horaVencimiento;

	public Tarea(int codigo, String nombre, String descripcion, Prioridad prioridad,boolean realizada, LocalDate fechaVen, LocalTime horaVen) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		
		this.prioridad = prioridad;
		this.realizada = realizada;
		fechaVencimiento = fechaVen;
		horaVencimiento = horaVen;
		this.creadoEn = LocalDateTime.now();
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public Prioridad getPrioridad() {
		return prioridad;
	}
	
	public String getRealizada() {
		return String.valueOf(realizada);
	}
	
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public LocalTime getHoraVencimiento() {
		return horaVencimiento;
	}
	
	
	public String toString() {
		return "Tarea con codigo: " + this.codigo + " de " + this.nombre + " con descripcion: " + this.descripcion;
	}
	
}
