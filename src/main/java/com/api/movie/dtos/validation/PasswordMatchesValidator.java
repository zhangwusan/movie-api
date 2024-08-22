package com.api.movie.dtos.validation;

import com.api.movie.dtos.request.UserRegisterRequest;
import com.api.movie.dtos.validation.annotation.PasswordMatches;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegisterRequest> {

    @Override
    public boolean isValid(UserRegisterRequest value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getConfirmPassword());
    }
    
} 
