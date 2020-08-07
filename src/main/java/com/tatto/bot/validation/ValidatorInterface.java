package com.tatto.bot.validation;

public interface ValidatorInterface<T> {
    void validate(T t);

    void checkPositiveId(Long id);

    boolean isOnlyLetter(String name);
}
