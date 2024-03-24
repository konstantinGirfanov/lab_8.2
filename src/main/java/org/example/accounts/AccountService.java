package org.example.accounts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.*;
public class AccountService {
    private static final Map<String, UserProfile> loginToProfile = new HashMap<String, UserProfile>()
    {
        {
            put("1", new UserProfile("1", "1", "1@1"));
        }
    };

    public static void AddNewUser(UserProfile user) throws IOException {
        loginToProfile.put(user.getLogin(), user);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

}