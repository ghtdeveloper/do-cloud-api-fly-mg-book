package com.vortechgroup.queen.skies.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ActiveStatusValidator.class)
public @interface ActiveStatusValid {
   String message() default Constants.ACTIVE_STATUS_VALUE_OF_ENUM;
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default  {};
}