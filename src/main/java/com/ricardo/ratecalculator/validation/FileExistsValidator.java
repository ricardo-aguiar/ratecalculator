package com.ricardo.ratecalculator.validation;

import com.ricardo.ratecalculator.validation.annotation.FileExists;
import java.io.File;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileExistsValidator implements ConstraintValidator<FileExists, File> {

    @Override
    public boolean isValid(File value, ConstraintValidatorContext context) {
        return value != null && (value.exists() || value.isFile());
    }
}
