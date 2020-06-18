package com.crud.usermanagement.dao;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO<T> {
    void insertUser(T t) throws SQLException;

    List<T> selectAllUsers();

    boolean updateUser(T t) throws SQLException;

    boolean deleteUser(int t) throws SQLException;

    T selectUser(int t);
}
