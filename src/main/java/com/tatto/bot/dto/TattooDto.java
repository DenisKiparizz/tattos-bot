package com.tatto.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TattooDto {
    private Long id;
    private String picture;
    private String url;
    private String description;
    private StyleDto style;
}
