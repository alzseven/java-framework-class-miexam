package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementStrategy implements IStatementStrategy{

    @Override
    public PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException {
        User user = (User) object;
        PreparedStatement preparedStatement = connection.prepareStatement("update userinfo set name =?,password =? where id = ? ");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        return preparedStatement;
    }
}
