package com.tatto.bot.exeptions;

public class IncorrectStyleException extends IllegalAccessError {
    public IncorrectStyleException(String style) {
        super(String.format("Style [%s] does not exist", style));
    }
}
