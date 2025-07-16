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

import java.sql.*;  // 1

public class Fetch {    
    static final String url = "jdbc:mysql://127.0.0.1/university";
    static final String user_name = "root";
    static final String password = "root";
    public static void main(String[] args) throws Exception {
        
        Class.forName("com.mysql.cj.jdbc.Driver");  //2
        Connection con = DriverManager.getConnection(url, user_name, password);  //3
        Statement st = con.createStatement(); //4

        String query = "SELECT * FROM UNIVERSITY.STUDENTS";
        ResultSet rs = st.executeQuery(query);    //5    // DML

        System.out.println("Roll" + "\t" +  "Name");
        while (rs.next()) {
            int roll = rs.getInt(1); //6
            String name = rs.getString(2);
            System.out.println(roll + "\t" + name);
        }
        
        st.close();  //7
        con.close();
    }
}
