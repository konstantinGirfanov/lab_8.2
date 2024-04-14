package org.example.DB;

import org.example.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.sql.*;

public class DbContext {
    public Configuration configuration;
    private SessionFactory sessionFactory;
    public DbContext(){
        this.configuration = new Configuration();
        this.configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        this.configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        this.configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/test");
        this.configuration.setProperty("hibernate.connection.username", "postgres");
        this.configuration.setProperty("hibernate.connection.password", "root");
        this.configuration.setProperty("hibernate.show_sql", "true");
        this.configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        this.configuration.addAnnotatedClass(UserProfile.class);
        createSessionFactory();
    }

    public void execUpdate(UserProfile user){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public UserProfile execQuery(String login){
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserProfile.class);
        UserProfile user = (UserProfile) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        session.close();
        return user;
    }

    public void createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(this.configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
}