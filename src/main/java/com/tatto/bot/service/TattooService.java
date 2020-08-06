package com.tatto.bot.service;

import com.tatto.bot.dto.TattooDto;
import com.tatto.bot.dto.request.TattooRequest;

import java.util.List;

public interface TattooService extends CrudService<TattooDto, TattooRequest> {
    List<TattooDto> findByStyle(String style);

    List<TattooDto> findByPicture(String style);
}
