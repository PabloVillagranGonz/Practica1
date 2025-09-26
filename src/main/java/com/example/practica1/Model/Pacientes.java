package com.example.practica1.Model;

public class Pacientes {

    public int idPaciente;
    public String dni;
    public String pass;
    public String nombre;
    public String direccion;
    public String telefono;

    public Pacientes() {
    }

    public Pacientes(int idPaciente, String dni, String pass, String nombre, String direccion, String telefono) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.pass = pass;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
