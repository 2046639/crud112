package com.crud.usermanagement.service;

import com.crud.usermanagement.dao.UserHibernateDAO;
import com.crud.usermanagement.dao.UserJdbcDAO;
import com.crud.usermanagement.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserJdbcService {

//    private final UserJdbcDAO dao = UserJdbcDAO.getInstance();

    private final UserHibernateDAO dao = UserHibernateDAO.getInstance();

    private UserJdbcService() {
    }

    public static UserJdbcService INSTANCE;

    public static UserJdbcService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJdbcService();
        }
        return INSTANCE;
    }


    public List<User> selectAllUsers() throws SQLException {
        return dao.selectAllUsers();
    }

    public User selectUser(int id) throws SQLException {
        return dao.selectUser(id);
    }

    public void insertUser(User user) throws SQLException {
        dao.insertUser(user);
    }

    public boolean updateUser(User user) throws SQLException {
        return dao.updateUser(user);
    }

    public boolean deleteUser(int id) throws SQLException {
        return dao.deleteUser(id);
    }
}
