/*
JDBC: with MySQL Database

There are 7 steps to connect Java program to MySQL Databse,
1. Import the packages,
2. Load and Register JDBC Driver
3. Establish the Connection
4. Create a Statement
5. Execute a SQL Query
6. Extract Data from ResultSet
7. Cleanup the Environment
*/


import java.sql.*;

class Student {
    int roll;
    String name;
}

class Student_DAO {

    Connection con = null;

    public Student_DAO() {        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/university", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public void connect() {
    //     try {
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/university", "root", "root");
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }
    
    public Student getStudent(int roll) {        
        try {
            Student s = new Student();
            s.roll = roll;
            
            Statement st = con.createStatement();
            String query = "SELECT * FROM UNIVERSITY.STUDENTS where roll =" +roll;
            ResultSet rs = st.executeQuery(query);

            rs.next();
            s.roll = rs.getInt(1);
            s.name = rs.getString(2);

            st.close();
            con.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addStudent(Student s) {
        String query = "insert into university.students values(?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, s.roll);
            ps.setString(2, s.name);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeStudent(int roll) {
        String query = "DELETE FROM students WHERE roll = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, roll);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateStudent(Student s) {
        String query = "UPDATE students SET name = ? WHERE roll = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(2, s.roll);
            ps.setString(1, s.name);
            ps.executeUpdate();

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showStudents() {
        try {
            Statement st = con.createStatement();
            String query = "SELECT * FROM UNIVERSITY.STUDENTS";
            ResultSet rs = st.executeQuery(query);
    
            System.out.println("Roll" + "\tName");
            while (rs.next()) {
                int roll = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println(roll + "\t" + name);
            }

            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class JDBC {    

    public static void main(String[] args) throws Exception {
        Student_DAO dao = new Student_DAO();
        // dao.connect();// connect() method code I place in Student_DAO() constructor
        
        // // Put Data
        // Student s = new Student();
        // s.roll = 65;
        // s.name = "Dipto";
        // dao.addStudent(s);
        
        // // Get Data
        // Student s = dao.getStudent(16);
        // System.out.println(s.roll + " " + s.name);

        // // Update Data
        // Student s = new Student();
        // s.roll = 48;
        // s.name = "Tanzid";
        // dao.updateStudent(s);

        // // Remove Data
        // dao.removeStudent(57);
                
        // Fetch Data
        dao.showStudents();
    }
}