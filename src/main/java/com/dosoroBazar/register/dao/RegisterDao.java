package com.dosoroBazar.register.dao;

import com.dosoroBazar.register.model.User;


public interface RegisterDao {
    boolean getUserPhoneNoDetails(String phoneNumber);

    boolean getUserEmailIdDetails(String emailId);
    void saveDetails(User user);
}
