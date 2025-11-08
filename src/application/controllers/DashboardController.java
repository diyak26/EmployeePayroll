package application.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class DashboardController {

    @FXML
    private VBox sidebar;

    @FXML
    private StackPane dashboardRoot;

    @FXML
    private Rectangle background;

    @FXML
    public void initialize() {
        // Responsive background
        background.widthProperty().bind(dashboardRoot.widthProperty());
        background.heightProperty().bind(dashboardRoot.heightProperty());

        // Sidebar slide animation
        sidebar.setTranslateX(-250);
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(900), sidebar);
        slideIn.setToX(0);
        slideIn.play();

        // Fade in main dashboard
        FadeTransition fadeIn = new FadeTransition(Duration.millis(900), dashboardRoot);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    // Scene navigation
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

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), ((Node) event.getSource()).getScene().getRoot());
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            stage.setScene(scene);
            stage.show();

            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), scene.getRoot());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        });
        fadeOut.play();
    }
}
