package com.tatto.bot.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class TattooDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    @NotNull
    @Size(min = 2, max = 20, message = "Name of Picture has to be in the range: 2 - 20")
    private String picture;
    private String url;
    private String description;
    private StyleDto style;
}
