package com.shopping.ecommerce.Exceptions.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {

		Class<?>[] groups() default {};

		Class<? extends Payload>[] payload() default {};

		String message() default "phone number must be of 10 digits and should start with either 6,7,8 or 9.";

}
