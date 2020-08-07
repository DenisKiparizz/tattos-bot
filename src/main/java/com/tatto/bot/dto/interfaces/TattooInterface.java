package com.tatto.bot.dto.interfaces;

import com.tatto.bot.dto.TattooStyleDto;

public interface TattooInterface {

    String getPicture();

    String getUrl();

    String getDescription();

    TattooStyleDto getStyle();
}
