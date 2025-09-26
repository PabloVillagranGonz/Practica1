package com.example.practica1.Controller;

import com.example.practica1.DAO.CitasDAO;
import com.example.practica1.Model.Citas;
import com.example.practica1.Model.Especialidades;
import com.example.practica1.Model.Pacientes;
import com.example.practica1.util.AlertUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class CitaController {

    @FXML
    private TableColumn<Citas, String> ColumnEspecialidad;

    @FXML
    private TableColumn<Citas, String> ColumnFecha;

    @FXML
    private Button btnBorrarCita;

    @FXML
    private Button btnVerCitas;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnModificarCita;

    @FXML
    private Button btnNuevaCita;

    @FXML
    private TableColumn<Citas, String> columnCitaNum;

    @FXML
    private ComboBox<String> comboEspecialidad;

    @FXML
    private DatePicker dateCita;

    @FXML
    private TableView<Citas> tableView;

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

    private final CitasDAO citasDao; // DAO que nos pasa el LoginController

    public CitaController(CitasDAO citasDao) {
        this.citasDao = citasDao;
    }

    @FXML
    void onClickLBorrar(ActionEvent event) {

    }

    @FXML
    void onClickLimpiar(ActionEvent event) {
        limpiarcampos();
    }


    @FXML
    void onClickModificar(ActionEvent event) {

    }

    private boolean creandoNuevaCita = false; // controlamos el estado del boton

    @FXML
    void onClickNueva(ActionEvent event) {

        if (!creandoNuevaCita) {
            desbloquearCampos();
            limpiarcampos();

            int siguienteId = citasDao.obtenerSiguienteIdCita();
            txtNumCita.setText(String.valueOf(siguienteId));

            creandoNuevaCita = true; // ahora estamos en modo "crear nueva cita"
            btnNuevaCita.setText("Guardar Cita"); // cambiamos el texto del boton para saber lo que estamos creando
        } else {
            // Creamos y guardamos la cita

            if(txtDni.getText().isEmpty() || txtNombrePaciente.getText().isEmpty() || dateCita.getValue() == null || comboEspecialidad.getValue() == null) {
                AlertUtils.mostrarError("Debes rellenar todos los campos antes de guardar la cita.");
                return;
            }

            try {
                Pacientes paciente = citasDao.obtenerPacientesPorDni(txtDni.getText());
                if (paciente == null) {
                    AlertUtils.mostrarError("El DNI no existe en la base de datos.");
                    return;
                }

                Especialidades especialidad = citasDao.obtenerEspecialidadPorTipo(comboEspecialidad.getValue());
                if (especialidad == null) {
                    AlertUtils.mostrarError("La especialidad seleccionada no existe.");
                    return;
                }

                Citas nuevaCita = new Citas();
                nuevaCita.paciente = paciente;
                nuevaCita.especialidad = especialidad;
                nuevaCita.fecha = dateCita.getValue();

                citasDao.agregarCita(nuevaCita);
                AlertUtils.mostrarMensaje("Cita agregada correctamente");

                limpiarcampos();
                desbloquearCampos();

                int siguienteId = citasDao.obtenerSiguienteIdCita();
                txtNumCita.setText(String.valueOf(siguienteId));
            } catch (SQLException e) {
                e.printStackTrace();
                AlertUtils.mostrarError("Error al guardar la cita en la base de datos.");
            }
        }
    }

    public void initialize () {
        txtDni.setDisable(true);
        txtNombrePaciente.setDisable(true);
        txtNumCita.setDisable(true);
        txtTelefono.setDisable(true);
        txtDireccion.setDisable(true);
        comboEspecialidad.setDisable(true);
        dateCita.setDisable(true);

        txtDni.setOnAction(actionEvent -> cargarDatosPaciente());
        cargarEspecialidades();

        columnCitaNum.setCellValueFactory(new PropertyValueFactory<>("idCita"));
        ColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColumnEspecialidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().especialidad.tipo));

    }

    private void cargarDatosPaciente() {
        String dni = txtDni.getText().trim();

        if(!dni.isEmpty()) {
            try{
                Pacientes pacientes = citasDao.obtenerPacientesPorDni(dni);

                if(pacientes != null) {
                    txtNombrePaciente.setText(pacientes.nombre);
                    txtDireccion.setText(pacientes.direccion);
                    txtTelefono.setText(pacientes.telefono);
                } else {
                    txtNombrePaciente.clear();
                    AlertUtils.mostrarError("El Dni no existe en la base de datos");
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
    }

    private void cargarEspecialidades() {
        try {
            List<String> especialidades = citasDao.obtenerEspecialidades();
            comboEspecialidad.getItems().setAll(especialidades);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtils.mostrarError("Error cargando especialidades.");
        }
    }

    private void limpiarcampos() {
        txtDireccion.clear();
        txtDni.clear();
        txtTelefono.clear();
        txtNumCita.clear();
        txtNombrePaciente.clear();
        dateCita.setValue(null);
        comboEspecialidad.setValue(null);
    }

    private void desbloquearCampos() {
        dateCita.setDisable(false);
        txtDni.setDisable(false);
        comboEspecialidad.setDisable(false);
    }
}