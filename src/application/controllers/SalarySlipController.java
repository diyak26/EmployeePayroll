package application.controllers;

import application.models.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

}
