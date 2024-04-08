package org.example.accounts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.*;
import java.sql.*;
import org.example.DB.*;

public class AccountService {

    private DbContext db = new DbContext();

    public void AddNewUser(UserProfile user){
        String query = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
        try {
            db.execUpdate(query, user.getLogin(), user.getPass(), user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login){
        String query = "SELECT * FROM users WHERE login = ?";
        UserProfile user = null;
        try {
            user = db.execQuery(query, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}