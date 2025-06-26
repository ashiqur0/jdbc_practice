/*
JDBC: Java Database Connectivity

There are 7 steps to connect Java program to Databse,
1. Import the packages,
2. Loading / Registering JDBC Driver
3. Create and Open Connection
4. Create a Statement
5. Execute a SQL Query
6. Extract Data from ResultSet
7. Cleanup the Environment

*/

import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {   // either throws exception or try catch
        String url = "jdbc:mysql://127.0.0.1:3306/university";
        String user_name = "root";
        String password = "root";
        String query = "SELECT * FROM university.students";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user_name, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("Roll\tName");
        while(rs.next()) {
            int roll = rs.getInt(1);
            String name = rs.getString(2);
            System.out.println(roll + "\t" + name);
        }

        st.close();
        con.close();
    }
}
