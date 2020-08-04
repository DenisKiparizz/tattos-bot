package com.tatto.bot.exeptions;

public class WrongModelException extends IllegalAccessError {
    public WrongModelException(String error) {
        super(String.format("Field consider some mistake: %s ", error));
    }}
