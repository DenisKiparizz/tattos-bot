package com.tatto.bot.service;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.entity.prod.Tattoo;
import com.tatto.bot.mapper.TattooMapper;
import com.tatto.bot.repository.TattooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public TattooDto findByStyle(String style) {
        return mapper.toDto(tattooRepository.findAll().stream()
                .filter(tattoo ->
                        tattoo.getStyle().getStyle().equalsIgnoreCase(style))
                .findFirst().orElseThrow(() ->
                        new NullPointerException("No this style")));
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
                .orElseThrow(() -> new NullPointerException("Tattoo with this id not found")));
    }
}
