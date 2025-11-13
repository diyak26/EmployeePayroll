package application.controllers;

import application.dao.EmployeeDAO;  // ✅ ADD THIS
import application.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class EmployeeListController {

    @FXML private TableView<Employee> tableEmployees;
    @FXML private TableColumn<Employee, String> colId;
    @FXML private TableColumn<Employee, String> colName;
    @FXML private TableColumn<Employee, String> colDept;
    @FXML private TableColumn<Employee, Number> colBasic;
    @FXML private TableColumn<Employee, Number> colDA;
    @FXML private TableColumn<Employee, Number> colHRA;
    @FXML private TableColumn<Employee, Number> colPF;
    @FXML private TableColumn<Employee, Number> colGross;
    @FXML private TableColumn<Employee, Number> colNet;

    private final ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colDept.setCellValueFactory(data -> data.getValue().departmentProperty());
        colBasic.setCellValueFactory(data -> data.getValue().basicSalaryProperty());
        colDA.setCellValueFactory(data -> data.getValue().daProperty());
        colHRA.setCellValueFactory(data -> data.getValue().hraProperty());
        colPF.setCellValueFactory(data -> data.getValue().pfProperty());
        colGross.setCellValueFactory(data -> data.getValue().grossProperty());
        colNet.setCellValueFactory(data -> data.getValue().netProperty());

        tableEmployees.setItems(employees);

        // ✅ Load from database on startup
        loadEmployeesFromDatabase();
    }

    // ==================================================
    // Load employees from SQLite on app start
    // ==================================================
    private void loadEmployeesFromDatabase() {
        employees.clear();
        try {
            List<Employee> list = EmployeeDAO.getAllEmployees();
            employees.addAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Database Load Error");
            alert.setContentText("Could not load employees from database.\n" + e.getMessage());
            alert.show();
        }
    }

    // ==================================================
    // ADD EMPLOYEE
    // ==================================================
    @FXML
    private void openAddEmployee() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/resources/add_employee.fxml"));
            Stage popup = new Stage();
            popup.setScene(new Scene(loader.load()));
            popup.setTitle("Add Employee");
            popup.initModality(Modality.APPLICATION_MODAL);

            AddEmployeeController controller = loader.getController();
            popup.showAndWait();

            Employee emp = controller.getCreatedEmployee();

            if (emp != null) {
                employees.add(emp);              // update UI list
                EmployeeDAO.addEmployee(emp);    // ✅ save in DB
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    // ==================================================
    // SALARY SLIP
    // ==================================================
    @FXML
    private void openSalarySlip() {
        try {
            Employee selected = tableEmployees.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("No Employee Selected");
                alert.setContentText("Please select an employee first.");
                alert.show();
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/resources/salary_slip.fxml"));
            Stage popup = new Stage();
            popup.setScene(new Scene(loader.load()));
            popup.setTitle("Salary Slip");
            popup.initModality(Modality.APPLICATION_MODAL);

            SalarySlipController controller = loader.getController();
            controller.setEmployee(selected);
            popup.show();

        } catch (Exception e) { e.printStackTrace(); }
    }

    // ==================================================
    // DELETE EMPLOYEE
    // ==================================================
    @FXML
    private void deleteEmployee() {
        Employee selected = tableEmployees.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select an employee to delete.");
            alert.show();
            return;
        }

        try {
            EmployeeDAO.deleteEmployee(selected.getId());  // ❗delete from DB
            employees.remove(selected);                    // remove from UI
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Delete Failed");
            alert.setContentText("Failed to delete employee:\n" + e.getMessage());
            alert.show();
        }
    }
}
