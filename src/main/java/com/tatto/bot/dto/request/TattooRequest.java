package com.tatto.bot.dto.request;

import com.tatto.bot.dto.interfaces.TattooInterface;
import com.tatto.bot.dto.TattooStyleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TattooRequest implements TattooInterface {
    private String picture;
    private String description;
    private String url;
    private TattooStyleDto style;
}
