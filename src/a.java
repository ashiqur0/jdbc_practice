import java.sql.*;
import java.util.Scanner;

public class a {
    static final String URL = "jdbc:mysql://127.0.0.1:3306/university";
    static final String USER_NAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create (Insert)");
            System.out.println("2. Read (Retrieve)");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    create(con, sc);
                    break;
                case 2:
                    read(con);
                    break;
                case 3:
                    update(con, sc);
                    break;
                case 4:
                    delete(con, sc);
                    break;
                case 5:
                    con.close();
                    sc.close();
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Create (Insert) operation
    public static void create(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter roll: ");
        int roll = sc.nextInt();
        sc.nextLine();                      // Consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        String insertQuery = "INSERT INTO students (roll, name) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(insertQuery);
        ps.setInt(1, roll);
        ps.setString(2, name);

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Record inserted successfully.");
        }
        ps.close();
    }

    // Read (Retrieve) operation
    public static void read(Connection con) throws SQLException {
        String selectQuery = "SELECT * FROM students";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(selectQuery);

        System.out.println("Roll\tName");
        while (rs.next()) {
            int roll = rs.getInt("roll");
            String name = rs.getString("name");
            System.out.println(roll + "\t" + name);
        }
        rs.close();
        st.close();
    }

    // Update operation
    public static void update(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter roll of the student to update: ");
        int roll = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        String updateQuery = "UPDATE students SET name = ? WHERE roll = ?";
        PreparedStatement ps = con.prepareStatement(updateQuery);
        ps.setString(1, newName);
        ps.setInt(2, roll);

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("No record found with the given roll.");
        }
        ps.close();
    }

    // Delete operation
    public static void delete(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter roll of the student to delete: ");
        int roll = sc.nextInt();

        String deleteQuery = "DELETE FROM students WHERE roll = ?";
        PreparedStatement ps = con.prepareStatement(deleteQuery);
        ps.setInt(1, roll);

        int rowsDeleted = ps.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Record deleted successfully.");
        } else {
            System.out.println("No record found with the given roll.");
        }
        ps.close();
    }
}
