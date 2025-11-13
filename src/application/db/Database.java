package application.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // ============================================================
    // 📌 FIXED: ABSOLUTE PATH SO APP ALWAYS READS SAME DATABASE
    // ============================================================
    // Database will always be stored here:
    // C:\Users\Yash\employee_payroll.db
    private static final String DB_FILE =
            System.getProperty("user.home") + File.separator + "employee_payroll.db";

    private static final String DB_URL = "jdbc:sqlite:" + DB_FILE;

    static {
        try {
            System.out.println("📌 Using SQLite DB at: " + DB_FILE);

            // Ensure folder exists (usually home always exists, but keep safe)
            File file = new File(DB_FILE);
            file.getParentFile().mkdirs();

            // Create table if not exists
            try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
                String sql = """
                    CREATE TABLE IF NOT EXISTS employees (
                        id TEXT PRIMARY KEY,
                        name TEXT NOT NULL,
                        department TEXT,
                        basic_salary REAL,
                        da REAL,
                        hra REAL,
                        pf REAL,
                        gross REAL,
                        net REAL
                    );
                """;
                stmt.execute(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database: " + e.getMessage());
        }
    }

    // ============================================================
    // 📌 Return a connection to the same DB every time
    // ============================================================
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
