package com.dosoroBazar.register.service;

import com.dosoroBazar.register.dao.RegisterDao;
import com.dosoroBazar.register.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private RegisterDao registerDao;
    public String registerUser(User requestParameter){
    registerDao.saveDetails(requestParameter);
    return "Successfully Registered";
    }
}
