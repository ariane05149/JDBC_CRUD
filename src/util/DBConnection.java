package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/studentdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ariane@123";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTables() {
        String studentsTable = """
            CREATE TABLE IF NOT EXISTS students(
                id SERIAL PRIMARY KEY,
                first_name VARCHAR(50),
                last_name VARCHAR(50),
                email VARCHAR(100) UNIQUE,
                date_of_birth DATE
            );
        """;

        String coursesTable = """
            CREATE TABLE IF NOT EXISTS courses(
                id SERIAL PRIMARY KEY,
                course_name VARCHAR(100) UNIQUE,
                course_description TEXT
            );
        """;

        String marksTable = """
            CREATE TABLE IF NOT EXISTS marks(
                student_id INT REFERENCES students(id) ON DELETE CASCADE,
                course_id INT REFERENCES courses(id) ON DELETE CASCADE,
                marks FLOAT,
                PRIMARY KEY(student_id, course_id)
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(studentsTable);
            stmt.execute(coursesTable);
            stmt.execute(marksTable);

            System.out.println("Tables created or already exist.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}