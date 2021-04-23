package kr.ac.jejunu.userdao;

public class DaoFactory {
    public UserDao getUserDao(){
        return new UserDao(getConnectionMaker());
    }

    public IConnectionMaker getConnectionMaker(){
        return new JejuConnectionMaker();
    }
}
