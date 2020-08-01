package com.tatto.bot.dto;

import com.tatto.bot.entity.prod.EStyle;
import io.swagger.annotations.ApiModelProperty;

public class TattooDto {
    @ApiModelProperty(hidden = true)
    private Long id;
    private EStyle style;
    private String description;

    public TattooDto() {
    }

    public TattooDto(Long id, EStyle style, String description) {
        this.id = id;
        this.style = style;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EStyle getStyle() {
        return style;
    }

    public void setStyle(EStyle style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
