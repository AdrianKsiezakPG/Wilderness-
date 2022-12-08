import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {

    static Connection conn;

    private JdbcConnection() {
    }

    public static Connection getJdbcConnection() {
        try {
            if (conn == null) {
                Class.forName("org.h2.Driver");
                conn = DriverManager.getConnection("jdbc:h2:~/app", "sa", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection closeJdbcConnection() {
        return null;
    }
}
