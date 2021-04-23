package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementStrategy implements IStatementStrategy {
    private Integer id;

    public DeleteStatementStrategy(Integer id){
        this.id = id;
    }


    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from userinfo where id = ?"
                );
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
