package com.tago.api.common.annotation;


import com.tago.api.common.handler.PhoneNumberPatternValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberPatternValidator.class)
public @interface PhoneNumberPattern {
    String message() default "잘못된 형식의 휴대폰 번호입니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}
