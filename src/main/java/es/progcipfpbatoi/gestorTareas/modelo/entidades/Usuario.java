package es.progcipfpbatoi.gestorTareas.modelo.entidades;

import java.time.LocalDate;

public class Usuario {

    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    private String prefijo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String codPostal;
    private String password;
    
    public Usuario(String nombre, String apellidos, String dni, String email, String prefijo, String telefono, LocalDate fechaNacimiento, String codPostal, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.codPostal = codPostal;
        this.prefijo = prefijo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Usuario con dni: " + this.dni + " de " + this.nombre + " con email: " + this.email;
    }


}
