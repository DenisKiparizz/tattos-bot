package com.tatto.bot.mapper;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.request.StyleRequest;
import com.tatto.bot.entity.Style;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StyleMapper {

    private final ModelMapper modelMapper;

    public StyleDto toDto(Style style) {
        return modelMapper.map(style, StyleDto.class);
    }

    public StyleDto setStyleRequestParam(StyleRequest styleRequest, Style style) {
        modelMapper.map(styleRequest, style);
        StyleDto styleDto = modelMapper.map(styleRequest, StyleDto.class);
        styleDto.setId(style.getId());
        return styleDto;
    }

    public Style toResource(StyleDto style) {
        return modelMapper.map(style, Style.class);
    }

    public List<StyleDto> mapListToDto(List<Style> styles) {
        return styles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
