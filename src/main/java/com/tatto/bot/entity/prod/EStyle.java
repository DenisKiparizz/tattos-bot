package com.tatto.bot.entity.prod;

public enum EStyle {
    JAPAN("JAPAN"),
    HARDCORE("HARDCORE"),
    REALISM("REALISM"),
    OLD_SCHOOL("OLD_SCHOOL");

    private final String style;

    EStyle(String code) {
        this.style = code;
    }

    public String getStyle() {
        return style;
    }
}
