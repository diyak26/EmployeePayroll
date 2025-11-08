package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    @FXML
    void goToDashboard(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/dashboard.fxml");
    }

    @FXML
    void goToAddEmployee(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/add_employee.fxml");
    }

    @FXML
    void goToViewEmployee(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/view_employee.fxml");
    }

    @FXML
    void goToSalary(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/salary.fxml");
    }

    @FXML
    void goToPayslip(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/payslip.fxml");
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        switchScene(event, "/application/resources/login.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
