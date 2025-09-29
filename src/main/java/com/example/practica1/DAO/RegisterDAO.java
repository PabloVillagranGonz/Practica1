package com.example.practica1.DAO;

import com.example.practica1.Model.Pacientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO {

    private final Connection conexion;

    public RegisterDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean existeDni(String dni) throws SQLException {
        String sql = "SELECT 1 FROM pacientes WHERE Dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void agregarPaciente(Pacientes paciente) throws SQLException {
        String sql = "INSERT INTO Pacientes (Dni, pass, nombre, direccion, telefono) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getPass());
            ps.setString(3, paciente.getNombre());
            ps.setString(4, paciente.getDireccion());
            ps.setString(5, paciente.getTelefono());

            ps.executeUpdate();
        }
    }
}
