package com.imooc.web.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    @Override
    public void initialize(MyConstraint myConstraint) {
       log.info ("My validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        log.info ("Object : {} , Context : {}", o,context);
        return false;
    }


}
