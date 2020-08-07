package com.tatto.bot.dto;

import com.tatto.bot.dto.interfaces.TattooInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TattooDto implements TattooInterface {
    @ApiModelProperty(hidden = true)
    private Long id;
    private String picture;
    private String url;
    private String description;
    @NotNull
    private TattooStyleDto style;
}
