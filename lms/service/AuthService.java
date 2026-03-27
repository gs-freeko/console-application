package lms.service;

import lms.model.User;
import lms.model.Admin;
import lms.data.DataStore;

public class AuthService {

    public User login(String email, String password) {

        for (Admin a : DataStore.getAdmins()) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return a;
            }
        }

        for (User u : DataStore.getUsers()) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }

        return null;
    }
}
