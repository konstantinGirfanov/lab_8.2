package org.example.accounts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.*;
import java.sql.*;
import org.example.DB.*;

public class AccountService {
    private DbContext db = new DbContext();

    public void AddNewUser(UserProfile user) {
        db.execUpdate(user);
    }

    public UserProfile getUserByLogin(String login){
        return db.execQuery(login);
    }
}