package com.valko.SpringMusic.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SongFileValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SongFile {
    String message() default "File is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
