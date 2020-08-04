package com.tatto.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StyleRequest {
    @NotNull
    @Size(min = 2, max = 12, message = "Name of Style has to be in the range: 2 - 12")
    private String style;
}
