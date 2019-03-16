package com.ricardo.ratecalculator.validation.annotation;

import com.ricardo.ratecalculator.validation.ValidLoanAmountIntervalValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidLoanAmountIntervalValidator.class)
@Documented
public @interface ValidLoanAmountInterval {

    String message() default "The loan amount '${validatedValue}' must be in increment interval of 100";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}