package com.dosoroBazar.register.constraint;

import com.dosoroBazar.register.validator.RegisterValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = RegisterValidator.class)
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterConstraint {

    String message() default "Invalid Parameter in Register Module";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}