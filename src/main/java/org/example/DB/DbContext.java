package org.example.DB;

import org.example.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class DbContext {
    public Configuration configuration;
    private SessionFactory sessionFactory;
    private Connection connection;

    private void createConnection() {
        /*
        if (connection == null) {
            try {
                DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
                String url = "jdbc:postgresql://localhost:5432/test";
                String username = "postgres";
                String password = "root";
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/

        this.configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/test");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        configuration.addAnnotatedClass(UserProfile.class);
    }

    public void execUpdate(String query, Object... params) throws SQLException{
        /*createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        statement.executeUpdate();*/

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public UserProfile execQuery(String query, Object... params) throws SQLException{
        /*createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String  login = result.getString("login");
            String  pass = result.getString("password");
            String  email = result.getString("email");
            return new UserProfile(login, pass, email);
        }

        return null;*/


    }

    public SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}