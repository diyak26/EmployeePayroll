package application.models;

import javafx.beans.property.*;

public class Employee {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty department;
    private final DoubleProperty basicSalary;
    private final DoubleProperty da;
    private final DoubleProperty hra;
    private final DoubleProperty pf;
    private final DoubleProperty gross;
    private final DoubleProperty net;

    public Employee(String id, String name, String department,
                    double basicSalary, double da, double hra,
                    double pf, double gross, double net) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.basicSalary = new SimpleDoubleProperty(basicSalary);
        this.da = new SimpleDoubleProperty(da);
        this.hra = new SimpleDoubleProperty(hra);
        this.pf = new SimpleDoubleProperty(pf);
        this.gross = new SimpleDoubleProperty(gross);
        this.net = new SimpleDoubleProperty(net);
    }

    // ====================================================
    // PROPERTY GETTERS (for TableView bindings)
    // ====================================================
    public StringProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty departmentProperty() { return department; }
    public DoubleProperty basicSalaryProperty() { return basicSalary; }
    public DoubleProperty daProperty() { return da; }
    public DoubleProperty hraProperty() { return hra; }
    public DoubleProperty pfProperty() { return pf; }
    public DoubleProperty grossProperty() { return gross; }
    public DoubleProperty netProperty() { return net; }

    // ====================================================
    // STANDARD VALUE GETTERS (for Controllers like SalarySlip)
    // ====================================================
    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getDepartment() { return department.get(); }
    public double getBasicSalary() { return basicSalary.get(); }
    public double getDa() { return da.get(); }
    public double getHra() { return hra.get(); }
    public double getPf() { return pf.get(); }
    public double getGross() { return gross.get(); }
    public double getNet() { return net.get(); }

    // ====================================================
    // SETTERS (optional)
    // ====================================================
    public void setId(String id) { this.id.set(id); }
    public void setName(String name) { this.name.set(name); }
    public void setDepartment(String department) { this.department.set(department); }
    public void setBasicSalary(double value) { this.basicSalary.set(value); }
    public void setDa(double value) { this.da.set(value); }
    public void setHra(double value) { this.hra.set(value); }
    public void setPf(double value) { this.pf.set(value); }
    public void setGross(double value) { this.gross.set(value); }
    public void setNet(double value) { this.net.set(value); }
}
