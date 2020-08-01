package com.tatto.bot.dto;

import com.tatto.bot.entity.prod.EStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TattooDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private EStyle style;
    private String description;
}
