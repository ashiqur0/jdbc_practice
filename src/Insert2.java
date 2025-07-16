import java.sql.*;

public class Insert2 {
    static String url = "jdbc:mysql://127.0.0.1/university";
    static String user = "root";
    static String pass = "root";

    public static void main(String[] args) throws Exception {
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement st = con.createStatement();

        int roll = 39;
        String name = "Ananto";
        String query = "insert into students values (" + roll + " , '" + name + "')";

        int count = st.executeUpdate(query);    // DML
        System.out.println((count > 0? "success" : "fail"));

        st.close();
        con.close();
    }
}
