package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Integer id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return findById(sql, params);
    }

    public User findById(String sql, Object[] params) throws SQLException {
        return jdbcContext.JdbcContextFindbyId(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo(name,password) values (?,?) ";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        jdbcContext.JdbcContextInsert(user, connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < params.length; ++i) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name =?,password =? where id = ? ";
        Object[] params = new Object[]{user.getId(), user.getName(), user.getPassword()};
        jdbcContext.JdbcContextUpdate(connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                    return preparedStatement;
                }
        );
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.JdbcContextUpdate(connection -> {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(sql);
                    for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
                    return preparedStatement;
                }
        );
    }
}
