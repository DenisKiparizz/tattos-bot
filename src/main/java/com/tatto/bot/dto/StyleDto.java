package com.tatto.bot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tatto.bot.entity.enums.EStyle;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleDto {
    private Long id;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private EStyle style;
}
