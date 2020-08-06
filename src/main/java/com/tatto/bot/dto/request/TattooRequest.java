package com.tatto.bot.dto.request;

import com.tatto.bot.dto.StyleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TattooRequest {
    @NotNull
    @Size(min = 2, max = 20, message = "Name of Picture has to be in the range: 2 - 20")
    private String picture;
    private String description;
    private String url;
    private StyleDto style;
}
