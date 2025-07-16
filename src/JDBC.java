import java.sql.*;

class Student {
    int roll;
    String name;
}

class Student_DAO {

    Connection con = null;
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/university", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showStudent() {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class JDBC {    

    public static void main(String[] args) throws Exception {
        Student_DAO dao = new Student_DAO();
        
        // Put Data
        dao.connect();
        Student s = new Student();
        s.roll = 65;
        s.name = "Dipto";
        dao.addStudent(s);
        
        // Fetch Data
        dao.connect();
        dao.showStudent();
        
        // Get Data
        // Student s = dao.getStudent(16);
        // System.out.println(s.roll + " " + s.name);

        // // Update Data
        // Student s = new Student();
        // s.roll = 48;
        // s.name = "Tanzid";
        // dao.connect();
        // dao.updateStudent(s);

        // // Remove Data
        // dao.connect();
        // dao.removeStudent(57);
    }
}