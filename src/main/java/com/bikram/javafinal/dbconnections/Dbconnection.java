package com.bikram.javafinal.dbconnections;

import com.bikram.javafinal.Models.GradeWiseData;
import com.bikram.javafinal.Models.SubjectWiseData;
import com.bikram.javafinal.Models.LoginResult;
import com.bikram.javafinal.Models.Students;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import  java.sql.*;

public class Dbconnection {
    static Statement stm;
    static Connection conn;


    public void connect() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String username = "user";
            String password = "pass";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafinal",username,password);
            stm = conn.createStatement();
            System.out.println("Connected");
        } catch (Exception e){
            System.out.println("Error while connecting to database. " + e.getMessage());
        }
    }
    public static LoginResult login(String username, String password) {
        try {
            // check if provided username and password are correct
            PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, password);
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()) {
                return new LoginResult(true,rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error with login. " + e.getMessage());
            return new LoginResult(false);
        }
        return  new LoginResult(false);
    }
    public static void signUp(String name,String username, String password) throws Exception{


        PreparedStatement pst = conn.prepareStatement("INSERT INTO Users(name,username,password) VALUES (?,?,?)");
        pst.setString(1,name);
        pst.setString(2,username);
        pst.setString(3,password);

        pst.executeUpdate();
    }
    public static void registerStudent(Students s) throws Exception {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Student(name,roll,maths,science,social,english,nepali) VALUES (?,?,?,?,?,?,?)");
        pst.setString(1,s.getName());
        pst.setInt(2,s.getRoll());
        pst.setInt(3,s.getMaths());
        pst.setInt(4,s.getScience());
        pst.setInt(5,s.getSocial());
        pst.setInt(6,s.getEnglish());
        pst.setInt(7,s.getNepali());
        pst.executeUpdate();
    }
    //    void getAllData() throws Exception{
    //        final String command = "SELECT * FROM Students";
    //        ResultSet rs =stm.executeQuery(command);
    //        while (rs.next()){
    //            System.out.println(rs.getString(1));
    //        }
    //    }
    public static ObservableList<Students> getAllStudents() throws Exception {

        ObservableList<Students> list = FXCollections.observableArrayList();
        Statement st = conn.createStatement();
        String statement = "SELECT * FROM student";
        ResultSet rs = st.executeQuery(statement);
        while (rs.next()) {
            list.add(new Students(rs.getInt("roll"),rs.getString("name"),rs.getInt("maths"),rs.getInt("science"),rs.getInt("social"),rs.getInt("english"),rs.getInt("nepali")));
        }
        return list;
    }
    public static Students getStudent( int roll) throws Exception {
        Statement st = conn.createStatement();
        String statement = "SELECT * FROM student where roll= " + Integer.toString(roll);
        ResultSet rs = st.executeQuery(statement);
        if (rs.next()) {
            return new Students(rs.getInt("roll"),rs.getString("name"),rs.getInt("maths"),rs.getInt("science"),rs.getInt("social"),rs.getInt("english"),rs.getInt("nepali"));
        } else return  null;
    }


    // Get Total Number of Students,
    public static Integer getTotalStudents() throws Exception {
        String statement = "SELECT COUNT(*) FROM student";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return rs.getInt(1);
    }
    //  Total Pass students assuming pass marks 40
    public static Integer getTotalPassStudents() throws Exception {
        String statement = "SELECT COUNT(*) FROM student WHERE maths >= 40 AND science >= 40 AND social >= 40 AND english >= 40 AND nepali >= 40";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return rs.getInt(1);
    }
    // Highest Percentage Scored
    public static Integer getHighestPercentage() throws Exception {
        String statement = "SELECT MAX(maths+science+social+english+nepali) FROM student";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return rs.getInt(1);
    }

    // Get separate average marks of each
    // subject
    public static SubjectWiseData getAverageMarks() throws Exception {
        String statement = "SELECT AVG(maths),AVG(science),AVG(social),AVG(english),AVG(nepali) FROM student";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return new SubjectWiseData(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
    }
    public static SubjectWiseData getHighestMarks() throws Exception {
        String statement = "SELECT MAX(maths),MAX(science),MAX(social),MAX(english),MAX(nepali) FROM student";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return new SubjectWiseData(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
    }
    public static SubjectWiseData getLowestMarks() throws Exception {
        String statement = "SELECT MIN(maths),MIN(science),MIN(social),MIN(english),MIN(nepali) FROM student";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        return new SubjectWiseData(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
    }
    // Number of students scoring A+, A, B+, B, C+, C and F Grade in maths
    public static GradeWiseData getGradeWiseMarks(String sub) throws Exception {

        String statement = "SELECT COUNT(*) FROM student WHERE "+sub+">= 90";
        ResultSet rs = stm.executeQuery(statement);
        rs.next();
        int ap = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 80 AND "+sub+"< 90";
        rs = stm.executeQuery(statement);
        rs.next();
        int a = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 70 AND "+sub+"< 80";
        rs = stm.executeQuery(statement);
        rs.next();
        int bp = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 60 AND "+sub+"< 70";
        rs = stm.executeQuery(statement);
        rs.next();
        int b = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 50 AND "+sub+"< 60";
        rs = stm.executeQuery(statement);
        rs.next();
        int cp = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 40 AND "+sub+"< 50";
        rs = stm.executeQuery(statement);
        rs.next();
        int c = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 20 AND "+sub+"< 40";
        rs = stm.executeQuery(statement);
        rs.next();
        int d = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+" >= 1 AND "+sub+"< 20";
        rs = stm.executeQuery(statement);
        rs.next();
        int e = rs.getInt(1);
        statement = "SELECT COUNT(*) FROM student WHERE "+sub+"<= 0";
        rs = stm.executeQuery(statement);
        rs.next();
        int n = rs.getInt(1);
        return new GradeWiseData(ap,a,bp,b,cp,c,d,e,n);
    }

}
