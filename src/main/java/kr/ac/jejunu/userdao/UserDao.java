package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Integer id) throws SQLException {
        FindByIdStatementStrategy findByIdStatementStrategy = new FindByIdStatementStrategy(id);
        return jdbcContext.JdbcContextFindbyId(findByIdStatementStrategy);
    }

    public void insert(User user) throws SQLException {
        InsertStatementStrategy insertStatementStrategy = new InsertStatementStrategy(user);
        jdbcContext.JdbcContextInsert(user, insertStatementStrategy);
    }

    public void update(User user) throws SQLException {
        UpdateStatementStrategy updateStatementStrategy =  new UpdateStatementStrategy(user);
        jdbcContext.JdbcContextUpdate(updateStatementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        DeleteStatementStrategy deleteStatementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.JdbcContextUpdate(deleteStatementStrategy);
    }
}
