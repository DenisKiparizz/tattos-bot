package com.tatto.bot.mapper;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.entity.prod.Tattoo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TattooMapper {

    @Autowired
    ModelMapper modelMapper;

    public Tattoo toResource(TattooDto tattoo) {
        return modelMapper.map(tattoo, Tattoo.class);
    }

    public TattooDto toDto(Tattoo tattoo) {
        return modelMapper.map(tattoo, TattooDto.class);
    }

    public List<TattooDto> mapListToDto(List<Tattoo> tattooList) {
        return tattooList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
