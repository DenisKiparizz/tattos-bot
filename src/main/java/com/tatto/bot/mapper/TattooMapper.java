package com.tatto.bot.mapper;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;
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
    public final StyleMapper styleMapper;

    public Tattoo toResource(TattooDto tattooDto) {
        Tattoo tattoos = modelMapper.map(tattooDto, Tattoo.class);
        tattoos.setStyles(styleMapper.toResource(tattooDto.getStyle()));
        return tattoos;
    }

    public TattooDto setTattooRequestParam(TattooRequest tattooRequest, Tattoo tattoo) {
        tattoo.setStyles(styleMapper.toResource(tattooRequest.getStyle()));
        modelMapper.map(tattooRequest, tattoo);
        TattooDto tattooDto = modelMapper.map(tattooRequest, TattooDto.class);
        tattooDto.setId(tattoo.getId());
        return tattooDto;
    }

    public TattooDto toDto(Tattoo tattoo) {
        TattooDto tattooDto = modelMapper.map(tattoo, TattooDto.class);
        tattooDto.setStyle(styleMapper.toDto(tattoo.getStyles()));
        return tattooDto;
    }

    public List<TattooDto> mapListToDto(List<Tattoo> tattooList) {
        return tattooList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
