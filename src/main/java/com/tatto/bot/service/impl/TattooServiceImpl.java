package com.tatto.bot.service.impl;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;
import com.tatto.bot.entity.Tattoo;
import com.tatto.bot.exeptions.TattooNotFoundException;
import com.tatto.bot.mapper.StyleMapper;
import com.tatto.bot.mapper.TattooMapper;
import com.tatto.bot.repository.TattooRepository;
import com.tatto.bot.service.TattooService;
import com.tatto.bot.validation.TattooValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TattooServiceImpl implements TattooService {

    public final TattooRepository tattooRepository;
    public final TattooMapper mapper;
    public final TattooValidation validation;
    public final StyleMapper styleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TattooDto> getAll() {
        return mapper.mapListToDto(tattooRepository.findAll());
    }

    @Override
    public TattooDto create(TattooDto tattooDto) {
        validation.validate(tattooDto);
        Tattoo tattoo = mapper.toResource(tattooDto);
        Tattoo save = tattooRepository.save(tattoo);
        return mapper.toDto(save);
    }

    @Override
    public TattooDto update(Long id, TattooRequest request) {
        validation.checkPositiveId(id);
        validation.validate(request);
        return tattooRepository.findById(id)
                .map(tattoo -> mapper.setTattooRequestParam(request, tattoo))
                .orElseThrow(() -> new TattooNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        validation.checkPositiveId(id);
        tattooRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TattooDto> findByStyle(String style) {
        return mapper.mapListToDto(tattooRepository.findAll().stream()
                .filter(tattoo -> tattoo.getStyles().getStyle().equalsIgnoreCase(style))
                .collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TattooDto> findByPicture(String picture) {
        return mapper.mapListToDto(tattooRepository.findAll().stream()
                .filter(tattoo -> tattoo.getPicture().equalsIgnoreCase(picture))
                .collect(Collectors.toList()));
    }
}
