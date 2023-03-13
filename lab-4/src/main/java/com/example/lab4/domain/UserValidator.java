package com.example.lab4.domain;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User user) throws ValidationException {
            if(user.getFirstName() == null || user.getLastName() == null)
                throw new ValidationException("Numele nu poate fi gol");
        }
}
