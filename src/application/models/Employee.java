package application.models;

import javafx.beans.property.*;

public class Employee {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final DoubleProperty basicSalary = new SimpleDoubleProperty();

    private final DoubleProperty da = new SimpleDoubleProperty();
    private final DoubleProperty hra = new SimpleDoubleProperty();
    private final DoubleProperty pf = new SimpleDoubleProperty();
    private final DoubleProperty gross = new SimpleDoubleProperty();
    private final DoubleProperty net = new SimpleDoubleProperty();

    public Employee() {}

    public Employee(int id, String name, String dept, double basic) {
        this.id.set(id);
        this.name.set(name);
        this.department.set(dept);
        this.basicSalary.set(basic);

        calculateSalary();
    }

    // -------------------------
    // SALARY CALCULATIONS
    // -------------------------
    public void calculateSalary() {
        double daVal = basicSalary.get() * 0.10;
        double hraVal = basicSalary.get() * 0.15;
        double pfVal = basicSalary.get() * 0.12;

        double grossSalary = basicSalary.get() + daVal + hraVal;
        double netSalary = grossSalary - pfVal;

        da.set(daVal);
        hra.set(hraVal);
        pf.set(pfVal);
        gross.set(grossSalary);
        net.set(netSalary);
    }

    // -------------------------
    // ID
    // -------------------------
    public int getId() { return id.get(); }
    public void setId(int v) { id.set(v); calculateSalary(); }
    public IntegerProperty idProperty() { return id; }

    // -------------------------
    // NAME
    // -------------------------
    public String getName() { return name.get(); }
    public void setName(String v) { name.set(v); }
    public StringProperty nameProperty() { return name; }

    // -------------------------
    // DEPT
    // -------------------------
    public String getDepartment() { return department.get(); }
    public void setDepartment(String v) { department.set(v); }
    public StringProperty departmentProperty() { return department; }

    // -------------------------
    // BASIC SALARY
    // -------------------------
    public double getBasicSalary() { return basicSalary.get(); }
    public void setBasicSalary(double v) { basicSalary.set(v); calculateSalary(); }
    public DoubleProperty basicSalaryProperty() { return basicSalary; }

    // -------------------------
    // DA
    // -------------------------
    public double getDa() { return da.get(); }
    public DoubleProperty daProperty() { return da; }

    // -------------------------
    // HRA
    // -------------------------
    public double getHra() { return hra.get(); }
    public DoubleProperty hraProperty() { return hra; }

    // -------------------------
    // PF
    // -------------------------
    public double getPf() { return pf.get(); }
    public DoubleProperty pfProperty() { return pf; }

    // -------------------------
    // GROSS
    // -------------------------
    public double getGross() { return gross.get(); }
    public DoubleProperty grossProperty() { return gross; }

    // -------------------------
    // NET
    // -------------------------
    public double getNet() { return net.get(); }
    public DoubleProperty netProperty() { return net; }
}
