package dao;

import util.DBConnection;

import java.sql.*;

public class MarkDAO {

    public void addMark(int studentId,int courseId,float marks){

        String sql="INSERT INTO marks(student_id,course_id,marks) VALUES(?,?,?)";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1,studentId);
            ps.setInt(2,courseId);
            ps.setFloat(3,marks);

            ps.executeUpdate();

            System.out.println("Mark added");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateMark(int studentId,int courseId,float marks){

        String sql="UPDATE marks SET marks=? WHERE student_id=? AND course_id=?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setFloat(1,marks);
            ps.setInt(2,studentId);
            ps.setInt(3,courseId);

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteMark(int studentId,int courseId){

        String sql="DELETE FROM marks WHERE student_id=? AND course_id=?";

        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){

            ps.setInt(1,studentId);
            ps.setInt(2,courseId);

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}