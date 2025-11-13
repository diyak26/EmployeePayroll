package application.dao;

import application.db.Database;
import application.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public static void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (id, name, department, basic_salary, da, hra, pf, gross, net) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getDepartment());
            ps.setDouble(4, emp.getBasicSalary());
            ps.setDouble(5, emp.getDa());
            ps.setDouble(6, emp.getHra());
            ps.setDouble(7, emp.getPf());
            ps.setDouble(8, emp.getGross());
            ps.setDouble(9, emp.getNet());
            ps.executeUpdate();
        }
    }

    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("basic_salary"),
                        rs.getDouble("da"),
                        rs.getDouble("hra"),
                        rs.getDouble("pf"),
                        rs.getDouble("gross"),
                        rs.getDouble("net")
                );
                list.add(e);
            }
        }
        return list;
    }

    public static Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getDouble("basic_salary"),
                            rs.getDouble("da"),
                            rs.getDouble("hra"),
                            rs.getDouble("pf"),
                            rs.getDouble("gross"),
                            rs.getDouble("net")
                    );
                }
            }
        }
        return null;
    }

    public static void deleteEmployee(String id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

}
