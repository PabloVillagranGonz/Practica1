package com.example.practica1.Model;

public class Citas {

    public int idCita;
    public Pacientes paciente;
    public Especialidades especialidad;
    public String direccion;
    public String telefono;

    public Citas() {
    }

    public Citas(int idCita, Pacientes paciente, Especialidades especialidad, String direccion, String telefono) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cita de " + paciente + " para " + especialidad + " en " + direccion;
    }
}
