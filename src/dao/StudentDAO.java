package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(Student student) {

        String sql = "INSERT INTO students(first_name,last_name,email,date_of_birth) VALUES(?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, student.getDateOfBirth());

            ps.executeUpdate();

            System.out.println("Student added successfully");

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents(){

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try(Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {

                Student s = new Student();

                s.setId(rs.getInt("id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setEmail(rs.getString("email"));
                s.setDateOfBirth(rs.getDate("date_of_birth"));

                students.add(s);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public void updateStudent(int id,String email){

        String sql = "UPDATE students SET email=? WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1,email);
            ps.setInt(2,id);

            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id){

        String sql = "DELETE FROM students WHERE id=?";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1,id);
            ps.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}