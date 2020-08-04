package com.tatto.bot.exeptions;

public class TattooNotFoundException extends RuntimeException {
    public TattooNotFoundException(Long id) {
        super(String.format("Picture with id = [%s] not found", id));
    }
}
