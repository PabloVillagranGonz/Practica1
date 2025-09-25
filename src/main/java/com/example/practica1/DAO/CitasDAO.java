package com.example.practica1.DAO;

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

    public boolean obtenerDni(String dni) throws SQLException {
        List<Pacientes> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE Dni = ?";

        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, dni);
        ResultSet resultado = sentencia.executeQuery();

        return resultado.next();
    }
}
