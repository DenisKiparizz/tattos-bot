package com.tatto.bot.service.impl;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.request.StyleRequest;
import com.tatto.bot.entity.Style;
import com.tatto.bot.exeptions.StyleNotFoundException;
import com.tatto.bot.mapper.StyleMapper;
import com.tatto.bot.repository.StyleRepository;
import com.tatto.bot.validation.StyleValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StyleServiceImplTest {

    private static final Style JAPAN = new Style(1L, "JAPAN");
    private static final Style HARDCORE = new Style(2L, "HARDCORE");
    private static final Style TRAD = new Style(3L, "TRAD");

    private static final StyleDto JAPAN_DTO = new StyleDto(1L, "JAPAN");
    private static final StyleDto HARDCORE_DTO = new StyleDto(2L, "HARDCORE");
    private static final StyleDto TRAD_DTO = new StyleDto(3L, "TRAD");

    private static final StyleRequest JAPAN_REQUEST = new StyleRequest();

    private final static List<Style> STYLE_LIST = Arrays.asList(
            JAPAN, HARDCORE, TRAD);

    private final static List<StyleDto> STYLE_LIST_DTO = Arrays.asList(
            JAPAN_DTO, HARDCORE_DTO, TRAD_DTO);
    @Mock
    private StyleValidation validation;

    @Mock
    private StyleRepository repository;

    @Mock
    private StyleMapper mapper;

    @InjectMocks
    private StyleServiceImpl service;

    @Test
    public void create() {
        when(repository.save(any())).thenReturn(JAPAN);
        when(mapper.toDto(JAPAN)).thenReturn(JAPAN_DTO);
        StyleDto styleDto = service.create(JAPAN_DTO);
        assertNotNull(styleDto);
    }


    @Test
    public void getAll() {
        when(mapper.mapListToDto(any())).thenReturn(STYLE_LIST_DTO);
        when(repository.findAll()).thenReturn(STYLE_LIST);
        List<StyleDto> styles = service.getAll();
        assertEquals(3, styles.size());
        assertNotNull(styles);
    }

    @Test
    public void update() {
        when(repository.findById(1L)).thenReturn(Optional.of(JAPAN));
        when(mapper.setStyleRequestParam(JAPAN_REQUEST, JAPAN)).thenReturn(JAPAN_DTO);
        StyleDto styleDto = service.update(1L, JAPAN_REQUEST);
        assertEquals("JAPAN", styleDto.getStyle());
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void throwStyleNotFoundException() {
        when(repository.findById(any())).thenThrow(new StyleNotFoundException(any()));
        assertThrows(StyleNotFoundException.class, () -> service.update(2L, JAPAN_REQUEST));
    }
}
