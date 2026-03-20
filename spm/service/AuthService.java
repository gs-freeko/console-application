package spm.service;

import spm.model.Admin;
import spm.model.Customer;
import spm.model.User;
import spm.data.DataStore;

public class AuthService {
    public User login(String email, String password){
        for(Admin admin : DataStore.getAdmins()){
            if(admin.getEmail().equals(email) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        for(Customer customer : DataStore.getCustomers()){
            if(customer.getEmail().equals(email) && customer.getPassword().equals(password)){
                return customer;
            }
        }
        return null;
    }
}
