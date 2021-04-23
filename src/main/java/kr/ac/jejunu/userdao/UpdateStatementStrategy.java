package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementStrategy implements IStatementStrategy{
    private User user;

    public UpdateStatementStrategy(User user){
        this.user = user;
    }

    @Override
    public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name =?,password =? where id = ? ");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement;
    }
}
