package com.tatto.bot.service.impl;

import com.tatto.bot.dto.StyleDto;
import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;
import com.tatto.bot.entity.Style;
import com.tatto.bot.entity.Tattoo;
import com.tatto.bot.exeptions.TattooNotFoundException;
import com.tatto.bot.mapper.TattooMapper;
import com.tatto.bot.repository.TattooRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TattooServiceImplTest {

    private static final Style JAPAN = new Style(2L, "JAPAN");
    private static final Tattoo KOI_JAPAN = new Tattoo(1L, "Koi", "URL", "DESCRIPTION", JAPAN);

    private static final StyleDto JAPAN_DTO = new StyleDto(2L, "JAPAN");
    private static final StyleDto OLD_JAPAN_DTO = new StyleDto(1L, "OLD JAPAN");
    private static final TattooDto KOI_JAPAN_DTO = new TattooDto(1L, "Koi", "URL", "DESCRIPTION", JAPAN_DTO);
    private static final TattooDto UPDATED_DTO = new TattooDto(1L, "Hannya", "Hannya url", "Hannya description", OLD_JAPAN_DTO);

    private static final TattooRequest TATTOO_REQUEST = new TattooRequest("Hannya ", "Hannya description", "Hannya url", OLD_JAPAN_DTO);

    private static final List<Tattoo> TATTOO_LIST = Collections.singletonList(KOI_JAPAN);
    private static final List<TattooDto> TATTOO_LIST_DTO = Collections.singletonList(KOI_JAPAN_DTO);

    @Mock
    private TattooRepository repository;

    @Mock
    private TattooMapper tattooMapper;

    @InjectMocks
    private TattooServiceImpl service;

    @Test
    public void create() {
        when(repository.save(any())).thenReturn(KOI_JAPAN);
        when(tattooMapper.toDto(KOI_JAPAN)).thenReturn(KOI_JAPAN_DTO);
        TattooDto tattooDto = service.create(KOI_JAPAN_DTO);
        assertNotNull(tattooDto);
    }

    @Test
    public void getAll() {
        when(tattooMapper.mapListToDto(any())).thenReturn(TATTOO_LIST_DTO);
        when(repository.findAll()).thenReturn(TATTOO_LIST);
        List<TattooDto> tattoo = service.getAll();
        assertEquals(1, tattoo.size());
        assertNotNull(tattoo);
    }

    @Test
    public void update() {
        when(repository.findById(1L)).thenReturn(Optional.of(KOI_JAPAN));
        when(tattooMapper.setTattooRequestParam(TATTOO_REQUEST, KOI_JAPAN)).thenReturn(UPDATED_DTO);
        TattooDto tattooDto = service.update(1L, TATTOO_REQUEST);
        assertEquals("Hannya", tattooDto.getPicture());
        assertEquals("Hannya url", tattooDto.getUrl());
        assertEquals("Hannya description", tattooDto.getDescription());
        assertNotEquals(UPDATED_DTO.getStyle(), KOI_JAPAN_DTO.getStyle());
    }

    @Test
    public void delete() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void findByPicture() {
        when(tattooMapper.mapListToDto(any())).thenReturn(TATTOO_LIST_DTO);
        when(repository.findAll()).thenReturn(TATTOO_LIST);
        List<TattooDto> tattooList = service.findByPicture("kOI");
        assertNotNull(tattooList);
        assertEquals(tattooList, TATTOO_LIST_DTO);
    }

    @Test
    public void findByStyle() {
        when(tattooMapper.mapListToDto(any())).thenReturn(TATTOO_LIST_DTO);
        when(repository.findAll()).thenReturn(TATTOO_LIST);
        List<TattooDto> tattooList = service.findByStyle("japan");
        assertNotNull(tattooList);
        assertEquals(tattooList, TATTOO_LIST_DTO);
    }

    @Test
    public void throwPictureNotFoundException() {
        when(repository.findById(any())).thenThrow(new TattooNotFoundException(any()));
        assertThrows(TattooNotFoundException.class, () -> service.update(2L, TATTOO_REQUEST));
    }
}