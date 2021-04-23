package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao{
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection =
                DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC"
                        , "jeju", "jejupw");
        return connection;
    }
}
