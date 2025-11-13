package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Employee Payroll System.
 * Starts with the Login screen.
 */
public class Main extends Application {gf

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/resources/login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Employee Payroll System - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
