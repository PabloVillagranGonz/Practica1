package com.example.practica1.Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

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
        conexion = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password
        );
    }

    public java.sql.Connection getConexion() {
        return conexion;
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
