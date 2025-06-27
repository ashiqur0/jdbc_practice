import java.sql.*;

class b {
    static final String url = "jdbc:mysql://127.0.0.1/university";
    static final String user = "root";
    static final String password = "root";
    
    public static void main(String[] args) throws Exception {
        String query = "select * from university.students";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        System.out.println("Roll\t" + "Name");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));
        }

        statement.close();
        connection.close();
    }
}