package com.example.lab4.domain;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
