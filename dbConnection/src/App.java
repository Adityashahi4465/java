import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String dbUrl = "jdbc:oracle:thin:@localhost:1521/orclpdb";
        String username = "hr";
        String password = "hr";

        try {
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connected to the database");

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet resultSet = statement.executeQuery(sql);

            // Get ResultSet metadata to determine column count and names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column headers
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-15s", metaData.getColumnName(i));
            }
            System.out.println();

            // Print rows in tabular format
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.printf("%-15s", resultSet.getString(i));
                }
                System.out.println();
            }

            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
