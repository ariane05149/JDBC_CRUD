package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {

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

        try (Connection conn = DBConnection.getConnection();
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