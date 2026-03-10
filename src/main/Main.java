package main;
import util.DBSetup;

import dao.CourseDAO;
import dao.MarkDAO;
import dao.StudentDAO;
import model.Course;
import model.Student;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DBSetup.createTables();

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        MarkDAO markDAO = new MarkDAO();

        char choice;

        do {

            System.out.println("\n-----STUDENT MANAGEMENT SYSTEM-------");

            System.out.println("a. Add Student");
            System.out.println("b. View All Students");
            System.out.println("c. Update Student");
            System.out.println("d. Delete Student");

            System.out.println("e. Add Course");
            System.out.println("f. Get All Courses");
            System.out.println("g. View Course");
            System.out.println("h. Update Course");
            System.out.println("i. Delete Course");

            System.out.println("j. Add Mark");
            System.out.println("k. View Student Marks");
            System.out.println("l. Update Student Mark");
            System.out.println("m. Delete Student Mark");

            System.out.println("n. Exit");

            System.out.print("Enter choice: ");

            choice = sc.next().charAt(0);

            switch(choice){

                case 'a':

                    sc.nextLine();

                    System.out.print("First Name: ");
                    String fn = sc.nextLine();

                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Date of Birth (yyyy-mm-dd): ");
                    String dob = sc.nextLine();

                    Student student = new Student(fn, ln, email, Date.valueOf(dob));

                    studentDAO.addStudent(student);

                    break;

                case 'b':

                    List<Student> students = studentDAO.getAllStudents();

                    for(Student s : students){

                        System.out.println(
                                s.getId()+" "+
                                        s.getFirstName()+" "+
                                        s.getLastName()+" "+
                                        s.getEmail()
                        );
                    }

                    break;

                case 'c':

                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();

                    sc.nextLine();

                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();

                    studentDAO.updateStudent(sid,newEmail);

                    break;

                case 'd':

                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();

                    studentDAO.deleteStudent(deleteId);

                    break;

                case 'e':

                    sc.nextLine();

                    System.out.print("Course Name: ");
                    String cname = sc.nextLine();

                    System.out.print("Course Description: ");
                    String cdesc = sc.nextLine();

                    Course course = new Course(cname,cdesc);

                    courseDAO.addCourse(course);

                    break;

                case 'f':

                    List<Course> courses = courseDAO.getAllCourses();

                    for(Course c : courses){

                        System.out.println(
                                c.getId()+" "+
                                        c.getCourseName()+" "+
                                        c.getDescription()
                        );
                    }

                    break;

                case 'j':

                    System.out.print("Student ID: ");
                    int studentId = sc.nextInt();

                    System.out.print("Course ID: ");
                    int courseId = sc.nextInt();

                    System.out.print("Marks: ");
                    float marks = sc.nextFloat();

                    markDAO.addMark(studentId,courseId,marks);

                    break;

                case 'l':

                    System.out.print("Student ID: ");
                    int sid2 = sc.nextInt();

                    System.out.print("Course ID: ");
                    int cid2 = sc.nextInt();

                    System.out.print("New Marks: ");
                    float newMarks = sc.nextFloat();

                    markDAO.updateMark(sid2,cid2,newMarks);

                    break;

                case 'm':

                    System.out.print("Student ID: ");
                    int sid3 = sc.nextInt();

                    System.out.print("Course ID: ");
                    int cid3 = sc.nextInt();

                    markDAO.deleteMark(sid3,cid3);

                    break;

            }

        }while(choice!='n');

        System.out.println("Program Closed");

    }
}