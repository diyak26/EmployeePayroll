package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Load Employee List Page as Home Screen
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("resources/employee_list.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Employee Payroll System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
