package com.tatto.bot.mapper;

import com.tatto.bot.dto.StyleDto;
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
public class TattooMapper {

    public final ModelMapper modelMapper;

    public Tattoo toResource(TattooDto tattoo) {
        StyleDto dto = tattoo.getStyle();
        Style styleDto = modelMapper.map(dto, Style.class);
        Tattoo tattoos = modelMapper.map(tattoo, Tattoo.class);
        tattoos.setStyles(styleDto);
        return tattoos;
    }

    public TattooDto toDto(Tattoo tattoo) {
        Style style = tattoo.getStyles();
        StyleDto styleDto = modelMapper.map(style, StyleDto.class);
        TattooDto tattooDto = modelMapper.map(tattoo, TattooDto.class);
        tattooDto.setStyle(styleDto);
        return tattooDto;
    }

    public List<TattooDto> mapListToDto(List<Tattoo> tattooList) {
        return tattooList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
