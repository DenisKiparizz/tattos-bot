package com.tatto.bot.dto;

import com.tatto.bot.dto.interfaces.StyleInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TattooStyleDto implements StyleInterface {
    private Long id;
    @ApiModelProperty(hidden = true)
    private String style;
}
