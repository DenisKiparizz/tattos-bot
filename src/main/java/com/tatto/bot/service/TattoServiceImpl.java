package com.tatto.bot.service;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.entity.prod.Tattoo;
import com.tatto.bot.mapper.TattooMapper;
import com.tatto.bot.repository.TatooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TattoServiceImpl {

    @Autowired
    TatooRepository tatooRepository;

    @Autowired
    TattooMapper mapper;

    public TattooDto create(TattooDto tattooDto) {
        Tattoo tattoo = mapper.toResource(tattooDto);
        Tattoo save = tatooRepository.save(tattoo);
        return mapper.toDto(save);
    }

    public List<TattooDto> getAll() {
        return mapper.mapListToDto(tatooRepository.findAll());
    }

    public void delete(Long id) {
        tatooRepository.deleteById(id);
    }

    public TattooDto updateTattoo(Long id, TattooDto request) {
        return mapper.toDto(tatooRepository.findById(id)
                .map(tattoo -> mapper.toResource(request))
                .map(tattoo -> tatooRepository.save(tattoo))
                .orElseThrow(() -> new NullPointerException("Tattoo with this id not found")));
    }
}
