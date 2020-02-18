package com.finalproject.quizsystem.config;

import com.finalproject.quizsystem.controller.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassconfValidator implements ConstraintValidator<PassConf, Object> {
    @Override
    public void initialize(PassConf constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) o;

        if(user != null && user.getPassword() != null
                && user.getPassword().length() > 0
                && user.getPassword().contentEquals(user.getPassconf())){
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context
                .getDefaultConstraintMessageTemplate()).addNode("passconf").addConstraintViolation();
        return false;
    }
}
