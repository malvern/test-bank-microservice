package zw.co.malvern.utils.validators;

import zw.co.malvern.utils.exceptions.AgeLimitException;
import zw.co.malvern.utils.exceptions.ValidationException;

public class CustomValidators {
    //TODO refactor to use functional interface
    public static void validateStringAndThrow(String input){
        if(input == null || input.isEmpty())
            throw new ValidationException("Field cannot be empty or null");
    }

    public static void validateAgeLimit(Long age){
        if(age< 18)
            throw new AgeLimitException("account holder below 18 are not allowed to create account");
    }

}
