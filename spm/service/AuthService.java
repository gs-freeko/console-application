package spm.service;

import spm.data.DataStore;
import spm.model.*;

public class AuthService {

    public User login(String email, String password) {

        for (Admin a : DataStore.admins) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return a;
            }
        }

        for (Customer c : DataStore.customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                return c;
            }
        }

        return null;
    }
}