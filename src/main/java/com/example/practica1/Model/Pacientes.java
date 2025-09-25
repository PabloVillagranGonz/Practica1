package com.example.practica1.Model;

public class Pacientes {

    public int idPaciente;
    public String dni;
    public String pass;
    public String nombre;

    public Pacientes() {
    }

    public Pacientes(int idPaciente, String dni, String pass, String nombre) {
        this.idPaciente = idPaciente;
        this.dni = dni;
        this.pass = pass;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
