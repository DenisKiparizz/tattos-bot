package com.tatto.bot.service;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.entity.Tattoo;
import com.tatto.bot.exeptions.TattooNotFoundException;
import com.tatto.bot.mapper.TattooMapper;
import com.tatto.bot.repository.TattooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TattooServiceImpl {

    public final TattooRepository tattooRepository;
    public final TattooMapper mapper;

    public TattooDto create(TattooDto tattooDto) {
        Tattoo tattoo = mapper.toResource(tattooDto);
        Tattoo save = tattooRepository.save(tattoo);
        return mapper.toDto(save);
    }

    public List<TattooDto> findByStyle(String style) {
        return mapper.mapListToDto(tattooRepository.findAll().stream()
                .filter(tattoo ->
                        tattoo.getStyles()
                                .getStyle().equalsIgnoreCase(style))
                .collect(Collectors.toList()));
    }

    public List<TattooDto> findByPicture(String picture) {
        return mapper.mapListToDto(tattooRepository.findAll().stream()
                .filter(tattoo ->
                        tattoo.getPicture().equalsIgnoreCase(picture))
                .collect(Collectors.toList()));
    }

    public List<TattooDto> getAll() {
        return mapper.mapListToDto(tattooRepository.findAll());
    }

    public void delete(Long id) {
        tattooRepository.deleteById(id);
    }

    public TattooDto updateTattoo(Long id, TattooDto request) {
        return mapper.toDto(tattooRepository.findById(id)
                .map(tattoo -> mapper.toResource(request))
                .map(tattooRepository::save)
                .orElseThrow(() -> new TattooNotFoundException(id)));
    }
}
