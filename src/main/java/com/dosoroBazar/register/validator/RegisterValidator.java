package com.dosoroBazar.register.validator;

import com.dosoroBazar.register.constraint.RegisterConstraint;
import com.dosoroBazar.register.dao.RegisterDao;
import com.dosoroBazar.register.model.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterValidator implements ConstraintValidator<RegisterConstraint, User> {


    @Autowired
    private RegisterDao registerDao;
    @Override
    public void initialize(RegisterConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(User requestParameter, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (requestParameter.getFirstName() == null || requestParameter.getFirstName().isEmpty()) {
            addConstraintViolation( "First name cannot be empty", context);
            isValid = false;
        }


        if (requestParameter.getLastName() == null || requestParameter.getLastName().isEmpty()) {
            addConstraintViolation( "Last name cannot be empty", context);
            isValid = false;
        }

        if (requestParameter.getEmailId() == null || requestParameter.getEmailId().isEmpty()) {
            addConstraintViolation( "Email ID cannot be empty", context);
            isValid = false;
        }

        if (requestParameter.getPassword() == null || requestParameter.getPassword().isEmpty()) {
            addConstraintViolation( "Password cannot be empty", context);
            isValid = false;
        }

        if (requestParameter.getPassword().contains(requestParameter.getFirstName())){
            addConstraintViolation("Name cannot be added as Password",context);
            isValid = false;
        }

        if(requestParameter.getFirstName().length() >=10){
            addConstraintViolation("FirstName Should be less than 10 character",context);
            isValid = false;
        }

        if(requestParameter.getLastName().length() >=10){
            addConstraintViolation("LastName Should be less than 10 character",context);
            isValid = false;
        }
        if (containsInvalidCharacters(requestParameter.getFirstName())) {
            addConstraintViolation( "First name contains invalid characters (=, <, >)", context);
            isValid = false;
        }
        if (containsInvalidCharacters(requestParameter.getLastName())) {
            addConstraintViolation( "Last name contains invalid characters (=, <, >)", context);
            isValid = false;
        }
        if (containsInvalidCharacters(requestParameter.getEmailId())) {
            addConstraintViolation( "EmailId contains invalid characters (=, <, >)", context);
            isValid = false;
        }
        if (containsInvalidCharacters(requestParameter.getPassword())) {
            addConstraintViolation( "Password contains invalid characters (=, <, >)", context);
            isValid = false;
        }
        if(requestParameter.getPassword().length() <= 5){
            addConstraintViolation("Password Should not be less than 5 character",context);
            isValid = false;
        }
        if(alreadyExistPhoneNo(requestParameter.getPhoneNo()))
        {
            addConstraintViolation("Phone Number Already exists",context);
            isValid = false;
        }

        if(alreadyExistEmailId(requestParameter.getEmailId()))
        {
            addConstraintViolation("EmailId Already exists",context);
            isValid = false;
        }

        return isValid;
    }

    private boolean containsInvalidCharacters(String field) {
        return field != null && (field.contains("=") || field.contains("<") || field.contains(">"));
    }

    private boolean alreadyExistPhoneNo(String phoneNumber){
    boolean alreadyExist  =  registerDao.getUserPhoneNoDetails(phoneNumber);
    return  alreadyExist;
    }
    private boolean alreadyExistEmailId(String emailId){
        boolean alreadyExist  =  registerDao.getUserEmailIdDetails(emailId);
        return  alreadyExist;
    }
    private void addConstraintViolation(String message, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}
