package com.example.practica1.DAO;

import com.example.practica1.Model.Pacientes;
import com.example.practica1.util.HashUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private final Connection conexion;

    public LoginDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean validarLogin(String dni, String password) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE Dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String passHash = rs.getString("pass");
                    String hashedInput = HashUtils.sha256(password);
                    return hashedInput.equals(passHash);
                }
            }
        }
        return false;
    }

    public Pacientes obtenerPacientePorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE Dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pacientes(
                            rs.getInt("idPaciente"),
                            rs.getString("Dni"),
                            rs.getString("pass"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("telefono")
                    );
                }
            }
        }
        return null;
    }
}
