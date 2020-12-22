import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnetion {

    static public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/copy";
        String user = "root";
        String pass = "blank";
        Connection conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }
}
