import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultDao {

    public void create(ResultEntity resultEntity) {
        Connection connection = JdbcConnection.getJdbcConnection();
        try {
            int generateId = DatabaseUniqueIdGenerator.generateId();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO RESULT (ID,LEVEL,TIME,DATA) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, generateId);
            preparedStatement.setInt(1, resultEntity.getLevel());
            preparedStatement.setInt(3, resultEntity.getTime());
            preparedStatement.setDate(4, resultEntity.getData());
            preparedStatement.execute();

            resultEntity.setId(generateId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
