package com.tatto.bot.dto.request;

import com.tatto.bot.dto.interfaces.StyleInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StyleRequest implements StyleInterface {
    private String style;
}
