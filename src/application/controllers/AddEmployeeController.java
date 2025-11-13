package application.controllers;

import application.models.Employee;
import application.dao.EmployeeDAO;
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

            // Collect input values
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String dept = txtDept.getText().trim();

            if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Missing Fields", "Please fill in all required fields.");
                return;
            }

            double basic = Double.parseDouble(txtBasic.getText().trim());
            double da = Double.parseDouble(txtDA.getText().trim());
            double hra = Double.parseDouble(txtHRA.getText().trim());
            double pf = Double.parseDouble(txtPF.getText().trim());


            // Perform salary calculations

            double gross = basic + da + hra;
            double net = gross - pf;


            // Create Employee object

            createdEmployee = new Employee(id, name, dept, basic, da, hra, pf, gross, net);


            // Save to database (SQLite)

            try {
                EmployeeDAO.addEmployee(createdEmployee);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Employee added and saved successfully!");
            } catch (Exception dbEx) {
                dbEx.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to save employee to database:\n" + dbEx.getMessage());
                return;
            }

            closeWindow();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numeric values for salary fields.");
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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
