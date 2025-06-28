package com.amankrmj.portfolio_backend.services;

public abstract class AbstractValidator<T> {
    public abstract T validate(String value);
}
