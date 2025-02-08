package com.vortechgroup.queen.skies.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
public class ActiveStatusValidator implements ConstraintValidator<ActiveStatusValid, Character> {
    @Override
    public void initialize(@NotNull ActiveStatusValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Character value, ConstraintValidatorContext constraintValidatorContext) {
        return value.equals('A') || value.equals('I');
    }
}