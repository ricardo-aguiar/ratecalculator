package com.ricardo.ratecalculator.validation;

import com.ricardo.ratecalculator.RateCalculatorApp;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ParameterValidator {

    private final RateCalculatorApp applicationContext;

    public ParameterValidator(RateCalculatorApp applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Performs Bean validation for input parameters using hibernate-validator
     * @return if there are no violation, returns an empty list, otherwise, a list of {@code String} containing violation messages.
     */
    public List<String> validate() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RateCalculatorApp>> violations = validator.validate(applicationContext);

        return violations.stream()
                         .map(ConstraintViolation::getMessage)
                         .collect(Collectors.toList());
    }
}
