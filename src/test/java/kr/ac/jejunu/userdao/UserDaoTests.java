package kr.ac.jejunu.userdao;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao;

    @BeforeAll
    static void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean(UserDao.class);
    }

    @Test
    public void testGet() throws SQLException {
        Integer id = 1;
        String name = "JHP";
        String password = "JHPPW";

        User user = userDao.findById(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException {
        String name = "JHP";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        User insertedUser = userDao.findById(user.getId());

        assertThat(insertedUser.getId(), greaterThan(0));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException {
        String name = "JHP";
        String password = "1234";
        String newName = "PJH";
        String newPassword = "5678";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);

        User insertedUser = userDao.findById(user.getId());
        insertedUser.setName(newName);
        insertedUser.setPassword(newPassword);
        userDao.update(insertedUser);

        assertThat(insertedUser.getId(), greaterThan(0));
        assertThat(insertedUser.getName(), is(newName));
        assertThat(insertedUser.getPassword(), is(newPassword));
    }

    @Test
    public void delete() throws SQLException {
        String name = "JHP";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userDao.insert(user);
        User insertedUser = userDao.findById(user.getId());

        userDao.delete(insertedUser.getId());
        User deletedUser = userDao.findById(insertedUser.getId());

        assertThat(deletedUser, nullValue());
    }

}