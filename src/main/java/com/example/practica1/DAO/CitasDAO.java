package com.example.practica1.DAO;

import com.example.practica1.Model.Citas;
import com.example.practica1.Model.Especialidades;
import com.example.practica1.Model.Pacientes;
import com.example.practica1.util.R;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CitasDAO {

    private Connection conexion;

    public void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/com/example/practica1/configuration/database.properties")) {
            if (in == null) {
                throw new RuntimeException("Archivo database.properties no encontrado");
            }
            configuration.load(in);
        }
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public boolean validarLogin(String dni, String pass) throws SQLException {
        List<Pacientes> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE Dni = ? AND pass = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, dni);
            sentencia.setString(2, pass);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next();

            }
        }
    }


    public int obtenerSiguienteIdCita() {
        String sql = "SELECT IFNULL(MAX(idCita), 0) + 1 AS siguiente FROM Citas";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("siguiente"); // devuelve el próximo número de cita
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1; // si no hay citas todavía
    }


    public List<String> obtenerEspecialidades() throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT Tipo FROM Especialidades";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString("Tipo"));
            }
        }
        return lista;
    }


    public Especialidades obtenerEspecialidadPorTipo(String tipo) throws SQLException {
        String sql = "SELECT * FROM Especialidades WHERE Tipo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Especialidades esp = new Especialidades();
                    esp.idEspecialidad = rs.getInt("idEspecialidad");
                    esp.tipo = rs.getString("Tipo");
                    return esp;
                }
            }
        }
        return null;
    }


    public Pacientes obtenerPacientesPorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM pacientes WHERE Dni = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
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
        return null;
    }


    public void agregarCita(Citas cita) throws SQLException {
        String sql = "INSERT INTO Citas (idPaciente, idEspecialidad, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cita.paciente.idPaciente);
            ps.setInt(2, cita.especialidad.idEspecialidad);
            ps.setDate(3, java.sql.Date.valueOf(cita.fecha));

            ps.executeUpdate();
        }
    }

    public List<Citas> obtenerCitasPorPaciente(String dni) throws SQLException {
        List<Citas> lista = new ArrayList<>();
        String sql = "SELECT c.idCita, c.fecha, e.idEspecialidad, e.Tipo " +
                "FROM Citas c " +
                "JOIN Pacientes p ON c.idPaciente = p.idPaciente " +
                "JOIN Especialidades e ON c.idEspecialidad = e.idEspecialidad " +
                "WHERE p.Dni = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Citas cita = new Citas();
                    cita.idCita = rs.getInt("idCita");
                    cita.fecha = rs.getDate("fecha").toLocalDate();

                    Especialidades esp = new Especialidades();
                    esp.idEspecialidad = rs.getInt("idEspecialidad");
                    esp.tipo = rs.getString("Tipo");
                    cita.especialidad = esp;

                    lista.add(cita);
                }
            }
        }
        return lista;
    }

}
