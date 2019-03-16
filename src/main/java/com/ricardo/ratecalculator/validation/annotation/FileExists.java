package com.ricardo.ratecalculator.validation.annotation;

import com.ricardo.ratecalculator.validation.FileExistsValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileExistsValidator.class)
@Documented
public @interface FileExists {

    String message() default "File '${validatedValue}' does not exists";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
