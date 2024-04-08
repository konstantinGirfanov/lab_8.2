package org.example.DB;

import org.example.UserProfile;

import java.sql.*;

public class DbContext {

    private Connection connection;

    private void createConnection() {
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
        }
    }

    public void execUpdate(String query, Object... params) throws SQLException{
        createConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
        statement.executeUpdate();
    }

    public UserProfile execQuery(String query, Object... params) throws SQLException{
        createConnection();
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

        return null;
    }
}