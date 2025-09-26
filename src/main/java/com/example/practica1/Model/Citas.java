package com.example.practica1.Model;

import java.time.LocalDate;

public class Citas {

    public int idCita;
    public Pacientes paciente;
    public Especialidades especialidad;
    public LocalDate fecha;


    public Citas() {
    }

    public Citas(int idCita, Pacientes paciente, Especialidades especialidad, LocalDate fecha) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.especialidad = especialidad;
        this.fecha = fecha;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cita de " + paciente + " para " + especialidad + " con fecha: " + fecha;
    }
}
