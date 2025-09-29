package com.example.practica1.Controller;

import com.example.practica1.Conexion.Conexion;
import com.example.practica1.DAO.RegisterDAO;
import com.example.practica1.Model.Pacientes;
import com.example.practica1.util.AlertUtils;
import com.example.practica1.util.HashUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnAtras;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    private RegisterDAO registerDAO;
    private Conexion conectar;

    @FXML
    public void initialize() {
        conectar = new Conexion();
        try {
            conectar.conectar();
            registerDAO = new RegisterDAO(conectar.getConexion());
        } catch (SQLException | ClassNotFoundException | IOException e) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    @FXML
    void onClickRegister(ActionEvent event) throws SQLException {
        String dni = txtDNI.getText().trim();
        String contraseña = HashUtils.sha256(txtPassword.getText().trim());
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (dni.isEmpty() || txtPassword.getText().isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            AlertUtils.mostrarError("Debes rellenar todos los datos");
            return;
        }

        if (dni.length() != 9) {
            AlertUtils.mostrarError("El DNI debe tener 9 caracteres");
            return;
        }

        if (registerDAO.existeDni(dni)) {
            AlertUtils.mostrarError("El paciente con ese DNI ya existe");
            return;
        }

        Pacientes paciente = new Pacientes(0, dni, contraseña, nombre, direccion, telefono);

        registerDAO.agregarPaciente(paciente);
        AlertUtils.mostrarMensaje("Paciente registrado correctamente");
    }

    public void onClickAtras(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/practica1/inicio.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
