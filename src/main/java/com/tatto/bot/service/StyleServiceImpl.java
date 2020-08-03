package com.tatto.bot.service;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.StyleRequest;
import com.tatto.bot.entity.Style;
import com.tatto.bot.mapper.StyleMapper;
import com.tatto.bot.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StyleServiceImpl {

    private final StyleRepository styleRepository;
    private final StyleMapper styleMapper;

    public List<StyleDto> getAll() {
        return styleMapper.mapListToDto(styleRepository.findAll());
    }

    public StyleDto create(StyleRequest styleDto) {
        Style style = styleMapper.toResource(styleDto);
        Style save = styleRepository.save(style);
        return styleMapper.toDto(save);
    }

    public StyleDto update(Long id, StyleRequest style) {
        Style styleDto = styleRepository.findById(id)
                .map(style1 -> {
                    style1.setStyle(style.getStyle());
                    return style1;
                })
                .map(styleRepository::save)
                .orElseThrow(() -> new NullPointerException("No style with this id"));
        return styleMapper.toDto(styleDto);
    }

    public void delete(Long id) {
        styleRepository.deleteById(id);
    }
}
