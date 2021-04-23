package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("insert into userinfo(name,password) values (?,?) ",
                            Statement.RETURN_GENERATED_KEYS
                    );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
            resultSet.close();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("update userinfo set name =?,password =? where id = ? ");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("delete from userinfo where id = ?"
                    );
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
