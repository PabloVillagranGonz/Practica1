package com.example.practica1.Model;

public class Especialidades {

    public int idEspecialidad;
    public String tipo;

    public Especialidades() {
    }

    public Especialidades(int idEspecialidad, String tipo) {
        this.idEspecialidad = idEspecialidad;
        this.tipo = tipo;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
