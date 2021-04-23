package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByIdStatementStrategy implements IStatementStrategy {
    private Integer id;

    public FindByIdStatementStrategy(Integer id){
        this.id = id;
    }


    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
