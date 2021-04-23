package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IStatementStrategy {
    PreparedStatement makePreparedStatement(Object object, Connection connection) throws SQLException;
}
