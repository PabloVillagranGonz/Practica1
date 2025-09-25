package com.example.practica1.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CitaController {

    @FXML
    private TableColumn<?, ?> ColumnEspecialidad;

    @FXML
    private TableColumn<?, ?> ColumnFecha;

    @FXML
    private Button btnBorrarCita;

    @FXML
    private Button btnCitasPendientes;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnModificarCita;

    @FXML
    private Button btnNuevaCita;

    @FXML
    private TableColumn<?, ?> columnCitaNum;

    @FXML
    private ComboBox<?> comboEspecialidad;

    @FXML
    private DatePicker dateCita;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombrePaciente;

    @FXML
    private TextField txtNumCita;

    @FXML
    private TextField txtTelefono;

    @FXML
    void btnVerCitas(ActionEvent event) {

    }

    @FXML
    void onClickLBorrar(ActionEvent event) {

    }

    @FXML
    void onClickLimpiar(ActionEvent event) {

    }

    @FXML
    void onClickModificar(ActionEvent event) {

    }

    @FXML
    void onClickNueva(ActionEvent event) {

    }

}
