package com.example.freshfoods.annotation;

import com.example.freshfoods.validator.StrongPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = StrongPasswordValidator.class)
public @interface StrongPassword {
    String message() default "{StrongPassword}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
