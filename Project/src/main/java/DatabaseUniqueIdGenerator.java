import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUniqueIdGenerator {
    public static int generateId() {
        Connection connection = JdbcConnection.getJdbcConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select nextval('TEST_SEQUENCE') as NEXT_ID;");
            if (resultSet.next()) {
                int nextId = resultSet.getInt("NEXT_ID");
                System.out.println(nextId);
                return nextId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
