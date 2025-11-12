package application.controllers;

import application.models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeeController {

    @FXML private TextField txtId, txtName, txtDept, txtBasic, txtDA, txtHRA, txtPF;
    @FXML private Button btnSave, btnCancel;

    private Employee createdEmployee;

    public Employee getCreatedEmployee() { return createdEmployee; }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String dept = txtDept.getText().trim();
            double basic = Double.parseDouble(txtBasic.getText().trim());
            double da = Double.parseDouble(txtDA.getText().trim());
            double hra = Double.parseDouble(txtHRA.getText().trim());
            double pf = Double.parseDouble(txtPF.getText().trim());
            double gross = basic + da + hra;
            double net = gross - pf;

            createdEmployee = new Employee(id, name, dept, basic, da, hra, pf, gross, net);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Employee added successfully!");
            alert.showAndWait();

            closeWindow();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Please enter valid numeric values for salary fields.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        createdEmployee = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
