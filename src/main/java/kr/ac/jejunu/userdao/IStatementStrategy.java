package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IStatementStrategy {
    PreparedStatement makePreparedStatement(Connection connection) throws SQLException;
}
