/*
JDBC : Retrieve Data From MySql Database

*/

import java.sql.*;

public class Retrieve {
    static final String url = "jdbc:mysql://127.0.0.1/university";
    static final String user_name = "root";
    static final String password = "root";

    public static void main(String[] args) throws Exception {
        String query = "SELECT * FROM UNIVERSITY.STUDENTS";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user_name, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
        }

        statement.close();
        connection.close();
    }
}
