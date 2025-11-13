package application.controllers;

import application.models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class SalarySlipController {

    @FXML private Label lblId, lblName, lblDept, lblBasic, lblDa, lblHra, lblPf, lblGross, lblNet;
    private Employee employee;

    public void setEmployee(Employee emp) {
        this.employee = emp;
        fillSlip();
    }

    private void fillSlip() {
        if (employee == null) return;

        lblId.setText(employee.getId());
        lblName.setText(employee.getName());
        lblDept.setText(employee.getDepartment());
        lblBasic.setText(String.format("%.2f", employee.getBasicSalary()));
        lblDa.setText(String.format("%.2f", employee.getDa()));
        lblHra.setText(String.format("%.2f", employee.getHra()));
        lblPf.setText(String.format("%.2f", employee.getPf()));
        lblGross.setText(String.format("%.2f", employee.getGross()));
        lblNet.setText(String.format("%.2f", employee.getNet()));
    }




    @FXML
    private void closeWindow() {
        // get any node's window and close it
        lblId.getScene().getWindow().hide();
    }

    @FXML
    private void downloadTextFile() {
        try {
            // Choose save location
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Salary Slip Text File");
            chooser.setInitialFileName("SalarySlip_" + lblId.getText() + ".txt");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            Stage stage = (Stage) lblId.getScene().getWindow();
            File file = chooser.showSaveDialog(stage);

            if (file == null) return; // user cancelled

            // Prepare text content
            StringBuilder sb = new StringBuilder();
            sb.append("------------- Salary Slip -------------\n\n");
            sb.append("Employee ID: ").append(lblId.getText()).append("\n");
            sb.append("Name: ").append(lblName.getText()).append("\n");
            sb.append("Department: ").append(lblDept.getText()).append("\n\n");

            sb.append("Basic Salary: ").append(lblBasic.getText()).append("\n");
            sb.append("HRA (10%): ").append(lblHra.getText()).append("\n");
            sb.append("DA (5%): ").append(lblDa.getText()).append("\n");
            sb.append("PF (12%): ").append(lblPf.getText()).append("\n\n");

            sb.append("Gross Salary: ").append(lblGross.getText()).append("\n");
            sb.append("Net Salary: ").append(lblNet.getText()).append("\n");

            // Write the file
            FileWriter writer = new FileWriter(file);
            writer.write(sb.toString());
            writer.close();

            // Optional: show confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Text file saved successfully!");
            alert.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error writing text file: " + e.getMessage());
            alert.show();
        }
    }


}



