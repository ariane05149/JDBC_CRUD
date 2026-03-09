package dao;

import model.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void addCourse(Course course){

        String sql = "INSERT INTO courses(course_name,course_description) VALUES(?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,course.getCourseName());
            ps.setString(2,course.getDescription());

            ps.executeUpdate();

            System.out.println("Course added");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses(){

        List<Course> courses = new ArrayList<>();

        String sql="SELECT * FROM courses";

        try(Connection conn=DBConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql)){

            while(rs.next()){

                Course c=new Course();

                c.setId(rs.getInt("id"));
                c.setCourseName(rs.getString("course_name"));
                c.setDescription(rs.getString("course_description"));

                courses.add(c);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return courses;
    }
}