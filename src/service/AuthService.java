package service;

import data.DataStore;
import model.User;

public class AuthService {

    public static User login(String email, String password) {
        for (User u : DataStore.users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
