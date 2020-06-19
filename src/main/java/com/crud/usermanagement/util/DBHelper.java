package com.crud.usermanagement.util;

import com.crud.usermanagement.dao.UserHibernateDAO;
import com.crud.usermanagement.dao.UserJdbcDAO;
import com.crud.usermanagement.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static DBHelper INSTANCE;
    public static DBHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBHelper();
        }
        return INSTANCE;
    }
//    private static final String hibernate_show_sql = "true";
//    private static final String hibernate_hbm2ddl_auto = "update";
    public DBHelper() {
        sessionFactory = getSessionFactory();
    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Class.forName(UtilProperties.getPropertyValue("JDBC_DRIVER"));
            connection = DriverManager.getConnection(
                    UtilProperties.getPropertyValue("BASE"),
                    UtilProperties.getPropertyValue("BASE_USER"),
                    UtilProperties.getPropertyValue("BASE_PASSWORD"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //Непонятно зачем нужен метод
    public Configuration getConfiguration() {
        return DBHelper.getMySqlConfiguration();
    }


    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

//        configuration.addAnnotatedClass(UserJdbcDAO.class);
        configuration.addAnnotatedClass(UserHibernateDAO.class);

        configuration.setProperty("hibernate.dialect", UtilProperties.getPropertyValue("DIALECT"));
        configuration.setProperty("hibernate.connection.driver_class", UtilProperties.getPropertyValue("JDBC_DRIVER_HIBERNATE"));
        configuration.setProperty("hibernate.connection.url", UtilProperties.getPropertyValue("BASE"));
        configuration.setProperty("hibernate.connection.username", UtilProperties.getPropertyValue("BASE_USER"));
        configuration.setProperty("hibernate.connection.password", UtilProperties.getPropertyValue("BASE_PASSWORD"));
        configuration.setProperty("hibernate.show_sql", UtilProperties.getPropertyValue("SHOW_SQL"));
        configuration.setProperty("hibernate.hbm2ddl.auto", UtilProperties.getPropertyValue("hbm2ddl_auto"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

