import java.sql.*;

public class Insert3 {
    static String url = "jdbc:mysql://127.0.0.1/university";
    static String user = "root";
    static String pass = "root";

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pass);

        int roll = 48;
        String name = "Tanzid";
        String query = "insert into students values (?, ?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, roll);
        ps.setString(2, name);

        int count = ps.executeUpdate();    // DML
        System.out.println((count > 0? "success" : "fail"));

        ps.close();
        con.close();
    }
}
