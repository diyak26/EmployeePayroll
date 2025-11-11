package application.controllers;

import application.models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SalarySlipController {

    @FXML private Label lblId;
    @FXML private Label lblName;
    @FXML private Label lblDept;
    @FXML private Label lblBasic;

    @FXML private Label lblHra;
    @FXML private Label lblDa;
    @FXML private Label lblPf;

    @FXML private Label lblGross;
    @FXML private Label lblNet;

    private Employee employee;

    // Called from parent controller to send selected employee
    public void setEmployee(Employee emp) {
        this.employee = emp;
        fillSlip();
    }

    private void fillSlip() {
        if (employee == null) return;

        double basic = employee.getBasicSalary();
        double hra = basic * 0.10;
        double da = basic * 0.05;
        double pf = basic * 0.12;
        double gross = basic + hra + da;
        double net = gross - pf;

        lblId.setText(String.valueOf(employee.getId()));
        lblName.setText(employee.getName());
        lblDept.setText(employee.getDepartment());
        lblBasic.setText(String.format("%.2f", basic));

        lblHra.setText(String.format("%.2f", hra));
        lblDa.setText(String.format("%.2f", da));
        lblPf.setText(String.format("%.2f", pf));

        lblGross.setText(String.format("%.2f", gross));
        lblNet.setText(String.format("%.2f", net));
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) lblId.getScene().getWindow();
        stage.close();
    }
}
