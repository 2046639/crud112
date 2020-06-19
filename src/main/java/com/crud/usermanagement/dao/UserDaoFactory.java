package com.crud.usermanagement.dao;

import com.crud.usermanagement.util.UtilProperties;

public class UserDaoFactory {
    public static UserDAO createUserDAO() {
        String propertyValue = UtilProperties.getPropertyValue("DAO");
        switch (propertyValue) {
            case "JDBC":
                return UserJdbcDAO.getInstance();
            case "HIBERNATE":
                return UserHibernateDAO.getInstance();
            default:
                throw new IllegalArgumentException("unknown " + propertyValue);
        }
    }
}