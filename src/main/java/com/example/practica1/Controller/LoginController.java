package com.example.practica1.Controller;

import com.example.practica1.DAO.CitasDAO;
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

    private final CitasDAO citasDAO;

    public LoginController() {
        citasDAO = new CitasDAO();
        try {
            citasDAO.conectar();
        } catch (SQLException sqle) {
            AlertUtils.mostrarError("Error al conectar con la base de datos");
        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicacion");
        } catch (IOException ioe) {
            AlertUtils.mostrarError("Error al cargar la configuracion");
        }

        System.out.println(System.getProperty("user.home"));
    }

    @FXML
    void onClickLogin(ActionEvent event) {
        String dni = txtDNI.getText().trim();
        String password = txtPassword.getText().trim();

        if (dni.isEmpty() && password.isEmpty()) {
            AlertUtils.mostrarError("Debes introducir DNI y contraseña");
        } else {
            try {
                if (citasDAO.validarLogin(dni, password)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/practica1/cita.fxml"));

                    // Aquí le pasamos el DAO al controlador
                    fxmlLoader.setControllerFactory(param -> new CitaController(citasDAO));

                    Parent root = fxmlLoader.load();

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    AlertUtils.mostrarError("Alguno de los dos datos no son correctos");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}