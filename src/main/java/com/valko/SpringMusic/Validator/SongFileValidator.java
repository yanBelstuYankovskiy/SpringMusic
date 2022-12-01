package com.valko.SpringMusic.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SongFileValidator implements ConstraintValidator<SongFile, String> {

    @Override
    public void initialize(SongFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.toLowerCase().endsWith(".mp3")){
            return true;
        }else{
            return false;
        }
    }
}
