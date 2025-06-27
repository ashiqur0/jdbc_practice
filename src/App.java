/*
JDBC: Java Database Connectivity

There are 7 steps to connect Java program to Databse,
1. Import the packages,
2. Load and Register JDBC Driver
3. Establish the Connection
4. Create a Statement
5. Execute a SQL Query
6. Extract Data from ResultSet
7. Cleanup the Environment
*/

import java.sql.*;

public class App {
    static final String url = "jdbc:mysql://127.0.0.1/university";
    static final String user_name = "root";
    static final String password = "root";

    public static void main() throws Exception {
        String query = "SELECT * FROM UNIVERSITY.STUDENTS";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(query, user_name, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("Roll" + "\t" +  "Name");
        while (resultSet.next()) {
            int roll = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(roll + "\t" + name);
        }
        
        statement.close();
        connection.close();
    }
}
