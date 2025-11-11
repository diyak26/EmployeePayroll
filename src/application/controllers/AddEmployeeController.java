package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.models.Employee;

public class AddEmployeeController {

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    @FXML
    private TextField txtDepartment;

    @FXML
    private TextField txtSalary;

    private Employee createdEmployee;  // This will hold the Employee created

    // ===========================
    // SAVE EMPLOYEE
    // ===========================
    @FXML
    private void saveEmployee() {

        try {
            int id = Integer.parseInt(txtEmpId.getText());
            String name = txtEmpName.getText();
            String dept = txtDepartment.getText();
            double salary = Double.parseDouble(txtSalary.getText());

            // Create Employee object
            createdEmployee = new Employee(id, name, dept, salary);

            System.out.println("Employee Saved: " + createdEmployee);

            // Close window
            Stage stage = (Stage) txtEmpId.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Invalid input! Please check values.");
        }
    }

    // ===========================
    // CANCEL / CLEAR FORM
    // ===========================
    @FXML
    private void cancelEmployee() {
        txtEmpId.clear();
        txtEmpName.clear();
        txtDepartment.clear();
        txtSalary.clear();

        System.out.println("Form cleared!");
    }

    // ===========================
    // METHOD CALLED BY EmployeeListController
    // ===========================
    public Employee getCreatedEmployee() {
        return createdEmployee;
    }
}
