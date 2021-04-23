package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionMaker {
    abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
