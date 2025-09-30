package com.example.practica1.Controller;

import com.example.practica1.Conexion.Conexion;
import com.example.practica1.DAO.CitasDAO;
import com.example.practica1.DAO.LoginDAO;
import com.example.practica1.util.AlertUtils;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtDNI;

    @FXML
    private TextField txtPassword;

    private LoginDAO loginDAO;

    private Conexion conectar;
    @FXML
    public void initialize() {
        conectar = new Conexion();
        try {
            conectar.conectar();
            loginDAO = new LoginDAO(conectar.getConexion());
        } catch (SQLException | ClassNotFoundException | IOException e) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    @FXML
    void onClickLogin(ActionEvent event) {
        String dni = txtDNI.getText().trim();
        String password = txtPassword.getText().trim();

        if (dni.isEmpty() || password.isEmpty()) {
            AlertUtils.mostrarError("Debes introducir DNI y contraseña");
            return;
        }

        try {
            if (loginDAO.validarLogin(dni, password)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/practica1/cita.fxml"));
                fxmlLoader.setControllerFactory(param -> new CitaController(new CitasDAO(conectar.getConexion()), dni));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                AlertUtils.mostrarError("DNI o contraseña incorrectos");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onClickRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/practica1/register.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
