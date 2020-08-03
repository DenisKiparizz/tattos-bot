package com.tatto.bot.mapper;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.StyleRequest;
import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.entity.Style;
import com.tatto.bot.entity.Tattoo;
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

    public Style toResource(StyleRequest style) {
        return modelMapper.map(style, Style.class);
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
