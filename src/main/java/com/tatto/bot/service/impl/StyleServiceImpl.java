package com.tatto.bot.service.impl;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.request.StyleRequest;
import com.tatto.bot.entity.Style;
import com.tatto.bot.exeptions.StyleNotFoundException;
import com.tatto.bot.mapper.StyleMapper;
import com.tatto.bot.repository.StyleRepository;
import com.tatto.bot.service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;
    private final StyleMapper styleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StyleDto> getAll() {
        return styleMapper.mapListToDto(styleRepository.findAll());
    }

    @Override
    public StyleDto create(StyleDto styleDto) {
        Style style = styleMapper.toResource(styleDto);
        Style save = styleRepository.save(style);
        return styleMapper.toDto(save);
    }

    @Override
    public StyleDto update(Long id, StyleRequest styleRequest) {
        return styleRepository.findById(id)
                .map(style -> styleMapper.setStyleRequestParam(styleRequest, style))
                .orElseThrow(() -> new StyleNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        styleRepository.deleteById(id);
    }
}