package net.rhuanrocha.customconstraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenValidator implements ConstraintValidator<Even, Long> {
    @Override
    public void initialize(Even constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long number, ConstraintValidatorContext constraintValidatorContext) {

        if(number == null){
            return true;
        }
        return (number%2) == 0;

    }
}
