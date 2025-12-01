package com.productservice.demo.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {
    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        if (value == null) return true;

        System.out.println("RUNNING VALIDATIONNNN");

        return value.getPassword() != null && value.getPassword() == value.getConfirmPassword();
    }
    
}
